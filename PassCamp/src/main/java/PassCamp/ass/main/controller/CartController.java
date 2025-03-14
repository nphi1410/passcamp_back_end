/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.controller;

import PassCamp.ass.main.service.CategoryService;
import org.springframework.stereotype.Controller;


/**
 *
 * @author AD
 */
@Controller
public class CartController {
    private final CategoryService categoryService;

    public CartController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

}
