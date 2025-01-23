package org.koreait.global.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash(timeToLive = 300) // 5분간 값 유지 / 유효시간 / 5분지나면 데이터는 삭제됨.
public class CodeValue implements Serializable { // 레디스로 써져야하기 때문에Serializable 추가.

    @Id // 자카르타 아니고 스프링쪽으로 추가해줘야함
    private String code;
    private Object value; // 다양한데이터를 저장하지만 유틸쪽에서 편의기능으로 해당타입으로 구현할 수 있게 지네릭메서드형태로 해줄꺼라 여기서는 지네릭보단 오브젝트가 더 맞음.

}
