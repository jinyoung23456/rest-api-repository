package com.ohigraffers.practice.post.controller;

import com.ohigraffers.practice.post.dto.request.PostCreateRequest;
import com.ohigraffers.practice.post.dto.request.PostUpdateRequest;
import com.ohigraffers.practice.post.dto.response.PostResponse;
import com.ohigraffers.practice.post.dto.response.ResponseMessage;
import com.ohigraffers.practice.post.model.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/* Swagger 문서화 시 Grouping 작성 */

@Tag(name = "post 관련 api")
@RestController
@RequestMapping("/posts")
public class PostController {

    private List<Post> posts;

    public PostController(){
        posts = new ArrayList<>();
        posts.add(new Post(1L, "제목1", "내용1", "홍길동"));
        posts.add(new Post(2L, "제목2", "내용2", "유관순"));
        posts.add(new Post(3L, "제목3", "내용3", "신사임당"));
        posts.add(new Post(4L, "제목4", "내용4", "이순신"));
        posts.add(new Post(5L, "제목5", "내용5", "장보고"));
    }

    /* 1. 전체 포스트 조회 */
    /* Swagger 문서화 시 설명 어노테이션 작성 */
    /* RequestMapping 어노테이션 작성 */
    @Operation(summary = "전체 포스트 조회")
    @GetMapping("/allPosts")
    public ResponseEntity<ResponseMessage> findAllPosts() {

        /* 응답 데이터 설정 */
        /* Post 타입은 PostResponse 타입으로 변환해서 반환 */
        /* hateoas 적용 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<EntityModel<Post>> postWithRel = posts.stream().map(
                post ->
                        EntityModel.of(
                                post,
                                linkTo(methodOn(PostController.class).findPostByCode(post.getCode())).withSelfRel(),
                                linkTo(methodOn(PostController.class).findAllPosts()).withRel("allPosts")
                                )
        ).toList();


        /* ResponseEntity 반환 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("posts", postWithRel);
        ResponseMessage responseMessage = new ResponseMessage(200, "포스트 조회 성공", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);

    }

    /* 2. 특정 코드로 포스트 조회 */
    /* Swagger 문서화 시 설명 어노테이션 작성 */
    /* RequestMapping 어노테이션 작성 */
    @Operation(summary = "특정 코드 포스트 조회")
    @GetMapping("/findPosts/{postCode}")
    public ResponseEntity<ResponseMessage> findPostByCode(/* 필요 매개변수 선언 */@PathVariable Long postCode) {

        /* 응답 데이터 설정 */
        /* Post 타입은 PostResponse 타입으로 변환해서 반환 */
        /* hateoas 적용 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        Post foundPost = posts.stream().filter(post -> post.getCode() == postCode).toList().get(0);

        /* ResponseEntity 반환 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("foundPost", foundPost);
        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", responseMap);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(responseMessage);
    }

    /* 3. 신규 포스트 등록 */
    /* Swagger 문서화 시 설명 어노테이션 작성 */
    /* RequestMapping 어노테이션 작성 */
    @Operation(summary = "신규 포스트 등록", description = "신규 포스트를 등록한다")
    @PostMapping("/allPosts")
   public ResponseEntity<Void> registPost(/* 필요 매개변수 선언, 유효성 검사 */@RequestBody @Valid PostCreateRequest postCreateRequest) {

       /* 리스트에 추가 */

        long lastPostCode = posts.get(posts.size() - 1).getCode();
        Post newPost = new Post(lastPostCode + 1, postCreateRequest.getTitle(), postCreateRequest.getContent(), postCreateRequest.getWriter());
        posts.add(newPost);

       /* ResponseEntity 반환 */
       return ResponseEntity
               .created(URI.create("/entity/allPosts/" + posts.get(posts.size() - 1).getCode()))
               .build();
   }

   /* 4. 포스트 제목과 내용 수정 */
   /* Swagger 문서화 시 설명 어노테이션 작성 */
   /* RequestMapping 어노테이션 작성 */
    @Operation(summary = "기존 포스트 수정")
    @PutMapping("/findPosts/{postCode}")
    public ResponseEntity<Void> modifyPost(/* 필요 매개변수 선언, 유효성 검사 */@PathVariable long postCode , @RequestBody PostUpdateRequest updateRequest) {

        /* 리스트에서 찾아서 수정 */
        Post foundPost = posts.stream().filter(post -> post.getCode() == postCode).toList().get(0);
        /* 수정 메소드 활용 */
        Post updatePost = new Post(
                foundPost.getCode(),
                updateRequest.getTitle(),
                updateRequest.getContent(),
                foundPost.getWriter()
        );

        posts.remove(foundPost);
        posts.add(updatePost);

        /* ResponseEntity 반환 */
        return ResponseEntity
                .created(URI.create("/entity/posts/" + updatePost.getCode()))
                .build();
    }

    /* 5. 포스트 삭제 */
    /* Swagger 문서화 시 설명 어노테이션 작성 */
    /* RequestMapping 어노테이션 작성 */
    @Operation(summary = "포스트 삭제")
    @DeleteMapping("/findPosts/{postCode}")
    public ResponseEntity<Void> removeUser(/* 필요 매개변수 선언 */@PathVariable long postCode) {

        /* 리스트에서 찾아서 삭제 */
        Post foundPost = posts.stream().filter(post -> post.getCode() == postCode).toList().get(0);
        posts.remove(foundPost);
        /* ResponseEntity 반환 */
        return ResponseEntity
                .noContent()
                .build();
    }

}
