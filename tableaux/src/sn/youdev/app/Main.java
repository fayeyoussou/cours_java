package sn.youdev.app;

import sn.youdev.model.Etudiant;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Integer[] ages = {12,3,10,20,31,22,5,12,9,7,3};
        for(int i=0;i<ages.length;i+=2){
            IO.println(ages[i]);
        }
        Comparator<Integer>  plusPetit = (g,d)->{
            if(g%2==0 && d%2==0 || g%2==1 && d%2==1){
                return g -d;
            } else if(g%2==0){
                return  -1;
            }
            return 1;
        };
        IO.println("==================");
        Arrays.sort(ages,plusPetit);
        for(int age : ages){
            IO.print(age+" ");
        }

    }
}