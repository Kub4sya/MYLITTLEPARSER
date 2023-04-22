package com.fuckingcheese;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonReader {
    private ArrayList<ReactorType> r = new ArrayList<>();

    public void readJson() {
        String date = getStringFile("data.json");
        Gson g = new Gson();
        //ReactorType r = g.
//        ReactorType reactor = g.fromJson(date,ReactorType.class);
//        System.out.println(reactor);
        Type type = new TypeToken<Map<String, ArrayList<ReactorType>>>() {
        }.getType();
        Map<String, ArrayList<ReactorType>> myMap = g.fromJson(date, type);
        r = myMap.get("params");
        System.out.println(r);
    }

  /*  public void readXML() {
//        String date = getStringFile("data.xml");
          Map<String, ArrayList<ReactorType>> myMap = new HashMap<>();
          ArrayList<ReactorType> r = new ArrayList<>();
        try {
            System.out.println("XML Parser using SAXParser Example");
            System.out.println();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            *//** defining the custom handler *//*
            DefaultHandler handler = new DefaultHandler() {

                HashMap<String, Boolean> elements = new HashMap<String, Boolean>();

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    System.out.println("Start Element :" + qName);
                    *//** storing the fact that the element has started *//*
                    elements.put(qName, true);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    System.out.println("End Element :" + qName);

                    *//** this section is just to add some space in the console print out *//*
                    if (qName.equalsIgnoreCase("params")) {
                        System.out.println();
                    }
                }

                @Override
                public void characters(char[] characters, int start, int length) throws SAXException {
                    if (elements.containsKey("params") && elements.get("params"))
                    {
                        String s = "params";
                    }
                    if (elements.containsKey("class") && elements.get("class")) {
                        System.out.println("class: " + new String(characters, start, length));
                        elements.put("class", false);
                    }
                    if (elements.containsKey("email") && elements.get("email")) {
                        System.out.println("Email: " + new String(characters, start, length));
                        elements.put("email", false);
                    }
                }

            };
            saxParser.parse("data.xml", handler);


        } catch (Exception e) {
            e.printStackTrace();
        }
    //}*/



    public String getStringFile(String path) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(path));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();
           // System.out.println(inputStr);
            return inputStr;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
