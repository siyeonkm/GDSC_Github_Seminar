package github.action.test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointRequestDTO {
    private Integer point;

    @Builder
    public PointRequestDTO(Integer point) {
        this.point = point;
    }
}
