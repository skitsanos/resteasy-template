package com.skitsanos.apps.restapi.utils;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public final class JsonResponse {
    private final int code;
    private final Object payload;

    public JsonResponse(int code, Object payload) {
        this.code = code;
        this.payload = payload;
    }

    public Response json() {
        Response.ResponseBuilder builder = Response.status(code);
        if (payload == null) {
            return builder.build();
        }

        return builder
                .entity(payload)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
