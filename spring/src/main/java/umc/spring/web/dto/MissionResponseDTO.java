package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createMissionDTO{
        Long missionId;
        LocalDateTime createAt;
    }

    // 미션 도전
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class challengingMissionDTO{
        Long memberMissionId;
        MissionStatus status;
        LocalDateTime createAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class myChallengingMissionListDTO{
        List<MissionResponseDTO.myMissionDTO> challengMissionDTOList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class myMissionDTO{
        String storeName;
        Integer reward;
        LocalDate deadline;
        String missionSpec;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class missionCompleteDTO{
        Long memberMissionId;
        MissionStatus status;
        LocalDateTime createAt;
    }
}
