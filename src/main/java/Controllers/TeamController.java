package Controllers;

import Exceptions.TeamSizeException;
import Models.Member;
import Models.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamController{

	public static float rateStandardDeviation(List<Team> teams){
		float sumOfAvgRates = 0.0F;
		for(Team team : teams){
			sumOfAvgRates += team.getAvgRate();
		}
		float mean = sumOfAvgRates / teams.size();
		float sumOfSquaredDeviations = 0.0F;
		for(Team team : teams){
			float deviation = team.getAvgRate() - mean;
			sumOfSquaredDeviations += deviation * deviation;
		}
		float variance = sumOfSquaredDeviations / teams.size();
		float standardDeviation = (float) Math.sqrt(variance);
		return (float) (Math.round(standardDeviation * 100.0) / 100.0);
	}

	public static List<Team> createTeams(List<Member> members, int numberOfTeams) throws TeamSizeException{
		if(members.size() % numberOfTeams != 0)
			throw new TeamSizeException("Cannot create teams with equal number of members with " + numberOfTeams + " teams and " + members.size() + " members");
		else{
			Collections.sort(members);
			List<Team> teams = new ArrayList<>();

			for(int i = 0; i < numberOfTeams; i++){
				teams.add(new Team());
			}

			int teamSize = members.size() / numberOfTeams;
			for(int i = 0; i < teamSize; i++){
				for(int j = 0; j < numberOfTeams; j++){
					int index = (i * (numberOfTeams)) + (j + i) % numberOfTeams;
					teams.get(j).addMember(members.get(index));
				}
			}

			return optimizeTeams(teams, teamSize);
		}
	}

	private static List<Team> optimizeTeams(List<Team> teams, int teamSize){
		boolean swapped = true;
		while(swapped){
			swapped = false;
			Team minRateTeam = findMin(teams);
			Team maxRateTeam = findMax(teams);
			float ratesDiff = maxRateTeam.getSumOfRates() - minRateTeam.getSumOfRates();
			float bestSwapDiff = Float.MAX_VALUE;
			Member bestMinMember = null;
			Member bestMaxMember = null;
			Member maxMemberToSwap;
			Member minMemberToSwap;
			for(int i = 0; i < teamSize; i++){
				float temp = (maxRateTeam.getMembers().get(i).getRate() - minRateTeam.getMembers().get(i).getRate());
				if(temp >= 1 && temp < ratesDiff){
					float diff = ratesDiff - temp;
					if(diff < bestSwapDiff){
						maxMemberToSwap = maxRateTeam.getMembers().get(i);
						minMemberToSwap = minRateTeam.getMembers().get(i);
						bestSwapDiff = diff;
						bestMaxMember = maxMemberToSwap;
						bestMinMember = minMemberToSwap;
						swapped = true;
					}
				}
			}
			if(swapped){
				minRateTeam.removeMember(bestMinMember);
				maxRateTeam.removeMember(bestMaxMember);
				minRateTeam.addMember(bestMaxMember);
				maxRateTeam.addMember(bestMinMember);
			}
		}
		return teams;
	}

	private static Team findMax(List<Team> teams){
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

	private static Team findMin(List<Team> teams){
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

	public static String printTeamsWithDeviation(List<Team> teams){
		StringBuilder output = new StringBuilder();
		for(int i = 0; i < teams.size(); i++){
			output.append("Team no ").append(i + 1).append(" has ").append(teams.get(i).getMembers().size()).append(" players (").append(teams.get(i).printMembers()).append("). Average rate: ").append(teams.get(i).getAvgRate()).append("\n");
		}
		output.append("Teams rate standard deviation: ").append(rateStandardDeviation(teams));
		return output.toString();
	}

	public static String printTeamsWithRateAndDeviation(ArrayList<Team> teams){
		StringBuilder output = new StringBuilder();
		for(int i = 0; i < teams.size(); i++){
			output.append("Team no ").append(i + 1).append(" has ").append(teams.get(i).getMembers().size()).append(" players (").append(teams.get(i).printMembersRate()).append("). Average rate: ").append(teams.get(i).getAvgRate()).append("\n");
		}
		output.append("Teams rate standard deviation: ").append(rateStandardDeviation(teams));
		return output.toString();
	}
}
