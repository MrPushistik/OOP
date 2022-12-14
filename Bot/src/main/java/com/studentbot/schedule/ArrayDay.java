package com.studentbot.schedule;

import com.studentbot.schedule.lessons.Lesson;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ArrayDay {
    
    private int currDay = 0;
    private List<Day> days = new ArrayList<>();

    public ArrayDay(Document document){
        
        Elements daysNode = document.select(".day");
        
        int j = -1;
          
        for (int i = 0; i < daysNode.size(); i++){
            
            if (i%7==0) continue;
            j++;
            
            Element dayNode = daysNode.get(i);
            if (dayNode.hasClass("day-current")) this.currDay = j;

            List<Lesson> arrLessons = new ArrayList<>();
            Elements lessonsNode = dayNode.select(".day-lesson > div");
  
            String tmp = dayNode.selectFirst(".day-header > div").text();
            
            if (lessonsNode.isEmpty()){
                this.days.add(new Day(tmp.substring(0, tmp.length()-5),tmp.substring(tmp.length()-5, tmp.length())));
            }
            else {
                for (Element lessonNode : lessonsNode){

                    String time = lessonNode.select(".lesson-hour").text();
                    String room = lessonNode.select(".lesson-room").text();
                    String name = lessonNode.select(".lesson-name").text();
                    String type = lessonNode.select(".lesson-type").text();
                    
                    arrLessons.add(Lesson.getLesson(type, time, room, name));
                } 

                this.days.add(new BusyDay(tmp.substring(0, tmp.length()-5),tmp.substring(tmp.length()-5, tmp.length()),arrLessons));
            }  
        }
    }

    public List<Day> getDays(){
        List<Day> copy = new ArrayList<>();
        for (Day d : this.days) copy.add(d.clone());
        return copy;
    }
    
    public int getCurrDay(){
        return this.currDay;
    }
    
    @Override
    public String toString(){
        String res = "";
        for (Day d : days) res += d + "\n";
        return res;
    }
}
