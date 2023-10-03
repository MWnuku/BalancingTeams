package Controllers;

import Exceptions.TeamSizeException;
import Models.Member;
import Models.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TeamController{
	public static ArrayList<Team> createTeams(ArrayList<Member> members, int numberOfTeams) throws TeamSizeException{
		if(members.size() % numberOfTeams != 0)
			throw new TeamSizeException("Cannot create teams with equal number of members with " + numberOfTeams + " teams and " + members.size() + " members");
		else{
			Collections.sort(members);
			int teamsSize = members.size() / numberOfTeams;
			ArrayList<Team> teams = new ArrayList<>();

			int left = 0;
			int right = members.size() - 1;
			while(left < right){
				ArrayList<Member> newTeam = new ArrayList<>(Arrays.asList(members.get(left), members.get(right)));
				teams.add(new Team(newTeam));
				left++;
				right--;
			}
			return teams;
		}
	}

	public static String printTeams(ArrayList<Team> teams){
		String output = "";
		for(int i = 0; i < teams.size(); i++){
			output += ("Team no " + (i + 1) + " has " + teams.get(i).getMembers().size() + " players (" + teams.get(i).printMembers() + "). Average rate: " + teams.get(i).getAvgRate()) + "\n";
		}
		return output;
	}
}
