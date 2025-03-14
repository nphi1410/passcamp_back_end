/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.service;

import PassCamp.ass.main.dto.AccountDto;
import PassCamp.ass.main.dto.ItemDto;
import PassCamp.ass.main.dto.ItemDto;
import PassCamp.ass.main.entity.Category;
import PassCamp.ass.main.entity.Item;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author AD
 */
public interface ItemService {

    List<Item> getItemList();

    Page<ItemDto> getItemList(
            int page,
            String categoryId,
            String name,
            String sortType
    );

    int getItemAmount(String itemId);

    List<Category> getItemCategory(String itemId);

    Page<Item> getItemList(List<String> itemIdList, Pageable pageable);

    List<Item> getItemList(List<String> itemIdList);

    ItemDto getItemDetails(String itemId);

    List<Item> getSellItems(String sellerAccountId);

    List<Item> getSoldItems(String sellerAccountId);

    String addSellItem(Item item);

    String updateSellItem(Item updatedItem);

    String uploadImage(MultipartFile file, AccountDto accountDto, String type);
}
