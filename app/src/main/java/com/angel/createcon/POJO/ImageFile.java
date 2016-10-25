package com.angel.createcon.POJO;

/**
 * Created by Angel on 10/25/2016.
 */

public class ImageFile {
    String filename, file, userid;
    int id, conid;
    public ImageFile(int id, int conid, String filename, String file, String userid){
        this.id = id;
        this.conid= conid;
        this.filename =filename;
        this.file = file;
        this.userid = userid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConid() {
        return conid;
    }

    public void setConid(int conid) {
        this.conid = conid;
    }
}
