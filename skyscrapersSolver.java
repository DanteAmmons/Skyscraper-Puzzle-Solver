/*
 * this is the same as the weekly skyscrapers solver, but now, you get to choose the size of your board!
 */
package skyscrapersSolver;
import java.util.*;
public class skyscrapersSolver {

/********************************************global variables***********************************************/
    static int s = 0;
    
/**************************************printing/debugging functions*****************************************/
   
    /*
     * this function only for debugging
     */
     public static void printArray(int[] input) {
        System.out.print("{");
        for(int i = 0; i < input.length; i++) {
            System.out.print(input[i]);
            if(i<input.length-1){
                 System.out.print(", ");
            }
        }
        System.out.print("},");
        System.out.print('\n');
    }
  
    /*
    * method that prints the board
    * INPUTS:
    * int[][] board - self-explanatory
    */
    public static void printBoard(int[][] board){
        //prints top edge
        System.out.print("    ");
        for(int a = 1; a<=s; a++){
            System.out.print(board[0][a]+" ");
        }
        System.out.print("\n");
        System.out.print("    ");
        for(int x = 0; x<s; x++) {
        	System.out.print("V ");
        }
        System.out.print("\n");
        //prints skyscraper values, left, and right edges
        for(int b = 1; b<=s; b++){
            System.out.print(board[b][0]+" > ");
            for(int c = 1; c<=s; c++){
                System.out.print(board[b][c]+" ");
            }
            System.out.println("< "+board[b][s+1]);
        }
        //prints bottom edge
        System.out.print("    ");
        for(int y = 0; y<s; y++) {
        	System.out.print("^ ");
        }
        System.out.print("\n");
        System.out.print("    ");
        for(int d = 1; d<=s; d++){
            System.out.print(board[s+1][d]+" ");
        }
        System.out.print("\n");
    }
    
    /*
     * method that prints the guesses matrix but with the normal board edges
     * INPUTS:
     * board - self-explanatory
     * guesses - self-explanatory
     */
    public static void printGuesses(int[][] board, int[][] guesses) {
    	//prints top edge
        System.out.print("      ");
        for(int a = 1; a<=s; a++){
            System.out.print(board[0][a]+"/"+guesses[0][a]+" ");
        }
        System.out.print("\n");
        System.out.print("       ");
        for(int x = 0; x<s; x++) {
        	System.out.print("V   ");
        }
        System.out.print("\n");
        //prints skyscraper values, left, and right edges
        for(int b = 1; b<=s; b++){
            System.out.print(board[b][0]+"/"+guesses[b][0]+" > ");
            for(int c = 1; c<=s; c++){
            	if(guesses[b][c]<100) {
            		System.out.print(" ");
            	}
                System.out.print(guesses[b][c]+" ");
                if(guesses[b][c]<10) {
                	System.out.print(" ");
                }
            }
            System.out.println("< "+board[b][s+1]);
        }
        //prints bottom edge
        System.out.print("       ");
        for(int y = 0; y<s; y++) {
        	System.out.print("^   ");
        }
        System.out.print("\n");
        System.out.print("       ");
        for(int d = 1; d<=s; d++){
            System.out.print(board[s+1][d]+"   ");
        }
        System.out.print("\n");
    }
    
/****************************************boolean utility functions******************************************/
     
     /*
      * method that checks to see if a given permutation for a row/column is compatible with the existing guesses
      * INPUTS: 
      * permCandidate - 1xs array of the permutation candidate being tested against the guesses, bitwise operator form
      * exGuesses - 1xs array of the guesses row/column, in bitwise operator form
      * OUTPUTS:
      * true if each each integer in permCandidate is a valid guess at its corresponding guesses index
      * false if even a single permCandidate int is not a valid guess at its corresponding guesses index
      */
      public static boolean validGuess(int[] permCandidate, int[]exGuesses) {
     	int z = 0;
     	boolean VG = true;
     	while(VG&&z<permCandidate.length) {
     		VG = (permCandidate[z] & exGuesses[z]) != 0;
     		z++;
     	}
     	return VG;
     }
      
      /*
       * method that checks if the input bitwise int is a singular guess, i.e. if it's a power of 2 between 2 and 2^s
       * INPUT:
       * a - input bitwise integer
       * OUTPUT: 
       * true if a = 2, 4,..., or 2^s
       * false otherwise
       */
      public static boolean isSingularGuess(int a) {
     	 boolean isg = false;
     	 int b = 0;
     	 int[] singularGuessValues = new int[s];
     	 for(int x = 0; x<s; x++) {
     		 singularGuessValues[x] = (int) Math.pow(2, x+1);
     	 }
     	 while(!isg&&b<singularGuessValues.length) {
     		 isg = a==singularGuessValues[b];
     		 b++;
     	 }
     	 return isg;
      }
      
      /*
       * method that checks if the edge input is a valid edge input or not
       * INPUTS:
       * edge - the edge string input from the console line
       * OUTPUTS:
       * true if the length of edge is equal to s and if every character in edge is either 0, 1,..., or s
       * false otherwise
       */
      public static boolean validEdgeInput(String edge) {
     	 boolean vei = true;
     	 if(edge.length()!=s) {
     		 return false;
     	 }else {
     		 int a = 0, b = 0;
     		 while(a<s&&vei) {
     			 b = edge.charAt(a)-'0';
     			 vei = b>=0&&b<=s;
     			 a++;
     		 }
     		 return vei;
     	 }
      }
      
      /*
       * method that checks if the board input is valid or not
       * INPUTS:
       * boardInput - the board string input from the console line
       * OUTPUTS:
       * true if the length of boardInput is equal to 3 and if boardInput = "end" or if every character in boardInput is either 1, 2, ..., or s
       * false otherwise
       */
      public static boolean validBoardInput(String boardInput) {
     	 boolean vbi = true;
     	 if(boardInput.length()!=3) {
     		 return false;
     	 }else {
     		 if(boardInput.equals("end")) {
     			 return true;
     		 }else {
     			 int a = 0, b = 0;
     			 while(a<3&&vbi) {
     				 b = boardInput.charAt(a)-'0';
     				 vbi = b>=1&&b<=s;
     				 a++;
     			 }
     			 return vbi;
     		 }
     	 }
      }
      
