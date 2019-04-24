import util.Tuple;
import util.models.ComparisonMap;
import util.models.ComparisonResult;
import util.models.EmployeeHashMap;
import util.models.EmployeeRecord;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comparer {
    // A list, that contains a tuple of a source EHM and a target EHM for every run.
    private List<Tuple<EmployeeHashMap, EmployeeHashMap>> recordsPerRun = new ArrayList<>();
    private ComparisonMap comparisonMap;

    public Comparer(List<EmployeeRecord> records, ComparisonMap comparisonMap) {
        this.comparisonMap = comparisonMap;
        init(records);
    }

    public void init(List<EmployeeRecord> records) {
        for (EmployeeRecord record:records) {
            int runNumber = record.runNumber;
            int index = runNumber-1;
            if (runNumber > recordsPerRun.size()) recordsPerRun.add(new Tuple<>(new EmployeeHashMap(), new EmployeeHashMap()));
            Tuple<EmployeeHashMap, EmployeeHashMap> tuple = recordsPerRun.get(index);
            switch (record.sourceTarget) {
                case SOURCE:
                    tuple.item1.putEmployeeRecord(record);
                    break;
                case TARGET:
                    tuple.item2.putEmployeeRecord(record);
                    break;
            }
        }
    }

    public List<ComparisonResult> compareRun(Tuple<EmployeeHashMap, EmployeeHashMap> run) {
        List<ComparisonResult> finalResults = new ArrayList<>();
        EmployeeHashMap source = run.item1;
        EmployeeHashMap target = run.item2;
        List<String> allIds = source.getAllEmployeeIds();
        for (String sourceId : allIds) {
            String targetId = comparisonMap.getIdMapping(sourceId);
            HashMap<Tuple<YearMonth, String>, EmployeeRecord> recordsOfEmployee = source.getAllRecordsOfEmployee(sourceId);
            for (Map.Entry<Tuple<YearMonth, String>, EmployeeRecord> employee : recordsOfEmployee.entrySet()) {
                EmployeeRecord sourceRecord = employee.getValue();
                YearMonth date = sourceRecord.payPeriod;
                String payTypeSource = sourceRecord.wageType;
                double amtpernumSource = sourceRecord.amtpernum;
                String payTypeTarget = comparisonMap.getPayTypeMapping(payTypeSource);
                // Add null-check
                EmployeeRecord targetRecord = target.getEmployeeRecord(targetId, date, payTypeTarget);
                if (targetRecord == null) continue;
                double amtpernumTarget = targetRecord.amtpernum;

                finalResults.add(generateComparisonResult(sourceId, amtpernumSource, amtpernumTarget,
                                                          payTypeSource, payTypeTarget));
            }
        }
        return finalResults;
    }

    public ComparisonResult generateComparisonResult(String id, double amtpernumSource, double amtpernumTarget,
                                                     String payTypeSource, String payTypeTarget) {
        double deviation = amtpernumSource-amtpernumTarget;
        ComparisonResult comparisonResult = new ComparisonResult(
                id,
                amtpernumSource,
                amtpernumTarget,
                deviation,
                payTypeSource,
                payTypeTarget,
                (deviation==0));
        return comparisonResult;
    }

    public List<Tuple<EmployeeHashMap, EmployeeHashMap>> getRecordsPerRun() {
        return recordsPerRun;
    }
}
