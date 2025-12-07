package com.vinn.packagebooking.features.countries.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.vinn.packagebooking.common.data.model.Country;
import com.vinn.packagebooking.common.data.repository.jdbc.CountryJdbcRepository;
import com.vinn.packagebooking.common.data.repository.jpa.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.vinn.packagebooking.features.countries.api.CountryApiConstants.*;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryJdbcRepository countryJdbcRepository;
    private final CountryValidator countryValidator;

    public CountryServiceImpl(CountryRepository countryRepository, CountryJdbcRepository countryJdbcRepository, CountryValidator countryValidator) {
        this.countryRepository = countryRepository;
        this.countryJdbcRepository = countryJdbcRepository;
        this.countryValidator = countryValidator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        return countryJdbcRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Country findById(Long id) {
        return countryJdbcRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id: " + id));
    }

    @Override
    public Country create(String requestBody) {
        JsonNode jsonNode = countryValidator.parseAndValidateJson(requestBody);
        countryValidator.validateCreateRequest(jsonNode);

        Country country = new Country();
        country.setName(countryValidator.getRequiredStringField(jsonNode, FIELD_NAME));
        country.setShortName(countryValidator.getRequiredStringField(jsonNode, FIELD_SHORT_NAME));
        country.setCode(countryValidator.getRequiredStringField(jsonNode, FIELD_CODE));

        countryValidator.validateNameUniquenessForCreate(country.getName());
        countryValidator.validateCodeUniquenessForCreate(country.getCode());

        return countryRepository.save(country);
    }

    @Override
    public Country update(Long id, String requestBody) {
        Country existingCountry = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id: " + id));

        JsonNode jsonNode = countryValidator.parseAndValidateJson(requestBody);
        countryValidator.validateUpdateRequest(jsonNode);

        String name = countryValidator.getRequiredStringField(jsonNode, FIELD_NAME);
        countryValidator.validateNameUniqueness(name, existingCountry.getName());
        existingCountry.setName(name);

        String shortName = countryValidator.getRequiredStringField(jsonNode, FIELD_SHORT_NAME);
        existingCountry.setShortName(shortName);

        String code = countryValidator.getRequiredStringField(jsonNode, FIELD_CODE);
        countryValidator.validateCodeUniqueness(code, existingCountry.getCode());
        existingCountry.setCode(code);

        return countryRepository.save(existingCountry);
    }

    @Override
    public void delete(Long id) {
        if (!countryRepository.existsById(id)) {
            throw new RuntimeException("Country not found with id: " + id);
        }
        countryRepository.deleteById(id);
    }
}
