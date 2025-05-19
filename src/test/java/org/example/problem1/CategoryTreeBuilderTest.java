package org.example.problem1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTreeBuilderTest {
    @Test
    @DisplayName("CategoryRelation 리스트를 기반으로 트리를 정확히 구성하는지 확인")
    void testCategoryTreeBuilder() {
        List<CategoryRelation> relations = List.of(
                new CategoryRelation(null, 1, "루트"),
                new CategoryRelation(1, 2, "자식")
        );

        CategoryTree tree = new CategoryTreeBuilder().buildTree(relations);
        CategoryNode root = tree.findById(1).orElseThrow();

        assertEquals("루트", root.getCategoryName());
        assertEquals(1, root.getChildCategories().size());
        assertEquals("자식", root.getChildCategories().get(0).getCategoryName());
    }
}