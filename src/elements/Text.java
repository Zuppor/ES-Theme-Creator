/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import es.theme.editor.objects.ChangesListener.ChangeType;
import es.theme.editor.objects.FilePath;
import es.theme.editor.objects.StringColor;
import es.theme.editor.objects.Vector2;
import es.theme.editor.objects.ViewObject;

/**
 *
 * @author andrea
 */
public class Text extends ViewObject{
    protected FilePath fontPath;
    protected float fontSize;
    protected StringColor color;
    protected int forceUppercase;
    protected Vector2 pos;
    protected Vector2 size;
    
    /**
     *
     * @param name
     */
    public Text(String name){
        super(name);
        fontPath = new FilePath();
        fontPath.addListener(this);
        
        color = new StringColor();
        color.addListener(this);
        
        pos = new Vector2();
        pos.addListener(this);
        
        size = new Vector2();
        size.addListener(this);
        
        forceUppercase = 0;
    }
    
//    public Text(Text t){
//        super(t.name);
//        fontPath = new FilePath(t.fontPath);
//        fontPath.addListener(this);
//        
//        color = new StringColor(t.getColor());
//        color.addListener(this);
//        
//        pos = new Vector2(t.getPos());
//        pos.addListener(this);
//        
//        size = new Vector2(t.getSize());
//        size.addListener(this);
//        
//        forceUppercase = t.forcingUpperCase();
//    }
    
    public void setFontSize(float size){
        fontSize = size;
        alertListeners(ChangeType.VALUE_CHANGED);
    }
    
    public void forceUpperCase(boolean toggle){
        forceUppercase = toggle ? 1 : 0;
        alertListeners(ChangeType.VALUE_CHANGED);
    }
    
    
    public float getFontSize(){
        return fontSize;
    }
    
    public StringColor getColor(){
        return color;
    }
    
    public int forcingUpperCase(){
        return forceUppercase;
    }
    
    public Vector2 getPos(){
        return pos;
    }
    
    public Vector2 getSize(){
        return size;
    }
    
    public FilePath getFontPath(){
        return fontPath;
    }
}
