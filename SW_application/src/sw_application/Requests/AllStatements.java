/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw_application.Requests;

import java.io.InputStream;
import org.apache.jena.rdf.model.Model ;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import static org.apache.jena.sparql.engine.http.Service.base;
//import static org.apache.jena.sparql.vocabulary.ResultSetGraphVocab.ResultSet;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDFS;

/**
 *
 * @author VTProjectTeam
 */
public class AllStatements {

    public static void getAllStatements(String fileName) {

        Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(fileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + fileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, "");
        // list the statements in the Model
        StmtIterator iter = model.listStatements();

        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();  // get next statement
            Resource subject = stmt.getSubject();     // get the subject
            Property predicate = stmt.getPredicate();   // get the predicate
            RDFNode object = stmt.getObject();      // get the object

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }

            System.out.println(" .");

        }
    }
    public static void getAllClasses (String fileName) {
        Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(fileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + fileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, "");
    String query = "\n" +
        "prefix rdfs: <"+RDFS.getURI()+">\n" +
        "\n" +
        "select distinct ?class where {\n" +
        "  { ?class a rdfs:Class } union\n" +
        "  { ?class rdfs:subClassOf|^rdfs:subClassOf [] }\n" +
        "}";
    ResultSet results;
        results = QueryExecutionFactory.create( query,model).execSelect();
    System.out.println( query );
    ResultSetFormatter.out( results );
    
    }
}


