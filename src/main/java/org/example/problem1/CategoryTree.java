package org.example.problem1;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class CategoryTree {
    private final List<CategoryNode> rootCategories;

    public CategoryTree(List<CategoryNode> rootCategories) {
        this.rootCategories = rootCategories;
    }

    public String toJson() {
        ObjectMapper mapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build();
        try {
            return mapper.writeValueAsString(rootCategories);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize category tree to JSON", e);
        }
    }

    public Optional<CategoryNode> findById(int categoryId) {
        return findNode(category -> category.getCategoryId() == categoryId);
    }

    public Optional<CategoryNode> findByName(String categoryName) {
        return findNode(category -> category.getCategoryName().equals(categoryName));
    }

    private Optional<CategoryNode> findNode(Predicate<CategoryNode> predicate) {
        for (CategoryNode root : rootCategories) {
            Optional<CategoryNode> result = searchRecursively(root, predicate);
            if (result.isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }

    private Optional<CategoryNode> searchRecursively(CategoryNode current, Predicate<CategoryNode> predicate) {
        if (predicate.test(current)) return Optional.of(current);
        for (CategoryNode child : current.getChildCategories()) {
            Optional<CategoryNode> result = searchRecursively(child, predicate);
            if (result.isPresent()) return result;
        }
        return Optional.empty();
    }
}
