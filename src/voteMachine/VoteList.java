package voteMachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class VoteList extends Observable{
	private Map<String, Integer> votes;
	
	public VoteList(){		
		this.votes = new HashMap<String, Integer>();
	}
	
	public int getVotes(String partyName){
		Integer votes;
		votes = this.votes.get(partyName);
		if (votes == null){
			return 0;
		} else {
			return votes;
		}
	}
	
	public void makeVote(String partyName){
		this.votes.put(partyName, getVotes(partyName) + 1);
		this.setChanged();
		this.notifyObservers("vote");
	}
	
	public Map<String, Integer> getVotes(){
		return this.votes;
	}
}
