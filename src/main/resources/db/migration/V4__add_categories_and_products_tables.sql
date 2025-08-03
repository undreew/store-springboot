CREATE TABLE store.categories (
	id TINYINT auto_increment NOT NULL,
	name varchar(255) NOT NULL,
	CONSTRAINT categories_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE store.products (
	id BIGINT auto_increment NOT NULL,
	name varchar(255) NOT NULL,
	price DECIMAL(10, 2) NULL,
	category_id TINYINT NOT NULL,
	CONSTRAINT products_pk PRIMARY KEY (id),
	CONSTRAINT products_categories_FK FOREIGN KEY (category_id) REFERENCES store.categories(id) ON DELETE RESTRICT
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

