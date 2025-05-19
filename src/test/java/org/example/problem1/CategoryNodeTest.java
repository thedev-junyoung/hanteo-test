package org.example.problem1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryNodeTest {

    @Test
    @DisplayName("CategoryNode 생성 및 자식 노드 추가 확인")
    void testNodeStructure() {
        CategoryNode parent = new CategoryNode(1, "부모");
        CategoryNode child = new CategoryNode(2, "자식");

        parent.getChildCategories().add(child);

        assertEquals("부모", parent.getCategoryName());
        assertEquals(1, parent.getChildCategories().size());
        assertEquals("자식", parent.getChildCategories().get(0).getCategoryName());
    }
}
