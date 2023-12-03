package com.bookatop.country.trans;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class TransKey {

    private static String decodeText(String input, String encoding) throws IOException {
        return
                new BufferedReader(
                        new InputStreamReader(
                                new ByteArrayInputStream(input.getBytes()),
                                Charset.forName(encoding)))
                        .readLine();
    }

    public static String transToKey(String value) throws Exception {

        String encodedRussian = decodeText("абвгдеёжзийклмнопрстуфхцчшщъыьэюя -().'?,0123456789abcdefghijklmnopqrstuvwxyzôéãí", "UTF-8");
        String[] translateRussian = {"a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "y", "k", "l", "m",
                "n", "o", "p", "r", "s", "t", "u", "f", "kh", "ts", "ch", "sh", "sh", "", "y", "", "e", "yu", "ya",
                "_", "_", /* -*/ "" /*(*/, "" /*)*/, "" /*.*/, "" /*'*/, "" /*?*/, "" /*,*/,
                "", "", "", "", "", "", "", "", "", "",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
                "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "v" /*ô*/, "e" /*é*/, "a" /*ã*/, "i"/*í*/};

        if (encodedRussian.length() != translateRussian.length)
            throw new Exception("Different size");

        StringBuilder strBuffer = new StringBuilder();

        for (char c : value.toLowerCase().toCharArray()) {
            int index = encodedRussian.indexOf(c);
            if (index != -1) {
                strBuffer.append(translateRussian[index]);
            } else {
                throw new Exception("Unknown Character " + c);
            }

        }

        return strBuffer.toString();
    }

}
