/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw_application.Requests;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.util.FileManager;

/**
 *
 * @author VTProjectTeam
 */
public class RemoveStatement {
public static void removeStatement (String fileName) {
Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(fileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + fileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, "");
        // list the statements in the Model

        String familyUri = "http://family/";
        String relationshipUri = "http://purl.org/vocab/relationship/";

// Create an empty Model
// Create a Resource for each family member, identified by their URI
        
        Resource adam = model.createResource(familyUri + "adam");
        Property parentOf = model.createProperty(relationshipUri, "parentOf");

        Statement statement = model.createStatement(adam, parentOf, "fran");
        Statement statement1 = model.createStatement(adam,parentOf,"edward");

// but remember to add the created statement to the model
        model.remove(statement);
        model.remove(statement1);
        

        FileWriter out = null;
        try {
            out = new FileWriter(fileName);
        } catch (IOException ex) {
            Logger.getLogger(test_add.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            model.write(out, "RDF/XML");
        } finally {
            try {
                out.close();
            } catch (IOException closeException) {
                // ignore
            }
        }

    }
};     

