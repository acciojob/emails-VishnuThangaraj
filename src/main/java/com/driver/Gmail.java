package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
class Mail {
    private Date date;
    private String sender;
    private String message;

    //Constructor

    public Mail(Date date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }

    // Getters and setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store

    ArrayList<Mail> Inbox;
    ArrayList<Mail> Trash;
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(Inbox.size() >= inboxCapacity){
            Trash.add(Inbox.get(0));
            Inbox.remove(0);
        }
        Inbox.add(new Mail(date, sender, message));
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int index=0; index<Inbox.size(); index++){
            String inboxMessage = Inbox.get(index).getMessage();

            if(inboxMessage.equals(message)){
                Trash.add(Inbox.get(index));
                Inbox.remove(index);
                return;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(Inbox.size() == 0) return null;

        return Inbox.get(Inbox.size()-1).getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(Inbox.size() == 0) return null;

        return Inbox.get(0).getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

        int count = 0;

        for(Mail receivedMails : Inbox){
            Date current = receivedMails.getDate();
            if(current.after(start)  && current.before(end) ){
                count++;
            }
        }

        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }

    public void setInboxCapacity(int inboxCapacity) {
        this.inboxCapacity = inboxCapacity;
    }

    public ArrayList<Mail> getInbox() {
        return this.Inbox;
    }

    public void setInbox(ArrayList<Mail> inbox) {
        this.Inbox = inbox;
    }

    public ArrayList<Mail> getTrash() {
        return this.Trash;
    }

    public void setTrash(ArrayList<Mail> trash) {
        this.Trash = trash;
    }
}
