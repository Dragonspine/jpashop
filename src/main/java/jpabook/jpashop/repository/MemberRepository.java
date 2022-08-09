package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * packageName    : jpabook.jpashop.repository
 * fileName       : MemberRepository
 * author         : kanghyun Kim
 * date           : 2022/08/09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/09        kanghyun Kim      최초 생성
 */
@Repository //컴포넌트 스캔에 의해 자동으로 빈 관리가 됨(안에 @Component있음)
public class MemberRepository {

    @PersistenceContext // Jpa 표준 어노테이션으로 스프링이 EntityManager를  만들어 Injection 해줌
    private EntityManager em;

//    @PersistenceUnit // EntityManagerFactory를 주입받고 싶을 때 씀(거의안씀)
//    private EntityManagerFactory emf;

    public void save(Member member){
        em.persist(member); // 영속성컨택스트에 member 객체 넣음(트랜잭션 커밋시 저장)
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); // 단건조회 타입, PK 파라미터로 느
    }

    public List<Member> findAll() {
        //sql은 테이블을 대상(from절)으로 퀴리를 하는 반면
        //jpql은 엔티티(Member)를 대상(from절)으로 쿼리한다
        return em.createQuery("select m from Member m",Member.class)// jpql 작성, 반환타입 지정
                .getResultList(); //리스트 형식으로 받음
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",Member.class)// jpql 작성, 반환타입 지정
                .setParameter("name", name) // jpql의 파라미터 바인딩
                .getResultList(); //리스트 형식으로 받음
    }
}
