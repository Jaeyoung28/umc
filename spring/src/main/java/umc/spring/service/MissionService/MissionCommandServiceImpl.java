package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Mission createMission(MissionRequestDTO.MissionDTO request){
        Mission mission = MissionConverter.toMission(request);

        mission.setStore(storeRepository.findById(request.getStordId()).get());

        return missionRepository.save(mission);
    }


    @Override
    public MemberMission missionChallenging(Long missionId, Long memberId){
        Mission mission = missionRepository.findById(missionId).get();
        Member member = memberRepository.findById(memberId).get();

        MemberMission memberMission = MissionConverter.toMemberMission();
        memberMission.setMission(mission);
        memberMission.setMember(member);
        memberMission.setStatus(MissionStatus.CHALLENGING);

        return memberMissionRepository.save(memberMission);
    }

}
