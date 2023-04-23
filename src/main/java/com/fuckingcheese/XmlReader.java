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

public class XmlReader extends DefaultHandler{
    private HashMap<String, Boolean> elements = new HashMap<String, Boolean>();
    private ArrayList<ReactorType> r = new ArrayList<>();
    private ArrayList<String> docData = new ArrayList<>();
    String thisElement;

    public void readXml(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XmlReader saxp = new XmlReader();
            parser.parse(new File("data.xml"), saxp);

        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
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
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }
    @Override
    public void endDocument() {
        //System.out.println("Stop parse XML...");
        setAtrb();
    }
}
