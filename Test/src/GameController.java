import java.util.*;

class Turn{
	ArrayList<Integer> buttonpressed=new ArrayList<Integer>();
	ArrayList<Integer> point=new ArrayList<Integer>();
	ArrayList<GridCell> type=new ArrayList<GridCell>();
}
public class GameController {
	public Player playerone;
	public GridCell grid[][];
	public int grids;
	public Turn savegame= new Turn();
	
    public GameController(Player p, int gridsize)
    {
    	playerone=p;
    	grid= new GridCell[gridsize][gridsize];
    	grids=gridsize;
    	boolean nosnark=true;
    	for (int i=0;i<grids;i++)
    	{
    		for (int j=0;j<grids;j++)
        	{
        		Random r=new Random();
        	    int rand=r.nextInt(4);
        	    switch(rand)
        	    {
        	    case 0: 
        	    	grid[i][j]=new Type1();
        	        break;
        	    case	1:
        	    	grid[i][j]=new Type2();
        	    	nosnark=false;
        	    	break;
        	    case	2:
        	    	grid[i][j]=new Type3();
        	    	break;
        	    case	3:
        	    	grid[i][j]=new Type4();
        	    	break;
        	    }
        	}
    		
    	}
    	Random r=new Random();
		if (nosnark)
		{
			System.out.println("One snark enforced");
			grid[r.nextInt(grids)][r.nextInt(grids)]=new Type2();
		}
    	
    }
    void printGrid()
    {
    	for (int i=0;i<grids;i++)
    	{
    		for (int j=0;j<grids;j++)
        	{
    			System.out.print("r:"+i+" c:"+j+":");
    			grid[i][j].printInfo();
        	}
        }
    }
    void modifyGreed()
    {
    	boolean nosnark=true;
    	for (int i=0;i<grids;i++)
    	{
    		for (int j=0;j<grids;j++)
        	{
        		Random r=new Random();
        	    int rand=r.nextInt(4);
        	    switch(rand)
        	    {
        	    case 0: 
        	    	grid[i][j]=new Type1();
        	        break;
        	    case	1:
        	    	grid[i][j]=new Type2();
        	    	nosnark=false;
        	    	break;
        	    case	2:
        	    	grid[i][j]=new Type3();
        	    	break;
        	    case	3:
        	    	grid[i][j]=new Type4();
        	    	break;
        	    }
        	}
    		
    	}
    	Random r=new Random();
		if (nosnark)
		{	
			System.out.println("One snark enforced");
			grid[r.nextInt(grids)][r.nextInt(grids)]=new Type2();
		}
    	
    }
}
class GridCell
{
	protected String name;
	void printInfo()
	{
		System.out.println("I am basic gridcell");
	}
}

class Type1 extends GridCell
{
	protected int point;
	void printInfo()
	{
		System.out.println("I am empty cell");
	}
}

class Type2 extends GridCell
{
	protected char point;
	void printInfo()
	{
		System.out.println("I am point snark");
	}
}
class Type3 extends GridCell
{
	protected boolean point;
	void printInfo()
	{
		System.out.println("I am bonus hint");
	}
}
class Type4 extends GridCell
{
	protected boolean point;
	void printInfo()
	{
		System.out.println("I am a high danger");
	}
}
