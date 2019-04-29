package util.models.JSON;

import java.util.HashMap;

public class JsonUtils {
    private static HashMap<String, Integer> paytypeToInt = new HashMap<String, Integer>();
    private static HashMap<String, Integer> dateToInt = new HashMap<String, Integer>();
    private static int currentPayNumber = 0;
    private static int currentDateNumber = 0;

    public static String generateExternalCode(String runNumber, String id, String period, String wagetype) {
        generatePayTypeIntegers(wagetype);
        generateDateIntegers(period);
        return "" + runNumber + id + dateToInt.get(period) + paytypeToInt.get(wagetype);
    }

    public static void generatePayTypeIntegers(String paytype) {
        if (!paytypeToInt.containsKey(paytype)) paytypeToInt.put(paytype, currentPayNumber++);
    }

    public static void generateDateIntegers(String date) {
        if (!dateToInt.containsKey(date)) dateToInt.put(date, currentDateNumber++);
    }
}
