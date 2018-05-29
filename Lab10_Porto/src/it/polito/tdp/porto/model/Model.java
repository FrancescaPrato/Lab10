package it.polito.tdp.porto.model;

import java.nio.file.Path;
import java.util.ArrayList;
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
	Graph <Author,Paper> grafo2;
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
		grafo2 = new SimpleGraph<>(Paper.class);
		dao.getAllCollaborations(authorIdMap, paperIdMap);
		
		Graphs.addAllVertices(grafo, autori );
		Graphs.addAllVertices(grafo2, autori );
		
		addEdges();
		addEdges2();
		
		
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
private void addEdges2() {
		
		for(Author a: grafo.vertexSet()) {
			for(Paper p: a.getListaArticoli()) {
				for(Author a1: p.getListaAutori()) {
					if(!a.equals(a1)) 
						grafo2.addEdge(a, a1, p);
					
				}
			}
		}
		}
	public List<Author> getCoautori(Author autore) {
		// TODO Auto-generated method stub
		return Graphs.neighborListOf(grafo, autore);
	}
	public List<Paper> getPercorso3(Author value, Author value2) {
		// TODO Auto-generated method stub
		DijkstraShortestPath<Author,DefaultEdge> p = new DijkstraShortestPath<Author,DefaultEdge>(grafo, value, value2);
		List<Paper> percorso= new ArrayList<Paper>();
		for(DefaultEdge k : p.getPathEdgeList()){
			Author a =grafo.getEdgeSource(k);
			Author a1 = grafo.getEdgeTarget(k);
			for (Paper p1 :a.getListaArticoli())
				for(Paper p2 : a1.getListaArticoli()) {
					if(p1.equals(p2))
					{
						percorso.add(p1);
						break;
					}
				}
			
		}
		
		return percorso;
		
		
	}
	public List<Paper> getPercorso1(Author value, Author value2) {
		// TODO Auto-generated method stub
		DijkstraShortestPath<Author,DefaultEdge> p = new DijkstraShortestPath<Author,DefaultEdge>(grafo, value, value2);
		List<Paper> percorso= new ArrayList<Paper>();
		for(DefaultEdge k : p.getPathEdgeList()){
			Author a =grafo.getEdgeSource(k);
			Author a1 = grafo.getEdgeTarget(k);
			Paper articolo = dao.getArticolo(a,a1);
			percorso.add(articolo);
			}
		
		return percorso;
		
		
	}
	public List<Paper> getPercorso2(Author value, Author value2) {
		// TODO Auto-generated method stub
		DijkstraShortestPath<Author,Paper> p = new DijkstraShortestPath<Author,Paper>(grafo2, value, value2);
						
		return p.getPathEdgeList();
		
		
	}
}

