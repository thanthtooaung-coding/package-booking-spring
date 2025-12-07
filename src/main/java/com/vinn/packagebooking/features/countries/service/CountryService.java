package com.vinn.packagebooking.features.countries.service;

import com.vinn.packagebooking.common.data.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country findById(Long id);
    Country create(String requestBody);
    Country update(Long id, String requestBody);
    void delete(Long id);
}
