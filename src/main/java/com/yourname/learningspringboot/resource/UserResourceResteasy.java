package com.yourname.learningspringboot.resource;

import com.yourname.learningspringboot.model.User;
import com.yourname.learningspringboot.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Component
@Path("api/v1/users")
public class UserResourceResteasy {

    private UserService userService;

    @Autowired
    public UserResourceResteasy(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<User> fetchUsers(@QueryParam("gender") String gender, @QueryParam("ageLessThan") Integer ageLessThan) {
        return userService.getAllUsers(Optional.ofNullable(gender));
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{userUid}")
    public Response fetchUser(@PathParam("userUid") UUID userUid) {

        return userService.getUser(userUid).map((user) -> Response.ok().build())
                .orElseGet(() -> Response.noContent().build());
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response insertNewUser(User user) {
        int result = userService.insertUser(user);
        return getIntegerResponseEntity(result);
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateUser(User user) {
        int result = userService.updateUser(user);
        return getIntegerResponseEntity(result);
    }

    @DELETE
    @Produces(APPLICATION_JSON)
    @Path("{userUid}")
    public Response deleteUser(@PathParam("userUid") UUID userUid) {
        int result = userService.removeUser(userUid);
        return getIntegerResponseEntity(result);
    }

    private Response getIntegerResponseEntity(int result) {
        if (result == 1) {
            return Response.ok().build();
        }
        return Response.status(NOT_FOUND).build();
    }
}
