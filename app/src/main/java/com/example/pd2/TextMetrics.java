package com.example.pd2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextMetrics {

    private static final String SENTENCE_SEPARATOR = "\\.";
    private static final Pattern WORD_PATTERN = Pattern.compile("\\p{L}+");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    public int CountSentences(String text) {
        int sentenceCount = 0;
        for (String part : text.split(SENTENCE_SEPARATOR)) {
            if (!part.trim().isEmpty()) {
                sentenceCount++;
            }
        }
        return sentenceCount;
    }

    public int CountWords(String text) {
        return CountMatches(WORD_PATTERN, text);
    }

    public int CountNumbers(String text) {
        return CountMatches(NUMBER_PATTERN, text);
    }

    // Skyrybos zenklai pagal uzduoti: tarpai, kableliai, taskai
    public int CountPunctuation(String text) {
        int punctuationCount = 0;
        for (char symbol : text.toCharArray()) {
            if (symbol == ' ' || symbol == ',' || symbol == '.') {
                punctuationCount++;
            }
        }
        return punctuationCount;
    }

    private int CountMatches(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        int matchCount = 0;
        while (matcher.find()) {
            matchCount++;
        }
        return matchCount;
    }
}