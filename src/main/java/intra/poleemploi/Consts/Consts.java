package intra.poleemploi.Consts;

public final class Consts {
    public static final String ONCLICK ="onclick";

    public static final String APPLICATIONID = "applicationId=";
    public static final String PUBID = "pubId=";
    public static final String PUBLICATIONHREF = "location.href=";
//URLs
    // http://pr051-gfpe-3upxjf0.sip91.pole-emploi.intra:22391/know/login.jsp    //prod
    // http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/index.jsp    //recette
    public static final String PRODUCTION = "http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know";
    public static final String RECETTE = "http://pr051-gfpe-3upxjf0.sip91.pole-emploi.intra:22391/know";
    public static final String RECETTENV = "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative";
    public static final String URLBASE = RECETTENV;
    public static final String URLBASELOGINKM = URLBASE + "/LoginCheck";
    public static final String URLBASEFORCONTENTSDETAILS = URLBASE + "/admin/statistic/?applicationId=";
    public static final String URLBASEFORSTATISTICSDETAILS = URLBASE + "/view/statistics/publicationStatistics?pubId=";

    public static final String HEADER_ACCEPT_VALUE = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9";
    public static final String HEADER_ACCEPT_ENCODING_VALUE = "gzip, deflate, br";
    public static final String HEADER_ACCEPT_LANGUAGE_VALUE = "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7";
    public static final String HEADER_CACHE_CONTROL_VALUE = "max-age=0";
    public static final String HEADER_CONNECTION_VALUE ="keep-alive";
    public static final String HEADER_USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36";
    public static final String HEADER_HOST_VALUE = "pr051-vipa-a0rxpr.sii24.pole-emploi.intra";

//    public static final String URLBASELOGINKM = "http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/servlet/LoginCheck";
//    public static final String URLBASEFORCONTENTSDETAILS = "http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/admin/statistic/?applicationId=";
//    public static final String URLBASEFORSTATISTICSDETAILS ="http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/view/statistics/publicationStatistics?pubId=";

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
