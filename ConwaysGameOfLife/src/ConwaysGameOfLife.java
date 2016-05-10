
public class ConwaysGameOfLife {
	
	static int[][] current; //current game board
	static int[][] next;	//game board used for next round
	static int iterationCount; //what round we're in

	public static void main(String[] args) {
		
		//Create game boards
		//TODO: Lookup standard size?
		current = new int[5][5];
		next = new int[current.length][current[1].length];
		
		//Create starting board
		createSeed();
		
		//Print out starting board
		System.out.println("Iteration: Start");
		printCurrent();
		
		//Play the game of life
		play();

	}
	
	//Main iteration method
	//Iterates through x number of rounds
	//Checks each cell against game of life rules to determine alive or dead in next round using countNeighbors
	//Sets values for next round in "next" matrix
	//Copies "next" matrix into "current" matrix for next round
	//Prints board for next round
	//TODO: Look up how many rounds you play
	//TODO: Maybe way to eliminate matrix copy each iteration?
	public static void play()
	{
		for(iterationCount = 1; iterationCount <= 10; iterationCount++)
		{
			for(int i = 0; i < current.length; i++)
			{
				for(int j = 0; j < current[1].length; j++)
				{
					int n = countNeighbors(i,j);
					if(n < 2 || n > 3)
						next[i][j] = 0;
					else if(n == 2)
						next[i][j] = current[i][j];
					else
						next[i][j] = 1;
				}
			}
			for(int i = 0; i < next.length; i++)
				for(int j = 0; j < next[i].length; j++)
					current[i][j] = next[i][j];
			printCurrent();
		}
	}
	
	public static void printCurrent()
	{
		if(iterationCount != 0)
			System.out.println("Iteration: " + iterationCount);
		for(int i = 0; i < current.length; i++)
		{
			for(int j = 0; j < current[i].length; j++)
			{
				System.out.print(current[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();		
				
	}
	
	//Create starting board
	//Takes a random to decide if cell starts as 0/1
	//TODO: Look up seed algorithms, random value to use, etc
	public static void createSeed()
	{
		for(int i = 0; i < current.length; i++)
		{
			for(int j = 0; j < current[i].length; j++)
			{
				double r = Math.random();
				if(r > 0.5)
					current[i][j] = 1;
			}
			
		}
	}
	
	//Counts living cells in surround cell[i][j]
	//Since I use 0/1 for dead/living I just += to the count variable
	public static int countNeighbors(int i, int j)
	{
		int count = 0;
		for(int a = i-1; a <= i + 1; a++)
		{
			for(int b = j-1; b <= j + 1; b++)
			{
				try{
					if(a != i || b != j)
					{
						count += current[a][b];
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					continue;
				}
					
			}
		}
		return count;
	}
	

}
