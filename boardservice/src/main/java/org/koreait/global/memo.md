레디스 설정들 추가

모든요청이 들어오면 우선 쿠키를 보내게 되는데
처음에는 없으면 먼저 생성을 해주게 됨 / 이유는 세션을 사용하지 않기 때문임
-> 유저해시를 만듦 (개개인을 구분하기 위해서)

인터셉터 -> 레디스형태로 할거라 콘피그에서 레디스 콘피그 추가
/ 레디스를 넣은 이유 ? 임시데이터, 기록하기 위한거 그래서 만료시간을 넣어준거 ->
엔티티에서 코드밸류 추가 / 레포지토리에서 코드밸류레포지토리추가 -> 
글로벌(유틸)에서 Code - Value 레디스 저장소 저장 관련 추가 ->

// BoardAuthService
if (utils.getValue(utils.getUserHash() + "_board_" + seq) == null) {
ㄴ//utils.getUserHash() + "_board_" + seq 이만큼이 키==코드가 됨
utils.saveValue(utils.getUserHash() + "_seq", seq);
ㄴ//utils.getUserHash() + "_seq"는 키==코드고 seq는 밸류가 되는거
throw new GuestPasswordCheckException();