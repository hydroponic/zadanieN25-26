package com.company;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.parser.Parser;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Metro {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    ArrayList<String> lines=new ArrayList<>();
    ArrayList<String> linesnum= new ArrayList<>();
    ArrayList<String> stations=new ArrayList<>();
    ArrayList<Lines> linesss=new ArrayList<>();
    ArrayList<String> stationsnum=new ArrayList<>();
    ArrayList<Stations> stationss=new ArrayList<>();
    private static List<Lines> lines1 = new LinkedList<>();
    private static Map<String, List<String>> stations1 = new TreeMap<>();

    final String URL="https://www.moscowmap.ru/metro.html#lines";
    public void getName() {
        try {
            Document doc = Jsoup.connect(URL).maxBodySize(0).userAgent("Chrome/4.0.249.0 Safari/532.5").referrer("https://yandex.ru").get();
            String[] name=doc.select("span.name").toString().split("\n");
            String[] num=new String[name.length];
            for (int i=0;i<name.length;i++)
            {
                name[i]=name[i].substring(name[i].indexOf(">")+1,name[i].lastIndexOf("<"));
                if (i<=26) {num[i]="1";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=50) {num[i]="2";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=72) {num[i]="3";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=85) {num[i]="4";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=97) {num[i]="5";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=121) {num[i]="6";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=144) {num[i]="7";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=164) {num[i]="8";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=189) {num[i]="9";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=212) {num[i]="10";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=220) {num[i]="11";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=223) {num[i]="11A";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=230) {num[i]="12";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=272) {num[i]="14";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=2297) {num[i]="D1";stations.add(name[i]);stationsnum.add(num[i]);continue;}
                if (i<=332) {num[i]="D2";stations.add(name[i]);stationsnum.add(num[i]);continue;}

            }

        for (int i=0;i<name.length;i++) {
            if (!stations1.containsKey(num[i])) {
                stations1.put(num[i], new ArrayList<>());
                stations1.get(num[i]).add(name[i]);}
            else stations1.get(num[i]).add(name[i]);
               }
        }
        catch (IOException e) {e.printStackTrace();}
    }
    public String[][] getLine() {
        try {
            Document doc = Jsoup.connect(URL).maxBodySize(0).userAgent("Chrome/4.0.249.0 Safari/532.5").referrer("http://www.google.com").get();
            String[] name=doc.select("div#metrodata span:nth-child(n+1)").toString().split("\n");
            int j=0;
            for (int i=0;i<name.length;i++)
                if (name[i].charAt(13)!='n')
                { lines.add(name[i].substring(name[i].indexOf(">")+1,name[i].lastIndexOf(" <")));j++; }
            name=doc.select("div#metrodata div:nth-child(n)").toString().split("\n");
                for (Element el:doc.select("div#metrodata div:nth-child(n)"))
                    if (el.attr("data-depend-set")!="")
                    {name=el.attr("data-depend-set").toString().split("\n");
                    int l=0;
                      String num= String.join("-",name);
                    name=num.split("-");
                    for (int f=0;f<name.length;f++)
                        if (f%2!=0)
                        {linesnum.add(name[f]);}}
                    for (int h=0;h<lines.size();h++)
                    {
                        Lines liness=new Lines(linesnum.get(h),lines.get(h));
                        lines1.add(liness);
                    }
        }
        catch (IOException e) {e.printStackTrace();}
        return null;
    }

    static void createJsonFile() throws IOException {

        Underground metro = new Underground(Metro.lines1, Metro.stations1);
        try (FileWriter file = new FileWriter("task.json")) {
            file.write(GSON.toJson(metro));
        }
    }
    static String parseFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> sb.append(line).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    static void JsonParser() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(parseFile("task.json"));

        Map<String, List<String>> stations = (Map<String, List<String>>) jsonObject.get("stations");
        for (String lineId : stations.keySet()) {
            JSONArray stationsArray = (JSONArray) stations.get(lineId);
            for (Lines line : new Underground(Metro.lines1, Metro.stations1).getLines()) {
                if (line.getId().equals(lineId)) {
                    System.out.println("Линия " + lineId +", количество станций: " + stationsArray.size());

                }
            }
        }
    }
}



