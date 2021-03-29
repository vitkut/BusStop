import models.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static final LocalTime HOUR = LocalTime.of(1, 0);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to input file");
        //String path = scanner.nextLine();
        String path = "input.txt";
        File file = new File(path);
        try {
            ArrayList<Service> inputServices = InputScanner.parseFile(file);
            Collections.sort(inputServices);
            removalOfIneffectiveServices(inputServices);

            for(Service s:inputServices){
                System.out.println(s);
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }

    //Method gets sorted ArrayList of Services
    //Ineffective Services will be removed
    //Method returns effective ArrayList of Services
    private static void removalOfIneffectiveServices(ArrayList<Service> inputList){
        for(int i = 0; i < inputList.size(); i++){
            Service current = inputList.get(i);
            for(int j = i+1; j < inputList.size(); j++){
                Service next = inputList.get(j);

                if(current.getDeparture().plusHours(1).compareTo(next.getDeparture()) > 0){
                    if(current.getDeparture().compareTo(next.getDeparture()) == 0){
                        if(current.getArrival().compareTo(next.getArrival()) > 0){
                            inputList.remove(current);
                            i -=1;
                            break;
                        }
                        if(current.getArrival().compareTo(next.getArrival()) < 0){
                            inputList.remove(next);
                            j--;
                        }
                        if(current.getArrival().compareTo(next.getArrival()) == 0){
                            if(current.getName().equals("Posh") && next.getName().equals("Grotty")){
                                inputList.remove(next);
                                j--;
                            }
                            if(current.getName().equals("Grotty") && next.getName().equals("Posh")){
                                inputList.remove(current);
                                i -=1;
                                break;
                            }
                            if(current.getName().equals(next.getName())){
                                inputList.remove(next);
                                j--;
                            }
                        }
                    }
                    if(current.getDeparture().compareTo(next.getDeparture()) < 0){
                        if(current.getArrival().compareTo(next.getArrival()) >= 0){
                            inputList.remove(current);
                            i -=1;
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }
    }

}
