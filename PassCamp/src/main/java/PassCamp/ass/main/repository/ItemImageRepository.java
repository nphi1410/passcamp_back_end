/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.repository;

import PassCamp.ass.main.entity.ItemImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author AD
 */
public interface ItemImageRepository extends JpaRepository<ItemImage, String> {
    
    @Query("SELECT i.imageId FROM ItemImage i ORDER BY i.imageId DESC LIMIT 1")
    String findLastImageId();

    List<ItemImage> findByItemId(String itemId);
    
    ItemImage findByItemIdAndIsPrimary(String itemId, boolean isPrimary);
}
