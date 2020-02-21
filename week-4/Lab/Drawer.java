// Patrick Temple
// Prof. Whitener
// CSCI165
// 21 February 2020

// Week 4 Lab - Bit Mapped Path Finder
// Purpose: to draw real geographic features
// from data provided in a .dat file, with the
// size of the window and array within the file's
// name. Also, we will use a "greedy algorithm" to
// sketch out paths and to find the shortest path
// of all of the paths taken by the program.


// graphics and program window libraries included 
// from the professor
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

// classes not originally included with
// this program template, and added by myself:
import java.util.Scanner;             // for reading the data fi;e
import java.io.File;                  // for the class File
import java.io.FileNotFoundException; // for when the data file cannot be found
import java.lang.Math;                // for the absolute value function
import java.util.Random;              // for random numbers (coin flips)

class Drawer extends JPanel {
	

    private static final long serialVersionUID = 1L;
    private JFrame window = new JFrame();            // defines window object
    
    private int windowWidth = 0;                     // for the width and height of the window
    private int windowHeight = 0;                    // and the mins and maxes of the file boundary
                                                     // both of which come from the file name in getDimensions()
                                                     // in a moment, along with the location
    
    private String location = "";                    // the location of the landscape, which will be the window title
    
    private int[][] mapData;                         // holds the data from the file
                                                     // (this will be defined further in a moment)

    private int highestPoint = Integer.MIN_VALUE; // the highest value of the data file
    private int lowestPoint = Integer.MAX_VALUE;  // the lowest value of the data file
    
    // the function that calls the other functions
    // that assist in creating our map
    public Drawer(String arg) {
        
        getDimensions(arg);              // get file and define array
        fillArray(mapData, arg);         // fill the array with the map data
        
        highestPoint = findMax(mapData); // get the highest point of the data
        lowestPoint = findMin(mapData);  // get the lowest point of the data
        initUI();                        // get ready to draw and 
    }
    
    public void getDimensions(String arg) {
    	
    	// using the name of the file supplied from
    	// the args, get the location, width, and height
    	// of the data and add them where relevant.
    	
    	// file name schema assumed:
    	// (place)_(width)x(height).dat
    	
    	int underscoreHere = arg.indexOf("_");                        // get index of underscore
    	location = arg.substring(0, underscoreHere);                  // use underscore loc. as an argument...
    	                                                              // to substring the name into its own String
    	int xHere = arg.indexOf("x", underscoreHere);                 // find the x and get index
    	int dotHere = arg.indexOf(".", xHere);                        // find the dot and get index
    	String winWidth = arg.substring((underscoreHere + 1), xHere); // get the width as a String (temporary)
    	String winHeight = arg.substring((xHere + 1), dotHere);       // and do the same with the height
    	windowWidth = Integer.parseInt(winWidth);                     // make the widths and heights into...
    	windowHeight = Integer.parseInt(winHeight);                   // integers, then use them to complete...
    	mapData = new int [windowHeight][windowWidth];                // the definition of the map data array
    	
    }
    
    public void fillArray(int matrix[][], String arg) {
		try {
			
			File numberList = new File(arg);               // using the file name from args
			Scanner getNumbers = new Scanner(numberList);  // for scanning the number list
			
			// load array from file according
			// to width and height
			for(int i = 0; i < windowHeight; i++) {
				
				for(int j = 0; j < windowWidth; j++) {
					
					matrix[i][j] = getNumbers.nextInt();   // get the numbers from the text file
					
				} // end second level for
				
			} // end for
			getNumbers.close();                            // close file scanner
			
		} // end try
		
		catch (FileNotFoundException e) { // files not found
			System.out.println("ERROR: File not found");
		} // end catch
		
	} // end fillArray (void)
    
    
    public int findMax(int[][] matrix) {
    	
		// find the maximum number in the entire array
		for(int i = 0; i < windowHeight; i++) {
			
			for(int j = 0; j < windowWidth; j++) {
				
				if(matrix[i][j] >= highestPoint) { // if higher number found
					
					highestPoint = matrix[i][j]; // save the new maximum
					
				} // end if 
				
			} // end secondary for
		} // end for
		
		return highestPoint;
		
	}
    
    public int findMin(int[][] matrix) {
		
		// find the minimum array in the entire array
		for(int i = 0; i < windowHeight; i++) {
			
			for(int j = 0; j < windowWidth; j++) {
				
				if(matrix[i][j] <= lowestPoint) { // lower number found
					
					lowestPoint = matrix[i][j]; // save the new minimum
					
				} // end if
				
			} // end secondary for
		} // end for
		
		return lowestPoint;
		
	}
    
