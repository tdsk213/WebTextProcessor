package com.simbirsoft.test;

import java.io.*;
import java.util.Scanner;


public class WebTextProcessor {

    public static void main(String[] args) {
        //  regex = "[\\p{L}]+-[\\p{L}]+|[\\p{L}]+";
        char[] splitSymbols = {' ', ',', '.', '!', '?','"', ';', ':', '[', ']', '(', ')', '\n', '\r', '\t'};
//        String url ="https://wololo.net";
//        HTMLParser.printStats(url, splitSymbols);
        Scanner sc = new Scanner(System.in);
        printHelloMessage();
        String enteredString = sc.nextLine();

        while (!"q".equalsIgnoreCase(enteredString.trim())) {
            try {
                HTMLParser.printStats(enteredString, splitSymbols);
            } catch (Exception e) {
                System.out.println("Error: Malformed URL!");
            }
            printHelloMessage();
            enteredString = sc.nextLine();
        }
        sc.close();
    }

    public static void printHelloMessage() {
        System.out.print("Enter correct URL or enter \"q\" to close program: ");
    }

}

