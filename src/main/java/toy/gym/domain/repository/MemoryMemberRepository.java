package toy.gym.domain.repository;

import org.springframework.stereotype.Repository;
import toy.gym.domain.member.Member;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member>store = new HashMap<>();
    private static Long sequence =0L;
    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByPassword(Long password) {
        return findAll().stream().filter(m->m.getPassword().equals(password)).findFirst();
    }

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(sequence, member);
        return store.get(member.getId());
    }

    @Override
    public void clear() {
        store.clear();
    }
}
