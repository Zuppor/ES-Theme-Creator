/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.theme.editor.objects;

/**
 *
 * @author andrea
 */
public interface ChangesListener {
    public enum ChangeType {ELEMENT_ADDED,ELEMENT_REMOVED,VALUE_CHANGED};
    
    void onChangesHappened(ChangeType changeType);
}
