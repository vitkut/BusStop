import models.InputException;
import models.Service;
import models.Time;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static Time LONG_TIME = null;

    public static void main(String[] args) {
        init();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to input file");
        //String path = scanner.nextLine();
        String path = "input.txt";
        File file = new File(path);
        try {
            try {
                LinkedList<Service> inputList = InputScanner.parseFile(file);
                Collections.sort(inputList);
                inputList = removalOfIneffectiveServices(inputList);

                for(Service s:inputList){
                    System.out.println(s);
                }

            } catch (InputException ex){
                System.out.println(ex.getMessage());
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }

    private static void init(){
        try{
            LONG_TIME = new Time((byte) 1, (byte) 0);
        } catch (InputException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static LinkedList<Service> removalOfIneffectiveServices(LinkedList<Service> inputList) throws InputException{
        ListIterator<Service> iterator = inputList.listIterator();
        Service current = iterator.next();
        Service next = iterator.next();
        while (iterator.hasNext()){
            System.out.println(iterator.nextIndex());
            if((Time.sum(current.getDepartureTime(), LONG_TIME)).compareTo(next.getDepartureTime()) >= 0){
                current = next;
                next = iterator.next();
            } else {
                if(current.getDepartureTime().compareTo(next.getDepartureTime()) == 0){
                    if(current.getArrivalTime().compareTo(next.getArrivalTime()) > 0){
                        inputList.remove(current);
                    } else {
                        if(current.getArrivalTime().compareTo(next.getArrivalTime()) < 0){
                            inputList.remove(next);
                        } else {
                            if(current.getArrivalTime().compareTo(next.getArrivalTime()) == 0){
                                if(current.getName().equals("Posh") && next.getName().equals("Grotty")){
                                    inputList.remove(next);
                                } else {
                                    if(current.getName().equals("Grotty") && next.getName().equals("Posh")){
                                        inputList.remove(current);
                                    } else {
                                        if(current.getName().equals(next.getName())){
                                            inputList.remove(next);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if(current.getDepartureTime().compareTo(next.getDepartureTime()) < 0){
                        inputList.remove(current);
                    }
                }
            }
            current = next;
            next = iterator.next();
        }

        return inputList;
    }

}
