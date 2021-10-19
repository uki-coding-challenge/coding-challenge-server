package org.ukidevelopers;

import java.util.Collections;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/developer")
@ApplicationScoped
public class DeveloperResource {

    private static final Logger LOG = LoggerFactory.getLogger(DeveloperResource.class);

    @Channel("display")
    Emitter<DeveloperListModel> displayChannel;

    @POST
    @Path("/{username}")
    public Response addDeveloper(@PathParam("username") String username, @QueryParam("tier0") boolean tier0, @QueryParam("tier1") boolean tier1, @QueryParam("tier2") boolean tier2){
        DeveloperModel developer = new DeveloperModel();
        developer.setUsername(username);
        developer.setTier0(tier0);
        developer.setTier1(tier1);
        developer.setTier2(tier2);

        LOG.info("Developer {}",  developer);

        DeveloperListModel developersList = new DeveloperListModel();
        developersList.setDevelopers(Collections.singletonMap(username, developer));

        displayChannel.send(developersList);

        return Response.ok().build();

    }

    
}
