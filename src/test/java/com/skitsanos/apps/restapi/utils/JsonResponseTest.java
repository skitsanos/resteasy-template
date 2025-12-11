package com.skitsanos.apps.restapi.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;
import org.junit.jupiter.api.Test;

class JsonResponseTest {

    @Test
    void returnsStatusAndJsonTypeWhenPayloadPresent() {
        Response response = new JsonResponse(200, Map.of("hello", "world")).json();

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
        assertEquals(Map.of("hello", "world"), response.getEntity());
    }

    @Test
    void returnsStatusWithoutEntityWhenPayloadNull() {
        Response response = new JsonResponse(204, null).json();

        assertEquals(204, response.getStatus());
        assertNull(response.getEntity());
        assertNull(response.getMediaType());
    }
}
