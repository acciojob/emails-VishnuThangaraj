package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        this.calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int maxMeet = 0;

        Collections.sort(calendar, Comparator.comparing(Meeting::getStartTime));

        LocalTime newStart = LocalTime.parse("00:00:00");

        for(Meeting current : calendar){
            int value = current.getStartTime().compareTo(newStart);

            if(value > 0){ // new time is greater
                maxMeet++;
                newStart = current.getEndTime();
            }else if(current.getEndTime().compareTo(newStart) < 0){
                newStart = current.getEndTime();
            }
        }

        return maxMeet;
    }
}
