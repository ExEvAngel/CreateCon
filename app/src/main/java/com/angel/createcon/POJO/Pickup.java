package com.angel.createcon.POJO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angel on 10/21/2016.
 */

public class Pickup implements Parcelable {
    private int cid, conid, nopiece;
    private boolean dg;
    private String sendname;
    private String sendaddress;
    private String sendcity;
    private String sendpostcode;
    private String description;
    private String driver;

    public Pickup (int cid, int conid, int nopiece, boolean dg, String description, String sendname, String sendaddress, String sendcity, String sendpostcode){
        this.cid = cid;
        this.conid = conid;
        this.nopiece = nopiece;
        this.dg = dg;
        this.description = description;
        this.sendname = sendname;
        this.sendaddress = sendaddress;
        this.sendcity = sendcity;
        this.sendpostcode = sendpostcode;
    }
    public String getSendpostcode() {
        return sendpostcode;
    }

    public void setSendpostcode(String sendpostcode) {
        this.sendpostcode = sendpostcode;
    }
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getConid() {
        return conid;
    }

    public void setConid(int conid) {
        this.conid = conid;
    }

    public int getNopiece() {
        return nopiece;
    }

    public void setNopiece(int nopiece) {
        this.nopiece = nopiece;
    }

    public boolean isDg() {
        return dg;
    }

    public void setDg(boolean dg) {
        this.dg = dg;
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

    public String getSendaddress() {
        return sendaddress;
    }

    public void setSendaddress(String sendaddress) {
        this.sendaddress = sendaddress;
    }

    public String getSendcity() {
        return sendcity;
    }

    public void setSendcity(String sendcity) {
        this.sendcity = sendcity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cid);
        dest.writeInt(conid);
        dest.writeInt(nopiece);
        dest.writeByte((byte) (dg ? 1 : 0));     //if dg == true, byte == 1
        dest.writeString(description);
        dest.writeString(sendname);
        dest.writeString(sendaddress);
        dest.writeString(sendcity);
        dest.writeString(sendpostcode);
        dest.writeString(driver);

    }
    private Pickup(Parcel in) {
        this.cid = in.readInt();
        this.conid = in.readInt();
        this.nopiece = in.readInt();
        this.dg=in.readByte() != 0;     //dg == true if byte != 0
        this.description = in.readString();
        this.sendname = in.readString();
        this.sendaddress = in.readString();
        this.sendcity = in.readString();
        this.sendpostcode = in.readString();
        this.driver = in.readString();
    }
    public static final Creator<Pickup> CREATOR = new Creator<Pickup>() {
        @Override
        public Pickup createFromParcel(Parcel in) {
            return new Pickup(in);
        }

        @Override
        public Pickup[] newArray(int size) {
            return new Pickup[size];
        }
    };
}
