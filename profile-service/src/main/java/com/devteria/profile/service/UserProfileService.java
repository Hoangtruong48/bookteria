package com.devteria.profile.service;

import com.devteria.profile.dto.request.PageRequestDTO;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.entity.UserProfile;
import com.devteria.profile.mapper.UserProfileMapper;
import com.devteria.profile.repository.UserProfileRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    public UserProfileResponse createProfile(ProfileCreationRequest request) {
        UserProfile userProfile = userProfileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);

        return userProfileMapper.toUserProfileReponse(userProfile);
    }

    public UserProfileResponse getProfile(String id) {
        UserProfile userProfile =
                userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));

        return userProfileMapper.toUserProfileReponse(userProfile);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfileResponse> getAllProfiles() {
        var profiles = userProfileRepository.findAll();

        return profiles.stream().map(userProfileMapper::toUserProfileReponse).toList();
    }

    public Page<UserProfileResponse> getAllProfiles(PageRequestDTO pageRequestDTO) {
        Pageable pageable = new PageRequestDTO().getPageable(pageRequestDTO);
        Page<UserProfile> userProfilePage = userProfileRepository.findAll(pageable);
        List<UserProfileResponse> userProfileResponses = userProfilePage.stream()
                .map(userProfileMapper::toUserProfileReponse).toList();
        return new PageImpl<>(userProfileResponses, userProfilePage.getPageable(), userProfilePage.getTotalElements());
    }

    public Page<UserProfileResponse> getAllProfilesUsingPaginationList(PageRequestDTO pageRequestDTO) {
        List<UserProfile> userProfileList = userProfileRepository.findAll();

        // Chuyển đổi danh sách UserProfile thành UserProfileResponse
        List<UserProfileResponse> userProfileResponseList = userProfileList.stream()
                .map(userProfileMapper::toUserProfileReponse)
                .collect(Collectors.toList());

        // Sắp xếp danh sách trước
        boolean ascending = pageRequestDTO.getSort().isAscending();
        PropertyComparator.sort(userProfileResponseList, new MutableSortDefinition(pageRequestDTO.getSortByColumn(), true, ascending));

        // Tạo PagedListHolder và cấu hình phân trang
        PagedListHolder<UserProfileResponse> pagedListHolder = new PagedListHolder<>(userProfileResponseList);
        pagedListHolder.setPage(pageRequestDTO.getPageNo());
        pagedListHolder.setPageSize(pageRequestDTO.getPageSize());

        // Lấy phần tử của trang
        List<UserProfileResponse> pageSlice = pagedListHolder.getPageList();

        // Tạo đối tượng PageImpl
        Pageable pageable = PageRequest.of(pageRequestDTO.getPageNo(), pageRequestDTO.getPageSize(), pageRequestDTO.getSort(), pageRequestDTO.getSortByColumn());
        Page<UserProfileResponse> result = new PageImpl<>(pageSlice, pageable, userProfileResponseList.size());

        return result;
    }

    public Page<UserProfileResponse> getAllProfilesUsingQuery(PageRequestDTO pageRequestDTO, String city) {
        Pageable pageable = new PageRequestDTO().getPageable(pageRequestDTO);
        Page<UserProfile> userProfileList = userProfileRepository.findAllByCity(city, pageable);
        List<UserProfileResponse> userProfileResponses = userProfileList.stream().map(userProfileMapper::toUserProfileReponse).toList();
        return new PageImpl<>(userProfileResponses, pageable, userProfileResponses.size());
    }
}
