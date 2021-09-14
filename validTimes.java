import java.util.*;


public class validTimes {
    final static int maxHour1 = 2;
    final static int maxHour2withH1equals1 = 9;
    final static int maxHour2withH1equals2 = 3;
    final static int maxMinute1 = 5;
    final static int maxMinute2 = 9;

    public static void main(String[] args){
        int[] inputDigits = new int[4];
        scanUserInput(inputDigits);
        System.out.println(validTimesSolution(inputDigits));
    }

    private static void scanUserInput(int[] toBeFilled) {
        try(Scanner scn = new Scanner(System.in)){
            for(int i = 0; i < toBeFilled.length; i++)
                toBeFilled[i] = scn.nextInt();
        }
    }

    private static void encodeDigits(int[] digits, Map<Integer, Integer> digitToNumOfAppearances){
        digitToNumOfAppearances.clear();
        for (int digit : digits)
            digitToNumOfAppearances.put(digit, digitToNumOfAppearances.getOrDefault(digit, 0) + 1);
    }

    private static void encodeDigits(int h1, int h2, int m1, int m2, Map<Integer, Integer> digitToNumOfAppearances){
        int[] hhmm = {h1, h2, m1, m2};
        encodeDigits(hhmm, digitToNumOfAppearances);
    }

    private static int validTimesSolution(int[] inputDigits){
        int res = 0;
        Map<Integer, Integer> digitsToQuantity = new HashMap<>();
        Map<Integer, Integer> solDigitsToQuantity = new HashMap<>();
        encodeDigits(inputDigits, digitsToQuantity);
        //generate all valid times using nested loops
        for(int h1 = 0; h1 <= maxHour1; h1++){
            for(int h2 = 0; h2 <= maxHour2withH1equals1; h2++){
                if(h1 == 2 && h2 > maxHour2withH1equals2){
                    break;
                }
                for(int m1 = 0; m1 <= maxMinute1; m1++){
                    for(int m2 = 0; m2 <= maxMinute2; m2++){
                        encodeDigits(h1, h2, m1, m2, solDigitsToQuantity);
                        //check if current valid time is permutation of user input digits
                        if(digitsToQuantity.equals(solDigitsToQuantity))
                            res++;
                    }
                }
            }
        }
        return res;
    }
}

