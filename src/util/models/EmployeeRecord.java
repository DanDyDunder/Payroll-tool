package util.models;

import java.time.YearMonth;

public class EmployeeRecord {
    public final String runNumber;
    public final String costCenter;
    // UserId can be a string and numbers
    public final String userId;
    // Precision hasn't been specified, so we keep it as a string for now
    public final String amtpernum;
    public final String wageType;
    public final String companyCode;
    public final YearMonth payPeriod;
    public final String payrollArea;
    // Maybe refactor this to enum
    public final SourceTarget sourceTarget;

    public EmployeeRecord(String runNumber, String costCenter, String userId,
                          String amtpernum, String wageType, String companyCode,
                          YearMonth payPeriod, String payrollArea, String sourceTarget) {
        this.runNumber = runNumber;
        this.costCenter = costCenter;
        this.userId = userId;
        this.amtpernum = amtpernum;
        this.wageType = wageType;
        this.companyCode = companyCode;
        this.payPeriod = payPeriod;
        this.payrollArea = payrollArea;
        this.sourceTarget = stringToSourceTarget(sourceTarget);
    }

    private SourceTarget stringToSourceTarget(String sourcetarget) {
        switch (sourcetarget) {
            case "Source":
                return SourceTarget.SOURCE;
            case "Target":
                return SourceTarget.TARGET;
        }
        return SourceTarget.ERROR;
    }

    public enum SourceTarget {
        SOURCE, TARGET, ERROR;
    }

    @Override
    public String toString() {
        return String.format("External name: %s\nCost center: %s\nUser id: %s\nAmount/Percentage/Number: %s\n" +
                "Wage type: %s\nCompany code: %s\nPay period: %s\nPayroll Area: %s\nSource or target: %s",
                runNumber, costCenter, userId, amtpernum, wageType, companyCode,
                payPeriod, payrollArea, sourceTarget);
    }
}
