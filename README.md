![image](https://github.com/user-attachments/assets/279df4fd-20dc-4afb-822b-e7bd07e0dc32)
## 서울시 공공 데이터 API를 사용한 의류 수거함 위치 찾기 웹페이지 ottdadam 

1. 스프링부트 + react를 사용한 의류 수거함 위치 사이트 


## :mag: 프로젝트를 시작하게 된 계기
```
요즘 의류 수거함이 집 근처에 없는 경우도 간혹 있어 의류 수거함을 한참 동안 찾아 헤매던 기억이 떠올라 프로젝트를 개발을 기획하게 되었다.
해당 프로젝트는 공공데이터를 제공받을 수 있는 서울시 일부 지역 한정으로만 의류 수거함의 위치를 찾을 수 있다.
```

## 🗺️ 화면 설명

![image](https://private-user-images.githubusercontent.com/60298173/281826378-b80f830d-e751-471f-8391-6061b7990bb3.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU3MTE5ODksIm5iZiI6MTcyNTcxMTY4OSwicGF0aCI6Ii82MDI5ODE3My8yODE4MjYzNzgtYjgwZjgzMGQtZTc1MS00NzFmLTgzOTEtNjA2MWI3OTkwYmIzLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA3VDEyMjEyOVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTU5NjYwN2M4MmVhYWZjODNhMGIwNThmZTlkZGNjZmUyN2E3MzRkMGI5YjU2YmQ2NDNjM2NjMzU3ZjA1MWFhZDAmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.fQA6-YL4rzuUVshXWgu5bkFS2X0XNhnpMXo1hktzIpQ)

## :two_women_holding_hands: 멤버
프론트 1명 ,  백엔드 1명

## :dart: 프로젝트 구성 
- 서울시 공공 의류 수거함 데이터 API RestTemplate 사용하여 가져오기 및 데이터 저장 (C)
- Batch Scheduler를 사용하여 데이터 00:30분 기준으로 동기화 (RUD)
- POI라이브러리를 사용하여 엑셀 의류 수거함 데이터 정제 및 저장 (C)
- Front에서 해당 지역 검색 시, 조회 로직 추가 
- GCP를 사용하여 배포하여 배포하였습니다. 

## :alarm_clock: 개발 기간 
- 2023.04 ~ 2023.08

## :hammer: 개발 환경 
- java : 11
- IDE : Intellij
- Framework : SpringBoot(2.7)
- ORM : MyBatis
- DataBase : mysql
- Cloud : AWS EC2
- Server : Nginx
- View : React

## :page_facing_up: 프로젝트 접속 링크 
<http://43.200.171.27:3000>

## 기능 설명 
![image](https://github.com/user-attachments/assets/7aad05cf-7354-413a-b1ab-e61dd1e0b8ce)

페이지 실행 첫 화면이다. 처음 화면은 디폴트로 구로구 구로2동에 관한 위치정보를 알려준다.

![image](https://github.com/user-attachments/assets/f4cbe278-f8a5-421b-84e3-4411f9836c37)

거주하고 있는 구의 의류수거함을 클릭한다.

![image](https://github.com/user-attachments/assets/9ddf3e34-9f7f-4253-bed0-87fe26b71aa5)

거주하고 있는 구에 맞는 동을 클릭한다.

![image](https://github.com/user-attachments/assets/685fb6c6-b98f-4e90-beed-873b24f12f24)
![image](https://github.com/user-attachments/assets/d9fa56b3-9e4d-4a3a-bbb4-2168e8963fec)

지도를 보며 위치와 가까운 의류수거함 아이콘을 클릭하거나, 옆의 주소 리스트를 클릭하면 지도에 의류수거함 위치 정보가 나온다.
