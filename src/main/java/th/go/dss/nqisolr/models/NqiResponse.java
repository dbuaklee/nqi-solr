package th.go.dss.nqisolr.models;

import java.util.List;

import org.apache.solr.common.SolrDocument; 

public class NqiResponse {
    private long numFound;
    private long start;
    private boolean numFoundExact;

    private List<SolrDocument> docs;

    public NqiResponse(long numFound,long start, boolean numFoundExact,List<SolrDocument> docs) {
        this.numFound = numFound;
        this.start = start;
        this.numFoundExact = numFoundExact;
        this.docs = docs;
    }

    public long getNumFound() {
        return this.numFound;
    }

    public long getStart() {
        return this.start;
    }

    public boolean getNumFoundExact() {
        return this.numFoundExact;
    }

    public List<SolrDocument> getDocs() {
        return this.docs;
    }
}
