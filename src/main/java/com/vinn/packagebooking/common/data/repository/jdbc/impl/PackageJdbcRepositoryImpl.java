package com.vinn.packagebooking.common.data.repository.jdbc.impl;

import com.vinn.packagebooking.common.data.model.County;
import com.vinn.packagebooking.common.data.model.Package;
import com.vinn.packagebooking.common.data.repository.jdbc.PackageJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PackageJdbcRepositoryImpl implements PackageJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public PackageJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Package> PACKAGE_ROW_MAPPER = new RowMapper<Package>() {
        @Override
        public Package mapRow(ResultSet rs, int rowNum) throws SQLException {
            Package packageEntity = new Package();
            packageEntity.setId(rs.getLong("p_id"));
            packageEntity.setName(rs.getString("p_name"));
            packageEntity.setCredit(rs.getInt("p_credit"));
            if (rs.wasNull()) {
                packageEntity.setCredit(null);
            }

            java.sql.Date expiredDate = rs.getDate("p_expired_date");
            if (expiredDate != null) {
                packageEntity.setExpiredDate(expiredDate.toLocalDate());
            }

            packageEntity.setDescription(rs.getString("p_description"));
            
            BigDecimal price = rs.getBigDecimal("p_price");
            packageEntity.setPrice(price);

            // Map County
            Long countyId = rs.getLong("c_id");
            if (!rs.wasNull() && countyId != null) {
                County county = new County();
                county.setId(countyId);
                county.setName(rs.getString("c_name"));
                county.setShortName(rs.getString("c_short_name"));
                county.setCode(rs.getString("c_code"));
                packageEntity.setCounty(county);
            }

            return packageEntity;
        }
    };

    @Override
    public List<Package> findAll() {
        String sql = "SELECT " +
                "p.id AS p_id, p.name AS p_name, p.credit AS p_credit, " +
                "p.expired_date AS p_expired_date, p.description AS p_description, p.price AS p_price, " +
                "c.id AS c_id, c.name AS c_name, c.short_name AS c_short_name, c.code AS c_code " +
                "FROM packages p " +
                "LEFT JOIN counties c ON p.country_id = c.id";
        return jdbcTemplate.query(sql, PACKAGE_ROW_MAPPER);
    }
}
