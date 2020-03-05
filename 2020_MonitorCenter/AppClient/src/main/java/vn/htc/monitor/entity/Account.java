package vn.htc.monitor.entity;

import vn.htc.monitor.app.AppStart;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.db.DBPool;
import vn.htc.monitor.resource.http.NotifyMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.log4j.Logger;

public class Account {

    
    final Logger logger = Logger.getLogger(Account.class);
    public static final HashMap<String, Account> CACHE = new HashMap<>();
//    public static final HashMap<String, > CACHE = new HashMap<>();
    
//tiglong messbird chinasms 
    static {
        Account dao = new Account();
        ArrayList<Account> cache = dao.getAgentcyAndUserOnBoot();
        for (Account one : cache) {
            CACHE.put(one.getAccountName(), one);
        }
    }
    
    
    public static void reloadCache(){
        Account dao = new Account();
        ArrayList<Account> cache = dao.getAgentcyAndUserOnBoot();
        if(cache.size()>0){
            synchronized (CACHE) {
                CACHE.clear();
                for (Account one : cache) {
                    CACHE.put(one.getAccountName(), one);
                }
                CACHE.notify();
            }
            
        }
        
    }


    public static void remove4Cache(Account acc) {
        synchronized (CACHE) {
            if (acc != null) {
                CACHE.remove(acc.getAccountName());
            }
            CACHE.notify();
        }
    }
    
    public static void add2Cache(Account acc) {
        synchronized (CACHE) {
            if (acc != null) {
                CACHE.put(acc.getAccountName(), acc);
            }
            CACHE.notify();
        }
    }
    
    public static void update2Cache(Account acc) {
        synchronized (CACHE) {
            if (acc != null) {
                CACHE.replace(acc.getAccountName(), acc);
            }
            CACHE.notify();
        }
    }

    public static Account getAccount4Cache(String user) {
        synchronized (CACHE) {
            return CACHE.get(user);
        }
    }
    
    public static ArrayList<Account> getAccount4Cache() {
        ArrayList all = new ArrayList();
        synchronized (CACHE) {
            Collection<Account> values = CACHE.values();
            for (Account one : values) {
                all.add(one);
            }
            CACHE.notify();
        }
        
        return all;
    }
    
    public static String[] getAccountPrepaid4Cache() {
        String listAcc = "congnxcohtc";
        synchronized (CACHE) {
            Collection<Account> values = CACHE.values();
            for (Account one : values) {
                if(one.getPrepaid()==1){
                    listAcc = listAcc +","+one.getAccountName();
                }
            }
            CACHE.notify();
        }
        listAcc = listAcc.replace("congnxcohtc,", "");
        listAcc = listAcc.replace("congnxcohtc", "");
        return listAcc.split(",");
    }
    
    public static void updateDB4Cache() {
        Account acc = null;
        synchronized (CACHE) {
            Collection<Account> values = CACHE.values();
            for (Account one : values) {
                boolean updateValue = update2DB(one);
                if(!updateValue){
                    System.out.println("Update DB for account "+one.getAccountName()+" is faile at "+System.currentTimeMillis());
                }
            }
            CACHE.notify();
        }
    }
    
    
    public static void showAcc4Cache() {
        Account acc = null;
        synchronized (CACHE) {
            Collection<Account> values = CACHE.values();
            for (Account one : values) {
                    System.out.println(one.getAccountName()+": balance="+one.getBalance()+", vtePrice="+one.getVtePrice()+", gpcPrice="+one.getGpcPrice()+", vmsPrice="+one.getVmsPrice()+", vnmPrice="+one.getVnmPrice()+", blPrice="+one.getBlPrice());
            }
            CACHE.notify();
        }
    }
    
