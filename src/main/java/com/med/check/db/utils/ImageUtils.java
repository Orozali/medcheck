package com.med.check.db.utils;

import com.med.check.db.model.ImageData;
import java.util.Base64;
public class ImageUtils {

    public static String getBase64Image(ImageData image){
        String base64Image = Base64.getEncoder().encodeToString(image.getImageData());
        return "data:" + image.getType() + ";base64," + base64Image;
    }

}