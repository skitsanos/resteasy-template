package com.skitsanos.apps.restapi.api;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/")
public class ApiEndpoints
{
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response index()
    {
        var result = new Object()
        {
            final String info = String.format("REST API (%s) v.%s. Runtime v.%s (%s)",
                    this.getClass().getPackage().getImplementationTitle(),
                    this.getClass().getPackage().getImplementationVersion(),
                    System.getProperty("java.runtime.version"),
                    System.getProperty("java.vendor"));
        };

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        try
        {
            String data = mapper.writeValueAsString(result);
            return Response.ok(data).build();
        } catch (IOException e)
        {
            return Response.status(500).entity(e.toString()).build();
        }
    }

    @GET
    @Path("/info")
    public Response info()
    {
        return Response.status(200).entity("It is working!").build();
    }
}
