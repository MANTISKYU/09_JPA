package com.ohgiraffers.section07.subquery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SubQueryTests {


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

    @Test
    @DisplayName("서브쿼리를 이용한 메뉴 조회 테스트")
    void test() {

        // given
        String categoryNameParameter = "한식";

        // when
        String jpql = """
                select
                m
                from menu_section07 m
                where m.categoryCode = (select c.categoryCode
                                        from category_section07 c
                                        where c.categoryName = :categoryName)
                
                """;

        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class)
                .setParameter("categoryName", categoryNameParameter)
                .getResultList();


        // then
        assertNotNull(menuList);
        menuList.forEach(System.out::println);

    }



}
