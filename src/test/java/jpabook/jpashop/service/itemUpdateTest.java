package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

/**
 * packageName    : jpabook.jpashop.service
 * fileName       : itemUpdateTest
 * author         : kanghyun Kim
 * date           : 2022/08/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/15        kanghyun Kim      최초 생성
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class itemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
        Book book = em.find(Book.class, 1L);

        //TX
        book.setName("asdas"); // set해서 기존 데이터가 변경되면

        //변경감지(flush 할떄 dirty checking)를 해서 변경된 부분을 update
        //TX commit
    }
}

