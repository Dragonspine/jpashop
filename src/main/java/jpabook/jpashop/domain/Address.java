package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * packageName    : jpabook.jpashop.domain
 * fileName       : Address
 * author         : kanghyun Kim
 * date           : 2022/08/07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/07        kanghyun Kim      최초 생성
 */

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // JPA 구현 라이브러리가 객체를 생성할 때 리플랙션, 프록시 같은 기술을
    // 사용할 수 있도록 지원해야 하므로
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
