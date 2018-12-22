/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.theme.editor.objects;

import es.theme.editor.objects.ChangesListener.ChangeType;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrea
 */
public class StringColor {
    private String color;
    
    transient private List<ChangesListener> listeners;
    
    public StringColor(String color){
        this.color = color;
        listeners = new ArrayList<>();
    }
    
    public StringColor(StringColor c){
        this(c.getColor());
    }
    
    public StringColor(){
        this("000000FF");
    }
    
    public void setColor(String color){
        this.color = color;
        alertListeners(ChangeType.VALUE_CHANGED);
    }
    
    public void setColor(Color color){
        this.color = toHexString(color);
        alertListeners(ChangeType.VALUE_CHANGED);
    }
    
    public String getColor(){
        return color;
    }
    
    public void addListener(ChangesListener toAdd){
        listeners.add(toAdd);
    }
    
    private void alertListeners(ChangesListener.ChangeType ct){
        for(ChangesListener l : listeners){
            l.onChangesHappened(ct);
        }
    }
    
    private String toHexString(Color color){
        return String.format("%02X%02X%02X%02X", color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha());
    }
}
