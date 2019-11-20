package intra.poleemploi.Consts;

public final class Consts {
    public static final String ONCLICK ="onclick";

    public static final String APPLICATIONID = "applicationId=";
    public static final String PUBID = "pubId=";
    public static final String PUBLICATIONHREF = "location.href=";
//URLs
    // http://pr051-gfpe-3upxjf0.sip91.pole-emploi.intra:22391/know/login.jsp    //prod
    // http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/index.jsp    //recette
    public static final String URLBASELOGINKM = "http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/servlet/LoginCheck";
    public static final String URLBASEFORCONTENTSDETAILS = "http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/admin/statistic/?applicationId=";
    public static final String URLBASEFORSTATISTICSDETAILS ="http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/view/statistics/publicationStatistics?pubId=";

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
