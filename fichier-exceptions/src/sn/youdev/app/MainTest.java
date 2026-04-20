package sn.youdev.app;

public class MainTest {
    public static void main(String[] args){
        try {
            int a = 0;
            int b = 2;
            int c = b / a;
            IO.println("apres la division");
        } catch (ArithmeticException e) {
            IO.println(e.getMessage());
        } finally {
            IO.println("j'ai bien tout nettoye");
        }
        IO.println("Hello World!");
    }
}
