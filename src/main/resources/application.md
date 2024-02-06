## application.properties : spring boot 애플리케이션 구성 속성을 설정하는 파일
- 키-값 쌍의 형식으로 사용
- ex) DB 연결, 로깅, 서버 포트 등등
```dtd
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/{DB 이름}?useUnicode=yes&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=Asia/Seoul
spring.datasource.username=root
spring.datasource.password=
<!--jpa 테이블 이름 대문자로 지정 가능하게 하는 설정-->
spring.jpa.hibernate.naming.physical-strategy = org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

mybatis.type-aliases-package=com.spring.boot.mapper
mybatis.mapper-locations=mybatis-mapper/*.xml

        # application.yml : 차이) 구조가 단순함
        # ex)
        # spring:
        #    datasource:
        #        driver-class-name:
        #        url:
        #        username:

        # 우선순위: application.properties > application.yml

    # 실행 SQL 문을 확인하겠다
spring.jpa.show-sql=true

spring.jpa.hibernate.ddl-auto=none
    # create: 기존 테이블 삭제, 새로 생성
    # create-drop: create 속성에 추가로 애플리케이션이 종료하면 테이블 삭제 [DROP + CREATE + DROP]
    # update: DB 테이블과 Entity 정보를 비교해서 변경 사항을 반영 [ ALERT TABLE ]
    # validate : DB 테이블과 Entity 정보를 비교해서 차이가 있으면 실행 x
    # none: 자동 생성 기능을 사용하지 않겠다.
```

