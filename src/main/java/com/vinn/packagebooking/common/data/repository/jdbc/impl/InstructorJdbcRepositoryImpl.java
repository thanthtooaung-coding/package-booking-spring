package com.vinn.packagebooking.common.data.repository.jdbc.impl;

import com.vinn.packagebooking.common.data.model.Instructor;
import com.vinn.packagebooking.common.data.repository.jdbc.InstructorJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InstructorJdbcRepositoryImpl implements InstructorJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public InstructorJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Instructor> INSTRUCTOR_ROW_MAPPER = new RowMapper<Instructor>() {
        @Override
        public Instructor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Instructor instructor = new Instructor();
            instructor.setId(rs.getLong("id"));
            instructor.setName(rs.getString("name"));
            instructor.setUsername(rs.getString("username"));
            return instructor;
        }
    };

    @Override
    public List<Instructor> findAll() {
        String sql = "SELECT id, name, username FROM instructors";
        return jdbcTemplate.query(sql, INSTRUCTOR_ROW_MAPPER);
    }
}
