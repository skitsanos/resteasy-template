package com.skitsanos.apps.restapi.api;

import com.skitsanos.apps.restapi.utils.JsonResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/")
public class ApiEndpoints {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response index() {
        Map<String, String> result = Map.of(
                "info", String.format("REST API (%s) v.%s. Runtime v.%s (%s)",
                        this.getClass().getPackage().getName(),
                        this.getClass().getPackage().getImplementationVersion(),
                        System.getProperty("java.runtime.version"),
                        System.getProperty("java.vendor"))
        );

        return new JsonResponse(200, result).json();
    }

    @GET
    @Path("/echo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response echo(@Context HttpHeaders requestHeaders) {
        List<String> headersParsed = new ArrayList<>(requestHeaders.getRequestHeaders().keySet());

        Map<String, Object> result = Map.of(
                "headers", headersParsed
        );

        return new JsonResponse(200, result).json();
    }

    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response info() {
        return new JsonResponse(200, "It is working").json();
    }
}
