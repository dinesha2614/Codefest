package com.example.codefestfinalapplication.Model;

public class Customer {
    private String name;
    private  String email;
    String gender;

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    String nic;
    private String mobile;

    public String getCustomerPhotopath() {
        return CustomerPhotopath;
    }

    public void setCustomerPhotopath(String customerPhotopath) {
        CustomerPhotopath = customerPhotopath;
    }

    String CustomerPhotopath;

    public String getFcmId() {
        return fcmId;
    }

    public void setFcmId(String fcmId) {
        this.fcmId = fcmId;
    }


    String fcmId;

    public String getCustomerDocId() {
        return customerDocId;
    }

    public void setCustomerDocId(String customerDocId) {
        this.customerDocId = customerDocId;
    }

    private String customerDocId;


    public Customer() {

    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Customer(String name, String email, String nic, String mobile, String customerPhotopath, String fcmId, String customerDocId, int status) {
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.mobile = mobile;
        CustomerPhotopath = customerPhotopath;
        this.fcmId = fcmId;
        this.customerDocId = customerDocId;
        this.status = status;
    }
}
