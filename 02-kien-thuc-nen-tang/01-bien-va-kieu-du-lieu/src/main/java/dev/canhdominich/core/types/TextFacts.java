package dev.canhdominich.core.types;

import java.text.Normalizer;

public class TextFacts {

    public static void main(String[] args) {
        String smile = "😀";
        String composed = "ế";
        String decomposed = Normalizer.normalize(composed, Normalizer.Form.NFD);
        System.out.println("char holds 16 bits: (int) 'A'      = " + (int) 'A' + ", 'A' + 1 = " + (char) ('A' + 1) + ", 'A' + 1 as int = " + ('A' + 1));
        System.out.println("smiley length()                   = " + smile.length() + ", code points = " + smile.codePointCount(0, smile.length()));
        System.out.println("composed e-circumflex-acute       = length " + composed.length() + ", same text decomposed = length " + decomposed.length());
        System.out.println("composed equals decomposed        = " + composed.equals(decomposed));
        System.out.println("normalized equal                  = " + composed.equals(Normalizer.normalize(decomposed, Normalizer.Form.NFC)));
        String name = "Nguyễn";
        System.out.println("\"Nguyen\" with tilde: length()      = " + name.length());
        System.out.println("'5' - '0'                         = " + ('5' - '0'));
        System.out.println("(char) 97                         = " + (char) 97);
    }
}
