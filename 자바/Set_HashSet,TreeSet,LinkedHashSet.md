![Set](/자바/images/img7.png)
## 01.Set

- 비선형 자료구조
- Set 인터페이스를 구현한 주요 클래스
    - HashSet
        - 순서가 전혀 필요 없는 데이터를 해시 테이블에 저장
        - Set 중 성능이 가장 좋음
    - TreeSet
        - 저장된 데이터의 값에 따라서 정렬되는 셋
        - red-black 트리 타입으로 값이 저장
        - HashSet 보다 성능 약간 느림
    - LinkedHashSet
        - 연결된 목록 타입으로 구현된 해시 테이블에 데이터를 저장
        - 저장된 순서에 따라 값이 정렬
        - 성능이 가장 안좋음
- Set/Map을 구현한 종류로는 Tree/Hash 가 있음
    - Hash가 Tree보다 빨라서 자주 사용

![Set](/자바/images/img8.png)

## 02. Set 컬렉션

1. 선언

```java
//HashSet
Set<String> set = new HashSet<>();

//TreeSet
Set<String> set = new TreeSet<>();

//LinkedHashSet 
Set<String> set = new LinkedHashSet<>();
```

1. 메서드
- `boolean add(Object o)` : 데이터 추가
- `boolean remove(Object o)` : 지정된 객체를 삭제
    - 성공 true, 실패 false
- `void clear()` : 전체 삭제
- `boolean contains(Object o)` : 지정된 객체를 포함하고 있는지 확인 (대소문자 구분)
- `boolean isEmpty()` : Set이 비어있는지 확인
- `int size()` : 지정된 객체의 개수를 반환
- `Iterator iterator()` : 검색을 위하여 반복자를 생성/반환 (Set은 인덱스 관리X)
    - `hasNext()` : 다음 값을 갖고 있는지 true/false 반환
    - `next()` : 다음 값으로 이동 및 반환

1. 출력
- Iterator 생성 후 while문으로 다음 읽을 데이터 있으면 출력

```java
Iterator<String> iterator = set.iterator();

while (iterator.hasNext()) {
	System.out.println(iterator.next());
}
```

1. Set → List

```java
Set<String> set = new HashSet<>();
List<String> setToList = new ArrayList<>(set);

//오름차순 정렬
Collections.sort(setToList);

//내림차순 정렬
Collections.sort(setToList, Collections.reverseOrder());
```

- HashSet, TreeSet 모두 정렬하고 싶으면,
    - List로 변환 후 Collections.sort()로 오름차순 정렬 가능

1. Set → Array

```java
Set<String> set = new HashSet<>();
String[] setToArray = set.toArray(new String[0]);

//오름차순 정렬
Arrays.sort(setToArray);

//내림차순 정렬
Arrays.sort(setToArray, Collections.reverseOrder());
```

## 03.HashSet

- Hash란?
    - 값을 저장하고 조회하는데 있어 가장 빠른 알고리즘
    - 저장하고자 하는 값을 어떤 값과도 중복되지 않는 숫자 코드로 변환( == 해시 코드)하여 해당 코드의 메모리 위치에 값을 저장하는 방법
    - 저장하려는 값을 일련의 공식을 통해 해시코드로 변환된 값이 ⇒ 저장되는 위치
    - 값 조회시, 저장된 값만 알고 있으면 해시 코드로 변환하여 곧바로 그 주소에서 읽음
    - 저장 : O(1), 탐색 : O(1)

![Set](/자바/images/img9.png)

- HashSet
    - 저장한 순서대로 값이 저장되지 않고, 출력 또한 저장된 순서대로 출력되지 않음
    - 값의 중복 허용X
    - null도 데이터로 입력 가능

## 04. TreeSet

- TreeSet
    - 이진 검색 트리(binary search tree) 자료구조의 형태로 데이터를 저장하는 컬렉션 클래스
    - 중복된 데이터 저장을 허용X
    - 정렬된 위치에 저장 ⇒ 저장 순서를 유지 하진 않음
        - 기본으로 오름차순으로 자동 정렬 후 관리
        - 정규식 0-9, A-Z, a-z 로 정렬
    - 내림차순으로 정렬하려면 초기에 지정 가능
    
    ```java
    Set<Integer> treeSet = new TreeSet<>((a, b) -> b - a); // 내림차순
    ```
    
- 이진 검색 트리 저장 과정

  ![Set](/자바/images/img10.png)
    
- 이진 검색 트리란?
    - 모든 노드는 최대 두 개의 자식노드를 가질 수 있음
    - 왼쪽 자식노드의 값은 부모 노드의 값보다 작고 오른쪽 자식 노드의 값은 부모노드의 값보다 커야 함
    - 노드의 추가 삭제에 시간이 걸림 (순차적으로 저장하지 않음)
    - 검색과 정렬에 유리
    - 중복된 값은 저장하지 못함

## 05. LinkedHashSet

- HashSetd과 달리 저장되는 순서를 유지하고 관리할 수 있음
- 내부적으로 LinkedHashMap을 사용