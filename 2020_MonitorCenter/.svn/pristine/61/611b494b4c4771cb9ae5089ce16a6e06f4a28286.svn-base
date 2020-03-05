package vn.htc.monitor.service;

import java.util.Scanner;
import static vn.htc.monitor.bootstrap.config.ReadProperties.CHECK;
import static vn.htc.monitor.bootstrap.config.ReadProperties.CACHE;
import vn.htc.monitor.entity.model.Infomation;

public class Handling extends Thread {

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String s = null;
        while (true) {
            s = sc.nextLine();
            switch (s) {
                case "reload config":
                    CACHE.clear();
                    CHECK.clear();
                    System.out.println("Delete succesfull");
                    break;
                default:
                    System.out.println("not found");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = null;
        while (true) {
            s = sc.nextLine();
            switch (s) {
                case "reload config":
                    System.out.println("CACHE1 " + CACHE.size());
                    System.out.println("CHECK1 " + CHECK.size());
                    for (Infomation infomation : CACHE) {
                        System.out.println("ip " + infomation.getIp());
                        System.out.println("pass " + infomation.getPassword());
                        System.out.println("user " + infomation.getUser());
                    }
                    for (int i = 0; i < CACHE.size(); i++) {
                        CACHE.remove(i);
                    }
                    CHECK.clear();
                    System.out.println("==========================");
                    for (Infomation infomation : CACHE) {
                        System.out.println("ip " + infomation.getIp());
                        System.out.println("pass " + infomation.getPassword());
                        System.out.println("user " + infomation.getUser());
                    }
                    System.out.println("CACHE2 " + CACHE.size());
                    System.out.println("CHECK2 " + CHECK.size());

                    break;

                default:
                    System.out.println("not found");
                    break;
            }
        }
    }
}
