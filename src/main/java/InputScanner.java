import models.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class InputScanner {

    //Method gets File
    //Parses the file to ArrayList of Services
    public static ArrayList<Service> parseFile(File file) throws IOException{
        ArrayList<Service> inputServices = new ArrayList<>();
        try(FileReader fileReader = new FileReader(file)){
            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNextLine()){

                Service service = parseServiceString(scanner.nextLine());
                if(service.getTravelTime().compareTo(Main.HOUR) < 0) {
                    inputServices.add(service);
                }
            }
        }
        return inputServices;
    }

    //Method parses String to Service
    private static Service parseServiceString(String string){
        String[] serviceStrings = string.split(" ", 3);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate date = LocalDate.of(0, 1, 1);

        LocalTime depTime = LocalTime.parse(serviceStrings[1], format);
        LocalDateTime departure = LocalDateTime.of(date, depTime);

        LocalTime arrTime = LocalTime.parse(serviceStrings[2], format);
        LocalDateTime arrival = LocalDateTime.of(date, arrTime);
        if(departure.compareTo(arrival) > 0){
            arrival = arrival.plusDays(1);
        }

        Service service = new Service(serviceStrings[0], departure, arrival);
        return service;
    }

}