      /*
       * method that checks if the puzzle is complete
       * if the puzzle is complete, than every single array list in both rowPerms and colPerms should only have 1 value, and that's what this method checks
       * since there is no way for every array list in rowPerms to only have 1 value and for any array list in colPerms to have >1 value, this method onlly checks rowPerms
       * INPUTS:
       * row - rowPerms
       * OUTPUTS:
       * true if every single array list in rowPerms has a size of 1
       * false otherwise
       */
      public static boolean isComplete(ArrayList<ArrayList<int[]>> row) {
    	  boolean ic = true;
    	  int a = 0;
    	  while(a<s&&ic) {
    		  ic = row.get(a).size() == 1;
    		  a++;
    	  }
    	  return ic;
      }
      
/**********************************int/int[]-returning utility functions************************************/
      
      /*
       * method that takes an input integer and returns which power of 2 it is
       * NOTE: DO NOT USE THIS FUNCTION FOR ANYTHING OTHER THAN POWERS OF 2
       * INPUT:
       * a - input integer
       * OUTPUT:
       * the base 2 logarithm of a
       */
      public static int logBase2(int a) {
     	 return (int)(Math.log(a)/Math.log(2));
      }
      
      /*
       * method that takes an array and returns that same array in bitwise operator form
       * INPUTS:
       * a - integer array to be converted to bitwise form
       * OUTPUTS:
       * b - an array of the same size as a but in bitwise operator form.
       * i.e. if a = [4, 6, 2, 7, 1, 5], b = [16, 64, 4, 128, 2, 32] 
       */
      public static int[] convertToGuesses(int[] a) {
     	 int[] b = new int[a.length];
     	 for(int c = 0; c<a.length; c++) {
     		 b[c] = (int) Math.pow(2,a[c]);
     	 }
     	 return b;
      }
      
/***************************heapsort functions for generating all permutations******************************/

	private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }
	
    /*
    * method that puts all permutations of numbers 1-8 into an array list
    * name is a bit of a misnomer - i ganked it from a website that shows you how to print all permutations of an array
    * INPUTS:
    * n - i think this is always going to be s at the highest recursion level
    * elements - the array that's like [1,2,...,s]
    * destination - this is never not allperms
    * delimiter - not sure what this does
    */
   public static void printAllRecursive(
     int n, int[] elements, ArrayList<int[]> destination, char delimiter) {
       if(n == 1) {
           //printArray(elements);
       	int[] toBeAdded = new int[s];
       	for(int x = 0; x<s; x++){
       		toBeAdded[x] = elements[x];
       	}
       	//okay i think i have to copy over elements to a newly initialized array because otherwise destination just stores a pointer to elements and all the values are the same
       	//java is so fucking stupid man
           destination.add(toBeAdded);
       } else {
           for(int i = 0; i < n-1; i++) {
               printAllRecursive(n - 1, elements, destination, delimiter);
               if(n % 2 == 0) {
                   swap(elements, i, n-1);
               } else {
                   swap(elements, 0, n-1);
               }
           }
           printAllRecursive(n - 1, elements, destination, delimiter);
       }
   }

/**************************************parsing console input functions**************************************/

   /*
    * method that takes console input and changes edge values on the board
    * INPUTS: 
    * c - int that selects which edge is being changed (0 for top, 1 for bottom, 2 for left, 3 for right)
    * board - self explanatory
    * edge - s-char string input from console that will be converted to array and stored in the board's edges
    */
   public static void parseEdgeInput(int c, int[][] board, String edge) {
   	//converts input string into s-int array
   	int[] edgeInput = new int[s];
   	for(int a = 0; a<s; a++) {
   		edgeInput[a] = edge.charAt(a) - '0';
   	}
   	//top/bottom edge input handler
   	if(c<2) {
   		for(int b = 0; b<s; b++) {
   			board[(s+1)*c][b+1] = edgeInput[b];//bottom row if c = 1, top row if c = 0
   		}
   	}else {//left/right edge input handler
   		for(int d = 0; d<s; d++) {
   			board[d+1][(s+1)*(c%2)] = edgeInput[d];//left column if c = 2, right column if c = 3
   		}
   	}
   }

