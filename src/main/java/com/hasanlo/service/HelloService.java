package com.hasanlo.service;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Component
@Path("/hello")
public class HelloService {

    @GET
    public Response hello(){
        return Response.ok("hello").build();
    }
}
