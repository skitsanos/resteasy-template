package com.skitsanos.apps.restapi.api;

import com.skitsanos.apps.restapi.utils.JsonResponse;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        return new JsonResponse(200, result).json();
    }

    @GET
    @Path("/echo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response echo(@Context HttpHeaders requestHeaders)
    {
        List<String> headersParsed = new ArrayList<>(requestHeaders.getRequestHeaders().keySet());

        var result = new Object()
        {
            final String[] headers = headersParsed.toArray(new String[0]);
        };

        JsonResponse r = new JsonResponse(200, result);
        return r.json();
    }

    @GET
    @Path("/info")
    public Response info()
    {
        return new JsonResponse(200, "It is working").json();
    }
}
