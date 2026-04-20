package sn.youdev.app;


import java.util.*;

public class ExamMain {
    public static void main(String[] args) {

        List<Double> list = List.of(12.0, 13.0, 14.0, 17.0, 7.0, 8.0);

        double average = list.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        System.out.println("Average: " + average);



    }
}
