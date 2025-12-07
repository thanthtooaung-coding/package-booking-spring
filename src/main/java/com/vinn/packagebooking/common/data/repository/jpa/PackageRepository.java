package com.vinn.packagebooking.common.data.repository.jpa;

import com.vinn.packagebooking.common.data.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    Optional<Package> findByName(String name);
    List<Package> findByCountryId(Long countryId);
    boolean existsByName(String name);
}
