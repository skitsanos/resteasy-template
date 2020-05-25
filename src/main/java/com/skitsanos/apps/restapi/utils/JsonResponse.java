package com.skitsanos.apps.restapi.utils;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.Response;
import java.io.IOException;

public final class JsonResponse
{
    protected int code = 200;
    protected Object payload = null;

    public JsonResponse(int code, Object payload)
    {
        this.code = code;
        this.payload = payload;
    }

    public Response json()
    {
        if (payload == null)
        {
            return Response.noContent().entity("Payload is empty").build();
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        try
        {
            String data = mapper.writeValueAsString(this.payload);
            return Response.ok(data).build();
        } catch (IOException e)
        {
            return Response.status(500).entity(e.toString()).build();
        }
    }
}