/***************************************initial row/column functions****************************************/
   
   /*
    * method that fills the row permutations array list with potential permutation candidates based on the edge numbers and edge numbers alone
    * also updates the guess matrix accordingly
    * INPUTS: 
    * leftSided - true if the height indicator is on the left side, false if it's on the right
    * row - what row is currently being input into
    * height - number at either the left or right edge, basically how many skyscrapers you should see if you're looking at it from that edge
    * source - the list of permutations the function is pulling from, usually allPerms unless it's a double-sided row
    * target - the row array list that is accepting permutation candidates
    * guesses - self-explanatory
    */
   public static void initialRowInput(boolean leftSided, int row, int height, ArrayList<int[]> source, ArrayList<int[]> target, int[][] guesses) {
   	int[] guessCandidates = new int[s];//this will later be filled in in the guesses matrix
   	int tallestSoFar = 0, z = 0;
   	for(int x = 0; x<source.size(); x++) {
   		tallestSoFar = 0;
   		z = 0;
   		int[] permCandidate = source.get(x), guessCandidatesTemp = convertToGuesses(permCandidate);
   		//guessCandidatesTemp converts each permutation candidate into guesses, it gets or operator'd with guessCandidates if permCandidate fits the bill
   		if(leftSided) {//left sided case
   			for(int a = 0; a<permCandidate.length; a++) {
   				if(tallestSoFar<permCandidate[a]) {
   					tallestSoFar = permCandidate[a];
   					z++;
   				}
   			}
   		}else {//right sided case
   			for(int b = permCandidate.length-1; b>=0; b--) {
   				if(tallestSoFar<permCandidate[b]) {
   					tallestSoFar = permCandidate[b];
   					z++;
   				}
   			}
   		}
   		if(z==height) {
   			target.add(permCandidate);
   			for(int c = 0; c<guessCandidates.length; c++) {
   				guessCandidates[c] = guessCandidates[c]|guessCandidatesTemp[c];
   			}
   		}
   	}
   	for(int d = 0; d<s; d++) {
   		guesses[row][d+1] = guessCandidates[d];
   	}//fills in guess candidates in the row
   }

   /*
    * i literally have to make this method or else every 0-sided row array list just becomes a link to allPerms
    * copies the source array list to the target array list
    * so fucking dumb
    * INPUTS:
    * source - the source
    * target - the target
    */
   public static void initialRowInputZeroCase(ArrayList<int[]> source, ArrayList<int[]> target) {
   	for(int a = 0; a<source.size(); a++) {
   		int[] b = source.get(a);
   		target.add(b);
   	}
   }
   
   /*
    * this method is pretty similar to the initial row input method but with some added things it does
    * - first off, it's initial inputs for the columns, not the rows
    * - it also throws out certain permutation candidates if they are incompatible with 
    * - it's also the first time i do "guess flagging," which i explain later in this method
    * so to put it shortly: 
    * method that fills the row permutations array list with potential permutation candidates based on the edge numbers & existing guesses from the inital row fill method
    * also updates the guess matrix accordingly and engages in guess flagging of rows
    * INPUTS:
    * topSided - true if height indicator is on the top edge, false if it's on the bottom edge
    * col - what column is currently being input into
    * height - height indicator value
    * source - the list of permutations the function is pulling from, usually allPerms unless it's a double-sided column
    * target - the column array list that is accepting permutation candidates
    * guesses - self-explanatory
    */
   public static void initialColumnInput(boolean topSided, int col, int height, ArrayList<int[]> source, ArrayList<int[]> target, int[][] guesses) {
   	int[] exGuesses = new int[s];
   	for(int x = 1; x<=s; x++) {
   		exGuesses[x-1] = guesses[x][col];
   	}
   	//existing guesses for that column
   	//since getting rows and columns is going to be a subtractive method from here on out, this will be cross-referenced later with each permuation
   	//candidate to throw out prohibitive ones
   	int[] newGuesses = new int[s];//this will later be &-ed with the guesses matrix
   	int tallestSoFar = 0, z = 0;
   	for(int a = 0; a<source.size(); a++) {
   		tallestSoFar = 0;
   		z = 0;
   		int[] permCandidate = source.get(a), permCandidateGuesses = convertToGuesses(permCandidate);
   		if(validGuess(permCandidateGuesses,exGuesses)) {//only does the following if the permuation candidate doesn't contradict existing guesses
   			if(topSided) {//top sided case
   				for(int b = 0; b<permCandidate.length; b++) {
   					if(tallestSoFar<permCandidate[b]) {
   						tallestSoFar = permCandidate[b];
   						z++;
   					}
   				}
   			}else {//bottom sided case
   				for(int c = permCandidate.length-1; c>=0; c--) {
   					if(tallestSoFar<permCandidate[c]) {
   						tallestSoFar = permCandidate[c];
   						z++;
   					}
   				}
   			}
   			if(z==height) {
   				target.add(permCandidate);
   				for(int d = 0; d<s; d++) {
   					newGuesses[d] = newGuesses[d]|permCandidateGuesses[d];
   				}
   			}
   		}
   	}
   	columnFlaggingRoutine(col,guesses,newGuesses);
   	/*
   	 * i guess this is where i should explain flagging
   	 * basically, whenever i make a change to a given column's existing guesses, i flag the row that that guess is in by changing the edge value of that row to 1, and vice versa
   	 * later on in the program, I'm going to be iterating over columns and rows, and this is a good way to tell the program
   	 * "hey, this row/column has had a change recently, this might mean that the list of candidate permutations for this row/column is out of date, do something about that"
   	 */
   }
   
   /*
    * initial column input method for when the top and bottom edge are both zero
    * relatively stripped down version of the initial column input method is
    * all it does is add permutation candidates to the respective column array list based on existing guesses
    * INPUTS:
    * col - column index
    * source - the list of permutations the function is pulling from, usually allPerms unless it's a double-sided column
    * target - the column array list that is accepting permutation candidates
    * guesses - self-explanatory
    */
   public static void initialColumnInputZeroCase(int col, ArrayList<int[]> source, ArrayList<int[]> target, int[][] guesses) {
   	int[] exGuesses = new int[s];
   	for(int x = 1; x<=s; x++) {
   		exGuesses[x-1] = guesses[x][col];
   	}
   	for(int a = 0; a<source.size(); a++) {
   		int[] permCandidate = source.get(a), permCandidateGuesses = convertToGuesses(permCandidate);
   		if(validGuess(permCandidateGuesses,exGuesses)) {//only does the following if the permuation candidate doesn't contradict existing guesses
   			target.add(permCandidate);
   		}
   	}
   }

/**************************************row/column flagging functions****************************************/
   
   /*
    * name of this method is a bit of a misnomer; its actually the rows that are getting flagged
    * compares existing guesses in the guesses matrix with the new guesses array
    * if there is a difference between the existing and new guesses at a given index, it flags that index's row
    * finally, it &s the existing and new guesses
    * INPUTS: 
    * index - column index
    * guesses - self-explanatory
    * newGuesses - array of new guesses to be compared against existing guesses
    */
   public static void columnFlaggingRoutine(int index, int[][] guesses, int[] newGuesses) {
   	for(int a = 1; a<=s; a++) {
   		if(newGuesses[a-1]!=guesses[a][index]) {
   			guesses[a][0] = 1;
   			//flagging routine
   		}
   		guesses[a][index] = guesses[a][index]&newGuesses[a-1];
   	}
   }
   
   /*
    * name of this method is a bit of a misnomer; its actually the columns that are getting flagged
    * compares existing guesses in the guesses matrix with the new guesses array
    * if there is a difference between the existing and new guesses at a given index, it flags that index's column
    * finally, it &s the existing and new guesses
    * INPUTS: 
    * index - row index
    * guesses - self-explanatory
    * newGuesses - array of new guesses to be compared against existing guesses
    */
   public static void rowFlaggingRoutine(int index, int[][] guesses, int[] newGuesses) {
   	for(int a = 1; a<=s; a++) {
   		if(newGuesses[a-1]!=guesses[index][a]) {
   			guesses[0][a] = 1;
   			//flagging routine
   		}
   		guesses[index][a] = guesses[index][a]&newGuesses[a-1];
   	}
   }

