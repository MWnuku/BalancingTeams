package Controllers;

import Exceptions.TeamSizeException;
import Models.Member;
import Models.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TeamControllerTest{
	@Test
	void sampleDataTest(){
		ArrayList<Member> members = new ArrayList<>();
		members.add(new Member("Johnny", 8));
		members.add(new Member("Robbie", 5));
		members.add(new Member("Juliet", 3));
		members.add(new Member("Scarlet", 5));
		members.add(new Member("Jude", 9));
		members.add(new Member("Deborah", 6));

		int numberOfTeams = 3;

		try{
			ArrayList<Team> resultTeams = TeamController.createTeams(members, numberOfTeams);
			System.out.println(TeamController.printTeams(resultTeams));
		} catch(TeamSizeException e){
			e.printStackTrace();
		}
	}

	@Test
	void wrongNumberOfTeamsToMembers(){
		ArrayList<Member> members = new ArrayList<>();
		members.add(new Member("Johnny", 8));
		members.add(new Member("Robbie", 5));
		members.add(new Member("Juliet", 3));
		members.add(new Member("Scarlet", 5));
		members.add(new Member("Jude", 9));
		members.add(new Member("Deborah", 6));
		members.add(new Member("Monica", 7));

		int numberOfTeams = 3;

		try{
			ArrayList<Team> resultTeams = TeamController.createTeams(members, numberOfTeams);
			System.out.println(TeamController.printTeams(resultTeams));
		} catch(TeamSizeException e){
			e.printStackTrace();
		}
	}
}