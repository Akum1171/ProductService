package com.scaler.ProductService.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}

/*
  If interviewer asks:

What is difference between @MappedSuperclass and @Inheritance?

Answer:

@MappedSuperclass	                             @Inheritance
No separate table	                         Parent table exists
Used only for sharing fields	             Used for polymorphic queries
Cannot be queried directly	                 Can be queried
 */
