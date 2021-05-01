package be.helha.ttmc.ui.gui.util;

import java.util.List;



public class IteratorMusic {
	private List<String> listEtu;
	private int index;
	
	public IteratorMusic(List<String> le) {
		super();
		this.listEtu=le;
		index=0;
	}
	
	public int listeSize() {
		return listEtu.size();
	}
	
	public String item() {
		if(index==listEtu.size()) {
			reset();
		}
		
		return listEtu.get(index);
	}
	
	public void next() {
		index++;
	}
	
	public void preview() {
		index--;
	}
	
	public void reset() {
		index=0;
	}
	
	public int getIndex() {
		return index;
	}
}
