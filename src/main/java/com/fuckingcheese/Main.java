package com.fuckingcheese;

public class Main {
    public static void main(String[] args) {
        JsonReader json = new JsonReader();
        XmlReader xml = new XmlReader();
        YamlReader yaml = new YamlReader();
        json.readJson();
        xml.readXml();
        yaml.readYaml();
    }
}