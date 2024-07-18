package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.menu.model.dto.CategoryDto;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDto;
import com.ohgiraffers.springdatajpa.menu.model.entity.Category;
import com.ohgiraffers.springdatajpa.menu.model.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.model.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    public MenuService(MenuRepository menuRepository, ModelMapper modelMapper) {
        this.menuRepository = menuRepository;
//        this.modelMapper = new ModelMapper();
        this.modelMapper = modelMapper;
    }

    public MenuDto findMenuByCode(int menuCode) {

        //

        Menu menu = menuRepository.findById(menuCode)
             .orElseThrow(IllegalArgumentException::new);

        log.info("menu ======= {}", menu);



        return modelMapper.map(menu, MenuDto.class);

    }

    public List<MenuDto> findMenuList() {
        List<Menu> menuList =
                menuRepository.findAll();
        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDto.class))
                .collect(Collectors.toList());
    }

    public Page<MenuDto> findAllMenus(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(), Sort.by("menuCode").descending());

        Page<Menu> menuList = menuRepository.findAll(pageable);

        return  menuList.map(menu -> modelMapper.map(menu, MenuDto.class));

    }

    public List<MenuDto> findByMenuPrice(Integer menuPrice) {

        List<Menu> menuList = null;

        if(menuPrice ==0) {

            menuList = menuRepository.findAll();
        } else if (menuPrice > 0) {
            menuList = menuRepository.findByMenuPriceLessThanOrderByMenuCode(menuPrice);
//                    Sort.by("menuPrice").descending());
        }

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDto.class))
                .collect(Collectors.toList());

    }

//    public List<CategoryDto> findAllCategory() {
//
//        List<Category> categoryList = categoryRepository.findAll
//
//    }
}
