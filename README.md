# UNVEIL Backend

> Spring Boot 기반 메인 백엔드 서비스

---

## 📋 개요

UNVEIL Backend는 사용자 인증, 위치 관리, 안전도 분석 등 핵심 비즈니스 로직을 담당하는 Spring Boot 기반 서비스입니다.

---

## 🎯 주요 기능

- **사용자 인증**: 회원가입, 로그인, JWT 기반 인증
- **위치 관리**: 사용자 위치 정보 등록 및 조회
- **안전도 분석**: CCTV 및 가로등 데이터 기반 안전도 분석

---

## 🛠️ 기술 스택

- **Java** 17
- **Spring Boot** 3.2.0
- **Spring Data JPA** - 데이터 접근 계층
- **MySQL** - 데이터베이스

---

## 🏗️ 프로젝트 구조

```
src/
├── main/
│   ├── java/com/unveil/
│   │   ├── controller/      # REST API 컨트롤러
│   │   ├── service/         # 비즈니스 로직
│   │   ├── repository/      # 데이터 접근 계층
│   │   ├── domain/          # 도메인 모델 (user, location 등)
│   │   ├── config/          # 설정 (Security, CORS, Swagger 등)
│   │   └── common/          # 공통 유틸리티 및 응답 객체
│   └── resources/
│       └── application.yml  # 애플리케이션 설정
└── test/                    # 테스트 코드
```

---

## 📡 주요 API

### 사용자 API

- 회원가입
- 로그인

### 위치 API

- 위치 등록
- 내 위치 목록 조회

---

## 🔒 보안

- Spring Security 기반 인증/인가
- JWT 토큰 기반 인증