/****************************************looping section functions******************************************/
   
    /*
     * method that scans the guesses matrix for cells that only have 1 valid guess, i.e. have a value that's a power of 2 between 2 and 2^s
     * if it finds one AND the corresponding index on the board matrix doesn't have a value yet, it inputs the value of that guess in the board
     * INPUTS:
     * board - self-explanatory
     * guesses - self-explanatory
     */
    public static void checkForSingularGuesses(int[][] board, int[][] guesses) {
    	for(int x = 1; x<=s; x++) {
    		for(int y = 1; y<=s; y++) {
    			if(isSingularGuess(guesses[x][y])&&board[x][y]==0) {
    				//only goes into the insert number routine if the singular guess is at an index where there isn't a board input yet, i.e. if it = 0
    				insertNumberRoutine(board, guesses, logBase2(guesses[x][y]), x, y);
    			}
    		}
    	}
    }
    
    /*
     * method for inserting a number into the board matrix
     * it inserts the number and removes all guesses of that number from its row and colum in the guesses matrix
     * INPUTS:
     * board - self-explanatory
     * guesses - self-explanatory
     * skyscraper - the number being inserted into the board at position (x, y)
     * x - row index of inserted number
     * y - column index of inserted number
     */
    public static void insertNumberRoutine(int[][] board, int[][] guesses, int skyscraper, int x, int y) {
    	int[] newRowGuesses = new int[s], newColGuesses = new int[s];    
    	for(int f = 0; f<s; f++) {
    		newRowGuesses[f] = guesses[x][f+1];
    		newColGuesses[f] = guesses[f+1][y];
    	}
    	int notSkyscraperBitwise = ~((int)Math.pow(2, skyscraper));
    	//the following for loop &s every input of newRowGuesses and newColGuesses with notSkyscraperBitwise EXCEPT for the index at x,y
    	//this is to set up newRowGuesses and newColGuesses for row and column flagging routines, respectively
    	//i'm skipping over the indices of skyscraper in newRowGuesses & newColumnGuesses because i want to remove skyscraper as a guess from every cell
    	//in the row and column of skyscraper EXCEPT for skyscraper itself because of interplay between both my singular guesses checking function AND
    	//my puzzle completion checking function
    	for(int a = 0; a<s; a++) {
    		if(a!=y-1) {
    			newRowGuesses[a] = newRowGuesses[a] & notSkyscraperBitwise;
    		}
    		if(a!=x-1) {
    			newColGuesses[a] = newColGuesses[a] & notSkyscraperBitwise;
    		}
    	}
    	board[x][y] = skyscraper;
    	guesses[x][y] = (int) Math.pow(2, skyscraper);
    	rowFlaggingRoutine(x, guesses, newRowGuesses);
    	columnFlaggingRoutine(y,guesses,newColGuesses);
    }
    
    /*
     * method that removes row permutations from row array lists that contradict with existing guesses in the guess matrix
     * some similarities with the initial column input functions
     * INPUT:
     * row - row index
     * perms - array list of permutations that is being analyzed
     * guesses - self-explanatory
     */
    public static void removeInvalidRowPerms(int row, ArrayList<int[]> perms, int[][] guesses) {
    	int[] exGuesses = new int[s];
    	for(int g = 0; g<s; g++) {
    		exGuesses[g] = guesses[row][g+1];
    	}
    	int[] newGuesses = new int[s];
    	int x = 0;
    	while(x<perms.size()) {
    		//i'm making this a while loop that only incs when it's a valid perm candidate bc i don't really trust for loop iteration over an array list
    		int[] permCandidate = perms.get(x), permCandidateGuesses = convertToGuesses(permCandidate);
    		//following if statement: 
    		//if perms is a valid permutation candidate, increment the index counter and store perms as guesses in newGuesses. if not, drop from the array list
    		//this way i'm sure that I iterate over every permutation in perms and every permutation at an index before x is a valid permutation
    		if(validGuess(permCandidateGuesses, exGuesses)) {
    			x++;
    			for(int a = 0; a<permCandidate.length; a++) {
    				newGuesses[a] = newGuesses[a]|permCandidateGuesses[a];
    			}
    		}else {
    			perms.remove(x);
    		}
    	}
    	rowFlaggingRoutine(row, guesses, newGuesses);
    }
    
    /*
     * method that removes column permutations from column array lists that contradict with existing guesses in the guess matrix
     * some similarities with the initial column input functions
     * INPUT:
     * col - column index
     * perms - array list of permutations that is being analyzed
     * guesses - self-explanatory
     */
    public static void removeInvalidColumnPerms(int col, ArrayList<int[]> perms, int[][] guesses) {
    	int[] exGuesses = new int[s];
    	for(int g = 0; g<s; g++) {
    		exGuesses[g] = guesses[g+1][col];
    	}
    	int[] newGuesses = new int[s];
    	int x = 0;
    	while(x<perms.size()) {
    		//i'm making this a while loop that only incs when it's a valid perm candidate bc i don't really trust for loop iteration over an array list
    		int[] permCandidate = perms.get(x), permCandidateGuesses = convertToGuesses(permCandidate);
    		//following if statement: 
    		//if perms is a valid permutation candidate, increment the index counter and store perms as guesses in newGuesses. if not, drop from the array list
    		//this way i'm sure that I iterate over every permutation in perms and every permutation at an index before x is a valid permutation
    		if(validGuess(permCandidateGuesses, exGuesses)) {
    			x++;
    			for(int a = 0; a<permCandidate.length; a++) {
    				newGuesses[a] = newGuesses[a]|permCandidateGuesses[a];
    			}
    		}else {
    			perms.remove(x);
    		}
    	}
    	columnFlaggingRoutine(col, guesses, newGuesses);
    }
