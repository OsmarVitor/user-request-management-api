package com.user.managementuserapi.Repository;

import com.user.managementuserapi.domain.User;
import com.user.managementuserapi.domain.enums.Role;
import com.user.managementuserapi.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void saveUser(){
        User userSaved = repository.save(new User(null, "user", "user1@email.com", "user1", Role.ADMIN, null, null));

        Assert.assertEquals("user1@email.com", userSaved.getEmail());
        Assert.assertEquals("user", userSaved.getName());
        Assert.assertEquals("user1", userSaved.getPassword());
        Assert.assertEquals(Role.ADMIN, userSaved.getRole());
    }

    @Test
    public void updateUser(){
        User userToUpdate = repository.save(new User(null, "user", "userToUpdate@email.com", "user1", Role.ADMIN, null, null));
        Assert.assertEquals("userToUpdate@email.com", userToUpdate.getEmail());

        userToUpdate.setEmail("user-to-update@email.com");

        User userUpdated = repository.save(userToUpdate);
        Assert.assertEquals("user-to-update@email.com", userToUpdate.getEmail());
    }

    @Test
    public void listUsers(){
        User userOne = repository.save(new User(null, "userOne", "user1@email.com", "user1", Role.ADMIN, null, null));
        User userTwo = repository.save(new User(null, "userTwo", "user2@email.com", "user2", Role.CONSULTANT, null, null));
        User userThree = repository.save(new User(null, "userThree", "user3@email.com", "user3", Role.CONSULTANT, null, null));

        List<User> users = repository.findAll();

        Assert.assertEquals(users.get(0).getName(), userOne.getName());
        Assert.assertEquals(users.get(0).getEmail(), userOne.getEmail());
        Assert.assertEquals(users.get(0).getPassword(), userOne.getPassword());
        Assert.assertEquals(users.get(0).getRole(), userOne.getRole());

        Assert.assertEquals(users.get(1).getName(), userTwo.getName());
        Assert.assertEquals(users.get(1).getEmail(), userTwo.getEmail());
        Assert.assertEquals(users.get(1).getPassword(), userTwo.getPassword());
        Assert.assertEquals(users.get(1).getRole(), userTwo.getRole());

        Assert.assertEquals(users.get(2).getName(), userThree.getName());
        Assert.assertEquals(users.get(2).getEmail(), userThree.getEmail());
        Assert.assertEquals(users.get(2).getPassword(), userThree.getPassword());
        Assert.assertEquals(users.get(2).getRole(), userThree.getRole());
    }

    @Test
    public void login(){
        User userToLogin = repository.save(new User(null, "user", "user1@email.com", "user1", Role.ADMIN, null, null));

        Optional<User> userLogged = repository.login(userToLogin.getEmail(), userToLogin.getPassword());

        Assert.assertEquals(userLogged.isPresent(), true);
        Assert.assertEquals(userLogged.get().getName(), userToLogin.getName());
        Assert.assertEquals(userLogged.get().getEmail(), userToLogin.getEmail());
        Assert.assertEquals(userLogged.get().getPassword(), userToLogin.getPassword());
        Assert.assertEquals(userLogged.get().getRole(), userToLogin.getRole());
    }
}
