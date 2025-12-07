package com.vinn.packagebooking.features.countries.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinn.packagebooking.common.data.repository.jpa.CountryRepository;
import com.vinn.packagebooking.common.exception.InvalidFieldException;
import org.springframework.stereotype.Component;

import static com.vinn.packagebooking.features.countries.api.CountryApiConstants.*;

@Component
public class CountryValidator {

    private final CountryRepository countryRepository;
    private final ObjectMapper objectMapper;

    public CountryValidator(CountryRepository countryRepository, ObjectMapper objectMapper) {
        this.countryRepository = countryRepository;
        this.objectMapper = objectMapper;
    }

    public JsonNode parseAndValidateJson(String requestBody) {
        if (requestBody == null || requestBody.trim().isEmpty()) {
            throw new InvalidFieldException(FIELD_REQUEST_BODY, "Request body cannot be empty");
        }

        try {
            return objectMapper.readTree(requestBody);
        } catch (Exception e) {
            throw new InvalidFieldException(FIELD_REQUEST_BODY, "Invalid JSON format: " + e.getMessage());
        }
    }

    public void validateCreateRequest(JsonNode jsonNode) {
        if (!jsonNode.has(FIELD_NAME) || jsonNode.get(FIELD_NAME).isNull() || jsonNode.get(FIELD_NAME).asText().trim().isEmpty()) {
            throw new InvalidFieldException(FIELD_NAME, "Name is required and cannot be empty");
        }
        if (!jsonNode.has(FIELD_SHORT_NAME) || jsonNode.get(FIELD_SHORT_NAME).isNull() || jsonNode.get(FIELD_SHORT_NAME).asText().trim().isEmpty()) {
            throw new InvalidFieldException(FIELD_SHORT_NAME, "Short name is required and cannot be empty");
        }
        if (!jsonNode.has(FIELD_CODE) || jsonNode.get(FIELD_CODE).isNull() || jsonNode.get(FIELD_CODE).asText().trim().isEmpty()) {
            throw new InvalidFieldException(FIELD_CODE, "Code is required and cannot be empty");
        }

        validateNoUnknownFields(jsonNode);
    }

    public void validateUpdateRequest(JsonNode jsonNode) {
        if (!jsonNode.has(FIELD_NAME) || jsonNode.get(FIELD_NAME).isNull() || jsonNode.get(FIELD_NAME).asText().trim().isEmpty()) {
            throw new InvalidFieldException(FIELD_NAME, "Name is required and cannot be empty");
        }
        if (!jsonNode.has(FIELD_SHORT_NAME) || jsonNode.get(FIELD_SHORT_NAME).isNull() || jsonNode.get(FIELD_SHORT_NAME).asText().trim().isEmpty()) {
            throw new InvalidFieldException(FIELD_SHORT_NAME, "Short name is required and cannot be empty");
        }
        if (!jsonNode.has(FIELD_CODE) || jsonNode.get(FIELD_CODE).isNull() || jsonNode.get(FIELD_CODE).asText().trim().isEmpty()) {
            throw new InvalidFieldException(FIELD_CODE, "Code is required and cannot be empty");
        }

        validateNoUnknownFields(jsonNode);
    }

    public void validateNameUniqueness(String name, String existingName) {
        if (!name.equals(existingName) && countryRepository.existsByName(name)) {
            throw new InvalidFieldException(FIELD_NAME, "Country with this name already exists");
        }
    }

    public void validateNameUniquenessForCreate(String name) {
        if (countryRepository.existsByName(name)) {
            throw new InvalidFieldException(FIELD_NAME, "Country with this name already exists");
        }
    }

    public void validateCodeUniqueness(String code, String existingCode) {
        if (!code.equals(existingCode) && countryRepository.existsByCode(code)) {
            throw new InvalidFieldException(FIELD_CODE, "Country with this code already exists");
        }
    }

    public void validateCodeUniquenessForCreate(String code) {
        if (countryRepository.existsByCode(code)) {
            throw new InvalidFieldException(FIELD_CODE, "Country with this code already exists");
        }
    }

    public String getRequiredStringField(JsonNode jsonNode, String fieldName) {
        if (!jsonNode.has(fieldName)) {
            throw new InvalidFieldException(fieldName, "Field is required");
        }
        JsonNode fieldNode = jsonNode.get(fieldName);
        if (fieldNode.isNull()) {
            throw new InvalidFieldException(fieldName, "Field cannot be null");
        }
        if (!fieldNode.isTextual()) {
            throw new InvalidFieldException(fieldName, "Field must be a string");
        }
        String value = fieldNode.asText().trim();
        if (value.isEmpty()) {
            throw new InvalidFieldException(fieldName, "Field cannot be empty");
        }
        return value;
    }

    private void validateNoUnknownFields(JsonNode jsonNode) {
        jsonNode.fieldNames().forEachRemaining(fieldName -> {
            boolean isAllowed = false;
            for (String allowedField : ALLOWED_FIELDS) {
                if (fieldName.equals(allowedField)) {
                    isAllowed = true;
                    break;
                }
            }
            if (!isAllowed) {
                throw new InvalidFieldException(fieldName, "Unknown field. Allowed fields are: " + String.join(", ", ALLOWED_FIELDS));
            }
        });
    }
}
