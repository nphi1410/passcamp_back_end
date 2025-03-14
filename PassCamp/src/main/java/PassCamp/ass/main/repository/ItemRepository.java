/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.repository;

import PassCamp.ass.main.entity.Item;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AD
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    Item findByItemId(String itemId);

    List<Item> findBySellerAccountId(String accountId);

    Page<Item> findByItemNameContaining(String name, Pageable pageable);

    Page<Item> findByItemNameContainingAndItemIdIn(
            String name,
            List<String> itemIds,
            Pageable pageable
    );

    List<Item> findByItemIdIn(List<String> itemIds);

    Page<Item> findByItemIdIn(List<String> itemIds, Pageable pageable);

    List<Item> findAllByItemName(String name);

}
