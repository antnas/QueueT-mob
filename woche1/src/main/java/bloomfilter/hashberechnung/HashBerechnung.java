package bloomfilter.hashberechnung;

import org.apache.commons.codec.digest.DigestUtils;

public class HashBerechnung implements I_Hashberechnung {
// nimmt das wort und rechnet ihr hashwert
    // jeder byte von hashwert

    private final int n;
    private final int m;

    public HashBerechnung(int n, int m) {
        this.n = n;
        this.m = m;
    }

    @Override
    public int[] hasberechnung(String wort) {
        byte[] bytes = DigestUtils.sha1(wort);
        int[] hashBytes = new int[20];

        for(int i=0; i<20; i++){
            int unsigend = bytes[i];
            unsigend = unsigend & 0xFF; // garniterit in positiv umwandeln
            hashBytes[i] = unsigend; // Werte werden in HashWWerte gespeichert in integer array.
        }

        int[] hashWerte = new int[m];
        for(int i = 0; i < m; i++) {
            for(int j = i*n ; j < i*n + n ; j++) {
                hashWerte[i] += hashBytes[j];
            }
            System.out.println(hashWerte[i]);
        }

        return hashWerte;
    }
}
