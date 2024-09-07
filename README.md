
## 서울시 공공 데이터 API를 사용한 의류 수거함 위치 찾기 웹페이지 ottdadam 

1. 스프링부트 + react를 사용한 의류 수거함 위치 사이트 
2. 번외로 회사에서 사용하는 개발 기술 스택에 익숙해지기 위하여 회사에서 사용하는 기술 스택을 위주로 프로젝트를 진행하였다.


## :mag: 프로젝트를 시작하게 된 계기
```
요즘 의류 수거함이 집 근처에 없는 경우도 간혹 있어 의류 수거함을 한참 동안 찾아 헤매던 기억이 떠올라 프로젝트를 개발을 기획하게 되었다.
해당 프로젝트는 공공데이터를 제공받을 수 있는 서울시 일부 지역 한정으로만 의류 수거함의 위치를 찾을 수 있다.
```
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
