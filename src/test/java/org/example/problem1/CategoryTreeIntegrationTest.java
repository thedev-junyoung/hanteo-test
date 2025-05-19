package org.example.problem1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTreeIntegrationTest {

    @Test
    @DisplayName("ID 또는 이름으로 검색 시 자식 노드를 포함한 트리를 반환해야 한다")
    void findByIdOrName_shouldReturnSubtree() {
        List<CategoryRelation> relations = List.of(
                new CategoryRelation(null, 1, "장르"),
                new CategoryRelation(1, 2, "발라드"),
                new CategoryRelation(2, 3, "공지사항")
        );

        CategoryTree tree = new CategoryTreeBuilder().buildTree(relations);

        CategoryNode byId = tree.findById(2).orElseThrow();
        CategoryNode byName = tree.findByName("발라드").orElseThrow();

        assertEquals("발라드", byId.getCategoryName());
        assertEquals("발라드", byName.getCategoryName());
        assertEquals("공지사항", byId.getChildCategories().get(0).getCategoryName());
    }

    @Test
    @DisplayName("같은 이름의 공지사항도 ID가 다르면 다른 노드여야 한다")
    void sameNameDifferentId_shouldBeDistinct() {
        List<CategoryRelation> relations = List.of(
                new CategoryRelation(null, 1, "남자"),
                new CategoryRelation(1, 2, "엑소"),
                new CategoryRelation(2, 3, "공지사항"),
                new CategoryRelation(1, 4, "방탄소년단"),
                new CategoryRelation(4, 5, "공지사항")
        );

        CategoryTree tree = new CategoryTreeBuilder().buildTree(relations);

        CategoryNode notice1 = tree.findById(3).orElseThrow();
        CategoryNode notice2 = tree.findById(5).orElseThrow();

        assertNotSame(notice1, notice2);
        assertEquals("공지사항", notice1.getCategoryName());
        assertEquals("공지사항", notice2.getCategoryName());
    }

    @Test
    @DisplayName("ID가 동일한 익명게시판은 여러 부모 아래 공유되어야 한다")
    void sameId_shouldBeSharedAcrossParents() {
        List<CategoryRelation> relations = List.of(
                new CategoryRelation(null, 1, "남자"),
                new CategoryRelation(1, 2, "엑소"),
                new CategoryRelation(2, 3, "익명게시판"),
                new CategoryRelation(1, 4, "방탄소년단"),
                new CategoryRelation(4, 3, "익명게시판")
        );

        CategoryTree tree = new CategoryTreeBuilder().buildTree(relations);

        CategoryNode exo = tree.findByName("엑소").orElseThrow();
        CategoryNode bts = tree.findByName("방탄소년단").orElseThrow();

        CategoryNode anonFromExo = exo.getChildCategories().stream().filter(c -> c.getCategoryId() == 3).findFirst().orElseThrow();
        CategoryNode anonFromBts = bts.getChildCategories().stream().filter(c -> c.getCategoryId() == 3).findFirst().orElseThrow();

        assertSame(anonFromExo, anonFromBts);
    }
}
