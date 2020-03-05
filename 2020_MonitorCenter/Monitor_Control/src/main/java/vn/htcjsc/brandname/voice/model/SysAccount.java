package vn.htcjsc.brandname.voice.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Private
 */
@XmlRootElement
public class SysAccount implements Serializable {

    int id;
    String username;
    String password;
    String full_name;
    String description;
    String address;
    String phone;
    String email;
    String telegram;
    String voice;
    int status;
    Timestamp create_date;
    String create_by;
    Timestamp update_date;
    String update_by;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Timestamp getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Timestamp update_date) {
        this.update_date = update_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public SysAccount(int id, String username, String password, String full_name, String description, String address, String phone, String email, String telegram, String voice, int status, Timestamp create_date, String create_by, Timestamp update_date, String update_by) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.voice = voice;
        this.status = status;
        this.create_date = create_date;
        this.create_by = create_by;
        this.update_date = update_date;
        this.update_by = update_by;
    }

    public SysAccount() {
    }

}
