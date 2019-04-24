package util.models;

import util.Tuple;
import util.models.EmployeeRecord;
import util.models.IRecordsStructure;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeHashMap implements IRecordsStructure {
    // Since UserId can be a string aswell, we have to use string for the first hashmap.
    // Id -> (date, type) -> complete record record
    private HashMap<String, HashMap<Tuple<YearMonth, String>, EmployeeRecord>> employeeMap = new HashMap<>();

    public EmployeeHashMap() {
    }

    public EmployeeRecord getEmployeeRecord(String id, YearMonth date, String wageType) {
        return employeeMap.get(id).get(tuple(date, wageType));
    }

    public EmployeeRecord getEmployeeRecord(EmployeeRecord employeeRecord) {
        return getEmployeeRecord(employeeRecord.userId, employeeRecord.payPeriod, employeeRecord.wageType);
    }

    public HashMap<Tuple<YearMonth, String>, EmployeeRecord> getAllRecordsOfEmployee(String id) {
        return employeeMap.get(id);
    }

    public HashMap<Tuple<YearMonth, String>, EmployeeRecord> getAllRecordsOfEmployee(EmployeeRecord employeeRecord) {
        return getAllRecordsOfEmployee(employeeRecord.userId);
    }

    public List<String> getAllEmployeeIds() {
        return new ArrayList<>(employeeMap.keySet());
    }

    private void putEmployeeRecord(String id, YearMonth date, String wageType, EmployeeRecord employeeRecord) {
        if (containsEmployeeRecord(employeeRecord)) throw new Error("Duplicate");
        if (!employeeMap.containsKey(id)) employeeMap.put(id, new HashMap<>());
        HashMap<Tuple<YearMonth, String>, EmployeeRecord> employeeRecords = employeeMap.get(id);
        employeeRecords.put(tuple(date, wageType), employeeRecord);
    }

    public void putEmployeeRecord(EmployeeRecord employeeRecord) {
        putEmployeeRecord(employeeRecord.userId, employeeRecord.payPeriod, employeeRecord.wageType, employeeRecord);
    }

    public int countEmployeeRecords(String id) {
        return employeeMap.get(id).size();
    }

    public int countEmployeeRecords(EmployeeRecord employeeRecord) {
        return countEmployeeRecords(employeeRecord.userId);
    }

    public int countEmployees() {
        return employeeMap.size();
    }

    public int countTotalRecords() {
        int i = 0;
        for (Map.Entry<String, HashMap<Tuple<YearMonth, String>, EmployeeRecord>> employee : employeeMap.entrySet()) {
            i += employee.getValue().size();
        }
        return i;
    }

    public boolean containsEmployee(EmployeeRecord employeeRecord) {
        return employeeMap.containsKey(employeeRecord.userId);
    }

    public boolean containsEmployeeRecord(EmployeeRecord employeeRecord) {
        // If the first check fails, just return false instantly, as there is no employee with that id.
        return containsEmployee(employeeRecord) && getAllRecordsOfEmployee(employeeRecord).containsKey(tuple(employeeRecord.payPeriod, employeeRecord.wageType));
    }

    public Tuple<YearMonth, String> tuple(YearMonth date, String wageType) {
        return new Tuple<>(date, wageType);
    }
}
