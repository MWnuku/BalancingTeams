package Models;

import java.util.*;
import java.util.function.Consumer;

public class Team implements Iterable<Member>, Comparable<Team>{
	private List<Member> members = new ArrayList<>();
	private float avgRate;
	private int sumOfRates = 0;

	public Team(List<Member> members){
		this.members = members;
		for(Member member : members)
			sumOfRates += member.getRate();
		calculateAverageRate();
	}

	public Team(){
	}

	public void addMember(Member member){
		members.add(member);
		sumOfRates += member.getRate();
		calculateAverageRate();
	}

	public void addMember(String name, int rate){
		members.add(new Member(name, rate));
		sumOfRates += rate;
		calculateAverageRate();
	}

	private void calculateAverageRate(){
		avgRate = (float) sumOfRates / members.size();
	}

	public void removeMember(Member member){
		members.remove(member);
		sumOfRates -= member.getRate();
		calculateAverageRate();
	}

	public List<Member> getMembers(){
		return members;
	}

	public void setMembers(List<Member> members){
		this.members = members;
	}

	public String printMembers(){
		StringBuilder s = new StringBuilder();
		for(Member member : this.getMembers()){
			s.append(member.getName()).append(", ");
		}
		s = new StringBuilder(s.substring(0, s.length() - 2));
		return s.toString();
	}

	public String printMembersRate(){
		StringBuilder s = new StringBuilder();
		for(Member member : this.getMembers()){
			s.append(member.getRate()).append(", ");
		}
		s = new StringBuilder(s.substring(0, s.length() - 2));
		return s.toString();
	}

	public int getSumOfRates(){
		return sumOfRates;
	}

	public float getAvgRate(){
		return avgRate;
	}

	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Team team = (Team) o;
		return avgRate == team.avgRate && Objects.equals(members, team.members);
	}

	@Override
	public int hashCode(){
		return Objects.hash(members, avgRate);
	}

	@Override
	public String toString(){
		return "Team{" + "members=" + members + ", avgRate=" + avgRate + '}';
	}

	@Override
	public Iterator<Member> iterator(){
		return members.iterator();
	}

	@Override
	public void forEach(Consumer<? super Member> action){
		for(Member member : this){
			action.accept(member);
		}
	}

	@Override
	public Spliterator<Member> spliterator(){
		return Iterable.super.spliterator();
	}

	@Override
	public int compareTo(Team otherTeam){
		return Float.compare(this.avgRate, otherTeam.avgRate);
	}
}
