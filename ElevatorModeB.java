package com.gfx.elevator.solution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class ElevatorModeB {

    private Integer currentFloor;

    private TreeSet<Integer> startList = new TreeSet<>();
    private TreeSet<Integer> endList = new TreeSet<>();

    private List<Integer> finalTravelList = new ArrayList<>();

    public static ElevatorModeB getInstance() {
         return new ElevatorModeB();
    }

    public void printTravelPath(){
        int totalFloor = 0;
       
        StringBuffer sb = new StringBuffer();

        for (int i = 0, j = i + 1; i < finalTravelList.size() - 1; i++, j++) {
            int start = finalTravelList.get(i);
            int end = finalTravelList.get(j);

            totalFloor += Math.abs(start - end);

        }

        for (int stop : finalTravelList) {
            sb.append(stop + " ");
        }

        sb.append("( " + totalFloor + " )");
        System.out.println(sb.toString());
    }

    public void executeInstructions() {
        finalTravelList.add(currentFloor);

        findFirstStart();

        calculateClosetPath();
    }

    private void calculateClosetPath() {
        int difference = Integer.MAX_VALUE;
        int next = 0;

        Iterator<Integer> itrEnd = endList.iterator();
        while (itrEnd.hasNext()) {
            int end = itrEnd.next();
            int calDiff = Math.abs(currentFloor - end);

            if (difference > calDiff) {
                difference = calDiff;
                next = end;
            }
        }

        Iterator<Integer> itr = startList.iterator();
        while (itr.hasNext()) {
            int start = itr.next();
            int calDiff = Math.abs(currentFloor - start);

            if (difference > calDiff) {
                difference = calDiff;
                next = start;
            }
        }

        if (!startList.remove(next)) {
            endList.remove(next);
        }

        addToFinalList(next);
        currentFloor = next;

        if (startList.size() > 0 || endList.size() > 0) {
            calculateClosetPath();
        }
    }


    private void addToFinalList(int next) {

        if (!finalTravelList.contains(next)) {
            finalTravelList.add(next);
        }

    }


    private void findFirstStart() {
        int difference = Integer.MAX_VALUE;
        int nextStart = 0;

        Iterator<Integer> itr = startList.iterator();
        while (itr.hasNext()) {
            int start = itr.next();
            int calDiff = Math.abs(currentFloor - start);

            if (difference > calDiff) {
                difference = calDiff;
                nextStart = start;
            }
        }

        startList.remove(nextStart);
        currentFloor = nextStart;

        if (!finalTravelList.contains(nextStart)) {
            finalTravelList.add(nextStart);
        } else {
            findFirstStart();
        }
    }


    public ElevatorModeB giveInstructions(String command) {

        String[] splitCommand = command.split(":");

        setCurrentFloor(Integer.valueOf(splitCommand[0]));

        String actualCommands = splitCommand[1];

        buildInstructions(actualCommands);
        
        executeInstructions();

        return this;
    }


    private void buildInstructions(String commands) {
        startList.clear();
        endList.clear();
        finalTravelList.clear();

        String[] instructions = commands.split(",");

        for (String instruction : instructions) {
            Integer startFloor = Integer.valueOf(instruction.substring(0, instruction.indexOf("-")));
            Integer endFloor = Integer.valueOf(instruction.substring(instruction.indexOf("-") + 1));
            startList.add(startFloor);
            endList.add(endFloor);
        }
    }



    public void setCurrentFloor(Integer currentFloor) {
        this.currentFloor = currentFloor;
    }

}
