package com.gfx.elevator.solution;

import java.util.ArrayList;
import java.util.List;

public class ElevatorModeA {

    private Integer currentFloor;

    private List<Integer> modeAList = new ArrayList<>();
    
    public void printTravelPath() {

        int totalFloor = 0;
        StringBuffer sb = new StringBuffer();

        for (int i = 0, j = i + 1; i < modeAList.size() - 1; i++, j++) {
            int start = modeAList.get(i);
            int end = modeAList.get(j);

            totalFloor += Math.abs(start - end);
        }

        for (int stop : modeAList) {
            sb.append(stop + " ");
        }

        sb.append("( " + totalFloor + " )");
        System.out.println(sb.toString());
    }

    public ElevatorModeA giveInstructions(String command) {

        String[] splitCommand = command.split(":");

        setCurrentFloor(Integer.valueOf(splitCommand[0]));

        String actualCommands = splitCommand[1];

        buildInstructions(actualCommands);

        return this;
    }


    private void buildInstructions(String commands) {
        modeAList.clear();
        modeAList.add(currentFloor);

        String[] instructions = commands.split(",");

        for (String instruction : instructions) {
            Integer startFloor = Integer.valueOf(instruction.substring(0, instruction.indexOf("-")));
            Integer endFloor = Integer.valueOf(instruction.substring(instruction.indexOf("-") + 1));
            modeAList.add(startFloor);
            modeAList.add(endFloor);
        }

    }

    public void setCurrentFloor(Integer currentFloor) {
       this.currentFloor = currentFloor;
    }
    
    public static ElevatorModeA getInstance(){
        return new ElevatorModeA();
    }
   
}
