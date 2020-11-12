package bloomfilter.bitfeld;

public class BitFeld implements I_Bitfeld {
    private final boolean[] bitFeld;

    public BitFeld(int n) {
        bitFeld = new boolean[(int) Math.pow(256, n)];
    }

    // HALLO!

    //Hallo von Garo

    //sucht jeder Byte 0-255 = 256 bit
    //blommfilter 20 byte , 1 vorhanden , dann true
    // hashwerte = 20 byte von sha1
    @Override
    public void save(int[] hashwerte) {
        for(int i = 0 ; i < hashwerte.length  ; i++) {
            bitFeld[hashwerte[i]] = true;
        }
        //if(vorhanden) istVorhanden();
    }
// sucht jeder byte in bitfeld
    @Override
    public boolean search(int[] hashWert) {
        for(int i = 0 ; i < hashWert.length; i++){
            if(!bitFeld[hashWert[i]]){
                System.out.println("Das Wort steht nicht im WörterBuch");
                return false;
            }
        }
        System.out.println("Das Wort steht im Wörterbuch");
        return true;
    }

}


