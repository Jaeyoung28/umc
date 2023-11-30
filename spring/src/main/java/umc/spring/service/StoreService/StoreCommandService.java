package umc.spring.service.StoreService;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store createStore(Long regionId, StoreRequestDTO.createStoreDTO request);

    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request);
    public MemberMission challengMission(Long missionId, Long memberId, StoreRequestDTO.MissionDTO request);
}
