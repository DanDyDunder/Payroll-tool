import org.junit.jupiter.api.*;
import util.EmployeeHashMap;
import util.models.EmployeeRecord;
import util.models.RecordsStructure;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

class RecordsStructureTest {

    private RecordsStructure recordStructure;
    private EmployeeRecord e1;
    private EmployeeRecord e2;
    private EmployeeRecord e3;

    @BeforeEach
    void setUp() {
        recordStructure = new EmployeeHashMap();
        e1 = new EmployeeRecord("1", "A", "001", "10", "1", "13", YearMonth.of(2019, 10), "DK", "source");
        e2 = new EmployeeRecord("1", "A", "001", "50", "2", "13", YearMonth.of(2019, 11), "DK", "source");
        e3 = new EmployeeRecord("2", "A", "002", "10", "1", "13", YearMonth.of(2019, 12), "DK", "target");
    }

    @AfterEach
    void tearDown() {
        recordStructure = null;
    }

    @Test
    void getEmployeeRecord() {


    }

    @Test
    void getEmployeeMapOfAllRecords() {
    }

    @Test
    void putEmployeeRecord() {
        // Action
        recordStructure.putEmployeeRecord(e1);
        recordStructure.putEmployeeRecord(e2);
        recordStructure.putEmployeeRecord(e3);

        // Get result
        int resultAmountOfEmployees = recordStructure.countEmployees();
        int resultAmountOfRecords = recordStructure.countTotalRecords();

        Assertions.assertEquals(2, resultAmountOfEmployees);
        Assertions.assertEquals(3, resultAmountOfRecords);
    }

    @Test
    void putManyEmployeeRecords() {
        // Initialize
        ArrayList<EmployeeRecord> employees = new ArrayList<>(List.of(e1, e2, e3));

        // Action
        recordStructure.putManyEmployeeRecords(employees);

        // Get result
        int resultAmountOfEmployees = recordStructure.countEmployees();
        int resultAmountOfRecords = recordStructure.countTotalRecords();

        Assertions.assertEquals(2, resultAmountOfEmployees);
        Assertions.assertEquals(3, resultAmountOfRecords);
    }

    @Test
    void countEmployeeRecords() {
    }

    @Test
    void countEmployees() {
    }

    @Test
    void countTotalRecords() {
    }

    @Test
    void containsEmployee() {
        // Initialize
        ArrayList<EmployeeRecord> employees = new ArrayList<>(List.of(e1, e2, e3));
        EmployeeRecord sameEmployeeDifferentObjectE1 = new EmployeeRecord("1", "A", "001", "10", "1", "13", YearMonth.of(2019, 10), "DK", "source");
        EmployeeRecord sameEmployeeDifferentObjectE3 = new EmployeeRecord("2", "A", "002", "10", "1", "13", YearMonth.of(2019, 12), "DK", "target");
        EmployeeRecord falseEmployeeRecord = new EmployeeRecord("2", "A", "005", "10", "1", "13", YearMonth.of(2019, 12), "DK", "target");


        // Action
        recordStructure.putManyEmployeeRecords(employees);

        // Get result
        boolean resultContains001 = recordStructure.containsEmployee(sameEmployeeDifferentObjectE1);
        boolean resultContains002 = recordStructure.containsEmployee(sameEmployeeDifferentObjectE3);
        boolean containsFalseEmployee = recordStructure.containsEmployee(falseEmployeeRecord);

        Assertions.assertEquals(true, resultContains001);
        Assertions.assertEquals(true, resultContains002);
        Assertions.assertEquals(false, containsFalseEmployee);
    }

    @Test
    void containsEmployeeRecord() {
        // Initialize
        ArrayList<EmployeeRecord> employees = new ArrayList<>(List.of(e1, e2, e3));
        EmployeeRecord sameEmployeeDifferentObjectE2 = new EmployeeRecord("1", "A", "001", "50", "2", "13", YearMonth.of(2019, 11), "DK", "source");

        // Action
        recordStructure.putManyEmployeeRecords(employees);

        // Get result
        boolean resultContainsRecord2 = recordStructure.containsEmployeeRecord(sameEmployeeDifferentObjectE2);

        Assertions.assertEquals(true, resultContainsRecord2);
    }
}