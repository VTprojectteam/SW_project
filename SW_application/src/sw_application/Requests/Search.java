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
import org.apache.jena.rdf.model.RDFNode ;
import org.apache.jena.rdf.model.Property ; 
import org.apache.jena.rdf.model.Resource ; 
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDFS;
import static org.apache.jena.vocabulary.RDFS.Resource;

/**
 *
 * @author VTProjectTeam
 */
public class Search {

    public static void getStatementByName(Resource StatementName, String fileName) {
        Resource name = StatementName;
        Property pr = null;
        RDFNode node = null;
        SimpleSelector nameSelector = new SimpleSelector(name, pr, node);
        Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(fileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + fileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, "");
        StmtIterator iter = model.listStatements(nameSelector);
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

    ;
public static void getClassByName(String ClassName, String fileName) {
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
        "select ?uri ?label \n where {\n" +
        "?uri rdfs:label ?label ."  + " \n" +
        "filter (?label =  "+ "\"" + ClassName + "\"" + ")\n" +
        "}";
    System.out.println( query );
    ResultSet results;
        results = QueryExecutionFactory.create( query,model).execSelect();
    
    ResultSetFormatter.out( results );
    
    }
;
}
