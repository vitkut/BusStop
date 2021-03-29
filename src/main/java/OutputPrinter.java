import models.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputPrinter {

    //Method write data to file output.txt
    //If file isn't exists, method creates new file
    public static void printToFile(ArrayList<Service> outputServices, File file) throws IOException{
        ArrayList<Service> poshServices = new ArrayList<>();
        ArrayList<Service> grottyServices = new ArrayList<>();

        for(Service s:outputServices){
            if(s.getName().equals("Posh")){
                poshServices.add(s);
            }
            if(s.getName().equals("Grotty")){
                grottyServices.add(s);
            }
        }

        String absPath = file.getAbsolutePath();
        absPath = absPath.replace(file.getName(), "output.txt");
        File outputFile = new File(absPath);
        try(FileWriter fileWriter = new FileWriter(outputFile)) {
            if (!outputFile.exists()){
                if(outputFile.createNewFile()){
                    System.out.println("Output file creates on "+outputFile.getAbsolutePath());
                }
            }

            for(Service s:poshServices){
                fileWriter.write(s.toString()+"\n");
            }
            fileWriter.write("\n");
            for(Service s:grottyServices){
                fileWriter.write(s.toString());
                if(!grottyServices.get(grottyServices.size()-1).equals(s)){
                    fileWriter.write("\n");
                }
            }
            fileWriter.flush();
        }
    }
}
