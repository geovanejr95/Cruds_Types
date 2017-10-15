package br.com.kedge.mylibrary.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cConvertDate {

    public static String convertDateString(Date dtData) {
        SimpleDateFormat formatBra = new SimpleDateFormat("dd/MM/yyyy");
        return (formatBra.format(dtData));
    }

    public static Date convertStringDate(String data) {
        if (data == null || data.equals(""))
            return null;

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (java.util.Date) formatter.parse(data);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static java.sql.Date convertStringSqlDate(Date myDate) {
        java.sql.Date mySqlDate;

        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            myDate = formatter.parse(convertDateString(myDate));
            mySqlDate = new java.sql.Date(myDate.getTime());
            return mySqlDate;
        } catch (ParseException pe) {
            pe.printStackTrace();

        }
        return null;
    }

    public static Date convertSqlDateUtilDate(java.sql.Date mySqlDate) {
        Date sql = convertStringDate(mySqlDate.toString());

        return sql;

    }
}
