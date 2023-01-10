package com.wmb2.controller;

import com.wmb2.model.Food;
import com.wmb2.model.request.FoodRequest;
import com.wmb2.model.response.PagingResponse;
import com.wmb2.model.response.SuccessResponse;
import com.wmb2.service.IFoodService;
import com.wmb2.utils.UrlMapping;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.FOODS)
@Validated
public class FoodController {

    private ModelMapper modelMapper;
    private IFoodService foodService;

    public FoodController(ModelMapper modelMapper, IFoodService foodService) {
        this.modelMapper = modelMapper;
        this.foodService = foodService;
    }

    @GetMapping
    public ResponseEntity getAllFoods(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "foodId") String sortBy
    ) throws Exception {
        Page<Food> result = foodService.list(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get food list", result));
    }

    @PostMapping
    public ResponseEntity createFood(FoodRequest foodRequest) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Food result = foodService.create(foodRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success add food", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) throws Exception {
        foodService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete food", null));
    }


}
