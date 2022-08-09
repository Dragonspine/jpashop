package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : jpabook.jpashop.service
 * fileName       : MemberService
 * author         : kanghyun Kim
 * date           : 2022/08/09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/09        kanghyun Kim      최초 생성
 */
@Service // 컴포넌트 스캔의 대상으로 자동으로 빈에 등록됨
@Transactional(readOnly = true) // 데이터 변경시 한 트랜잭션으로 묶어 처리,
                                // 해당 public 메서드에 전부 적용
                                // 쓰기작업 있는 메서드만 @Transactional 추가로 세팅하는걸 권장
//@AllArgsConstructor // 필드를 가지고 생성자를 만들어줌
@RequiredArgsConstructor // final이 있는 필드만 가지고 생성자를 만들어줌 (가장 권장되는 방법)
public class MemberService {

    /**
     * Field Injection
     */
//    @Autowired
//    private MemberRepository memberRepository;

    /**
     * Setter Injection
     * Caution: runtime에 set 호출하면 repository가 변경되버림(잘안씀)
     */
//    private MemberRepository memberRepository;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * Constructor Injection
     * runtime 중간에 못바꿈
     */
    private final MemberRepository memberRepository; // final로 하면 컴파일시점에 디버깅가능

//    @Autowired // 생성자 한개인 경우 Autowired 없어도 스프링에서 자동으로 Injection 됨
//    public MemberService(MemberRepository memberRepository) { //AllArgsConstructor 있을경우 생략 가능
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional // 쓰기 작업에 readOnly = true 넣으면 데이터 변경이 안됨
    public Long join(Member member){
        validateDuplicateMember(member); //중복회원 검증,
                                         //멀티쓰레드에서 동시에 이 검증을 통과할 시
                                         //같은이름으로 2데이터 생성 될 수 있어 DB에 Name을 유니크 제약조건 걸기
        memberRepository.save(member);
        return member.getId(); // em.persist시 Id 값이 항상 있는게 보장이 됨
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
//    @Transactional(readOnly = true) // jpa가 조회하는곳에선 성능최적화해줌
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

//    @Transactional(readOnly = true) // jpa가 조회하는곳에선 성능최적화해줌
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

}