    public static int chargMonthly(String accounts) {
        int chargMon = 0;
        
        Account acc = Account.CACHE.get(accounts);
        synchronized (CACHE) {
            int monthlyMoney = acc.getMonthly_price_active();
            int balanceBefore = acc.getBalance();
            int balanceAfter = acc.getBalance() - monthlyMoney;
            
            acc.setBalance(balanceAfter);//Thiet lap lai so du
            CACHE.replace(accounts, acc);//Day lai vao cach acc nay
            
            
            //Day vao queue ghi log
            TransActionBilling oneAction = new TransActionBilling();
            oneAction.setAccount_name(accounts);
            oneAction.setNumber_unit(0);
//            oneAction.setTelco(telcoCode);
//            oneAction.setUnit_price(currentPrice);
            oneAction.setMoney_trans(monthlyMoney);
            oneAction.setBalance_trans(balanceAfter);
            oneAction.setBalance_before(balanceBefore);
            oneAction.setAction_account("SYS BILLING");
            oneAction.setBilling_trans("MONTHLYCHARG"+String.valueOf(System.currentTimeMillis()));
            oneAction.setType_trans("MONTHLY");
            oneAction.setComment_note("Tru phi duy tri nhan cua tai khoan(khong phu thuoc vao so nhan)");
            AppStart.QUEUE_TRANSACTION_BILLING.enqueue(oneAction);
            //Ket thuc ghi log transaction billing
            
            CACHE.notify();
        }
        
        return chargMon;
    }
    
    
    
    public static int chargMonthlyChenh(String accounts, int sotienChenh) {
        int chargMon = 0;
        
        Account acc = Account.CACHE.get(accounts);
        synchronized (CACHE) {
//            int monthlyMoney = acc.getMonthly_price_active();
            int balanceBefore = acc.getBalance();
            int balanceAfter = acc.getBalance() - sotienChenh;
            
            acc.setBalance(balanceAfter);//Thiet lap lai so du
            CACHE.replace(accounts, acc);//Day lai vao cach acc nay
            
            
            //Day vao queue ghi log
            TransActionBilling oneAction = new TransActionBilling();
            oneAction.setAccount_name(accounts);
            oneAction.setNumber_unit(0);
//            oneAction.setTelco(telcoCode);
//            oneAction.setUnit_price(currentPrice);
            oneAction.setMoney_trans(sotienChenh);
            oneAction.setBalance_trans(balanceAfter);
            oneAction.setBalance_before(balanceBefore);
            oneAction.setAction_account("SYS BILLING");
            oneAction.setBilling_trans("MONTHLYCHARG"+String.valueOf(System.currentTimeMillis()));
            oneAction.setType_trans("MONTHLY");
            oneAction.setComment_note("Tru them phi duy tri nhan cua tai khoan(Tru them do chenh phi duy tri)");
            AppStart.QUEUE_TRANSACTION_BILLING.enqueue(oneAction);
            //Ket thuc ghi log transaction billing
            
            CACHE.notify();
        }
        
        return chargMon;
    }

    
    
    
    //////////////Cac phuong thuc tru tien va cap nhat tien cung nhu tao tai khoan len cache
    public int charg(String account, int smsNumber, String telcoCode, int groupSms, String messageID) {
        int result = NotifyMessage.CODE.CHARG_FAILE.val;
        synchronized (CACHE) {
            Account acc =  CACHE.get(account);
            int currentQuotaSms = acc.getSms_quota();
            int quotaAfter = currentQuotaSms - smsNumber;
            if(quotaAfter>=0){
                acc.setSms_quota(quotaAfter);//Thiet lap lai quota
                CACHE.replace(account, acc);//Day lai vao cach acc nay
                return NotifyMessage.CODE.CHARG_SUSSCESS.val;
            }
            int currentPrice = getPriceTelco(telcoCode,groupSms);
            int totalPrice = smsNumber * currentPrice;
            int balanceBefore = acc.getBalance();
            int balanceAfter = acc.getBalance() - smsNumber * currentPrice;
            if(balanceAfter>0 && balanceAfter<acc.getBalance()){
                acc.setBalance(balanceAfter);//Thiet lap lai so du
                CACHE.replace(account, acc);//Day lai vao cach acc nay
                result = NotifyMessage.CODE.CHARG_SUSSCESS.val;
            }
            //Day vao queue ghi log
            TransActionBilling oneAction = new TransActionBilling();
            oneAction.setAccount_name(account);
            oneAction.setNumber_unit(smsNumber);
            oneAction.setTelco(telcoCode);
            oneAction.setUnit_price(currentPrice);
            oneAction.setMoney_trans(totalPrice);
            oneAction.setBalance_trans(balanceAfter);
            oneAction.setBalance_before(balanceBefore);
            oneAction.setAction_account("APP_API");
            oneAction.setBilling_trans(messageID);
            oneAction.setType_trans("CHARG");
            AppStart.QUEUE_TRANSACTION_BILLING.enqueue(oneAction);
            //Ket thuc ghi log transaction billing
            return result;
        }
    }

