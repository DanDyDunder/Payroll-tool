package util.models;

import java.time.YearMonth;
import java.util.Objects;

public class ComparisonResult {
    public final int runNumber;
    public final String userIdSource;
    public final String userIdTarget;
    public final YearMonth payPeriod;
    public final double amtpernumSource;
    public final double amtpernumTarget;
    public final double deviation;
    public final String wageTypeSource;
    public final String wageTypeTarget;
    public boolean status;

    public ComparisonResult(int runNumber, String userIdSource, String userIdTarget, YearMonth payPeriod,
                            double amtpernumSource, double amtpernumTarget, double deviation,
                            String wageTypeSource, String wageTypeTarget, boolean status) {
        this.runNumber = runNumber;
        this.userIdSource = userIdSource;
        this.userIdTarget = userIdTarget;
        this.payPeriod = payPeriod;
        this.amtpernumSource = amtpernumSource;
        this.amtpernumTarget = amtpernumTarget;
        this.deviation = deviation;
        this.wageTypeSource = wageTypeSource;
        this.wageTypeTarget = wageTypeTarget;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Run number: %s\nId source: %s\nId target: %s\nDate: %s\nLegacy amount: %s\nTarget amount: %s\nDeviation: %s\nLegacy wagetype: %s\nTarget wagetype: %s\nStatus: %s",
                runNumber,
                userIdSource,
                userIdTarget,
                payPeriod,
                amtpernumSource,
                amtpernumTarget,
                deviation,
                wageTypeSource,
                wageTypeTarget,
                status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComparisonResult)) return false;
        ComparisonResult that = (ComparisonResult) o;
        return runNumber == that.runNumber &&
                Double.compare(that.amtpernumSource, amtpernumSource) == 0 &&
                Double.compare(that.amtpernumTarget, amtpernumTarget) == 0 &&
                Double.compare(that.deviation, deviation) == 0 &&
                status == that.status &&
                userIdSource.equals(that.userIdSource) &&
                userIdTarget.equals(that.userIdTarget) &&
                payPeriod.equals(that.payPeriod) &&
                wageTypeSource.equals(that.wageTypeSource) &&
                wageTypeTarget.equals(that.wageTypeTarget);
    }

}
