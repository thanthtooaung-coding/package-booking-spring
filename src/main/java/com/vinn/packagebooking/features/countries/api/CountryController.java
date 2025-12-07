package com.vinn.packagebooking.features.countries.api;

import com.vinn.packagebooking.common.data.model.Country;
import com.vinn.packagebooking.features.countries.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @Operation(summary = "Get all countries")
    public ResponseEntity<List<Country>> findAll() {
        List<Country> countries = countryService.findAll();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get country by ID")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        Country country = countryService.findById(id);
        return ResponseEntity.ok(country);
    }

    @PostMapping
    @Operation(summary = "Create a new country")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CountryRequestSchema.class),
                    examples = @ExampleObject(
                            name = "Example Country",
                            value = CountryRequestSchema.EXAMPLE_JSON
                    )
            )
    )
    public ResponseEntity<Country> create(@RequestBody String requestBody) {
        Country country = countryService.create(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(country);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing country")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CountryRequestSchema.class),
                    examples = @ExampleObject(
                            name = "Example Country",
                            value = CountryRequestSchema.EXAMPLE_JSON
                    )
            )
    )
    public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody String requestBody) {
        Country country = countryService.update(id, requestBody);
        return ResponseEntity.ok(country);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
