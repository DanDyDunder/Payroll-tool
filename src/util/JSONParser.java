package util;

import util.models.ComparisonResult;
import util.models.JSON.*;
import util.models.EmployeeRecord;
import util.models.ComparisonMapRecord;

import com.squareup.moshi.*;
import util.models.KPI;

import java.lang.reflect.Type;
import java.util.List;

// JSONParser, uses moshi for parsing
// Parses by inlining what class that is to be used for a model, if the class is not going to be directly mapped
// to a model, we use an adapter class, that processes the JSON-data, into data we can use for comparisons.
public class JSONParser {

    // Removes the format imposed by SuccessFactors to properly parse the JSON through moshi
    public static String SFJSONToProperJSON (String SFJson) {
        return SFJson.substring(SFJson.indexOf('['), SFJson.indexOf(']')+1);
    }

    // Creates a moshi-object, that parses ComparisonMap json to our custom ComparisonMapRecord class.
    public static List<ComparisonMapRecord> parseComparisonMap(String input) {
        String json;
        List<ComparisonMapRecord> maps = null;
        try {
            // During instatiation, we tell moshi that we want to process the JSON data through our custom adapter
            Moshi moshi = new Moshi.Builder().add(new ComparisonMapJsonAdapter()).build();
            json = SFJSONToProperJSON(input);
            // Specifies that we are parsing multiple objects, and that we want those objects returned into a list.
            Type type = Types.newParameterizedType(List.class, ComparisonMapRecord.class);
            // Get our results through the adapter
            JsonAdapter<List<ComparisonMapRecord>> adapter = moshi.adapter(type);
            // The final results
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeRecords;
    }

    public static List<RunResultJson> parseRunResult(String input) {
        String json;
        List<RunResultJson> results = null;
        try {
            Moshi moshi = new Moshi.Builder().build();
            json = SFJSONToProperJSON(input);
            Type type = Types.newParameterizedType(List.class, RunResultJson.class);
            JsonAdapter<List<RunResultJson>> adapter = moshi.adapter(type);
            results = adapter.fromJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public static List<KPI> parseKPIs(String input) {
        String json;
        List<KPI> results = null;
        try {
            Moshi moshi = new Moshi.Builder().add(new KpiJsonAdapter()).build();
            json = SFJSONToProperJSON(input);
            Type type = Types.newParameterizedType(List.class, KPI.class);
            JsonAdapter<List<KPI>> adapter = moshi.adapter(type);
            results = adapter.fromJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    // Takes a list of ComparisonResults and turns them into JSON that we can upsert into our model
    public static String runResultToJson(List<ComparisonResult> cResults) {
        String jsonResult = "";
        try {
            Moshi moshi = new Moshi.Builder().add(new RunResultJsonAdapter()).build();
            Type type = Types.newParameterizedType(List.class, ComparisonResult.class);
            JsonAdapter<List<ComparisonResult>> adapter = moshi.adapter(type);
            jsonResult = adapter.toJson(cResults);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    public static String kpiToJson(List<KPI> kpis) {
        String jsonResult = "";
        try {
            Moshi moshi = new Moshi.Builder().add(new KpiJsonAdapter()).build();
            Type type = Types.newParameterizedType(List.class, KPI.class);
            JsonAdapter<List<KPI>> adapter = moshi.adapter(type);
            jsonResult = adapter.toJson(kpis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}
