package com.fuckingcheese;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonReader implements JsonDeserializer<ReactorType> {
    private ArrayList<ReactorType> r = new ArrayList<>();

    public void readJson(File file) {
        Gson g = new GsonBuilder().registerTypeAdapter(ReactorType.class, new JsonReader()).create();
        String date = getStringFile(file.getAbsolutePath());
        g.fromJson(date, ReactorType.class);
    }
    @Override
    public ReactorType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jack = jsonObject.getAsJsonArray("params");
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
            r.add(rc);
        }
        System.out.println(r);
        return rc;
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
