/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.testcode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ss
 */
public class TestCode {

    public static void main(String args[]) {

        String pattern = "yyyy-MM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        System.out.println(date);

    }
}
