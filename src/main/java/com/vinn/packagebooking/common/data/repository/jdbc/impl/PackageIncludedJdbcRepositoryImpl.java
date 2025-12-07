package com.vinn.packagebooking.common.data.repository.jdbc.impl;

import com.vinn.packagebooking.common.data.model.Package;
import com.vinn.packagebooking.common.data.model.PackageIncluded;
import com.vinn.packagebooking.common.data.repository.jdbc.PackageIncludedJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PackageIncludedJdbcRepositoryImpl implements PackageIncludedJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public PackageIncludedJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<PackageIncluded> PACKAGE_INCLUDED_ROW_MAPPER = new RowMapper<PackageIncluded>() {
        @Override
        public PackageIncluded mapRow(ResultSet rs, int rowNum) throws SQLException {
            PackageIncluded packageIncluded = new PackageIncluded();
            packageIncluded.setId(rs.getLong("pi_id"));
            packageIncluded.setDescription(rs.getString("pi_description"));

            // Map Package
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

            packageIncluded.setPackageEntity(packageEntity);

            return packageIncluded;
        }
    };

    @Override
    public List<PackageIncluded> findAll() {
        String sql = "SELECT " +
                "pi.id AS pi_id, pi.description AS pi_description, " +
                "p.id AS p_id, p.name AS p_name, p.credit AS p_credit, " +
                "p.expired_date AS p_expired_date, p.description AS p_description, p.price AS p_price " +
                "FROM package_included pi " +
                "INNER JOIN packages p ON pi.package_id = p.id";
        return jdbcTemplate.query(sql, PACKAGE_INCLUDED_ROW_MAPPER);
    }
}
