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

    @PatchMapping("/{memberId}/name")
    public ResponseEntity<MemberResponseDTO> memberNameModify(@PathVariable Long memberId, @RequestBody MemberRequestDTO memberInput) {
        Member memberPatched = memberService.updateName(memberId, memberInput.getName());
        MemberResponseDTO memberOutput = MemberResponseDTO.builder()
                .m(memberPatched)
                .build();
        return ResponseEntity.ok(memberOutput);
    }

    @PatchMapping("/{memberId}/point")
    public ResponseEntity<MemberResponseDTO> memberPointModify(@PathVariable Long memberId, @RequestBody PointRequestDTO pointInput) {
        Member memberPatched = memberService.updatePoint(memberId, pointInput.getPoint());
        MemberResponseDTO memberOutput = MemberResponseDTO.builder()
                .m(memberPatched)
                .build();
        return ResponseEntity.ok(memberOutput);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> memberDelete(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok("member: " + memberId + "deleted");
    }
}
