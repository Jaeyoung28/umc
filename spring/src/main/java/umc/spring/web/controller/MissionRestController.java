package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/stores")
    public ApiResponse<MissionResponseDTO.createMissionDTO> createMission(@RequestBody @Validated MissionRequestDTO.MissionDTO request){
        Mission mission = missionCommandService.createMission(request);
        return ApiResponse.onSuccess(MissionConverter.toCreateMissionDTO(mission));
    }

    // 미션 도전하기
    @PostMapping("/{missionId}/members/{memberId}")
    public ApiResponse<MissionResponseDTO.challengingMissionDTO> challengMission(@ExistMission @PathVariable(name = "missionId") Long missionId,
                                                                                @ExistMember @RequestParam(name = "memberId") Long memberId){
        MemberMission memberMission = missionCommandService.missionChallenging(missionId, memberId);
        return ApiResponse.onSuccess(MissionConverter.toChallengingMissionDTO(memberMission));
    }

    // week10-3 내가 진행중인 미션 목록
    @GetMapping("/{memberId}")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API",description = "내가 진행중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 없습니다.",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "page는 1부터 입력해야 합니다.",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    public ApiResponse<MissionResponseDTO.myChallengingMissionListDTO> getChallengingMissionList(@ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                            @CheckPage @RequestParam(name="page") Integer page){
        return ApiResponse.onSuccess(MissionConverter.myChallengingMissionListDTO(missionQueryService.getChallengingMission(memberId, page)));
    }

    // week10-4 진행중인 미션 미션완료로 변경하기
    @PostMapping("/{missionId}/{memberId}")
    @Operation(summary = "진행중인 미션 진행완료로 변경하기 API",description = "진행중인 미션을 진행완료로 변경하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 없습니다.",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4001", description = "미션이 없습니다.",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "missionId", description = "미션 아이디, path variable 입니다!"),
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.missionCompleteDTO> missionComplete(@ExistMission @PathVariable(name = "missionId") Long missionId,
                                                                                       @ExistMember @PathVariable(name = "memberId") Long memberId){
        return ApiResponse.onSuccess(MissionConverter.missionCompleteDTO(missionQueryService.missionComplete(missionId, memberId)));
    }
}
