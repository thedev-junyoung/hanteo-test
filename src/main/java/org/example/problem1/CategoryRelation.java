package org.example.problem1;

public class CategoryRelation {
    private final Integer parentCategoryId;
    private final int categoryId;
    private final String categoryName;

    public CategoryRelation(Integer parentCategoryId, int categoryId, String categoryName) {
        this.parentCategoryId = parentCategoryId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
