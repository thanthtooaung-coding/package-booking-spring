package com.vinn.packagebooking.common.data.repository.jdbc.impl;

import com.vinn.packagebooking.common.data.model.ProfileDetails;
import com.vinn.packagebooking.common.data.model.User;
import com.vinn.packagebooking.common.data.repository.jdbc.ProfileDetailsJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProfileDetailsJdbcRepositoryImpl implements ProfileDetailsJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProfileDetailsJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<ProfileDetails> PROFILE_DETAILS_ROW_MAPPER = new RowMapper<ProfileDetails>() {
        @Override
        public ProfileDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProfileDetails profileDetails = new ProfileDetails();
            profileDetails.setId(rs.getLong("pd_id"));
            profileDetails.setPhoneNumber(rs.getString("pd_phone_number"));
            profileDetails.setEmail(rs.getString("pd_email"));
            profileDetails.setProfileUrl(rs.getString("pd_profile_url"));
            profileDetails.setAddress(rs.getString("pd_address"));
            profileDetails.setGender(rs.getString("pd_gender"));
            profileDetails.setIsFirstTimeLogin(rs.getBoolean("pd_is_first_time_login"));
            if (rs.wasNull()) {
                profileDetails.setIsFirstTimeLogin(null);
            }
            profileDetails.setEntityType(rs.getInt("pd_entity_type"));
            if (rs.wasNull()) {
                profileDetails.setEntityType(null);
            }

            // Map User
            User user = new User();
            user.setId(rs.getLong("u_id"));
            user.setName(rs.getString("u_name"));
            user.setUsername(rs.getString("u_username"));
            user.setPassword(rs.getString("u_password"));
            profileDetails.setUser(user);

            return profileDetails;
        }
    };

    @Override
    public List<ProfileDetails> findAll() {
        String sql = "SELECT " +
                "pd.id AS pd_id, pd.phone_number AS pd_phone_number, pd.email AS pd_email, " +
                "pd.profile_url AS pd_profile_url, pd.address AS pd_address, " +
                "pd.gender AS pd_gender, pd.is_first_time_login AS pd_is_first_time_login, " +
                "pd.entity_type AS pd_entity_type, " +
                "u.id AS u_id, u.name AS u_name, u.username AS u_username, u.password AS u_password " +
                "FROM profile_details pd " +
                "INNER JOIN users u ON pd.user_id = u.id";
        return jdbcTemplate.query(sql, PROFILE_DETAILS_ROW_MAPPER);
    }
}
