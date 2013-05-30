/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TrungThanh
 */
public class control {

    /**
     * Generate md5 string
     *
     * @param str
     * @return String
     */
    public static String generateMD5(String str) {
        String hashword = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            hashword = hash.toString(16);
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, e);
        }
        return hashword;
    }

    public static Date conver2Date(String value, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            java.util.Date d = format.parse(value);
            Calendar ca = Calendar.getInstance();
            ca.setTime(d);
            return new Date(ca.getTimeInMillis());
        } catch (ParseException ex) {
            Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String conver2String(Date d, String pattern) {
        return new SimpleDateFormat(pattern).format(d);
    }
}
