package github.action.test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Integer point;

    @Builder
    public MemberResponseDTO(Member m) {
        this.id = m.getMemberId();
        this.name = m.getName();
        this.email = m.getEmail();
        this.point = m.getPoint();
    }
}
