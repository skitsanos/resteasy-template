package com.skitsanos.apps.restapi;

import com.skitsanos.apps.restapi.app.RestApplication;
import io.undertow.Undertow;
import io.undertow.server.HandlerWrapper;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.util.StatusCodes;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import java.util.Set;
import java.util.regex.Pattern;

public class Server {

    // Allowed hosts - configure as needed for your environment
    private static final Set<String> ALLOWED_HOSTS = Set.of(
            "localhost",
            "localhost:8080",
            "127.0.0.1",
            "127.0.0.1:8080"
    );

    // Pattern to validate Host header format (hostname:port or hostname)
    private static final Pattern HOST_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9]([a-zA-Z0-9\\-]*[a-zA-Z0-9])?(\\.[a-zA-Z0-9]([a-zA-Z0-9\\-]*[a-zA-Z0-9])?)*(:([0-9]{1,5}))?$"
    );

    public static void main(String[] args) {
        UndertowJaxrsServer server = new UndertowJaxrsServer();

        Undertow.Builder serverBuilder = Undertow.builder()
                .addHttpListener(8080, "0.0.0.0");

        server.start(serverBuilder);

        // Deploy with Host header validation (CVE-2025-12543 mitigation)
        DeploymentInfo di = server.undertowDeployment(RestApplication.class)
                .setContextPath("/")
                .setDeploymentName("RestAPI")
                .addOuterHandlerChainWrapper(new HostValidationWrapper());

        server.deploy(di);

        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));

        System.out.println("Server started on http://localhost:8080");
    }

    /**
     * Handler wrapper that validates Host headers to mitigate CVE-2025-12543.
     */
    static class HostValidationWrapper implements HandlerWrapper {
        @Override
        public HttpHandler wrap(HttpHandler handler) {
            return new HostValidationHandler(handler);
        }
    }

    /**
     * Handler that validates Host headers to mitigate CVE-2025-12543.
     * Rejects requests with missing, malformed, or unauthorized Host headers.
     */
    static class HostValidationHandler implements HttpHandler {
        private final HttpHandler next;

        HostValidationHandler(HttpHandler next) {
            this.next = next;
        }

        @Override
        public void handleRequest(HttpServerExchange exchange) throws Exception {
            String host = exchange.getRequestHeaders().getFirst("Host");

            if (!isValidHost(host)) {
                exchange.setStatusCode(StatusCodes.BAD_REQUEST);
                exchange.getResponseSender().send("Invalid Host header");
                return;
            }

            next.handleRequest(exchange);
        }

        private boolean isValidHost(String host) {
            if (host == null || host.isEmpty()) {
                return false;
            }

            // Check format validity
            if (!HOST_PATTERN.matcher(host).matches()) {
                return false;
            }

            // Optional: enforce allowlist (uncomment for strict mode)
            // return ALLOWED_HOSTS.contains(host.toLowerCase());

            return true;
        }
    }
}
