package util.models;

public class Employee {
    public final String externalName;
    public final String cust_costCenter;
    public final String cust_userId;
    public final String cust_amtpernum;
    public final String cust_wageType;
    public final String cust_companyCode;
    public final String cust_payPeriod;
    public final String cust_payrollArea;
    public final String cust_sourcetarget;

    public Employee(String externalName, String cust_costCenter, String cust_userId, String cust_amtpernum,
                    String cust_wageType, String cust_companyCode, String cust_payPeriod,
                    String cust_payrollArea, String cust_sourcetarget) {
        this.externalName = externalName;
        this.cust_costCenter = cust_costCenter;
        this.cust_userId = cust_userId;
        this.cust_amtpernum = cust_amtpernum;
        this.cust_wageType = cust_wageType;
        this.cust_companyCode = cust_companyCode;
        this.cust_payPeriod = cust_payPeriod;
        this.cust_payrollArea = cust_payrollArea;
        this.cust_sourcetarget = cust_sourcetarget;
    }

    @Override
    public String toString() {
        return String.format("External name: %s\nCost center: %s\nUser id: %s\nAmount/Percentage/Number: %s\n" +
                "Wage type: %s\nCompany code: %s\nPay period: %s\nPayroll Area: %s\nSource or target: %s",
                externalName, cust_costCenter, cust_userId, cust_amtpernum, cust_wageType, cust_companyCode,
                cust_payPeriod, cust_payrollArea, cust_sourcetarget);
    }
}
