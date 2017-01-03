package entity;

import javax.persistence.*;

/**
 * Created by caopeihe on 2016-12-19.
 */
@SuppressWarnings("serial")
@Entity
@Table(name="tb_song")
public class Song {
    private int songId;
    private String songName;
    private String singer;
    private String specialName;
    private String songType;
    private String fileName;
    @Lob
    private byte[] content;

    public Song(){
    }

    public Song(int songId,String songName,String singer,String specialName,String songType,String fileName){
        this.songId = songId;
        this.songName = songName;
        this.singer = singer;
        this.specialName = specialName;
        this.songType = songType;
        this.fileName = fileName;
    }

    @Id
    @Column(name = "songId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
    @Column(name = "songName")
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
    @Column(name = "singer")
    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
    @Column(name = "specialName")
    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }
    @Column(name = "songType")
    public String getSongType() {
        return songType;
    }

    public void setSongType(String songType) {
        this.songType = songType;
    }
    @Column(name = "fileName")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Column(name = "content")
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
