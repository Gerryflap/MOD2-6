package voteMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class VoteTUIView implements VoteView, Observer{
	private VoteMachine voteMachine;
	public VoteTUIView(VoteMachine voteMachine){
		this.voteMachine = voteMachine;
	}
	
	public void start(){
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()){
			Scanner lineScanner = new Scanner(scanner.nextLine());
			ArrayList<String> lineData = new ArrayList<String>();
			
			while (lineScanner.hasNext()){
				lineData.add(lineScanner.next());
			}
			
			if (lineData.size() > 0){
				if(lineData.get(0).equals("VOTES")){
					
					showVotes(this.voteMachine.getVotes());
					
				} else if(lineData.get(0).equals("PARTIES")){
					
					showParties(this.voteMachine.getParties());
					
				} else if(lineData.get(0).equals("EXIT")){
					
					System.exit(0);
					
				} else if(lineData.get(0).equals("HELP")){
					
					System.out.println("Commands: VOTES, PARTIES, EXIT, HELP, VOTE [partyName], ADD PARTY [partyName]");
					
				} else if (lineData.get(0).equals("VOTE")){
					if (stringInList(getPartyName(lineData, 1), this.voteMachine.getParties())){
						this.voteMachine.vote(getPartyName(lineData, 1));
					} else {
						showError("Please try again with a valid party");
					}
					
				} else if (lineData.size() > 2 && lineData.get(0).equals("ADD") && lineData.get(1).equals("PARTY")){
					
					this.voteMachine.addParty(getPartyName(lineData, 2));

					
				} else {
					showError("Please enter a command");
				}
			}
			lineScanner.close();
		}
		scanner.close();
	}
	
	public void showVotes(Map<String, Integer> votes){
		for (String key: votes.keySet()){
			System.out.println(String.format("Party: %s, votes: %s", key, votes.get(key)));
		}
	}
	
	public void showParties(List<String> parties){
		for (String party: parties){
			System.out.println(String.format("Party: %s", party));
		}
	}
	
	public void showError(String error){
		System.err.println(error);
	}

	
	public boolean stringInList(String string, List<String> list){
		for(String element: list){
			if (element.equals(string)){
				return true;
			}			
		}
		return false;
	}
	
	public String getPartyName(List<String> lineData, int startIndex){
		String partyName = "";
		for(int i = startIndex; i < lineData.size(); i++){
			if (i != startIndex){
				partyName += " ";
			}
			partyName += lineData.get(i);
		}
		return partyName;
	}
	
	public void update(Observable object, Object argument) {
		if(argument instanceof String){
			if(argument.equals("party")){
				System.out.println(((PartyList) object).getParties());
			}
			else if(argument.equals("vote")){
				System.out.println(((VoteList) object).getVotes());
			}
		}

	}
}
