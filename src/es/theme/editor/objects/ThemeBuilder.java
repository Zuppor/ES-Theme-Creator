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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author andrea
 */
public class ThemeBuilder {
    
    private SaveFile saveFile;
    public Path rootDir;
    private Path fontsPath;
    private Path imagesPath;
    
    public void setProject(SaveFile saveFile){
        this.saveFile = saveFile;
    }
    
    public boolean buildXMLOfSystem(ESystem s){
        try {
            Path currPath = Paths.get(rootDir.toString()+"/"+s.getSpec("name"));
            Files.createDirectories(currPath);
            File f = new File(currPath.toString()+"/theme.xml");
            f.createNewFile();
            FileWriter fw = new FileWriter(f.getAbsolutePath());
            fw.append(generateXML(s.getSpec("name")));
            fw.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ThemeBuilder.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean buildResourcesOfSystem(ESystem s){
        
        try {
            Files.createDirectories(fontsPath);
            Files.createDirectories(imagesPath);
            
            
            for(Datetime d : s.getSystemView().getDatetimes()){
                File f = new File(fontsPath+"/"+s.getSpec("name")+"/"+d.getFontPath().getFileName());
                copyResource(d.getFontPath().getPath(),f);
            }
            for(Image i : s.getSystemView().getImages()){
                File f = new File(imagesPath+"/"+s.getSpec("name")+"/"+i.getImagePath().getFileName());
                copyResource(i.getImagePath().getPath(),f);
            }
            for(Rating r : s.getSystemView().getRatings()){
                File f = new File(imagesPath+"/"+s.getSpec("name")+"/"+r.getFilledPath().getFileName());
                copyResource(r.getFilledPath().getPath(),f);
                f = new File(imagesPath+"/"+s.getSpec("name")+"/"+r.getUnfilledPath().getFileName());
                copyResource(r.getUnfilledPath().getPath(),f);
            }
            for(Text t : s.getSystemView().getTexts()){
                File f = new File(fontsPath+"/"+s.getSpec("name")+"/"+t.getFontPath().getFileName());
                copyResource(t.getFontPath().getPath(),f);
            }
            for(Textlist t : s.getSystemView().getTextlists()){
                File f = new File(fontsPath+"/"+s.getSpec("name")+"/"+t.getFontPath().getFileName());
                copyResource(t.getFontPath().getPath(),f);
            }
            
            return true;
        } 
        catch (IOException ex) {
            Logger.getLogger(ThemeBuilder.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    private void copyResource(String from,File to) throws IOException,FileAlreadyExistsException{
        Files.copy(Paths.get(from), to.toPath());
    }
    
    public String generateXML(String systemName) throws IOException,UnsupportedEncodingException{
        if(saveFile==null){
            return "Save file is null";
        }
        ESystem system = saveFile.getSystemByName(systemName);
        if(system==null){
            return "Error: system not found (paramenter: "+systemName+")";
        }
        
        StringWriter result = new StringWriter();
        
        Document document = DocumentHelper.createDocument();

        if(!saveFile.hasEmptyInfo()){
            document.addComment(saveFile.getInfoAsString());
        }   

        Element root = document.addElement("theme");

        root.addElement("formatVersion").addText("4");
        Element sys = root.addElement("view").addAttribute("name", "system");
        Element basic = root.addElement("view").addAttribute("name", "basic");
        Element det = root.addElement("view").addAttribute("name", "detailed");
        Element video = root.addElement("view").addAttribute("name", "video");

        PopulateView(sys,system.getSystemView());
        PopulateView(basic,system.getBasicView());
        PopulateView(det,system.getDetailedView());
        PopulateView(video,system.getVideoView());
        

        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(result,format);
        writer.write(document);
        
        return result.toString();
    }
        
    private void PopulateView(Element root,View v){
        if(v.getElementsCount()==0){
            root.addElement("Alert").addText("empty view!");
            return;
        }
        if(saveFile == null){
            root.addElement("Error").addText("savefile is null!");
            return;
        }
        for(int k=0;k<v.getElementsCount();k++){
            Object vo = v.getElementByIndex(k);
            if(vo instanceof Image){
                Image i = (Image)vo;
                
                Element e = root.addElement("image");
                e.addAttribute("name", i.getName()).addAttribute("extra", i.getExtra()?"true":"false");
                e.addElement("path").addText(i.getImagePath().getPath());
                AddVector2(e,"origin",i.getOrigin());
                AddVector2(e,"pos",i.getPos());
                AddVector2(e,"size",i.getSize());
                e.addElement("color").addText(i.getColor().getColor());
            }
            else if(vo instanceof Text){
                Text t = (Text)vo;
                
                Element e = root.addElement("text");
                e.addAttribute("name", t.getName()).addAttribute("extra", t.getExtra()?"true":"false");
                e.addElement("path").addText(t.getFontPath().getPath());
                e.addElement("fontsize").addText(t.getFontSize()+"");
                e.addElement("color").addText(t.getColor().getColor());
                e.addElement("forceuppercase").addText(t.forcingUpperCase()+"");
                AddVector2(e,"pos",t.getPos());
                AddVector2(e,"size",t.getSize());
            }
            else if(vo instanceof Datetime){
                Datetime d = (Datetime)vo;
                
                Element e = root.addElement("datetime");
                e.addAttribute("name", d.getName()).addAttribute("extra", d.getExtra()?"true":"false");
                e.addElement("path").addText(d.getFontPath().getPath());
                e.addElement("fontsize").addText(d.getFontSize()+"");
                e.addElement("color").addText(d.getColor().getColor());
                e.addElement("forceuppercase").addText(d.forcingUpperCase()+"");
                AddVector2(e,"pos",d.getPos());
                AddVector2(e,"size",d.getSize());
            }
            else if(vo instanceof Rating){
                Rating r = (Rating)vo;
                
                Element e = root.addElement("rating");
                e.addAttribute("name", r.getName()).addAttribute("extra", r.getExtra()?"true":"false");
                AddVector2(e,"pos",r.getPos());
                AddVector2(e,"size",r.getSize());
                e.addElement("filledpath").addText(r.getFilledPath().getPath());
                e.addElement("unfilledpath").addText(r.getUnfilledPath().getPath());
            }
            else if(vo instanceof Textlist){
                Textlist t = (Textlist)vo;
                
                Element e = root.addElement("rating");
                e.addAttribute("name", t.getName()).addAttribute("extra", t.getExtra()?"true":"false");
                e.addElement("selectorcolor").addText(t.getSelectorColor().getColor());
                e.addElement("selectedcolor").addText(t.getSelectedColor().getColor());
                e.addElement("primarycolor").addText(t.getPrimaryColor().getColor());
                e.addElement("secondarycolor").addText(t.getSecondaryColor().getColor());
                e.addElement("fontsize").addText(t.getFontSize()+"");
                e.addElement("path").addText(t.getFontPath().getPath());
                AddVector2(e,"pos",t.getPos());
                AddVector2(e,"size",t.getSize());
            }
            else {
                root.addElement("Error").addText("class: ViewObject");
            }
        }
    }
        
    private void AddVector2(Element root,String name,Vector2 v){
        root.addElement(name).addText(v.getX() + " " + v.getY());
    }
    
    public SaveFile getSaveFile(){
        return saveFile;
    }
    
    public void setPath(String path){
        rootDir = Paths.get(path);
        fontsPath = Paths.get(rootDir.toString()+"/_inc/fonts");
        imagesPath = Paths.get(rootDir.toString()+"/_inc/images");
    }
}
