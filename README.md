# online-order-system

매장 기반 온라인 상품 주문 시스템 REST API입니다.
고객 등록, 상품/매장 관리, 주문 처리 및 재고 검증 기능을 제공합니다.

## 기술 스택

| 구분 | 기술 | 버전 |
|------|------|------|
| **Language** | Java | 17 |
| **Framework** | Spring Boot | 3.3.1 |
| **Data Access** | Spring Data JDBC | - |
| **Database** | MySQL | 8.0.31 |
| **Template** | Thymeleaf | - |
| **Build** | Gradle | - |
| **Infra** | Docker | - |
| **기타** | Lombok | - |

## 주요 기능

### 고객 (Customer)
- 회원가입 (이름, 주소, 전화번호)

### 상품 (Product)
- 상품 등록, 수정, 삭제
- 매장별 재고 관리

### 매장 (Store)
- 매장 정보 관리 (이름, 전화번호, 영업시간)
- 주문 수락

### 주문 (Order)
- 고객이 특정 매장에서 상품 주문
- **재고 검증**: 품절 상태 시 주문 불가 처리
- 주문 시 재고 자동 차감

## 프로젝트 구조

```
src/main/java/com/example/onlineordersystem/
├── controller/
│   ├── CustomerController.java       # 고객 API
│   ├── OrderController.java          # 주문 API
│   ├── GlobalExceptionHandler.java   # 전역 예외 처리
│   ├── NewOrderRequest.java          # 주문 요청 DTO
│   └── Response.java                 # 공통 응답 래퍼
├── domain/
│   ├── Customer.java                 # 고객 엔티티
│   ├── Product.java                  # 상품 엔티티
│   ├── Store.java                    # 매장 엔티티
│   ├── StoreProduct.java             # 매장-상품 재고 엔티티
│   ├── Order.java                    # 주문 엔티티 (@MappedCollection)
│   └── Orderitem.java               # 주문 항목 엔티티
├── repository/
│   ├── CustomerRepository.java       # CrudRepository
│   ├── OrderRepository.java          # CrudRepository
│   └── StoreProductRepository.java   # 재고 조회 쿼리
└── service/
    ├── CustomerService.java          # 고객 비즈니스 로직
    ├── OrderService.java             # 주문 + 재고 검증 로직
    └── StoreService.java             # 매장 상품 관리
```

## API 명세

### Customer API
| Method | Path | 설명 |
|--------|------|------|
| POST | `/api/v1/customers` | 고객 등록 |

**요청 예시**
```
POST /api/v1/customers?name=user&address=Suwon&phoneNumber=010-1111-1111
```

**응답**
```json
{
  "statusCode": "OK",
  "message": "SUCCESS",
  "data": {
    "name": "user",
    "address": "Suwon",
    "phoneNumber": "010-1111-1111"
  }
}
```

### Order API
| Method | Path | 설명 |
|--------|------|------|
| POST | `/api/v1/orders` | 상품 주문 |

**요청 예시**
```json
{
  "customerId": 1,
  "storeId": 1,
  "products": {
    "1": 2,
    "2": 1
  }
}
```

## 아키텍처 특징

- **Spring Data JDBC**: JPA 대신 경량 ORM 사용, `@MappedCollection`으로 Aggregate Root 패턴 적용
- **Command Object 패턴**: `CreateCustomer`, `CreateOrder`로 Controller-Domain 계층 분리
- **공통 응답 래퍼**: `Response<T>` 클래스로 `success()` / `fail()` 표준 응답 형식
- **재고 검증**: OrderService + StoreProduct 이중 검증 (Defense in Depth)
- **SQL 초기화**: `schema.sql` + `data.sql`로 테이블 생성 및 샘플 데이터 자동 로드

## 시작하기

### Docker로 실행

```bash
# 1. 프로젝트 빌드
./gradlew build

# 2. Docker 이미지 빌드
docker build -t online-order-system .

# 3. Docker 네트워크 생성
docker network create order-network

# 4. MySQL 실행
docker run -d --name mysql-container --network order-network \
  -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql:8.0.31

# 5. 애플리케이션 실행
docker run -d --name spring-container --network order-network \
  -p 8080:8080 online-order-system
```

### 로컬 실행

```bash
# MySQL 로컬 실행 후
./gradlew bootRun
```

접속: `http://localhost:8080`

## Dockerfile

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/online-order-system-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
```
