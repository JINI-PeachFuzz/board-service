package org.koreait.global.repositories;

import org.koreait.global.entities.CodeValue;
import org.springframework.data.repository.CrudRepository;

public interface CodeValueRepository extends CrudRepository<CodeValue, String> {
    CodeValue findByCode(String code);
    // 코드가지고 코드밸류 데이터를 조회할 수 있게 추가함.
}
