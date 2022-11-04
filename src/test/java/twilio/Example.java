package twilio;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example {
//    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
//    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";

    public static void main(String[] args) {

        System.out.println(getOTP(""));

    }

    public static String getOTP(String phoneNumber){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        ResourceSet<Message> messages = Message.reader()
                .setTo("+1" + phoneNumber)
                .limit(1)
                .read();

        Pattern p = Pattern.compile("\\d+");

        String otp = null;

        for(Message record : messages) {
            Matcher m = p.matcher(record.getBody());
            while(m.find()) {
                otp = m.group(0);
            }
        }
        return otp;
    }
}