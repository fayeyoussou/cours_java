package sn.youdev.app;

import java.sql.Array;
import java.util.Arrays;
import java.util.Comparator;

public class MainCompare {
    public static void main(String[] args) {
        Comparator<Integer> paireEnPremier = (g,d)->{
            if(g%2==0 && d%2!=0) return -1;
            if(g%2!=0 && d%2==0) return 1;
            return 0;
        };
        Integer[] tab = {7,8,0,12};
        Arrays.sort(tab);
        for (Integer i:tab) {
            System.out.println(i);
        }
    }
}
