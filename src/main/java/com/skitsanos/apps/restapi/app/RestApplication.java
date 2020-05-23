/**
 * REST Application
 *
 * @author skitsanos
 */
package com.skitsanos.apps.restapi.app;

import com.skitsanos.apps.restapi.api.ApiEndpoints;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class RestApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public RestApplication() {
        // Register our hello service
        singletons.add(new ApiEndpoints());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
