package sud.exmp1;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Calender {
    Map<Date, Day> calenderDays;
    
    public Calender() {
        calenderDays = new TreeMap();
    }

    
    // "Sabatical 1/1/2050"
    
    public void saveDayToCalendar(Day d) {
        if (calenderDays.containsKey(d.getDate())) {
            Day d1 = calenderDays.get(d.getDate());
            Collection<Event> events = d1.getEvents();
            events.addAll(d.getEvents());
            d1.setEvents(events);
            calenderDays.put(d.getDate(), d1);
        } else 
            calenderDays.put(d.getDate(), d);
    }
    
    public Day getDay(Date date) {
        return calenderDays.get(date);
    }
}

class Day {
    Collection<Event> events;
    Map<String, Event> eventMap;
    Date date;
    
    public Day(Date date) {
        this.date = date;
        events = new ArrayList();
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public Collection<Event> getEvents() {
        return this.events;
    }
    
    public void addEvent(Event e) {
        this.events.add(e);
        eventMap.put(e.getEventName(), e); 
    }
    
    public void deleteEvent(String eventName) {
        Event e = eventMap.get(eventName);
        if (this.events.contains(e)) events.
        remove(e);
    }
    
    public void setEvents(Collection<Event> events) {
        this.events = events; 
    }
    
    public Collection<Event> retrieveAllEvents() {
        return this.events;
    }
}

class Event {
    String eventName;
    Date fromDateTime;
    Date toDateTime;
    String location;
    
    public Event(String eventName, String location, Date fromDate, Date toDate) {
        this.eventName = eventName;
        this.location = location;
        this.toDateTime = toDate;
        this.fromDateTime = fromDate;
    }
    
    public String getEventName() {
        return this.eventName;
    }
}
public class ReverseDepth {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Date d = new Date();
        Day day = new Day(d);
        Calender c = new Calender();
        c.saveDayToCalendar(day);
        Event e = new Event("lunch", "Dennys", d, d);
        day.addEvent(e);
        for (Event ee: day.retrieveAllEvents()) {
             System.out.println(ee.getEventName());
        }
        day.deleteEvent("lunch");
        System.out.println("--After Deletion--");
        for (Event ee: day.retrieveAllEvents()) {
             System.out.println(ee.getEventName());
        }
    }
}

// Simple Calendar
// Add an event
// Delete a given event
// Retrieve all events for a given day

// Calendar
// Lunch 4/22/19 12:00
// Lunch 4/23/19 12:00
