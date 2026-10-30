package com.example.pd2;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TextMetricsTest {

    private static final String SAMPLE_TEXT = "Labas, pasaule. Man 25 metai.";

    private TextMetrics textMetrics;

    @Before
    public void setUp() {
        textMetrics = new TextMetrics();
    }

    @Test
    public void countSentences_returnsTwo() {
        assertEquals(2, textMetrics.countSentences(SAMPLE_TEXT));
    }

    @Test
    public void countWords_returnsFour() {
        assertEquals(4, textMetrics.countWords(SAMPLE_TEXT));
    }

    @Test
    public void countPunctuation_returnsSeven() {
        assertEquals(7, textMetrics.countPunctuation(SAMPLE_TEXT));
    }

    @Test
    public void countNumbers_returnsOne() {
        assertEquals(1, textMetrics.countNumbers(SAMPLE_TEXT));
    }

    @Test
    public void emptyText_returnsZeroForAllMetrics() {
        assertEquals(0, textMetrics.countSentences(""));
        assertEquals(0, textMetrics.countWords(""));
        assertEquals(0, textMetrics.countPunctuation(""));
        assertEquals(0, textMetrics.countNumbers(""));
    }

    @Test
    public void textWithoutDot_countsAsOneSentence() {
        assertEquals(1, textMetrics.countSentences("Labas rytas"));
    }

    @Test
    public void onlyNumbers_hasNoWords() {
        assertEquals(0, textMetrics.countWords("123 45"));
        assertEquals(2, textMetrics.countNumbers("123 45"));
    }

    @Test
    public void lithuanianLetters_areCountedAsWord() {
        assertEquals(1, textMetrics.countWords("Žodžiai"));
    }
}