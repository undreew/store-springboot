ALTER TABLE store.wishlist DROP FOREIGN KEY wishlist_products_FK;
ALTER TABLE store.wishlist ADD CONSTRAINT wishlist_products_FK FOREIGN KEY (product_id) REFERENCES store.products(id) ON DELETE CASCADE ON UPDATE RESTRICT;
ALTER TABLE store.wishlist DROP FOREIGN KEY wishlist_users_FK;
ALTER TABLE store.wishlist ADD CONSTRAINT wishlist_users_FK FOREIGN KEY (user_id) REFERENCES store.users(id) ON DELETE CASCADE ON UPDATE RESTRICT;
