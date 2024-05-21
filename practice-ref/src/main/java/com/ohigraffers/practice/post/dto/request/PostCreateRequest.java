package com.ohigraffers.practice.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/* Swagger 문서화 시 설명 어노테이션 작성 */
@Schema(description = "포스트 생성 DTO")
public class PostCreateRequest {

    /* Swagger 문서화 시 설명 어노테이션 작성 */
    /* 필수 값이므로 유효성 검사 어노테이션 작성 */
    @NotNull(message = "코드는 반드시 입력되어야 합니다.")
    @NotBlank(message = "코드는 공백일 수 없습니다.")
    private String code;

    /* Swagger 문서화 시 설명 어노테이션 작성 */
    /* 필수 값이므로 유효성 검사 어노테이션 작성 */
    @NotNull(message = "제목은 반드시 입력되어야 합니다.")
    @NotBlank(message = "제목은 공백일 수 없습니다.")
    private String title;

    /* Swagger 문서화 시 설명 어노테이션 작성 */
    /* 필수 값이므로 유효성 검사 어노테이션 작성 */
    @NotNull(message = "내용은 반드시 입력되어야 합니다.")
    @NotBlank(message = "내용은 공백일 수 없습니다.")
    private String content;

    /* Swagger 문서화 시 설명 어노테이션 작성 */
    /* 필수 값이므로 유효성 검사 어노테이션 작성 */
    @NotNull(message = "작성자는 반드시 입력되어야 합니다.")
    @NotBlank(message = "작성자는 공백일 수 없습니다.")
    private String writer;

    /* Swagger 문서화 시 설명 어노테이션 작성 */
    /* 필수 값이므로 유효성 검사 어노테이션 작성 */
    @NotNull(message = "작성날짜는 반드시 입력되어야 합니다.")
    @NotBlank(message = "작성날짜는 공백일 수 없습니다.")
    private String createAt;

}

