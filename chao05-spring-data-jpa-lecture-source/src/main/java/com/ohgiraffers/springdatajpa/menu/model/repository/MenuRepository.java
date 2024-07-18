package com.ohgiraffers.springdatajpa.menu.model.repository;

import com.ohgiraffers.springdatajpa.menu.model.entity.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

//    List<Menu> findByMenuPriceGreaterThan(Integer menuPrice, Sort menuPrice1);
//    List<Menu> findByMenuPriceGreaterThanOrderByMenuPriceDesc(Integer menuPrice);
//    List<Menu> findByMenuPriceEqualsOrderByMenuCode(Integer menuPrice);
    List<Menu> findByMenuPriceLessThanOrderByMenuCode(Integer menuPrice);
}
