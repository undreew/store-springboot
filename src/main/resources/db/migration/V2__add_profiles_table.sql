CREATE TABLE store.profiles (
	id BIGINT NULL,
	bio TEXT NULL,
	phone_number varchar(15) NULL,
	date_of_birth DATE NULL,
	loyalty_points INT UNSIGNED DEFAULT 0 NULL,
	CONSTRAINT profiles_users_FK FOREIGN KEY (id) REFERENCES store.users(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
