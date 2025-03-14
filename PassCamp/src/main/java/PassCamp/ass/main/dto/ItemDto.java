/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.dto;

import PassCamp.ass.main.entity.Category;
import PassCamp.ass.main.entity.Item;
import PassCamp.ass.main.entity.ItemImage;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author AD
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDto {
    private Item item;
    private String sellerAccount;
    private int availableAmount;
    private List<Category> categories;
    private List<ItemImage> images;
}
