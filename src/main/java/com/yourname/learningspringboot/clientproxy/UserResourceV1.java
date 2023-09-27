package com.yourname.learningspringboot.clientproxy;

import com.yourname.learningspringboot.model.User;
import jakarta.annotation.Resource;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Resource
public interface UserResourceV1 {

    @GET
    @Produces(APPLICATION_JSON)
    public List<User> fetchUsers(@QueryParam("gender") String gender);

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{userUid}")
    public Response fetchUser(@PathParam("userUid") UUID userUid);

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response insertNewUser(User user);

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateUser(User user);

    @DELETE
    @Produces(APPLICATION_JSON)
    @Path("{userUid}")
    public Response deleteUser(@PathParam("userUid") UUID userUid);
}
