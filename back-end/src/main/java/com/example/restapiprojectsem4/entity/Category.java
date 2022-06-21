package com.example.restapiprojectsem4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @CreationTimestamp// suy ra đc ngày giờ tạo
    private LocalDateTime createdAt;
    @UpdateTimestamp// Suy ra đc ngày giờ tạo
    private LocalDateTime updatedAt;

//    @LastModifiedDate// Null thường đi theo @CreatedDate. thay đổi đc = user
//    private LocalDateTime endDate;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Product> products;
}
