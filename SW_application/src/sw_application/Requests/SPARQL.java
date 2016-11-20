/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw_application.Requests;

import java.io.InputStream;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDFS;

/**
 *
 * @author VTProjectTeam
 */
public class SPARQL {

    public static void runSPARQLRequest(String Request, String fileName) {
        Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(fileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + fileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, "");
        String query = Request;
        ResultSet results;
        results = QueryExecutionFactory.create(query, model).execSelect();
        System.out.println(query);
        ResultSetFormatter.out(results);

    }
;
}
