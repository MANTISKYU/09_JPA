package com.ohgiraffers.section02.parameter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParameterBindingTests {


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
    @DisplayName("이름 기준 파라미터 바인딩 메뉴 목록 조회 테스트")
    void test1() {

        // given
        String menuNameParameter = "홍어마카롱";
        // when
        String jpql = "select m from menu_section02 m where m.menuName = :menuName";
        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class)
                .setParameter("menuName", menuNameParameter).getResultList();
        // then
        assertNotNull(menuList);
        menuList.forEach(System.out::println);



    }

    @Test
    @DisplayName("위치 기준 파라미터 바인딩 메뉴 목록 조회 테스트")
    void test2() {

        // given
        String menuNameParameter = "홍어마카롱";
        // when
        String jpql = "select m from menu_section02 m where m.menuName = ?1";
        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class)
                .setParameter(1, menuNameParameter).getResultList();
        // then
        assertNotNull(menuList);
        menuList.forEach(System.out::println);



    }

}
