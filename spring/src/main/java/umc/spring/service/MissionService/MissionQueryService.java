package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;

public interface MissionQueryService {
    public Page<MemberMission> getChallengingMission(Long memberId, Integer page);
    public MemberMission missionComplete(Long missionId, Long memberId);
}
