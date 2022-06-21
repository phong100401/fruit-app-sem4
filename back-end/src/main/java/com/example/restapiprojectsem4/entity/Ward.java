package com.example.restapiprojectsem4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wards")
@Entity
public class Ward {
    @Id
    private int id;
    private String name;
    @Column(name = "districtId")
    private int districtId;

    @ManyToOne
    @JoinColumn(name = "districtId",insertable = false,updatable = false)
    @JsonIgnore
    private District district;
}
