/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.resource.http;

import vn.htc.monitor.entity.Account;
import vn.htc.monitor.entity.rest.RequestMessage;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.mortbay.jetty.HttpStatus;

/**
 *
 * @author Private
 */
@Path("/message")
public class NotifyMessage {

    static final Logger logger = Logger.getLogger(NotifyMessage.class);
    @Context
    HttpServletRequest request;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doPostJson(String data) {
        //System.out.println("CO NHAN DUOC KHONG TA HAY BI LOI TU NGOAI ROI");
        //Doi tuong tac dong la account, do vay se tao nhieu doi tuong account va sync tren doi tuong nay
        
        int valueResponse = 0;
        //XU LI TOAN BO BILLING O DAY
        //1. CHARG: Tru tien sms tren tai khoan
        //2. REFUND: Hoan tra so tien sms cho tai khoan
        //3. BALANCE: Lay so du trong tai khoan
        //4. TOPUP: Nap tien vao tai khoan
        //5. RELOAD: Cap nhat gia tien sms cua moi mang doi voi tai khoan
        //6. CREATENEW: Tao acc moi len he thong billing cho tai khoan moi
        System.out.println("data="+data);
        try {
            RequestMessage reqData = RequestMessage.toObject(data);
            
            if (reqData == null) {
                return Response.status(HttpStatus.ORDINAL_200_OK).entity(CODE.FAILE_INFO_ACTION.val).build();
            }
            
            //Xu li logic billing o day
            String actionRequest = reqData.getAction();
            if(!actionRequest.equals("") && actionRequest!=null){
                actionRequest = actionRequest.toUpperCase();
            }
            switch(actionRequest){
                case "CHARG":   // Tru tien voi tham so account, numbersms, telcocode
                                int valueProcessCharg = charg(reqData.getAccount(),reqData.getSmsNumber(),reqData.getTelcoCode(),reqData.getGroupSMS(),reqData.getMessageID());
                                return Response.status(HttpStatus.ORDINAL_200_OK).entity(valueProcessCharg).build();
                case "REFUND":  // Hoan tien voi tham so account, numbersms, telcocode
                                int valueProcessRefund = refund(reqData.getAccount(),reqData.getSmsNumber(),reqData.getTelcoCode(),reqData.getGroupSMS(),reqData.getMessageID());
                                return Response.status(HttpStatus.ORDINAL_200_OK).entity(valueProcessRefund).build();
                case "BALANCE": // Kiem tra so du tai khoan voi tham so account
                                int valueProcessBalance = balance(reqData.getAccount());
                                return Response.status(HttpStatus.ORDINAL_200_OK).entity(valueProcessBalance).build();
                case "TOPUP":   // Cong tien vao tai khoan voi tham so account, money nap vao
                                int valueProcessTopup = topup(reqData.getAccount(),reqData.getMoney(),reqData.getNotecomment(),reqData.getAccAction());
                                return Response.status(HttpStatus.ORDINAL_200_OK).entity(valueProcessTopup).build();
                case "RELOAD":  // Cap nhat gia tien cua moi mang cho tai khoan voi tham so account, vtePrice, gpcPrice, vmsPrice, vnmPrice, blPrice
                                int valueProcessReload = reload(reqData.getAccount(),reqData.getVtePrice(),reqData.getGpcPrice(),reqData.getVmsPrice(),reqData.getVnmPrice(),reqData.getBlPrice(),reqData.getDdgPrice(),reqData.getVte_pe(),reqData.getGpc_pe(),reqData.getVms_pe(),reqData.getVnm_pe(),reqData.getBl_pe(),reqData.getDdg_pe(),reqData.getMonthly_price_active());
                                return Response.status(HttpStatus.ORDINAL_200_OK).entity(valueProcessReload).build();
                case "CREATENEW": // Tao moi tai khoan vao he thong billing voi tham so account, vtePrice, gpcPrice, vmsPrice, vnmPrice, blPrice, money
                                int valueProcessCreate = createNew(reqData.getAccount(),reqData.getVtePrice(),reqData.getGpcPrice(),reqData.getVmsPrice(),reqData.getVnmPrice(),reqData.getBlPrice(),reqData.getDdgPrice(),reqData.getMoney(),reqData.getVte_pe(),reqData.getGpc_pe(),reqData.getVms_pe(),reqData.getVnm_pe(),reqData.getBl_pe(),reqData.getDdg_pe(),reqData.getMonthly_price_active());
                                return Response.status(HttpStatus.ORDINAL_200_OK).entity(valueProcessCreate).build();
                default:
                                break;
            }
            return Response.status(HttpStatus.ORDINAL_200_OK).entity(CODE.FAILE_INFO_ACTION.val).build();
            
        } catch (Exception e) {
            return Response.status(HttpStatus.ORDINAL_200_OK).entity(CODE.FAILE_INFO_ACTION.val).build();
        }
    }


