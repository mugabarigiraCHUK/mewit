/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.util;

import java.io.File;
import java.util.Random;

/**
 *
 * @author azrulhasni
 */
public class FileUtil {

    public static File createTempDir() {
        final String baseTempPath = System.getProperty("java.io.tmpdir");

        Random rand = new Random();
        int randomInt = 1 + rand.nextInt();

        File tempDir = new File(baseTempPath + File.separator + "tempDir" + randomInt);
        if (tempDir.exists() == false) {
            tempDir.mkdir();
        }

        tempDir.deleteOnExit();

        return tempDir;
    }
}
