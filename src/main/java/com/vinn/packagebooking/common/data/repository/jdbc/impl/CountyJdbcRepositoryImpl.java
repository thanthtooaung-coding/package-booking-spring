package com.vinn.packagebooking.common.data.repository.jdbc.impl;

import com.vinn.packagebooking.common.data.model.County;
import com.vinn.packagebooking.common.data.repository.jdbc.CountyJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CountyJdbcRepositoryImpl implements CountyJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public CountyJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<County> COUNTY_ROW_MAPPER = new RowMapper<County>() {
        @Override
        public County mapRow(ResultSet rs, int rowNum) throws SQLException {
            County county = new County();
            county.setId(rs.getLong("id"));
            county.setName(rs.getString("name"));
            county.setShortName(rs.getString("short_name"));
            county.setCode(rs.getString("code"));
            return county;
        }
    };

    @Override
    public List<County> findAll() {
        String sql = "SELECT id, name, short_name, code FROM counties";
        return jdbcTemplate.query(sql, COUNTY_ROW_MAPPER);
    }
}
