package util.models;

public class KPI {
    public int runNumber;
    public int total;
    public int matches;
    public double matchesPercentage;
    public int deviations;
    public int redundancies;
    public int missing;
    public int runMatchImprovement;
    public double runMatchImprovementPercentage;

    public KPI() {
    }

    public void calculatePercentages(KPI prevKPI) {
        matchesPercentage = (double)matches / ((double)total/100);
        if (prevKPI == null) {
            runMatchImprovement = 0;
            runMatchImprovementPercentage = 0;
        } else {
            runMatchImprovement = matches - prevKPI.matches;
            runMatchImprovementPercentage = matchesPercentage-prevKPI.matchesPercentage;
        }
    }

    public String toString() {
        return String.format("Total records: %s\nMatches: %s\nMatches in percentages: %s\nDeviations: %s\nRedundancies: %s\nMissing: %s" +
                "\nImprovement from last run: %s\nImprovement from last run in percentages: %s", total, matches, matchesPercentage, deviations, redundancies, missing, runMatchImprovement, runMatchImprovementPercentage);
    }
}
