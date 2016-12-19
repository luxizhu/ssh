package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.reflect.annotation.ExceptionProxy;
import util.CheckCode;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by caopeihe on 2016-9-7.
 */
@Controller
@RequestMapping("hidden")
public class HiddenCheckCode {
    @RequestMapping("getCheckCode")
    public void getCheckCode(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","No-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("imatge/jpeg");

        int width = 190;
        int height = 60;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);

        Graphics g = image.getGraphics();
        Graphics2D g2d = (Graphics2D)g;
        Random random = new Random();
        Font mFont = new Font("华文宋体",Font.BOLD,30);
        g.setColor(CheckCode.getRandColor(200,250));
        g.fillRect(0,0,width,height);

        BasicStroke bs = new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs);
        g.setColor(Color.DARK_GRAY);
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        for(int j=0;j<3;j++){
            xPoints[j] = random.nextInt(width - 1);
            yPoints[j] = random.nextInt(height - 1);
        }
        g.drawPolyline(xPoints,yPoints,3);

        g.setFont(mFont);
        String sRand = "";
        int itmp = 0;
        for(int i=0;i<4;i++){
            itmp = random.nextInt(10) + 48;
            char ctmp = (char) itmp;
            sRand += String.valueOf(ctmp);
            Color color = new Color(20 + random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110));
            g.setColor(color);

            Graphics2D g2d_word=(Graphics2D)g;
            AffineTransform trans = new AffineTransform();
            trans.rotate(random.nextInt(45)*3.14/180,15*i+10,7);

            float scaleSize = random.nextFloat()+0.8f;
            if(scaleSize>1.1f)scaleSize=1f;
            trans.scale(scaleSize,scaleSize);
            g2d_word.setTransform(trans);

            g.drawString(String.valueOf(ctmp),30*i+40,16);
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("randCheckCode",sRand);
        g.dispose();
        try{
            ImageIO.write(image,"JPEG",response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
