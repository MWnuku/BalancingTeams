package Models;

import java.util.Objects;

public class Member implements Comparable<Member>{
	private String name;
	private int rate;

	public Member(String name, int rate){
		this.name = name;
		this.rate = rate;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getRate(){
		return rate;
	}

	public void setRate(int rate){
		this.rate = rate;
	}

	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Member that = (Member) o;
		return rate == that.rate && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode(){
		return Objects.hash(name, rate);
	}

	@Override
	public String toString(){
		return "Individual{" + "name='" + name + '\'' + ", rate=" + rate + '}';
	}

	@Override
	public int compareTo(Member anotherMember){
		return Integer.compare(this.rate, anotherMember.rate);
	}
}
