package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    /*
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
    */

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName()) // review에 ManyToOne으로 지정해뒀기 때문
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponseDTO.MyReviewPreViewDTO MyReviewPreViewDTO(Review myReview){
        return StoreResponseDTO.MyReviewPreViewDTO.builder()
                .nickname(myReview.getMember().getName())
                .score(myReview.getScore())
                .createdAt(myReview.getCreatedAt().toLocalDate())
                .body(myReview.getBody())
                .build();
    }
    public static StoreResponseDTO.MyReviewPreViewListDTO MyReviewPreViewListDTO(Page<Review> myReviewList){
        List<StoreResponseDTO.MyReviewPreViewDTO> myReviewPreViewDTOList = myReviewList.stream()
                .map(StoreConverter::MyReviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.MyReviewPreViewListDTO.builder()
                .storeName(myReviewList.toList().get(0).getStore().getName())
                .isLast(myReviewList.isLast())
                .isFirst(myReviewList.isFirst())
                .totalPage(myReviewList.getTotalPages())
                .totalElements(myReviewList.getTotalElements())
                .listSize(myReviewPreViewDTOList.size())
                .myReviewList(myReviewPreViewDTOList)
                .build();
    }
}
