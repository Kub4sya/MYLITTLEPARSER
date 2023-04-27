package com.fuckingcheese;

import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

public class YamlReader implements Reader{
    private ArrayList<ReactorType> r = new ArrayList<>();
    ArrayList<Map<String,String>> sister = new ArrayList<>();
    private ArrayList<String> docData = new ArrayList<>();
    private Reader reader;

//    public void readYaml(File file)
//    {
//        if(getFileType(file.getAbsolutePath()).equals("yaml"))
//        {
//        String s = getStringFile(file.getAbsolutePath());
//        Yaml yaml = new Yaml();
//        Map<String, ArrayList<Map<String,String>>> myMap = yaml.load(s);
//        sister = myMap.get("params");
//        //System.out.println(String.valueOf(sister.get(0).get("burnup")));
//        divideMap();
//        }
//        else
//        {
//            //нет нифига
//        }
//    }
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
        for(ReactorType rc : r)
        {
            rc.setFrom("yaml");
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
    
//    public DefaultMutableTreeNode fillReactors()
//        {
//            DefaultMutableTreeNode units = new DefaultMutableTreeNode("чуваки");
//            //System.out.println(r);
//            for(ReactorType rx : r)
//            {
//                DefaultMutableTreeNode unit = new DefaultMutableTreeNode(rx.getName());
//                unit.add(new DefaultMutableTreeNode("Burnup is "+rx.getKpd()));
//                unit.add(new DefaultMutableTreeNode("KPD is "+rx.getKpd()));
//                unit.add(new DefaultMutableTreeNode("Enrichment is "+rx.getEnrichment()));
//                unit.add(new DefaultMutableTreeNode("Termal_capacity is "+rx.getTermal_capacity()));
//                unit.add(new DefaultMutableTreeNode("Electrical_capacity is "+rx.getElectrical_capacity()));
//                unit.add(new DefaultMutableTreeNode("Life_time is "+rx.getLife_time()));
//                unit.add(new DefaultMutableTreeNode("First_load is "+rx.getFirst_load()));
//                unit.add(new DefaultMutableTreeNode("From "+rx.getFrom()));
//                //unit.add("burnup is "+rx.);
//                units.add(unit);
//            }
//            return units;
//        }

    @Override
    public void read(File file) {
        if(getFileType(file.getAbsolutePath()).equals("yaml"))
        {
        String s = getStringFile(file.getAbsolutePath());
        Yaml yaml = new Yaml();
        Map<String, ArrayList<Map<String,String>>> myMap = yaml.load(s);
        sister = myMap.get("params");
        //System.out.println(String.valueOf(sister.get(0).get("burnup")));
        divideMap();
        }
        else
        {
           reader.read(file);
           r = reader.getR();
        }   
    }

    @Override
    public String getFileType(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }

    @Override
    public ArrayList<ReactorType> getR() {
        return r;
    }

    @Override
    public void setNeibour(Reader reader) {
        this.reader = reader;
    }
    
}