    public int refund(String account, int smsNumber, String telcoCode, int groupSms, String messageID) {
        int result = NotifyMessage.CODE.REFUND_FAILE.val;
        synchronized (CACHE) {
            Account acc =  CACHE.get(account);
            int currentPrice = getPriceTelco(telcoCode,groupSms);
            int balanceBefore = acc.getBalance();
            int totalPrice = smsNumber * currentPrice;
            int balanceAfter = acc.getBalance() + smsNumber * currentPrice;
            if(balanceAfter>acc.getBalance()){
                acc.setBalance(balanceAfter);//Thiet lap lai so du
                CACHE.replace(account, acc);//Day lai vao cach acc nay
                result = NotifyMessage.CODE.REFUND_SUSSCESS.val;
            }
            //Day vao queue ghi log
            TransActionBilling oneAction = new TransActionBilling();
            oneAction.setAccount_name(account);
            oneAction.setNumber_unit(smsNumber);
            oneAction.setTelco(telcoCode);
            oneAction.setUnit_price(currentPrice);
            oneAction.setMoney_trans(totalPrice);
            oneAction.setBalance_trans(balanceAfter);
            oneAction.setBalance_before(balanceBefore);
            oneAction.setAction_account("APP_API");
            oneAction.setBilling_trans(messageID);
            oneAction.setType_trans("REFUND");
            AppStart.QUEUE_TRANSACTION_BILLING.enqueue(oneAction);
            //Ket thuc ghi log transaction billing
            return result;
        }
    }

    public int balance(String account) {
        synchronized (CACHE) {
            Account acc =  CACHE.get(account);
            return acc.getBalance();
        }
    }

    public int topup(String account, int money, String notecomment,String accAction) {
        int result = NotifyMessage.CODE.TOPUP_FAILE.val;
        synchronized (CACHE) {
            Account acc =  CACHE.get(account);
            int balanceBefore = acc.getBalance();
            int balanceAfter = acc.getBalance() + money;
//            int quotaSMSAfter = acc.getSms_quota() + smsNumber;
            if(balanceAfter>acc.getBalance()){
                acc.setBalance(balanceAfter);//Thiet lap lai so du
//                acc.setSms_quota(quotaSMSAfter);//Thiet lap lai so du
                CACHE.replace(account, acc);//Day lai vao cach acc nay
                result = NotifyMessage.CODE.TOPUP_SUSSCESS.val;
            }
            //Day vao queue ghi log
            TransActionBilling oneAction = new TransActionBilling();
            oneAction.setAccount_name(account);
            oneAction.setNumber_unit(0);
//            oneAction.setTelco(telcoCode);
//            oneAction.setUnit_price(currentPrice);
            oneAction.setMoney_trans(money);
            oneAction.setBalance_trans(balanceAfter);
            oneAction.setBalance_before(balanceBefore);
            oneAction.setAction_account(accAction);
            oneAction.setBilling_trans("TOPUP"+String.valueOf(System.currentTimeMillis()));
            oneAction.setType_trans("TOPUP");
            oneAction.setComment_note(notecomment);
            AppStart.QUEUE_TRANSACTION_BILLING.enqueue(oneAction);
            //Ket thuc ghi log transaction billing
            return result;
        }
    }

