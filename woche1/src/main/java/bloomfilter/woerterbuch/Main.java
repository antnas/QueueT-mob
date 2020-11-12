package bloomfilter.woerterbuch;

public class Main {
    public static void main(String[] args) {
        /*WoerterBuch woerterbuch = WoerterBuch.create();

        boolean result = woerterbuch.check("Propra2");
        woerterbuch.save2("Propra2");
        woerterbuch.save2("Propra2");
        result = woerterbuch.check("Propra2");
        */
        int n = 2;
        int m = 3;

        int[] hashBytes = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        int[] hashWerte = new int[m];

        for(int i = 0; i < m; i++) {
            for(int j = i*n ; j < i*n + n ; j++) {
                hashWerte[i] += hashBytes[j];
            }
            System.out.println(hashWerte[i]);
        }

    }
}
