package sn.youdev.app;



public class MainStream {
    public static void main(String[] args) {
        String nomComplet = "Youssoupha";
        nomComplet+=" ";
        nomComplet+="FAYE";
        System.out.println(nomComplet);
        StringBuilder sb1 = new StringBuilder("Youssoupha");

        sb1.append(" ");
        sb1.append("FAYE");
        sb1.reverse();
        sb1.append(" Test");
        String nomFinal = sb1.toString();
        System.out.println(sb1);
    }
}
