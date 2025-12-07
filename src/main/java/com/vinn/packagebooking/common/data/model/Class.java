package com.vinn.packagebooking.common.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "required_credit")
    private Integer requiredCredit;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "class_start_time")
    private LocalDateTime classStartTime;

    @Column(name = "class_end_time")
    private LocalDateTime classEndTime;

    @Column
    private Integer slot;

    @Column
    private String location;
}
