package com.vinn.packagebooking.common.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String email;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column
    private String address;

    @Column
    private String gender;

    @Column(name = "is_first_time_login")
    private Boolean isFirstTimeLogin;

    @Column(name = "entity_type")
    private Integer entityType;
}
