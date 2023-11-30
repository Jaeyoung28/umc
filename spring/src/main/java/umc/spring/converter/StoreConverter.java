package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {
    public static StoreResponseDTO.createResultDTO toCreateResultDTO(Store store){
        return StoreResponseDTO.createResultDTO.builder()
                .storeId(store.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.createStoreDTO request){
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }

    public static StoreResponseDTO.createReviewDTO toCreateReviewDTO(Review review){
        return StoreResponseDTO.createReviewDTO.builder()
                .reviewId(review.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(StoreRequestDTO.ReviewDTO request){
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

    public static StoreResponseDTO.challengMissionDTO tochallengMissionDTO(MemberMission memberMission){
        return StoreResponseDTO.challengMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .status(memberMission.getStatus())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemberMission(StoreRequestDTO.MissionDTO request){
        return MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}
