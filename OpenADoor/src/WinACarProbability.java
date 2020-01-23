import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WinACarProbability {
    final static int doorsNr = 8;
    final static int iterations = 100;

    final static int[] doors = new int[doorsNr];
    final static int openDoor = -1;
    final static int carDoor = 10;
    final static int simpleDoor = 0;
    static boolean chosen = false;
    static int door = 0;
    static boolean  nDoorWin = false;
    static boolean lastDoorWin = false;

    static Random random = new Random();
    static int [] statistics = new int [(doorsNr+1) / 2];

    public static int[] doYouGuess() {
        for (int i = 0; i < iterations; i++) {
            doorsInitialStatus();
            for (int k = 0; k < doorsNr / 2; k++) {
                chooseADoor();
                if (nDoorWin)
                    statistics[k] = statistics[k]+1;
                openASimpleDoor();
            }
            chooseLastDoor();
            if (lastDoorWin)
                statistics[statistics.length-1] = statistics[statistics.length-1]+1;
//            System.out.println(Arrays.toString(doors));
        }
        if (doorsNr %2 == 0)
            statistics[statistics.length-1] = statistics[statistics.length-1]/2;
        return statistics;
    }

    public static  void doorsInitialStatus() {
        int randomCarDoor = random.nextInt(doorsNr);
        for (door = 0; door < doorsNr; door++) {
            if (door == randomCarDoor)
                doors[door] = carDoor;
            else doors[door] = simpleDoor;
        }
    }

    public static  boolean chooseADoor() {
        nDoorWin = false;
        while (chosen = true) {
            int randomChosenDoor = random.nextInt(doorsNr);
            if (doors[randomChosenDoor] == simpleDoor || doors[randomChosenDoor] == carDoor) {
                doors[randomChosenDoor] = doors[randomChosenDoor] + 1;
                chosen = true;
                if (doors[randomChosenDoor]-1 == carDoor) {
                    nDoorWin = true;
                }
                break;
            }
        }
        return nDoorWin;
    }

    public static  boolean chooseLastDoor() {
        lastDoorWin = false;
        for (door = 0; door < doorsNr; door++) {
            if (doors[door] == carDoor) {
                lastDoorWin = true;
                break;
            }
        }
        return lastDoorWin;
    }

    public static  void openASimpleDoor() {
        for (door = 0; door < doorsNr; door++) {
            if (doors[door] == simpleDoor) {
                doors[door] = openDoor;
                break;
            }
        }
    }

    public static void main(String[] args) {
        WinACarProbability.doYouGuess();
        System.out.println("Probability is like this : " + Arrays.toString(statistics) + " from " +WinACarProbability.iterations);
    }
}
