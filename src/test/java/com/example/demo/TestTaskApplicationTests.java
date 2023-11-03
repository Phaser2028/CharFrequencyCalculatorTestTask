package com.example.demo;

import com.example.demo.service.CalculationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestTaskApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CalculationService calculationService;

    @Test
    public void testPostRequest() throws Exception {
        String inputText = "aaaaabcccc";
        String expectedResponse = "{\"a\":5,\"c\":4,\"b\":1}";

        mvc.perform(MockMvcRequestBuilders.post("/calc")
                        .content(inputText)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void testSimpleExpectedCalculate() {
        String inputText = "aaaaabcccc";
        Map<Character, Integer> expectedResult = Map.of('a', 5, 'c', 4, 'b', 1);

        Map<Character, Integer> result = calculationService.calculate(inputText);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testExpectedCalculate() {
        String inputText = "hhheeeeeello";
        Map<Character, Integer> expectedResult = Map.of('e', 6, 'h', 3, 'l',2,'o',1);

        Map<Character, Integer> result = calculationService.calculate(inputText);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testUnexpectedCalculate() {
        String inputText = "aaaaabcccc";
        Map<Character, Integer> expectedResult = Map.of('a', 1, 'c', 4, 'b', 1);

        Map<Character, Integer> result = calculationService.calculate(inputText);

        assertNotEquals(expectedResult, result);
    }

    @Test
    public void testEmptyInput() {
        String inputText = "";
        Map<Character, Integer> expectedResult = Collections.emptyMap();

        Map<Character, Integer> result = calculationService.calculate(inputText);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testSpecialCharactersInput() {
        String inputText = "!@#$$$$%^&*";
        Map<Character, Integer> expectedResult = Map.of('$', 4,'!', 1, '@', 1, '#', 1,  '%', 1, '^', 1, '&', 1, '*', 1);

        Map<Character, Integer> result = calculationService.calculate(inputText);

        assertEquals(expectedResult, result);
    }


}
