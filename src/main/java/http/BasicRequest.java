package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.Socket;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * 
 var lanPara = new Array(
 "00-21-27-3D-9A-C2", "192.168.1.1", 2, 
 0,0 );
 var wanPara = new Array(
 0, "00-21-27-3D-9A-C3", "222.95.250.89", 3, "255.255.255.255", 0, 0, "222.95.250.89", 0, 1, 0, "218.2.135.1 , 61.147.37.1", "1 day(s) 02:30:28", "Connected", "", 0, 
 0,0 );
 var wlanPara = new Array(
 1, "KKK", 10, 2, "00-21-27-3D-9A-C2", "192.168.1.1", 
 10,
 0,0 );
 */
public class BasicRequest {
    private static final String USER = "administrator"; // 用户名
    private static final String PASS = "administrator"; // 密码
    private static final String URL = "http://192.168.1.1/userRpm/StatusRpm.htm";

    private static String wanIP = "";

    private static class MyAuthenticator extends Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return (new PasswordAuthentication(USER, PASS.toCharArray()));
        }

    }
    
    private static ScheduledExecutorService execService =   Executors.newScheduledThreadPool(1);
    
    public static void main(String[] args) {
//        init();
        
        String urlStr = URL;
        if (args.length == 1 && args[0] != null && !args[0].isEmpty()) {
            urlStr = args[0];
        } else if (args.length > 1) {
            System.out.println("only support one param: url");
            return;
        }
        
        final String fs = urlStr;
        
        execService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                String ip = getWanIP(fs);
                if (!ip.equals(wanIP) && ip.length() > 7) {
                    System.out.println("ip changed from " + wanIP + " to " + ip);
                    cacheIP(ip);
                    try {
                        sendNotification(ip);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("ip not change or ip less than 7." + wanIP + " to " + ip);
                }
            }
        }, 5, 600, TimeUnit.SECONDS);
//        sendNotification("1.1.1.1");

        
    }

    private static void init() {
        // TODO Auto-generated method stub
        
    }

    private static void cacheIP(String ip) {
        wanIP = ip;

        // ProcessBuilder pb = new ProcessBuilder("/bin/sh"); // or any other
        // program you want to run
        //
        // Map<String, String> envMap = pb.environment();
        //
        // envMap.put("WAN_IP", wanIP);

        // ProcessBuilder pb = new ProcessBuilder("export WAN_IP=\"" + wanIP +
        // "\"");
        // try {
        // pb.start();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // System.out.println("new ip:" + System.getenv("WAN_IP"));
        // try {
        //
        // Runtime.getRuntime().exec(new String[]{"export WAN_IP=\"" + wanIP +
        // "\""});
        // // Runtime.getRuntime().exec("WAN_IP=" + wanIP +
        // // ";export WAN_IP ");
        // System.out.println("new ip:" + System.getenv("WAN_IP"));
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

    // private static void sendIP(String ip) {
    // SimpleEmail mail = new SimpleEmail();
    // mail.setHostName("smtp.163.com");
    // // mail.setTLS(true);
    // mail.setSmtpPort(25);
    // String acount = "hkw1113@163.com";
    // // mail.setAuthentication("kewen.h@qq.com", "Msgnm!!1"); // Input email
    // mail.setAuthentication(acount, "8781811hsyHKW"); // Input email
    // // id/pass
    // String result = "Failure";
    // try {
    // mail.addTo(acount);
    // mail.setFrom(acount);
    // mail.setSubject("new ip:" + ip);
    // mail.setMsg("ip=" + ip);
    // result = mail.send();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // System.out.println("Result: " + result);
    // }

    private static void sendNotification(String ip) {
        // System.out.println("send ip");

        String acount = "raspberrytest@163.com";
        String server = "smtp.163.com";
        String toAddr = "61477785@qq.com";
        // String toAddr = acount;

        String helo = "HELO " + server;

        String auth = "AUTH LOGIN";

        String username = new sun.misc.BASE64Encoder()
                .encode(acount.getBytes());

        String password = new sun.misc.BASE64Encoder().encode("raspberry"
                .getBytes());

        // System.out.println(username+"/t"+password);

        String mailFrom = "MAIL FROM:<" + acount + ">";

        String rcpt = "RCPT TO:<" + toAddr + ">";

        String data = "DATA";

        String subject = "Subject:ip=" + ip;

        String from = "From:<" + acount + ">";

        String to = "To:<" + toAddr + ">";

        // String contxt01 = "null";

        // String contxt02 = "this progrom is writed by qinlouke";
        //
        // String contxt03 = "thanks";

        String end = ".";

        String quit = "QUIT";

        Socket client = null;

        BufferedReader buf = null;

        PrintStream out = null;

        try {
            client = new Socket(server, 25);
            out = new PrintStream(client.getOutputStream());
            buf = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            out.println(helo);
            System.out.println("response：" + buf.readLine());
            out.println(auth);
            System.out.println("response：" + buf.readLine());
            out.println(username);
            System.out.println("response：" + buf.readLine());
            out.println(password);
            System.out.println("response：" + buf.readLine());
            out.println(mailFrom);
            System.out.println("response：" + buf.readLine());
            out.println(rcpt);
            System.out.println("response：" + buf.readLine());
            out.println(data);
            System.out.println("response：" + buf.readLine());
            out.println(subject);
            out.println(from);
            out.println(to);
            out.println("");
            out.println("");
            out.println("");
            // out.println(contxt01);
            // out.println(contxt02);
            // out.println(contxt03);
            out.println(end);
            System.out.println("response：" + buf.readLine());
            out.println(quit);
            System.out.println("response：" + buf.readLine());
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (buf != null) {
                try {
                    buf.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                out.close();
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private static String getWanIP(String urlStr) {

        String strWan = getList(urlStr);
        String[] arr = strWan.split(",");
        if (arr.length > 3) {
            String tmp = arr[2].trim();
            return tmp.substring(1, tmp.length() - 1);
        }
        return "";
    }

    private static String getList(String urlStr) {
        InputStream ins = null;
        BufferedReader reader = null;
        try {
            Authenticator.setDefault(new MyAuthenticator());
            URL url;
            url = new URL(urlStr);
            ins = url.openConnection().getInputStream();
            reader = new BufferedReader(new InputStreamReader(ins));
            String str;
            while ((str = reader.readLine()) != null) {
                // System.out.println(str);
                if (str.contains("var wanPara")) {
                    return reader.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                }
            }
        }
        return "";
    }
}
