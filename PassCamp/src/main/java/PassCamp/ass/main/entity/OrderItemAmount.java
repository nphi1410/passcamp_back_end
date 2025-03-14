/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.entity;

/**
 *
 * @author AD
 */
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "OrderItemAmount")
public class OrderItemAmount {

    @Id
    @Column(name = "orderId")
    private String orderId;

    @Column(name = "itemId")
    private String itemId;
    
    @Column(name = "orderAmount")
    private int orderAmount;

    @Column(name = "totalPrice")
    private double totalPrice;
}