/*****************************************hypothetical functions********************************************/
   
    /*
     * figured initializing all the variables properly for the hypothetical state would take a while, so i made it its own method
     * copies over all values from board to hypoBoard and copies over all values from guesses to hypoGuesses
     * initializes each individual array list in rowReceptacle and colReceptacle
     * also puts all two guess values into twoGuesses
     * INPUTS:
     * same thing they are in the main function
     */
    public static void hypoSetup(int[][]board, int[][] hypoBoard, int[][] guesses, int[][] hypoGuesses, ArrayList<ArrayList<int[]>> rowReceptacle, ArrayList<ArrayList<int[]>> colReceptacle, int[][] twoGuesses){
    	for(int a = 0; a<=s+1; a++) {
    		for(int b = 0; b<=s+1; b++) {
    			hypoBoard[a][b] = board[a][b];
    			hypoGuesses[a][b] = guesses[a][b];
    		}
    	}
    	for(int c = 0; c<s; c++) {
    		rowReceptacle.add(new ArrayList<int[]>());
    		colReceptacle.add(new ArrayList<int[]>());
    	}
    	int d = 1, e = 2;
    	for(int g = 0; g<(s*(s-1))/2; g++) {
    		twoGuesses[g][0] = ((int)Math.pow(2, d))+((int)Math.pow(2, e));
    		twoGuesses[g][1] = d;
    		twoGuesses[g][2] = e;
    		if(d==e-1) {
    			d = 1;
    			e++;
    		}else {
    			d++;
    		}//this is the algorithm for putting every sum of 2 distinct numbers from 1 to s into an array
    	}
    }
    
    /*
     * method that finds an arbitrary cell in the hypoGuesses matrix that only has 2 guesses and outputs it
     * INPUTS:
     * hypoGuesses - same as main function
     * twoGuessses - same as main function
     * OUTPUTS: 
     * a 1x4 array with these values:
     * [x coordinate of 2 guesses cell, y coordinate of 2 guesses cell, lower of the 2 guesses, higher of the 2 guesses]
     */
    public static int[] findTwoGuesses(int[][] hypoGuesses, int[][] twoGuesses) {
    	int a = 0, b = 1, c = -1;
    	boolean twoGuessesFound = false;
    	while(!twoGuessesFound&&b<s+1) {
    		a++;
    		if(a==s+1) {
    			a = 1;
    			b++;
    		}//i'm doing all this incrementing weirdly because i want it to return the a, b, and twoGuesses[c] value the moment twoGuessesFound turns out to be true
    		while(!twoGuessesFound&&c<(s*(s-1))/2-1) {
    			c++;
    			twoGuessesFound = hypoGuesses[a][b]==twoGuesses[c][0];
    		}
    		if(!twoGuessesFound) {
    			c = -1;//resets the two guesses counter if the two guesses haven't been found yet
    		}
    	}
    	int[] ftg = {a, b, twoGuesses[c][1], twoGuesses[c][2]};
    	return ftg;
    }
 
    /*
     * method that removes row permutations from row array lists that contradict with existing guesses in the guess matrix
     * some similarities with the initial column input functions
     * also checks to make sure that perms isn't empty by the end of the function
     * INPUT:
     * row - row index
     * perms - array list of permutations that is being analyzed
     * receptacle - array list that all permutations being thrown out of perms is entered into
     * guesses - this is actually hypoGuesses
     * OUTPUT:
     * true if there is still at least 1 array in perms
     * false otherwise
     */
    public static boolean removeInvalidRowPermsHypo(int row, ArrayList<int[]> perms, ArrayList<int[]> receptacle, int[][] guesses) {
    	int[] exGuesses = new int[s];
    	for(int g = 0; g<s; g++) {
    		exGuesses[g] = guesses[row][g+1];
    	}
    	int[] newGuesses = new int[s];
    	int x = 0;
    	while(x<perms.size()) {
    		//i'm making this a while loop that only incs when it's a valid perm candidate bc i don't really trust for loop iteration over an array list
    		int[] permCandidate = perms.get(x), permCandidateGuesses = convertToGuesses(permCandidate);
    		//following if statement: 
    		//if perms is a valid permutation candidate, increment the index counter and store perms as guesses in newGuesses. if not, drop from the array list
    		//this way i'm sure that I iterate over every permutation in perms and every permutation at an index before x is a valid permutation
    		if(validGuess(permCandidateGuesses, exGuesses)) {
    			x++;
    			for(int a = 0; a<permCandidate.length; a++) {
    				newGuesses[a] = newGuesses[a]|permCandidateGuesses[a];
    			}
    		}else {
    			int[] temp = perms.remove(x);
    			receptacle.add(temp);
    		}
    	}
    	rowFlaggingRoutine(row, guesses, newGuesses);
    	boolean rirph = perms.size()!=0;
    	return rirph;
    }
    
    /*
     * method that removes column permutations from column array lists that contradict with existing guesses in the guess matrix
     * some similarities with the initial column input functions
     * also checks to make sure that perms isn't empty by the end of the function
     * INPUT:
     * col - column index
     * perms - array list of permutations that is being analyzed
     * receptacle - array list that every permutation thrown out of perms goes into
     * guesses - self-explanatory
     * OUTPUT:
     * true if perms wasn't emptied by this function
     * false otherwise
     */
    public static boolean removeInvalidColumnPermsHypo(int col, ArrayList<int[]> perms, ArrayList<int[]> receptacle, int[][] guesses) {
    	int[] exGuesses = new int[s];
    	for(int g = 0; g<s; g++) {
    		exGuesses[g] = guesses[g+1][col];
    	}
    	int[] newGuesses = new int[s];
    	int x = 0;
    	while(x<perms.size()) {
    		//i'm making this a while loop that only incs when it's a valid perm candidate bc i don't really trust for loop iteration over an array list
    		int[] permCandidate = perms.get(x), permCandidateGuesses = convertToGuesses(permCandidate);
    		//following if statement: 
    		//if perms is a valid permutation candidate, increment the index counter and store perms as guesses in newGuesses. if not, drop from the array list
    		//this way i'm sure that I iterate over every permutation in perms and every permutation at an index before x is a valid permutation
    		if(validGuess(permCandidateGuesses, exGuesses)) {
    			x++;
    			for(int a = 0; a<permCandidate.length; a++) {
    				newGuesses[a] = newGuesses[a]|permCandidateGuesses[a];
    			}
    		}else {
    			receptacle.add(perms.remove(x));
    		}
    	}
    	columnFlaggingRoutine(col, guesses, newGuesses);
    	boolean ricph = perms.size()!=0;
    	return ricph;
    }
    
    /*
     * method that puts everything in array list receptacle into array list original
     * by the end, every array list in receptacle should be empty and every array list in original should include what it came into the method with 
     * + every array that was in the corresponding receptacle array list index when the method was called
     * INPUTS:
     * receptacle - array list of array lists that are each getting emptied
     * original - array list of array lists that are each getting filled
     */
    public static void returnFromReceptacle(ArrayList<ArrayList<int[]>> receptacle, ArrayList<ArrayList<int[]>> original) {
    	for(int i = 0; i<receptacle.size(); i++) {
    		for(int[] j : receptacle.get(i)) {
    			if(!original.get(i).contains(j)) {//for some reason i would sometimes get duplicates so this is a way around that
    			original.get(i).add(j);    				
    			}
    		}
    	}
    }

    
    /*
     * method that replicates the logic of the looping section
     */
    public static boolean hypotheticalRecursionTree(int[][] board, int[][] guesses, ArrayList<ArrayList<int[]>> rowPerms, ArrayList<ArrayList<int[]>> colPerms, int recursionLevel) {
	    int[][] hypoBoard = new int[s+2][s+2], hypoGuesses = new int[s+2][s+2];
	   	//hypothetical board array and guesses array that's going to be what i'm working in in the hypothetical section
	   	ArrayList<ArrayList<int[]>> rowReceptacle = new ArrayList<ArrayList<int[]>>(), colReceptacle = new ArrayList<ArrayList<int[]>>();
	   	//i'm going to be removing arrays from the array lists in rowPerms and colPerms, but I don't want to just throw away those arrays in case the hypothetical is invalid
	   	//so i'm going to be putting them in these receptacle array lists, so that if the guess turns out to be invalid, i can just put them right back
	   	int[][] twoGuesses = new int[(s*(s-1))/2][3];
	   	//0-index column becomes an array of every possible value a guess array cell with two guesses can have
	   	//values will be [2^1+2^2, 2^1+2^3,....., 2^(s-2)+2^s, 2^(s-1)+2^s]
	   	//1-index and 2-index columns are just which powers of 2 are being summed, makes the findTwoGuesses function easier
	   	//it's triangular numbers so that's why it has that many rows
	   	hypoSetup(board, hypoBoard, guesses, hypoGuesses, rowReceptacle, colReceptacle, twoGuesses);
	   	//method that just sets everything up the way i've described it
	   	int[] hypoCandidate = findTwoGuesses(hypoGuesses, twoGuesses);
	   	insertNumberRoutine(hypoBoard, hypoGuesses, hypoCandidate[2], hypoCandidate[0], hypoCandidate[1]);
	   	//arbitrarily guesses the lower of the 2 guesses at the arbitrary 2-guess cell
	   	System.out.println("guessing the value at ("+hypoCandidate[0]+","+hypoCandidate[1]+") is "+hypoCandidate[2]+" at recursion level "+recursionLevel);
	   	int h = 1, o=1;
	   	boolean validHypothetical = true, moreWorkToBeDone = true;
	   	while(moreWorkToBeDone&&validHypothetical) {
	   		System.out.println("~~~~~~~~~~~~~~~~~~~~~HYPOTHETICAL LOOP NUMBER "+h+" at recursion level "+recursionLevel+"~~~~~~~~~~~~~~~~~~~~~");
	       	h++;
	       	o = 1;
	       	moreWorkToBeDone = false;//exits while loop once all there are no more guess flags
	           checkForSingularGuesses(hypoBoard, hypoGuesses);//checks the guesses matrix to see if any of the cells only have 1 guess
	           while(o<=s&&validHypothetical) {//changed this to a while loop bc it's also dependent on if it's a valid hypothetical
	           	if(hypoGuesses[o][0]==1) {//triggers if there's row a guess flag
	           		hypoGuesses[o][0] = 0;//resets guess flag
	           		moreWorkToBeDone = true;
	           		validHypothetical = removeInvalidRowPermsHypo(o, rowPerms.get(o-1), rowReceptacle.get(o-1), hypoGuesses);
	           		//removes invalid row permutations from the relevant row permutations array list and does guess flagging       		      		
	           	}
	           	o++;
	           }
	           o = 1;
	           while(o<=s&&validHypothetical) {//doesn't enter this while loop if it's been flagged as an invalid hypothetical
	           	if(hypoGuesses[0][o]==1) {
	           		hypoGuesses[0][o] = 0;
	           		moreWorkToBeDone = true;
	           		validHypothetical = removeInvalidColumnPermsHypo(o,colPerms.get(o-1), colReceptacle.get(o-1),hypoGuesses);
	           	}
	           	o++;
	           }
	           printBoard(hypoBoard);
	           System.out.print("\n");
	           printGuesses(hypoBoard,hypoGuesses);
	           System.out.print("\n");
	           }
	   	//so from here, there are 3 possible outcomes:
	   	//outcome 1: guessing the value at (hypoCandidate[0], hypoCandidate[1]) to be hypoCandidate[2] yielded an invalid solution
	   	//validHypothetical = false
	   	//outcome 2: guessing the value at (hypoCandidate[0], hypoCandidate[1]) to be hypoCandidate[2] yielded a valid, complete solution
	   	//validHypothetical = true, isComplete = true
	   	//outcome 3: guessing the value at (hypoCandidate[0], hypoCandidate[1]) to be hypoCandidate[2] yielded an incomplete solution
	   	//validHypothetical = true, isComplete = false
	   	//for outcome 1, i always want to enter the 2nd hypothetical subsection, and for outcome 2, i basically want the function to end here
	   	//but for outcome 3, more investigation is necessary, and this is where i want to implement the first instance of recursion
	   	if(validHypothetical&&!isComplete(rowPerms)) {
   			System.out.println("guessing the value at ("+hypoCandidate[0]+","+hypoCandidate[1]+") as "+hypoCandidate[2]+" at recursion level "+recursionLevel+" was inconclusive\ngoing 1 recursion level deeper");		
	   		validHypothetical = hypotheticalRecursionTree(hypoBoard, hypoGuesses, rowPerms, colPerms, recursionLevel+1);
	   	}
	   	if(!validHypothetical) {
	   		//2nd hypothetical subsection
	   		System.out.println("guessing the value at ("+hypoCandidate[0]+","+hypoCandidate[1]+") as "+hypoCandidate[2]+" at recursion level "+recursionLevel+" was incorrect\ntrying out value of "+hypoCandidate[3]);   			
	   		returnFromReceptacle(colReceptacle, colPerms);
	   		returnFromReceptacle(rowReceptacle, rowPerms);
	   		for(int a = 0; a<=s+1; a++) {
	    		for(int b = 0; b<=s+1; b++) {
	    			hypoBoard[a][b] = board[a][b];
	    			hypoGuesses[a][b] = guesses[a][b];
	    		}
	   		}//resets colPerms, rowPerms, hypoBoard, and hypoGuesses
	   		insertNumberRoutine(hypoBoard, hypoGuesses, hypoCandidate[3], hypoCandidate[0], hypoCandidate[1]);
	           moreWorkToBeDone = true;
	           validHypothetical = true;
	           while(moreWorkToBeDone&&validHypothetical) {
	        	   System.out.println("~~~~~~~~~~~~~~~~~~~~~HYPOTHETICAL LOOP NUMBER "+h+" at recursion level "+recursionLevel+"~~~~~~~~~~~~~~~~~~~~~");
	              	h++;
	              	o = 1;
	              	moreWorkToBeDone = false;//exits while loop once all there are no more guess flags
	                  checkForSingularGuesses(hypoBoard, hypoGuesses);//checks the guesses matrix to see if any of the cells only have 1 guess
	                  while(o<=s&&validHypothetical) {//changed this to a while loop bc it's also dependent on if it's a valid hypothetical
	                  	if(hypoGuesses[o][0]==1) {//triggers if there's row a guess flag
	                  		hypoGuesses[o][0] = 0;//resets guess flag
	                  		moreWorkToBeDone = true;
	                  		validHypothetical = removeInvalidRowPermsHypo(o, rowPerms.get(o-1), rowReceptacle.get(o-1), hypoGuesses);
	                  		//removes invalid row permutations from the relevant row permutations array list and does guess flagging       		      		
	                  	}
	                  	o++;
	                  }
	                  o = 1;
	                  while(o<=s&&validHypothetical) {//doesn't enter this while loop if it's been flagged as an invalid hypothetical
	                  	if(hypoGuesses[0][o]==1) {
	                  		hypoGuesses[0][o] = 0;
	                  		moreWorkToBeDone = true;
	                  		validHypothetical = removeInvalidColumnPermsHypo(o,colPerms.get(o-1), colReceptacle.get(o-1),hypoGuesses);
	                  	}
	                  	o++;
	                  }
	                  printBoard(hypoBoard);
	                  System.out.print("\n");
	                  printGuesses(hypoBoard,hypoGuesses);
	                  System.out.print("\n");
	                  }   
	           //same 3 outcomes here as previously mentioned
	           if(validHypothetical&&!isComplete(rowPerms)) {
	        	   System.out.println("guessing the value at ("+hypoCandidate[0]+","+hypoCandidate[1]+") as "+hypoCandidate[3]+" at recursion level "+recursionLevel+" was inconclusive\ngoing 1 recursion level deeper");	        		   
	        	   validHypothetical = hypotheticalRecursionTree(hypoBoard, hypoGuesses, rowPerms, colPerms, recursionLevel+1);
	           }
	           if(!validHypothetical) {
	        	   System.out.println("guessing the value at ("+hypoCandidate[0]+","+hypoCandidate[1]+") as "+hypoCandidate[3]+" at recursion level "+recursionLevel+" was also incorrect\nreturning to previous recursion level");	        		   
	        	   returnFromReceptacle(colReceptacle, colPerms);
	        	   returnFromReceptacle(rowReceptacle, rowPerms);
	        	   //resets rowPerms and colPerms; don't really care about hypoBoard or hypoGuesses bc they get thrown out once i leave this recursion level
	           }
	   		}
	   	return validHypothetical;
    }
    
