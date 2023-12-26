package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class MissionRequestDTO {
    @Getter
    public static class MissionDTO{
        @NotNull
        Integer reward;
        @NotBlank
        String missionSpec;
        @NotNull
        LocalDate deadline;
        @ExistStore
        Long stordId;
    }
}
