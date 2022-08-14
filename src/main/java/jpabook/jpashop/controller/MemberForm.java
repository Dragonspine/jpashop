package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * packageName    : jpabook.jpashop.controller
 * fileName       : MemberForm
 * author         : kanghyun Kim
 * date           : 2022/08/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/14        kanghyun Kim      최초 생성
 */
@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다") // 값없으면 오류
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
