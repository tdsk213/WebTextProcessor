package com.simbirsoft.test;

import java.util.regex.Pattern;

public class RegexHelper {

    private static final char[] SPECIAL_SYMBOLS = {' ', '\n', '\r', '\t', '[', ']'};

    public static Pattern getPatternFromCharArray(char[] splitSymbols) {

        Pattern pattern;

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (char splitSymbol : splitSymbols) {
            for (char specialSymbol : SPECIAL_SYMBOLS) {
                if (specialSymbol == splitSymbol) {
                    sb.append('\\');
                    break;
                }
            }
            sb.append(splitSymbol);
        }
        sb.append(']');

        pattern = Pattern.compile(sb.toString());

        return  pattern;
    }
}
