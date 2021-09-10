package eltag;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat formmater = 
			new SimpleDateFormat("yyyy-MM-dd");
	
	public static String format(Date date) {
		return formmater.format(date);
	}
}