    public int reload(String account, int vtePrice, int gpcPrice, int vmsPrice, int vnmPrice, int blPrice, int ddgPrice, String vtePE, String gpcPE, String vmsPE, String vnmPE, String blPE, String ddgPE, int monthlyPrice) {
        
        synchronized (CACHE) {
            Account acc =  CACHE.get(account);
            acc.setVtePrice(vtePrice);
            acc.setGpcPrice(gpcPrice);
            acc.setVmsPrice(vmsPrice);
            acc.setVnmPrice(vnmPrice);
            acc.setBlPrice(blPrice);
            acc.setDdgPrice(ddgPrice);
            acc.setVte_pe(vtePE);
            acc.setGpc_pe(gpcPE);
            acc.setVms_pe(vmsPE);
            acc.setVnm_pe(vnmPE);
            acc.setBl_pe(blPE);
            acc.setDdg_pe(ddgPE);
            acc.setMonthly_price_active(monthlyPrice);
            CACHE.replace(account, acc);//Day lai vao cach acc nay
            return NotifyMessage.CODE.RELOAD_PRICE_FAILE.val;
        }
    }

    public int createNew(String account, int vtePrice, int gpcPrice, int vmsPrice, int vnmPrice, int blPrice, int ddgPrice, int money) {
        int result = NotifyMessage.CODE.CREATE_NEW_FAILE.val;
        synchronized (CACHE) {
            Account acc = new Account();
            acc.setAccountName(account);
            acc.setVtePrice(vtePrice);
            acc.setGpcPrice(gpcPrice);
            acc.setVmsPrice(vmsPrice);
            acc.setVnmPrice(vnmPrice);
            acc.setBlPrice(blPrice);
            acc.setDdgPrice(ddgPrice);
            acc.setBalance(money);
            CACHE.put(account, acc);//Push vao cach tai khoan moi
            return result;
        }
    }

    /**
     * Bao Gom ca Tai Khoan Bi Khoa
     *
     * @return
     */
    
