package in.ac.iiitb.speart.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class HelperFunctions {
    public Date returnSQLDate(java.util.Date date){
    java.util.Date utilDate = date;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String stringDate= dateFormat.format(utilDate);
    java.sql.Date sqlDate=  java.sql.Date.valueOf(stringDate);
    return sqlDate;
    }
}
