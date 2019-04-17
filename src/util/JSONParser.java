package util;

import util.models.Employee;
import util.models.PayMap;

import com.squareup.moshi.*;
import okio.Buffer;

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
            Type type = Types.newParameterizedType(List.class, PayMap.class);
            JsonAdapter<List<PayMap>> adapter = moshi.adapter(type);
            List<PayMap> maps = adapter.fromJson(json);

            for (PayMap map : maps) {
                System.out.println(map);
            }

            System.out.println("THIS IS THE AMOUNT: " + maps.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parseEmployeeResult(String input) {
        String json;
        try {
            Moshi moshi = new Moshi.Builder().build();
            json = SFJSONToProperJSON(input);
            Type type = Types.newParameterizedType(List.class, Employee.class);
            JsonAdapter<List<Employee>> adapter = moshi.adapter(type);
            List<Employee> employees = adapter.fromJson(json);

            for (Employee employee : employees) {
                System.out.println(employee);
            }
            System.out.println("THIS IS THE AMOUNT: " + employees.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
