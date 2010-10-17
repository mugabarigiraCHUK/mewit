/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.util;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Azrul
 */
public class ImageUtil {

    public static ImageIcon createImageIcon(String path,String description) {
        URL imgURL = Image.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
