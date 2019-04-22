package util.models;

import util.Tuple;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;

public interface IRecordsStructure {
    EmployeeRecord getEmployeeRecord(EmployeeRecord employeeRecord);
    HashMap<Tuple<YearMonth, String>, EmployeeRecord> getAllRecordsOfEmployee(EmployeeRecord employeeRecord);
    void putEmployeeRecord(EmployeeRecord employeeRecord);
    default void putManyEmployeeRecords(List<EmployeeRecord> employeeRecords) {
        for (EmployeeRecord employeeRecord : employeeRecords) {
            putEmployeeRecord(employeeRecord);
        }
    }
    int countEmployeeRecords(EmployeeRecord employeeRecord);
    int countEmployees();
    int countTotalRecords();
    boolean containsEmployee(EmployeeRecord employeeRecord);
    boolean containsEmployeeRecord(EmployeeRecord employeeRecord);
}
