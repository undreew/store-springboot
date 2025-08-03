CREATE TABLE store.wishlist (
	user_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	CONSTRAINT wishlist_pk PRIMARY KEY (user_id,product_id),
	CONSTRAINT wishlist_users_FK FOREIGN KEY (user_id) REFERENCES store.users(id),
	CONSTRAINT wishlist_products_FK FOREIGN KEY (product_id) REFERENCES store.products(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
