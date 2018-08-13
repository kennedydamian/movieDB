package com.damian.moviedb.data.db;

import android.arch.persistence.room.TypeConverter;

public class DBTypeConverters {

    @TypeConverter
    public static int[] stringToIntArray(String stringOfInts) {
        String[] arrayOfStrings = stringOfInts.split(", ");

        int[] convertedArray = new int[arrayOfStrings.length];

        for (int i=0; i<convertedArray.length; i++) {
            convertedArray[i] = Integer.parseInt(arrayOfStrings[i]);
        }
        return convertedArray;
    }

    @TypeConverter
    public static String intArrayToString(int[] arrayOfInts) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int value : arrayOfInts) {
            stringBuilder.append(value);
        }

        return stringBuilder.toString();
    }

}
