/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.theme.editor.objects;

import elements.Datetime;
import elements.Image;
import elements.Rating;
import elements.Text;
import elements.Textlist;
import es.theme.editor.objects.ChangesListener.ChangeType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrea
 */
public class View {

    private ArrayList<Datetime> datetimes;
    private ArrayList<Image> images;
    private ArrayList<Rating> ratings;
    private ArrayList<Text> texts;
    private ArrayList<Textlist> textlists;
    
    private int elementsCount;

    transient private List<ChangesListener> listeners = new ArrayList<>();;
    
    public View(){
        datetimes = new ArrayList<>();
        images = new ArrayList<>();
        ratings = new ArrayList<>();
        texts = new ArrayList<>();
        textlists = new ArrayList<>();
        
        elementsCount = 0;
        
        listeners = new ArrayList<>();
    }
    
    public void addDatetime(Datetime... d){
        for(Datetime x : d){
            datetimes.add(x);
            x.setIndex(elementsCount);
            onElementAdded();
        }
    }
    
    public void addImage(Image... i){
        for(Image x : i){
            images.add(x);
            x.setIndex(elementsCount);
            onElementAdded();
        }
    }
    
    public void addRating(Rating... r){
        for(Rating x : r){
            ratings.add(x);
            x.setIndex(elementsCount);
            onElementAdded();
        }
    }
    
    public void addText(Text... t){
        for(Text x : t){
            texts.add(x);
            x.setIndex(elementsCount);
            onElementAdded();
        }
    }
    
    public void addTextlist(Textlist... t){
        for(Textlist x : t){
            textlists.add(x);
            x.setIndex(elementsCount);
            onElementAdded();
        }
    }
    
    private void onElementAdded(){
        elementsCount++;
        alertListeners(ChangeType.ELEMENT_ADDED);
    }
    
    public Object getElementByIndex(int index){
        for(Datetime d : datetimes){
            if(d.getIndex() == index){
                return d;
            }
        }
        for(Image d : images){
            if(d.getIndex() == index){
                return d;
            }
        }
        for(Rating d : ratings){
            if(d.getIndex() == index){
                return d;
            }
        }
        for(Text d : texts){
            if(d.getIndex() == index){
                return d;
            }
        }
        for(Textlist d : textlists){
            if(d.getIndex() == index){
                return d;
            }
        }
        
        return null;
    }
    
    public void setDatetimes(ArrayList<Datetime> elements){
        this.datetimes = elements;
    }
    
    public ArrayList<Datetime> getDatetimes(){
        return datetimes;
    }
    
    public void setImages(ArrayList<Image> elements){
        this.images = elements;
    }
    
    public ArrayList<Image> getImages(){
        return images;
    }
    
    public void setRatings(ArrayList<Rating> elements){
        this.ratings = elements;
    }
    
    public ArrayList<Rating> getRatings(){
        return ratings;
    }
    
    public void setTexts(ArrayList<Text> elements){
        this.texts = elements;
    }
    
    public ArrayList<Text> getTexts(){
        return texts;
    }
    
    public void setTextlists(ArrayList<Textlist> elements){
        this.textlists = elements;
    }
    
    public ArrayList<Textlist> getTextlists(){
        return textlists;
    }
    
    public void setElementsCount(int i){
        elementsCount = i;
    }
    
    public int getElementsCount(){
        return elementsCount;
    }
    
//    public void addExtra(ViewObject obj){
//        elements.add(obj);
//        alertListeners(ChangeType.ELEMENT_ADDED);
//    }
//    
    public void removeExtra(int index){
        for(Datetime d : datetimes){
            if(d.getIndex() == index){
                datetimes.remove(d);
                alertListeners(ChangeType.ELEMENT_REMOVED);
                return;
            }
        }
        for(Image d : images){
            if(d.getIndex() == index){
                images.remove(d);
                alertListeners(ChangeType.ELEMENT_REMOVED);
                return;
            }
        }
        for(Rating d : ratings){
            if(d.getIndex() == index){
                ratings.remove(d);
                alertListeners(ChangeType.ELEMENT_REMOVED);
                return;
            }
        }
        for(Text d : texts){
            if(d.getIndex() == index){
                texts.remove(d);
                alertListeners(ChangeType.ELEMENT_REMOVED);
                return;
            }
        }
        for(Textlist d : textlists){
            if(d.getIndex() == index){
                textlists.remove(d);
                alertListeners(ChangeType.ELEMENT_REMOVED);
                return;
            }
        }
    }
    
//    public void setExtra(int index,ViewObject obj){
//        // complete this function
//    }
    
    public void addListener(ChangesListener toAdd){
        listeners.add(toAdd);
    }
    
    private void alertListeners(ChangesListener.ChangeType ct){
        for(ChangesListener l : listeners){
            l.onChangesHappened(ct);
        }
    }
}
