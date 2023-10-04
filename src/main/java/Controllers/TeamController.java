package Controllers;

import Exceptions.TeamSizeException;
import Models.Member;
import Models.Team;

import java.util.ArrayList;
import java.util.Collections;

public class TeamController{

	//todo
	public static float rateStandardDeviation(ArrayList<Team> teams){
		float sumOfAvgRates = 0.0F;
		for(Team team : teams){
			sumOfAvgRates += team.getAvgRate();
		}
		return sumOfAvgRates / teams.size();
	}

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
					//int addition = ((j + i) >= numberOfTeams) ? ((k + i) % numberOfTeams) : (k + i);
					int index = (i * (numberOfTeams)) + (j + i) % numberOfTeams;
					teams.get(j).addMember(members.get(index));
				}
			}

			boolean swapped = true;
			while(swapped){
				System.out.println("au");
				swapped = false;
				Team minRateTeam = findMin(teams);
				Team maxRateTeam = findMax(teams);
				float ratesDiff = maxRateTeam.getSumOfRates() - minRateTeam.getSumOfRates();
				float bestSwapDiff = Float.MAX_VALUE;
				Member bestMinMember = null;
				Member bestMaxMember = null;
				Member maxMemberToSwap = null;
				Member minMemberToSwap = null;
				for(int i = 0; i < teamSize; i++){
					System.out.println(maxRateTeam.getMembers().get(i).getRate() + " " + minRateTeam.getMembers().get(i).getRate() + " " + ratesDiff);
					float temp = (maxRateTeam.getMembers().get(i).getRate() - minRateTeam.getMembers().get(i).getRate());
					if(temp >= 1 && temp < ratesDiff){
						System.out.println("au");
						float diff = ratesDiff - temp;
						if(diff < bestSwapDiff){
							maxMemberToSwap = maxRateTeam.getMembers().get(i);
							minMemberToSwap = minRateTeam.getMembers().get(i);
							bestSwapDiff = diff;
							bestMaxMember = maxMemberToSwap;
							bestMinMember = minMemberToSwap;
							swapped = true;
							System.out.println("auu");
						}
					}
				}
				if(swapped){
					minRateTeam.removeMember(bestMinMember);
					maxRateTeam.removeMember(bestMaxMember);
					minRateTeam.addMember(bestMaxMember);
					maxRateTeam.addMember(bestMinMember);
					System.out.println("swapping" + bestMaxMember + " " + bestMinMember);
				}
			}

			return teams;
		}
	}

	public static String printTeams(ArrayList<Team> teams){
		StringBuilder output = new StringBuilder();
		for(int i = 0; i < teams.size(); i++){
			output.append("Team no ").append(i + 1).append(" has ").append(teams.get(i).getMembers().size()).append(" players (").append(teams.get(i).printMembers()).append("). Average rate: ").append(teams.get(i).getAvgRate()).append("\n").append("Teams rate standard deviation: " + rateStandardDeviation(teams));
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
