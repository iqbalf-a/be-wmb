package com.wmb2.repository;

import com.wmb2.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodRepository extends JpaRepository<Food, String> {
}
