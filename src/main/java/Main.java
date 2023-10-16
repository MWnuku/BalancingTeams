import Controllers.TeamController;
import Exceptions.TeamSizeException;
import Models.Member;
import Models.Team;

import java.util.ArrayList;
import java.util.List;

public class Main{
	public static void main(String[] args){
		ArrayList<Member> members = new ArrayList<>();
		members.add(new Member("Johnny", 8));
		members.add(new Member("Robbie", 5));
		members.add(new Member("Juliet", 3));
		members.add(new Member("Scarlet", 5));
		members.add(new Member("Jude", 9));
		members.add(new Member("Deborah", 6));

		int numberOfTeams = 3;

		try{
			List<Team> resultTeams = TeamController.createTeams(members, numberOfTeams);
			System.out.println(TeamController.printTeamsWithDeviation(resultTeams));
		} catch(TeamSizeException e){
			e.printStackTrace();
		}
	}
}