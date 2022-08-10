package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : jpabook.jpashop.repository
 * fileName       : OrderSearch
 * author         : kanghyun Kim
 * date           : 2022/08/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/10        kanghyun Kim      최초 생성
 */
@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
