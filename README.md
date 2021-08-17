# springboot-prevent-xss


springboot 에서 XSS 방지 처리 구현

DB 에 XSS 방지 문자열을 저장 또는 업데이트 해야되는 경우 이용
(* JSON 응답 시 escape 처리가 가능하나 위 제약 조건이 있을 경우 처리)

API 요청 시 Controller 에서 `java bean validation` 을 이용하여 필드 유효성 검증
요청 DTO 를 엔티티로 변환하는 과정에서 XSS 방지 목적 escape 처리 (BaseDto 내 preventXss() 참고)

샘플의 경우 PHP 의 htmlspecialchars 함수와 대응됩니다.

업데이트의 경우 이중으로 인코딩되는 문제를 방지하고자 디코딩 수행 후 인코딩 처리