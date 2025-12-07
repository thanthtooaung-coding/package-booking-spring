package com.vinn.packagebooking.common.data.repository.jpa;

import com.vinn.packagebooking.common.data.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    Optional<Class> findByName(String name);
    List<Class> findByLocation(String location);
    boolean existsByName(String name);
}
