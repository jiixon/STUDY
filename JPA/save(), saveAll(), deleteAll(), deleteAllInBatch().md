### save()

![JPA](/JPA/images/img12.png)

### saveAll()

![JPA](/JPA/images/img13.png)
- 둘다 `@Transactional` 적용되어있음
- save(): 1건 마다 save()함수 호출
- saveAll(): 1건 마다 인스턴스 내부의 save()함수 호출

***→ saveAll()이 성능이 더 좋은데, 이유가 뭐냐?***

- `@Transactional`
    - AOP 프록시 기반으로 외부 Bean 객체가 있고, 이 객체의 함수를 호출해 Intercept가 되어 트랜잭션으로 묶이게 됨
    - Bean 객체 내부에서 내부함수 호출 시 @Transcational 적용X
- save() 호출 시,
    - 상위 `@Transactional` 존재하는 경우, 해당 Transcation에 참여
    - 존재하지 않은 경우, 새로 Trascation을 생성 후 save 후 commit
    - 외부 빈(repository) 객체의 save 함수 호출
        - 갯수가 많아질수록 비용발생 + 시간 오래걸림
- saveAll() 호출 시,
    - Bean 객체의 내부함수를 호출하기 때문에 save() 호출마다 Transaction이 생성되거나 참여하는 프록시 로직을 타지않고, 단순 메서드 호출
        - 비용 발생 안함

→ 결론?

![JPA](/JPA/images/img14.png)
- save(): 300개 데이터 insert시, 트랜잭션 300번 발동
- saveAll(): 300개의 묶음 단위 하나의 트랜잭션 발동

### deleteAll()

- deleteAll() 까보면 while문으로 delete쿼리가 나가기 때문에 사실  delete 쿼리가 하나씩 나감!

![JPA](/JPA/images/img15.png)

- 결국 데이터가 1000개면 1000번, 10000개면 10000번 돈다는 것이다

### deleteAllInBatch()

![JPA](/JPA/images/img16.png)

- `QueryUtils.*getQueryString*("delete from %s x` 부분이 있는데,
    - 즉, delete from {테이블 이름} 으로 delete 쿼리만 수행!
    - findAll이나 findById를 사용하지 않았기 때문에 delete이전에 select하는 과정은 없음
    - 벌크 작업으로 처리
- 대용량일 수록 성능 좋음
- 벌크(Bulk): 한 번의 쿼리로 다수의 데이터를 처리하는 방식
- 배치(Batch): 데이터를 여러 번에 나누어 처리하는 방식

- 벌크: 배치처리보다 빠르고 인덱스 활용할 수 있는 장점
    - 전이 메커니즘(cascadeType.ALL)이나 자동 애플리케이션 수준 낙관적 잠금 메커니즘(`@Version`)의 장점X
    - 엔티티 수정 사항이 영속성 컨텍스트에 자동으로 반영되지 않음
        - 예시)`@Version` 필드 가진 엔티티에서 삭제 진행시, 엔티티 버전 정보가 업데이트 되지 않음
        - 다른 트랜잭션에서 이 엔티티 참조하고 있을 시, 낙관적 잠금 충돌 감지 못함
