package com.fuckingcheese;

import java.io.File;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
//        File f = new File("data.yaml");
//        JsonReaderOrigin json = new JsonReaderOrigin();
//        XmlReader xml = new XmlReader();
//        YamlReader yaml = new YamlReader();
//        //json.readJson(f);
//       // xml.readXml(f);
//        yaml.readYaml(f);
//        //suck dick
        ElementStorage el = new ElementStorage();
        el.goSQL();
    }
}