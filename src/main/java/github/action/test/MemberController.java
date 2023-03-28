package github.action.test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<MemberResponseDTO> memberAdd(@RequestBody MemberRequestDTO memberInput) {
        Member member = memberService.AddMember(memberInput);
        MemberResponseDTO memberOutput = MemberResponseDTO.builder()
                .m(member)
                .build();
        return ResponseEntity.created(URI.create("/members/" + memberOutput.getId())).body(memberOutput);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDTO> memberDetailById(@PathVariable Long memberId) {
        Member member = memberService.findMemberById(memberId);
        MemberResponseDTO memberOutput = MemberResponseDTO.builder()
                .m(member)
                .build();
        return ResponseEntity.ok(memberOutput);
    }
}
