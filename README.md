
## 목차

1. [**웹 서비스 소개**](#1)
2. [**버전 기록**](#2)
3. [**기술 스택**](#3)
4. [**주요 기능**](#4)
5. [**개발 기간 및 일정**](#5)
6. [**실행 방법**](#6)

<br />

<div id="1"></div>

## 📖 웹 서비스 소개

> **AUCTION(옥션)** 는 게시판 기반 실시간 경매 사이트입니다.

<br />  

옥션을 통해 판매자는 원하는 물품을 등록하고
구매자는 원하는 물품에 입찰할 수 있습니다.
이를 통해 판매자와 구매자가 모두 만족할 수 있는 가격에 거래할 수 있는 시스템입니다.



<div id="2"></div>

## 👷 버전 기록

|   버전   | <div align="center">업데이트 내용</div>      | 업데이트 날짜 |
| :------: | :------------------------------------------- | :-----------: |
| `v1.0.3` | - 더민트 api 및 도메인 변경                  |   22.09.03.   |
| `v1.0.2` | - 회원가입 및 정보수정 휴대폰 인증 방식 변경 |   22.08.24.   |
| `v1.0.1` | - 프로필 페이지 오류 수정                    |   22.08.23.   |
| `v1.0.0` | - 더민트 서비스 오픈                         |   22.08.19.   |

<div id="3"></div>

## 🛠 기술 스택 

### FrontEnd

| <div align="center"><img src="README.assets/html.svg" alt="HTML5" width="50px" height="50px" /> </div> | <div align="center"><img src="README.assets/css.svg" alt="CSS3" width="50px" height="50px" /></div> | <div align="center"><img src="README.assets/js.png" alt="JavaScript" width="50px" height="50px" /></div> |
| :----------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------: |
|                                     &nbsp;&nbsp;HTML5&nbsp;&nbsp;                                      |                        &nbsp;&nbsp;&nbsp;&nbsp;CSS3&nbsp;&nbsp;&nbsp;&nbsp;                         |                                                JavaScript                                                |


### BackEnd

| <div align="center"><img src="README.assets/java.svg" alt="java" width="50px" height="50px" /> </div> | <div align="center"><img src="README.assets/springboot.png" alt="springboot" width="100px" height="50px" /> </div> | <div align="center"><img src="README.assets/springsecurity.png" alt="springsecurity" width="100px" height="50px" /></div> | <div align="center"><img src="README.assets/gradle.png" alt="gradle" width="50px" height="50px" /></div> | <div align="center"><img src="README.assets/websocket.svg" alt="websocket" width="50px" height="50px" /></div> | <div align="center"><img src="README.assets/postgresql.svg" alt="mysql" width="70px" height="50px" /> </div> |
| :---------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------: |
|                                                 Java                                                  |                                                    Spring-Boot                                                     |                                                      Spring-Security                                                      |                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Gradle&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                    |                                 &nbsp;&nbsp;&nbsp;WebSocket&nbsp;&nbsp;&nbsp;                                  |                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PostgreSql&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


<div id="4"></div>

## 💡주요 기능

- **카운트다운**

  ```
  게시글이 만들어진 시점을 기준으로 1시간 카운트다운 시작.
  자바스크립트를 이용하여 나오는 버튼이 달라지게 하고 이 버튼마다 다른 기능 부여
  ```

- **경매 생성 및 경매 참여**

  ```
  판매자의 게시글 등록에 따라 구매자들이 원하는 금액으로 입찰하며
  시간이 만료되었을 때 가장 높은 금액을 제시한 사용자가 중고 물품을 구매할 수 있는 시스템

  ```


- **1:1 채팅**

  ```
  경매에서 승리한 입찰자와 판매자의 1대1 채팅 기능 제공
  ```


<div id="5"></div>


## 📅 개발 기간

**2024.05.06 ~ 2024.05.17**
<br />
<br />
