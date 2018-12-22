/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.theme.editor.objects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrea
 */
public class ViewObject implements ChangesListener{
    private String name;
    private int index;
    private boolean enabled;
    private boolean extra;
    
    transient private List<ChangesListener> listeners;
    
    public ViewObject(String name){
        listeners = new ArrayList<>();
        this.name = name;
        enabled = true;
        extra = false;
    }
    
    public void setExtra(boolean b){
        extra = b;
    }
    
    public boolean getExtra(){
        return extra;
    }
    
    public void setIndex(int i){
        index = i;
    }
    
    public int getIndex(){
        return index;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setEnabled(boolean b){
        enabled = b;
    }
    
    public boolean getEnabled(){
        return enabled;
    }
    
    public void addListener(ChangesListener toAdd){
        listeners.add(toAdd);
    }
    
    protected void alertListeners(ChangesListener.ChangeType ct){
        for(ChangesListener l : listeners){
            l.onChangesHappened(ct);
        }
    }

    @Override
    public void onChangesHappened(ChangeType changeType) {
        alertListeners(changeType);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
