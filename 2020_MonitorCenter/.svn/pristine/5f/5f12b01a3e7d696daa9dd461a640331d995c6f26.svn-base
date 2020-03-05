/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htcjsc.brandname.voice.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import vn.htcjsc.brandname.voice.model.Monitor;

/**
 *
 * @author Private
 */
public interface ChartDaoIF extends BasicDaoIF<Monitor> {

    public ArrayList<Monitor> view(int page, int maxRow, String key, String phone, String email, int status);

    public int count(String key, String phone, String email, int status) ;

    public Monitor checkLoginDB(String user, String pass);

    public Monitor undoDelete(int accID);

    public Monitor deleteForEver(int accID);
    
    public Monitor findIdAndTime(int id,Timestamp timesreach);

}
