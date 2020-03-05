/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

/**
 *
 * @author Private
 */
public class FirewallCommand {

    public String executeAddIPCmd(String ip, int port) throws Exception {
        int result = doAddIPCmd(ip, port);
        if (result == 0) {
            return reloadFireWall();
        } else {
            return "false";
        }
    }

    private int doAddIPCmd(String ip, int port) {
        int iExitValue = 0;
        CommandLine executeAddIPCmd = new CommandLine("firewall-cmd");
        executeAddIPCmd.addArgument("--permanent");
        executeAddIPCmd.addArgument("--zone=public");
        executeAddIPCmd.addArgument("--add-rich-rule=\"\"rule family=ipv4 source address=" + ip + " port port=" + port + " protocol=tcp accept\"\"", false);
        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
        oDefaultExecutor.setExitValue(0);
        try {
            iExitValue = oDefaultExecutor.execute(executeAddIPCmd);
        } catch (Exception e) {
            System.err.println("Execution failed.");
            e.printStackTrace();
        }
        return iExitValue;
    }

    public String executeRemoveIPCmd(String ip, int port) throws Exception {
        int remove = doRemoveIPCmd(ip, port);
        if (remove == 0) {
            return reloadFireWall();
        } else {
            return "false";
        }
    }

    private int doRemoveIPCmd(String ip, int port) {
        int iExitValue = 0;
        CommandLine executeAddIPCmd = new CommandLine("firewall-cmd");
        executeAddIPCmd.addArgument("--permanent");
        executeAddIPCmd.addArgument("--zone=public");
        executeAddIPCmd.addArgument("--remove-rich-rule=\"\"rule family=ipv4 source address=" + ip + " port port=" + port + " protocol=tcp accept\"\"", false);
        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
        oDefaultExecutor.setExitValue(0);
        try {
            iExitValue = oDefaultExecutor.execute(executeAddIPCmd);
        } catch (Exception e) {
            System.err.println("Execution failed.");
            e.printStackTrace();
        }
        return iExitValue;
    }

    private String reloadFireWall() throws Exception {
        String cmd = "firewall-cmd --reload";
        return executeCommand(cmd);
    }

    public String checkFirewall() {
        String cmd = "firewall-cmd --list-rich-rules";
        return executeCommand(cmd);
    }
//    public void testPing() {
//        CommandLine commandLine = new CommandLine("firewall-cmd");
//        commandLine.addArgument("--permanent");
//        commandLine.addArgument("--zone=public");
//        commandLine.addArgument("--add-rich-rule=\"\"rule family=ipv4 source address=127.0.0.123/32 port port=1233 protocol=tcp accept\"\"", false);
//        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
//        oDefaultExecutor.setExitValue(0);
//        try {
//            int iExitValue = oDefaultExecutor.execute(commandLine);
//            Tool.debug("iExitValue:" + iExitValue);
//        } catch (Exception e) {
//            System.err.println("Execution failed.");
//            e.printStackTrace();
//        }
//
//    }

    private String executeCommand(String command) {
//        Tool.debug("Execute Command:" + command);
        StringBuilder output = new StringBuilder();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
//            int tmp = p.waitFor();
            p.waitFor();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append(System.lineSeparator());
                }
                //Check result
//            int tmp = p.waitFor();
//            if (tmp == 0) {
//                MyLog.debug("executeCommand: Success!");
//            } else {
//                MyLog.debug("tmp:" + tmp);
//            }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
//        Tool.debug("Execute Result:" + output.toString());
        return output.toString();
    }
}