    private static boolean update2DB(Account one) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE ACCOUNTS SET BALANCE = ?,VTE_PRICE = ?,GPC_PRICE = ?,VMS_PRICE = ? ,VNM_PRICE = ?,BL_PRICE=?,DDG_PRICE=?, GPC_PE=?, VTE_PE=?, VMS_PE=?, VNM_PE=?, BL_PE=?,DDG_PE=?, MONTHLY_PRICE_ACTIVE=?, SMS_QUOTA=? WHERE USERNAME = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, one.getBalance());
            pstm.setInt(i++, one.getVtePrice());
            pstm.setInt(i++, one.getGpcPrice());
            pstm.setInt(i++, one.getVmsPrice());
            pstm.setInt(i++, one.getVnmPrice());
            pstm.setInt(i++, one.getBlPrice());
            pstm.setInt(i++, one.getDdgPrice());
            
            pstm.setString(i++, one.getGpc_pe());
            pstm.setString(i++, one.getVte_pe());
            pstm.setString(i++, one.getVms_pe());
            pstm.setString(i++, one.getVnm_pe());
            pstm.setString(i++, one.getBl_pe());
            pstm.setString(i++, one.getDdg_pe());
            pstm.setInt(i++, one.getMonthly_price_active());
            pstm.setInt(i++, one.getSms_quota());
            
            pstm.setString(i++, one.getAccountName());
            if (pstm.executeUpdate() == 1) {
                flag = true;
            }
        } catch (Exception e) {
            System.out.println(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }
    
    
    private ArrayList<Account> getAgentcyAndUserOnBoot() {
        ArrayList all = new ArrayList();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT USERNAME,BALANCE,GPC_PRICE,VTE_PRICE,VMS_PRICE,VNM_PRICE,BL_PRICE,DDG_PRICE,GPC_PE,VTE_PE,VMS_PE,VNM_PE,BL_PE,DDG_PE,SMS_QUOTA,MONTHLY_PRICE_ACTIVE,SMS_QUOTA,ALARMMONEY,PREPAID FROM ACCOUNTS WHERE PREPAID = 1 AND (USER_TYPE = ? OR USER_TYPE = ?) AND STATUS=1";

        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, TYPE.AGENCY.val);
            pstm.setInt(i++, TYPE.USER.val);

            rs = pstm.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAccountName(rs.getString("USERNAME"));
                acc.setBalance(rs.getInt("BALANCE"));
                acc.setGpcPrice(rs.getInt("GPC_PRICE"));
                acc.setVtePrice(rs.getInt("VTE_PRICE"));
                acc.setVmsPrice(rs.getInt("VMS_PRICE"));
                acc.setVnmPrice(rs.getInt("VNM_PRICE"));
                acc.setBlPrice(rs.getInt("BL_PRICE"));
                acc.setDdgPrice(rs.getInt("DDG_PRICE"));
                
                acc.setVte_pe(rs.getString("VTE_PE"));
                acc.setGpc_pe(rs.getString("GPC_PE"));
                acc.setVms_pe(rs.getString("VMS_PE"));
                acc.setVnm_pe(rs.getString("VNM_PE"));
                acc.setBl_pe(rs.getString("BL_PE"));
                acc.setDdg_pe(rs.getString("DDG_PE"));
                acc.setSms_quota(rs.getInt("SMS_QUOTA"));
                acc.setMonthly_price_active(rs.getInt("MONTHLY_PRICE_ACTIVE"));
                acc.setAlarmmoney(rs.getInt("ALARMMONEY"));
                acc.setPrepaid(rs.getInt("PREPAID"));
                
                all.add(acc);
            }
        } catch (SQLException ex) {
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }
    
    
    public static String[] getUserPrepaidActive() {
        String listAcc = "congnxcohtc";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT USERNAME FROM ACCOUNTS WHERE PREPAID = 1 AND STATUS = 1";

        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            

            rs = pstm.executeQuery();
            while (rs.next()) {
                String accountNameOne = rs.getString("USERNAME");
                listAcc = listAcc +","+accountNameOne;
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        listAcc = listAcc.replace("congnxcohtc,", "");
        listAcc = listAcc.replace("congnxcohtc", "");
        return listAcc.split(",");
    }
    
    
    public int getAlarm4Db(String username) {
//        ArrayList all = new ArrayList();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT ALARMMONEY FROM ACCOUNTS WHERE USERNAME = ?";
        int alarmValue=0;
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
//            pstm.setInt(i++, TYPE.AGENCY.val);
            pstm.setString(i++, username);

            rs = pstm.executeQuery();
            while (rs.next()) {
                alarmValue = rs.getInt("ALARMMONEY");
            }
        } catch (SQLException ex) {
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return alarmValue;
    }

    
    private String accountName;
    private int balance;
    private int gpcPrice;
    private int vtePrice;
    private int vmsPrice;
    private int vnmPrice;
    private int blPrice;
    private int ddgPrice;
    
    private String gpc_pe;
    private String vte_pe;
    private String vms_pe;
    private String vnm_pe;
    private String bl_pe;
    private String ddg_pe;
    private int sms_quota;
    private int monthly_price_active;
    private int alarmmoney;
    private int prepaid;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getGpcPrice() {
        return gpcPrice;
    }

    public void setGpcPrice(int gpcPrice) {
        this.gpcPrice = gpcPrice;
    }

    public int getVtePrice() {
        return vtePrice;
    }

    public void setVtePrice(int vtePrice) {
        this.vtePrice = vtePrice;
    }

    public int getVmsPrice() {
        return vmsPrice;
    }

    public void setVmsPrice(int vmsPrice) {
        this.vmsPrice = vmsPrice;
    }

    public int getVnmPrice() {
        return vnmPrice;
    }

    public void setVnmPrice(int vnmPrice) {
        this.vnmPrice = vnmPrice;
    }

    public int getBlPrice() {
        return blPrice;
    }

    public void setBlPrice(int blPrice) {
        this.blPrice = blPrice;
    }

    public String getGpc_pe() {
        return gpc_pe;
    }

    public void setGpc_pe(String gpc_pe) {
        this.gpc_pe = gpc_pe;
    }

    public String getVte_pe() {
        return vte_pe;
    }

    public void setVte_pe(String vte_pe) {
        this.vte_pe = vte_pe;
    }

    public String getVms_pe() {
        return vms_pe;
    }

    public void setVms_pe(String vms_pe) {
        this.vms_pe = vms_pe;
    }

    public String getVnm_pe() {
        return vnm_pe;
    }

    public void setVnm_pe(String vnm_pe) {
        this.vnm_pe = vnm_pe;
    }

    public String getBl_pe() {
        return bl_pe;
    }

    public void setBl_pe(String bl_pe) {
        this.bl_pe = bl_pe;
    }

    public int getSms_quota() {
        return sms_quota;
    }

    public void setSms_quota(int sms_quota) {
        this.sms_quota = sms_quota;
    }

    public int getMonthly_price_active() {
        return monthly_price_active;
    }

    public int getDdgPrice() {
        return ddgPrice;
    }

    public void setDdgPrice(int ddgPrice) {
        this.ddgPrice = ddgPrice;
    }

    public String getDdg_pe() {
        return ddg_pe;
    }

    public void setDdg_pe(String ddg_pe) {
        this.ddg_pe = ddg_pe;
    }

    
    public void setMonthly_price_active(int monthly_price_active) {
        this.monthly_price_active = monthly_price_active;
    }

    public int getAlarmmoney() {
        return alarmmoney;
    }

    public void setAlarmmoney(int alarmmoney) {
        this.alarmmoney = alarmmoney;
    }

    public int getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(int prepaid) {
        this.prepaid = prepaid;
    }

    
    
    
    public static enum TYPE {

        USER(0, "Người dùng"), // Create Ads - Manager allow Createby Id                  USER
        ADMIN(1, "Quyền quản trị"), // ADMIN
        AGENCY(2, "Đại lý"), // Duoc phep ket noi gui Qua API  // TODO co nen cho tao tk con hay khong ??
        AGENCY_MANAGER(3, "Quản lý Đại lý") // Chi co quyen quan ly thong ke, khong duoc lam gi ca cao hon quyen user la duoc thong ke nhieu dai ly
        ;
        public int val;
        public String name;

        private TYPE(int val, String name) {
            this.val = val;
            this.name = name;
        }
    }
    
    private int getPriceTelco(String telcoCode,int groupsms){
        switch(telcoCode.toUpperCase()){
            case "VTE":
                    PriceTelco vtePE = PriceTelco.json2Objec(this.vte_pe);
                    if(groupsms==0){return vtePE.getGroup0Price();}
                    if(groupsms==1){return vtePE.getGroup1Price();}
                    if(groupsms==2){return vtePE.getGroup2Price();}
                    if(groupsms==3){return vtePE.getGroup3Price();}
                    if(groupsms==4){return vtePE.getGroup4Price();}
                    if(groupsms==5){return vtePE.getGroup5Price();}
                    if(groupsms==6){return vtePE.getGroup6Price();}
                    if(groupsms==7){return vtePE.getGroup7Price();}
                    if(groupsms==8){return vtePE.getGroup8Price();}
                    if(groupsms==9){return vtePE.getGroup9Price();}
            case "GPC":
                    PriceTelco gpcPE = PriceTelco.json2Objec(this.gpc_pe);
                    if(groupsms==0){return gpcPE.getGroup0Price();}
                    if(groupsms==1){return gpcPE.getGroup1Price();}
                    if(groupsms==2){return gpcPE.getGroup2Price();}
                    if(groupsms==3){return gpcPE.getGroup3Price();}
                    if(groupsms==4){return gpcPE.getGroup4Price();}
                    if(groupsms==5){return gpcPE.getGroup5Price();}
                    if(groupsms==6){return gpcPE.getGroup6Price();}
                    if(groupsms==7){return gpcPE.getGroup7Price();}
                    if(groupsms==8){return gpcPE.getGroup8Price();}
                    if(groupsms==9){return gpcPE.getGroup9Price();}
            case "VMS":
                    PriceTelco vmsPE = PriceTelco.json2Objec(this.vms_pe);
                    if(groupsms==0){return vmsPE.getGroup0Price();}
                    if(groupsms==1){return vmsPE.getGroup1Price();}
                    if(groupsms==2){return vmsPE.getGroup2Price();}
                    if(groupsms==3){return vmsPE.getGroup3Price();}
                    if(groupsms==4){return vmsPE.getGroup4Price();}
                    if(groupsms==5){return vmsPE.getGroup5Price();}
                    if(groupsms==6){return vmsPE.getGroup6Price();}
                    if(groupsms==7){return vmsPE.getGroup7Price();}
                    if(groupsms==8){return vmsPE.getGroup8Price();}
                    if(groupsms==9){return vmsPE.getGroup9Price();}
            case "VNM":
                    PriceTelco vnmPE = PriceTelco.json2Objec(this.vnm_pe);
                    if(groupsms==0){return vnmPE.getGroup0Price();}
                    if(groupsms==1){return vnmPE.getGroup1Price();}
                    if(groupsms==2){return vnmPE.getGroup2Price();}
                    if(groupsms==3){return vnmPE.getGroup3Price();}
                    if(groupsms==4){return vnmPE.getGroup4Price();}
                    if(groupsms==5){return vnmPE.getGroup5Price();}
                    if(groupsms==6){return vnmPE.getGroup6Price();}
                    if(groupsms==7){return vnmPE.getGroup7Price();}
                    if(groupsms==8){return vnmPE.getGroup8Price();}
                    if(groupsms==9){return vnmPE.getGroup9Price();}
            case "BL":
                    PriceTelco blPE = PriceTelco.json2Objec(this.bl_pe);
                    if(groupsms==0){return blPE.getGroup0Price();}
                    if(groupsms==1){return blPE.getGroup1Price();}
                    if(groupsms==2){return blPE.getGroup2Price();}
                    if(groupsms==3){return blPE.getGroup3Price();}
                    if(groupsms==4){return blPE.getGroup4Price();}
                    if(groupsms==5){return blPE.getGroup5Price();}
                    if(groupsms==6){return blPE.getGroup6Price();}
                    if(groupsms==7){return blPE.getGroup7Price();}
                    if(groupsms==8){return blPE.getGroup8Price();}
                    if(groupsms==9){return blPE.getGroup9Price();}
            case "DDG":
                    PriceTelco ddgPE = PriceTelco.json2Objec(this.ddg_pe);
                    if(groupsms==0){return ddgPE.getGroup0Price();}
                    if(groupsms==1){return ddgPE.getGroup1Price();}
                    if(groupsms==2){return ddgPE.getGroup2Price();}
                    if(groupsms==3){return ddgPE.getGroup3Price();}
                    if(groupsms==4){return ddgPE.getGroup4Price();}
                    if(groupsms==5){return ddgPE.getGroup5Price();}
                    if(groupsms==6){return ddgPE.getGroup6Price();}
                    if(groupsms==7){return ddgPE.getGroup7Price();}
                    if(groupsms==8){return ddgPE.getGroup8Price();}
                    if(groupsms==9){return ddgPE.getGroup9Price();}
                
            default:
                    return 0;
        }
    }
    
}
