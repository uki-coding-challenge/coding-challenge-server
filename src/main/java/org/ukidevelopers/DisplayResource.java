package org.ukidevelopers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.reactive.RestSseElementType;

import io.smallrye.mutiny.Multi;

@Path("/display")
@ApplicationScoped
public class DisplayResource {

    @Inject
    @Channel("display")
    Multi<DeveloperListModel> display;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestSseElementType(MediaType.APPLICATION_JSON)
    public Multi<DeveloperListModel> display(){
        return display;
    }

    
}
