package util.models;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.time.Month;
import java.time.YearMonth;


public class EmployeeJsonAdapter {

    @FromJson
    EmployeeRecord eventFromJson(EmployeeRecordJson erJSON) {

        YearMonth date = textDateToDate(erJSON.cust_payPeriod);
        return new EmployeeRecord(
                erJSON.externalName, erJSON.cust_costCenter, erJSON.cust_userId,
                erJSON.cust_amtpernum, erJSON.cust_wageType, erJSON.cust_companyCode,
                date, erJSON.cust_payrollArea,erJSON.cust_sourcetarget);
    }

    @ToJson
    EmployeeRecordJson eventToJson(EmployeeRecord employeeRecord) {
        return new EmployeeRecordJson(
                employeeRecord.runNumber, employeeRecord.costCenter, employeeRecord.userId,
                employeeRecord.amtpernum, employeeRecord.wageType, employeeRecord.companyCode,
                employeeRecord.payPeriod.toString(), employeeRecord.payrollArea, employeeRecord.sourcetarget);
    }

    public static YearMonth textDateToDate(String textDate) {
        String[] yearAndMonth = textDate.split(" ");
        int year = Integer.parseInt(yearAndMonth[1]);
        Month month;
        switch ((yearAndMonth[0]).toLowerCase()) {
            case "januar":
            case "january":
                month = Month.JANUARY;
                break;
            case "february":
                month = Month.FEBRUARY;
                break;
            case "march":
                month = Month.MARCH;
                break;
            case "april":
                month = Month.APRIL;
                break;
            case "may":
                month = Month.MAY;
                break;
            case "june":
                month = Month.JUNE;
                break;
            case "july":
                month = Month.JULY;
                break;
            case "august":
                month = Month.AUGUST;
                break;
            case "september":
                month = Month.SEPTEMBER;
                break;
            case "october":
                month = Month.OCTOBER;
                break;
            case "november":
                month = Month.NOVEMBER;
                break;
            case "december":
                month = Month.DECEMBER;
                break;
            default:
                return null;
        }
        return YearMonth.of(year, month);
    }
}
