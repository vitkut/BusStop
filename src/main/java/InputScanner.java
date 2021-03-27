import models.InputException;
import models.Service;
import models.Time;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputScanner {

    public static ArrayList<Service> parseFile(File file) throws IOException, InputException {
        ArrayList<Service> inputList = new ArrayList<Service>();
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);
        while (scanner.hasNextLine()){
            Service service = parseServiceString(scanner.nextLine());
            if(service.getTravelTime().compareTo(Main.LONG_TIME) < 0){
                inputList.add(service);
            }
        }
        fileReader.close();
        return inputList;
    }

    private static Service parseServiceString(String serviceString) throws InputException{
        String[] tempStrings = serviceString.split(" ");
        if (tempStrings.length > 3){
            throw new InputException("Incorrect service format");
        }
        Service service = new Service(tempStrings[0], Time.parseTime(tempStrings[1]),
                Time.parseTime(tempStrings[2]));
        return service;
    }

}
