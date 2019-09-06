import java.util.*;

/**
 * Write a description of class NQueens here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NQueens
{
    // instance variables - replace the example below with your own
    private int n;
    private int board [][]; 
    private int placed = 0;

    /**
     * Constructor for objects of class NQueens
     */
    public NQueens(int x) 
    {
        // initialise instance variables
        n = x;
        board = new int [n][n];
    }
    
    public void debug()
    {
        System.out.println(n);
        /*
        board[0][1] = 1;
        board[1][3] = 1;
        board[2][0] = 1;
        System.out.println(Arrays.deepToString(board));
        System.out.println(openSpace(3,2));
        */
        placeNextQueen(0);
    }
    
    
    public boolean openSpace(int x, int y)
    {
        //System.out.println("Checking x, y: " + x + " "+ y);
        // check if occupied
        if (board[x][y] == 1)
        {
            return false;
        }
        
        //check if attacked
        for (int i = 0; i<n; i++)
        {
            if (board[i][y] == 1) // check vertically
            {
                return false;
            }
            if (board[x][i] == 1) // check horizontally
            {
                return false;
            }
        }
        for (int i = 0; i<n; i++)
        {
            if (x+i>=n || y+i>=n)
            {
                break;
            }
            if (board[x+i][y+i] == 1) // check diagonally \ down
            {
                return false;
            }
        }
        for (int i = 0; i<n; i++)
        {
            if (x-i<0 || y-i<0)
            {
                break;
            }
            if (board[x-i][y-i] == 1) // check diagonally \ up
            {
                return false;
            }
        }
        for (int i = 0; i<n; i++)
        {
            if (x-i<0 || y+i>=n)
            {
                break;
            }
            if (board[x-i][y+i] == 1) // check diagonally /up
            {
                return false;
            }
        }
        for (int i = 0; i<n; i++)
        {
            if (x+i>=n || y-i<0)
            {
                break;
            }
            if (board[x+i][y-i] == 1) // check diagonally /down
            {
                return false;
            }
        }
        return true;
    }
    
    public int placeQueen(int x, int start) 
    //loops through entire row to try to place a queen. 
    //if successful, returns the spot where queen was placed. If no queen was placed, returns n. 
    {
        for (int i=start; i<n; i++)
        {
            //System.out.println("i = "+i+"space: "+openSpace(x,i));
            if (openSpace(x,i))
            {
                //System.out.println("Q was placed at "+x+" "+i);
                board[x][i] = 1;
                return i;
            }
        }
        return n;
    }
    
    public void removeQueen(int x, int y)
    {
        //System.out.println("Q was removed at "+x+" "+y);
        board[x][y] = 0;
    }
    
    public boolean placeNextQueen(int x)
    {
        //System.out.println("PNQ > "+x);
        Scanner scan = new Scanner(System.in);
        int bookmark=0;
        //System.out.println("Starting Board State:");
        //System.out.println(printBoard());
        
        //System.out.println("Hit enter to continue.");
        //scan.nextLine();

        if (x>=n){return true;} //row out of bounds. exit function. no queen to place.
        bookmark = placeQueen(x,bookmark);
        /*
        System.out.println("Bookmark = "+bookmark);
        
        System.out.println("Hit enter to continue.");
        scan.nextLine();
        */
        
        if (bookmark==n){
            //System.out.println("Queen cannot be placed. Backtracking.");
            return false;
        } //queen cannot be placed. exit function and backtrack. 
        // backtracking portion below. 
        boolean next;
        next = placeNextQueen(x+1);
        while (!next)
        {
            //System.out.println("Backtracking at PNQ > "+x);
            
            removeQueen(x,bookmark);
            bookmark = placeQueen(x,bookmark+1);
            /*
            System.out.println("Bookmark = "+bookmark);
        
            System.out.println("Hit enter to continue.");
            scan.nextLine();
            */
            
            if (bookmark==n){
                //System.out.println("Queen cannot be placed. Backtracking.");
                return false;
            } //queen cannot be placed. exit function and backtrack. 
            next = placeNextQueen(x+1);
        }
        //queens successfully placed 
        placed ++;
        return true;
    }
    
    public boolean placeNQueens() throws Exception
    {
        placed = 0;
        if (n<1)
        {
            throw new Exception("Invalid board size.");
        }
        placeNextQueen(0);
        System.out.println(printBoard());
        if (placed == n){return true;}
        else {return false;}
    }
    
    public String printBoard()
    {
        String result = "";
        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
            {
                if (board[i][j] == 0)
                {
                    result += "[_]";
                }
                if (board[i][j] == 1)
                {
                    result += "[Q]";
                }
            }
            result +="\n";
        }
        return result;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static void main(String [ ] args)
    {
        NQueens Q = new NQueens(4);
        Q.debug();
    }
}
