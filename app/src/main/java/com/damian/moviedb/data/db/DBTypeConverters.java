package com.damian.moviedb.data.db;

import android.arch.persistence.room.TypeConverter;

public class DBTypeConverters {

    @TypeConverter
    public static int[] stringToIntArray(String stringOfInts) {
        if (stringOfInts==null || stringOfInts.isEmpty()) {
            return new int [] {-1};
        }
        String[] arrayOfStrings = stringOfInts.split(",");
        int[] convertedArray = new int[arrayOfStrings.length];

        for (int i=0; i<convertedArray.length; i++) {
            convertedArray[i] = Integer.parseInt(arrayOfStrings[i]);
        }
        return convertedArray;
    }

    @TypeConverter
    public static String intArrayToString(int[] arrayOfInts) {
        if (arrayOfInts.length==0 || (arrayOfInts.length==1 && arrayOfInts[0]==-1)) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int value : arrayOfInts) {
            stringBuilder.append(value);
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

}