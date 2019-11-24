package co.com.mdt.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    public static void printSeparator() {
        for (int x=0; x<80; x++){
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printDoubleSeparator() {
        for (int x=0; x<80; x++){
            System.out.print("=");
        }
        System.out.println();
    }

    public static void printSeparator(String separator) {
        for (int x=0; x<80; x++){
            System.out.print(separator);
        }
        System.out.println();
    }

    public static void printMessage(String message) {
        System.out.println(getTime() + message);
    }

    public static void printMessage(Integer message) {
        System.out.println(getTime() + message);
    }

    public static String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS | ");
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy HH:mm:ss.SSS | ");
        return sdf.format(Calendar.getInstance().getTime());
    }
}
