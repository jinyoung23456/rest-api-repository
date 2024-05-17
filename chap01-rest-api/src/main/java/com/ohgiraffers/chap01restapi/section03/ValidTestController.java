package com.ohgiraffers.chap01restapi.section03;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/valid")
public class ValidTestController {

    @GetMapping("/users/{userNo}")
    public ResponseEntity<Void> findUserByNo() throws UserNotFoundException {

        boolean check = true;
        if(check) {
            throw new UserNotFoundException("회원 정보를 찾을 수 없습니다.");
            //의도적 오류 발생시키기
        }

        return ResponseEntity.ok().build(); //끝!
    }

    @PostMapping("/users")       /* @Valid DTO어노테이션 설정들은 이걸 달아야 적용됨 */
    public ResponseEntity<Void> registUser(@Valid @RequestBody UserDTO user) {

        return ResponseEntity
                .created(URI.create("/valid/users/1"))
                .build();
    }

}
