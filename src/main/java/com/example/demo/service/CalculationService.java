package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalculationService {

    public Map<Character, Integer> calculate(String inputText) {

        Map<Character, Integer> data = new TreeMap<>();

        for (int i = 0; i < inputText.length(); i++) {
            char c = inputText.charAt(i);
            int count = data.getOrDefault(c, 0);
            data.put(c, count + 1);
        }


        List<Map.Entry<Character, Integer>> list = new ArrayList<>(data.entrySet());

        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Map<Character, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;


    }
}
