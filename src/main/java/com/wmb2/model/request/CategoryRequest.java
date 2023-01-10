package com.wmb2.model.request;

public class CategoryRequest {
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryRequest{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
