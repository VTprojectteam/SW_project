/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw_application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import java.io.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.ontology.*;
import org.apache.jena.shared.JenaException;
import org.apache.jena.vocabulary.RDFS;
import sw_application.Requests.AllStatements ; 
import sw_application.Requests.AddStatement ;
import sw_application.Requests.RemoveStatement ;
import sw_application.Requests.SPARQL ;
import sw_application.Requests.Search ;
import sw_application.Requests.test_add ;


/**
 *
 * @author VTProjectTeam
 */
public class SW_application extends Object {
@SuppressWarnings( value = "unused" )
  
    public static void main(String[] args) { new SW_application().run(); }
    static final String  inputFileName = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\SW_project\\SW_application\\src\\sw_application\\schema_org_reloaded.rdf" ;
    static final String  outputFileName = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\SW_project\\SW_application\\src\\sw_application\\schema_out.rdf" ;

    public void run() {
    System.out.print("Getting all statements from "+ inputFileName + " ontology\n");
    AllStatements.getAllStatements(inputFileName) ; 
    System.out.print ("\n\n");
    System.out.print ("Getting all classes from "+ inputFileName + "ontology, using SPARQL request \n");
    //AllStatements.getAllClasses(inputFileName);
    String query = "\n" +
        "prefix rdfs: <"+RDFS.getURI()+">\n" +
        "\n" +
        "select distinct ?class where {\n" +
        "  { ?class a rdfs:Class } union\n" +
        "  { ?class rdfs:subClassOf|^rdfs:subClassOf [] }\n" +
        "}";
    SPARQL.runSPARQLRequest(query, inputFileName);
    System.out.print("Searching a statement in "+ inputFileName + "ontology\n");
    Model model = ModelFactory.createDefaultModel();
    
    String schemaUri = "http://schema.org/";//paymentStatus
    Resource search = model.createResource(schemaUri+"paymentStatus") ;
    System.out.print("Search a " + search.toString() + " statement in ontology\n");
    Search.getStatementByName(search, inputFileName);
    String ClassName = "Good relations class" ;
    System.out.print("Searching a "+ ClassName + " class in "+ inputFileName + "ontology\n");
    Search.getClassByName(ClassName, inputFileName);
    
    
    System.out.print("Creating rdf ontology and adding some statements into it\n");
    AddStatement.addStatement(outputFileName);
    System.out.print("Getting all statements from "+ outputFileName + " ontology\n");
    AllStatements.getAllStatements(outputFileName) ; 
    System.out.print("Remove some of statement out of ontology\n");
    RemoveStatement.removeStatement(outputFileName);
    System.out.print("Getting all statements from "+ outputFileName + " ontology\n");
    AllStatements.getAllStatements(outputFileName) ;
    
    }
    
    
}