/**********************************************main function************************************************/

    /*
     * the main function
     * you know what a main function is
     */
    public static void main(String args[]) {
    /******************************INITALIZATION SECTION******************************/
    	/**********Setting size of board subsection**********/
        Scanner fromConsole = new Scanner(System.in);
    	System.out.println("Input size of board:");
    	String boardSize = fromConsole.nextLine();
    	while(boardSize.length()!=1||(boardSize.charAt(0)-'0')<4||(boardSize.charAt(0)-'0')>9) {
    		System.out.println("Invalid board size, try again: ");
    		boardSize = fromConsole.nextLine();
    		//if the input is anything other than '4', '5', '6', '7', '8', or '9' (the only valid board sizes), it rejects the input and tries agian
    	}
    	s = boardSize.charAt(0)-'0';//sets board size global variable
    	/**********variable initialization subsection**********/
        int[][] board = new int[s+2][s+2];
                         /*initializes the board as a blank array
                         corners are not used
                         edges indicate the numbers on the side of the grid, 0 if there no numbers
                         center sxs grid is the input skyscraper number (0 if no input yet)*/
        int[][] guesses = new int[s+2][s+2];
                         /*initializes the guesses board
                         top and left edges are used for flagging changes (more on that in the initial column input function), bottom and right edges are not used
                         each guess int is treated as a bitwise operator where the 0th bit isn't used and the xth bit represents a guess of x
                         ex. a cell with a guesses value of 178 = 2^1+2^4+2^5+2^7 would have valid guesses of 1, 4, 5, and 7
                         0 is chosen as an initial value for all guesses because i'm doing de-facto initialization at the row perm fillout phase*/
        ArrayList<int[]> allPerms = new ArrayList<int[]>();
        ArrayList<int[]> permsTemp = new ArrayList<int[]>();//for getting and setting permutations 
        ArrayList<int[]> permsTemp2 = new ArrayList<int[]>();
        int[] toBePermed = new int[s];
        for(int a = 1; a<=s; a++) {
        	toBePermed[a-1] = a;
        }//creates an array of [1, 2, ....., s]. this is for generating permutations in allPerms later
        int sidedness = 0; //more on this one later; it's a 2-bit indicator for initial row and column inputs
        boolean moreWorkToBeDone = true;//flag variable for the looping section
        printAllRecursive(s,toBePermed,allPerms, ',');
        /*initalizes lists for each row and column
         * allPerms is a list of every permutation of numbers 1-s
         * each array list will contain a continually-updated list of all possible permutations for that given row/column, based on edge numbers, cell candidacy, etc
         * it'll interface with the guesses array in a way i'll explain later
         * 0 index for column arrays is row 1, s-1 index is row s, 0 index for row arrays is column 1, s-1 index is column s, you get the gist
         */
        ArrayList<ArrayList<int[]>> rowPerms = new ArrayList<ArrayList<int[]>>(), colPerms = new ArrayList<ArrayList<int[]>>();
       for(int a = 0; a<s; a++) {
    	   rowPerms.add(new ArrayList<int[]>());
    	   colPerms.add(new ArrayList<int[]>());
       }
        /*i have to create an array list of ArrayList<int[]> because i need a way to iterate over all my 
          array lists that i'm storing int arrays in and I can't make an array of ArrayList<int[]>. hope this
          doesn't create any pointer fuckery*/
        /*fucking java man*/      
        /**********parse edge input subsection**********/
        
        System.out.println("input the top edge height values from left to right, no spaces. use 0 for null height values");
        String topEdge = fromConsole.nextLine();
        while(!validEdgeInput(topEdge)) {
        	System.out.println("invalid edge input, try again:");
        	topEdge = fromConsole.nextLine();
        }
        parseEdgeInput(0, board, topEdge);
        System.out.println("input the bottom edge height values from left to right, no spaces. use 0 for null height values");
        String botEdge = fromConsole.nextLine();
        while(!validEdgeInput(botEdge)) {
        	System.out.println("invalid edge input, try again:");
        	botEdge = fromConsole.nextLine();
        }
        parseEdgeInput(1, board, botEdge);
        System.out.println("input the left edge height values from top to bottom, no spaces. use 0 for null height values");
        String leftEdge = fromConsole.nextLine();
        while(!validEdgeInput(leftEdge)) {
        	System.out.println("invalid edge input, try again:");
        	leftEdge = fromConsole.nextLine();
        }
        parseEdgeInput(2, board, leftEdge);
        System.out.println("input the right edge height values from top to bottom, no spaces. use 0 for null height values");
        String rightEdge = fromConsole.nextLine();
        while(!validEdgeInput(rightEdge)) {
        	System.out.println("invalid edge input, try again:");
        	rightEdge = fromConsole.nextLine();
        }
        parseEdgeInput(3, board, rightEdge);
        /**********initial row permutation fillout**********/
        for(int a = 1; a<=s; a++) {
        	sidedness = 1*(board[a][0]!=0? 1: 0) + 2*(board[a][s+1]!=0? 1: 0);
        	//returns 0 if neither side of the row has a number, 1 if the number is on the left side, 2 if it's on the right, 3 if both sides have numbers
        	permsTemp = rowPerms.get(a-1);
        	switch(sidedness) {
        	case 1: 
        		initialRowInput(true,a,board[a][0],allPerms,permsTemp,guesses);
        		rowPerms.set(a-1, permsTemp);
        		break;
        	case 2:
        		initialRowInput(false,a,board[a][s+1],allPerms,permsTemp,guesses);
        		rowPerms.set(a-1, permsTemp);
        		break;
        	case 3: 
        		permsTemp2 = new ArrayList<int[]>();//clears permsTemp2 i think
        		initialRowInput(true,a,board[a][0],allPerms,permsTemp,guesses);
        		//puts all left-handed valid permutations into permsTemp
        		initialRowInput(false,a,board[a][s+1],permsTemp,permsTemp2,guesses);
        		//puts all right-handed valid permutations that are also in permsTemp into permsTemp2
        		rowPerms.set(a-1, permsTemp2);
        		break;
        	default: 
        		initialRowInputZeroCase(allPerms, permsTemp);
        		rowPerms.set(a-1, permsTemp);
        		//since there are no constraints it can just be like any permutation
        		for(int b = 1; b<=s; b++) {
        			guesses[a][b] = ((int)Math.pow(2, s+1))-2;
        			//has to be this number as it's a sum of 2^1+2^2+....+2^s
        		}//setting all the guesses in this row as any guesses
        	}
        }
        /**********initial column permutation fillout**********/
        for(int b = 1; b<=s; b++) {
        	sidedness = 1*(board[0][b]!=0? 1: 0) + 2*(board[s+1][b]!=0? 1: 0);
        	//returns 0 if neither side of the column has a number, 1 if the number is on the top side, 2 if it's on the bottom, 3 if both sides have numbers
        	permsTemp = colPerms.get(b-1);
        	switch(sidedness) {
        	case 1: 
        		initialColumnInput(true,b,board[0][b],allPerms,permsTemp,guesses);
        		colPerms.set(b-1, permsTemp);
        		break;
        	case 2:
        		initialColumnInput(false,b,board[s+1][b],allPerms,permsTemp,guesses);
        		colPerms.set(b-1, permsTemp);
        		break;
        	case 3: 
        		permsTemp2 = new ArrayList<int[]>();//clears permsTemp2 i think
        		initialColumnInput(true,b,board[0][b],allPerms,permsTemp,guesses);
        		//puts all top-sided valid permutations into permsTemp
        		initialColumnInput(false,b,board[s+1][b],permsTemp,permsTemp2,guesses);
        		//puts all bottom-sided valid permutations that are also in permsTemp into permsTemp2
        		colPerms.set(b-1, permsTemp2);
        		break;
        	default: 
        		initialColumnInputZeroCase(b, allPerms, permsTemp, guesses);
        		colPerms.set(b-1, permsTemp);
        	}
        }
        /**********parse initial board values input subsection**********/
        
        System.out.println("input new number to insert as this format: number, row, column. \ni.e. input \"541\" if you wish to input the number five in the fourth row and first column of the board. "
        		+ "\ninput \"end\" if you are done inputting numbers");
        String boardValueInput = fromConsole.nextLine();
        while(boardValueInput.charAt(0)!='e') {
        	while(!validBoardInput(boardValueInput)) {
        		System.out.println("invalid input, try again");
        		boardValueInput = fromConsole.nextLine();
        	}
        	insertNumberRoutine(board, guesses, boardValueInput.charAt(0)-'0', boardValueInput.charAt(1)-'0', boardValueInput.charAt(2)-'0');
        	System.out.println("input new number to insert as this format: number, row, column. \ni.e. input \"541\" if you wish to input the number five in the fourth row and first column of the board. "
            		+ "\ninput \"end\" if you are done inputting numbers");
        	boardValueInput = fromConsole.nextLine();
        }
        /******************************LOOPING SECTION******************************/
        int h = 1;
        printBoard(board);
        while(moreWorkToBeDone) {
        	System.out.println("~~~~~~~~~~~~~~~~~~~~~LOOP NUMBER "+h+"~~~~~~~~~~~~~~~~~~~~~");
        	h++;
        	moreWorkToBeDone = false;//exits while loop once all there are no more guess flags
            checkForSingularGuesses(board, guesses);//checks the guesses matrix to see if any of the cells only have 1 guess
            for(int q = 1; q<=s; q++) {//loop for iterating over rows
            	if(guesses[q][0]==1) {//triggers if there's row a guess flag
            		guesses[q][0] = 0;//resets guess flag
            		moreWorkToBeDone = true;
            		permsTemp = rowPerms.get(q-1);
            		removeInvalidRowPerms(q, permsTemp, guesses);
            		//removes invalid row permutations from the relevant row permutations array list and does guess flagging
            		rowPerms.set(q-1, permsTemp);
            	}
            }
            for(int r = 1; r<=s; r++) {
            	if(guesses[0][r]==1) {
            		guesses[0][r] = 0;
            		moreWorkToBeDone = true;
            		permsTemp = colPerms.get(r-1);
            		removeInvalidColumnPerms(r,permsTemp,guesses);
            		colPerms.set(r-1, permsTemp);
            	}
            }
            printBoard(board);
            System.out.print("\n");
            printGuesses(board,guesses);
            System.out.print("\n");
        }
        fromConsole.close();
        /******************************HYPOTHETICAL SECTION******************************/
        if(!isComplete(rowPerms)) {
        	 //so here's where I explain what i'm doing
        	 //basically, if the program is able to go through all those loops without solving the puzzle, it enters a hypothetical state
        	 //in the guessing state, the program finds an arbitrary cell with only 2 valid guesses and arbitrarily guesses 1 of the 2 guesses
        	 //the program then takes that guess to its logical conclusion, i.e. treating that guess as if it's fact.
        	 //if the guess is found out to be incorrect, i.e. any of the array lists in rowPerms or colPerms has a size of 0, hypothetical state will abort and the other of the two guesses will be input
        	 //if it's ever inconclusive for any guess i just go 1 level deeper into the recursion
        	hypotheticalRecursionTree(board, guesses, rowPerms, colPerms, 1);
        }
    }
}
