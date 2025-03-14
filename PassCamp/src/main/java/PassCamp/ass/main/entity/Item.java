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
@Table(name = "Item")
public class Item {

    @Id
    @Column(name = "itemId")
    private String itemId;

    @Column(name = "sellerAccountId")
    private String sellerAccountId;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "price")
    private double price;

    @Column(name = "material")
    private String material;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "width")
    private double width;

    @Column(name = "description")
    private String description;

    @Column(name = "isInStock")
    private boolean isInStock;
}
