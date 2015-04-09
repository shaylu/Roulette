/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import rouletteexcercise.RouletteGame.BetType;

/**
 *
 * @author Dell
 */
public class RouletteBetWizard {

    private static BetOption[] GetTrioBetOptions(RouletteGame.RouletteType rouletteType) {
        HashMap<String, String[]> options = new HashMap<>();
        options.put("0 2 3", new String[]{"0", "2", "3"});
        options.put("0 1 2", new String[]{"0", "1", "2"});

        BetOption[] res = new BetOption[options.size()];
        String[] keys = options.keySet().toArray(new String[options.size()]);
        for (int i = 0; i < options.size(); i++) {
            res[i] = new BetOption(BetType.TRIO, rouletteType, keys[i], options.get(keys[i]));
        }

        return res;
    }

    public static class BetOptionGroup {

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
        
        public static boolean BetFound(RouletteGame.RouletteType rouletteType,RouletteGame.BetType betType, ArrayList<String> numbers) throws Exception{
            BetOption[] options = GetBetOptionsForBetType(rouletteType, betType).BetOptions;
            HashSet<String> numbersToCheck = new HashSet<>(numbers);
            for (BetOption option : options) {
                ArrayList<String> numbersOnBet =  new ArrayList<String>(Arrays.asList(option.NumbersOnBet));
                if (numbersOnBet.containsAll(numbersToCheck) == true && numbersOnBet.size() == numbersToCheck.size())
                    return true;
            }
            
            return false;
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

    private static BetOption[] GetStreetBetOptions(RouletteGame.RouletteType rouletteType) {
        HashMap<String, String[]> options = new HashMap<>();
        options.put("1 2 3", new String[]{"1", "2", "3"});
        options.put("4 5 6", new String[]{"4", "5", "6"});
        options.put("7 8 9", new String[]{"7", "8", "9"});
        options.put("10 11 12", new String[]{"10", "11", "12"});
        options.put("13 14 15", new String[]{"13", "14", "15"});
        options.put("16 17 18", new String[]{"16", "17", "18"});
        options.put("19 20 21", new String[]{"19", "20", "21"});
        options.put("22 23 24", new String[]{"22", "23", "24"});
        options.put("25 26 27", new String[]{"25", "26", "27"});
        options.put("28 29 30", new String[]{"28", "29", "30"});
        options.put("31 32 33", new String[]{"31", "32", "33"});
        options.put("34 35 36", new String[]{"34", "35", "36"});

        BetOption[] res = new BetOption[options.size()];
        String[] keys = options.keySet().toArray(new String[options.size()]);
        for (int i = 0; i < options.size(); i++) {
            res[i] = new BetOption(BetType.STREET, rouletteType, keys[i], options.get(keys[i]));
        }

        return res;
    }

    private static BetOption[] GetCornerBetOptions(RouletteGame.RouletteType rouletteType) {
        HashMap<String, String[]> options = new HashMap<>();
        options.put("2 3 5 6", new String[]{"2", "3", "5", "6"});
        options.put("1 2 4 5", new String[]{"1", "2", "4", "5"});
        options.put("5 6 8 9", new String[]{"5", "6", "8", "9"});
        options.put("4 5 7 8", new String[]{"4", "5", "7", "8"});
        options.put("8 9 11 12", new String[]{"8", "9", "11", "12"});
        options.put("7 8 10 11", new String[]{"7", "8", "10", "11"});
        options.put("11 12 14 15", new String[]{"11", "12", "14", "15"});
        options.put("10 11 13 14", new String[]{"10", "11", "13", "14"});
        options.put("14 15 17 18", new String[]{"14", "15", "17", "18"});
        options.put("13 14 16 17", new String[]{"13", "14", "16", "17"});
        options.put("17 18 20 21", new String[]{"17", "18", "20", "21"});
        options.put("16 17 19 20", new String[]{"16", "17", "19", "20"});
        options.put("20 21 23 24", new String[]{"20", "21", "23", "24"});
        options.put("19 20 22 23", new String[]{"19", "20", "22", "23"});
        options.put("23 24 26 27", new String[]{"23", "24", "26", "27"});
        options.put("22 23 25 26", new String[]{"22", "23", "25", "26"});
        options.put("26 27 29 30", new String[]{"26", "27", "29", "30"});
        options.put("25 26 28 29", new String[]{"25", "26", "28", "29"});
        options.put("29 30 32 33", new String[]{"29", "30", "32", "33"});
        options.put("28 29 31 32", new String[]{"28", "29", "31", "32"});
        options.put("32 33 35 36", new String[]{"32", "33", "35", "36"});
        options.put("31 32 34 35", new String[]{"31", "32", "34", "35"});

        BetOption[] res = new BetOption[options.size()];
        String[] keys = options.keySet().toArray(new String[options.size()]);
        for (int i = 0; i < options.size(); i++) {
            res[i] = new BetOption(BetType.CORNER, rouletteType, keys[i], options.get(keys[i]));
        }

        return res;
    }

    private static BetOption[] GetSixInLineBetOptions(RouletteGame.RouletteType rouletteType) {
        HashMap<String, String[]> options = new HashMap<>();
        options.put("1 2 3 4 5 6", new String[]{"1", "2", "3", "4", "5", "6"});
        options.put("4 5 6 7 8 9", new String[]{"4", "5", "6", "7", "8", "9"});
        options.put("7 8 9 10 11 12", new String[]{"7", "8", "9", "10", "11", "12"});
        options.put("10 11 12 13 14 15", new String[]{"10", "11", "12", "13", "14", "15"});
        options.put("13 14 15 16 17 18", new String[]{"13", "14", "15", "16", "17", "18"});
        options.put("16 17 18 19 20 21", new String[]{"16", "17", "18", "19", "20", "21"});
        options.put("19 20 21 22 23 24", new String[]{"19", "20", "21", "22", "23", "24"});
        options.put("22 23 24 25 26 27", new String[]{"22", "23", "24", "25", "26", "27"});
        options.put("25 26 27 28 29 30", new String[]{"25", "26", "27", "28", "29", "30"});
        options.put("28 29 30 31 32 33", new String[]{"28", "29", "30", "31", "32", "33"});
        options.put("31 32 33 34 35 36", new String[]{"31", "32", "33", "34", "35", "36"});

        BetOption[] res = new BetOption[options.size()];
        String[] keys = options.keySet().toArray(new String[options.size()]);
        for (int i = 0; i < options.size(); i++) {
            res[i] = new BetOption(BetType.SIX_LINE, rouletteType, keys[i], options.get(keys[i]));
        }

        return res;
    }

    private static BetOption[] GetBasketBetOptions(RouletteGame.RouletteType rouletteType) {
        HashMap<String, String[]> options = new HashMap<>();
        options.put("00 2 3", new String[]{"1", "2", "3", "4", "5", "6"});
        options.put("0 00 2", new String[]{"4", "5", "6", "7", "8", "9"});
        options.put("0 1 2", new String[]{"4", "5", "6", "7", "8", "9"});

        BetOption[] res = new BetOption[options.size()];
        String[] keys = options.keySet().toArray(new String[options.size()]);
        for (int i = 0; i < options.size(); i++) {
            res[i] = new BetOption(BetType.BASKET, rouletteType, keys[i], options.get(keys[i]));
        }

        return res;
    }

    private static BetOption[] GetColumnBetOptions(RouletteGame.RouletteType rouletteType) {
        HashMap<String, String[]> options = new HashMap<>();
        options.put("3 6 9 12 15 18 21 24 27 30 33 36", new String[]{"3", "6", "9", "12", "15", "18", "21", "24", "27", "30", "33", "36"});
        options.put("2 5 8 11 14 17 20 23 26 29 32 35", new String[]{"2", "5", "8", "11", "14", "17", "20", "23", "26", "29", "32", "35"});
        options.put("1 4 7 10 13 16 19 22 25 28 31 34", new String[]{"1", "4", "7", "10", "13", "16", "19", "22", "25", "28", "31", "34"});

        BetOption[] res = new BetOption[options.size()];
        String[] keys = options.keySet().toArray(new String[options.size()]);
        for (int i = 0; i < options.size(); i++) {
            res[i] = new BetOption(BetType.COLUMN, rouletteType, keys[i], options.get(keys[i]));
        }

        return res;
    }

    private static RouletteBetWizard.BetOptionGroup GetStraightBetGroup(RouletteGame.RouletteType rouletteType) {
        return new BetOptionGroup(1, GetStraightBetOptions(rouletteType));
    }

    private static RouletteBetWizard.BetOptionGroup GetSplitBetGroup(RouletteGame.RouletteType rouletteType) {
        return new BetOptionGroup(2, GetSplitBetOptions(rouletteType));
    }

    private static RouletteBetWizard.BetOptionGroup GetStreetBetGroup(RouletteGame.RouletteType rouletteType) {
        return new BetOptionGroup(3, GetStreetBetOptions(rouletteType));
    }

    private static RouletteBetWizard.BetOptionGroup GetCornerBetGroup(RouletteGame.RouletteType rouletteType) {
        return new BetOptionGroup(4, GetCornerBetOptions(rouletteType));
    }

    private static RouletteBetWizard.BetOptionGroup GetSixInLineBetGroup(RouletteGame.RouletteType rouletteType) {
        return new BetOptionGroup(6, GetSixInLineBetOptions(rouletteType));
    }

    private static RouletteBetWizard.BetOptionGroup GetTrioBetGroup(RouletteGame.RouletteType rouletteType) throws Exception {
        if (rouletteType != RouletteGame.RouletteType.FRENCH) {
            throw new Exception("Trio is only valid for franch roulette.");
        }

        return new BetOptionGroup(3, GetTrioBetOptions(rouletteType));
    }

    private static RouletteBetWizard.BetOptionGroup GetBasketBetGroup(RouletteGame.RouletteType rouletteType) throws Exception {
        if (rouletteType != RouletteGame.RouletteType.AMERICAN) {
            throw new Exception("Basket is only valid for american roulette.");
        }

        return new BetOptionGroup(3, GetBasketBetOptions(rouletteType));
    }

    private static RouletteBetWizard.BetOptionGroup GetColumnBetGroup(RouletteGame.RouletteType rouletteType) throws Exception {
        return new BetOptionGroup(12, GetColumnBetOptions(rouletteType));
    }

    public static BetOptionGroup GetBetOptionsForBetType(RouletteGame.RouletteType rouletteType, RouletteGame.BetType betType) throws Exception {
        switch (betType) {
            case STRAIGHT:
                return GetStraightBetGroup(rouletteType);
            case SPLIT:
                return GetSplitBetGroup(rouletteType);
            case TRIO:
                return GetTrioBetGroup(rouletteType);
            case CORNER:
                return GetCornerBetGroup(rouletteType);
            case SIX_LINE:
                return GetSixInLineBetGroup(rouletteType);
            case BASKET:
                return GetBasketBetGroup(rouletteType);
            case COLUMN:
                return GetColumnBetGroup(rouletteType);
            default:
                throw new Exception("No options for selected bet type");
        }
    }
}
