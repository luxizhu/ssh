package controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import util.CheckCode;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by caopeihe on 2016-9-7.
 */
@Controller
@RequestMapping("chinese")
public class ChineseCheckCode {

    @RequestMapping("getRandomCode.action")
    public void getRandomCode(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","No-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");

        int width = 270;
        int height = 50;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();
        Random random = new Random();
        Font mFont = new Font("宋体",Font.ITALIC,26);
        g.setColor(CheckCode.getRandColor(200,500));
        g.fillRect(0, 0, width, height);

//        g.setFont(mFont);
//        g.setColor(getRandColor(180,200));
//        String sRand = "";
//        String ctmp = "";
//        int itmp = 0;
//        for(int i=0; i<4; i++){
//            String[] rBase = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
//            int r1 = random.nextInt(3) + 11;
//            String str_r1 = rBase[r1];
//
//            int r2;
//            if(r1 == 13){
//                r2 = random.nextInt(7);
//            }else{
//                r2 = random.nextInt(16);
//            }
//            String str_r2 = rBase[r2];
//
//            int r3 = random.nextInt(6) + 10;
//            String str_r3 = rBase[r3];
//
//            int r4;
//            if(r3 == 10){
//                r4 = random.nextInt(15)+1;
//            }else if(r3 == 15) {
//                r4 = random.nextInt(15);
//            }else{
//                r4 = random.nextInt(16);
//            }
//            String str_r4 = rBase[r4];
//
//            byte[] bytes = new byte[2];
//
//            String str_r12 = str_r1+str_r2;
//            int tempLow = Integer.parseInt(str_r12,16);
//            bytes[0] = (byte)tempLow;
//            String str_r34 = str_r3+str_r4;
//            int tempHigh = Integer.parseInt(str_r34,16);
//            bytes[1] = (byte) tempHigh;
//
//            ctmp = new String(bytes);
//            sRand += ctmp;
//            Color color = new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110));
//            g.setColor(color);
//            Graphics2D g2d_word = (Graphics2D) g;
//            AffineTransform trans = new AffineTransform();
//            trans.rotate(random.nextInt(45)*3.14/180,26*i+8,7);
//            float scaleSize = random.nextFloat() + 0.7f;
//            if(scaleSize > 1f) scaleSize = 1f;
//            trans.scale(scaleSize,scaleSize);
//            g2d_word.setTransform(trans);
//            g.drawString(ctmp,width/6*i+23,height/3);
//        }

        g.setColor(new Color(0xCCCCCC));

        for(int i=0;i< 150; i++){
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int x1 = random.nextInt(3)+1;
            int y1 = random.nextInt(6)+1;
            g.drawLine(x,y,x+x1,y+y1);
        }

        String str = "456321";

        String sRand = "";
        String randomPosition = "";
        int strLength = str.length();
        int r=0;
        int i=0;
        while(i<4){
            r=random.nextInt(strLength-1);
            if(randomPosition.indexOf(","+r+",")==-1){
                randomPosition+=String.valueOf(r)+",";
                i++;
            }
        }

        String colorValue="";
        Color color = Color.RED;
        switch(random.nextInt(3)){
            case 0:
                colorValue="红色";
                color=Color.RED;
                break;
            case 1:
                colorValue="黑色";
                color=Color.BLACK;
                break;
            case 2:
                colorValue="蓝色";
                color=Color.BLUE;
                break;
        }
        for(int j=0;j<strLength;j++){
            if(randomPosition.indexOf(","+String.valueOf(j)+",")==-1){
                g.setFont(new Font("宋体",Font.BOLD,22));
                g.setColor(new Color(0x009900));
            }else{
                g.setFont(new Font("宋体",Font.BOLD,24));
                g.setColor(color);
                sRand += str.substring(j,j+1);
            }
            g.drawString(str.substring(j,j+1),width/(strLength)*j+3,20);
        }

        g.setFont(new Font("宋体",Font.PLAIN,13));
        g.setColor(new Color(0x009900));
        g.drawString("请输入上面字符串中["+colorValue+"]的大号文字！",3,40);
        HttpSession session = request.getSession(true);
        session.setAttribute("chineseCheckCode",sRand);
        g.dispose();
        try{
            OutputStream os=response.getOutputStream();
            ImageIO.write(image,"JPEG",os);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @RequestMapping("getCode.action")
    public String chineseCheckCode(HttpServletRequest request, HttpServletResponse response){
        return "html/chineseCheckCode.html";
    }

//    public Color getRandColor(int s, int e){
//        Random random = new Random();
//        if(s>255) s = 255;
//        if(e>255) e = 255;
//        int r = s + random.nextInt(e - s);
//        int g = s + random.nextInt(e - s);
//        int b = s + random.nextInt(e - s);
//        return new Color(r,g,b);
//    }
}
