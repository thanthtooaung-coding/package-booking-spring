package com.vinn.packagebooking.common.data.repository.jdbc;

import com.vinn.packagebooking.common.data.model.ProfileDetails;

import java.util.List;

public interface ProfileDetailsJdbcRepository {
    List<ProfileDetails> findAll();
}
