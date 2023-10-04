package Controllers;

import Exceptions.TeamSizeException;
import Models.Member;
import Models.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

		ArrayList<Team> resultTeams = TeamController.createTeams(members, numberOfTeams);
		assertEquals("""
				Team no 1 has 2 players (Deborah, Scarlet). Average rate: 5.5
				Team no 2 has 2 players (Robbie, Johnny). Average rate: 6.5
				Team no 3 has 2 players (Jude, Juliet). Average rate: 6.0
				""", TeamController.printTeams(resultTeams));
	}

	@Test
	void sevenMembersFor3TeamShouldReturnTeamSizeException(){
		ArrayList<Member> members = new ArrayList<>();
		members.add(new Member("Johnny", 8));
		members.add(new Member("Robbie", 5));
		members.add(new Member("Juliet", 3));
		members.add(new Member("Scarlet", 5));
		members.add(new Member("Jude", 9));
		members.add(new Member("Deborah", 6));
		members.add(new Member("Monica", 7));

		int numberOfTeams = 3;
		assertThrows(TeamSizeException.class, () -> {
			ArrayList<Team> resultTeams = TeamController.createTeams(members, numberOfTeams);
		});
	}

	@Test
	void ninePerson3TeamsData(){
		ArrayList<Member> members = new ArrayList<>();
		members.add(new Member("Johnny", 8));
		members.add(new Member("Robbie", 5));
		members.add(new Member("Juliet", 3));
		members.add(new Member("Scarlet", 7));
		members.add(new Member("Jude", 9));
		members.add(new Member("Deborah", 6));
		members.add(new Member("Mike", 1));
		members.add(new Member("Monica", 4));
		members.add(new Member("Lucas", 2));

		int numberOfTeams = 3;

		ArrayList<Team> resultTeams = TeamController.createTeams(members, numberOfTeams);
		assertEquals("""
				Team no 1 has 3 players (Mike, Deborah, Johnny). Average rate: 5.0
				Team no 2 has 3 players (Lucas, Monica, Jude). Average rate: 5.0
				Team no 3 has 3 players (Juliet, Robbie, Scarlet). Average rate: 5.0
				""", TeamController.printTeams(resultTeams));
	}
}