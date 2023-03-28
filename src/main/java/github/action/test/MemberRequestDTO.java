package github.action.test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberRequestDTO {
    private String name;
    private String email;

    @Builder
    public MemberRequestDTO(String name, String email){
        this.name = name;
        this.email = email;
    }
}
