package util;

import util.models.EmployeeRecord;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EmployeeHashMap {
    // INSERT USER ID INTO ANOTHER HASHMAP THAT CONTAINS ALL RECORDS OF AN EMPLOYEE FOR EVERY MONTH, Currently date is stored as string instead of date
    // Since UserId can be a string aswell, we have to use string for the first hashmap.
    private HashMap<String, HashMap<Tuple<YearMonth, String>, EmployeeRecord>> employeeMap = new HashMap<>();

    public EmployeeHashMap() {
    }

    private EmployeeRecord getEmployeeRecord(String id, Tuple<YearMonth, String> dateAndWageType) {
        return employeeMap.get(id).get(dateAndWageType);
    }

    public EmployeeRecord getEmployeeRecord(EmployeeRecord employeeRecord) {
        return getEmployeeRecord(employeeRecord.userId, tuple(employeeRecord.payPeriod, employeeRecord.wageType));
    }

    private HashMap<Tuple<YearMonth, String>, EmployeeRecord> getAllRecordsOfEmployee(String id) {
        return employeeMap.get(id);
    }

    public HashMap<Tuple<YearMonth, String>, EmployeeRecord> getAllRecordsOfEmployee(EmployeeRecord employeeRecord) {
        return getAllRecordsOfEmployee(employeeRecord.userId);
    }

    private void putEmployeeRecord(String id, YearMonth date, String wageType, EmployeeRecord employeeRecord) {
        if (!employeeMap.containsKey(id)) employeeMap.put(id, new HashMap<>());
        HashMap<Tuple<YearMonth, String>, EmployeeRecord> employeeRecords = employeeMap.get(id);
        employeeRecords.put(tuple(date, wageType), employeeRecord);
    }

    public void putEmployeeRecord(EmployeeRecord employeeRecord) {
        putEmployeeRecord(employeeRecord.userId, employeeRecord.payPeriod, employeeRecord.wageType, employeeRecord);
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

    private class Tuple<T1, T2> {
        public T1 item1;
        public T2 item2;

        public Tuple(T1 item1, T2 item2) {
            this.item1 = item1;
            this.item2 = item2;
        }

        public T1 getItem1() {
            return item1;
        }

        public void setItem1(T1 item1) {
            this.item1 = item1;
        }

        public T2 getItem2() {
            return item2;
        }

        public void setItem2(T2 item2) {
            this.item2 = item2;
        }

        @Override
        public boolean equals(Object anObject) {
            if (this == anObject) return true;
            if (anObject == null || getClass() != anObject.getClass()) return false;
            Tuple<?, ?> aTuple = (Tuple<?, ?>) anObject;
            return item1.equals(aTuple.item1) &&
                    item2.equals(aTuple.item2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(item1, item2);
        }
    }
}
