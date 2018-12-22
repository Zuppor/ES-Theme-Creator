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
public class Image extends ViewObject{
    private FilePath imagePath;
    private Vector2 origin;
    private Vector2 pos;
    private Vector2 size;
    private StringColor color;
    private boolean tiled;
    
    public Image(String name){
        super(name);
        imagePath = new FilePath();
        imagePath.addListener(this);
        
        origin = new Vector2();
        origin.addListener(this);
        
        pos = new Vector2();
        pos.addListener(this);
        
        size = new Vector2();
        size.addListener(this);
        
        color = new StringColor("FFFFFFFF");
        color.addListener(this);
        
        tiled = false;
    }
    
//    public Image(Image i){
//        super(i.name);
//        imagePath = new FilePath(i.getImagePath());
//        imagePath.addListener(this);
//        
//        origin = new Vector2(i.getOrigin());
//        origin.addListener(this);
//        
//        pos = new Vector2(i.getPos());
//        pos.addListener(this);
//        
//        size = new Vector2(i.getSize());
//        size.addListener(this);
//        
//        color = new StringColor(i.getColor());
//        color.addListener(this);
//        
//        tiled = i.getTiled();
//    }
    
    
    public void setTiled(boolean tiled){
        this.tiled = tiled;
        alertListeners(ChangeType.VALUE_CHANGED);
    }

    
    
    public StringColor getColor(){
        return color;
    }
    
    public boolean getTiled(){
        return tiled;
    }
    public Vector2 getPos(){
        return pos;
    }
    
    public Vector2 getSize(){
        return size;
    }
    
    public Vector2 getOrigin(){
        return origin;
    }
    
    public FilePath getImagePath(){
        return imagePath;
    }
}
