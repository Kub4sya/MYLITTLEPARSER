package com.fuckingcheese;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f = new File("data.yaml");
        JsonReader json = new JsonReader();
        XmlReader xml = new XmlReader();
        YamlReader yaml = new YamlReader();
        //json.readJson(f);
       // xml.readXml(f);
        yaml.readYaml(f);
        //suck dick
    }
}