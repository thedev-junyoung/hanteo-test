package org.example.problem1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 통합 시나리오 테스트
 * - 공지사항은 동일한 이름이지만 다른 게시판 (id 다름)
 * - 익명 게시판은 같은 게시판이지만 여러 카테고리에 소속됨 (id 동일)
 */
public class CategoryTreeIntegrationTest {

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

    @Test
    @DisplayName("공지사항은 이름은 같지만 ID가 다르므로 별도 노드로 존재해야 함")
    void noticeBoard_shouldBeDistinctPerCategory() {
        List<CategoryRelation> relations = List.of(
                new CategoryRelation(null, 1, "남자"),
                new CategoryRelation(1, 2, "엑소"),
                new CategoryRelation(2, 3, "공지사항"),
                new CategoryRelation(1, 4, "방탄소년단"),
                new CategoryRelation(4, 5, "공지사항")
        );

        CategoryTree tree = new CategoryTreeBuilder().buildTree(relations);

        var notice1 = tree.findById(3);
        var notice2 = tree.findById(5);

        assertTrue(notice1.isPresent());
        assertTrue(notice2.isPresent());
        assertNotEquals(notice1.get().getCategoryId(), notice2.get().getCategoryId());
        assertEquals("공지사항", notice1.get().getCategoryName());
        assertEquals("공지사항", notice2.get().getCategoryName());
    }

    @Test
    @DisplayName("익명게시판은 ID가 같으므로 같은 노드가 여러 부모 아래 소속되어야 함")
    void anonymousBoard_shouldBeSharedByMultipleParents() {
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

        long countInExo = exo.getChildCategories().stream().filter(c -> c.getCategoryId() == 3).count();
        long countInBts = bts.getChildCategories().stream().filter(c -> c.getCategoryId() == 3).count();

        assertEquals(1, countInExo);
        assertEquals(1, countInBts);

        assertSame(
                exo.getChildCategories().stream().filter(c -> c.getCategoryId() == 3).findFirst().get(),
                bts.getChildCategories().stream().filter(c -> c.getCategoryId() == 3).findFirst().get()
        );
    }

    @Test
    @DisplayName("카테고리 이름 또는 ID로 검색 시 하위 포함 트리를 반환해야 한다")
    void findByNameAndId_shouldIncludeChildren() {
        List<CategoryRelation> relations = List.of(
                new CategoryRelation(null, 100, "장르"),
                new CategoryRelation(100, 200, "발라드"),
                new CategoryRelation(200, 300, "공지사항")
        );

        CategoryTree tree = new CategoryTreeBuilder().buildTree(relations);

        CategoryNode byId = tree.findById(200).orElseThrow();
        CategoryNode byName = tree.findByName("발라드").orElseThrow();

        assertEquals("발라드", byId.getCategoryName());
        assertEquals("발라드", byName.getCategoryName());
        assertEquals(1, byId.getChildCategories().size());
        assertEquals(1, byName.getChildCategories().size());
        assertEquals("공지사항", byId.getChildCategories().get(0).getCategoryName());
        assertEquals("공지사항", byName.getChildCategories().get(0).getCategoryName());
    }
}
