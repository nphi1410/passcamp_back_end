/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.service.implement;

import PassCamp.ass.main.constant.IdPrefix;
import PassCamp.ass.main.constant.OrderState;
import PassCamp.ass.main.entity.Cart;
import PassCamp.ass.main.entity.CartItem;
import PassCamp.ass.main.entity.Item;
import PassCamp.ass.main.entity.Order;
import PassCamp.ass.main.entity.OrderItemAmount;
import PassCamp.ass.main.repository.OrderItemAmountRepository;
import PassCamp.ass.main.repository.OrderRepository;
import PassCamp.ass.main.service.CartService;
import PassCamp.ass.main.service.ItemService;
import PassCamp.ass.main.service.OrderService;
import PassCamp.ass.main.util.Generate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AD
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemAmountRepository orderItemAmountRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private ItemService itemService;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrders(String accountId) {
        return orderRepository.findByPayAccountId(accountId);
    }

    @Override
    public Order getOrder(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public String createOrder(Cart cart) {
        if (cart == null) {
            return "nothing in cart";
        }

        List<CartItem> cartItemList = new ArrayList<>(
                cartService.getCartItems(cart.getCartId())
        );

        List<String> itemIds = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            itemIds.add(cartItem.getItemId());
        }

        List<Item> itemList = new ArrayList<>(
                itemService.getItemList(itemIds)
        );
        while (!cartItemList.isEmpty()) {
            Order order = new Order();
            order.setOrderId(
                    Generate.getId(
                            IdPrefix.ORDER,
                            orderRepository.findLastOrderId()
                    )
            );
            double totalOrderPrice = 0;

            String sellerAccount = itemList.get(0).getSellerAccountId();
            List<CartItem> processedItems = new ArrayList<>();

            // Process all items with the same sellerAccount
            int index = 0;
            for (CartItem cartItem : cartItemList) {
                if (itemList.get(index).getSellerAccountId()
                        .equals(sellerAccount)) {
                    OrderItemAmount orderItem = new OrderItemAmount();
                    orderItem.setItemId(cartItem.getItemId());
                    orderItem.setOrderId(order.getOrderId());
                    orderItem.setOrderAmount(cartItem.getAmount());
                    orderItem.setTotalPrice(cartItem.getTotalPrice());

                    orderItemAmountRepository.save(orderItem);
                    totalOrderPrice += orderItem.getTotalPrice();
                    processedItems.add(cartItem);
                }
                index++;
            }

            cartItemList.removeAll(processedItems);

            order.setTotalOrderPrice(totalOrderPrice);
            order.setOrderDate(LocalDateTime.now());
            order.setPayAccountId(cart.getAccountId());
            order.setState(OrderState.ORDERED);

            orderRepository.save(order);
        }

        return "order Created";
    }

}
