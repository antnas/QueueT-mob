package bloomfilter.bitfeld;

public interface I_Bitfeld {
    void save(int[] hashwerte);
    boolean search(int[] hashWert);
}
