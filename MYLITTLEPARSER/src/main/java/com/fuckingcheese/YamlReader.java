package com.fuckingcheese;

import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YamlReader {
    private ArrayList<ReactorType> r = new ArrayList<>();
    ArrayList<Map<String,String>> sister = new ArrayList<>();
    private ArrayList<String> docData = new ArrayList<>();

    public void readYaml()
    {
        String s = getStringFile("data.yaml");
        Yaml yaml = new Yaml();
        Map<String, ArrayList<Map<String,String>>> myMap = yaml.load(s);
        sister = myMap.get("params");
        //System.out.println(String.valueOf(sister.get(0).get("burnup")));
        divideMap();
    }
//hellow
    public void divideMap()
    {
        for(Map<String, String> mute : sister)
        {
            for(Map.Entry<String,String> node : mute.entrySet())
            {
                docData.add(String.valueOf(node.getValue()));
            }
        }
        setAtrb();
       // System.out.println(docData);
    }

    public void setAtrb()
    {
        //System.out.println(docData);
        for(int i=0; i<docData.size(); i+=8)
        {
            r.add(new ReactorType(docData.get(i),Double.parseDouble(docData.get(i+1)),Double.parseDouble(docData.get(i+2)),Double.parseDouble(docData.get(i+3)),Integer.parseInt(docData.get(i+4)),Double.parseDouble(docData.get(i+5)),Integer.parseInt(docData.get(i+6)),Double.parseDouble(docData.get(i+7))));
        }
        System.out.println(r);
    }

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
