package util;

import util.models.JSON.ComparisonMapJsonAdapter;
import util.models.JSON.EmployeeJsonAdapter;
import util.models.EmployeeRecord;
import util.models.ComparisonMapRecord;

import com.squareup.moshi.*;
import util.models.JSON.RunResultJson;

import java.lang.reflect.Type;
import java.util.List;

public class JSONParser {

    public static String SFJSONToProperJSON (String SFJson) {
        return SFJson.substring(SFJson.indexOf('['), SFJson.indexOf(']')+1);
    }

    public static List<ComparisonMapRecord> parseComparisonMap(String input) {
        String json;
        List<ComparisonMapRecord> maps = null;
        try {
            Moshi moshi = new Moshi.Builder().add(new ComparisonMapJsonAdapter()).build();
            json = SFJSONToProperJSON(input);
            Type type = Types.newParameterizedType(List.class, ComparisonMapRecord.class);
            JsonAdapter<List<ComparisonMapRecord>> adapter = moshi.adapter(type);
            maps = adapter.fromJson(json);

            return maps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }

    public static List<EmployeeRecord> parseEmployeeRecords(String input) {
        String json;
        List<EmployeeRecord> employeeRecords = null;
        try {
            Moshi moshi = new Moshi.Builder().add(new EmployeeJsonAdapter()).build();
            json = SFJSONToProperJSON(input);
            Type type = Types.newParameterizedType(List.class, EmployeeRecord.class);
            JsonAdapter<List<EmployeeRecord>> adapter = moshi.adapter(type);
            employeeRecords = adapter.fromJson(json);

            for (EmployeeRecord employeeRecord : employeeRecords) {
                System.out.println(employeeRecord);
            }
            System.out.println("THIS IS THE AMOUNT: " + employeeRecords.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeRecords;
    }

    public static void parseRunResult(String input) {
        String json;
        try {
            Moshi moshi = new Moshi.Builder().build();
            json = SFJSONToProperJSON(input);
            Type type = Types.newParameterizedType(List.class, RunResultJson.class);
            JsonAdapter<List<RunResultJson>> adapter = moshi.adapter(type);
            List<RunResultJson> results = adapter.fromJson(json);

            for (RunResultJson result : results) {
                System.out.println(result);
            }

            System.out.println("THIS IS THE AMOUNT: " + results.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
