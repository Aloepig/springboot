package com.example.boot.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@WebMvcTest
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello로_접속하면_Hello가_반환된다() throws Exception {
        String hello = "Hello";

        mvc.perform(MockMvcRequestBuilders.get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
    }

    @Test
    public void hello_dto로_접속하면_helloDto가_반환된다() throws  Exception {
        String name = "Hello";
        int amount = 1000;

        mvc.perform(MockMvcRequestBuilders.get("/hello/dto").param("name", name).param("amount", String.valueOf(amount))).andExpect(status().isOk()).andExpect(jsonPath("$.name", is(name))).andExpect(jsonPath("$.amount", is(amount)));
    }

}