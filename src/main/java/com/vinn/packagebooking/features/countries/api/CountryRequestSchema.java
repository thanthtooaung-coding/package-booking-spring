package com.vinn.packagebooking.features.countries.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import static com.vinn.packagebooking.features.countries.api.CountryApiConstants.*;

@Schema(
    description = "Country request body. Required fields: " + FIELD_NAME + ", " + FIELD_SHORT_NAME + ", " + FIELD_CODE,
    example = "{\"" + FIELD_NAME + "\":\"Kenya\",\"" + FIELD_SHORT_NAME + "\":\"KE\",\"" + FIELD_CODE + "\":\"001\"}"
)
@Getter
@Setter
public class CountryRequestSchema {
    
    @Schema(description = "Country name", example = "Kenya", required = true)
    private String name;

    @Schema(description = "Country short name", example = "KE", required = true)
    private String shortName;

    @Schema(description = "Country code", example = "001", required = true)
    private String code;

    public static final String EXAMPLE_JSON = "{\"" + FIELD_NAME + "\":\"Kenya\",\"" + FIELD_SHORT_NAME + "\":\"KE\",\"" + FIELD_CODE + "\":\"001\"}";
}
