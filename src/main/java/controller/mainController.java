package controller;

import entity.Song;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caopeihe on 2016-12-19.
 */
@Controller
@RequestMapping("/con")
public class mainController {

    @RequestMapping("onLineMusic.action")
    public String onlineMusic(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Song song = new Song();
        song.setSongId(1);
        song.setSongName("songName");
        song.setSinger("singer");
        song.setSpecialName("specialName");
        song.setSongType("songType");
        Song song1 = new Song(2, "song2Name", "singer2", "specialName2", "song2Type", "");
        List<Song> songList = new ArrayList<Song>();
        songList.add(song);
        songList.add(song1);
        model.addAttribute("songList", songList);
        return "html/songList.html";
    }

    @RequestMapping("continuePlay.action")
    public String continuePlay(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException, IOException {
        Song song = new Song(1, "songName", "singer", "specialName", "songType", "");
        Song song1 = new Song(2, "song2Name", "singer2", "specialName2", "song2Type", "");
        List<Song> songList = new ArrayList<Song>();
        songList.add(song);
        songList.add(song1);
        model.addAttribute("songList", songList);
        return "html/songPlay.html";
    }

    @RequestMapping("listMusic.action")
    public String listMusic(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException{
        String realPath = request.getSession().getServletContext().getRealPath("file/music");
        File file = new File(realPath);
        Map<String,Song> fileNameMap = new HashMap<String, Song>();
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files!= null){
                for(int i=0;i<files.length;i++){
                    Song song = new Song(i,files[i].getName(),files[i].getPath(),files[i].getAbsolutePath(),files[i].getCanonicalPath(),files[i].getParent());
                    fileNameMap.put(String.valueOf(i),song);
                }
            }
        }
        model.addAttribute("musicPath","../file/music");
        model.addAttribute("fileNameMap",fileNameMap);
        return "html/songPlay.html";
    }

    @RequestMapping("uploadMusic.action")
    public String uploadMusic(@RequestParam(value="myFile") MultipartFile file, HttpServletRequest request, ModelMap model)
            throws IOException {
        //第一种方法
        /*if (file.isEmpty()) {
            System.out.println("文件未上传");
        } else {
            System.out.println("文件长度: " + file.getSize());
            System.out.println("文件类型: " + file.getContentType());
            System.out.println("文件名称: " + file.getName());
            System.out.println("文件原名: " + file.getOriginalFilename());
            System.out.println("========================================");
            //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
            String realPath = request.getSession().getServletContext().getRealPath("file/music");
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, file.getOriginalFilename()));
        }*/
        //第二种方法
        String realPath = request.getSession().getServletContext().getRealPath("file/music");
        File pack = new File(realPath);
        if(!pack.exists()){
            pack.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        System.out.println(realPath);
        File targetFile = new File(realPath, fileName);
        try{
            file.transferTo(targetFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/con/listMusic.action";
    }
}
