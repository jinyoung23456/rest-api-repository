package com.ohigraffers.practice.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/* Swagger 문서화 시 설명 어노테이션 작성 */
@Schema(description = "포스트 업데이트")
public class PostUpdateRequest {

    /* Swagger 문서화 시 설명 어노테이션 작성 */
    /* 필수 값이므로 유효성 검사 어노테이션 작성 */
    @Schema(description = "타이틀 업데이트")
    @NotNull(message = "타이틀은 반드시 입력되어야 합니다.")
    @NotBlank(message = "타이틀은 공백일 수 없습니다.")
    private String title;

    /* Swagger 문서화 시 설명 어노테이션 작성 */
    /* 필수 값이므로 유효성 검사 어노테이션 작성 */
    @Schema(description = "콘텐츠 업데이트")
    @NotNull(message = "콘텐츠는 반드시 입력되어야 합니다.")
    @NotBlank(message = "콘텐츠는 공백일 수 없습니다.")
    private String content;

}
