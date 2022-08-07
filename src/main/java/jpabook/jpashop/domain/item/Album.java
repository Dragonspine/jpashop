package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * packageName    : jpabook.jpashop.domain
 * fileName       : Album
 * author         : kanghyun Kim
 * date           : 2022/08/07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/07        kanghyun Kim      최초 생성
 */
@Entity
@DiscriminatorValue("A")
@Getter @Setter
public class Album  extends Item{

    private String artist;
    private String etc;
}
