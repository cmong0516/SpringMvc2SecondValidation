package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodeResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");

        Assertions.assertThat(messageCodes).containsExactly("required.item", "required");
        //MessageCodesResolver 의 메서드 resolveMessageCodes 로 값을 찾으면 required 를 포함한 모든 값이 나온다,
    }

    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required.item", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
        Assertions.assertThat(messageCodes).containsExactly("required.item.item.itemName", "required.item.itemName", "required.item.java.lang.String","required.item");
    }
}

//        required.item.itemName=상품 이름은 필수입니다.
//        range.item.price=가격은 {0} ~ {1} 까지 허용합니다.
//        max.item.quantity=수량은 최대 {0} 까지 허용합니다.
//        totalPriceMin=가격 * 수량의 합은 {0}원 이상이어야 합니다. 현재 값 = {1}
//        required.itemName: 상품 이름은 필수 입니다.
//        required: 필수 값 입니다.