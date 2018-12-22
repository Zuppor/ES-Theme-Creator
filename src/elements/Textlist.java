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
public class Textlist extends ViewObject{
    
    private Vector2 pos;
    private Vector2 size;
    private StringColor selectorColor;
    private StringColor selectedColor;
    private StringColor primaryColor;
    private StringColor secondaryColor;
    private FilePath fontPath;
    private float fontSize;

    public Textlist(String name) {
        super(name);
        pos = new Vector2();
        pos.addListener(this);
        
        size = new Vector2();
        size.addListener(this);
        
        selectorColor = new StringColor();
        selectorColor.addListener(this);
        
        selectedColor = new StringColor();
        selectedColor.addListener(this);
        
        primaryColor = new StringColor();
        primaryColor.addListener(this);
        
        secondaryColor = new StringColor();
        secondaryColor.addListener(this);
        
        fontPath = new FilePath();
        fontPath.addListener(this);
        
        fontSize = 1;
    }
    
//    public Textlist(Textlist t){
//        super(t.name);
//        pos = new Vector2(t.getPos());
//        pos.addListener(this);
//        
//        size = new Vector2(t.getSize());
//        size.addListener(this);
//        
//        selectorColor = new StringColor(t.getSelectorColor());
//        selectorColor.addListener(this);
//        
//        selectedColor = new StringColor(t.getSelectedColor());
//        selectedColor.addListener(this);
//        
//        primaryColor = new StringColor(t.getPrimaryColor());
//        primaryColor.addListener(this);
//        
//        secondaryColor = new StringColor(t.getSecondaryColor());
//        secondaryColor.addListener(this);
//        
//        fontPath = new FilePath(t.getFontPath());
//        fontPath.addListener(this);
//        
//        fontSize = t.getFontSize();
//    }
    
    public void setFontSize(float size){
        fontSize = size;
        alertListeners(ChangeType.VALUE_CHANGED);
    }
    
    
    public StringColor getSelectorColor(){
        return selectorColor;
    }
    
    public StringColor getSelectedColor(){
        return selectedColor;
    }
    
    public StringColor getPrimaryColor(){
        return primaryColor;
    }
    
    public StringColor getSecondaryColor(){
        return secondaryColor;
    }
    
    public FilePath getFontPath(){
        return fontPath;
    }
    
    public float getFontSize(){
        return fontSize;
    }
    
    public Vector2 getPos(){
        return pos;
    }
    
    public Vector2 getSize(){
        return size;
    }
}
