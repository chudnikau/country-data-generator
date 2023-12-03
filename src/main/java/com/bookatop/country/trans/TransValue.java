package com.bookatop.country.trans;

public class TransValue {

    public static String transToValue(String value) {
        String res = value;
        while (res.indexOf('_') != -1) {
            int index = res.indexOf('_');
            if (index < res.length() - 1) {
                char c = res.charAt(index + 1);
                String oldStr = res.substring(index, index + 2);
                res = res.replace(oldStr, " " + Character.toUpperCase(c));
            }
        }
        // Making first char in the work to upper
        return res.substring(0, 1).toUpperCase() + res.substring(1);
    }

}
