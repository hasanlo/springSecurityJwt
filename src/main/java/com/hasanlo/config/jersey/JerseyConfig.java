package com.hasanlo.config.jersey;

import com.hasanlo.service.HelloService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

    @Autowired
    public JerseyConfig() {
        register(HelloService.class);
    }
}
