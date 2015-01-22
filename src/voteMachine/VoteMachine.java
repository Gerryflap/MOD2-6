package voteMachine;

import java.util.List;
import java.util.Map;

import voteMachine.gui.VoteGUIView;

public class VoteMachine {
	private PartyList partyList;
	private VoteList votesList;
	private VoteView voteView;
	
	public VoteMachine(){
		this.partyList = new PartyList(); 
		this.votesList = new VoteList(); 
		//this.voteView = new VoteTUIView(this);
		this.voteView = new VoteGUIView(this);
		partyList.addObserver(this.voteView);
		votesList.addObserver(this.voteView);
		
	}
	
	public static void main(String[] args){
	
		VoteMachine voteMachine = new VoteMachine();
		
		voteMachine.start();
	}
	
	public void start(){
		this.voteView.start();
	}
	
	public void addParty(String partyName){
		this.partyList.addParty(partyName);
	}
	
	public void vote(String partyName){
		this.votesList.makeVote(partyName);
	}
	
	public List<String> getParties(){
		return this.partyList.getParties();
	}
	
	public Map<String, Integer> getVotes(){
		return this.votesList.getVotes();
	}

}
