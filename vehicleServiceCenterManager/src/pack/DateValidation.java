package pack;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//import javax.swing.JTextField;

public class DateValidation {
	static boolean isValid(String dt) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		boolean ans=true;
        // Input to be parsed should strictly follow the defined date format
        // above.
        format.setLenient(false);
        try {
            format.parse(dt);
        } catch (ParseException e) {
            System.out.println("Date " + dt + " is not valid according to " +
                ((SimpleDateFormat) format).toPattern() + " pattern.");
            ans=false;
        }
        return ans;
	}
	static boolean isValid(javax.swing.JTextField jt) {
		return DateValidation.isValid(jt.getText());
	}
    public static void main(String[] args) {
        DateValidation.isValid("2018-10-08");//valid date
        DateValidation.isValid("2018-13-08");//invalid date
        
    }
}