    // get the window ready (where I commented was where I
    // made changes)
    private void initUI() {
        window.add(this);
        window.setTitle(location);                             // make the title the location before the underscore
        window.setSize(windowWidth, windowHeight);             // set the window width and height from
                                                               // the file's listed s
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    } // end initUI

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
    } // end paintComponent

    private void drawMap(Graphics g) {

        Graphics2D g2d = (Graphics2D) g; // graphics object
        
        
        double convertToRGB = 0.0;       // for doing decimal math for the RGB values 
        int currentRGB = 0;              // for stating the RGB value as a decimal value
        
        for(int j = 0; j < windowWidth; j++) { 
        	for(int i = 0; i < windowHeight; i++) {
        		// formula for finding the RGB values:
        		//       [[  current location - lowest value in data  ]]
        		// 255 * [  -----------------------------------------  ]
        		//       [[       highest value - lowest value        ]]
        		convertToRGB = ((double)(mapData[i][j] - lowestPoint) / (highestPoint - lowestPoint));
        		convertToRGB *= 255;
        		
        		// round that RBG value and make it an int
        		convertToRGB = Math.round(convertToRGB);
        		currentRGB = (int) convertToRGB;
        		
        		// set color and draw the pixels of the map
        		g2d.setColor(new Color(currentRGB, currentRGB, currentRGB));
        		g2d.drawRect(j, i, 1, 1);
        	} // end secondary for
        } // end for
        
        // save elevation changes from the drawing function's
        // math, which is about to happen
        int[] elevationChanges = new int[windowHeight];
        
        for(int i = 0; i < windowHeight; i++) {
        	// draw the shortest elevation paths
        	elevationChanges[i] = drawLowestElevPath(g2d, i, false);
        } // end for
        
        // find the lowest elevation path and make the path green
        int lowestRow = indexOfLowestElevPath(g2d, elevationChanges);
        System.out.printf("Lowest row at %d%n", lowestRow);
        
       
    } // end drawMap
    
    int drawLowestElevPath(Graphics g, int startingRow, boolean drawGreen) {
    	
    	int totalElevChange = 0; // result to be returned
    	
    	// starting position: [startingRow][0]
    	int currentRow = startingRow;
    	
    	// values for the locations assuming
    	// the top of the rendered map is
    	// pointing northward
    	int northEast = 0; // to the right and up one row
    	int east = 0;      // to the right and no change to row
    	int southEast = 0; // to the right and down one row
    	
    	Random rand = new Random(); // for if a random number needs to be drawn
    	int coinFlip = 0;           // the number that holds the random number
    	
    	// if the line needs to be green for indexOfLowestElevPath,
    	// then set it to green, but otherwise, draw in red
    	if(drawGreen == true) {
    		g.setColor(Color.GREEN);
    	} // end if
    	else {
    		g.setColor(Color.RED);
    	} // end else
    	
    	
    	for(int j = 0; j < windowWidth; j++) {
    		
    		g.fillRect(j, currentRow, 1, 1); // draw the current location
    		
    		// if at the eastern end of the map,
    		// stop immediately
    		if(j == (windowWidth - 1)) {
    			break;
    		} // end if
    		
    		// decide where to move next
    		if(currentRow == 0) {
    			// set distances to absolute value of the map data
    			// for the next locations
    			
    			// cannot go north-east from the top of
    			// the map - you'll fall off! (or get an
    			// array out of bounds error)
    			east = Math.abs(mapData[currentRow][j + 1] - mapData[currentRow][j]);
    			southEast = Math.abs(mapData[currentRow + 1][j + 1] - mapData[currentRow][j]);
    			
    			if(east == southEast) {
    				coinFlip = rand.nextInt(2);
    				
    				if(coinFlip != 1) { // coin flip of 1 = down one row
    					currentRow++;                 // go visually down one row
    					totalElevChange += southEast; // add to the elevation
    				} // end if
    				else {
    					totalElevChange += east;      // add to elevation without changing direction
    				} // end else
    			} // end if
    			
    			else if(southEast < east) {
    				currentRow++;                 // go visually down one row
    				totalElevChange += southEast; // add to elevation
    			} // end else if
    			else { // east < southEast
    				totalElevChange += east;     // add to elevation without changing direction
    			} // end else
    		} // end if currentRow == 0
    		
    		else if(currentRow == (windowHeight - 1)) { // at the bottom edge of map
    			// except for going to northEast, most of the methods
    			// I used to move on the map have already appeared
    			// and are annotated with comments between lines 253 and 282
				
				northEast = Math.abs(mapData[currentRow - 1][j + 1] - mapData[currentRow][j]);
				east = Math.abs(mapData[currentRow][j + 1] - mapData[currentRow][j]);
				// you'll fall off the map again
				// if you go south east!
				
				if(east == northEast) {
					coinFlip = rand.nextInt(2);
					
					if(coinFlip != 1) { // go north east
						currentRow--;                 // go up visually one row
						totalElevChange += northEast; // add to elevation
					} // end if
					else {
						totalElevChange += east;
					} // end else
				} // end if
				else if(northEast < east) {
					currentRow--;                 // go up visually one row
					totalElevChange += northEast; // add to elevation
				} // end else if
				else {
					totalElevChange += east;
				} // end else
				
				
			} // end if (bottom of map window)
    		
    		// else: somewhere in the middle,
    		// not touching any edge
    		else {
    			
    			northEast = Math.abs(mapData[currentRow - 1][j + 1] - mapData[currentRow][j]);
				east = Math.abs(mapData[currentRow][j + 1] - mapData[currentRow][j]);
				southEast = Math.abs(mapData[currentRow + 1][j + 1]- mapData[currentRow][j]);
				
				// everything is equal?
				if(northEast == east && east == southEast && southEast == northEast) {
					coinFlip = rand.nextInt(3);
					
					if(coinFlip == 2) { // CF of 2: go down
	    				currentRow++;
	    				totalElevChange += southEast;
					} // end if
					else if(coinFlip == 1) { // CF of 1: go up
						currentRow--;
						totalElevChange += northEast;
					} // end if
					else { // CF of 0: keep straight
						totalElevChange += east;
					} // end else
				} // end if - all three values match
				
				// northeast and east are equal?
				else if(northEast == east) {
					coinFlip = rand.nextInt(2);
					
					if(coinFlip != 1) { // go up one row
						currentRow--;
						totalElevChange += northEast;
					} // end if
					else { // remain straight
						totalElevChange += east;
					} // end else
					
				} // end if - NE and E match
				
				// east and southeast match?
				else if(east == southEast) {
					coinFlip = rand.nextInt(2);
					
					if(coinFlip != 1) { // go up 
						currentRow++;
						totalElevChange += southEast;
					} // end if
					else {
						totalElevChange += east;
					} // end else
				} // end else if (E = SE)
				
				// southeast = northeast?
				else if(southEast == northEast) {
					coinFlip = rand.nextInt(2);
					
					if(coinFlip != 0) {
						currentRow++;
						totalElevChange += southEast;
					} // end if
					else {
						currentRow--;
						totalElevChange += northEast;
					} // end else
				} // end else if (SE = NE)
				
				// everything is different?
				else {
					
					if(northEast < east && northEast < southEast) { // northeast is lowest
						currentRow--;
						totalElevChange += northEast;
					} // end if
					else if(southEast < east && southEast < northEast) { // southeast is lowest
						currentRow++;
						totalElevChange += southEast;
					} // end else if
					else { // east is lowest
						totalElevChange += east;
					} // end else
					
				} // end else (everything is different)
    			
    		} // end else (in middle)
    		
    	} // end for
    	
    	
    	return totalElevChange;
    } // end drawLowestElevPath
    
    // find lowest elevation path and draw it green
    public int indexOfLowestElevPath(Graphics g, int[] changes) {
    	int indexOflowestRow = 0;                 // holds the lowest array index
    	int valueOfLowestRow = Integer.MAX_VALUE; // holds that index's value as a lowest of the low
    	for(int i = 0; i < windowHeight; i++) {
    		if(changes[i] < valueOfLowestRow) {
    			indexOflowestRow = i;          // save the index along with
    			valueOfLowestRow = changes[i]; // the value at that index
    		} // end if
    	} // end for
    	
    	// draw the index's path as green
    	drawLowestElevPath(g, indexOflowestRow, true);
    	
    	return indexOflowestRow; // give back the lowest row of the map
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() { 
                Drawer ex = new Drawer(args[0]); // get all the items drawn
                ex.setVisible(true);             // make it visible to the whole world
            }
        });
    } // end main
} // end class
