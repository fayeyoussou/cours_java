package sn.youdev.app;

public class TableauxUtils {
    public static <T> int indexOf(T[] tab, T cible){
        if(tab == null){
            throw new NullPointerException("Le tableau ne peut etre null");
        }
        for(int i=0;i<tab.length;i++){
            if(cible == null && tab[i] == null || tab[i] !=null && tab[i].equals(cible)){
                return i;
            }
        }
        return -1;
    }
}
