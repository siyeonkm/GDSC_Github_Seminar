package github.action.test;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member AddMember(MemberRequestDTO memberInput) {
        Member newMember = Member.builder()
                                    .name(memberInput.getName())
                                    .email(memberInput.getEmail())
                                    .build();
        return memberRepository.save(newMember);
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long memberId) {
        return memberRepository.findMemberByMemberId(memberId)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Member updateName(Long id, String name) {
        Member member = findMemberById(id);
        member.updateName(name);
        return memberRepository.save(member);
    }
}
