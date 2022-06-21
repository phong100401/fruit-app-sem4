package com.example.restapiprojectsem4.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "districts")
@Entity
public class District {
    @Id
    private int id;
    private String name;
    @Column(name = "provinceId")
    private int provinceId;

    @OneToMany(mappedBy = "district")
    private Set<Ward> wards;

    @ManyToOne
    @JoinColumn(name = "provinceId",insertable = false,updatable = false)
    @JsonIgnore
    private Province province;
}
