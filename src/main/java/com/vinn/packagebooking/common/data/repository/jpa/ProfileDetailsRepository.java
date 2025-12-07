package com.vinn.packagebooking.common.data.repository.jpa;

import com.vinn.packagebooking.common.data.model.ProfileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileDetailsRepository extends JpaRepository<ProfileDetails, Long> {
    Optional<ProfileDetails> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
