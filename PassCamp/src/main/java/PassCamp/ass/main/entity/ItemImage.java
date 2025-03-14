/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author AD
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ItemImage")
public class ItemImage {

    @Id
    @Column(name = "imageId")
    private String imageId;
    
    @Column(name = "itemId")
    private String itemId;
    
    @Column(name = "imagePath")
    private String imagePath;
    
    @Column(name = "isPrimary")
    private boolean isPrimary;

}
