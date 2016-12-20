package entity;

/**
 * Created by caopeihe on 2016-12-19.
 */
public class Song {
    private int songId;
    private String songName;
    private String singer;
    private String specialName;
    private String songType;
    private String fileName;

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

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    public String getSongType() {
        return songType;
    }

    public void setSongType(String songType) {
        this.songType = songType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
