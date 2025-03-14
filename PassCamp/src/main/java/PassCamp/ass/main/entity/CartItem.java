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
@Table(name = "CartItem")
public class CartItem {

    @Id
    @Column(name = "cartId")
    private String cartId;

    @Column(name = "itemId")
    private String itemId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "totalPrice")
    private double totalPrice;
}
