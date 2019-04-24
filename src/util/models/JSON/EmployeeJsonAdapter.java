package util.models.JSON;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import util.models.EmployeeRecord;

import java.time.YearMonth;

import static util.Utils.textDateToDate;


public class EmployeeJsonAdapter {

    @FromJson
    EmployeeRecord employeeRecordFromJson(EmployeeRecordJson erJSON) {

        YearMonth date = textDateToDate(erJSON.cust_payPeriod);
        return new EmployeeRecord(
                Integer.parseInt(erJSON.externalName),
                erJSON.cust_costCenter,
                erJSON.cust_userId,
                erJSON.cust_amtpernum,
                erJSON.cust_wageType,
                erJSON.cust_companyCode,
                date,
                erJSON.cust_payrollArea,
                erJSON.cust_sourcetarget);
    }

    @ToJson
    EmployeeRecordJson employeeRecordToJson(EmployeeRecord employeeRecord) {
        return new EmployeeRecordJson(
                Integer.toString(employeeRecord.runNumber), employeeRecord.costCenter, employeeRecord.userId,
                Double.toString(employeeRecord.amtpernum), employeeRecord.wageType, employeeRecord.companyCode,
                employeeRecord.payPeriod.toString(), employeeRecord.payrollArea, employeeRecord.sourceTarget.toString());
    }
}
