package it.polito.tdp.porto.model;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	
	PortoDAO dao = new PortoDAO();
	Graph <Author,DefaultEdge> grafo;
	AuthorIdMap authorIdMap;
	PaperIdMap paperIdMap;
	List<Author> autori ;
	List<Paper> articoli ;

	public List<Author> getAutori() {
		authorIdMap = new AuthorIdMap();
		autori =dao.getAutori(authorIdMap);
		Collections.sort(autori);
			
		// TODO Auto-generated method stub
		return autori;
	}
	public void creaGrafo() {
		paperIdMap = new PaperIdMap();
		articoli = dao.getArticoli(paperIdMap);
		
		grafo = new SimpleGraph<>(DefaultEdge.class);
		dao.getAllCollaborations(authorIdMap, paperIdMap);
		
		Graphs.addAllVertices(grafo, autori );
		addEdges();
		
		
	}
	private void addEdges() {
		
		for(Author a: grafo.vertexSet()) {
			for(Paper p: a.getListaArticoli()) {
				for(Author a1: p.getListaAutori()) {
					if(!a.equals(a1)) 
						grafo.addEdge(a, a1);
				}
			}
		}
		
		
		}
	public List<Author> getCoautori(Author autore) {
		// TODO Auto-generated method stub
		return Graphs.neighborListOf(grafo, autore);
	}
	public Path getPercorso(Author value, Author value2) {
		// TODO Auto-generated method stub
		DijkstraShortestPath<Author,DefaultEdge> p = new DijkstraShortestPath<Author,DefaultEdge>(grafo, value, value2);
		return (Path) p.getPath();
		
		
	}
}

