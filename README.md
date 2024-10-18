# waitingQueue
대기열 시스템 만들어보기

## 기본 개념
특정 작업이 시간이 걸리는 작업이고  
한번에 서버가 수용할 수 있는 작업의 수가 제한되어 있을 때  
대기열 시스템을 만들어서 대기열에 있는 작업을 순차적으로 처리할 수 있도록 하는 것  

1. 작업을 요청하는 클라이언트가 있을 때, 서버는 작업을 수행할 수 있는지 확인한다.   
2. 서버가 작업을 수행할 수 없다면, 대기열에 작업을 추가한다
3. 대기열에 추가 되면 클라이언트에게 순서를 알려준다. (실시간으로 갱신 가능)

## 구현에 필요한 기능

1. 대기열을 관리할 수 있는 서버는 대기열을 관리하는 서버와 작업을 수행하는 서버로 나눈다. (스파이크성 트래픽을 관리 해야함)  
    - 대기열 등록 요청, 조회 서버
    - 대기열에 있는 작업을 수행하는 서버
2. 대기열을 관리할 DB가 필요
   - redis를 사용하여 대기열을 관리
   - 사용자 요청 request의 timestamp를 기록하여 대기열에 추가된 순서를 기록
3. 클라이언트에게 대기열의 상태를 실시간으로 전달할 수 있는 방법이 필요
   - 롱폴링 방식을 사용하여 대기열의 상태를 요청
4. 현재 활성 작업 수를 체크하여 모니터링 할 수 있는 방법이 필요
   - 작업 워커 서버에서 작업이 시작되면 카운트를 증가시키고 작업이 끝나면 감소시킨다.
