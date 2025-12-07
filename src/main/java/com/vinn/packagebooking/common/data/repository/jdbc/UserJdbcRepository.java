package com.vinn.packagebooking.common.data.repository.jdbc;

import com.vinn.packagebooking.common.data.model.User;

import java.util.List;

public interface UserJdbcRepository {
    List<User> findAll();
}
