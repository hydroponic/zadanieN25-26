package com.company;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try{
        Metro test=new Metro();
        test.getName();test.getLine();test.createJsonFile();test.JsonParser();}
        catch(IOException e) {e.printStackTrace();}
        catch(ParseException e) {e.printStackTrace();}
    }
}