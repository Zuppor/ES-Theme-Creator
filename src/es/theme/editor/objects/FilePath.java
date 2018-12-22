/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.theme.editor.objects;

import es.theme.editor.objects.ChangesListener.ChangeType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrea
 */
public class FilePath {
    private String path;
    private boolean enabled;
    
    transient private List<ChangesListener> listeners;
    
    public FilePath(){
        listeners = new ArrayList<>();
        path = "";
        enabled = false;
    }
    
    public FilePath(FilePath f){
        this.path = f.getPath();
        this.enabled = f.isEnabled();
    }
    
    public void setPath(String path){
        this.path = path;
        alertListeners(ChangeType.VALUE_CHANGED);
    }
    
    public void setEnabled(boolean en){
        this.enabled = en;
        alertListeners(ChangeType.VALUE_CHANGED);
    }
    
    public String getPath(){
        return path;
    }
    
    public String getFileName(){
        return path.substring(path.lastIndexOf("/")+1);
    }
    
    public boolean isEnabled(){
        return enabled;
    }
    
    public void addListener(ChangesListener toAdd){
        listeners.add(toAdd);
    }
    
    private void alertListeners(ChangesListener.ChangeType ct){
        for(ChangesListener l : listeners){
            l.onChangesHappened(ct);
        }
    }
}
