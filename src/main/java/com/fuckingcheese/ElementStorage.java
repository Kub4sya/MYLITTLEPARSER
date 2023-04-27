/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fuckingcheese;

import java.io.File;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author kubasya
 */
public class ElementStorage {
   // private ArrayList<ArrayList<ReactorType>> storage = new ArrayList();
    private ArrayList<ReactorType> currentReactor = new ArrayList();
    private JsonReaderOrigin json = new JsonReaderOrigin();
    private Reader red;
    
    
    public void setReactor()
    {
        
    }
    
    public void setStorage()
    {
        
    }
    
    public void goFile(File file)
    {
        red = createChain();
        //red = new JsonReader();
        red.read(file);
//        json.readJson(file);
        currentReactor = red.getR();
    }
    
    public Reader createChain()
    {
        Reader json = new JsonReader();
        Reader xml = new XmlReader();
        Reader yaml = new YamlReader();
        json.setNeibour(xml);
        xml.setNeibour(yaml);
        yaml.setNeibour(null);
        return json;
        
    }
    
    public DefaultMutableTreeNode fillReactors()
        {
            DefaultMutableTreeNode units = new DefaultMutableTreeNode("чуваки");
            //System.out.println(r);
            for(ReactorType rx : currentReactor)
            {
                DefaultMutableTreeNode unit = new DefaultMutableTreeNode(rx.getName());
                unit.add(new DefaultMutableTreeNode("Burnup is "+rx.getKpd()));
                unit.add(new DefaultMutableTreeNode("KPD is "+rx.getKpd()));
                unit.add(new DefaultMutableTreeNode("Enrichment is "+rx.getEnrichment()));
                unit.add(new DefaultMutableTreeNode("Termal_capacity is "+rx.getTermal_capacity()));
                unit.add(new DefaultMutableTreeNode("Electrical_capacity is "+rx.getElectrical_capacity()));
                unit.add(new DefaultMutableTreeNode("Life_time is "+rx.getLife_time()));
                unit.add(new DefaultMutableTreeNode("First_load is "+rx.getFirst_load()));
                unit.add(new DefaultMutableTreeNode("From "+rx.getFrom()));
                //unit.add("burnup is "+rx.);
                units.add(unit);
            }
            return units;
        }
    
}
