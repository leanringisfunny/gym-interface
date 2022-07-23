package toy.gym.domain.repository;

import toy.gym.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member findById(Long id);
    List<Member> findAll();
    Optional<Member> findByPassword(Long password);
    Member save(Member member);
    void clear();
}
