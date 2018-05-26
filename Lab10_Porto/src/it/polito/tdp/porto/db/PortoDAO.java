package it.polito.tdp.porto.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.Paper;
import it.polito.tdp.porto.model.PaperIdMap;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public List<Author> getAutori(AuthorIdMap authorIdMap) {

		final String sql = "SELECT * FROM author ";
		List<Author> autori = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
		

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				autori.add(authorIdMap.get(autore));
				
			}
			conn.close();
			return autori;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public List<Paper> getArticoli(PaperIdMap paperIdMap) {

		final String sql = "SELECT * FROM paper ";
		List<Paper> articoli = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
				rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				articoli.add(paperIdMap.get(paper));
				
			}

			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public void getAllCollaborations(AuthorIdMap authorIdMap, PaperIdMap paperIdMap) {
		final String sql = "SELECT * FROM creator ";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
	
			while (res.next()) {
				paperIdMap.get(res.getInt("eprintid")).setListaAutori(authorIdMap.get(res.getInt("authorid")));
				authorIdMap.get(res.getInt("authorid")).setListaArticoli(paperIdMap.get(res.getInt("eprintid")));;	
				
			
				
				
			}	
			conn.close();
			return ;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}