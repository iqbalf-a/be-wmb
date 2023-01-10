package com.wmb2.service;

import com.wmb2.model.Food;
import com.wmb2.model.request.FoodRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IFoodService {

    List<Food> list() throws Exception;

    Food create(FoodRequest foodRequest) throws Exception;

    Food get(String id) throws Exception;

    void delete(String id) throws Exception;

    Page<Food> list(Integer page, Integer size, String direction, String sortBy) throws Exception;

}
