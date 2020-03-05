package vn.htc.monitor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.service.ProcessingAPI;
import vn.htc.monitor.service.ProcessingMessage;

@Path("/monitor")
public class APISendInfo {

//    @GET
//    @Path("/sendMessage")
//    @Produces("application/json")
//    public String addGetMessage(@Context HttpServletRequest request, @QueryParam("user") String user, @QueryParam("pass") String pass, @QueryParam("chatid") String chatid, @QueryParam("text") String text) {
//        String ip = Tool.getClientIpAddr(request);
//
//        System.out.println("user: " + user);
//        System.out.println("pass: " + pass);
//        System.out.println("ip: " + ip);
//        System.out.println("chatid: " + chatid);
//        System.out.println("text: " + text);
//
//        String result = ProcessingMessage.securityLogin(user, pass, ip, chatid, text);
//
//        return result;
//    } 
    @POST
    @Path("/sendinfo")
    @Produces("application/json")
    public String addPostInfo(@Context HttpServletRequest request, @QueryParam("user") String user, @QueryParam("pass") String pass, @QueryParam("type") String type, @QueryParam("content") String content) {
//        String ip = Tool.getClientIpAddr(request);

        System.out.println("user: " + user);
        System.out.println("pass: " + pass);
        System.out.println("type: " + type);
        System.out.println("content: " + content);
        String result = ProcessingAPI.securityLogin(user, pass, content, type);
        return result;
    }

    //
}
