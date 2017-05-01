package com.gfx.elevator.solution;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ElevatorMain {
    
    private static final String MODE_A = "ModeA";
    private static final String MODE_B = "ModeB";

    public void runElevator(String fileName, String operationMode) {

        try {
            Path path = FileSystems.getDefault().getPath("resource", fileName);
            List<String> lines = Files.readAllLines(path, Charset.defaultCharset());

            if(MODE_A.equals(operationMode)){
                for (String commandSet : lines) {
                    ElevatorModeA.getInstance().giveInstructions(commandSet).printTravelPath();
                }
            }else if(MODE_B.equals(operationMode)){
                for (String commandSet : lines) {
                    ElevatorModeB.getInstance().giveInstructions(commandSet).printTravelPath();
                }
            } else {
                System.err.println(" Invalid Operation Mode");
            }
        } catch (IOException e) {
            System.out.println("Problem reading file");
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] instructions) {

        String fileName = null;
        String operationMode = null;
        
        if(instructions.length > 0){
            fileName = instructions[0];
            operationMode = instructions[1];
        }
        
        if(fileName == null){
            fileName = "Commands.txt";
        }
        
        if(operationMode == null){
            operationMode = "ModeB";
        }

        new ElevatorMain().runElevator(fileName, operationMode);

    }

}
