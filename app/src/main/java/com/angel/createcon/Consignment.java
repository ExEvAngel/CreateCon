package com.angel.createcon;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Angel on 10/9/2016.
 */

public class Consignment implements Parcelable {
    int id, conid, nopiece;
    double value;
    String payterm, custref, service, opt, description,currency, userid;
    String sendacc, sendname, sendaddress, sendcity, sendpostcode, sendcountry, sendcontactname, sendcontactno;
    String recacc, recname, recaddress, reccity, recpostcode, reccountry, reccontactname, reccontactno;
    boolean dg, parked;
    Date creationdate;

    public Consignment(int id, int conid, String payterm, String custref,
                       String sendacc, String sendname, String sendaddress, String sendcity, String sendpostcode, String sendcountry, String sendcontactname, String sendcontactno,
                       String recacc, String recname, String recaddress, String reccity, String recpostcode, String reccountry, String reccontactname, String reccontactno,
                       String service, String opt, boolean dg, int nopiece, String description, double  value, String currency, String userid, boolean parked, Date creationdate){
        this.setId(id);
        this.setConid(conid);
        this.setPayterm(payterm);
        this.setCustref(custref);
        this.setSendacc(sendacc);
        this.setSendname(sendname);
        this.setSendaddress(sendaddress);
        this.setSendcity(sendcity);
        this.setSendpostcode(sendpostcode);
        this.setSendcountry(sendcountry);
        this.setSendcontactname(sendcontactname);
        this.setSendcontactno(sendcontactno);
        this.setRecacc(recacc);
        this.setRecname(recname);
        this.setRecaddress(recaddress);
        this.setReccity(reccity);
        this.setRecpostcode(recpostcode);
        this.setReccountry(reccountry);
        this.setReccontactname(reccontactname);
        this.setReccontactno(reccontactno);
        this.setService(service);
        this.setOpt(opt);
        this.setDg(dg);
        this.setNopiece(nopiece);
        this.setDescription(description);
        this.setValue(value);
        this.setCurrency(currency);
        this.setUserid(userid);
        this.setParked(parked);
        this.setCreationdate(creationdate);

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

    public int getNopiece() {
        return nopiece;
    }

    public void setNopiece(int nopiece) {
        this.nopiece = nopiece;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getPayterm() {
        return payterm;
    }

    public void setPayterm(String payterm) {
        this.payterm = payterm;
    }

    public String getCustref() {
        return custref;
    }

    public void setCustref(String custref) {
        this.custref = custref;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSendacc() {
        return sendacc;
    }

    public void setSendacc(String sendacc) {
        this.sendacc = sendacc;
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

    public String getSendpostcode() {
        return sendpostcode;
    }

    public void setSendpostcode(String sendpostcode) {
        this.sendpostcode = sendpostcode;
    }

    public String getSendcountry() {
        return sendcountry;
    }

    public void setSendcountry(String sendcountry) {
        this.sendcountry = sendcountry;
    }

    public String getSendcontactname() {
        return sendcontactname;
    }

    public void setSendcontactname(String sendcontactname) {
        this.sendcontactname = sendcontactname;
    }

    public String getSendcontactno() {
        return sendcontactno;
    }

    public void setSendcontactno(String sendcontactno) {
        this.sendcontactno = sendcontactno;
    }

    public String getRecacc() {
        return recacc;
    }

    public void setRecacc(String recacc) {
        this.recacc = recacc;
    }

    public String getRecname() {
        return recname;
    }

    public void setRecname(String recname) {
        this.recname = recname;
    }

    public String getRecaddress() {
        return recaddress;
    }

    public void setRecaddress(String recaddress) {
        this.recaddress = recaddress;
    }

    public String getReccity() {
        return reccity;
    }

    public void setReccity(String reccity) {
        this.reccity = reccity;
    }

    public String getRecpostcode() {
        return recpostcode;
    }

    public void setRecpostcode(String recpostcode) {
        this.recpostcode = recpostcode;
    }

    public String getReccountry() {
        return reccountry;
    }

    public void setReccountry(String reccountry) {
        this.reccountry = reccountry;
    }

    public String getReccontactname() {
        return reccontactname;
    }

    public void setReccontactname(String reccontactname) {
        this.reccontactname = reccontactname;
    }

    public String getReccontactno() {
        return reccontactno;
    }

    public void setReccontactno(String reccontactno) {
        this.reccontactno = reccontactno;
    }

    public boolean isDg() {
        return dg;
    }

    public void setDg(boolean dg) {
        this.dg = dg;
    }

    public boolean isParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(conid);
        dest.writeString(payterm);
        dest.writeString(custref);
        dest.writeString(sendaddress);
        dest.writeString(sendcity);
        dest.writeString(sendpostcode);
        dest.writeString(sendcountry);
        dest.writeString(sendcontactname);
        dest.writeString(sendcontactno);
        dest.writeString(recacc);
        dest.writeString(recname);
        dest.writeString(recaddress);
        dest.writeString(reccity);
        dest.writeString(recpostcode);
        dest.writeString(reccountry);
        dest.writeString(reccontactname);
        dest.writeString(reccontactno);
        dest.writeString(service);
        dest.writeString(opt);
        dest.writeByte((byte) (dg ? 1 : 0));     //if dg == true, byte == 1
        dest.writeInt(nopiece);
        dest.writeString(description);
        dest.writeDouble(value);
        dest.writeString(currency);
        dest.writeString(userid);
        dest.writeByte((byte) (parked ? 1 : 0));     //if parked == true, byte == 1
        dest.writeLong(creationdate.getTime());

    }


    public static final Parcelable.Creator<Consignment> CREATOR
            = new Parcelable.Creator<Consignment>() {
        public Consignment createFromParcel(Parcel in) {
            return new Consignment(in);
        }

        public Consignment[] newArray(int size) {
            return new Consignment[size];
        }
    };

    private Consignment (Parcel in) {
        id=in.readInt();
        conid=in.readInt();
        payterm=in.readString();
        custref=in.readString();
        sendaddress=in.readString();
        sendcity=in.readString();
        sendpostcode=in.readString();
        sendcountry=in.readString();
        sendcontactname=in.readString();
        sendcontactno=in.readString();
        recacc=in.readString();
        recname=in.readString();
        recaddress=in.readString();
        reccity=in.readString();
        recpostcode=in.readString();
        reccountry=in.readString();
        reccontactname=in.readString();
        reccontactno=in.readString();
        service=in.readString();
        opt=in.readString();
        dg=in.readByte() != 0;     //dg == true if byte != 0
        nopiece=in.readInt();
        description=in.readString();
        value=in.readDouble();
        currency=in.readString();
        userid=in.readString();
        parked= in.readByte()!=0;     //parked == true if byte != 0
        creationdate=new Date(in.readLong());

    }

}
