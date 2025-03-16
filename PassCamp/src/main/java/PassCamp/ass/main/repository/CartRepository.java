/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.repository;

import PassCamp.ass.main.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AD
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, String>{

    @Query("SELECT c.cartId FROM Cart c ORDER BY c.cartId DESC LIMIT 1")
    String findLastCartId();
    
    public Cart findByAccountId(String accountId);
    
}
