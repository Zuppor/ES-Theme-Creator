/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.theme.editor.objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 *
 * @author andrea
 */
public class SavefileHandler {
    
    private SaveFile saveFile;
    private File projectFile;
    
    public SavefileHandler(){
        saveFile = null;
        projectFile = null;
    }
    
    
    public final void openProject(File project) throws FileNotFoundException, IOException{
        saveFile = readProject(project);
        projectFile = project;
    }
    
    public void saveProject(SaveFile saveFile,String  path) throws IOException{
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(gson.toJson(saveFile));
            writer.close();
        }
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(new File(path), saveFile);
    }
    
    public void saveCurrentProject() throws IOException{
        saveProject(saveFile,projectFile.getAbsolutePath());
    }
    
    private SaveFile readProject(File file) throws FileNotFoundException, IOException{
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = new Gson();
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
        return new Gson().fromJson(new FileReader(file.getAbsolutePath()), SaveFile.class);
//        ObjectMapper mapper = new ObjectMapper();
//        
//        return mapper.readValue(file, SaveFile.class);
    }
    
    public void closeCurrentProject() throws IOException{
        saveFile = null;
        projectFile = null;
    }
    
    
    public SaveFile getCurrSaveFile(){
        return saveFile;
    }
    
    public File getCurrentProjectFile(){
        return projectFile;
    }
    
}
