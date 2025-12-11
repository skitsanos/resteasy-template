/**
 * REST Application
 *
 * @author skitsanos
 */
package com.skitsanos.apps.restapi.app;

import com.skitsanos.apps.restapi.api.ApiEndpoints;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;

public class RestApplication extends Application {
    private final Set<Object> singletons = new HashSet<>();

    public RestApplication() {
        singletons.add(new ApiEndpoints());
        singletons.add(new ResteasyJackson2Provider());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
