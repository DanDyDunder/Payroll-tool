import util.Tuple;
import util.models.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comparer {
    // A list, that contains a tuple of a source EHM and a target EHM for every run.
    private List<Tuple<IRecordsStructure, IRecordsStructure>> recordsPerRun = new ArrayList<>();
    private IComparisonMap comparisonMap;
    private int runNumberIndex;
    private HashMap<Integer, Integer> runNumToIndex;

    public Comparer(List<EmployeeRecord> records, IComparisonMap comparisonMap) {
        this.comparisonMap = comparisonMap;
        runNumToIndex = new HashMap<>();
        init(records);
    }

    // Split every record inserted into runs, and then a source and a target map, when finalized, we have sorted every record
    public void init(List<EmployeeRecord> records) {
        for (EmployeeRecord record:records) {
            int index = runNumberToIndex(record.runNumber);
            if (runNumberIndex > recordsPerRun.size()) recordsPerRun.add(new Tuple<>(new EmployeeHashMap(), new EmployeeHashMap()));
            Tuple<IRecordsStructure, IRecordsStructure> tuple = recordsPerRun.get(index);
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

    // Generate all ComparisonResults for a run, and the attached KPI for that run.
    // For every sourceId, find the matching targetId, gain relevant information from the records, and store it in a ComparisonResult
    public Tuple<List<ComparisonResult>, KPI> generateComparisonResults(Tuple<IRecordsStructure, IRecordsStructure> run) {
        List<ComparisonResult> finalResults = new ArrayList<>();
        KPI currentKPI = new KPI();
        IRecordsStructure source = run.item1;
        IRecordsStructure target = run.item2;
        List<String> allIds = source.getAllEmployeeIds();
        for (String sourceId : allIds) {
            String targetId = comparisonMap.getIdMapping(sourceId);
            HashMap<Tuple<YearMonth, String>, EmployeeRecord> recordsOfEmployee = source.getAllRecordsOfEmployee(sourceId);

            for (Map.Entry<Tuple<YearMonth, String>, EmployeeRecord> employee : recordsOfEmployee.entrySet()) {
                ComparisonResult cResult;
                EmployeeRecord sourceRecord = employee.getValue();
                int runNumber = sourceRecord.runNumber;
                currentKPI.runNumber = runNumber;
                YearMonth date = sourceRecord.payPeriod;
                String payTypeSource = sourceRecord.wageType;
                double amtpernumSource = sourceRecord.amtpernum;
                String payTypeTarget = comparisonMap.getPayTypeMapping(payTypeSource);
                EmployeeRecord targetRecord = target.getEmployeeRecord(targetId, date, payTypeTarget);
                if (targetRecord == null) {
                    currentKPI.missing++;
                    cResult = generateCorrectComparisonResult(runNumber, sourceId, "MISSING", date, amtpernumSource, -1, payTypeSource, "MISSING");
                    finalResults.add(cResult);
                    continue;
                } else {
                    double amtpernumTarget = targetRecord.amtpernum;

                    cResult = generateCorrectComparisonResult(runNumber, sourceId, targetId, date, amtpernumSource, amtpernumTarget,
                            payTypeSource, payTypeTarget);
                    addMatchToKPI(currentKPI, cResult.status);
                    finalResults.add(cResult);
                }
            }
        }
        calculateKPIs(currentKPI, source, target);
        return new Tuple(finalResults, currentKPI);
    }

    // Utility method, to navigate the list of records for every run.
    private int runNumberToIndex(int runNumber) {
        if (!runNumToIndex.containsKey(runNumber)) runNumToIndex.put(runNumber, runNumberIndex++);
        return runNumToIndex.get(runNumber);
    }

    // Generate KPI parameters throughout the generateComparisonResults()
    private void calculateKPIs(KPI currentKPI, IRecordsStructure source, IRecordsStructure target) {
        int redundancies = 0;
        int missing = 0;
        int employeeCountDifference = target.countEmployees()-source.countEmployees();
        int recordCountDifference = target.countTotalRecords()-source.countTotalRecords();

        if (employeeCountDifference > 0) redundancies += employeeCountDifference;
        else if (employeeCountDifference < 0) missing += employeeCountDifference;
        if (recordCountDifference > 0) redundancies += recordCountDifference;
        else if (recordCountDifference < 0) missing += recordCountDifference;

        currentKPI.total = source.countTotalRecords();
        currentKPI.redundancies += redundancies;
        currentKPI.missing += missing;
        currentKPI.calculatePercentages(null);
    }

    private void addMatchToKPI(KPI kpi, boolean status) {
        if (status) kpi.matches++;
        else kpi.deviations++;
    }

    // Creates ComparisonResult at the end of a match between a sourceId and targetId
    public ComparisonResult generateCorrectComparisonResult(int runNumber, String idSource, String idTarget, YearMonth date, double amtpernumSource, double amtpernumTarget,
                                                            String payTypeSource, String payTypeTarget) {
        double deviation = amtpernumTarget-amtpernumSource;
        ComparisonResult comparisonResult = new ComparisonResult(
                runNumber,
                idSource,
                idTarget,
                date,
                amtpernumSource,
                amtpernumTarget,
                deviation,
                payTypeSource,
                payTypeTarget,
                (deviation<0.001 && deviation>-0.001));
        return comparisonResult;
    }

    public List<Tuple<IRecordsStructure, IRecordsStructure>> getRecordsPerRun() {
        return recordsPerRun;
    }
}
