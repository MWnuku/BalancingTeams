package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Team implements Iterable<Member>, Comparable<Team>{
	private ArrayList<Member> members = new ArrayList<>();
	private float avgRate;
	private int sumOfRates = 0;

	public Team(){
	}

	public Team(ArrayList<Member> members){
		this.members = members;
		for(Member member : members)
			sumOfRates += member.getRate();
		calculateAverageRate();
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

	public ArrayList<Member> getMembers(){
		return members;
	}

	public void setMembers(ArrayList<Member> members){
		this.members = members;
	}

	public String printMembers(){
		String s = "";
		for(Member member : this.getMembers()){
			s += member.getName() + ", ";
		}
		s = s.substring(0, s.length() - 2);
		return s;
	}

	public float getAvgRate(){
		return avgRate;
	}

	public void setAvgRate(long avgRate){
		this.avgRate = avgRate;
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
	public Spliterator spliterator(){
		return Iterable.super.spliterator();
	}

	@Override
	public int compareTo(Team otherTeam){
		// Compare teams based on their average rates
		return Float.compare(this.avgRate, otherTeam.avgRate);
	}
}
