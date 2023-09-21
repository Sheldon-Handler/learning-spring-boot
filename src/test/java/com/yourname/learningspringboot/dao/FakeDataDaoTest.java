package com.yourname.learningspringboot.dao;

import com.yourname.learningspringboot.model.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;

    @BeforeEach
    public void setUp() throws Exception {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    public void shouldSelectAllUsers() throws Exception {
        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(1);

        User user = users.get(0);

        assertThat(user.getAge()).isEqualTo(22);
        assertThat(user.getFirstName()).isEqualTo("Joe");
        assertThat(user.getLastName()).isEqualTo("Jones");
        assertThat(user.getGender()).isEqualTo(User.Gender.MALE);
        assertThat(user.getEmail()).isEqualTo("joe.jones@gmail.com");
        assertThat(user.getUserUid()).isNotNull();
    }

    @Test
    public void shouldSelectUserByUserUid() throws Exception {
        UUID annaUserUid = UUID.randomUUID();
        User anna = new User(annaUserUid, "anna",
                "montana", User.Gender.FEMALE, 30,"anna@gmail.com");
        fakeDataDao.insertUser(annaUserUid, anna);
        assertThat(fakeDataDao.selectAllUsers()).hasSize(2);

        Optional<User> annaOptional = fakeDataDao.selectUserByUserUid(annaUserUid);
        assertThat(annaOptional.isPresent()).isTrue();
        assertThat(annaOptional.get()).isEqualToComparingFieldByField(anna);
    }

    @Test
    public void shouldNotSelectUserByUserUid() throws Exception {
        Optional<User> user = fakeDataDao.selectUserByUserUid(UUID.randomUUID());
        assertThat(user.isPresent()).isFalse();
    }

    @Test
    public void updateUser() throws Exception {
    }

    @Test
    public void deleteUserByUserUid() throws Exception {
    }

    @Test
    public void insertUser() throws Exception {
    }
}