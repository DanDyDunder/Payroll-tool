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
    public final String sourcetarget;

    public EmployeeRecord(String runNumber, String costCenter, String userId,
                          String amtpernum, String wageType, String companyCode,
                          YearMonth payPeriod, String payrollArea, String sourcetarget) {
        this.runNumber = runNumber;
        this.costCenter = costCenter;
        this.userId = userId;
        this.amtpernum = amtpernum;
        this.wageType = wageType;
        this.companyCode = companyCode;
        this.payPeriod = payPeriod;
        this.payrollArea = payrollArea;
        this.sourcetarget = sourcetarget;
    }

    @Override
    public String toString() {
        return String.format("External name: %s\nCost center: %s\nUser id: %s\nAmount/Percentage/Number: %s\n" +
                "Wage type: %s\nCompany code: %s\nPay period: %s\nPayroll Area: %s\nSource or target: %s",
                runNumber, costCenter, userId, amtpernum, wageType, companyCode,
                payPeriod, payrollArea, sourcetarget);
    }
}
