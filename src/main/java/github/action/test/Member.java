package github.action.test;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String email;

    @Column
    private Integer point;

    @Builder
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        this.point = 0;
    }

    public void addPoint(Integer point) {
        this.point += point;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
