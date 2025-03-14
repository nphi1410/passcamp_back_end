/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.service.implement;

import PassCamp.ass.main.constant.Constant;
import PassCamp.ass.main.constant.Directory;
import PassCamp.ass.main.constant.IdPrefix;
import PassCamp.ass.main.dto.AccountDto;
import PassCamp.ass.main.dto.ItemDto;
import PassCamp.ass.main.entity.Category;
import PassCamp.ass.main.entity.Item;
import PassCamp.ass.main.entity.ItemImage;
import PassCamp.ass.main.repository.ItemAmountRepository;
import PassCamp.ass.main.repository.ItemCategoryRepository;
import PassCamp.ass.main.repository.ItemImageRepository;
import PassCamp.ass.main.repository.ItemRepository;
import PassCamp.ass.main.service.AccountService;
import PassCamp.ass.main.service.CategoryService;
import PassCamp.ass.main.service.ItemService;
import PassCamp.ass.main.util.Generate;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author AD
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemImageRepository itemImageRepository;
    @Autowired
    private ItemAmountRepository itemAmountRepository;
    @Autowired
    private ItemCategoryRepository itemCategoryRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AccountService accountService;

    @Override
    public List<Item> getItemList() {
        return itemRepository.findAll();
    }

    @Override
    public Page<ItemDto> getItemList(
            int page,
            String categoryId,
            String name,
            String sortType
    ) {
        Pageable pageable;
        if (sortType == null || sortType.isEmpty()) {
            pageable = PageRequest.of(
                    page,
                    Constant.PAGE_SIZE
            );
        } else {
            pageable = PageRequest.of(
                    page,
                    Constant.PAGE_SIZE,
                    Sort.by(sortType)
            );
        }

        Page<Item> itemPage;

        if (!name.isBlank() && !categoryId.isBlank()) {
            List<String> itemIds = itemCategoryRepository.findItemIdsByCategoryId(categoryId);
            itemPage = itemRepository.findByItemNameContainingAndItemIdIn(name, itemIds, pageable);
        } else if (!name.isBlank()) {
            itemPage = itemRepository.findByItemNameContaining(name, pageable);
        } else if (!categoryId.isBlank()) {
            List<String> itemIds = itemCategoryRepository.findItemIdsByCategoryId(categoryId);
            itemPage = itemRepository.findByItemIdIn(itemIds, pageable);
        } else {
            itemPage = itemRepository.findAll(pageable);
        }

        Page<ItemDto> itemDtoPage = itemPage.map(item -> {
            return getItemDetails(item.getItemId());
        });

        return itemDtoPage;
    }

    @Override
    public int getItemAmount(String itemId) {
        return itemAmountRepository.findByItemId(itemId).getAvailableAmount();
    }
    
    @Override
    public List<Category> getItemCategory(String itemId) {
        List<String> categorieIds = itemCategoryRepository
                .findCategoryIdsByItemId(itemId);

        return categoryService.getCategories(categorieIds);
    }

    @Override
    public Page<Item> getItemList(List<String> itemIdList, Pageable pageable) {
        return itemRepository.findByItemIdIn(itemIdList, pageable);
    }

    @Override
    public List<Item> getItemList(List<String> itemIdList) {
        return itemRepository.findByItemIdIn(itemIdList);
    }
    
    @Override
    public ItemDto getItemDetails(String itemId) {
        ItemDto itemDto = new ItemDto();
        Item itemInfo = itemRepository.findByItemId(itemId);
        String seller = accountService.
                getAccount(itemInfo.getSellerAccountId()).
                getAccountName();
        int itemAvailableAmount = getItemAmount(itemId);
        List<Category> itemCategories = getItemCategory(itemId);
        List<ItemImage> images = itemImageRepository.findByItemId(itemId);

        itemDto.setItem(itemInfo);
        itemDto.setAvailableAmount(itemAvailableAmount);
        itemDto.setCategories(itemCategories);
        itemDto.setSellerAccount(seller);
        itemDto.setImages(images);

        return itemDto;
    }

    @Override
    public List<Item> getSellItems(String sellerAccountId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Item> getSoldItems(String sellerAccountId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String addSellItem(Item item) {
        itemRepository.save(item);
        return "item created";
    }

    @Override
    public String updateSellItem(Item updatedItem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String uploadImage(
            MultipartFile file,
            AccountDto accountDto,
            String type
    ) {
        try {
            if (file.isEmpty()) {
                return "Please select a file!";
            }

            // Validate MIME type (ensures the actual file is an image)
            Tika tika = new Tika();
            String mimeType = tika.detect(file.getInputStream());

            if (!mimeType.startsWith("image/")) {
                return "Invalid file type! Please upload a JPG or PNG image.";
            }

            // Ensure upload directory exists
            Path uploadDir = Path.of(
                    Directory.UPLOAD_IMAGE_DIR,
                    accountDto.getAccountId()
            );
            Files.createDirectories(uploadDir);

            // Generate a unique filename with original extension
            String extension = getFileExtension(file.getOriginalFilename());
            String fileName;
            if ("avatar".equals(type)) {
                fileName = "avatar." + extension;
            } else {
                fileName = Generate.getId(
                        IdPrefix.IMAGE,
                        itemImageRepository.findLastImageId()
                ) + "." + extension;
            }
            Path filePath = uploadDir.resolve(fileName);

            // Save file
            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return "File uploaded successfully: " + filePath.toString();
        } catch (IOException e) {
            return "Failed to upload file: " + e.getMessage();
        }
    }

    private String getFileExtension(String filename) {
        if (filename != null && filename.contains(".")) {
            return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        }
        return ""; // No extension found
    }
}
