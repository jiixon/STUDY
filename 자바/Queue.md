## 0. Stack과 Queue 구조

![스택큐](/자바/images/img2.png)

좌) stack 우) queue

### Queue(큐)

- 대기줄
- 선입선출 구조 FIFO (First In First Out)
- 먼저 넣은 객체가 먼저 빠져나가는 자료 구조
- 너비 우선 탐색(BFS) 와 스레드풀(ExceutorService)의 작업 큐에 활용

## 1. Queue 구현

1)선언

Queue 인터페이스 구현체인 LinkedList 사용

```java
Queue<Integer> q = new LinkedList<>();
LinkedList<Integer> q = new LinkedList<>();
```

2) 값 추가 및 삭제

- 값 추가
    - `add()`
        - 삽입 성공 시 true 반환, 실패 시(저장 공간 부족시) Exception 발생
    - `offer()`
        - 삽입 성공 시 true 반환, 실패 시 false 반환
- 값 삭제
    - `remove()`
        - 반환 값 : <삭제된 value의 자료형> 삭제된 value
        - 공백 큐일 경우 NoSuchElementException 발생
    - `remove(삭제할 value)`
        - 반환값 : Boolean
        - 큐에 해당 value가 존재하면 해당 값 삭제 후 true, 존재하지 않으면 false 반환
    - `poll()`
        - 반환 값 : <삭제된 value의 자료형> 삭제된 value
        - 공백 큐일 경우 null 반환
- 모든 값 제거
    - `clear()` : void

```java
q.offer(5);
q.offer(2);
q.offer(3);
q.offer(7);
q.poll();
q.offer(1);
q.offer(4);
q.poll();
```

- 삽입(5) - 삽입(2) - 삽입(3) - 삽입(7) - 삭제() - 삽입(1) - 삽입(4) - 삭제()

3)출력

먼저 들어온 순서대로 출력하기

`isEmpty()`로 비어있는지 확인 후 `poll()`하면서 출력

```java
while (!q.isEmpty()){
            System.out.println(q.poll()); //3 7 1 4 (/n생략)
        }
```

## 2. Queue 메서드

- **`Object element()`**
    - 삭제없이 요소 읽기 + 리스트에서 요소를 제거하지 않고 가져오는 메서드
    - Queue 비어있으면 NoSuchElementException 발생
- **`Object peek()`**
    - 삭제없이 요소 읽기 + 리스트에서 요소를 제거하지 않고 가져오는 메서드
    - Queue *비어있으면 null 반환 : element()와 다른점*
- **`Object peekLast(), peekFirst()`**
    - 삭제없이 요소 읽기 + 리스트에서 요소를 제거하지 않고 가져오는 메서드
    - Dequeue 메서드이지만 구현체인 LinkedList에서 같이 사용가능
    - peekLast(): 리스트의 마지막 요소 가져옴
    - peekFirst(): 리스트의 첫번째 요소 가져옴
- **`int Size()`**
    - Queue의 사이즈 반환
- **`boolean contains(찾을 value)`**
    - 큐에 해당 원소가 존재하는지 확인하는 메서드
    - 해당 값이 존재할 때 true, 해당 값이 없을 때 false
- **`boolean isEmpty()`**
    - 공백 큐인지 확인하는 메서드
    - 공백 큐이면 true, 공백 큐 아니면 false