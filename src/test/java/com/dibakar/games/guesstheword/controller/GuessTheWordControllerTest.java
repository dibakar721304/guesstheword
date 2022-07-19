package com.dibakar.games.guesstheword.controller;

import com.dibakar.games.guesstheword.model.GuessTheWordGame;
import com.dibakar.games.guesstheword.service.GuessTheWordServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;

@RunWith(SpringRunner.class)
public class GuessTheWordControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    GuessTheWordController guessTheWordController;
    @Mock
    GuessTheWordGame guessTheWordGame;
    @Mock
    GuessTheWordServiceImpl guessTheWordService;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(guessTheWordController)
                .build();
    }
    @Test
    public void testCreateGame() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/guesstheword/createNewGame"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void testPlayGame() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/guesstheword/playGame/l"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
