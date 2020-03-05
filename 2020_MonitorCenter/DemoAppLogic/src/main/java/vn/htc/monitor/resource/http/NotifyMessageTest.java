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
import vn.htc.monitor.entity.rest.RequestNotifyMessage;

/**
 *
 * @author Private
 */
@Path("/apitest")
public class NotifyMessageTest {

    static final Logger logger = Logger.getLogger(NotifyMessageTest.class);
    @Context
    HttpServletRequest request;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doPostJson(String data) {
        //System.out.println("CO NHAN DUOC KHONG TA HAY BI LOI TU NGOAI ROI");
        //Doi tuong tac dong la account, do vay se tao nhieu doi tuong account va sync tren doi tuong nay
        System.out.println("Bat dau vao api");
        RequestNotifyMessage reqData = RequestNotifyMessage.toObject(data);
            System.out.println("Sau khi parse json");
            if (reqData == null) {
                return Response.status(HttpStatus.ORDINAL_200_OK).entity("Chuoi json string khong dinh dang abc").build();
            }
            System.out.println("Data truyen len co y nghia khong rong");
            //Xu li tiep o day
            System.out.println("User:"+reqData.getUser());
            System.out.println("Pass:"+reqData.getPass());
            System.out.println("Content:"+reqData.getContent());
            System.out.println("Type:"+reqData.getType());
            ////////
        
        int valueResponse = 0;
        //XU LI TOAN BO BILLING O DAY
        //1. CHARG: Tru tien sms tren tai khoan
        //2. REFUND: Hoan tra so tien sms cho tai khoan
        //3. BALANCE: Lay so du trong tai khoan
        //4. TOPUP: Nap tien vao tai khoan
        //5. RELOAD: Cap nhat gia tien sms cua moi mang doi voi tai khoan
        //6. CREATENEW: Tao acc moi len he thong billing cho tai khoan moi
        System.out.println("data="+data);
//            RequestMessage reqData = RequestMessage.toObject(data);
            
            return Response.status(HttpStatus.ORDINAL_200_OK).entity("Tiep nhan thong tin thanh cong" +data ).build();
            
            
    }


    

}
