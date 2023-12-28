package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    public Mission createMission(MissionRequestDTO.MissionDTO request);
    public MemberMission missionChallenging(Long missionId, Long memberId);
}
