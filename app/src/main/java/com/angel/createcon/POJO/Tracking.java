package com.angel.createcon.POJO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


/**
 * Created by Angel on 10/20/2016.
 */

public class Tracking implements Parcelable{
    private String status, remarks, userId, depot;
    private Date date;
    private int  id;
    private int cid;

    public int getConid() {
        return conid;
    }

    public void setConid(int conid) {
        this.conid = conid;
    }

    private int conid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    protected Tracking(Parcel in) {
        id = in.readInt();
        status = in.readString();
        remarks = in.readString();
        userId = in.readString();
        depot = in.readString();
        date =new Date(in.readLong());
        cid = in.readInt();
        conid = in.readInt();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(status);
        dest.writeString(remarks);
        dest.writeString(userId);
        dest.writeString(depot);
        dest.writeLong(date.getTime());
        dest.writeInt(cid);
        dest.writeInt(conid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tracking> CREATOR = new Creator<Tracking>() {
        @Override
        public Tracking createFromParcel(Parcel in) {
            return new Tracking(in);
        }

        @Override
        public Tracking[] newArray(int size) {
            return new Tracking[size];
        }
    };
}
