package utils;

import java.util.Random;

public class RandomNumber {

    private static Random random = new Random();

    public static int intRangedRandNum(int min, int max) {
        return random.nextInt(max)+min;
    }

    public static String stringIntRangedRandNum(int min, int max){
        return String.valueOf(random.nextInt(max)+min);
    }

    public static String simpleStringRandNum(){
        String res = "";
        for(int i = 0; i < 20; i++){
            res += String.valueOf(random.nextInt(10));
        }
        return res;
    }

}
