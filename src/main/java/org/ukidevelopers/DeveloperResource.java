package org.ukidevelopers;

import java.util.Collections;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@Path("/developer")
@ApplicationScoped
public class DeveloperResource {

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

        DeveloperListModel developersList = new DeveloperListModel();
        developersList.setDevelopers(Collections.singletonMap(username, developer));

        displayChannel.send(developersList);

        return Response.ok().build();

    }

    
}
