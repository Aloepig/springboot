package com.example.boot.domain.posts;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void Cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장후_불러오기(){
        //given
        String title = "테스트 글";
        String content = "테스트 본문";
        postsRepository.save(Posts.builder().title(title).content(content).author("woo@myalley.co.kr").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        Assertions.assertEquals(posts.getTitle(),title);
        Assertions.assertEquals(posts.getContent(), content);

    }

    @Test
    public void BaseTimeEntity등록된다(){
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2020,12,1,0,0,0);
        postsRepository.save(Posts.builder().title("title").author("author").content("content").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        System.out.println("createdDate" + posts.getCreateedDate());
        System.out.println("modifiedDate" + posts.getModifiedDate());

    }

}