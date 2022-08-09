package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * packageName    : jpabook.jpashop.service
 * fileName       : MemberServiceTest
 * author         : kanghyun Kim
 * date           : 2022/08/09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/09        kanghyun Kim      최초 생성
 */

@RunWith(SpringRunner.class) // junit실행시 스프링이랑 같이 실행하려면 필요
@SpringBootTest // 순수단위테스트X > 스프링 + JPA 테스트라 spring integration이 필요함
                // spring boot를 띄운상태의 테스트시
@Transactional //Test의 Transactional은 기본적으로 Rollback을 해버림
public class MemberServiceTest {

    /**
     * live template에 testcase 예약어 tdd로 등록
     * @Test
     * public void $NAME$() throws Exception {
     *     //given
     *     $END$
     *     //when
     *
     *     //then
     * }
     */

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
//    @Rollback(false) // rollback 안하게
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kimasdasd");

        //when
        // insert문이 안나오는 이유? > persist는 해도 커밋된게 아니라 안나옴(@GeneratedValue 전략에서는)
        Long savedId = memberService.join(member);

        //then
        // EntityManager 주입하고 flush 하면 영속성 컨택스트 내용을 DB에 반영하고,
        // Transactional이 rollback 하도록 가능함
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }
    
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예제() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        
        //when
        memberService.join(member1);
//        try {
//            memberService.join(member2); // 예외가 발생해야 한다
//        } catch (IllegalStateException e){
//            return;
//        }
        // @Test(expected = IllegalStateException.class) 를 쓰면 try-catch 없어도 됨
        memberService.join(member2); // 예외가 발생해야 한다

        //then
        fail("예외가 발생해야 한다");
    }
}