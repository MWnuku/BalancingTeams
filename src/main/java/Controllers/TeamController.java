package Controllers;

import Exceptions.TeamSizeException;
import Models.Member;
import Models.Team;

import java.util.ArrayList;
import java.util.Collections;

public class TeamController{
	public static ArrayList<Team> createTeams(ArrayList<Member> members, int numberOfTeams) throws TeamSizeException{
		if(members.size() % numberOfTeams != 0)
			throw new TeamSizeException("Cannot create teams with equal number of members with " + numberOfTeams + " teams and " + members.size() + " members");
		else{
			Collections.sort(members);
			ArrayList<Team> teams = new ArrayList<>();

			for(int i = 0; i < numberOfTeams; i++){
				teams.add(new Team());
			}

			int teamSize = members.size() / numberOfTeams;
			for(int i = 0; i < teamSize; i++){
				for(int j = 0; j < numberOfTeams; j++){
					int index = (i * teamSize) + (j + i);
					teams.get(j).addMember(members.get(index));
				}
			}

			boolean swapped = true;
			while(swapped){
				swapped = false;
				Team minRateTeam = findMin(teams);
				Team maxRateTeam = findMax(teams);
				float ratesDiff = maxRateTeam.getSumOfRates() - minRateTeam.getSumOfRates();
				for(int i = 0; i < teamSize; i++){
					float temp = maxRateTeam.getMembers().get(i).getRate() - minRateTeam.getMembers().get(i).getRate();
					if(temp >= 1 && temp < ratesDiff){
						Member maxMemberToSwap = maxRateTeam.getMembers().get(i);
						Member minMemberToSwap = minRateTeam.getMembers().get(i);
						minRateTeam.removeMember(minMemberToSwap);
						maxRateTeam.removeMember(maxMemberToSwap);
						minRateTeam.addMember(maxMemberToSwap);
						maxRateTeam.addMember(minMemberToSwap);
						swapped = true;
					}
				}
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

	public static Team findMax(ArrayList<Team> teams){
		float maxRate = Float.MIN_VALUE;
		Team maxTeam = new Team();
		for(Team team : teams){
			if(team.getAvgRate() > maxRate){
				maxTeam = team;
				maxRate = team.getAvgRate();
			}
		}
		return maxTeam;
	}

	public static Team findMin(ArrayList<Team> teams){
		float minRate = Float.MAX_VALUE;
		Team minTeam = new Team();
		for(Team team : teams){
			if(team.getAvgRate() < minRate){
				minTeam = team;
				minRate = team.getAvgRate();
			}
		}
		return minTeam;
	}

}
