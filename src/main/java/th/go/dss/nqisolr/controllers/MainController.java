package th.go.dss.nqisolr.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.go.dss.nqisolr.models.NqiResponse;

@CrossOrigin(origins = {"http://localhost:4200", "http://nqi.go.th", "http://onestop.mhesi.go.th", "https://nqi.go.th", "https://www.nqi.go.th", "http://www.nqi.go.th"})
@RestController
public class MainController {
    @Value("${SOLR.url}")
    private String solrUrl;


    @GetMapping("/search")
    public NqiResponse search(@RequestParam String q,
        @RequestParam Integer start, @RequestParam Integer rows) throws SolrServerException, IOException {
    
        
        HttpSolrClient solr = new HttpSolrClient.Builder(solrUrl).build();
        
        SolrQuery query = new SolrQuery();
        query.setQuery("standard:*" + q +"* || testing:*" + q + "*");
        query.setStart(start);
        query.setRows(rows);

        final QueryResponse resp = solr.query(query);
        final SolrDocumentList documents = resp.getResults();

        List<SolrDocument> docs = new ArrayList<>();
        
        for(SolrDocument doc : documents) {
            docs.add(doc);
        }

        NqiResponse nqiResponse = new NqiResponse(documents.getNumFound(), documents.getStart(), 
            documents.getNumFoundExact(), docs);

            return nqiResponse;
        
    }
}
