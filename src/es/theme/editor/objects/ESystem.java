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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Zuppor
 */
public class ESystem implements ChangesListener{
    
    private Map<String,String> specs;
    private View systemView;
    private View basicView;
    private View detailedView;
    private View videoView;
    
    transient private List<ChangesListener> listeners = new ArrayList<>();;
    
    
    public ESystem(String name){
        listeners = new ArrayList<>();
        specs = new HashMap<>();
        specs.put("name", name);
        
        systemView = new View();
        systemView.addText(new Text("helptext"));
        systemView.addListener(this);
        
        basicView = new View();
        basicView.addImage(new Image("logo"));
        basicView.addTextlist(new Textlist("gamelist"));
        basicView.addListener(this);
        
        detailedView = new View();
        detailedView.addImage(new Image("logo"),new Image("md_image"));
        detailedView.addTextlist(new Textlist("gamelist"));
        detailedView.addText(new Text("md_lbl_rating"),
                new Text("md_lbl_releasedate"),
                new Text("md_lbl_developer"),
                new Text("md_lbl_publisher"),
                new Text("md_lbl_genre"),
                new Text("md_lbl_players"),
                new Text("md_lbl_lastplayed"),
                new Text("md_lbl_playcount"),
                new Text("md_developer"),
                new Text("md_publisher"),
                new Text("md_genre"),
                new Text("md_players"),
                new Text("md_playcount"),
                new Text("md_description"));
        detailedView.addDatetime(new Datetime("md_releasedate"),
                new Datetime("md_lastplayed"));
        detailedView.addRating(new Rating("md_rating"));
        detailedView.addListener(this);
        
        videoView = new View();
        videoView.addListener(this);
    }
    
    
    public void setSpec(String specName,String value){
        specs.put(specName, value);
        alertListeners(ChangeType.VALUE_CHANGED);
    }
    
    public String getSpec(String spec){
        return specs.get(spec);
    }
    
    public void setSpecs(Map<String,String> specs){
        this.specs = new HashMap<>(specs);
    }
    
    public Map<String,String> getSpecs(){
        return specs;
    }
    
    
    public void setSystemView(View systemView){
        this.systemView = systemView;
    }
    public void setBasicView(View basicView){
        this.basicView = basicView;
    }
    public void setDetailedView(View detailedView){
        this.detailedView = detailedView;
    }
    public void setVideoView(View videoView){
        this.videoView = videoView;
    }
    
    public View getSystemView(){
        return this.systemView;
    }
    public View getBasicView(){
        return this.basicView;
    }
    public View getDetailedView(){
        return this.detailedView;
    }
    public View getVideoView(){
        return this.videoView;
    }
    
    
    public void addListener(ChangesListener toAdd){
        listeners.add(toAdd);
    }
    
    private void alertListeners(ChangeType ct){
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
