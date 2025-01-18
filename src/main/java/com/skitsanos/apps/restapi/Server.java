package com.skitsanos.apps.restapi;

import com.skitsanos.apps.restapi.app.RestApplication;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import io.undertow.Undertow;

public class Server {
    public static void main(String[] args) {
        UndertowJaxrsServer server = new UndertowJaxrsServer();

        Undertow.Builder serverBuilder = Undertow.builder()
                .addHttpListener(8080, "0.0.0.0");

        server.start(serverBuilder);

        // Deploy our application
        server.deploy(RestApplication.class);

        System.out.println("Server started on http://localhost:8080");

        // Keep the server running
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                server.stop();
                break;
            }
        }
    }
}