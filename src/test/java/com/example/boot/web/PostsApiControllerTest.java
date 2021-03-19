package com.example.boot.web;

import com.example.boot.domain.posts.Posts;
import com.example.boot.domain.posts.PostsRepository;
import com.example.boot.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown()throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void PostsAPI호출하면_등록됨() throws Exception {
        //given
        String title = "title";
        String content = "content";
        String author = "author";
        PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder().title(title).content(content).author(author).build();
        String url ="http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, postsSaveRequestDto, Long.class);

        //then
        List<Posts> allPosts = postsRepository.findAll();
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(allPosts.get(0).getTitle(), title);
        Assertions.assertEquals(allPosts.get(0).getAuthor(), author);
        Assertions.assertEquals(allPosts.get(0).getContent(), content);

    }
}