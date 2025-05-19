# Hanteo Global Backend Coding Test

본 프로젝트는 Hanteo Global의 백엔드 코딩테스트 문제 풀이입니다.  
Java 17을 기반으로 작성되었으며, 모든 기능은 테스트 코드로 검증됩니다.

---

## ✅ 문제 1. 게시판 카테고리 트리 구조

### 📌 요구사항 요약
- 각 카테고리는 ID, 이름, 부모 ID로 표현됨
- 하나의 카테고리는 여러 부모를 가질 수 있음 (ex. 익명 게시판)
- 같은 이름이라도 ID가 다르면 별도 노드로 취급해야 함 (ex. 공지사항)

### 📐 설계 개요

#### 자료구조

| 클래스명 | 설명 |
|----------|------|
| `CategoryRelation` | parentId, categoryId, categoryName 정보를 포함한 입력 모델 |
| `CategoryNode` | 트리의 실제 노드 객체, 자식 노드 목록 포함 |
| `CategoryTreeBuilder` | CategoryRelation 리스트를 기반으로 트리 구성 |
| `CategoryTree` | 트리 검색(findById, findByName) 및 JSON 직렬화 기능 제공 |

- `Map<Integer, CategoryNode>`를 통해 categoryId 기준으로 중복 방지
- `CategoryNode`는 트리형 구조를 재귀적으로 구성하며 `childCategories`를 포함

#### 트리 생성 알고리즘

1. `categoryId` 기준으로 모든 노드를 생성
2. `parentId == null`인 노드는 루트 노드로 간주
3. 그 외의 경우, 해당 부모의 자식 노드에 추가

### 🧪 검증 포인트

- `findById`, `findByName`: 자식 노드까지 포함하는 서브트리 반환
- 동일한 `categoryId`를 가진 익명게시판은 객체 재사용됨 (`assertSame`)
- 동일 이름이지만 다른 ID를 가진 공지사항은 별개 노드로 구성됨

---

## ✅ 문제 2. 동전 조합으로 합 구하기

### 📐 설계 개요
- 입력된 동전 종류로 합계 `target`을 만들 수 있는 모든 조합 수를 구함
- 중복 허용, 순서 무시 → Top-Down DFS + Memoization 적용

#### 구현 메소드
```java
int CoinChange.countWays(int[] coins, int target);
