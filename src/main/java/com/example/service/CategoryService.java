package com.example.service;

import java.util.List;

import com.example.domain.Category;
import com.example.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }
}
