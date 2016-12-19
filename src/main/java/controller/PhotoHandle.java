package controller;

import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.awt.image.codec.JPEGImageEncoderImpl;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by caopeihe on 2016-9-7.
 */
@Controller
@RequestMapping("photo")
public class PhotoHandle {
    @RequestMapping("handle.action")
    public String getPhotoHandle(HttpServletRequest request, HttpServletResponse response, ModelMap model){
        model.put("imgPath","");
        model.put("imgSrc","mr.jpg");
        return "html/photoHandle.html";
    }

    @RequestMapping("handlePhoto.action")
    protected String phptoHandle(HttpServletRequest request,HttpServletResponse response,ModelMap model){
        response.setContentType("text/html;charset=UTF-8");
        Image icon = new ImageIcon(request.getRealPath("images/mr.jpg")).getImage();
        String img = request.getParameter("imgSec");
        String type = request.getParameter("radiobutton");
        String font = request.getParameter("font");
        int fontSize = getIntParameter(request, "fontSize" ,72);
        int x = getIntParameter(request, "x" ,0);
        int y = getIntParameter(request, "y" ,0);
        double z = getDoubleParameter(request,"s",0);

        File imgFile = new File(request.getRealPath("images/mr.jpg"));
        try{
            BufferedImage photo = ImageIO.read(imgFile);
            Graphics2D graphics = photo.createGraphics();
            Font fonts = new Font("宋体",Font.BOLD,fontSize);
            graphics.setFont(fonts);
            graphics.setPaint(Color.RED);

            if(type.equals("type1")){
                Rectangle2D rec = fonts.getStringBounds(font,graphics.getFontRenderContext());
                double pw = rec.getWidth();
                double ph = rec.getHeight();
                graphics.rotate(Math.toRadians(z),x+pw/2,y+ph/2);
                graphics.setComposite(AlphaComposite.SrcOver.derive(0.4f));
                graphics.drawString(font,x,y+(int)ph);
            }else if(type.equals("type2")){
                int imgw=icon.getWidth(null);
                int imgh=icon.getHeight(null);
                int iconw = icon.getWidth(null);
                int iconh = icon.getHeight(null);
                int wid = Math.max(imgw,imgh);
                BufferedImage bi = new BufferedImage(wid,wid,BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = (Graphics2D)bi.getGraphics();
                g2.rotate(Math.toRadians(z),wid/2,wid/2);
                g2.drawImage(icon,wid/2-iconw/2,wid/2-iconh/2,null);
                graphics.setComposite(AlphaComposite.SrcOver.derive(0.2f));
                graphics.drawImage(bi,x,y,null);
            }
            photo.flush();

            File tempFile = new File(imgFile.getParent(),"temp_"+imgFile.getName());
            FileOutputStream fout = new FileOutputStream(tempFile);
            JPEGImageEncoder encoder = new JPEGImageEncoderImpl(fout);
            encoder.encode(photo);
            fout.close();
            model.put("fileName",tempFile.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "html/photoHandleResult.html";
    }
    @RequestMapping("showPicture.action")
    protected void showPicture(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("fileName");
        String filename = request.getRealPath("images/"+name);
        File tempFile = new File(filename);
        FileInputStream fis = new FileInputStream(tempFile);
        ServletOutputStream tout = response.getOutputStream();
        byte[] data = new byte[1024];
        int read;
        while((read = fis.read(data))>0){
            tout.write(data,0,read);
        }
        tout.close();
        fis.close();
    }


    private int getIntParameter(HttpServletRequest request,String para,int i){
        String str = request.getParameter(para);
        if(str != null && !"".equals(str) && !"null".equals(str)){
            i = Integer.valueOf(str);
        }
        return i;
    }

    private double getDoubleParameter(HttpServletRequest request,String para,double i){
        String str = request.getParameter(para);
        if(str != null && !"".equals(str) && !"null".equals(str)){
            i = Double.valueOf(str);
        }
        return i;
    }
}
