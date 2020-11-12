package woerterbuch;

public class Main {
    public static void main(String[] args) {
        WoerterBuch woerterbuch = WoerterBuch.create();

        boolean result = woerterbuch.check("Propra2");
        woerterbuch.save2("Propra2");
        woerterbuch.save2("Propra2");
        result = woerterbuch.check("Propra2");



    }
}
