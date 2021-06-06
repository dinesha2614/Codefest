package com.example.codefestfinalapplication.Model;

import java.util.Date;

public class Order {

    String Customer_Nic;
    String Customer_Name;
    String mobile;
    Date JobcreatedAt;
    double estimatedPrice;

    public String getRiderDocid() {
        return riderDocid;
    }

    public void setRiderDocid(String riderDocid) {
        this.riderDocid = riderDocid;
    }

    public String getCustomerDocid() {
        return customerDocid;
    }

    public void setCustomerDocid(String customerDocid) {
        this.customerDocid = customerDocid;
    }

    String riderDocid;
    String customerDocid;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;





    public String getCustomer_id() {
        return Customer_Nic;
    }

    public void setCustomer_id(String customer_id) {
        Customer_Nic = customer_id;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getJobcreatedAt() {
        return JobcreatedAt;
    }

    public void setJobcreatedAt(Date jobcreatedAt) {
        JobcreatedAt = jobcreatedAt;
    }



    public double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public String getDurationString() {
        return durationString;
    }

    public void setDurationString(String durationString) {
        this.durationString = durationString;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    String durationString;

    String status;
    Date statusTime;




    public Order() {

    }

    public Order(String customer_Nic, String customer_Name, String mobile, Date jobcreatedAt, double estimatedPrice, String customerDocid, String email, String durationString, String status) {
        Customer_Nic = customer_Nic;
        Customer_Name = customer_Name;
        this.mobile = mobile;
        JobcreatedAt = jobcreatedAt;
        this.estimatedPrice = estimatedPrice;
        this.customerDocid = customerDocid;
        this.email = email;
        this.durationString = durationString;
        this.status = status;
    }
}
