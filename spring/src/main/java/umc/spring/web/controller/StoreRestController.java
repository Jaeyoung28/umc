package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistRegions;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/regions/{regionId}")
    public ApiResponse<StoreResponseDTO.createResultDTO> create(@RequestBody @Valid StoreRequestDTO.createStoreDTO request,
                                                                           @ExistRegions @PathVariable(name = "regionId") Long regionId ){
        Store store = storeCommandService.createStore(regionId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateResultDTO(store));
    }

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.createReviewDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReviewDTO request,
                                                                            @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewDTO(review));
    }

    @PostMapping("/missions/{missionId}/members/{memberId}")
    public ApiResponse<StoreResponseDTO.challengMissionDTO> challengMission(@RequestBody @Valid StoreRequestDTO.MissionDTO request,
                                                                   @ExistMission @PathVariable(name = "missionId") Long missionId,
                                                                   @ExistMember @RequestParam(name = "memberId") Long memberId){
        MemberMission memberMission = storeCommandService.challengMission(missionId, memberId, request);
        return ApiResponse.onSuccess(StoreConverter.tochallengMissionDTO(memberMission));
    }
}
