package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BiDirectionTests {


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
    @DisplayName("양방향 연관관계 매핑 조회 테스트")
    public void test1() {

        // given
        int menuCode = 10;
        int categoryCode = 10;
        // when
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        Category foundCategory = entityManager.find(Category.class, categoryCode);

        // then
        System.out.println(foundMenu);
        System.out.println(foundCategory);

        for(Menu menu : foundCategory.getMenuList()) {

            System.out.println(menu);
        }
    }

    @Test
    @DisplayName("양방향 연관관계 주인 객체를 이용한 삽입 테스트")
    public void test2() {

        // given
        Menu menu = new Menu();
        menu.setMenuCode(125);
        menu.setMenuName("연관관계주인메뉴");
        menu.setMenuPrice(10000);
        menu.setOrderableStatus("Y");

        menu.setCategory(entityManager.find(Category.class, 4));
        // when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(menu);
        transaction.commit();
        // then
        Menu foundMenu = entityManager.find(Menu.class, menu.getMenuCode());
        assertEquals(menu.getMenuCode(), foundMenu.getMenuCode());
        System.out.println(foundMenu);
    }

    @Test
    @DisplayName("양방향 연관관계 주인이 아닌 객체를 이용한 삽입 테스트")
    public void test3() {

        // given
        Category category = new Category();
        category.setCategoryCode(1004);
        category.setCategoryName("양방향카테고리");
        category.setRefCategoryCode(null);

        // when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(category);
        transaction.commit();
        // then
        Category foundCategory = entityManager.find(Category.class, category.getCategoryCode());
        assertEquals(category.getCategoryCode(), foundCategory.getCategoryCode());
        System.out.println(foundCategory);
    }

}
