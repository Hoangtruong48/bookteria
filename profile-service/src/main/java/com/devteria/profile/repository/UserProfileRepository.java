package com.devteria.profile.repository;

import com.devteria.profile.dto.response.UserProfileResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.devteria.profile.entity.UserProfile;



@Repository
public interface UserProfileRepository extends Neo4jRepository<UserProfile, String> {
    Page<UserProfile> findAll(Pageable pageable);

    Page<UserProfile> findAllByCity(String city, Pageable pageable);
}
