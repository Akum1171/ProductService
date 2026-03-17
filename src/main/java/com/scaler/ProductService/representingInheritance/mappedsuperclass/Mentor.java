package com.scaler.ProductService.representingInheritance.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "msc_mentor")
public class Mentor extends User {
    private double mentorRating;
}