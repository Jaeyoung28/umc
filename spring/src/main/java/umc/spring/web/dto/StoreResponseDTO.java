package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

public class StoreResponseDTO {
    // 가게 추가
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createResultDTO{
        Long storeId;
        LocalDateTime createAt;
    }

    // 리뷰 추가
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createReviewDTO{
        Long reviewId;
        LocalDateTime createAt;
    }

    // 미션 도전
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class challengMissionDTO{
        Long memberMissionId;
        MissionStatus status;
        LocalDateTime createAt;
    }
}
