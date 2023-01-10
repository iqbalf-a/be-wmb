package com.wmb2.service;

import com.wmb2.exception.EntityExistException;
import com.wmb2.exception.NotFoundException;
import com.wmb2.model.Category;
import com.wmb2.model.Food;
import com.wmb2.model.request.FoodRequest;
import com.wmb2.repository.ICategoryRepository;
import com.wmb2.repository.IFoodRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService implements IFoodService {

    private IFoodRepository foodRepository;
    private ICategoryRepository categoryRepository;

    public FoodService(IFoodRepository foodRepository, ICategoryRepository categoryRepository) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Food> list() {
        List<Food> foods = foodRepository.findAll();
        return foods;
    }

    @Override
    public Food create(FoodRequest foodRequest) {
        try {
            System.out.println(foodRequest.getCategoryId());
            Optional<Category> category = categoryRepository.findById(foodRequest.getCategoryId());

            if (category.isEmpty()) {
                throw new NotFoundException("Category not found");
            }

            Food food = new Food();
            food.setFoodName(foodRequest.getFoodName());
            food.setPrice(foodRequest.getPrice());
            food.setCategory(category.get());
            return foodRepository.save(food);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Food get(String id) {
        Optional<Food> food = foodRepository.findById(id);
        if (food.isEmpty()) {
            throw new NotFoundException("Food not found");
        }
        return food.get();
    }

    @Override
    public void delete(String id) {
        try {
            Food existingFood = get(id);
            foodRepository.delete(existingFood);
        } catch (NotFoundException e) {
            throw new NotFoundException("ID not found");
        }
    }

    @Override
    public Page<Food> list(Integer page, Integer size, String direction, String sortBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Food> result = foodRepository.findAll(pageable);
        return result;
    }
}
