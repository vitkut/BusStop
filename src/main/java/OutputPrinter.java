import models.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputPrinter {

    //Method write data to file output.txt
    //If file isn't exists, method creates new file
    public static void printToFile(ArrayList<Service> inputList){
        ArrayList<Service> poshList = new ArrayList<Service>();
        ArrayList<Service> grottyList = new ArrayList<Service>();
        for(Service s:inputList){
            if (s.getName().equals("Posh")){
                poshList.add(s);
            }
            if(s.getName().equals("Grotty")){
                grottyList.add(s);
            }
        }
        try{
            File file = new File("output.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            for(Service s:poshList){
                fileWriter.write(s.toString()+"\n");
            }
            fileWriter.write("\n");
            for(Service s:grottyList){
                if(s.equals(grottyList.get(grottyList.size()-1))){
                    fileWriter.write(s.toString());
                } else {
                    fileWriter.write(s.toString()+"\n");
                }
            }
            fileWriter.flush();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
