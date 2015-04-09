/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import rouletteexcercise.RouletteGame.BetType;

/**
 *
 * @author Dell
 */
public class RouletteBetWizard {



    public static class BetOptionGroup{
        public int NumbersInBet;
        public BetOption[] BetOptions;

        public BetOptionGroup(int NumbersInBet, BetOption[] BetOptions) {
            this.NumbersInBet = NumbersInBet;
            this.BetOptions = BetOptions;
        }
    }
    public static class BetOption {

        public BetType TypeOfBet;
        public RouletteGame.RouletteType RouletteType;
        public String NameDescription;
        public String[] NumbersOnBet;

        public BetOption(BetType TypeOfBet, RouletteGame.RouletteType RouletteType, String NameDescription, String... NumbersOnBet) {
            this.TypeOfBet = TypeOfBet;
            this.RouletteType = RouletteType;
            this.NameDescription = NameDescription;
            this.NumbersOnBet = NumbersOnBet;
        }
    }

    private static BetOption[] GetStraightBetOptions(RouletteGame.RouletteType rouletteType) {
        String[] numbers = RouletteWheelFactory.Create(rouletteType);
        BetOption[] res = new BetOption[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            res[i] = new BetOption(BetType.STRAIGHT, rouletteType, numbers[i], numbers[i]);
        }

        return res;
    }
    
    private static BetOption[] GetSplitBetOptions(RouletteGame.RouletteType rouletteType) {
        HashMap<String, String[]> options = new HashMap<>();
        options.put("3 2", new String[]{"3", "2"});
        options.put("3 1", new String[]{"3", "1"});
        options.put("6 5", new String[]{"6", "5"});
        options.put("5 4", new String[]{"5", "4"}); 
        options.put("9 8", new String[]{"9", "8"});
        options.put("8 7", new String[]{"8", "7"});
        options.put("12 11", new String[]{"12", "11"});
        options.put("11 10", new String[]{"11", "10"});
        options.put("15 14", new String[]{"15", "14"});
        options.put("14 13", new String[]{"14", "13"});
        options.put("18 17", new String[]{"18", "17"});
        options.put("17 16", new String[]{"17", "16"});
        options.put("21 20", new String[]{"21", "20"});
        options.put("20 19", new String[]{"20", "19"});
        options.put("24 23", new String[]{"24", "23"});
        options.put("23 22", new String[]{"23", "22"});
        options.put("27 26", new String[]{"27", "26"});
        options.put("26 25", new String[]{"26", "25"});
        options.put("30 29", new String[]{"30", "29"});
        options.put("29 28", new String[]{"29", "28"});
        options.put("33 32", new String[]{"33", "32"});
        options.put("32 31", new String[]{"32", "31"});
        options.put("36 35", new String[]{"36", "35"});
        options.put("35 34", new String[]{"35", "34"});
        options.put("3 6", new String[]{"3", "6"});
        options.put("6 9", new String[]{"6", "9"});
        options.put("9 12", new String[]{"9", "12"});
        options.put("12 15", new String[]{"12", "15"});
        options.put("15 18", new String[]{"15", "18"});
        options.put("18 21", new String[]{"18", "21"});
        options.put("21 24", new String[]{"21", "24"});
        options.put("24 27", new String[]{"24", "27"});
        options.put("27 30", new String[]{"27", "30"});
        options.put("30 33", new String[]{"30", "33"});
        options.put("33 36", new String[]{"33", "36"});
        options.put("2 5", new String[]{"2", "5"});
        options.put("3 8", new String[]{"3", "8"});
        options.put("8 11", new String[]{"8", "11"});
        options.put("11 14", new String[]{"11", "14"});
        options.put("14 17", new String[]{"14", "17"});
        options.put("17 20", new String[]{"17", "20"});
        options.put("20 23", new String[]{"20", "23"});
        options.put("23 26", new String[]{"23", "26"});
        options.put("26 29", new String[]{"26", "29"});
        options.put("29 32", new String[]{"29", "32"});
        options.put("32 35", new String[]{"32", "35"});
        options.put("1 4", new String[]{"1", "4"});
        options.put("4 7", new String[]{"4", "7"});
        options.put("7 10", new String[]{"7", "10"});
        options.put("10 13", new String[]{"10", "13"});
        options.put("13 16", new String[]{"13", "16"});
        options.put("16 19", new String[]{"16", "19"});
        options.put("19 22", new String[]{"19", "22"});
        options.put("22 25", new String[]{"22", "25"});
        options.put("25 28", new String[]{"25", "28"});
        options.put("28 31", new String[]{"28", "31"});
        options.put("31 34", new String[]{"31", "34"});
        
        BetOption[] res = new BetOption[options.size()];
        String[] keys = options.keySet().toArray(new String[options.size()]);
        for (int i = 0; i < options.size(); i++) {
            res[i] = new BetOption(BetType.SPLIT, rouletteType, keys[i], options.get(keys[i]));
        }
        
        return res;
    }
    
    private static RouletteBetWizard.BetOptionGroup GetStraightBetGroup(RouletteGame.RouletteType rouletteType){
        return new BetOptionGroup(1, GetStraightBetOptions(rouletteType));
    }
    
    private static RouletteBetWizard.BetOptionGroup GetSplitBetGroup(RouletteGame.RouletteType rouletteType){
        return new BetOptionGroup(2, GetSplitBetOptions(rouletteType));
    }

//    public static BetOption[] AllOption = {
//        new BetOption(BetType.STRAIGHT, RouletteGame.RouletteType.FRENCH, null, NumbersOnBet)
//    };
    public static HashMap<String, String[]> GetBetOptionsForBetType(RouletteGame.RouletteType rouletteType, RouletteGame.BetType betType) {

    }
}
