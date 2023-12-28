package umc.spring.converter;

import lombok.Getter;
import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MissionResponseDTO.createMissionDTO toCreateMissionDTO(Mission mission){
        return MissionResponseDTO.createMissionDTO.builder()
                .missionId(mission.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.MissionDTO request){
        return Mission.builder()
                .reward(request.getReward())
                .missionSpec(request.getMissionSpec())
                .deadline(request.getDeadline())
                .build();
    }

    public static MissionResponseDTO.challengingMissionDTO toChallengingMissionDTO(MemberMission memberMission){
        return MissionResponseDTO.challengingMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .status(memberMission.getStatus())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemberMission(){
        return MemberMission.builder()
                .build();
    }


    public static MissionResponseDTO.myMissionDTO myMissionDTO(MemberMission memberMission){
        return MissionResponseDTO.myMissionDTO.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .build();
    }

    public static MissionResponseDTO.myChallengingMissionListDTO myChallengingMissionListDTO(Page<MemberMission> missionList){
        List<MissionResponseDTO.myMissionDTO> myChallengingMissionList = missionList.stream()
                .map(MissionConverter::myMissionDTO).collect(Collectors.toList());

        return MissionResponseDTO.myChallengingMissionListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(myChallengingMissionList.size())
                .challengMissionDTOList(myChallengingMissionList)
                .build();
    }

    public static MissionResponseDTO.missionCompleteDTO missionCompleteDTO(MemberMission memberMission){
        return MissionResponseDTO.missionCompleteDTO.builder()
                .memberMissionId(memberMission.getId())
                .status(memberMission.getStatus())
                .createAt(memberMission.getCreatedAt())
                .build();
    }


}
