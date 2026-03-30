package sn.youdev.app;


import java.util.Arrays;
import java.util.Comparator;

public class MainList {
    public static void main(String[] args){
        Integer [] tableau = {3,4,9,1,6,0,-9,10,13,2,34};
        Comparator<Integer> duPlusPetitAuPlusGrand = (a,b)->{
            boolean aespair = a%2==0;
            boolean bespair = b%2==0;
            boolean am3 = a%3==0;
            boolean bm3 = b%3==0;
            if(aespair && !bespair ){
                return -1;
            }else if(!aespair && bespair){
                return 1;
            }  else if(am3 && !bm3 ){
                return -1;
            }else if(!am3 && bm3){
                return 1;
            }
            return a-b;
        };
        Arrays.sort(tableau, duPlusPetitAuPlusGrand);
        for(int a : tableau){
            IO.print(a+" ");
        }

    }

    private static int verifierModulo(Integer a, int x, Integer b) {
        if (a % x == 0 && b % x == 0 || a % x == 1 && b % x == 1) {
            return 0;
        } else if (a % x == 1) {
            return 1;
        }
        return -1;
    }
}
