package com.vinn.packagebooking.common.data.repository.jdbc.impl;

import com.vinn.packagebooking.common.data.model.Enrollment;
import com.vinn.packagebooking.common.data.model.User;
import com.vinn.packagebooking.common.data.repository.jdbc.EnrollmentJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EnrollmentJdbcRepositoryImpl implements EnrollmentJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public EnrollmentJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Enrollment> ENROLLMENT_ROW_MAPPER = new RowMapper<Enrollment>() {
        @Override
        public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Enrollment enrollment = new Enrollment();
            enrollment.setId(rs.getLong("e_id"));
            enrollment.setEntityId(rs.getLong("e_entity_id"));
            if (rs.wasNull()) {
                enrollment.setEntityId(null);
            }

            Timestamp enrolledDate = rs.getTimestamp("e_enrolled_date");
            if (enrolledDate != null) {
                enrollment.setEnrolledDate(enrolledDate.toLocalDateTime());
            }

            enrollment.setEntityType(rs.getInt("e_entity_type"));
            if (rs.wasNull()) {
                enrollment.setEntityType(null);
            }

            // Map User
            User user = new User();
            user.setId(rs.getLong("u_id"));
            user.setName(rs.getString("u_name"));
            user.setUsername(rs.getString("u_username"));
            user.setPassword(rs.getString("u_password"));
            enrollment.setUser(user);

            return enrollment;
        }
    };

    @Override
    public List<Enrollment> findAll() {
        String sql = "SELECT " +
                "e.id AS e_id, e.entity_id AS e_entity_id, e.enrolled_date AS e_enrolled_date, " +
                "e.entity_type AS e_entity_type, " +
                "u.id AS u_id, u.name AS u_name, u.username AS u_username, u.password AS u_password " +
                "FROM enrollments e " +
                "INNER JOIN users u ON e.user_id = u.id";
        return jdbcTemplate.query(sql, ENROLLMENT_ROW_MAPPER);
    }
}
