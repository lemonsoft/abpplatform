/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.admin.batches;

import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author ss
 */
public class SrcDecorator extends TableDecorator {

    public String getWebcam() {
        UserDAO actorData = (UserDAO) getCurrentRowObject();
        String action;
        if(actorData.getWebcam().equalsIgnoreCase("Y")){
            action = "<a href=\"initChange.io?recid=" + actorData.getID()+ "&webcam="+actorData.getWebcam()+"&batchid="+actorData.getBatchid()+"\"><img src=\"http://localhost:8085/ABP-Ver1/assets/images/Tick-Box.png\" width=20 height=20/></a>";
        }else{
            action = "<a href=\"initChange.io?recid=" + actorData.getID()+ "&webcam="+actorData.getWebcam()+"&batchid="+actorData.getBatchid()+"\"><img src=\"http://localhost:8085/ABP-Ver1/assets/images/wrong.png\" width=20 height=20/></a>";
        }
        
        return action;
    }

    public String getPhotoname() {
        UserDAO actorData = (UserDAO) getCurrentRowObject();
        String photo = "<img src=\"http://localhost:8085/ABP-Ver1/assets/images/NoPhotoAvailable.jpg\" width=50 height=50 />";
        return photo;
    }
    
}
