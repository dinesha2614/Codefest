package com.example.codefestfinalapplication.Model;

public class Ticket1 {
    String ticket_title;
    String ticket_description;
    String ticket_type;

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    String customer_name;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public String getTicket_title() {
        return ticket_title;
    }

    public void setTicket_title(String ticket_title) {
        this.ticket_title = ticket_title;
    }

    public String getTicket_description() {
        return ticket_description;
    }

    public void setTicket_description(String ticket_description) {
        this.ticket_description = ticket_description;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public String getAttachment_path() {
        return attachment_path;
    }

    public void setAttachment_path(String attachment_path) {
        this.attachment_path = attachment_path;
    }

    String attachment_path;

    public Ticket1(String ticket_title, String ticket_description, String ticket_type, String attachment_path) {
        this.ticket_title = ticket_title;
        this.ticket_description = ticket_description;
        this.ticket_type = ticket_type;
        this.attachment_path = attachment_path;
    }

    public Ticket1(String ticket_title, String ticket_description, String ticket_type) {
        this.ticket_title = ticket_title;
        this.ticket_description = ticket_description;
        this.ticket_type = ticket_type;
        this.status = status;
    }



    public Ticket1(String ticket_title, String ticket_description, String ticket_type, String status, String attachment_path) {
        this.ticket_title = ticket_title;
        this.ticket_description = ticket_description;
        this.ticket_type = ticket_type;
        this.status = status;

    }

    public Ticket1(String ticket_title, String ticket_description, String ticket_type, String customer_name, String status, String attachment_path) {
        this.ticket_title = ticket_title;
        this.ticket_description = ticket_description;
        this.ticket_type = ticket_type;
        this.customer_name = customer_name;
        this.status = status;
        this.attachment_path = attachment_path;
    }

    public Ticket1() {

    }
}
