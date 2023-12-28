package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService{
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<MemberMission> getChallengingMission(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING,PageRequest.of(page, 10));

        return memberMissionPage;
    }

    @Transactional
    @Override
    public MemberMission missionComplete(Long missionId, Long memberId) {
        Mission mission = missionRepository.findById(missionId).get();
        Member member = memberRepository.findById(memberId).get();

        MemberMission memberMission = memberMissionRepository.findByMissionAndMember(mission, member);
        memberMission.setStatus(MissionStatus.COMPLETE);

        memberMissionRepository.save(memberMission);

        return memberMission;
    }


}
