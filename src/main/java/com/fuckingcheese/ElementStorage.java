/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fuckingcheese;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author kubasya
 */
public class ElementStorage {
   // private ArrayList<ArrayList<ReactorType>> storage = new ArrayList();
    private ArrayList<ReactorType> currentReactor = new ArrayList(); 
    private Reader red;
    
    public void goFile(File file)
    {
        red = createChain();
        //red = new JsonReader();
        red.read(file);
//        json.readJson(file);
        currentReactor = red.getR();
    }
    public void goSQL()
    {
        String url = "jdbc:sqlserver://92.51.39.177\\\\SQLEXPRESS:1433;databaseName=KINGDOM_OF_REACTORS";
        String user = "Kubasya";
        String pass = "0000";
        System.out.println(url);

        try {
            Connection connection = DriverManager.getConnection(url,user,pass);
            System.out.println("Connection Success");
            String sql = "SELECT id,code,unit_name,site,status,type,model,class,ru_design,thermal_capacity,gross_capacity,net_capacity,construction_start,commercial_operation,date_shutdown,enrichment,load_factor FROM units WHERE (date_shutdown > GETDATE() OR date_shutdown IS NULL) AND (construction_start < GETDATE() OR construction_start IS NOT NULL) AND (commercial_operation < GETDATE() AND commercial_operation IS NOT NULL)";
           // String sql = "SELECT DISTINCT type FROM units";// WHERE (date_shutdown > GETDATE() OR date_shutdown IS NULL) AND (construction_start < GETDATE() OR construction_start IS NOT NULL) AND (commercial_operation < GETDATE() AND commercial_operation IS NOT NULL)";
            Statement statement = connection.createStatement();
            convertSQL(statement.executeQuery(sql));
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            throw new RuntimeException(e);
        }

    }

    public void convertSQL(ResultSet result) throws SQLException {
        ArrayList<Reactor> units = new ArrayList<>();
        while(result.next())
        {
            units.add(new Reactor(result.getInt(1),
                    result.getString(2).trim(),
                    result.getString(3).trim(),
                    result.getInt(4),
                    result.getString(5).trim(),
                    result.getString(6).trim(),
                    result.getString(7).trim(),
                    MatchReactorType(result.getString(8).trim()), //злая хрень
                    result.getBoolean(9),result.getInt(10),result.getInt(11),result.getInt(12),
                    result.getString(13).trim(),
                    result.getString(14).trim(),
                    result.getString(15),
                    result.getDouble(16),
                    result.getInt(17)));
        }
       for (Reactor rec:units) {
           /*           try {*/
           if (true) {
               System.out.println(rec);
           }
/*               }
           }
           catch (NullPointerException u)
           {
               System.out.println(rec.getId());
           }*/
           //System.out.println(units);
       }
    }

    public ReactorType MatchReactorType(String name)
    {
        ReactorType currentType = null;
        for(ReactorType ru : currentReactor)
        {
            if(name.equals(ru.getName()))
            {
                currentType = ru;
                break;
            }
            if(name.equals("AGR") && ru.getName().equals("MAGNOX"))
            {
                currentType = ru;
                break;
            }
            if(ru.getName().equals("PWR") && (name.equals("PWR-900") || name.equals("PWR-1300") || name.equals("PWR-1500")))
            {
                currentType = ru;
                break;
            }
            if(ru.getName().equals("VVER-1200") && (name.equals("VVER-1000") || name.equals("VVER-440")))
            {
                currentType = ru;
                break;
            }
            if(name.equals("CPN") && ru.getName().equals("CPR_1000"))
            {
                currentType = ru;
                break;
            }
        }
        return currentType;
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
