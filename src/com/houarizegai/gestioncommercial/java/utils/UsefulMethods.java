package com.houarizegai.gestioncommercial.java.utils;

import javafx.scene.image.Image;
import java.sql.Blob;

public class UsefulMethods { // This class contains useful methods are used in many place

    public static java.sql.Date getSQLDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    public static Image blobToImage(Blob imageBlob) {
//        if (imageBlob != null) {
//            try {
//                ByteArrayInputStream in = new ByteArrayInputStream(imageBlob.getBytes(1, (int) imageBlob.length()));
//                return new Image(in);
//            } catch(IOException ioe) {
//                System.out.println("Blob to image error !");
//                ioe.printStackTrace();
//            }
//        }
        return null;
    }

    public static Blob imageToBlob(Image image) {
//        FileInputStream fis;
//        File file;
//        ps.setBinaryStream(9, fis, (int) file.length());

        return null;
    }
}
