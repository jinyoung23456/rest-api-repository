package com.ohgiraffers.chap01restapi.section05.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "회원정보 DTO")
public class UserDTO {

    @Schema(description = "회원번호(PK)")
    private int no;
    @Schema(description = "회원ID")
    private String id;
    @Schema(description = "회원비번")
    private String pwd;
    @Schema(description = "회원이름")
    private String name;
    @Schema(description = "회원등록일")
    private Date enrollDate;
}
