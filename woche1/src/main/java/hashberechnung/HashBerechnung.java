package hashberechnung;

import org.apache.commons.codec.digest.DigestUtils;

public class HashBerechnung implements I_Hashberechnung {
// nimmt das wort und rechnet ihr hashwert
    // jeder byte von hashwert
    @Override
    public int[] hasberechnung(String wort) {
        byte[] bytes = DigestUtils.sha1(wort);
// wird als integer wert umgewandelt, weil intger array einfacher als byte[]
        int[] hashWerte = new int[20];
        // nimmt byte byte und schreibt in int[]
        // in dieser for wird byte array in integer array
        for(int i=0; i<20; i++){
            int unsigend = bytes[i];
            unsigend = unsigend & 0xFF; // garniterit in positiv umwandeln
            hashWerte[i] = unsigend; // Werte werden in HashWWerte gespeichert in integer array.
        }

        return hashWerte;
    }
}
