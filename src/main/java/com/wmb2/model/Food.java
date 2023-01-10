package com.wmb2.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "m_food")
public class Food {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String foodId;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "price")
    private String price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId='" + foodId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
