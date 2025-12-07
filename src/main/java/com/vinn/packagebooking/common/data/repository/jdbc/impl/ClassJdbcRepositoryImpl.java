package com.vinn.packagebooking.common.data.repository.jdbc.impl;

import com.vinn.packagebooking.common.data.model.Class;
import com.vinn.packagebooking.common.data.repository.jdbc.ClassJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ClassJdbcRepositoryImpl implements ClassJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClassJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Class> CLASS_ROW_MAPPER = new RowMapper<Class>() {
        @Override
        public Class mapRow(ResultSet rs, int rowNum) throws SQLException {
            Class classEntity = new Class();
            classEntity.setId(rs.getLong("id"));
            classEntity.setName(rs.getString("name"));
            classEntity.setRequiredCredit(rs.getInt("required_credit"));
            if (rs.wasNull()) {
                classEntity.setRequiredCredit(null);
            }
            classEntity.setDescription(rs.getString("description"));

            Timestamp classStartTime = rs.getTimestamp("class_start_time");
            if (classStartTime != null) {
                classEntity.setClassStartTime(classStartTime.toLocalDateTime());
            }

            Timestamp classEndTime = rs.getTimestamp("class_end_time");
            if (classEndTime != null) {
                classEntity.setClassEndTime(classEndTime.toLocalDateTime());
            }

            classEntity.setSlot(rs.getInt("slot"));
            if (rs.wasNull()) {
                classEntity.setSlot(null);
            }
            classEntity.setLocation(rs.getString("location"));
            return classEntity;
        }
    };

    @Override
    public List<Class> findAll() {
        String sql = "SELECT id, name, required_credit, description, " +
                "class_start_time, class_end_time, slot, location " +
                "FROM classes";
        return jdbcTemplate.query(sql, CLASS_ROW_MAPPER);
    }
}
