## 0.Deque 구조

![Deque](/자바/images/img3.png)

- Double Ended Queue - 양방향 대기열
- LIFO, FIFO 와 같은 순서에 구속되지 않음
- Queue 인터페이스를 상속받은 인터페이스

특징

1. Stack 및 Queue를 모두 사용할 수 있다.
2. 양방향 끝에서 데이터 추가, 삭제가 용이하다.
    1. 양쪽의 추가 삭제할 데이터의 인덱스 정보를 가지고 있음
3. 양방향 끝이 아닌 데이터만 추가하거나 삭제하는 건 불가능하다.
    1. Deque는 양방향 끝의 인덱스 정보를 가지고 있음
    2. 양방향의 데이터가 아닌 중간에 있는 데이터에 접근 못함, 인덱스 정보가 없어서

## 1.Deque 구현

1) 선언

- LinkedList 와 ArrayDeque를 사용한다.$

```java
Deque<Integer> deque = new LinkedList<>();
Deque<Integer> deque = new ArrayDeque<>();
```

2) 값 추가

![Deque](/자바/images/img4.png)

- `add()` : 마지막에 원소 삽입
- `addFirst()` : 맨 앞에 원소 삽입
- `addLast()` : 마지막에 원소 삽입
- `offer()` : 마지막에 원소 삽입
- `offerFirst()` : 맨 앞에 원소 삽입
- `offerLast()` : 마지막에 원소 삽입

이때, add → 용량 초과시 Exception<br>
offer → 용양 초과시 false

```java
deque.offerFirst(1);//[1]
deque.offerFirst(2);//[2,1]
deque.offerFirst(3);//[3,2,1]
deque.offerFirst(4);//[4,3,2,1]
deque.offerLast(5);//[4,3,2,1,5]
deque.offerLast(6);//[4,3,2,1,5,6]
```

3)값 제거

![Deque](/자바/images/img5.png)

- `remove()` : 맨 앞의 원소 제거 후 해당 원소 리턴
- `removeFirst()` : 맨 앞 원소 제거 후 해당 원소 리턴
- `removeLast()` : 마지막 원소 제거 후 해당 원소 리턴
- `poll()` : 맨 앞 원소 제거 후 해당 원소 리턴
- `pollFirst()` : 맨 앞 원소 제거 후 해당 원소 리턴
- `pollLast()` : 마지막 원소 제거 후 해당 원소 리턴

이때 , remove → 비어있는 경우 Exception 발생<br>
poll → 비어있는 경우 null 리턴

```java
//[4,3,2,1,5,6]
deque.pollFirst();// 4 반환, [3,2,1,5,6]
deque.pollLast();// 6 반환, [3,2,1,5]
deque.pollFirst();// 3 반환, [2,1,5]
deque.pollLast();// 5 반환, [2,1]
```

4)값 확인

![Deque](/자바/images/img6.png)

- `getFirst()` : 맨 앞의 원소 리턴
- `getLast()` :  마지막 원소 리턴
- `peek()` : 맨 앞의 원소 리턴
- `peekFirst()` : 맨 앞의 원소 리턴
- `peekLast()` : 마지막 원소 리턴

이때, get → 비어있는 경우 Exception 발생<br>
peek → 비어있는 경우 null 리턴

```java
//[4,3,2,1,5,6]
deque.peekFirst(); //4 반환 , [4,3,2,1,5,6](그대로)
deque.peekLast(); //6 반환 , [4,3,2,1,5,6](그대로)
deque.peekFirst(); //4 반환 , [4,3,2,1,5,6](그대로)
deque.peekLast(); //6 반환 , [4,3,2,1,5,6](그대로)
```

5)그 외 메서드

- `contain(Object o)`
    - Object 인자와 동일한 엘리먼트가 포함되어 있는지 확인
- `size()`
    - Deque에 들어있는 엘리먼트의 개수

6) 순회

1. for문 이용
```java
for (String elem : deque1) {
  System.out.println(elem);
}
```
2. Iterator 이용

```java
Iterator<String> iterator = deque1.iterator();
while (iterator.hasNext()) {
	String elem = iterator.next();
	System.out.println(elem);
}
```