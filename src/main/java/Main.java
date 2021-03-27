import models.InputException;
import models.Service;
import models.Time;

import java.io.File;
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
                ArrayList<Service> inputList = InputScanner.parseFile(file);
                Collections.sort(inputList);
                inputList = removalOfIneffectiveServices(inputList);
                OutputPrinter.printToFile(inputList);
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
            LONG_TIME = new Time((byte) 0, (byte) 1, (byte) 0);
        } catch (InputException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static ArrayList<Service> removalOfIneffectiveServices(ArrayList<Service> inputList) throws InputException{
        for(int i = 0; i < inputList.size(); i++){
            Service current = inputList.get(i);
            for(int j = i+1; j < inputList.size(); j++){
                Service next = inputList.get(j);

                if(Time.sum(current.getDepartureTime(), LONG_TIME).compareTo(next.getDepartureTime()) > 0){
                    if(current.getDepartureTime().compareTo(next.getDepartureTime()) == 0){
                        if(current.getArrivalTime().compareTo(next.getArrivalTime()) > 0){
                            inputList.remove(current);
                            i -=1;
                            break;
                        }
                        if(current.getArrivalTime().compareTo(next.getArrivalTime()) < 0){
                            inputList.remove(next);
                            j--;
                        }
                        if(current.getArrivalTime().compareTo(next.getArrivalTime()) == 0){
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
                    if(current.getDepartureTime().compareTo(next.getDepartureTime()) < 0){
                        if(current.getArrivalTime().compareTo(next.getArrivalTime()) >= 0){
                            inputList.remove(current);
                            i -=1;
                            break;
                        } else {
                        }

                    }
                } else {
                    break;
                }
            }
        }
        return inputList;
    }

}
