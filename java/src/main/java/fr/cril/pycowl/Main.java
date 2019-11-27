package fr.cril.pycowl;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import py4j.GatewayServer;

public class Main {
    private final OWLOntologyManager manager;

    public Main() {
        manager = OWLManager.createOWLOntologyManager();
    }

    public Ontology loadOntology(String path) throws OWLOntologyCreationException {
        return new Ontology(manager, path);
    }

    public OWLOntology loadOWLOntology(String path) throws OWLOntologyCreationException {
        final File f = new File(path);
        final OWLOntology onto = manager.loadOntologyFromOntologyDocument(f);
        onto.subClassAxiomsForSubClass(null);
        return onto;
    }

    public static void main(String[] args) {
        final GatewayServer gatewayServer = new GatewayServer(new Main());
        gatewayServer.start();
        System.out.println("Gateway Server Started");
    }
}