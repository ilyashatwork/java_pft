package ru.stqa.pft.sandbox;

import java.util.Scanner;

public class MyFirstProgram {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Point p = new Point(1, 1, 5, 5);

        System.out.println("Расстояние между первой и второй точками = " + p.distance());
    }
}