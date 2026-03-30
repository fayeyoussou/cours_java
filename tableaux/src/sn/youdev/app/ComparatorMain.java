package sn.youdev.app;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorMain {
    public static void main(String[] args) {
        Comparator<Integer> duPlusPetitAuPlus = (a,b)->{
            return a-b;
        };
        Comparator<Integer> paireEnPremier = (a,b)->{
            return comparerAvecModulo(a, 2, b);
        };
        Comparator<Integer> mtroisPremier = (a,b)->{
            return comparerAvecModulo(a, 3, b);
        };
        Integer [] tableau = {3,4,1,9,0,-15,12,1,6,0,-9,10,13,2,34,6,1};
        Arrays.sort(tableau,mtroisPremier);
        for(Integer i : tableau){
            IO.print(i+" ");
        }
    }

    private static int comparerAvecModulo(Integer a, int x, Integer b) {
        if (a % x == 0 && b % x != 0) {
            return -1;
        } else if (a % x != 0 && b % x == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
