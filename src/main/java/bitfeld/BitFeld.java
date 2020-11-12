package bitfeld;

public class BitFeld implements I_Bitfeld {
    int [] bitFeld = new int[65536];
    public boolean vorhanden ;


    //sucht jeder Byte 0-255 = 256 bit
    //blommfilter 20 byte , 1 vorhanden , dann true
    // hashwerte = 20 byte von sha1
    @Override
    public void save(int[] hashwerte) {
        vorhanden = false;
        for(int i = 0 ; i < 20  ; i++) {
            vorhanden = false;
            if (bitFeld[hashwerte[i]] == 1) vorhanden = true;
            bitFeld[hashwerte[i]] = 1;
        }
        //if(vorhanden) istVorhanden();
    }
// sucht jeder byte in bitfeld
    @Override
    public boolean search(int[] hashWert) {
        for(int i = 0 ; i<20 ; i++){
            if(bitFeld[hashWert[i]] == 0){
                System.out.println("Das Wort steht nicht im WörterBuch");
                return false;
            }
        }
        System.out.println("Das Wort steht im Wörterbuch");
        return true;
    }

}


