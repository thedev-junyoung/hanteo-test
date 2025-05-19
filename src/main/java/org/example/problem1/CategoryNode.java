package org.example.problem1;

import java.util.ArrayList;
import java.util.List;

public class CategoryNode {
    private final int categoryId;
    private final String categoryName;
    private final List<CategoryNode> childCategories = new ArrayList<>();

    public CategoryNode(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<CategoryNode> getChildCategories() {
        return childCategories;
    }
}
