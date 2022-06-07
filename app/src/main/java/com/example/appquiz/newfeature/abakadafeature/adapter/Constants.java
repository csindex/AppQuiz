package com.example.appquiz.newfeature.abakadafeature.adapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
    public static String[] vowelsList = {"a","e","i","o","u"};
    public static String[] consonantsList = {"b","k","d","g","h","l","m","n","ng","p","r","s","t","w","y"};
    public static List<String> getAbacada(){
        List<String> abakadaList = new ArrayList<>(Arrays.asList(vowelsList));
        for (String s : consonantsList) {
            for (String value : vowelsList) {
                abakadaList.add(s + value);
            }
        }
        return abakadaList;
    }
}
