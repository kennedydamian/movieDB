package com.damian.moviedb.data.db;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DBTypeConvertersTest {

    @Test
    public void test_stringToIntArray_nullString() {
        String nullString = null;
        int[] result = DBTypeConverters.stringToIntArray(nullString);
        int[] expected = {-1};

        assertArrayEquals(expected, result);
    }

    @Test
    public void test_stringToIntArray_emptyString() {
        String emptyString = "";
        int[] result = DBTypeConverters.stringToIntArray(emptyString);
        int[] expected = {-1};

        assertArrayEquals(expected, result);
    }

    @Test
    public void test_stringToIntArray_validInput() {
        String stringOfInts = "1,2,3,4,5";
        int[] result = DBTypeConverters.stringToIntArray(stringOfInts);
        int[] expected = {1,2,3,4,5};

        assertArrayEquals(expected, result);
    }

    @Test
    public void test_stringToIntArray_validInput_trailingComma() {
        String stringOfInts = "1,2,3,4,5,";
        int[] result = DBTypeConverters.stringToIntArray(stringOfInts);
        int[] expected = {1,2,3,4,5};

        assertArrayEquals(expected, result);
    }

    @Test
    public void test_intArrayToString_emptyArray() {
        int[] arrayOfInts = {};
        String result = DBTypeConverters.intArrayToString(arrayOfInts);
        String expected = "";

        assertEquals(expected, result);
    }

    @Test
    public void test_intArrayToString_negativeValue() {
        int[] arrayOfInts = {-1};
        String result = DBTypeConverters.intArrayToString(arrayOfInts);
        String expected = "";

        assertEquals(expected, result);
    }

    @Test
    public void test_intArrayToString_validInput() {
        int[] arrayOfInts = {1,2,3,4,5};
        String result = DBTypeConverters.intArrayToString(arrayOfInts);
        String expected = "1,2,3,4,5,";

        assertEquals(expected, result);
    }
}
