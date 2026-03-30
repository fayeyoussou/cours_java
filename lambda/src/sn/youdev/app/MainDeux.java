package sn.youdev.app;

import sn.youdev.interfaces.*;

import java.util.function.Consumer;
import java.util.function.Function;

public class MainDeux {
    static Double multiplierParDeux(Double a){
        return a*2;
    }
    public static void main(String[] args) {
        ActionMessage afficher = IO::println;
        TraiterChiffer traiterChiffer = MainDeux::multiplierParDeux;
        Function<String, Integer> f = Integer::parseInt;
        Function<Integer,Personne> toPersonne = Personne::new;
        Function<String,Personne> stringToPersonne = f.andThen(toPersonne);

        Personne personne = stringToPersonne.apply("890");
        System.out.println(personne);


    }
}
