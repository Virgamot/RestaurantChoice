package ru.graduation.web;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;


class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testRoot() throws Exception {
        mockMvc.perform(get("/root"))
                .andDo(print())
                .andExpect(forwardedUrl("/WEB-INF/root.jsp"));
    }

}