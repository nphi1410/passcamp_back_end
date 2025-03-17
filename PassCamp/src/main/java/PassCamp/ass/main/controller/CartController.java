/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.controller;

import PassCamp.ass.main.entity.Cart;
import PassCamp.ass.main.entity.CartItem;
import PassCamp.ass.main.service.CartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AD
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestParam String accountId) {
        Cart cart = cartService.getCart(accountId);
        return ResponseEntity.ok(cart);  
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartItem cartItem) {
        String response = cartService.addToCart(cartItem);
        return ResponseEntity.ok(response);  
    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItem>> getCartItems(@RequestParam String cartId) {
        List<CartItem> cartItems = cartService.getCartItems(cartId);

        return ResponseEntity.ok(cartItems);

    }
    
    @PostMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestParam String cartItemId) {
        String response = cartService.removeFromCart(cartItemId);
        return ResponseEntity.ok(response);
    }
}
