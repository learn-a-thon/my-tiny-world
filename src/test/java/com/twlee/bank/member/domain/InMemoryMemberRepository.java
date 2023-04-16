package com.twlee.bank.member.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryMemberRepository implements MemberRepository {
    private final Map<Long, Member> stores = new HashMap<>();
    private Long sequence = 1L;

    @Override
    public Member save(Member requestMember) {
        if (stores.containsKey(sequence)) {
            throw new IllegalArgumentException("중복된 id가 존재합니다.");
        }
        Member member = new Member(sequence++, requestMember.getName(), requestMember.getEmail(), requestMember.getPassword());
        stores.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = stores.getOrDefault(id, null);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.empty();
    }
}
