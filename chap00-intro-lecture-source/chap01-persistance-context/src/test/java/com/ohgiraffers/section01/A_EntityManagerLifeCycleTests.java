package com.ohgiraffers.section01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerLifeCycleTests {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @DisplayName("엔티티 매니저 팩토리와 엔티티 매니저 생명주기 확인1")
    @Test
    public void lifeCycle1() {
        System.out.println("entityManagerFactory.hashCode() = "
        + entityManagerFactory.hashCode());
        System.out.println("entityManager.hashCode = " + entityManager.hashCode());
    }

    @DisplayName("엔티티 매니저 팩토리와 엔티티 매니저 생명주기 확인2")
    @Test
    public void lifeCycle2() {
        System.out.println("entityManagerFactory.hashCode() = "
                + entityManagerFactory.hashCode());
        System.out.println("entityManager.hashCode = " + entityManager.hashCode());
    }

}
