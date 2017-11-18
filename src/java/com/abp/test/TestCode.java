/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abp.test;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author ss
 */
public class TestCode {

    public static void main(String args[]) {

        String dateTime = "2017-11-18 02:58:00.0";
// Format for input
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss.s");
// Parsing the date
        DateTime jodatime = dtf.parseDateTime(dateTime);
        
        System.out.println(jodatime.toString().substring(0, 16));

    }
}
