package it.polito.tdp.porto.db;

import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.PaperIdMap;

public class TestPortoDAO {
	
	public static void main(String args[]) {
		AuthorIdMap mappa = new AuthorIdMap();
		PaperIdMap mappaP = new PaperIdMap();
		PortoDAO pd = new PortoDAO();
		pd.getAutori(mappa);
		pd.getArticoli(mappaP);
		//System.out.println(pd.getArticolo(2293546));
		//System.out.println(pd.getArticolo(1941144));

	}

}
