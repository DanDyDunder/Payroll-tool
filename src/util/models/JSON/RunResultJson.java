package util.models.JSON;

import java.time.YearMonth;

public class RunResultJson {
    public final String externalName;
    public final String cust_userIdSource;
    public final String cust_userIdTarget;
    public final String cust_payPeriod;
    public final String cust_amtpernumSource;
    public final String cust_amtpernumTarget;
    public final String cust_deviation;
    public final String cust_wageTypeSource;
    public final String cust_wageTypeTarget;
    public final boolean cust_status;


    public RunResultJson(String externalName, String cust_userIdSource, String cust_userIdTarget,
                         String cust_payPeriod, String cust_amtpernumSource,
                         String cust_amtpernumTarget, String cust_deviation, String cust_wageTypeSource,
                         String cust_wageTypeTarget, boolean cust_status) {
        this.externalName = externalName;
        this.cust_userIdSource = cust_userIdSource;
        this.cust_userIdTarget = cust_userIdTarget;
        this.cust_payPeriod = cust_payPeriod;
        this.cust_amtpernumSource = cust_amtpernumSource;
        this.cust_amtpernumTarget = cust_amtpernumTarget;
        this.cust_deviation = cust_deviation;
        this.cust_wageTypeSource = cust_wageTypeSource;
        this.cust_wageTypeTarget = cust_wageTypeTarget;
        this.cust_status = cust_status;
    }

    @Override
    public String toString() {
        return String.format("Run number: %s\nId source: %s\nId target: %s\nDate: %s\nLegacy amount: %s\nTarget amount: %s\nDeviation: %s\nLegacy wagetype: %s\nTarget wagetype: %s\nStatus: %s",
                externalName,
                cust_userIdSource,
                cust_userIdTarget,
                cust_payPeriod,
                cust_amtpernumSource,
                cust_amtpernumTarget,
                cust_deviation,
                cust_wageTypeSource,
                cust_wageTypeTarget,
                cust_status);
    }
}
