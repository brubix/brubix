package com.brubix.connect.sms;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmsSender {

    public static void main(String[] args) throws IOException {
        //sendSmsByTextLocal();

        sendSmsByNexmo();

    }

    public static String sendSmsByNexmo() {
        String   NEXMO_API_KEY ="bb5952a8" ;
        String   NEXMO_API_SECRET ="a612ebccfb2821ad" ;
        AuthMethod auth = new TokenAuthMethod(NEXMO_API_KEY, NEXMO_API_SECRET);
        NexmoClient client = new NexmoClient(auth);

        SmsSubmissionResult[] responses = new SmsSubmissionResult[0];
        try {
            responses = client.getSmsClient().submitMessage(new TextMessage(
                    "Brubix Inc",
                    "919945488182",
                    "A text message sent using the Brubix SMS API"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NexmoClientException e) {
            e.printStackTrace();
        }
        for (SmsSubmissionResult response : responses) {
            System.out.println(response);
        }
       return null ;
    }

    public static String sendSmsByTextLocal() {
        try {
            // Construct data
            String apiKey = "apikey=" + "rHjsS2lgTGE-Cz4ikQLS5GD87s8qMfX8OntJQ8k9kt";
            String userName ="&username=" +"rameshkumar.saxena@gmail.com" ;
            String hash = "&hash=e57cfb94cadcb942ae387d0dde68ebb7bf06555b2932fa74c61a76be02870d57" ;
            String password ="&password=Textlocal@123" ;

            String message = "&message=" + "This is message from bruix";
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + "919945488182";

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + userName +hash +password +numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            return "Error "+e;
        }
    }
}
