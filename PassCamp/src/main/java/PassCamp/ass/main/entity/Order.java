/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.entity;

/**
 *
 * @author AD
 */
import PassCamp.ass.main.constant.OrderState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Order")
public class Order {

    @Id
    @Column(name = "orderId")
    private String orderId;

    @Column(name = "totalOrderPrice")
    private double totalOrderPrice;

    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Column(name = "payAccountId")
    private String payAccountId;

    @Column(name = "state")
    private OrderState state;
}


