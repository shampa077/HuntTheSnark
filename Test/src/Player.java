import java.util.*;
public class Player {
	public String name;
	public int score=0;
	public Player(String n)
	{
		name=n;
	}
	public void printInfo()
	{
		System.out.print("Player name"+name+" score:"+score);
	}
}
