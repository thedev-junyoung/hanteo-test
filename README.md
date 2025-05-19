두 문제 모두 요구사항에 맞게 구현하였으며, 테스트 코드와 `main()` 실행을 통해 결과를 검증할 수 있습니다.

---

## ✅ 문제 1. 게시판 카테고리 트리 구조

### 📌 문제 요약

- 게시판은 여러 카테고리로 구분되며, 계층형 구조로 표현됨
- 카테고리 관계는 `(parent_id, child_id)`만 제공됨
- 이름은 같더라도 ID가 다르면 다른 게시판으로 간주해야 함
- 반대로, ID가 같으면 이름이 같든 다르든 같은 게시판으로 간주해야 함
- 트리를 구성한 뒤 특정 카테고리 `ID` 또는 `이름`으로 조회하면, **해당 노드 및 하위 노드를 포함하는 트리 구조가 JSON 형태로 반환**되어야 함

---

### 🧠 설계 및 구현 구조

| 클래스 | 설명 |
| --- | --- |
| `CategoryRelation` | 입력 관계 모델 (parentId, categoryId, categoryName) |
| `CategoryNode` | 트리 노드 객체. 자식 노드 리스트 포함 |
| `CategoryTreeBuilder` | CategoryRelation 리스트를 기반으로 트리 구성 |
| `CategoryTree` | 트리 검색 및 직렬화 기능 제공 |
- `Map<Integer, CategoryNode>`를 사용하여 동일 ID는 같은 객체로 재사용됨
- `findById`, `findByName`로 서브트리 조회 가능
- `toJson()`을 통해 전체 또는 부분 트리를 JSON 문자열로 직렬화

---

### 🧪 실행 예시 (Main 클래스)

```bash
./gradlew runProblem1
```

```
> Task :runProblem1
=== [카테고리 트리 구조 출력 예시] ===
[입력 관계]
parentId: null, categoryId: 1, categoryName: 남자
parentId: 1, categoryId: 2, categoryName: 엑소
parentId: 2, categoryId: 3, categoryName: 공지사항

[JSON 출력 결과]
[ {
  "categoryId" : 1,
  "categoryName" : "남자",
  "childCategories" : [ {
    "categoryId" : 2,
    "categoryName" : "엑소",
    "childCategories" : [ {
      "categoryId" : 3,
      "categoryName" : "공지사항",
      "childCategories" : [ ]
    } ]
  } ]
} ]

```

---

## ✅ 문제 2. 동전 조합으로 합 구하기

### 📌 문제 요약

- 동전 종류와 합계가 주어졌을 때, **중복 사용 가능한 조합의 개수**를 계산
- 동전 순서는 고려하지 않음 (조합 기준)
- Top-Down DFS + Memoization으로 구현

---

### 🧠 주요 메서드

```java
public static int countWays(int[] coins, int target);
```

- 입력: `coins = [1, 2, 3]`, `sum = 4`
- 출력: `4` → `{1+1+1+1}, {1+1+2}, {2+2}, {1+3}`

---

### 🧪 실행 예시 (Main 클래스)

```bash
./gradlew runProblem2
```

```
> Task :runProblem2
=== [동전 조합 문제 실행 결과] ===
입력: coins = [1, 2, 3], sum = 4 → 가능한 조합 수 = 4
입력: coins = [2, 5, 3, 6], sum = 10 → 가능한 조합 수 = 5
```

---

## ✅ 테스트 실행

```bash
./gradlew test
```

### 🧪 테스트 커버리지

- `CategoryTreeBuilderTest` → 트리 구조 생성 검증
- `CategoryTreeTest` → 직렬화(JSON 출력) 검증
- `CategoryTreeIntegrationTest` → 공지사항 분리, 익명게시판 공유 등 요구사항 시나리오 검증
- `CoinChangeTest` → 조합 수 계산 검증 (기본/엣지케이스 포함)

---

## 📁 프로젝트 구조

```
src/
├── main/
│   └── java/
│       ├── org.example.problem1/  ← 문제 1 (카테고리 트리)
│       └── org.example.problem2/  ← 문제 2 (동전 조합)
└── test/
    └── java/
        ├── org.example.problem1/
        └── org.example.problem2/

```

---

## ⚙️ 기술 스택

| 항목 | 사용 기술 |
| --- | --- |
| 언어 | Java 17 |
| 빌드 도구 | Gradle |
| 테스트 | JUnit 5, AssertJ |
| JSON 직렬화 | Jackson |

---

## ✅ 제출자 정보

- 이름: 전준영
- 이메일: thedev.junyoung@gmail.com
- 연락처: 010-3470-5735

감사합니다. 