/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htcjsc.brandname.voice.dao;

import java.util.ArrayList;
import vn.htcjsc.brandname.voice.model.SystemMonitor;

/**
 *
 * @author hoan
 */
public interface SystemMonitorDaoIF extends BasicDaoIF<SystemMonitor>{

    public ArrayList<SystemMonitor> listSystemMonitor();

    public ArrayList<SystemMonitor> view(int page, int maxRow, String name, String ip, int status);
    public int count(String key, String ip, String sento, int status) ;

  

}
