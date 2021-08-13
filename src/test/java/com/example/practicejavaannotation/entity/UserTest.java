package com.example.practicejavaannotation.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserTest {

    private final Logger log = LoggerFactory.getLogger(UserTest.class);

    @Autowired
    EntityManagerFactory factory;

    @Test
    void testSaveUser() {
        User user = getMockUser();
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        log.info("user id : {}", user.getId());
        User _user = em.find(User.class, user.getId());
        Assertions.assertThat(_user).isNotNull();
        log.info("user : {}", _user);
        em.close();
    }


    @Test
    void testSaveUserAndUserProfile() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        User user = getMockUser();
        em.persist(user);
        UserProfile userProfile = new UserProfile(user, "nickname", "http://", Instant.now().getEpochSecond());
        em.persist(userProfile);
        tx.commit();
        em.close();

        EntityManager em2 = factory.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();
        tx2.begin();
        User _user = em2.find(User.class, user.getId());
        Assertions.assertThat(_user).isNotNull();
        Optional<UserProfile> _userProfile = _user.getUserProfiles().stream().findFirst();
        Assertions.assertThat(_userProfile.isPresent()).isTrue();
        log.info("user : {}, userProfile : {}", user, userProfile);
        tx2.commit();
        em2.close();
    }

    private User getMockUser() {
        return new User("kimsikkong", 30, "address", "01012345678", Instant.now().getEpochSecond());
    }
}