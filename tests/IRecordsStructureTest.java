import org.junit.jupiter.api.*;
import util.models.EmployeeHashMap;
import util.models.EmployeeRecord;
import util.models.IRecordsStructure;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

class IRecordsStructureTest {

    private IRecordsStructure recordStructure;
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
        // Initialize
        ArrayList<EmployeeRecord> employees = new ArrayList<>(List.of(e1, e2, e3));

        // Action
        recordStructure.putManyEmployeeRecords(employees);

        // Get result
        int resultAmountOf001Records = recordStructure.countEmployeeRecords(e1);

        Assertions.assertEquals(2, resultAmountOf001Records);

    }

    @Test
    void getEmployeeMapOfAllRecords() {
        // Initialize
        ArrayList<EmployeeRecord> employees = new ArrayList<>(List.of(e1, e2, e3));

        // Action
        recordStructure.putManyEmployeeRecords(employees);

        // Get result
        int resultAmountOfRecords = recordStructure.countTotalRecords();

        Assertions.assertEquals(3, resultAmountOfRecords);
    }

    @Test
    void putEmployeeRecord() {
        // Action
        recordStructure.putEmployeeRecord(e1);
        recordStructure.putEmployeeRecord(e2);
        recordStructure.putEmployeeRecord(e3);

        // Get result
        int resultAmountOfEmployees = recordStructure.countEmployees();
        int resultAmountOf001Records = recordStructure.countEmployeeRecords(e1);

        int resultAmountOfRecords = recordStructure.countTotalRecords();

        Assertions.assertEquals(2, resultAmountOf001Records);
        Assertions.assertEquals(2, resultAmountOfEmployees);
        Assertions.assertEquals(3, resultAmountOfRecords);
    }

    @Test
    void putEmployeeThrowsException() {
        // Action
        recordStructure.putEmployeeRecord(e1);
        Error thrown = Assertions.assertThrows(Error.class, () -> recordStructure.putEmployeeRecord(e1), "Same record inserted twice, redundancy in JSON not detected");

        Assertions.assertTrue(thrown.getMessage().contains("Redundancy"));
    }

    @Test
    void putManyEmployeeRecords() {
        // Initialize
        ArrayList<EmployeeRecord> employees = new ArrayList<>(List.of(e1, e2, e3));

        // Action
        recordStructure.putManyEmployeeRecords(employees);

        // Get result
        int resultAmountOfEmployees = recordStructure.countEmployees();
        int resultAmountOf001Records = recordStructure.countEmployeeRecords(e1);
        int resultAmountOfRecords = recordStructure.countTotalRecords();

        Assertions.assertEquals(2, resultAmountOf001Records);
        Assertions.assertEquals(2, resultAmountOfEmployees);
        Assertions.assertEquals(3, resultAmountOfRecords);
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