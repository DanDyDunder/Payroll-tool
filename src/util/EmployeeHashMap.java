package util;

import util.models.EmployeeRecord;

import java.time.Year;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;

public class EmployeeHashMap {
    // INSERT USER ID INTO ANOTHER HASHMAP THAT CONTAINS ALL RECORDS OF AN EMPLOYEE FOR EVERY MONTH, Currently date is stored as string instead of date
    // Since UserId can be a string aswell, we have to use string for the first hashmap.
    private HashMap<String, HashMap<YearMonth, EmployeeRecord>> employeeMap = new HashMap<>();

    public EmployeeHashMap() {
    }

    public EmployeeRecord getEmployeeRecord(String id, YearMonth date) {
        return employeeMap.get(id).get(date);
    }

    public EmployeeRecord getEmployeeRecord(EmployeeRecord employeeRecord) {
        return getEmployeeRecord(employeeRecord.userId, employeeRecord.payPeriod);
    }

    public HashMap<YearMonth, EmployeeRecord> getEmployeeMapOfAllRecords(String id) {
        return employeeMap.get(id);
    }

    public HashMap<YearMonth, EmployeeRecord> getEmployeeMapOfAllRecords(EmployeeRecord employeeRecord) {
        return getEmployeeMapOfAllRecords(employeeRecord.userId);
    }

    public void putEmployeeRecord(String id, YearMonth date, EmployeeRecord employeeRecord) {
        if (!employeeMap.containsKey(id)) employeeMap.put(id, new HashMap<>());
        HashMap<YearMonth, EmployeeRecord> employeeRecords = employeeMap.get(id);
        employeeRecords.put(date, employeeRecord);
    }

    public void putEmployeeRecord(EmployeeRecord employeeRecord) {
        putEmployeeRecord(employeeRecord.userId, employeeRecord.payPeriod, employeeRecord);
    }

    public void putManyEmployeeRecords(List<EmployeeRecord> employeeRecords) {
        for (EmployeeRecord employeeRecord : employeeRecords) {
            putEmployeeRecord(employeeRecord);
        }
    }

    public int countEmployeeRecords(String id) {
        return employeeMap.get(id).size();
    }

    public int countEmployees() {
        return employeeMap.size();
    }

    public boolean containsEmployee(EmployeeRecord employeeRecord) {
        return employeeMap.containsKey(employeeRecord.userId);
    }

    public boolean containsEmployeeRecord(EmployeeRecord employeeRecord) {
        // If first fails, just return false instantly
        return containsEmployee(employeeRecord) && getEmployeeMapOfAllRecords(employeeRecord).containsKey(employeeRecord.payPeriod);
    }
}
