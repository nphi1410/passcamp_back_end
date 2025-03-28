/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.service.implement;

import PassCamp.ass.main.entity.Cart;
import PassCamp.ass.main.entity.CartItem;
import PassCamp.ass.main.repository.CartItemRepository;
import PassCamp.ass.main.repository.CartRepository;
import PassCamp.ass.main.service.CartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AD
 */
@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Override
    public Cart getCart(String accountId) {
        return cartRepository.findByAccountId(accountId);
    }
    
    @Override
    public String addToCart(CartItem cartItem) {
        Cart cart = cartRepository.findByCartId(cartItem.getCartId());
        cart.setItemAmount(cart.getItemAmount() + 1);
        cart.setTotalPrice(cart.getTotalPrice()+cartItem.getTotalPrice());
        
        cartRepository.save(cart);
        cartItemRepository.save(cartItem);
        return "Item added";
    }
    
    @Override
    public List<CartItem> getCartItems(String cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
    
    @Override
    public String removeFromCart(String itemId) {
        CartItem cartItem = cartItemRepository.findByItemId(itemId);
        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
            return "Item removed from cart";
        } else {
            return "Item not found in cart";
        }
    }
}
