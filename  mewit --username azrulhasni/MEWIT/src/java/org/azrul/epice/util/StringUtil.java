/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Azrul
 */
public class StringUtil {

    public static String formatAsCSV(Collection<String> c) {
        StringBuilder result = new StringBuilder("");
        for (String e : c) {
            result.append(e);
            result.append(',');
        }
        return result.toString();
    }

    public static String randomString(int length) {
        char[] res = new char[length];
        char[] template = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
        for (int i = 0; i < length; i++) {
            int rand = (int)(template.length*Math.random());
            res[i] = template[rand];
        }
        return new String(res);

    }


    public static void main(String[] args){
        System.out.println(randomString(5));
        System.out.println(randomString(6));
        System.out.println(randomString(7));
        System.out.println(randomString(8));
    }
}
