package sn.youdev.java.avance.app;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        List<Integer> listentier = List.of(-4,8,10,12,90,120,2,-20,3);
        var minimum = listentier.stream()
                        .collect(Collectors.groupingBy((x)->{
                            if(x%2!=0){
                                return "IMPAIRE";
                            }else {
                                return "PAIRE";
                            }
                        }));
        minimum.forEach((x,y)->{
            System.out.println(x);
            IO.println("Valeur");
            y.forEach(System.out::println);
        });
    }
}
