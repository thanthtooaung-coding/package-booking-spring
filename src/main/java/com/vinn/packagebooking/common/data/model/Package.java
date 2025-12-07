package com.vinn.packagebooking.common.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "packages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer credit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "expired_date")
    private LocalDate expiredDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private BigDecimal price;
}
