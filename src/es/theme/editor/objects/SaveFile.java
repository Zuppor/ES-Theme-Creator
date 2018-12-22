/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.theme.editor.objects;

import es.theme.editor.objects.ChangesListener.ChangeType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andrea
 */
public class SaveFile implements ChangesListener{
    private Map<String,String> infos;
    private ArrayList<ESystem> systems;
    
    transient private List<ChangesListener> listeners = new ArrayList<>();
    
    
    transient public static String[] defaultSystems = {
                "3do","amiga","amstradcpc","apple2",
                "atari800","atri2600","atari5200","atari7800",
                "atarijaguar","atarilynx","atarist","c64",
                "coco","colecovision","dragon32","dreamcast",
                "fba","fds","gamegear","gb",
                "gba","gbc","gc","genesis",
                "intellivision","kodi","macintosh","mame",
                "mastersystem","megadrive","msx","n64",
                "nds","neogeo","nes","ngp",
                "odyssey2","pc","pcengine","ports",
                "ps2","psp","psx","retropie",
                "saturn","scummvm","sega32x","segacd",
                "sg-1000","smw","snes","vectrex",
                "videopac","virtualboy","wii","wonderswan",
                "zmachine","zxspectrum"};
    
    
    public SaveFile(){}
    
    public SaveFile(String themeName){
        infos = new HashMap<>();
        systems = new ArrayList<>();
        listeners = new ArrayList<>();
        
        this.infos.put("name", themeName);
        this.infos.put("version", "1.0");
        this.infos.put("author", System.getProperty("user.name"));
        this.infos.put("email", "");
        this.infos.put("website", "");
        this.infos.put("license", "creative commons CC-BY-NC-SA");
        this.infos.put("basedon", "");
        
        for (String n : defaultSystems) {
            ESystem s = new ESystem(n);
            s.addListener(this);
            systems.add(s);
        }
    }
    
    public Map<String,String> getInfos(){
        return infos;
    }
    
    public void setInfos(Map<String,String> infos){
        this.infos = infos;
        //alertListeners();
    }
    
    public boolean hasEmptyInfo(){
        if(!infos.get("version").equals("")) return false;
        if(!infos.get("author").equals("")) return false;
        if(!infos.get("email").equals("")) return false;
        if(!infos.get("website").equals("")) return false;
        if(!infos.get("license").equals("")) return false;
        if(!infos.get("basedon").equals("")) return false;
        return infos.get("name").equals("");
    }
    
    public String getInfoAsString(){
        return "\ntheme name:     "+infos.get("name")+"\n"+
               "version:        "+infos.get("version")+"\n"+
               "author:         "+infos.get("author")+"\n"+
               "email:          "+infos.get("email")+"\n"+
               "website:        "+infos.get("website")+"\n"+
               "license:        "+infos.get("license")+"\n"+
               "based on:       "+infos.get("basedon")+"\n";
    }
    
    public String getInfo(String infoName){
        return infos.get(infoName);
    }
    
    public ESystem getSystemByName(String name){
        for(ESystem s : systems){
            if(s.getSpec("name").equals(name)){
                return s;
            }
        }
        
        return null;
    }
    
    public ArrayList<ESystem> getSystems(){
        return systems;
    }
    
    public void setSystems(ArrayList<ESystem> systems){
        this.systems = systems;
    }
    
    public void addSystem(String name){
        systems.add(new ESystem(name));
        alertListeners();
    }
    
    public void addListener(ChangesListener toAdd){
        System.out.println("adding " + toAdd.toString());
        listeners.add(toAdd);
    }
    
    private void alertListeners(){
        for(ChangesListener l : listeners){
            l.onChangesHappened(ChangeType.VALUE_CHANGED);
        }
    }

    @Override
    public void onChangesHappened(ChangeType changeType) {
        alertListeners();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
