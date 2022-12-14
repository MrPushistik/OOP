package com.studentbot.schedule;

import com.studentbot.schedule.lessons.Lesson;
import java.util.ArrayList;
import java.util.List;

public class BusyDay extends Day {
    
    private List<Lesson> lessons;
    
    BusyDay(String date, String dateNum, List<Lesson> lessons){
        super(date, dateNum);
        this.lessons = new ArrayList<>();
        for (Lesson l : lessons) this.lessons.add(l);
    }
    
    BusyDay(BusyDay day){
        this(day.date, day.dateNum, day.lessons);
    }
    
    public List<Lesson> getLessons(){
        List<Lesson> copy = new ArrayList<>();
        for (Lesson l : this.lessons) copy.add(l);
        return copy;
    }
    
    @Override
    public String toString(){
        String res = "<<< " + date + " " + dateNum + " >>>\n\n";
        for (Lesson l : lessons) res += l + "\n";
        return res;
    }
    
    @Override
    public BusyDay clone(){
        return new BusyDay(this);
    }
}
