package Controllers;

import Exceptions.TeamSizeException;
import Models.Member;
import Models.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static Controllers.TeamController.createTeams;
import static Controllers.TeamController.printTeamsWithDeviation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamControllerTest{
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
		assertThrows(TeamSizeException.class, () -> createTeams(members, numberOfTeams));
	}

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

		ArrayList<Team> resultTeams = createTeams(members, numberOfTeams);
		assertEquals("""
				Team no 1 has 2 players (Johnny, Robbie). Average rate: 6.5
				Team no 2 has 2 players (Jude, Juliet). Average rate: 6.0
				Team no 3 has 2 players (Scarlet, Deborah). Average rate: 5.5
				Teams rate standard deviation: 0.41""", printTeamsWithDeviation(resultTeams));
	}

	@Test
	void threeMembers3Teams(){
		ArrayList<Member> members = new ArrayList<>();

		members.add(new Member("Mike", 1));
		members.add(new Member("Monica", 2));
		members.add(new Member("Peter", 2));

		int numberOfTeams = 3;
		ArrayList<Team> teams = createTeams(members, numberOfTeams);

		assertEquals("""
				Team no 1 has 1 players (Mike). Average rate: 1.0
				Team no 2 has 1 players (Monica). Average rate: 2.0
				Team no 3 has 1 players (Peter). Average rate: 2.0
				Teams rate standard deviation: 0.47""", printTeamsWithDeviation(teams));
	}

	@Test
	void two2two3(){
		ArrayList<Member> members = new ArrayList<>();

		members.add(new Member("Mike", 2));
		members.add(new Member("Monica", 2));
		members.add(new Member("Peter", 3));
		members.add(new Member("Wallace", 3));

		int numberOfTeams = 2;
		ArrayList<Team> teams = createTeams(members, numberOfTeams);

		assertEquals("""
				Team no 1 has 2 players (Mike, Wallace). Average rate: 2.5
				Team no 2 has 2 players (Monica, Peter). Average rate: 2.5
				Teams rate standard deviation: 0.0""", printTeamsWithDeviation(teams));
	}

	@Test
	void fourMembers2Teams(){
		ArrayList<Member> members = new ArrayList<>();

		members.add(new Member("Mike", 1));
		members.add(new Member("Monica", 2));
		members.add(new Member("Peter", 2));
		members.add(new Member("Wallace", 9));

		int numberOfTeams = 2;
		ArrayList<Team> teams = createTeams(members, numberOfTeams);

		assertEquals("""
				Team no 1 has 2 players (Mike, Wallace). Average rate: 5.0
				Team no 2 has 2 players (Monica, Peter). Average rate: 2.0
				Teams rate standard deviation: 1.5""", printTeamsWithDeviation(teams));
	}

	@Test
	void twoEdgeRatesAnd6SameOnes2Teams(){
		ArrayList<Member> members = new ArrayList<>();

		members.add(new Member("Mike", 1));
		members.add(new Member("Monica", 4));
		members.add(new Member("Peter", 4));
		members.add(new Member("Adam", 4));
		members.add(new Member("Adrian", 4));
		members.add(new Member("Bert", 4));
		members.add(new Member("Basha", 4));
		members.add(new Member("Masha", 9));

		int numberOfTeams = 2;
		ArrayList<Team> teams = createTeams(members, numberOfTeams);

		assertEquals("""
				Team no 1 has 4 players (Mike, Adam, Adrian, Masha). Average rate: 4.5
				Team no 2 has 4 players (Monica, Peter, Bert, Basha). Average rate: 4.0
				Teams rate standard deviation: 0.25""", printTeamsWithDeviation(teams));
	}

	@Test
	void nineMembers3TeamsData(){
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

		ArrayList<Team> resultTeams = createTeams(members, numberOfTeams);
		assertEquals("""
				Team no 1 has 3 players (Mike, Robbie, Jude). Average rate: 5.0
				Team no 2 has 3 players (Lucas, Deborah, Scarlet). Average rate: 5.0
				Team no 3 has 3 players (Juliet, Monica, Johnny). Average rate: 5.0
				Teams rate standard deviation: 0.0""", printTeamsWithDeviation(resultTeams));
	}

	@Test
	void fourteenMember7Teams(){
		ArrayList<Member> members = new ArrayList<>();

		members.add(new Member("Mike", 1));
		members.add(new Member("Monica", 2));
		members.add(new Member("Peter", 2));
		members.add(new Member("Adam", 4));
		members.add(new Member("Adrian", 4));
		members.add(new Member("Bert", 5));
		members.add(new Member("Basha", 5));
		members.add(new Member("Masha", 6));
		members.add(new Member("Martin", 7));
		members.add(new Member("Anna", 7));
		members.add(new Member("Arthur", 8));
		members.add(new Member("Harry", 8));
		members.add(new Member("Wallace", 9));
		members.add(new Member("Simon", 9));

		int numberOfTeams = 7;
		ArrayList<Team> teams = createTeams(members, numberOfTeams);

		assertEquals("""
				Team no 1 has 2 players (Martin, Bert). Average rate: 6.0
				Team no 2 has 2 players (Anna, Adrian). Average rate: 5.5
				Team no 3 has 2 players (Peter, Arthur). Average rate: 5.0
				Team no 4 has 2 players (Adam, Harry). Average rate: 6.0
				Team no 5 has 2 players (Wallace, Monica). Average rate: 5.5
				Team no 6 has 2 players (Simon, Mike). Average rate: 5.0
				Team no 7 has 2 players (Basha, Masha). Average rate: 5.5
				Teams rate standard deviation: 0.38""", printTeamsWithDeviation(teams));
	}

	@Test
	void fourteenMembers2Teams(){
		ArrayList<Member> members = new ArrayList<>();

		members.add(new Member("Mike", 1));
		members.add(new Member("Monica", 2));
		members.add(new Member("Peter", 2));
		members.add(new Member("Adam", 4));
		members.add(new Member("Adrian", 4));
		members.add(new Member("Bert", 5));
		members.add(new Member("Basha", 5));
		members.add(new Member("Masha", 6));
		members.add(new Member("Martin", 7));
		members.add(new Member("Anna", 7));
		members.add(new Member("Arthur", 8));
		members.add(new Member("Harry", 8));
		members.add(new Member("Wallace", 9));
		members.add(new Member("Simon", 9));

		int numberOfTeams = 2;
		ArrayList<Team> teams = createTeams(members, numberOfTeams);

		assertEquals("""
				Team no 1 has 7 players (Mike, Adam, Adrian, Masha, Martin, Harry, Wallace). Average rate: 5.571429
				Team no 2 has 7 players (Monica, Peter, Bert, Basha, Anna, Arthur, Simon). Average rate: 5.428571
				Teams rate standard deviation: 0.07""", printTeamsWithDeviation(teams));
	}

	@Test
	void fifteenMember3Teams(){
		ArrayList<Member> members = new ArrayList<>();

		members.add(new Member("Mike", 1));
		members.add(new Member("Monica", 2));
		members.add(new Member("Peter", 2));
		members.add(new Member("John", 2));
		members.add(new Member("Adam", 4));
		members.add(new Member("Adrian", 4));
		members.add(new Member("Bert", 5));
		members.add(new Member("Basha", 5));
		members.add(new Member("Masha", 6));
		members.add(new Member("Martin", 7));
		members.add(new Member("Anna", 7));
		members.add(new Member("Arthur", 8));
		members.add(new Member("Harry", 8));
		members.add(new Member("Wallace", 9));
		members.add(new Member("Simon", 9));

		int numberOfTeams = 3;
		ArrayList<Team> teams = createTeams(members, numberOfTeams);

		assertEquals("""
				Team no 1 has 5 players (Mike, Adam, Martin, Wallace, Basha). Average rate: 5.2
				Team no 2 has 5 players (Monica, Adrian, Bert, Anna, Simon). Average rate: 5.4
				Team no 3 has 5 players (Peter, John, Arthur, Harry, Masha). Average rate: 5.2
				Teams rate standard deviation: 0.09""", printTeamsWithDeviation(teams));
	}

	@Test
	void fifteenMembers5Teams(){
		ArrayList<Member> members = new ArrayList<>();

		members.add(new Member("Mike", 1));
		members.add(new Member("Monica", 2));
		members.add(new Member("Peter", 2));
		members.add(new Member("John", 2));
		members.add(new Member("Adam", 4));
		members.add(new Member("Adrian", 4));
		members.add(new Member("Bert", 5));
		members.add(new Member("Basha", 5));
		members.add(new Member("Masha", 6));
		members.add(new Member("Martin", 7));
		members.add(new Member("Anna", 7));
		members.add(new Member("Arthur", 8));
		members.add(new Member("Harry", 8));
		members.add(new Member("Wallace", 9));
		members.add(new Member("Simon", 9));

		int numberOfTeams = 5;
		ArrayList<Team> teams = createTeams(members, numberOfTeams);

		assertEquals("""
				Team no 1 has 3 players (Bert, Harry, Peter). Average rate: 5.0
				Team no 2 has 3 players (Monica, Basha, Wallace). Average rate: 5.3333335
				Team no 3 has 3 players (Masha, Simon, Mike). Average rate: 5.3333335
				Team no 4 has 3 players (John, Martin, Anna). Average rate: 5.3333335
				Team no 5 has 3 players (Adam, Adrian, Arthur). Average rate: 5.3333335
				Teams rate standard deviation: 0.13""", printTeamsWithDeviation(teams));
	}

	@Test
	void sixteenMembers4TeamsData(){
		ArrayList<Member> members = new ArrayList<>();

		members.add(new Member("Mike", 1));
		members.add(new Member("Monica", 2));
		members.add(new Member("Peter", 2));
		members.add(new Member("John", 2));
		members.add(new Member("Adam", 4));
		members.add(new Member("Adrian", 4));
		members.add(new Member("Bert", 5));
		members.add(new Member("Basha", 5));
		members.add(new Member("Masha", 6));
		members.add(new Member("Martin", 7));
		members.add(new Member("Anna", 7));
		members.add(new Member("Arthur", 8));
		members.add(new Member("Harry", 8));
		members.add(new Member("Stephen", 8));
		members.add(new Member("Wallace", 9));
		members.add(new Member("Simon", 9));

		int numberOfTeams = 4;
		ArrayList<Team> teams = createTeams(members, numberOfTeams);

		assertEquals("""
				Team no 1 has 4 players (Adrian, Anna, Simon, Monica). Average rate: 5.5
				Team no 2 has 4 players (Bert, Arthur, Harry, Mike). Average rate: 5.5
				Team no 3 has 4 players (Peter, Basha, Masha, Stephen). Average rate: 5.25
				Team no 4 has 4 players (John, Adam, Martin, Wallace). Average rate: 5.5
				Teams rate standard deviation: 0.11""", printTeamsWithDeviation(teams));
	}

	@Test
	void sixteenMembers2Teams(){
		ArrayList<Member> members = new ArrayList<>();

		members.add(new Member("Mike", 1));
		members.add(new Member("Monica", 2));
		members.add(new Member("Peter", 2));
		members.add(new Member("John", 2));
		members.add(new Member("Adam", 4));
		members.add(new Member("Adrian", 4));
		members.add(new Member("Bert", 5));
		members.add(new Member("Basha", 5));
		members.add(new Member("Masha", 6));
		members.add(new Member("Martin", 7));
		members.add(new Member("Anna", 7));
		members.add(new Member("Arthur", 8));
		members.add(new Member("Harry", 8));
		members.add(new Member("Stephen", 8));
		members.add(new Member("Wallace", 9));
		members.add(new Member("Simon", 9));

		int numberOfTeams = 2;
		ArrayList<Team> teams = createTeams(members, numberOfTeams);

		assertEquals("""
				Team no 1 has 8 players (Mike, John, Adam, Basha, Masha, Arthur, Harry, Simon). Average rate: 5.375
				Team no 2 has 8 players (Monica, Peter, Adrian, Bert, Martin, Anna, Stephen, Wallace). Average rate: 5.5
				Teams rate standard deviation: 0.06""", printTeamsWithDeviation(teams));
	}

	@Test
	void sixteenMembers8Teams(){
		ArrayList<Member> members = new ArrayList<>();

		members.add(new Member("Mike", 1));
		members.add(new Member("Monica", 2));
		members.add(new Member("Peter", 2));
		members.add(new Member("John", 2));
		members.add(new Member("Adam", 4));
		members.add(new Member("Adrian", 4));
		members.add(new Member("Bert", 5));
		members.add(new Member("Basha", 5));
		members.add(new Member("Masha", 6));
		members.add(new Member("Martin", 7));
		members.add(new Member("Anna", 7));
		members.add(new Member("Arthur", 8));
		members.add(new Member("Harry", 8));
		members.add(new Member("Stephen", 8));
		members.add(new Member("Wallace", 9));
		members.add(new Member("Simon", 9));

		int numberOfTeams = 8;
		ArrayList<Team> teams = createTeams(members, numberOfTeams);

		assertEquals("""
				Team no 1 has 2 players (Martin, Bert). Average rate: 6.0
				Team no 2 has 2 players (Anna, Adrian). Average rate: 5.5
				Team no 3 has 2 players (Peter, Arthur). Average rate: 5.0
				Team no 4 has 2 players (John, Harry). Average rate: 5.0
				Team no 5 has 2 players (Adam, Stephen). Average rate: 6.0
				Team no 6 has 2 players (Wallace, Monica). Average rate: 5.5
				Team no 7 has 2 players (Simon, Mike). Average rate: 5.0
				Team no 8 has 2 players (Basha, Masha). Average rate: 5.5
				Teams rate standard deviation: 0.39""", printTeamsWithDeviation(teams));
	}
}