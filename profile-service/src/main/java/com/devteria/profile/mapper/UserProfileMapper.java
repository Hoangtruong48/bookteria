package com.devteria.profile.mapper;


import com.devteria.profile.entity.UserProfile;
import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest profileCreationRequest);
    UserProfileResponse toUserProfileResponse(UserProfile entity);
}
