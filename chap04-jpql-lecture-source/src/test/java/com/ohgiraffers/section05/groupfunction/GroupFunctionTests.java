package com.ohgiraffers.section05.groupfunction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupFunctionTests {

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
    @DisplayName("특정 카테고리의 등록된 메뉴 수 조회")
    void test1() {
        // given
        int categoryCodeParameter = 2;
        // when
        String jpql = """
                select count(m.menuPrice)
                from menu_section05 m
                where m.categoryCode = :categoryCode
                """;
        long countOfMenu = entityManager.createQuery(jpql, long.class)
                .setParameter("categoryCode", categoryCodeParameter)
                .getSingleResult();

        // then
        assertTrue(countOfMenu >=0);
        System.out.println("countOfMenu = " + countOfMenu);


    }


    @Test
    @DisplayName("count를 제외한 다른 그룹함수의 조회결과가 없는 경우 테스트")
    void test2() {
        // given
        int categoryCodeParameter = 2;
        // when
        String jpql = """
                select sum(m.menuPrice)
                from menu_section05 m
                where m.categoryCode = :categoryCode
              """;
//        long countOfMenu = entityManager.createQuery(jpql, long.class)
//                .setParameter("categoryCode", categoryCodeParameter)
//                .getSingleResult();

        // then
//        assertTrue(countOfMenu >=0);
//        System.out.println("countOfMenu = " + countOfMenu);

        // then
        assertThrows(NullPointerException.class, () -> {
            long sumOfMenu = entityManager.createQuery(jpql, long.class)
                    .setParameter("categoryCode", categoryCodeParameter)
                    .getSingleResult();


        });


    }

}
