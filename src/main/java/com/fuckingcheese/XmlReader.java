package com.fuckingcheese;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.tree.DefaultMutableTreeNode;

public class XmlReader extends DefaultHandler implements Reader{
    private HashMap<String, Boolean> elements = new HashMap<String, Boolean>();
    private ArrayList<ReactorType> r = new ArrayList<>();
    private ArrayList<String> docData = new ArrayList<>();
    String thisElement;
    private Reader reader;
   // private YamlReader yaml = new YamlReader();

//    public void readXml(File file){
//        if(getFileType(file.getAbsolutePath()).equals("xml"))
//        {
//        try {
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser parser = factory.newSAXParser();
//            XmlReader saxp = new XmlReader();
//            parser.parse(file, saxp);
//            r = saxp.r;
//        } catch (SAXException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ParserConfigurationException e) {
//            throw new RuntimeException(e);
//        }
//        }
//        else
//        {
//            yaml.readYaml(file);
//            r = yaml.getR();
//        }
//    }

    @Override
    public String getFileType(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }
    
    public ArrayList<ReactorType> setAtrb()
    {
        //ArrayList<ReactorType> rx = new ArrayList<>();
        //System.out.println(docData);
        for(int i=0; i<docData.size(); i+=8)
        {
            r.add(new ReactorType(docData.get(i),Double.parseDouble(docData.get(i+1)),Double.parseDouble(docData.get(i+2)),Double.parseDouble(docData.get(i+3)),Integer.parseInt(docData.get(i+4)),Double.parseDouble(docData.get(i+5)),Integer.parseInt(docData.get(i+6)),Double.parseDouble(docData.get(i+7))));           
        }
        for(ReactorType rc : r)
        {
            rc.setFrom("xml");
        }
        return r;
        //System.out.println(rx);
    }

    @Override
    public void startDocument() throws SAXException {
       // System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        //elements.put(qName,true);
        thisElement = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       // System.out.println("hi");
        if(thisElement.equals("class"))
        {
            this.docData.add(new String(ch, start, length));
        }
        if(thisElement.equals("burnup"))
        {
            this.docData.add(new String(ch, start, length));
        }
        if(thisElement.equals("kpd"))
        {
            this.docData.add(new String(ch, start, length));
        }
        if(thisElement.equals("enrichment"))
        {
            this.docData.add(new String(ch, start, length));
        }
        if(thisElement.equals("termal_capacity"))
        {
            this.docData.add(new String(ch, start, length));
        }
        if(thisElement.equals("electrical_capacity"))
        {
            this.docData.add(new String(ch, start, length));
        }
        if(thisElement.equals("life_time"))
        {
            this.docData.add(new String(ch, start, length));
        }
        if(thisElement.equals("first_load"))
        {
            this.docData.add(new String(ch, start, length));
        }
    }
    
    @Override
    public ArrayList<ReactorType> getR() {
        return r;
    }

//    public DefaultMutableTreeNode fillReactors()
//        {
//            DefaultMutableTreeNode units = new DefaultMutableTreeNode("чуваки");
//            System.out.println(docData);
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
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }
    @Override
    public void endDocument() {
        //System.out.println("Stop parse XML...");
        setAtrb();
    }

    @Override
    public void read(File file) {
        if(getFileType(file.getAbsolutePath()).equals("xml"))
        {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XmlReader saxp = new XmlReader();
            parser.parse(file, saxp);
            r = saxp.r;
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        }
        else
        {
            reader.read(file);
            r = reader.getR();
//            yaml.readYaml(file);
//            r = yaml.getR();
        }
    }

    @Override
    public void setNeibour(Reader reader) {
        this.reader = reader;
    }
}
