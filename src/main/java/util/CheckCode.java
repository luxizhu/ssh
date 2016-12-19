package util;

import java.awt.*;
import java.util.Random;

/**
 * Created by caopeihe on 2016-9-7.
 */
public class CheckCode {
    public static Color getRandColor(int s, int e){
        Random random = new Random();
        if(s>255) s = 255;
        if(e>255) e = 255;
        int r = s + random.nextInt(e - s);
        int g = s + random.nextInt(e - s);
        int b = s + random.nextInt(e - s);
        return new Color(r,g,b);
    }
}
