package util;

import util.models.EmployeeJsonAdapter;
import util.models.EmployeeRecord;
import util.models.ComparisonMap;

import com.squareup.moshi.*;

import java.lang.reflect.Type;
import java.util.List;

public class JSONParser {

    public static String SFJSONToProperJSON (String SFJson) {
        return SFJson.substring(SFJson.indexOf('['), SFJson.indexOf(']')+1);
    }

    public static void parseComparisonMap(String input) {
        String json;
        try {
            Moshi moshi = new Moshi.Builder().build();
            json = SFJSONToProperJSON(input);
            Type type = Types.newParameterizedType(List.class, ComparisonMap.class);
            JsonAdapter<List<ComparisonMap>> adapter = moshi.adapter(type);
            List<ComparisonMap> maps = adapter.fromJson(json);

            for (ComparisonMap map : maps) {
                System.out.println(map);
            }

            System.out.println("THIS IS THE AMOUNT: " + maps.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parseEmployeeRecords(String input) {
        String json;
        try {
            Moshi moshi = new Moshi.Builder().add(new EmployeeJsonAdapter()).build();
            json = SFJSONToProperJSON(input);
            Type type = Types.newParameterizedType(List.class, EmployeeRecord.class);
            JsonAdapter<List<EmployeeRecord>> adapter = moshi.adapter(type);
            List<EmployeeRecord> employeeRecords = adapter.fromJson(json);

            for (EmployeeRecord employeeRecord : employeeRecords) {
                System.out.println(employeeRecord);
            }
            System.out.println("THIS IS THE AMOUNT: " + employeeRecords.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
