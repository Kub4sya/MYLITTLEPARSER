package com.fuckingcheese;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

public class JsonReader implements JsonDeserializer<ArrayList<ReactorType>> {
    private ArrayList<ReactorType> r = new ArrayList<>();

    public DefaultMutableTreeNode fillReactors()
        {
            DefaultMutableTreeNode units = new DefaultMutableTreeNode("чуваки");
            //System.out.println(r);
            for(ReactorType rx : r)
            {
                DefaultMutableTreeNode unit = new DefaultMutableTreeNode(rx.getName());
                unit.add(new DefaultMutableTreeNode("Burnup is "+rx.getKpd()));
                unit.add(new DefaultMutableTreeNode("KPD is "+rx.getKpd()));
                unit.add(new DefaultMutableTreeNode("Enrichment is "+rx.getEnrichment()));
                unit.add(new DefaultMutableTreeNode("Termal_capacity is "+rx.getTermal_capacity()));
                unit.add(new DefaultMutableTreeNode("Electrical_capacity is "+rx.getElectrical_capacity()));
                unit.add(new DefaultMutableTreeNode("Life_time is "+rx.getLife_time()));
                unit.add(new DefaultMutableTreeNode("First_load is "+rx.getFirst_load()));
                //unit.add("burnup is "+rx.);
                units.add(unit);
            }
            return units;
        }
    
    public void readJson(File file) {
        Type type = new TypeToken<ArrayList<ReactorType>>(){}.getType();
        Gson g = new GsonBuilder().registerTypeAdapter(type, new JsonReader()).create();
        String date = getStringFile(file.getAbsolutePath());
        r = g.fromJson(date, type);
        System.out.println(r);
    }
    @Override
    public ArrayList<ReactorType> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jack = jsonObject.getAsJsonArray("params");
        ArrayList<ReactorType> rocket = new ArrayList<>();
        ReactorType rc = null;
        for(JsonElement jo : jack) {
            JsonObject js = jo.getAsJsonObject();
            String s = "";
            for(char ch : String.valueOf(js.getAsJsonPrimitive("class")).toCharArray()) //убераем кавычки со стрингов
            {
                if(ch != '"')
                {
                    s=s+ch;
                }
            }
            rc = new ReactorType(s, Double.parseDouble(String.valueOf(js.getAsJsonPrimitive("burnup"))), Double.parseDouble(String.valueOf(js.getAsJsonPrimitive("kpd"))), Double.parseDouble(String.valueOf(js.getAsJsonPrimitive("enrichment"))), Integer.parseInt(String.valueOf(js.getAsJsonPrimitive("termal_capacity"))), Double.parseDouble(String.valueOf(js.getAsJsonPrimitive("electrical_capacity"))), Integer.parseInt(String.valueOf(js.getAsJsonPrimitive("life_time"))), Double.parseDouble(String.valueOf(js.getAsJsonPrimitive("first_load"))));
            rc.setFrom("json");
            rocket.add(rc);
        }
        //System.out.println(rocket);
        //fillReactors(rocket);
        return rocket;
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
