package util.models;

import java.util.Objects;

public class ComparisonResult {
    public final String userId;
    public final double amtpernumSource;
    public final double amtpernumTarget;
    public final double deviation;
    public final String wageTypeSource;
    public final String wageTypeTarget;
    public boolean status;

    public ComparisonResult(String userId,
                            double amtpernumSource,
                            double amtpernumTarget,
                            double deviation,
                            String wageTypeSource,
                            String wageTypeTarget,
                            boolean status) {
        this.userId = userId;
        this.amtpernumSource = amtpernumSource;
        this.amtpernumTarget = amtpernumTarget;
        this.deviation = deviation;
        this.wageTypeSource = wageTypeSource;
        this.wageTypeTarget = wageTypeTarget;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Id: %s\nLegacy amount: %s\nTarget amount: %s\nDeviation: %s\nLegacy wagetype: %s\nTarget wagetype: %s\nStatus: %s",
                userId,
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
        return Double.compare(that.amtpernumSource, amtpernumSource) == 0 &&
                Double.compare(that.amtpernumTarget, amtpernumTarget) == 0 &&
                Double.compare(that.deviation, deviation) == 0 &&
                status == that.status &&
                userId.equals(that.userId) &&
                wageTypeSource.equals(that.wageTypeSource) &&
                wageTypeTarget.equals(that.wageTypeTarget);
    }
}
