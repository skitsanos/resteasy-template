package com.skitsanos.apps.restapi;

import com.skitsanos.apps.restapi.app.RestApplication;
import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

public class Server {
    public static void main(String[] args) {
        UndertowJaxrsServer server = new UndertowJaxrsServer();

        Undertow.Builder serverBuilder = Undertow.builder()
                .addHttpListener(8080, "0.0.0.0");

        server.start(serverBuilder);

        server.deploy(RestApplication.class);

        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));

        System.out.println("Server started on http://localhost:8080");
    }
}
