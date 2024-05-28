package com.devteria.profile.controller;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import com.devteria.profile.service.UserProfileService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {

    UserProfileService userProfileService;

    @PostMapping("/users/")
    UserProfileResponse createUserProfile(@RequestBody ProfileCreationRequest request){
        return userProfileService.createProfile(request);
    }
    @GetMapping("/users/{profileId}")
    UserProfileResponse getUserProfile(@PathVariable String profileId){
        return userProfileService.getProfile(profileId);
    }
}
