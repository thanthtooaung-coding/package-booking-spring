package com.vinn.packagebooking.common.data.repository.jpa;

import com.vinn.packagebooking.common.data.model.PackageIncluded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageIncludedRepository extends JpaRepository<PackageIncluded, Long> {
    List<PackageIncluded> findByPackageEntityId(Long packageId);
}
