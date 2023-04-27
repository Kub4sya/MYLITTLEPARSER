/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fuckingcheese;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author kubasya
 */
public interface Reader {
    //public ArrayList<ReactorType> r = new ArrayList<>();
    public String getFileType(String fileName);
    public void read(File file);
    public ArrayList<ReactorType> getR();
    public void setNeibour(Reader reader);
}
