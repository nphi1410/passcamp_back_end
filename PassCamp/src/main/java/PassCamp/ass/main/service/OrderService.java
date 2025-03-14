/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.service;

import PassCamp.ass.main.entity.Cart;
import PassCamp.ass.main.entity.Item;
import PassCamp.ass.main.entity.Order;
import java.util.List;

/**
 *
 * @author AD
 */
public interface OrderService {
    
    List<Order> getOrders();
    
    List<Order> getOrders(String accountId);
    
    Order getOrder(String orderId);
    
    String createOrder(Cart cart);

}
