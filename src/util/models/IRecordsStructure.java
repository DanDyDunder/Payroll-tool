package util.models;

import util.Tuple;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;

// If EHM is replaces, the IRecordsStructure ensures that the code still works, if a fitting correct datastructure takes over
public interface IRecordsStructure {
    EmployeeRecord getEmployeeRecord(EmployeeRecord employeeRecord);
    EmployeeRecord getEmployeeRecord(String id, YearMonth date, String wageType);
    HashMap<Tuple<YearMonth, String>, EmployeeRecord> getAllRecordsOfEmployee(EmployeeRecord employeeRecord);
    HashMap<Tuple<YearMonth, String>, EmployeeRecord> getAllRecordsOfEmployee(String id);
    List<String> getAllEmployeeIds();
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
