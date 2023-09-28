package com.yourname.learningspringboot;

import com.yourname.learningspringboot.clientproxy.UserResourceV1;
import com.yourname.learningspringboot.model.User;
import com.yourname.learningspringboot.resource.UserResourceResteasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

import static com.yourname.learningspringboot.model.User.Gender.FEMALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class LearningSpringBootApplicationTests {

    @Autowired
    private UserResourceResteasy userResourceResteasy;

//    @Test
//    public void shouldDeleteUser() throws Exception {
//
//        // Given
//        UUID userUid = UUID.randomUUID();
//        User user = new User(userUid, "Joe", "Jones",
//                User.Gender.MALE, 22, "joe.jones@gmail.com");
//
//        // When
//        userResourceResteasy.insertNewUser(user);
//
//        // Then
//        User joe = userResourceResteasy.fetchUser(userUid);
//        assertThat(joe).isEqualToComparingFieldByField(user);
//
//        // When
//        userResourceResteasy.deleteUser(userUid);
//
//        // Then
//        assertThatThrownBy(() -> userResourceResteasy.fetchUser(userUid))
//                .isInstanceOf(NotFoundException.class);
//    }

    @Test
    public void shouldUpdateUser() throws Exception {
        // Given
        UUID userUid = UUID.randomUUID();
        User user = new User(userUid, "Joe", "Jones",
                User.Gender.MALE, 22, "joe.jones@gmail.com");

        // When
        userResourceResteasy.insertNewUser(user);

        User updatedUser = new User(userUid, "Alex", "Jones",
                User.Gender.MALE, 22, "alex.jones@gmail.com");

        userResourceResteasy.updateUser(updatedUser);

        user = userResourceResteasy.fetchUser(userUid);
        assertThat(user).isEqualToComparingFieldByField(updatedUser);

    }

    @Test
    public void shouldFetchUsersByGender() throws Exception {
        // Given
        UUID userUid = UUID.randomUUID();
        User user = new User(userUid, "Joe", "Jones",
                User.Gender.MALE, 22, "joe.jones@gmail.com");

        // When
        userResourceResteasy.insertNewUser(user);

        List<User> females = userResourceResteasy.fetchUsers("female", null);

        assertThat(females).extracting("userUid").doesNotContain(user.getUserUid());
        assertThat(females).extracting("firstName").doesNotContain(user.getFirstName());
        assertThat(females).extracting("lastName").doesNotContain(user.getLastName());
        assertThat(females).extracting("gender").doesNotContain(user.getGender());
        assertThat(females).extracting("age").doesNotContain(user.getAge());
        assertThat(females).extracting("email").doesNotContain(user.getEmail());

        List<User> males = userResourceResteasy.fetchUsers(User.Gender.MALE.name(), null);

        assertThat(males).extracting("userUid").contains(user.getUserUid());
        assertThat(males).extracting("firstName").contains(user.getFirstName());
        assertThat(males).extracting("lastName").contains(user.getLastName());
        assertThat(males).extracting("gender").contains(user.getGender());
        assertThat(males).extracting("age").contains(user.getAge());
        assertThat(males).extracting("email").contains(user.getEmail());
    }
}
