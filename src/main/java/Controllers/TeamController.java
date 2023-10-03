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
		StringBuilder output = new StringBuilder();
		for(int i = 0; i < teams.size(); i++){
			output.append("Team no ").append(i + 1).append(" has ").append(teams.get(i).getMembers().size()).append(" players (").append(teams.get(i).printMembers()).append("). Average rate: ").append(teams.get(i).getAvgRate()).append("\n");
		}
		return output.toString();
	}
}
