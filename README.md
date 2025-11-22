# UNVEIL Backend

Spring Boot 기반 백엔드 프로젝트입니다.

## 기술 스택

- Java 17
- Spring Boot 3.2.0
- Gradle
- Spring Data JPA
- MySQL
- Lombok

## 프로젝트 구조

```
src/
├── main/
│   ├── java/com/unveil/
│   │   ├── controller/      # REST API 컨트롤러
│   │   ├── service/         # 비즈니스 로직
│   │   ├── repository/      # 데이터 접근 계층
│   │   └── common/          # 공통 유틸리티 및 응답 객체
│   └── resources/
│       └── application.yml  # 애플리케이션 설정
└── test/                    # 테스트 코드
```

## 실행 방법

1. 프로젝트 빌드
```bash
./gradlew build
```

2. 애플리케이션 실행
```bash
./gradlew bootRun
```

또는 IDE에서 `UnveilBeApplication.java`를 실행합니다.

## 기본 설정

- 서버 포트: 8080
- Context Path: /api
- 데이터베이스: MySQL (localhost:3306/unveil)

## 개발 환경

- JDK 17 이상
- Gradle 8.5 이상
- MySQL 8.0 이상

## 데이터베이스 설정

애플리케이션 실행 전에 MySQL 데이터베이스를 생성해야 합니다:

```sql
CREATE DATABASE unveil CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

`application-local.yml`에서 데이터베이스 연결 정보(사용자명, 비밀번호)를 환경에 맞게 수정해주세요.