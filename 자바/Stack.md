## 0. Stack과 Queue 구조

![스택큐](/자바/images/img2.png)
좌) stack 우) queue

### Stack(스택)

- 박스쌓기
- **선입후출(FILO)** 구조 또는 후입선출(LIFO)구조
- FILO == First In Last Out
- 먼저 들어온 데이터가 나중에 빠져나가는 구조
- 데이터를 하나씩만 넣고 뺄 수 있음
- 깊이우선탐색(DFS) 에 이용
- 재귀 함수의 동작 흐름과 같은 구조를 가짐

## 1.Stack 구현

1) 선언

- 데이터 타입은 클래스 또는 래퍼 클래스로 선언

```java
Stack<Integer> s = new Stack<>();
```

2) 값 추가 및 제거

- 값 추가
    - add()
    - push()
- 값 제거
    - pop()
    - 스택의 값이 제거됨과 동시에 해당 값을 반환
- 모든 값 제거
    - clear()
    - 반환 값 없음

```java
s.push(5);
s.push(2);
s.push(3);
s.push(7);
s.pop();
s.push(1);
s.push(4);
s.pop();
```

- 삽입(5) - 삽입(2) - 삽입(3) - 삽입(7) - 삭제() - 삽입(1) - 삽입(4) - 삭제()

3)출력

스택의 최상단 원소부터 출력해보자!

- peek()로 꺼내진 않고 반환하기 -> 이후 pop()하기

*이때 stack을 비우지 않는다면 while에 걸려 무한루프가 돌아용*

```java
while (!s.empty()){
    System.out.println(s.peek());
    s.pop();
}
```

- 아예 pop()하면서 출력시키기

```java
while (!s.empty()){
    System.out.println(s.pop()); //1 3 2 5 (/n 생략)
}
```

## 2. Stack 메서드

- **`boolean isEmpty() / empty()`**
    - Stack이 비어있으면 True,
    - Stack이 비어있지않으면 False
- **`Object peek()`**
    - Stack의 맨 위 저장된 객체 반환
    - Stack에 변화를 주지 않음
    - *pop() 과 다른점: 객체를 꺼내지 않음 => 즉, 그냥 반환만 함! 나오지않음*
    - 비어있을 경우 : **EmptyStackException** 발생
- **`Object pop()`**
    - Stack의 맨위 저장된 객체 꺼냄
    - 비어있을 경우 : **EmptyStackException** 발생 // isEmpty() == true 일때 예외처리하기
- **`Object push(Object item)`**
    - Stack에 item(객체)를 저장함
- **`int search(Object o)`**
    - Stack에서 주어진 객체를 찾아서 그위치 반환
    - 만약 해당 인자가 여러 개일 경우, 마지막 위치를 반환
    - 못찾을 시 -1 반환
    - *위치 1부터 시작(인덱스처럼 0 아님 주의)*
- **`int size()`**
    - Stack의 사이즈 반환