package com.scaler.ProductService.projections;

public interface ProductWithTitleAndDescription {
    String getTitle();

    String getDescription();
}
/*
🎯 Problem Without Projection

Suppose your Product entity has:

@Entity
public class Product {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private Category category;
}

Now imagine you only need:

title
description

But you write:
List<Product> products = productRepository.findAll();

🚨 What happens?

Hibernate loads:

id

title

price

description

category (possibly)

relationships

Even if you don’t need them.

This is:

❌ More memory
❌ More DB transfer
❌ Slower performance

✅ Solution: Projection Interface

When you use:

public interface ProductWithTitleAndDescription {
    String getTitle();
    String getDescription();
}

And write:

List<ProductWithTitleAndDescription> findByPriceGreaterThan(Double price);

Spring Data will generate:

SELECT title, description FROM product WHERE price > ?

⚡ Only required columns are fetched.

🧠 What Is This Concept Called?

👉 Interface-based Projection

Spring Data JPA dynamically creates implementation of that interface.

🔵 How It Works Internally

When method returns:

List<ProductWithTitleAndDescription>

Spring:

Executes query selecting only required columns

Uses proxy to map result

Returns object implementing your interface

You never write implementation manually.

📌 Example In Repository
@Query("SELECT p.title AS title, p.description AS description FROM Product p")
List<ProductWithTitleAndDescription> findAllProjected();

OR even better (derived query):

List<ProductWithTitleAndDescription> findByPriceGreaterThan(Double price);

No @Query needed.

🚀 Why This Is Important In Real Projects

In production:

Large tables (millions of rows)

Entities with many fields

Multiple joins

If you fetch full entity unnecessarily:

Memory spike 📈
Slow API ⚠

Projection solves that.

🟢 Types of Projections in Spring Data JPA
1️⃣ Interface-based (What you wrote ✅)

Best for read-only API

2️⃣ Class-based (DTO Projection)
public class ProductDto {
    private String title;
    private String description;
}

Used with:

@Query("SELECT new com.example.ProductDto(p.title, p.description) FROM Product p")
3️⃣ Dynamic Projection
<T> List<T> findByPrice(Double price, Class<T> type);

Advanced usage.

🧠 Interview-Level Answer

If interviewer asks:

Why use projection instead of entity?

Answer:

Projection improves performance by selecting only required fields instead of loading entire entity with relationships. It reduces memory usage and database load.

🔥 Very Important (When NOT To Use Projection)

Projection is:

✔ Good for read operations
❌ Not for update operations
❌ Not for complex business logic

📌 Practical Example In Your Product API

Instead of returning full Product:

@GetMapping("/titles")
public List<ProductWithTitleAndDescription> getProducts() {
    return productRepository.findAllProjected();
}

Response:

[
  {
    "title": "Macbook",
    "description": "Apple laptop"
  }
]

No id
No price
No category

Clean & optimized.
 */