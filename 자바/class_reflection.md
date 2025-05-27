## Class 클래스

Class 클래스의 주요 기능

- 타입 정보 얻기
- 리플렉션
    - 클래스에 정의된 메서드, 필드, 생성자 등을 조회 + 객체 인스턴스를 생성하거나 메서드 호출 등
- 동적 로딩과 생성
    - 클래스 동적 로딩 - `Class.forName()`
    - 새로운 인스턴스 생성 - `newInstance()`
    - 애노테이션 처리
        - 클래스에 적용된 애노테이션 조회하고 처리하는 기능

1. Class 조회
    1. 클래스에서 조회 - `Class clazz = String.class;`
    2. 인스턴스에서 조회 - `Class clazz = new String().getClass();`
    3. 문자열로 조회 - `Class clazz = Class.fotName(”java.lang.String”);`
2. 모든 필드 조회
    1. `Field[] fields = clazz.getDeclaredFields();`
3. 모든 메서드 조회
    1. `Method[] methods = clazz.getDeclaredMethods();`
4. 상위 클래스 정보 조회
    1. `clazz.getSuperclass().getName();`
5. 인터페이스 정보 조회
    1. `Class[] interfaces = clazz.getInterfaces();`

### 클래스 생성하기

- lang.clazz에 Hello 클래스 만들기
    
    ```java
    package lang.clazz;
    
    public class Hello {
    		public String hello() {
    			return "hello!";
    		}
    }
    ```
    
- 클래스를 조회해서 생성자에 접근하여 인스턴스를 생성할 수 있음 !
    
    ```java
    //문자열로 클래스 조회
    Class helloClass = Class.forName("lang.clazz.Hello");
    
    //getDeclaredConstructor() : 생성자 선택, newInstance() : 생성자 기반으로 인스턴스 생성
    Hello hello = (Hello) helloClass.getDeclaredConstructor().newInstance();
    
    //Hello의 hello() 메서드 호출
    String result = hello.hello();
    System.out.println("result = " + result);
    ```
    

### 리플렉션 - reflection

위처럼 Class의 메타 정보를 기반으로 클래스에 정의된 메서드, 필드, 생성자 등을 조회<br>
→ 객체 인스턴스를 생성하거나 메서드를 호출 하는 작업<br>
⇒ 이를 “리플렉션” 이라고 함!

- 자바에서 리플렉션의 중요성
    - 예로 의존성 주입(DI)
    - 리플렉션으로 클래스 메타데이터 분석 → @Autowired 어노테이션이 붙은 필드를 찾아서 자동 의존성 주입 해줌
    - 스프링 컨테이너는 해당 필드의 타입에 맞는 빈찾고, 할당
    - 개발자는 객체의 생성과 의존성 관리를 수동으로 하지 않아도 됨
- 스프링의 @Autowired 리플렉션을 사용하여 생성자 주입
    1. @Autowired 어노테이션이 생성자 위에 있으면, 해당 생성자를 찾기
    2. 생성자의 매개변수를 확인 → 객체 만들때 주입해야 할 의존성 해석
        1. 매개변수의 타입을 보고, 같은 타입의 객체(빈)을 스프링 컨테이너에서 찾음
    3. 찾은 객체들은 생성자의 매개변수로 사용하여 새로운 객체(인스턴스)를 만듦
        1. 즉, 객체 생성및 주입

`Class carClass2 = Class.forName("Car");`

- 클래스의 이름만으로 해당 클래스위 정보를 가져옴
- Reflection API 는 클래스의 이름만으로 생성자, 필드, 메서드 등 해당 클래스에 대한 거의 모든 정볼르 가져올 수 있는 API
- 안에서 이루어지는 과정
    - .java → .class : 컴파일 단계 (주체: 컴파일러)
        - 개발자 작성한 .java 파일을 자바 컴파일러(javac)에 의해서 바이트 코드로 변환
        - 이 바뀐 바이트 코드는 .class 파일에 저장
    - .class → JVM 메모리 적재 : 클래스 로딩 단계
        - 자바 애플리케이션 실행 시 .class 파일을 JVM이 클래스 로더를 통해 읽음
        - 로딩된 클래스의 구조 정보(메타 데이터) : 메타스페이스에 저장
        - 메타스페이스에 저장되는 정보 : 클래스 이름, 패키지, 상속 정보, 필드 메서드 시그니처 등
            - Reflection API가 메타스페이스에 저장된 정보 활용
    - 실행 단계가 런타임 단계
        - JVM이 바이트코드를 읽고 해석(인터프리팅) 또는 JIT 컴파일해서 CPU에서 실행

### 메서드

- 필드 접근
    - `getField()`: public 필드에 접근할 때 사용
    - `getDeclaredField()`: 클래스에 선언된 모든 필드에 접근할 때 사용 (접근 제어자 상관X)
- 필드 수정
    - `(Integer) publicField.get(example)` : 읽기
    - `publicField.set(example, 10)` : 수정
- 메서드 접근
    - `getMethod()` : public 메서드에 접근할 때 사용
    - `getDeclaredMethod()` : 클래스에 선언된 모드 메서드에 접근할 때 사용 (접근 제어자 상관X)
- 메서드 실행
    - `invoke()`
- 생성자 접근
    - `getConstructor()` : public 생성자에 접근할 때 사용
    - `getDeclaredConstructor()` : 클래스에 선언된 모든 생성자에 접근할 때 사용(접근 제어자 상관X)
- 접근한 생성자로 객체 생성
    - `.newInstance()`

### 리플렉션과 invoke()

- 런타임에 어떤 객체의 어떤 메서드를 호출할 지 결정가능
- 의존성 주입, AOP, 이벤트 처리 등에서 리플렉션 기반으로 동작 → invoke() 자주 사용
- 예시 - 디버그 중 발견
- 사용 이유
    - 리플렉션을 통해 어떤 메소드가 실행된다는 신호
    - 스프링이 복잡한 작업을 단순화하고 자동화하기 위해 사용하는 일반적인 방법 중 하나

### 리플렉션 단점

- 성능 오버헤드
- 리플렉션 기술은 런타임에 사용 → jvm이 최적화할 수 없기 때문
- 그렇기에, 애플리케이션 개발보다는 프레임워크나 라이브러리에 사용

### 리플렉션은 어디에 활용될까?

- Spring의 컴포넌트 스캔
    - 스프링 컨테이너에서 빈에서 빈을 생성할 때, 빈정의(BeanDefinition)에 따라 객체 생성에 대한 정보를 참조 → 이때, 리플렉션 이용해 객체(빈)을 생성
- 엔티티에서 기본 생성자가 필요한 이유
    - JPA를 사용하기 위해 엔티티에 기본 생성자 반드시 필요
    - 이유: 동적으로 객체를 생성 시 리플렉션 기술 사용
    - 생성자의 인자 정보 가져올 수 없기에, 기본생성자가 있어야 객체 생성 가능
    - 동적으로 객체 생성하려면 new 객체이기에, 인자 정보를 모르면 생성이 불가능 함 → 기본 생성자가 필요
    - JPA는 DB 값을 객체 필으데 주입할 때 기본 생성자로 객체를 생성한 후 reflection을 사용하여 값을 매핑