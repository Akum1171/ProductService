package com.scaler.ProductService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String title;

    @JsonIgnore
   @OneToMany(mappedBy = "category")//(fetch = FetchType.EAGER)
   private List<Product> products;
}