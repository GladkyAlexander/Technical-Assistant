package ru.greatlarder.technicalassistant.services.mail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.greatlarder.technicalassistant.domain.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LoadTask {
    private final String message;
    private final HashMap<Integer, String> hashMap = new HashMap<>();
    private LocalDate data;
    private LocalTime time;
    private String room;
    private String customer;

    public LoadTask(String message) {
        this.message = message;
        task();
    }

    private void task() {

        Document html = Jsoup.parse(message);
        int i = 1;
        for (Element element : html.select("p")){
            hashMap.put(i++,element.text());
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

    public Task getTask() {
        Task task = new Task();
        if(data != null && time != null){
            task.setDateOfCreation(data);
            task.setTimeOfCreation(time);
        }
        if(customer != null){
            task.setCreator(customer);
        }
        if (room != null){
            task.setRoom(room);
        }
        task.setExecutor(message);

        return task;
    }

    private LocalDate convert(String date) {
        String regex = "[/]";
        String[] parts = date.split(regex);
        List<String> list = new ArrayList<>(Arrays.asList(parts));
        String year;
        if (list.get(2).length() < 4) {
            year = 20 + list.get(2);
        } else year = list.get(2);

        LocalDate localDate = LocalDate.of(Integer.valueOf(year), Integer.valueOf(list.get(1))
                , Integer.valueOf(list.get(0)));

        return localDate;
    }

    private LocalTime getTime(String time) {
        LocalTime localTime;
            String regex = "[:]";
            String[] parts = time.split(regex);
            List<String> list = new ArrayList<>(Arrays.asList(parts));
            localTime = LocalTime.of(Integer.valueOf(list.get(0)), Integer.valueOf(list.get(1)));

        return localTime;
    }

}
