package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.Map;

public class PaperIdMap {
	
	private Map<Integer, Paper> map;
	public PaperIdMap()
	{
		map = new HashMap<>();
	}
	public Paper get (int paperId) {
		return map.get(paperId);
	}
	
	public Paper get(Paper paper) {
		Paper old = map.get(paper.getEprintid());
		if(old == null) {
			map.put(paper.getEprintid(), paper);
			return paper;
		}
		return old;
	}
	
	public void put (Paper paper, int paperId) {
		map.put(paperId, paper);
	}
	
}
