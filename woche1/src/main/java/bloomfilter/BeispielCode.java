package bloomfilter;

import org.apache.commons.codec.digest.DigestUtils;

public class BeispielCode {

    public static void main(String[] args) {
        byte[] bytes = DigestUtils.sha1("praktikum");

        byte b = (byte) 200;
        System.out.println(b);
        int unsignedB = b;
        System.out.println(unsignedB);
        unsignedB = unsignedB & 0xFF;
        System.out.println(unsignedB);


    }


}

