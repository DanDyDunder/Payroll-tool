import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.models.ComparisonMap;
import util.models.ComparisonMapRecord;
import util.models.IComparisonMap;

import java.util.ArrayList;
import java.util.List;

class IComparisonMapTest {
    private IComparisonMap comparisonMap;
    private ComparisonMapRecord id1;
    private ComparisonMapRecord id2;
    private ComparisonMapRecord id3;
    private ComparisonMapRecord pay1;
    private ComparisonMapRecord pay2;
    private ComparisonMapRecord pay3;
    private ComparisonMapRecord pay4;
    private ComparisonMapRecord company1;
    private ComparisonMapRecord company2;
    private ComparisonMapRecord company3;
    private ComparisonMapRecord company4;
    private ComparisonMapRecord company5;
    private List<ComparisonMapRecord> allMappings;

    @BeforeEach
    void setUp() {
        comparisonMap = new ComparisonMap();
        id1 = new ComparisonMapRecord("User ID", "1", "one");
        id2 = new ComparisonMapRecord("User ID", "2", "two");
        id3 = new ComparisonMapRecord("User ID", "5", "five");
        pay1 = new ComparisonMapRecord("Pay Component", "normal", "100");
        pay2 = new ComparisonMapRecord("Pay Component", "extra", "150");
        pay3 = new ComparisonMapRecord("Pay Component", "half", "50");
        pay4 = new ComparisonMapRecord("Pay Component", "quarter", "25");
        company1 = new ComparisonMapRecord("Company Code", "A", "Alpha");
        company2 = new ComparisonMapRecord("Company Code", "B", "Beta");
        company3 = new ComparisonMapRecord("Company Code", "C", "Charlie");
        company4 = new ComparisonMapRecord("Company Code", "D", "Delta");
        company5 = new ComparisonMapRecord("Company Code", "E", "Echo");
        allMappings = new ArrayList<>(List.of(id1, id2, pay1, pay2, company1, company2));
    }

    @AfterEach
    void tearDown() {
        comparisonMap = null;
    }

    @Test
    void getPayTypeMapping() {
        // Initialize
        comparisonMap.putManyComparisonMappings(allMappings);


        // Action and result 1
        int result1 = comparisonMap.countPayTypeMappings();

        // Action and result 2
        comparisonMap.putManyComparisonMappings(new ArrayList<>(List.of(pay3, pay4)));
        int result2 = comparisonMap.countPayTypeMappings();

        Assertions.assertEquals(2, result1);
        Assertions.assertEquals(4, result2);
    }

    @Test
    void getIdMapping() {
        // Initialize
        comparisonMap.putManyComparisonMappings(allMappings);


        // Action and result 1
        int result1 = comparisonMap.countIdMappings();

        // Action and result 2
        comparisonMap.putComparisonMapping(id3);
        int result2 = comparisonMap.countIdMappings();

        Assertions.assertEquals(2, result1);
        Assertions.assertEquals(3, result2);
    }

    @Test
    void getCompanyCodeMapping() {
        // Initialize
        comparisonMap.putManyComparisonMappings(allMappings);


        // Action and result 1
        int result1 = comparisonMap.countCompanyCodeMappings();

        // Action and result 2
        comparisonMap.putManyComparisonMappings(new ArrayList<>(List.of(company3, company4, company5)));
        int result2 = comparisonMap.countCompanyCodeMappings();

        Assertions.assertEquals(2, result1);
        Assertions.assertEquals(5, result2);
    }

    @Test
    void putComparisonMapping() {
        // Action
        comparisonMap.putComparisonMapping(pay3);
        comparisonMap.putComparisonMapping(pay1);
        comparisonMap.putComparisonMapping(company4);
        comparisonMap.putComparisonMapping(company1);
        comparisonMap.putComparisonMapping(company5);
        comparisonMap.putComparisonMapping(id1);

        // Result
        int resultTotalCount = comparisonMap.countTotalMappings();
        boolean containsId1 = comparisonMap.containsIdMapping("1");
        boolean containsId2 = comparisonMap.containsIdMapping("2");
        boolean containsId3 = comparisonMap.containsIdMapping("3");
        boolean containsNeither2and3 = containsId2 || containsId3;
        boolean containsPay1 = comparisonMap.containsPayTypeMapping("normal");
        boolean containsCompany1 = comparisonMap.containsCompanyCodeMapping("A");


        Assertions.assertEquals(6, resultTotalCount);
        Assertions.assertEquals(true, containsId1);
        Assertions.assertEquals(false, containsNeither2and3);
        Assertions.assertEquals(true,  containsPay1);
        Assertions.assertEquals(true, containsCompany1);
    }

    @Test
    void putManyComparisonMappings() {
        // Action
        List<ComparisonMapRecord> mappings = new ArrayList<>(List.of(pay3, pay1, company4, company1, company5, id1));
        comparisonMap.putManyComparisonMappings(mappings);

        // Result
        int resultTotalCount = comparisonMap.countTotalMappings();
        boolean containsId1 = comparisonMap.containsIdMapping("1");
        boolean containsId2 = comparisonMap.containsIdMapping("2");
        boolean containsId3 = comparisonMap.containsIdMapping("3");
        boolean containsNeither2and3 = containsId2 || containsId3;
        boolean containsPay1 = comparisonMap.containsPayTypeMapping("normal");
        boolean containsCompany1 = comparisonMap.containsCompanyCodeMapping("A");


        Assertions.assertEquals(6, resultTotalCount);
        Assertions.assertEquals(true, containsId1);
        Assertions.assertEquals(false, containsNeither2and3);
        Assertions.assertEquals(true,  containsPay1);
        Assertions.assertEquals(true, containsCompany1);
    }

    @Test
    void countTotalMappings() {
        // Action
        List<ComparisonMapRecord> mappings = new ArrayList<>(List.of(pay3, pay2, pay1, company4, company1, company5, id1, id3));
        comparisonMap.putManyComparisonMappings(mappings);

        // Result
        int resultTotalCount = comparisonMap.countTotalMappings();

        Assertions.assertEquals(8, resultTotalCount);
    }
}