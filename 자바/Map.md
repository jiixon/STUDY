## 01.Map

Map

- 리스트나 배열처럼 순차적으로 요소의 값을 구하는 것이 아닌 key를 통해 value 를 얻음
- Collection 인터페이스와 다른 저장 방식
    - 키와 값을 하나의 쌍으로 저장하는 key-value 방식 사용
- 특징
    - 요소의 저장 순서를 유지 하지 않음
    - key : 중복 허용 X
    - value : 중복 허용 O

Map 구현체

1. HashMap<K, V> 클래스
    - 가장 많이 사용되는 클래스
    - key 와 value를 묶어 하나의 entry로 저장
    - 해시 알고리즘을 사용하여 검색속도 매우 빠름
    - 중복된 키로 값 저장 불가
    - value에 null 사용 가능
    - 멀티쓰레드에서는 HashTable을 사용
        - 같은 값을 다른 키로 저장하는 것은 가능
    -

**1. put(K key, V value)**

키와 값을 맵에 저장 한다.<br>
키가 존재하면 새값으로 대체된다.

```java
Map<String, Integer> map = new HashMap<>();
map.put("apple", 50);
map.put("banana", 30);
```

**2.get(Object key)**

지정된 키에 대응하는 값을 반환하다.<br>
키가 없으면 null을 반환

```java
int price = map.get("apple");// 50
```

**3.remove(Object key)**

키와 그에 대응하는 값을 제거

```java
map.remove("banana");
```

**4.cotainsKey(Object key)**

Map에  지정된 키가 존재하는지 여부를 반환

```java
boolean hasApple = map.containsKey("apple");// true
```

**5.containsValue(Object value)**

Map에 해당 값이 존재하는 여부를 파악

```java
boolean hasPrice50 = map.containsValue(50);// true
```

**6. keySet()**

Map에 모든 키를 담은 Set 객체를 반환한다.

```java
// HashMap 준비
Map<Integer, String> map = new HashMap<Integer, String>();
map.put(1, "Apple");
map.put(2, "Banana");
map.put(3, "Orange");

// for loop (keySet())
Set<Integer> keySet = map.keySet();
for (Integer key : keySet) {
		System.out.println(key + " : " + map.get(key));
}

// 결과1 : Apple
2 : Banana
3 : Orange
```

**7.values()**

Map에 모든 값을 담은 Collection을  반환한다.

```java
  Map<Integer, String> map = new HashMap<Integer, String>();
  map.put(1, "Apple");
  map.put(2, "Banana");
  map.put(3, "Orange");

  Collection<String> values = map.values();
  System.out.println(values);// [Apple, Banana, Orange]
```

**8. entrySet()**

Map의 모든 키- 값을 꺼내야 할 때

```java
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
// 출력: apple: 50
```

**9.size()**

Map에 저장된 키-값 쌍의 개수를 반환

```java
int size = map.size();// 1
```

**10.clear()**

Map에 저장된 모든것을 지울떄

```java
map.clear();
```

**11.getOrDefault(Object key, V defaultValue)**

키에 대응 하는 값을 반환하고 키가 존재하지 않는다면 defaultValue 값을 반환

```java
int price = map.getOrDefault("orange", 0);// 0
```

**12.putIfAbsent(K key, V value)**

키에 대응하는 값이 없을때만 키-값을 맵에 저장한다.

```java
map.putIfAbsent("apple", 60);
```

**13.replaceAll()(BiFunction<? super K,? super V,? extends [V](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)> function)**

Map의 모든 키-값 쌍에 대해 지정된 함수를 적용하여 값을 대체

```java
map.replaceAll((key, value) -> value + 10);
```

**14.replace(k key, V value)**

Map 에 지정된 key가 있으면 그에 대응하는 값으로 대체한다.

```java
map.replace("apple", 70);
```