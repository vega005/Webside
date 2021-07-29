package com.example.Projekt;

import com.example.Projekt.GameItem;
import com.example.Projekt.HelloController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private HelloController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(controller).isNotNull(); //sprawdzamy czy kontroler został utworzony i wstrzyknięty do testu
    }

    @Test
    public void findAll_ShouldAddTodoEntriesToModelAndRenderTodoListView() throws Exception {

        MvcResult result = mockMvc.perform(get("/details/1")) // symulujemy wywołanie akcji wejścia w szczegóły gry
                .andExpect(status().isOk()) //sprawdzamy czy status wywołania jest ok, czyli kod http = 200
                .andExpect(view().name("details")) // sprawdzamy czy dostalismy przekierowanie na strone "details"
                .andExpect(model().attribute("game", notNullValue()))// sprawdzamy czy do modelu został dodany obiekt gry "game"
                .andReturn();

        Optional<GameItem> game = (Optional<GameItem>) result.getModelAndView().getModel().get("game");
        Assertions.assertThat(game.get().getId()).isEqualTo(1L); // sprawdzamy czy nasz obiekt gry zwrócony w model na pewno ma Id = 1
    }
}