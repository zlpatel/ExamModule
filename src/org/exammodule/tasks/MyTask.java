package org.exammodule.tasks;

import java.util.Calendar;  

import org.exammodule.serviceImpl.SendMailTLS;
 
public class MyTask {  
  
 public void printCurrentTime() {  
  // printing current system time  
//  System.out.println("Current Time : " + Calendar.getInstance().getTime()); 
  SendMailTLS mailService=new SendMailTLS();
  mailService.sendEmail("cdeshmuk@asu.edu", ""+Calendar.getInstance().getTime() ,"Reminder");
 }  
  
}  