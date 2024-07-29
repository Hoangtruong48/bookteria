package com.devteria.profile.controller;

import com.devteria.profile.dto.ApiResponse;
import com.devteria.profile.dto.request.PageRequestDTO;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.service.UserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping("/users/{profileId}")
    ApiResponse<UserProfileResponse> getProfile(@PathVariable String profileId) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.getProfile(profileId))
                .build();
    }

    @GetMapping("/users")
    ApiResponse<List<UserProfileResponse>> getAllProfiles() {
        return ApiResponse.<List<UserProfileResponse>>builder()
                .result(userProfileService.getAllProfiles())
                .build();
    }

    @GetMapping("/users/pageable")
    ApiResponse<Page<UserProfileResponse>> getUserProfiles(@RequestBody PageRequestDTO pageRequestDTO) {
        return ApiResponse.<Page<UserProfileResponse>>builder()
                .result(userProfileService.getAllProfiles(pageRequestDTO))
                .build();
    }

    @GetMapping("/users/pageable/page-impl")
    ApiResponse<Page<UserProfileResponse>> getUserProfilesPageable(@RequestBody PageRequestDTO pageRequestDTO) {
        return ApiResponse.<Page<UserProfileResponse>>builder()
                .result(userProfileService.getAllProfilesUsingPaginationList(pageRequestDTO))
                .build();
    }

    @GetMapping("/users/pageable/query")
    ApiResponse<Page<UserProfileResponse>> getUserProfilesQuery(@RequestBody PageRequestDTO pageRequestDTO,
                                                                @RequestParam String city) {
        return ApiResponse.<Page<UserProfileResponse>>builder()
                .result(userProfileService.getAllProfilesUsingQuery(pageRequestDTO, city))
                .build();
    }
}
