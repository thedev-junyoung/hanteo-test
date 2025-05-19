package org.example.problem1;

import java.util.List;

public class CategoryTreeMain {

    public static void main(String[] args) throws Exception {
        System.out.println("=== [카테고리 트리 구조 출력 예시] ===");

        List<CategoryRelation> relations = List.of(
                new CategoryRelation(null, 1, "남자"),
                new CategoryRelation(1, 2, "엑소"),
                new CategoryRelation(2, 3, "공지사항")
        );

        System.out.println("[입력 관계]");
        for (CategoryRelation r : relations) {
            System.out.printf("parentId: %s, categoryId: %d, categoryName: %s%n",
                    r.getParentCategoryId(), r.getCategoryId(), r.getCategoryName());
        }

        CategoryTree tree = new CategoryTreeBuilder().buildTree(relations);
        String json = tree.toJson();

        System.out.println("\n[JSON 출력 결과]");
        System.out.println(json);
    }
}
