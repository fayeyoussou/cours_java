package sn.youdev.app;

import sn.youdev.exception.EtudiantInexistantException;

public class MainMontrer {
    public static void jeLanceUneException() throws EtudiantInexistantException {
        throw new EtudiantInexistantException(9);
    }

    public static void main(String[] args)  {
        try {
            jeLanceUneException();
        }catch (EtudiantInexistantException e){
        }
        IO.println("Apres cela");
    }
}
