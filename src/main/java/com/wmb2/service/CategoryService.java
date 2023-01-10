package com.wmb2.service;

import com.wmb2.exception.EntityExistException;
import com.wmb2.model.Category;
import com.wmb2.model.request.CategoryRequest;
import com.wmb2.repository.ICategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CategoryService implements ICategoryService{

    ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> list(Integer page, Integer size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page-1), size, sort);
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories;
    }

    @Override
    public Category create(CategoryRequest categoryRequest) {
        try {
            Category category = new Category();
            category.setCategoryName(categoryRequest.getCategoryName());
            return categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

}
