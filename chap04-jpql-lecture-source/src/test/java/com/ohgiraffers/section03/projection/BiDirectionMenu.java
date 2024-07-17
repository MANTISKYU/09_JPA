package com.ohgiraffers.section03.projection;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="bidirection_menu")
@Table(name="tbl_menu")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BiDirectionMenu {

    @Id
    @Column(name="menu_code")
    private int menuCode;

    @Column(name="menu_name")
    private String menuName;

    @Column(name="menu_price")
    private int menuPrice;

    @JoinColumn(name="category_code")
    @ManyToOne
    private BiDirectionCategory category;

    @Column(name="orderable_status")
    private String orderableStatus;



}
