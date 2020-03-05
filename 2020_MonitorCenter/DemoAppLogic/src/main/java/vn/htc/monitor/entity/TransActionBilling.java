package vn.htc.monitor.entity;

import vn.htc.monitor.common.Tool;
import vn.htc.monitor.db.DBPool;
import vn.htc.monitor.resource.http.NotifyMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.log4j.Logger;

public class TransActionBilling {

    
    final Logger logger = Logger.getLogger(TransActionBilling.class);
    public static final HashMap<String, TransActionBilling> CACHE = new HashMap<>();

    int id;
    String billing_trans;
    String type_trans;
    int money_trans;
    String account_name;
    String action_account;
    int unit_price;
    int number_unit;
    String telco;
    Timestamp time_action;
    int balance_trans;
    int balance_before;
    String comment_note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBilling_trans() {
        return billing_trans;
    }

    public void setBilling_trans(String billing_trans) {
        this.billing_trans = billing_trans;
    }

    public String getType_trans() {
        return type_trans;
    }

    public void setType_trans(String type_trans) {
        this.type_trans = type_trans;
    }

    public int getMoney_trans() {
        return money_trans;
    }

    public void setMoney_trans(int money_trans) {
        this.money_trans = money_trans;
    }

    

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAction_account() {
        return action_account;
    }

    public void setAction_account(String action_account) {
        this.action_account = action_account;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    public int getNumber_unit() {
        return number_unit;
    }

    public void setNumber_unit(int number_unit) {
        this.number_unit = number_unit;
    }

    public String getTelco() {
        return telco;
    }

    public void setTelco(String telco) {
        this.telco = telco;
    }

    public Timestamp getTime_action() {
        return time_action;
    }

    public void setTime_action(Timestamp time_action) {
        this.time_action = time_action;
    }

    public int getBalance_trans() {
        return balance_trans;
    }

    public void setBalance_trans(int balance_trans) {
        this.balance_trans = balance_trans;
    }

    public int getBalance_before() {
        return balance_before;
    }

    public void setBalance_before(int balance_before) {
        this.balance_before = balance_before;
    }

    public String getComment_note() {
        return comment_note;
    }

    public void setComment_note(String comment_note) {
        this.comment_note = comment_note;
    }
    
    
    
    
}
