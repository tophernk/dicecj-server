package cj.dice.service;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Stateless
@Path("/hello")
public class HelloResource {

    @POST
    public String hello(String name) {
        return "Hello " + name;
    }
}
