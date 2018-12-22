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
public class Vector2{
    private float x,y;
    
    transient private List<ChangesListener> listeners;
    
    public Vector2(){
        x = 0;
        y = 0;
        listeners = new ArrayList<>();
    }
    
    public Vector2(Vector2 v){
        this(v.getX(),v.getY());
    }
    
    public Vector2(float x,float y){
        this.x = x;
        this.y = y;
    }
    
    public void setX(float x){
        this.x = x;
        alertListeners(ChangeType.VALUE_CHANGED);
    }
    
    public void setY(float y){
        this.y = y;
        alertListeners(ChangeType.VALUE_CHANGED);
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
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
