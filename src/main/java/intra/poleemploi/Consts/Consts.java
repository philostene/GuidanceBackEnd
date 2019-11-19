package intra.poleemploi.Consts;

public final class Consts {
    public static final String URLBASEFORSTATISTICSDETAILS ="http=/know/view/statistics/publicationStatistics?pubId=";
    public static final String ONCLICK ="onclick";
    public static final String URLBASEFORCONTENTSDETAILS = "http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/admin/statistic/?applicationId=";
    public static final String APPLICATIONID = "applicationId=";
    public static final String PUBID = "pubId=";
    public static final String PUBLICATIONHREF = "location.href=";

// PRIVATE //
    /**
     The caller references the constants using <tt>Consts.EMPTY_STRING</tt>,
     and so on. Thus, the caller should be prevented from constructing objects of 
     this class, by declaring this private constructor. 
     */
    private Consts(){
        //this prevents even the native class from 
        //calling this constructor as well :
        throw new AssertionError();
    }
}
