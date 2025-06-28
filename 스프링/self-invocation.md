### 01.들어가기 전

테스트 해볼 기본적인 인터페이스와 클래스 구현

```java
public interface Business {
    void ready();

    void go();
}
```

```java
@Slf4j
public class SimpleBusiness implements Business {

    @Override
    public void ready() {
        log.info("------ready");
        go();// this.go()
    }

    @Override
    public void go() {
        log.info("------go");
    }
}
```

- ready()에서 go()를 요청하는 모습
- 이때, go 는 this.go(); 인 셈

아래는 프록시를 통해 호출하기 위해서 만들어둔 클래스 입니다!

```java
@Slf4j
public class ExecuteLoggingAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("####### execute method [{}]", invocation.getMethod().getName());
        return invocation.proceed();
    }
}
```

- `ExecuteLoggingAdvice`
    - `MethodInterceptor` 인터페이스를 구현하는 **Advice 클래스**
    - invoke 메서드: 메서드 호출 일어날때 마다 실행, 부가작업 추가 가능
    - 메서드 호출 전 로그를 남기고, 그 다음 타겟 객체의 원본 메서드를 실행
- `SimpleBusiness` 클래스를 프록시 대상으로 설정 , 전달하여 기본 타겟 객체를 설정

### 02. 직접 ready() 호출하기 테스트

```java
@Test
@DisplayName("business를 만들어서 직접 호출한다")
void direct_business() {
    final Business bs = new SimpleBusiness();
    bs.ready();
}
```

- 만들어서 ready()를 호출
- 결과

![self-invocation](/스프링/images/img4.png)

- ready → go 이 호출됨

### 03.프록시 이용해 ready() 호출 테스트

```java
@Test
@DisplayName("proxy를 이용해서 호출한다")
void proxy_self_invocation() {
    final ProxyFactory factory = new ProxyFactory(new SimpleBusiness());
    factory.addInterface(Business.class);
    factory.addAdvice(new ExecuteLoggingAdvice());

    final Business bs = (Business) factory.getProxy();
     bs.ready();
 }
```

- 코드 설명
- `ProxyFactory`: 스프링에서 제공하는 프록시 생성 도구
- 인터페이스(Business)와 대상객체(SimpleBusiness)를 지정하여 객체의 대리역할을 하는 프록시 객체 생성
- 프록시에 부가 작업(advice - ExecuteLoggingAdvice) 추가

bs.ready()호출하면

- > **이때 프록시 객체는 SimpleBusiness객체에 대한 모든 메서드 호출을 가로채서 ExecuteLoggingAdvice의 invoke메서드 호출**
- 결과

![self-invocation](/스프링/images/img5.png)

- execute method [go]가 호출이 안되었네요

### 04. 어떻게 동작하는걸까?

client → ready() 호출 → proxy → go()호출 → target(SimpleBusiness)에 도달

- target object에 도달하면, 자기자신 호출이 가능
    - 이때, this.ready(), this.go()
    - **proxy가 아닌 this 참조로 호출한 것 → go()는 내부호출!**
    - execute method [go]가 호출되지 않은 이유

⇒ 이걸 **self-invocation**이라고 하는데,,,

transcational에서 이러한 self-invocation이 발생할 수 있음

한마디로 말하자면 **객체 내 메서드가 동일 객체의 다른 메서드를 호출할 때 발생**한다~

- > 스프링에서는 AOP, 트랜잭션 설정시 주로 나타날수 있는 문제이다!

![self-invocation](/스프링/images/img6.png)

### 05. self-invocation

```java
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void doSomething(final Member member) {
        saveMember(member);//self-invocation 발생
    }

    @Transactional
    public void saveMember(final Member member) {
        memberRepository.save(member);
    }
}
```

→ 근데 intellij에서 `*saveMember(member);*` 요기에 노란줄 그어준다~

```
@Transactional self-invocation (in effect, a method within the target object calling another method of the target object)
does not lead to an actual transaction at runtime
```

- doSomething → saveMember()를 호출
- 사실 Member 저장이되는데, 이건 transcational이 동작해서 그런것이 아닌, this로 저장된 것
    - AOP 기능이 수행되지 않아 트랜잭션 기능이 동작하지 않았다고 할 수 있음

→ 해결하려면?

service 단에 @Transactional 붙이거나, doSomething 위에 @Transactional 붙이면 된다!