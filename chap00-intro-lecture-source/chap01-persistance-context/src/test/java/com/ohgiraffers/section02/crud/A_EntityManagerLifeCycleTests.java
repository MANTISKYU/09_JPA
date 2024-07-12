package com.ohgiraffers.section02.crud;


import jakarta.persistence.*;
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

    @DisplayName("메뉴코드로 메뉴 조회 테스트")
    @Test
    public void selectMenuByMenuCodeTest() {

        // given
        int menuCode = 4;

        // when
        Menu menu = entityManager.find(Menu.class, menuCode);

        // then
        Assertions.assertNotNull(menu);

        Assertions.assertEquals(menuCode, menu.getMenuCode());
        System.out.println("menu" + menu);
        Menu menu2 = entityManager.find(Menu.class, menuCode);


    }

    @DisplayName("새로운 메뉴 추가 테스트")
    @Test
    public void insertNewMenuTest() {

        //given

        Menu menu = new Menu();
        menu.setMenuName("JPA 테스트용 신규 메뉴");
        menu.setMenuPrice(50000);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("Y");


        //when

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {

            entityManager.persist(menu);
            transaction.commit();
        } catch (Exception e) {

            transaction.rollback();
            e.printStackTrace();

        }

        //then
        Assertions.assertTrue(entityManager.contains(menu));

    }

    @DisplayName("메뉴 이름 수정 테스트")
    @Test
    public void modifyMenuNameTest() {

        // given
        Menu menu = entityManager.find(Menu.class, 23);
        System.out.println("menu" + menu);

        String menuNameChange = "생갈치스무디";
        //when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();


        try {
            menu.setMenuName(menuNameChange);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        // then

        Assertions.assertEquals(menuNameChange, menu.getMenuName());

        Assertions.assertEquals(menuNameChange, entityManager.find(Menu.class, 23));

    }

    @DisplayName("메뉴 삭제하기 테스트")
    @Test
    public void deleteMenu() {

        //given
        Menu menuToRemove = entityManager.find(Menu.class, 23); // 영속화
        System.out.println("menuToRemove = " + menuToRemove);

        //when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            entityManager.remove(menuToRemove);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

        //then
        Menu removedMenu = entityManager.find(Menu.class, 23);
        Assertions.assertEquals(null, removedMenu);


    }
}
