package com.simbirsoft.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Pattern;


public class HTMLParser {

    public static void printStats(String url, char[] splitSymbols) throws IOException {
        printStats(url, RegexHelper.getPatternFromCharArray(splitSymbols));
    }

    public static void printStats(String url, Pattern splitPattern) throws IOException {

        Document doc = null;
            doc = Jsoup.connect(url).get();

        Map<String, Integer> wordCollection = new HashMap<>();
        collectWords(doc, splitPattern, wordCollection);
        wordCollection = sortByValue(wordCollection, true);
        printMap(wordCollection);
        FileHandler.saveStatsToFile(wordCollection, url);
    }

    private static void collectWords(Document doc, Pattern splitPattern, Map<String, Integer> words) {
        for (Element element : doc.getAllElements()) {
            String[] splintedString = splitPattern.split(element.ownText().toUpperCase());
            for (String key : splintedString) {
                if (key.isEmpty()) continue;
                words.merge(key, 1, Integer::sum);
            }
        }
    }

    private static void printMap(Map<String, Integer> words) throws UnsupportedEncodingException {
        PrintStream outStream = new PrintStream(System.out, true);
        words.forEach((key, value) -> outStream.println(key + ": " + value));
    }

    /**
     * Method sorts HashMap by values
     * returns new sorted HashMap
     * @param innerMap a HashMap which type parameters must be Comparable
     * @param isDescending if true it will be sorted by descending
     */
    private static <K extends Comparable<? super K>, V extends Comparable<? super V>> HashMap<K, V> sortByValue(Map<K, V> innerMap, boolean isDescending) {
        List<Map.Entry<K, V> > list = new LinkedList<>(innerMap.entrySet());

        if (isDescending) {
            list.sort((Map.Entry<K, V> o1, Map.Entry<K, V> o2) -> (o2.getValue()).compareTo(o1.getValue()));
        } else list.sort(Map.Entry.comparingByValue());

        HashMap<K, V> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<K, V> map : list) {
            sortedMap.put(map.getKey(), map.getValue());
        }
        return sortedMap;
    }
}
