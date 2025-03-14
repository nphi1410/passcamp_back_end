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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Cart")
public class Cart {

    @Id
    @Column(name = "cartId")
    private String cartId;

    @Column(name = "accountId")
    private String accountId;

    @Column(name = "itemAmount")
    private int itemAmount;

    @Column(name = "totalPrice")
    private double totalPrice;
}
