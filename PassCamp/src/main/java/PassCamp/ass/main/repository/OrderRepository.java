/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.repository;

import PassCamp.ass.main.entity.Order;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AD
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    
    @Query("SELECT o.orderId FROM Order o ORDER BY o.orderId DESC LIMIT 1")
    String findLastOrderId();
    
    List<Order> findByPayAccountId(String accountId);
    
    Order findByOrderId(String orderId);
    
    List<Order> findByOrderDate(Date orderDate);
            
}
