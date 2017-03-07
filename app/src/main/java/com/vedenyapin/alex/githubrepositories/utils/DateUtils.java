package com.vedenyapin.alex.githubrepositories.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alex Vedenyapin on 07.03.2017
 */

public class DateUtils {

    public static String convertDateString(String notFormattedDate) {
        SimpleDateFormat formatterOld = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        SimpleDateFormat formatterNew = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date date = null;
        try {
            date = formatterOld.parse(notFormattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formatterNew.format(date);
    }
}
