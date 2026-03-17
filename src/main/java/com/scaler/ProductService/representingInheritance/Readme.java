package com.scaler.ProductService.representingInheritance;

public class Readme {
    /*
      ✅ 1️⃣ SINGLE_TABLE Strategy
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Product { }

All subclasses are stored in one single table.

Example:
@Entity
public class Book extends Product {}

@Entity
public class Electronics extends Product {}
📌 How it works:

One table:

product
--------------------------------
id | name | author | warranty | type
✅ Pros:

Fast queries (no joins)

Simple structure

❌ Cons:

Many NULL columns

Table becomes wide

🔥 Most commonly used in interviews.

✅ 2️⃣ JOINED Strategy
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product { }

Each class has its own table, joined using foreign key.

Tables:
product (id, name)
book (id, author)
electronics (id, warranty)
✅ Pros:

Proper normalization

No NULL columns

❌ Cons:

Requires JOIN (slower)

🔥 Preferred for clean DB design.

✅ 3️⃣ TABLE_PER_CLASS Strategy
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product { }

Each subclass has its own complete table.

Tables:
book (id, name, author)
electronics (id, name, warranty)
✅ Pros:

No joins

No NULLs

❌ Cons:

Duplicate columns

Union queries are expensive

🔥 Rarely used in real projects.

🔥 Interview Answer (Short Version)

“In Spring Boot using JPA, inheritance is implemented using @Inheritance annotation. There are three strategies: SINGLE_TABLE, JOINED, and TABLE_PER_CLASS. SINGLE_TABLE stores all classes in one table, JOINED creates separate tables with joins, and TABLE_PER_CLASS creates separate full tables per subclass.”
     */
}
