## 요구사항 기획 및 설계
- 사용자는 특정 매장으로부터 상품 주문이 가능함
- 품절 상태면 주문이 불가능하여야함

## 기능 설계

### 고객(User)
- 회원가입을 통해 주문을 할 수 있다.
- 회원은 이름, 주소, 전화번호가 등록이 되어야 한다.

### 상품(Product)
- 상품을 등록, 삭제, 수정을 할 수 있다.
- 재고 관리를 할 수 있다.

### 매장(Store)
- 매장 이름,전화번호,영업시간이 저장 되어야 한다.
- 매장에서 주문을 수락 할 수 있다.

### 주문(Order)
- 고객은 상품을 주문 할 수 있다.



***

기술 스택
+ Spring Boot
+ Lombok
+ Docker
+ Mysql

***



## Building

프로젝트 빌드
```
./gradlew build 
```
도커이미지 빌드
```
docker build -t <Images Name> .
```
DockerFile
```DockerFile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/online-order-system-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
```
Docker network 생성 후 Mysql 빌드
```
docker run -d --name mysql-container --network <network name> -p 3306:3306 -e MYSQL_ROOT_PASS=root mysql:8.0.31
```
Mysql 같은 네트워크 안에 프로젝트 빌드
```
docker run -d --name spring-container -p 8080:8080 --network <network name> <Iamges Name> <Tag>
```

***
1. Create Customer
   
POST http://localhost:8080/api/v1/customers?name=user&address=Suwon&phoneNumber=010-1111-1111
```JSON
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
2. Order Product
   
POST http://localhost:8080/api/v1/orders
```
Content-Type: application/json
{
  "customerId": 1,
  "storeId": 1,
  "products": {
    "1" : 0,
    "2" : 0
  }
}
```

```JSON
{
  "statusCode": "OK",
  "message": "SUCCESS",
  "data": null
}
```



