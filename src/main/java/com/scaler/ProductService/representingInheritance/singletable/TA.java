package com.scaler.ProductService.representingInheritance.singletable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "3")
public class TA extends User {
    private int numberOfSessions;
    private double avgRating;
}