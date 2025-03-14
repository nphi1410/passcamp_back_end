/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.service.implement;

import PassCamp.ass.main.entity.Category;
import PassCamp.ass.main.repository.CategoryRepository;
import PassCamp.ass.main.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AD
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
    
    
    @Override
    public List<Category> getCategories(List<String> categoryIds) {
        return categoryRepository.findByCategoryIdIn(categoryIds);
    }

    @Override
    public Category getCategory(int categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }


}
