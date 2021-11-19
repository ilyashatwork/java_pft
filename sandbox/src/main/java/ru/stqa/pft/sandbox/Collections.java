package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        /*String[] languages = {"Java", "Python", "C++", "Brainfuck"}; // массив строк
        for (String indexLanguages : languages) {
            System.out.println(indexLanguages);
        }*/

        List<String> languagesList = new ArrayList<String>();
        languagesList.add("Java");
        languagesList.add("Python");
        languagesList.add("C++");
        languagesList.add("Brainfuck");

        for (String indexLanguages : languagesList) {
            System.out.println(indexLanguages);
        }
    }

}
