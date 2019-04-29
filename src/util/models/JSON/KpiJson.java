package util.models.JSON;

public class KpiJson {
    public final MetaData __metadata;
    public final String cust_total;
    public final String cust_matches;
    public final String cust_matchesPer;
    public final String cust_deviations;
    public final String cust_redundancies;
    public final String cust_missing;
    public final String cust_runMatchImp;
    public final String cust_runMatchImpPer;

    public KpiJson(String runNumber, String cust_total, String cust_matches, String cust_matchesPer, String cust_deviations, String cust_redundancies, String cust_missing, String cust_runMatchImp, String cust_runMatchImpPer) {
        __metadata = new MetaData(runNumber);
        this.cust_total = cust_total;
        this.cust_matches = cust_matches;
        this.cust_matchesPer = cust_matchesPer;
        this.cust_deviations = cust_deviations;
        this.cust_redundancies = cust_redundancies;
        this.cust_missing = cust_missing;
        this.cust_runMatchImp = cust_runMatchImp;
        this.cust_runMatchImpPer = cust_runMatchImpPer;
    }
}
