## ArrayList

특징

- 연속적인 데이터의 리스트
- ArrayList 클래스는 내부적으로 Object[] 배열을 이용하여 요소 저장
    - 인덱스 이용하기에 요소에 빠르게 접근 가능
- 배열과 다른점 ⇒ 가변적으로 공간을 늘리거나 줄이기 가능
- 배열 공간이 꽉 찰때마다 **배열을 copy 하는 방식으로 늘림**
    - 지연 발생
    - 즉, 리스트 중간에 삽입/삭제 할 경우, 중간에 빈공간 생기지 않도록 요소들을 자동 이동시키에, 삽입/삭제는 다소 느림
    - 조회를 이용하는 경우에 좋다!

## ArrayList 사용법

- ArrayList 객체 생성

    ```java
    // 타입설정 Integer 객체만 적재가능
    ArrayList<Integer> members = new ArrayList<>();
    
    // 초기 용량지정
    ArrayList<Integer> num3 = new ArrayList<>(10);
    
    // 배열을 넣어 생성
    ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1,2,3));
    
    // 다른 컬렉션으로부터 그대로 요소를 받아와 생성
    //ArrayList를 인자로 받는 API를 사용하기 위해서 Collection 타입 변환이 필요할 때 많이 사용
    ArrayList<Integer> list3 = new ArrayList<>(list2);
    ```

- ArrayList 요소 추가
    - `boolean add(Object obj)` : ArrayList 마지막에 객체 추가
    - `void addAll(Collection c)` : 주어진 컬렉션의 모든 객체를 저장
    - `void add(int index, Object element)` : 지정된 위치(index)에 객체 저장
        - `list.add(3, "A");`  : 3번째 인덱스 자리에 요소 삽입
        - 기존 데이터는 뒤로 밀려나고 삭제되지는 않음
        - 이때, 리스트의 논리적 공간을 넘으면 `IndexOutOfBoundsException` 이 발생하니 주의
- ArrayList 요소 삭제
    - `Object remove(int index)` : 지정된 위치(index)에 있는 객체를 제거
    - `boolean remove(Object obj)` : 지정된 객체를 제거(성공시 true)
    - `boolean removeAll(Collection c)` : 지정한 컬렉션에 젖아도니 것과 동일한 객체들을 ArrayList에서 제거
    - `void clear()` : ArrayList 완전히 제거
    - 이떄, Integer의 경우, 인덱스와 객체 구분하는 방법

        ```java
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        
        // 이건 인덱스 1의 값을 삭제 (값은 1)
        list.remove(1); // 결과: [0, 2, 3]
        
        // 값이 1인 요소를 삭제하고 싶다면?
        list.remove(Integer.valueOf(1)); // 결과: [0, 2, 3]
        ```

        - 해당 객체를 삭제하고 싶으면 `Integer.valueOf(1)` 라고 하자!
- ArrayList 요소 검색
    - `boolean isEmpty()` : ArrayList가 비어있는지 확인
    - `boolean contains(Object obj)` : 저장된 객체가 ArrayList에 포함되어있는지 확인
    - `int indexOf(Object obj)` : 지정된 객체가 저장된 위치 찾아 반환 (없으면 -1)
    - `int lastIndexOf(Object obj)` : 지정된 객체가 저장된 위치를 뒤에서부터 역방향으로 찾아서 반환 (없으면 -1)
- ArrayList 요소 얻기
    - `Object get(in index)` : 지정된 위치에 저장된 객체를 반환
    - `List subList(int fromIndex, int toIndex)` : fromIndex 부터 toIndex사이에 저장된 객체를 반환
        - `list.subList(0, 7);`  : list[0] ~ list[6] 범위 반환
- ArrayList 요소 변경
    - `Object set(int index, Object obj)`
        - 주어진 객체를 지정한 위치에 저장
        - 만약, 해당 자리에 데이터가 있으면, 기존 데이터는 삭제
        - `list1.set(1, "setData");`  : index 1번의 데이터를 문자열 “setData”로 변경
- ArrayList 배열 반환
    - `Object[] toArray()` : ArrayList에 저장된 모든 객체들을 배열로 반환
    - 방법1 : 배열로 변환하고 반환
        - `String[] arr1 = languages.toArray();`
    - 방법2 : 매개변수로 지정된 배열에 담아 반환
        - `String[] arr2 = new String[languages.size()];`
        - `languages.toArray(arr2);`
- ArrayList 정렬
    - 오름차순
        - `Collections.sort(list);`
        - `list.sort(Comparator.naturalOrder());`
    - 내림차순
        - `Collections.sort(list, Collections.reverseOrder());`
        - `list.sort(Comparator.reverseOrder());`