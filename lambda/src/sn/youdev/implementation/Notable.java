package sn.youdev.implementation;

public abstract class Notable {
    double note;

    public String valide(){

        if(note > 10){
            return "valide";
        }else{
            return "invalide";
        }
    }
}
