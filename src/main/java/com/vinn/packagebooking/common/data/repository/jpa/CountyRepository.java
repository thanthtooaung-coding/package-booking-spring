package com.vinn.packagebooking.common.data.repository.jpa;

import com.vinn.packagebooking.common.data.model.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountyRepository extends JpaRepository<County, Long> {
    Optional<County> findByName(String name);
    Optional<County> findByCode(String code);
    boolean existsByName(String name);
    boolean existsByCode(String code);
}
