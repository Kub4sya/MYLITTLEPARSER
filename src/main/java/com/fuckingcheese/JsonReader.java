/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fuckingcheese;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author kubasya
 */
public class JsonReader implements Reader, JsonDeserializer<ArrayList<ReactorType>>{

    private ArrayList<ReactorType> r = new ArrayList<>();
    private Reader reader;
    
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
    public void read(File file) {
        if(getFileType(file.getAbsolutePath()).equals("json"))
        {
        Type type = new TypeToken<ArrayList<ReactorType>>(){}.getType();
        Gson g = new GsonBuilder().registerTypeAdapter(type, new JsonReader()).create();
        String date = getStringFile(file.getAbsolutePath());
        r = g.fromJson(date, type);
        System.out.println(r);
        }
       else
       {
//           xml.readXml(file);
          reader.read(file);
          r = reader.getR();
          System.out.println(r);
       }
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

    @Override
    public ArrayList<ReactorType> getR() {
        return r;
    }

    @Override
    public void setNeibour(Reader reader) {
        this.reader = reader;     
    }
}
