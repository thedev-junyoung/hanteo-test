package org.example.problem1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryTreeBuilder {

    public CategoryTree buildTree(List<CategoryRelation> categoryRelations) {
        Map<Integer, CategoryNode> categoryNodeMap = new HashMap<>();

        for (CategoryRelation relation : categoryRelations) {
            categoryNodeMap.putIfAbsent(
                    relation.getCategoryId(),
                    new CategoryNode(relation.getCategoryId(), relation.getCategoryName())
            );
        }

        List<CategoryNode> rootCategories = new ArrayList<>();
        for (CategoryRelation relation : categoryRelations) {
            CategoryNode current = categoryNodeMap.get(relation.getCategoryId());
            Integer parentId = relation.getParentCategoryId();
            if (parentId == null) {
                rootCategories.add(current);
            } else {
                CategoryNode parent = categoryNodeMap.get(parentId);
                parent.getChildCategories().add(current);
            }
        }

        return new CategoryTree(rootCategories);
    }
}
