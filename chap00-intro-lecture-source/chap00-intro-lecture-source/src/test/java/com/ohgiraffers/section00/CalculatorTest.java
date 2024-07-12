package com.ohgiraffers.section00;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach // 각 테스트 메소드가 실행되기 전에 실행되어야 하는 코드가 초기화 작업을 할 때 사용
    void setUp() {
        calculator = new Calculator();
        System.out.println("BeforeEach : 테스트를 시작");


    }

    @AfterEach // 각 테스트 메소드가 실행된 후에 실행되어야 하는 후속 작업 및 정리 작업
    void tearDown() {
        System.out.println("AfterEach 테스트 끝");
    }

    @DisplayName("덧셈 테스트") // 테스트 결과에 대한 이름 지정
    @Test // 테스트 메소드를 정의할 때 사용하는 어노테이션, 테스트 메소드로 등록되고,
          // 코드 검증 및 테스트 결과확인을 하게 해준다.
    void testAddition() {
        int a = 5;
        int b = 3;

        int result = calculator.add(a, b);

        assertEquals(8, result);
    }

    @DisplayName("뺄셈 테스트")
    @Test
    void testAddition2() {
        // given
        int a = 10;
        int b = 5;

        // when
        int result = calculator.subtract(a, b);

        // then
        assertEquals(5, result);
    }


}