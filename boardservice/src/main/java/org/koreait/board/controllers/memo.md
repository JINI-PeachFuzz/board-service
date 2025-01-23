@PathVariable
- 경로 변수를 표시하기 위해 메서드 매개변수에 사용됨.
- 경로 변수는 중괄호로 둘러싸인 값을 나타냄.
- URL 경로에서 변수 값을 추출하여 매개변수에 할당함.
- 기본적으로 경로변수는 반드시 값을 가져야 하고 값이 없는 경우 404 오류발생함.
- 주로 상세 조회, 수정, 삭제와 같은 작업에서 리소스 식별자로 사용됨.

@GetMapping("/config/{bid}") // 경로변수 추가
public JSONData config(@PathVariable("bid") String bid) { // URL 경로에서 값을 추출하기 위한 애노테이션 / bid을 활용한 콘피그 조회 로직
// /config/123일경우 bid는 123이 되는거 // 겟방식이라 123을 전달해줌 // id는 유일무이한 거임
// {bid} 이부분은 중괄호로 감싸야하고, 경로변수와 PathVariable에 적은거==bid 2개는 동일해야함
return null;
}