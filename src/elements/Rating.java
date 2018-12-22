/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import es.theme.editor.objects.FilePath;
import es.theme.editor.objects.Vector2;
import es.theme.editor.objects.ViewObject;

/**
 *
 * @author andrea
 */
public class Rating extends ViewObject{
    
    private Vector2 pos;
    private Vector2 size;
    private FilePath filledPath;
    private FilePath unfilledPath;
    
    public Rating(String name) {
        super(name);
        filledPath = new FilePath();
        filledPath.addListener(this);
        
        unfilledPath = new FilePath();
        unfilledPath.addListener(this);
        
        pos = new Vector2();
        pos.addListener(this);
        
        size = new Vector2();
        size.addListener(this);
    }
    
//    public Rating(Rating r){
//        super(r.name);
//        filledPath = new FilePath(r.getFilledPath());
//        filledPath.addListener(this);
//        
//        unfilledPath = new FilePath(r.getUnfilledPath());
//        unfilledPath.addListener(this);
//        
//        pos = new Vector2(r.getPos());
//        pos.addListener(this);
//        
//        size = new Vector2(r.getSize());
//        size.addListener(this);
//    }
    
    
    public FilePath getFilledPath(){
        return filledPath;
    }
    
    public FilePath getUnfilledPath(){
        return unfilledPath;
    }
    
    public Vector2 getPos(){
        return pos;
    }
    
    public Vector2 getSize(){
        return size;
    }
    
}
