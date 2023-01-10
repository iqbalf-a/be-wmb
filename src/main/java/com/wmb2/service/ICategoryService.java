package com.wmb2.service;

import com.wmb2.model.Category;
import com.wmb2.model.request.CategoryRequest;
import org.springframework.data.domain.Page;


public interface ICategoryService {
    Page<Category> list(Integer page, Integer size, String sortBy, String direction);

    Category create(CategoryRequest categoryRequest);
}
