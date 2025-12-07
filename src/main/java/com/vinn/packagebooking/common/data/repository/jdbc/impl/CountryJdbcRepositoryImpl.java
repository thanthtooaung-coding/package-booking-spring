package com.vinn.packagebooking.common.data.repository.jdbc.impl;

import com.vinn.packagebooking.common.data.model.Country;
import com.vinn.packagebooking.common.data.repository.jdbc.CountryJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CountryJdbcRepositoryImpl implements CountryJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public CountryJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Country> COUNTRY_ROW_MAPPER = new RowMapper<Country>() {
        @Override
        public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
            Country country = new Country();
            country.setId(rs.getLong("id"));
            country.setName(rs.getString("name"));
            country.setShortName(rs.getString("short_name"));
            country.setCode(rs.getString("code"));
            return country;
        }
    };

    @Override
    public List<Country> findAll() {
        String sql = "SELECT id, name, short_name, code FROM countries";
        return jdbcTemplate.query(sql, COUNTRY_ROW_MAPPER);
    }

    @Override
    public Optional<Country> findById(Long id) {
        String sql = "SELECT id, name, short_name, code FROM countries WHERE id = ?";
        List<Country> countries = jdbcTemplate.query(sql, COUNTRY_ROW_MAPPER, id);
        return countries.isEmpty() ? Optional.empty() : Optional.of(countries.get(0));
    }
}
