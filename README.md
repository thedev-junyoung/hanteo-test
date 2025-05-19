
본 레포지토리는 한터글로벌 백엔드 코딩테스트 문제에 대한 해결 결과입니다. 두 문제 모두 구현 및 테스트를 완료하였습니다.

---

## ✅ 문제 1 - 트리 기반 게시판 카테고리 구조 구현

### ✅ 핵심 구현
- `CategoryRelation`: parentId, id, name을 포함하는 관계 모델
- `CategoryNode`: 계층형 트리 노드
- `CategoryTreeBuilder`: 관계 기반 트리 구성 로직
- `CategoryTree`: 검색 및 JSON 직렬화 기능 담당

### 🔎 검증된 기능
- `findById(int id)` / `findByName(String name)`로 하위 트리 포함 검색
- `toJson()` 결과를 `ObjectMapper.readTree()`로 파싱 후 JSON 계층 검증

### 🧪 테스트 구성
- `CategoryTreeTest`: toJson 구조 검증 (`JsonNode` 기반 검증)
- `CategoryTreeIntegrationTest`: 익명게시판/공지사항 ID, 이름별 분기 테스트
- `CategoryNodeTest`, `CategoryTreeBuilderTest`: 단위 테스트 별도 존재

---

## ✅ 문제 2 - 동전 조합으로 합 구하기 (Coin Change)

---

### ✅ 구현

- `CoinChange.countWays(int[] coins, int target)`
- **Top-Down 재귀 기반 조합 탐색** 방식
- 중복 허용 & 순서 무시 → **index 기반 DFS**
- 내부적으로 `search(int[] coins, int remain, int index)` 호출하여 경우의 수 계산

---

###
---

## 🧪 실행 방법

### Gradle 기준
```bash
./gradlew test
```

또는 IDE에서 각 `*Test.java` 우클릭 → Run Test

---

## 📂 디렉토리 구조
```
src
├── main
│   └── java
│       └── org.example.problem1  # 트리 구조 문제
│       └── org.example.problem2  # 코인 조합 문제
├── test
    └── java  # 단위 & 통합 테스트
        └── org.example.problem1  
        └── org.example.problem2  
```

---