    private int charg(String account, int smsNumber, String telcoCode, int groupSMS, String messageID) {
        int result = CODE.CHARG_FAILE.val;
        Account acc = Account.getAccount4Cache(account);
        if(acc!=null && smsNumber>0){
            result = acc.charg(account, smsNumber, telcoCode, groupSMS, messageID);
        }
        return result;
    }

    private int refund(String account, int smsNumber, String telcoCode, int groupSms,String messageID) {
        int result = CODE.REFUND_FAILE.val;
        Account acc = Account.getAccount4Cache(account);
        if(acc!=null && smsNumber>0){
            result = acc.refund(account, smsNumber, telcoCode,groupSms,messageID);
        }
        return result;
    }

    private int balance(String account) {
        int result = 0;
        Account acc = Account.getAccount4Cache(account);
        if(acc!=null){
            result = acc.balance(account);
        }
        return result;
    }

    private int topup(String account, int money, String notecomment,String accAction) {
        int result = CODE.TOPUP_FAILE.val;
        Account acc = Account.getAccount4Cache(account);
        if(acc!=null && money>0){
            result = acc.topup(account,money,notecomment,accAction);
        }
        return result;
    }

    private int reload(String account, int vtePrice, int gpcPrice, int vmsPrice, int vnmPrice, int blPrice, int ddgPrice, String vtePE, String gpcPE, String vmsPE, String vnmPE, String blPE, String ddgPE, int monthlyPrice) {
        int result = CODE.RELOAD_PRICE_FAILE.val;
        Account acc = Account.getAccount4Cache(account);
        int monthlyPriceOld = acc.getMonthly_price_active();
        if(monthlyPrice>monthlyPriceOld){//Neu nhap phi duy tri lon hon thi tru luon so chenh trong tai khoan
            int sotienChenh = monthlyPrice-monthlyPriceOld;
            int reChargChenh = Account.chargMonthlyChenh(account,sotienChenh);
        }
        if(acc!=null && vtePrice>=0 && gpcPrice>=0 && vmsPrice>=0 && vnmPrice>=0 && blPrice>=0 && ddgPrice>=0){
            result = acc.reload(account,vtePrice,gpcPrice,vmsPrice,vnmPrice,blPrice,ddgPrice,vtePE,gpcPE,vmsPE,vnmPE,blPE,ddgPE,monthlyPrice);
        }
        if(acc==null){
            result = createNew(account,vtePrice,gpcPrice,vmsPrice,vnmPrice,blPrice,ddgPrice,0,vtePE,gpcPE,vmsPE,vnmPE,blPE,ddgPE,monthlyPrice);
        }
        return result;
    }

    private int createNew(String account, int vtePrice, int gpcPrice, int vmsPrice, int vnmPrice, int blPrice, int ddgPrice, int money, String vtePE, String gpcPE, String vmsPE, String vnmPE, String blPE, String ddgPE, int monthlyPrice) {
        int result = CODE.CREATE_NEW_FAILE.val;
        Account acc = new Account();
        if(vtePrice>=0 && gpcPrice>=0 && vmsPrice>=0 && vnmPrice>=0 && blPrice>=0 && ddgPrice>=0){
            result = acc.createNew(account,vtePrice,gpcPrice,vmsPrice,vnmPrice,blPrice,ddgPrice,money);
        }
        return result;
    }
    
    public static enum CODE {
        CHARG_SUSSCESS(1),
        CHARG_FAILE(2),
        REFUND_SUSSCESS(3),
        REFUND_FAILE(4),
        TOPUP_SUSSCESS(5),
        TOPUP_FAILE(6),
        RELOAD_PRICE_SUSSCESS(7),
        RELOAD_PRICE_FAILE(8),
        CREATE_NEW_SUSSCESS(9),
        CREATE_NEW_FAILE(10),
        FAILE_INFO_ACTION(0),
        ;
        public int val;

        private CODE(int val) {
            this.val = val;
        }
    }
    
    public static enum ACTION {
        CHARG("CHARG"),
        REFUND("REFUND"),
        BALANCE("BALANCE"),
        TOPUP("TOPUP"),
        RELOAD("RELOAD"),
        CREATENEW("CREATENEW"),
        ;
        public String val;

        private ACTION(String val) {
            this.val = val;
        }
    }
    
    

}
