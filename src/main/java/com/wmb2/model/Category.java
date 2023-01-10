package com.wmb2.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "m_category")
public class Category {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "category_name")
    private String categoryName;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String caategoryName) {
        this.categoryName = caategoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", caategoryName='" + categoryName + '\'' +
                '}';
    }
}
