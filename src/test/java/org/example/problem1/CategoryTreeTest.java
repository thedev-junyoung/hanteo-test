package org.example.problem1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTreeTest {

    @Test
    @DisplayName("toJson()이 올바른 JSON 계층 구조를 생성하는지 확인")
    void testToJsonProducesValidStructure() throws Exception {
        List<CategoryRelation> relations = List.of(
                new CategoryRelation(null, 1, "남자"),
                new CategoryRelation(1, 2, "엑소"),
                new CategoryRelation(2, 3, "공지사항")
        );

        CategoryTree tree = new CategoryTreeBuilder().buildTree(relations);
        String json = tree.toJson();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(json);

        // 첫 번째 루트 노드
        JsonNode root = rootArray.get(0);

        System.out.println(root.toPrettyString());

        assertEquals(1, root.get("categoryId").asInt());
        assertEquals("남자", root.get("categoryName").asText());

        System.out.println(root.toPrettyString());
        // 자식 노드: 엑소
        JsonNode exo = root.get("childCategories").get(0);
        assertEquals("엑소", exo.get("categoryName").asText());

        // 자식의 자식: 공지사항
        JsonNode notice = exo.get("childCategories").get(0);
        assertEquals("공지사항", notice.get("categoryName").asText());
    }


    @Test
    @DisplayName("findById()로 검색 시 하위 노드가 포함된 트리를 반환하는지 확인")
    void testFindSubtreeById() {
        List<CategoryRelation> relations = List.of(
                new CategoryRelation(null, 1, "남자"),
                new CategoryRelation(1, 2, "엑소"),
                new CategoryRelation(2, 3, "공지사항")
        );

        CategoryTree tree = new CategoryTreeBuilder().buildTree(relations);
        CategoryNode node = tree.findById(2).orElseThrow();

        assertEquals("엑소", node.getCategoryName());
        assertThat(node.getChildCategories()).anyMatch(child -> child.getCategoryName().equals("공지사항"));
    }
}
