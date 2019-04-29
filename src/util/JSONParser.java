package util;

import util.models.ComparisonResult;
import util.models.JSON.*;
import util.models.EmployeeRecord;
import util.models.ComparisonMapRecord;

import com.squareup.moshi.*;
import util.models.KPI;

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
