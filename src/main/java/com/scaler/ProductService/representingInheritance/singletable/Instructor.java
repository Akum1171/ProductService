package com.scaler.ProductService.representingInheritance.singletable;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@DiscriminatorValue(value = "1")
public class Instructor extends User {
    private String specialization;
}