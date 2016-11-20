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


/**
 *
 * @author VTProjectTeam
 */
public class SW_application extends Object {
@SuppressWarnings( value = "unused" )
  
    public static void main(String[] args) { new SW_application().run(); }
    static final String  inputFileName = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\SW_project\\SW_application\\src\\sw_application\\schema_org.rdf" ;
    public void run() {
    AllStatements.getAllStatements(inputFileName) ; 
    AllStatements.getAllClasses(inputFileName);
    String query = "\n" +
        "prefix rdfs: <"+RDFS.getURI()+">\n" +
        "\n" +
        "select distinct ?class where {\n" +
        "  { ?class a rdfs:Class } union\n" +
        "  { ?class rdfs:subClassOf|^rdfs:subClassOf [] }\n" +
        "}";
    SPARQL.runSPARQLRequest(query, inputFileName);
    String ClassName = "http://schema.org/MusicVideoObject" ;
    //Search.getClassByName(ClassName, inputFileName);
    }
    
    
}
