package com.example.pd2;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String SAMPLE_TEXT = "Labas, pasauli.";
    private static final int POSITION_SENTENCES = 0;
    private static final int POSITION_WORDS = 1;
    private static final int POSITION_PUNCTUATION = 2;
    private static final int POSITION_NUMBERS = 3;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    private void selectMetric(int position) {
        onView(withId(R.id.spinnerMetric)).perform(click());
        onData(anything()).atPosition(position).perform(click());
    }

    private String getExpectedResult(int metricNameResId, int value) {
        Context context = ApplicationProvider.getApplicationContext();
        return context.getString(R.string.result_format,
                context.getString(metricNameResId), value);
    }

    private void typeSampleText() {
        onView(withId(R.id.etInputText))
                .perform(typeText(SAMPLE_TEXT), closeSoftKeyboard());
    }

    @Test
    public void calculateSentences_showsCorrectResult() {
        typeSampleText();
        selectMetric(POSITION_SENTENCES);
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(
                withText(getExpectedResult(R.string.metric_sentences, 2))));
    }

    @Test
    public void calculateWords_showsCorrectResult() {
        typeSampleText();
        selectMetric(POSITION_WORDS);
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(
                withText(getExpectedResult(R.string.metric_words, 4))));
    }

    @Test
    public void calculatePunctuation_showsCorrectResult() {
        typeSampleText();
        selectMetric(POSITION_PUNCTUATION);
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(
                withText(getExpectedResult(R.string.metric_punctuation, 7))));
    }

    @Test
    public void calculateNumbers_showsCorrectResult() {
        typeSampleText();
        selectMetric(POSITION_NUMBERS);
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(
                withText(getExpectedResult(R.string.metric_numbers, 1))));
    }

    @Test
    public void emptyInput_leavesResultEmpty() {
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("")));
    }
}