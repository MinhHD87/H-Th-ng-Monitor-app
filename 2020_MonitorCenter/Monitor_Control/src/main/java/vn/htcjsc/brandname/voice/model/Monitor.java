package vn.htcjsc.brandname.voice.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import vn.htcjsc.brandname.voice.bean.MyItem;

public class Monitor {

    int id;
    String name;
    String description;
    String ip;
    String pos;
    String username;
    String password;
    int status;
    int type;
    int parent;
    Timestamp timedown;
    Timestamp timeup;
    String check_status;
    String phone;
    String email;
    String telegram;
    String voice;
    ArrayList<Monitor> arr;
    ArrayList<String> str;
    List<MyItem> listHDD;
    List<MyItem> listRam;
    List<MyItem> listCPU;
    String timesreach;

    public String getTimesreach() {
        return timesreach;
    }

    public void setTimesreach(String timesreach) {
        this.timesreach = timesreach;
    }

    public List<MyItem> getListRam() {
        return listRam;
    }

    public void setListRam(List<MyItem> listRam) {
        this.listRam = listRam;
    }

    public List<MyItem> getListCPU() {
        return listCPU;
    }

    public void setListCPU(List<MyItem> listCPU) {
        this.listCPU = listCPU;
    }

    public List<MyItem> getListHDD() {
        return listHDD;
    }

    public void setListHDD(List<MyItem> listHDD) {
        this.listHDD = listHDD;
    }

    public ArrayList<String> getStr() {
        return str;
    }
    
    public void setStr(ArrayList<Monitor> arr) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Monitor arr1 : arr) {
            arrayList.add(arr1.getName());
        }
        this.str = arrayList;
    }

    public ArrayList<Monitor> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Monitor> arr) {
        this.arr = arr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public Timestamp getTimedown() {
        return timedown;
    }

    public void setTimedown(Timestamp timedown) {
        this.timedown = timedown;
    }

    public Timestamp getTimeup() {
        return timeup;
    }

    public void setTimeup(Timestamp timeup) {
        this.timeup = timeup;
    }

    public String getCheck_status() {
        return check_status;
    }

    public void setCheck_status(String check_status) {
        this.check_status = check_status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public Monitor(int id, String name, String description, String ip, String pos, String username, String password, int status, int type, int parent, Timestamp timedown, Timestamp timeup, String check_status, String phone, String email, String telegram, String voice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ip = ip;
        this.pos = pos;
        this.username = username;
        this.password = password;
        this.status = status;
        this.type = type;
        this.parent = parent;
        this.timedown = timedown;
        this.timeup = timeup;
        this.check_status = check_status;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.voice = voice;
    }

    public Monitor() {
    }

}
