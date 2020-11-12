package bloomfilter.woerterbuch;

import bloomfilter.bitfeld.BitFeld;
import bloomfilter.bitfeld.I_Bitfeld;
import bloomfilter.hashberechnung.HashBerechnung;
import bloomfilter.hashberechnung.I_Hashberechnung;
// hat die funktionalität
public class WoerterBuch {
    private I_Bitfeld iBitfeld;
    private I_Hashberechnung iHashberechnung;

    public WoerterBuch (I_Bitfeld iBitfeld, I_Hashberechnung iHashberechnung){
        this.iBitfeld = iBitfeld;
        this.iHashberechnung = iHashberechnung;
    }
    //hilfe methode
    public static WoerterBuch create(int n, int m){
        BitFeld bitfeld = new BitFeld(n);
        HashBerechnung hashberechnung = new HashBerechnung(n, m);
        return new WoerterBuch(bitfeld, hashberechnung);
    }

    public void save2(String wort){
        int[] hashWerte = iHashberechnung.hasberechnung(wort); // nimmt das wort und hash rechenen und dann macht sie in bitfeld
        iBitfeld.save(hashWerte); // hashwert speichern
    }

    public boolean check(String wort){
        int[] hashWerte = iHashberechnung.hasberechnung(wort);// hash rechnen und jeder byte muss in bitfeld sein
        return iBitfeld.search(hashWerte);
    }
}
