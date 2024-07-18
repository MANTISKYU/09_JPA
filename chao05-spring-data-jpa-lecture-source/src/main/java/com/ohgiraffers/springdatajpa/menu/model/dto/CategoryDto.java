package com.ohgiraffers.springdatajpa.menu.model.dto;

import jakarta.persistence.Column;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDto {


    private int categoryCode;


    private String categoryName;


    private int refCategoryCode;
}
