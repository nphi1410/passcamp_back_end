/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.service;

import PassCamp.ass.main.entity.Cart;
import PassCamp.ass.main.entity.CartItem;
import java.util.List;

/**
 *
 * @author AD
 */
public interface CartService {
    
    Cart getCart(String accountId);

    String addToCart(CartItem cartItem);
    
    List<CartItem> getCartItems(String cartId);
}
