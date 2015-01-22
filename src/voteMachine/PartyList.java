package voteMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PartyList extends Observable{
	private ArrayList<String> partyList ;
	
	public PartyList(){
		this.partyList = new ArrayList<String>();
	}
	
	public void addParty(String partyName){
		this.partyList.add(partyName);
		this.setChanged();
		this.notifyObservers("party");
	}

	public List<String> getParties(){
		return this.partyList;
	}
	
	
	

}
