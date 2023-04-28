package ru.greatlarder.technicalassistant.services.mail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.greatlarder.technicalassistant.domain.Affairs;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LoadTask {
    private String message;
    private final HashMap<Integer, String> hashMap = new HashMap<>();
    private LocalDate data;
    private LocalTime time;
    private String room;
    private String customer;

    private void task() {

        Document html = Jsoup.parse(message);
        
            int i = 1;
            for (Element element : html.select("p")) {
                hashMap.put(i++, element.text());
            }
            String[] data1 = String.valueOf(hashMap.get(1)).split(" ");
            data = convert(data1[data1.length - 1]);
            String[] time1 = String.valueOf(hashMap.get(2)).split(" ");
            time = getTime(time1[time1.length - 1]);
            String[] customer1 = String.valueOf(hashMap.get(5)).split(" ");
            customer = customer1[customer1.length - 1];
            String[] room1 = String.valueOf(hashMap.get(6)).split(" ");
            room = room1[room1.length - 1];
    }

    public Affairs getTask(String message) {

        this.message = message;
        if(getLetter(message) == null) {
            task();
            Affairs task = new Affairs();
            if (data != null && time != null) {
                task.setDateOfCreation(data);
                task.setTimeOfCreation(time);
            }
            if (customer != null) {
                task.setCreator(customer);
            }
            if (room != null) {
                task.setRoom(room);
            }
            task.setExecutor(message);
            return task;
            
        } else {
            return getLetter(message);
        }
        
    }

    private LocalDate convert(String date) {
        String regex = "[/]";
        String[] parts = date.split(regex);
        
        List<String> list = new ArrayList<>(Arrays.asList(parts));
            String year;
            if (list.get(2).length() < 4) {
                year = 20 + list.get(2);
            } else year = list.get(2);
            
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(list.get(1))
            , Integer.parseInt(list.get(0)));
    }

    private LocalTime getTime(String time) {
        LocalTime localTime;
            String regex = "[:]";
            String[] parts = time.split(regex);
            List<String> list = new ArrayList<>(Arrays.asList(parts));
            localTime = LocalTime.of(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)));

        return localTime;
    }
    
    private Affairs getLetter(String m){
        
        Document message1 = Jsoup.parse(m);
        Element date_event = message1.getElementById("p_date_event");
        if(date_event == null)return null;
        Element time_start = message1.getElementById("p_time_start");
        if(time_start== null)return null;
        Element room = message1.getElementById("p_room");
        if(room == null)return null;
        Element event = message1.getElementById("p_event");
        if(event== null)return null;
        Element customer = message1.getElementById("p_customer");
        if(customer == null)return null;
        Element seating_arrangements = message1.getElementById("p_seating_arrangements");
        if(seating_arrangements == null)return null;
        Element number_of_participants = message1.getElementById("p_number_of_participants");
        if(number_of_participants == null)return null;
        Element equipment = message1.getElementById("p_equipment");
        if(equipment == null)return null;
        Element note = message1.getElementById("p_note");
        if(note == null)return null;
        
        Affairs task = new Affairs();
        task.setRoom(room.text());
        task.setDateOfCreation(LocalDate.parse(date_event.text()));
        task.setTimeOfCreation(getTime(time_start.text()));
        task.setCreator(customer.text());
        task.setStatus(0);
        task.setExecutor(m);
       
        return task;
    }

}
