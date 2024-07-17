package com.ohgiraffers.section08.namedquery;


import jakarta.persistence.*;
import lombok.*;

@Entity(name="menu_section08")
@Table(name="tbl_menu")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@NamedQueries({
        @NamedQuery(
                name="section08.namedquery.Menu.findAll",
                query = """
select m from menu_section08 m
"""
        ),
        // findByMenuName -> 메뉴이름으로 메뉴 찾아오는 JPQL
        // 메뉴 이름을 파라미터로 받고 일치하는 메뉴 객체를 반환하는 JPQL

        @NamedQuery(
                name="section08.namedquery.menu.findByMenuName",
                query = """
select m from menu_section08 m
where m.menuName = :menuName


"""


        ),
        // 메뉴이름에 "밥"이 들어간 메뉴 이름을 전부 조회하는 JPQL (like, concat)
        @NamedQuery(
                name="section08.namedquery.menu.findBobMenu",
                query = """
                select m from menu_section08 m
where m.menuName like concat('%', :menuName, '%')
                
                """


        )
})
public class Menu {

    @Id
    @Column(name="menu_code")
    private int menuCode;

    @Column(name="menu_name")
    private String menuName;

    @Column(name="menu_price")
    private int menuPrice;

    @JoinColumn(name="category_code")
    private int categoryCode;

    @Column(name="orderable_status")
    private String orderableStatus;
}