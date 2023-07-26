# ✨ Meeting for break bread ✨

## 🍴 혼밥을 원하지 않는 대학생들을 위한 식사 예약 및 식사 모임 앱 🍱

<br>

## 🔥 구현 목표 및 구현 내용 알아보기 🔥

* 혼자서 밥먹는 즉, **혼밥**을 싫어하는 대학생들을 위한 식사 예약 및 식사 모임 앱
* 학생들이 모이기 위해서 어떠한 방법이 좋을까?라는 생각을 하다가 **예약 시스템 방법**을 생각
* 사용자의 계정을 이용하여 로그인을 하게 되면, 사용자의 현재 위치 API를 이용하여 주위의 식당과 식당의 리뷰 별점 후기, 마커 기능이 추가된 KAKAO Map API, 사용자의 프로필 등을 확인
* 로그인이 된 계정은 **실시간으로 데이터베이스**에 저장
* 원하는 식당의 상세정보에는 기존 정보를 추가로 식당의 현 위치 지도를 나타내고, 주 메뉴와 예약을 할 수 있는 버튼들을 나열
* 예약 시, 인원 수와 Comment를 적고 만날 수 있는 시간을 정하여 예약하게 되고, 일정 인원 수가 모이면 만나서 식사를 할 수 있도록 구현
* 이때, 사용자의 계정으로 예약된 정보도 마찬가지로 **실시간으로 데이터베이스**에 저장

<br>
<br>

## 🔥 실행 결과 🔥

![image](https://github.com/Skyllerrr/Meeting-for-break-bread/assets/93968241/5792ceae-ec8a-40c0-8f10-69285ef2cdc5)

> **서비스를 실행하게 되면, 회원가입 및 로그인 화면으로 각 사용자의 계정을 공유하여 예약 시스템을 이용**

<br>
<br>

![image](https://github.com/Skyllerrr/Meeting-for-break-bread/assets/93968241/8ef980a6-ce6f-4a57-8f4a-2e701cb5e94a)

> **로그인을 하게 되면, 위의 사진처럼 사용자의 계정이 실시간으로 데이터베이스에 저장이 됨**

<br>
<br>

![image](https://github.com/Skyllerrr/Meeting-for-break-bread/assets/93968241/3b3c6773-eab0-4ea2-8812-70c2e5d22623)
![image](https://github.com/Skyllerrr/Meeting-for-break-bread/assets/93968241/50b8863b-f463-435e-b0b7-11fb7da57514)

> **사용자의 위치를 기준으로 적용된 주위 식당 및 식당의 리뷰 별점 후기, 마커 기능이 추가된 KAKAO Map, 사용자의 프로필**

<br>
<br>

![image](https://github.com/Skyllerrr/Meeting-for-break-bread/assets/93968241/4fb18a99-aa70-4fd1-a89f-ebc1d6091d0a)

> **식당의 기본 정보와 현 위치, 주 메뉴, 예약 시스템을 이용할 수 있는 식당의 상세정보**

<br>
<br>

![image](https://github.com/Skyllerrr/Meeting-for-break-bread/assets/93968241/75674b5e-7dfa-4f29-b556-0f2e259cf8bd)

> **식당을 예약하기 위해서는 인원 수, Comment, 만날 시간 등을 입력하여 저장**

<br>
<br>

## 💡 여기서 잠깐! 💡

### 식당의 리뷰를 남기는 별점의 정보는 어떤식으로 저장되며 계산이 될까?!

<br>

![image](https://github.com/Skyllerrr/Meeting-for-break-bread/assets/93968241/00348cf2-82f8-4f9e-8103-e625ab6e8057)
![image](https://github.com/Skyllerrr/Meeting-for-break-bread/assets/93968241/91b2efd7-cd26-46bf-921e-61c57f2e6461)

> **리뷰 작성을 마친 결과 및 별점의 정보**

<br>

> ### 식당에 대한 평점은 여러 사용자들의 총 누적 별점을 총 누적 리뷰 개수 즉, 리뷰를 남긴 누적된 인원 수만큼 나눠 계산이 된다. <br>
> ### 예시로, 위 사진의 평점은 4.75에서 반올림을 하여 약 5점의 평점을 보여준다.
  











