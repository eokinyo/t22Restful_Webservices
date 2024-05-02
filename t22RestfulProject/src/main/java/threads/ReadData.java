package threads;
/*
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import data.Robot;

public class ReadData implements Runnable {
    URL url = null;
    HttpURLConnection conn = null;
    InputStreamReader isr = null;
    BufferedReader br = null;

    String s = null;

    @Override
    public void run() {
        while (Robot.getRun() == 1) {
            try {
                // Reduced sleep to 200 ms to make the data fetching more responsive
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
                e.printStackTrace();
            }
            try {
                url = new URL("http://127.0.0.1:8080/rest/lego/getinformations");
                conn = (HttpURLConnection) url.openConnection();

                // Set up a timeout
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                try (InputStream is = conn.getInputStream()) {
                    isr = new InputStreamReader(is);
                    br = new BufferedReader(isr);
                    while ((s = br.readLine()) != null) {
                        System.out.println("Received data: " + s); // Log received data
                        System.out.println(Robot.getSpeed()); // Log received data
                        
                        String[] ds = s.split("#");
                        if (ds.length >= 5) {
                            Robot.setRun(ds[0]);
                            Robot.setSpeed(ds[1]);
                            Robot.setIntensity(ds[2]);
                            Robot.setDistance(ds[3]);
                            Robot.setDirection(ds[4]);
                        } else {
                            System.out.println("Invalid data format received: " + s);
                        }
                    }
                } finally {
                    // Close resources in the finally block to ensure they are closed even if an exception occurs
                    if (br != null) br.close();
                    if (isr != null) isr.close();
                    if (conn != null) conn.disconnect();
                }
            } catch (Exception e) {
                System.out.println("An error fetching data.");
                e.printStackTrace();
            }
        }
    }
}*/