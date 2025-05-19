package org.example.problem1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTreeTest {

    @Test
    @DisplayName("toJson()이 JSON 트리 구조로 직렬화되는지 확인")
    void toJson_shouldProduceNestedStructure() throws Exception {
        List<CategoryRelation> relations = List.of(
                new CategoryRelation(null, 1, "남자"),
                new CategoryRelation(1, 2, "엑소"),
                new CategoryRelation(2, 3, "공지사항")
        );

        CategoryTree tree = new CategoryTreeBuilder().buildTree(relations);
        String json = tree.toJson();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json).get(0);

        assertEquals(1, root.get("categoryId").asInt());
        assertEquals("남자", root.get("categoryName").asText());

        JsonNode exo = root.get("childCategories").get(0);
        assertEquals("엑소", exo.get("categoryName").asText());

        JsonNode notice = exo.get("childCategories").get(0);
        assertEquals("공지사항", notice.get("categoryName").asText());
    }
}
