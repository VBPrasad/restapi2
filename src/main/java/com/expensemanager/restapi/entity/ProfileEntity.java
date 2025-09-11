package com.expensemanager.restapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name="tbl_profile")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String  name;

    @Column(unique= true)
    private  String email;

    @Column(unique = true)
    private String profileId;

    private String password;

    @Column(updatable = false)
    @CreationTimestamp
    private String createdAt;
    
    @UpdateTimestamp
    private String updatedAt;

}
