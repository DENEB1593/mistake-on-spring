# Mistakes on Spring

---

### 1. leaking internal
 User의 Password 속성과 같이 Client단에 노출되어야 되지 말아야 하는 정보가 존재한다.
해당 정보는 Client에게 노출을 막기위해 해당 속성을 응답 값에서 제외하는 방법이 있다.

이때 @JsonIgnore을 getter에 적용하는 방법이 있지만 User는 Entity 속성이므로 응답과 관련된
이유로 Entity의 속성을 간섭할 수 있기 때문에 별도의 응답(ex UserDto)를 두어 password 속성을 제외한채 응답을 하는 방법을 고려할 수 있다.


### 2. record 사용 고려
 UserDto는 응답을 위해 생성된 모델이다. User의 속성을 그대로 의존하기 때문에 User에서 변환 후 
 별도의 수정이 발생해서는 안된다. 이때 맴버변수를 final로 하는 불변성을 지닌 객체를 고려할 수 있지만
 JDK 14이상이면 record 타입을 고려하는 것도 좋다. 단순한 키워드로 코드량을 줄일 수 있다.


### 3. 계층에 맞는 처리를 한다.
일반적으로 웹서비스 구조는 Controller -> Service -> DAO로 구성된다.<br>
Controller는 HTTP 상 요청/응답에 대한 처리를 하는 곳이지 DAO에서 조회/수정/삭제등에 대한 결과를 
하는 계층은 아니다. 현재 예시 코드의 UserController는 UserRepository를 의존하여 비즈니스 로직을 처리하기 때문에
계층 구조를 고려하지 않은 코드가 된다. 별도의 UserService를 두어 User와 관련된 비즈니스 처리는 Servce에서 처리한다.


### 4. 오류에 맞는 오류응답 처리
대표적으로 4XX 오류임에도 5XX(서버 측) 오류로 처리하는 경우가 있다.