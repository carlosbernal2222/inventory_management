package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.Model.Administrator;
import com.skillstorm.inventorymanagement.Model.Category;

import java.util.List;

public interface CategoryService {

    //Read Operations
    List<Category> getAllCategories();

}
