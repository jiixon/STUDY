## 01. PriorityQueue

- 일반적인 큐의 구조 FIFO(First In First Out)를 가짐
- 대신, 들어온 순서대로 나가는 것이 아닌 우선순위를 먼저 결정하고 높은 데이터가 먼저 나가는 자료구조
- 우선순위큐에 저장할 객체는 Comparable Interface를 구현
    - 이때 comparaTo method를 오바라이드하여 구현
    - 이 부분에서 우선순위 조건을 리턴해주면, 해당 조건으로 우선순위가 적용되도록 객체를 추출

## 02. PriorityQueue 선언 및 메소드

```java
// 우선순위가 낮은 숫자가 먼저 나옴 : 작은숫자 (오름차순)
PriorityQueue<Integer> queue = new PriorityQueue<>();

// 우선순위가 높은 숫자가 먼저 나옴 : 큰 숫자 (내림차순)
PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
```

- `add()` : 큐에 원소 추가, 큐가 꽉 차면 에러발생
- `offer()`: 큐에 원소 추가. 실패시 false 반환
- `poll()`: 큐에 맨 첫번째 값 반환 후 제거, 비어있으면 null 반환
- `remove()`: 큐에 첫번째 값 반환 후 제거, 비어있으면 에러발생
  - `remove(삭제할 value)`: 큐에 해당 value가 존재하면 해당 값 삭제 후 true, 존재하지 않으면 false 반환
- `peek()` : 첫번째 값 반환만 하고 제거는 하지 않음, 비어있으면 null 반환
- `element()` : 첫번째 값 반환만 하고 제거는 하지 않음, 비어있으면 에러 발생
- `isEmpty()`: 큐에 첫번째 값 반환 후 제거, 비어있으면 에러발생 *<- 보통 while문과 함께 사용*
- `clear()`: 큐 초기화
- `size()`: 큐에 포함된 원소 갯수 반환

## 03. 예제

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(3);// pq에 원소 3 추가
pq.offer(4);// pq에 원소 4 추가
pq.offer(1);// pq에 원소 1 추가

// pq가 비어있면: true, 그렇지 않으면 : false
while(!pq.isEmpty()) {
// pq 첫 번째 값을 반환하고 제거, 비어있다면 null 반환
    System.out.println("pq.poll() = " + pq.poll());
}
```

아래와 같이 1,3,4 순으로 나오는걸 확인할 수 있음!!

```java
//출력
pq.poll() = 1
pq.poll() = 3
pq.poll() = 4
```

## 04. 응용

기본값이 작은 숫자 이고, 내가 원한 객체를 원하는 대로 우선순위를 바꾸고 싶다면, Comparable 인터페이스를 구현해야하는데,

```java
class Node implements Comparable<Node>{
	int idx; // 노드
	int cost; // 비용

    Node(int idx, int cost){
        this.idx = idx;
        this.cost = cost;
    }

    //비용으로 우선순위 정하기
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost); //오름차순
    }

    //출력을 위한 오버라이드
    @Override
    public String toString() {
        return "Node{" + "idx=" + idx + ", cost=" + cost + '}';
    }

}

public static void main(String[] args) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
}
```

위처럼 초기화를 해주고,

```java
public class Example {
    public static void main(String[] args) {
        PriorityQueue<Student> pQ = new PriorityQueue<>();

        pq.offer(new Node(1, 10));
        pq.offer(new Node(2, 5));
        pq.offer(new Node(3, 20));

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
      }
   }
}
```

+)

```java
//혹은 바로 이렇게 적용시켜도 된다
PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
```

<출력>

```java
Node{idx=2, cost=5}
Node{idx=1, cost=10}
Node{idx=3, cost=20}
```

- 숫자형 비교
    - `Integer.compare(a,b)`
    - `a.compareTo(b)`
    - a>b : 1, a=b : 0, a<b: -1
- 문자열 비교
    - 기준형과 비교대상이 아예 같으면 : 0
    - 다르면-> 글자수 만큼 반환

### Comparable<T> vs Comparator<T>

1. `Comparable<T>`
    1. 자기 자신 기준으로 정렬
    2. `compareTo(T o)` 메서드 오버라이드
    3. 예시

        ```java
        class Node implements Comparable<Node> {
            int idx;
            int cost;
        
        		@Override
            public int compareTo(Node other) {
                return Integer.compare(this.cost, other.cost); // 오름차순
            }
        
        ```

       사용처

        ```java
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // Node의 compareTo 기준에 따라 우선순위 결정됨
        ```

    4. 자기사진(this)와 다른 객체(o)를 비교
    5. 정렬 기준이 클래스 내부에 고정됨
    6. `Collections.sort()` 또는 `PriorityQueue`에서 자동 사용
2. `Comparator<T>`
    1. 외부 기준으로 정렬
    2. `compare(T o1, T o2)` 메서드 오버라이드
    3. 예시

        ```java
        class StudentComparator implements Comparator<Student> {
        
        		@Override
            public int compare(Student s1, Student s2) {
                if (s1.mathScore == s2.mathScore)
                    return s2.engScore - s1.engScore; // 수학 동점이면 영어 내림차순
                return s1.mathScore - s2.mathScore;  // 수학 오름차순
            }
        }
        ```

       사용처

        ```java
        PriorityQueue<Student> pq = new PriorityQueue<>(new StudentComparator());
        ```

    4. 자기 객체에 없는 클래스에도 비교 기준 부여 가능
    5. 다양한 정렬 기준을 만들 수 있음 ⇒ 여러 기준으로 정렬할 때 유리