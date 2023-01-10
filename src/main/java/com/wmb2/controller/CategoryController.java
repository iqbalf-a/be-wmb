package com.wmb2.controller;

import com.wmb2.model.Category;
import com.wmb2.model.request.CategoryRequest;
import com.wmb2.model.response.PagingResponse;
import com.wmb2.model.response.SuccessResponse;
import com.wmb2.service.ICategoryService;
import com.wmb2.utils.UrlMapping;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.CATEGORIES)
public class CategoryController {
    ICategoryService categoryService;
    private ModelMapper modelMapper;

    public CategoryController(ICategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    ResponseEntity getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "categoryId") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction
    ) {
        Page<Category> result = categoryService.list(page, size, sortBy, direction);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get all category", result));
    }

    @PostMapping
    ResponseEntity createCategory(@RequestBody CategoryRequest categoryRequest) {
//        Category category = modelMapper.map(categoryRequest, Category.class);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Category result = categoryService.create(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create category", result));
    }
}
