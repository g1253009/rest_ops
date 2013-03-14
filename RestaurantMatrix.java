import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.Date;
import java.util.Random;
import java.applet.*;

public class RestaurantMatrix extends Applet
{	
	// Initialise game parameters.
	final int size = 35;
	int table_interval;
	int table_number;
	int total_dishes;
	double lambda = 0.07;
	int game_length = 60;
	
	// Initialise arrays for storing game data.
	boolean[] order_status = new boolean[size];
	double[] dish_time = new double[size];
	int[] score_board = new int[25];
	Date[] order_start_time = new Date[4];
	boolean[] scoreboard_status = new boolean[4];
	int[] preparation_time = new int[4];	
	
	// Initialise the buttons and TextFields.
	Button[] buttons = new Button[16];
	TextField[] textfields = new TextField[80];
	TextField scoretime, scoredispersion, scoretemp, scoretotal;
	
	/*
	 * Returns a Poisson-distributed random variable.
	 * @return double
	 */
	double exp()
	{
		double val = -Math.log(Math.random()) / lambda;
		return val;
	}
	
	/*
	 * Initialises the game screen and controls the main gameplay routine.
	 * @return void
	 */
	public void init()
	{
		// Number of possible dishes.
		int restaurant_size = 16;

		// Preparation time for each dish
		preparation_time[0] = 2;
		preparation_time[1] = 5;
		preparation_time[2] = 10;
		preparation_time[3] = 15;
		
		// The table to start with.
		table_number = 1;
		
		// The interval at which orders come in.
		table_interval = 7;
		
		// Set up GridLayout display area.
		resize(900,600);
		int rows = 0;
		int columns = 6;
		setLayout(new GridLayout(rows,columns,5,5)); 
		setFont(new Font("Calibri", Font.BOLD, 10));
		setBackground(new Color(85,153,187));
		
		// Add playgame button and actionListeners to initiate gameplay.
		final Button playgame = new Button("CLICK to PLAY");
		add(playgame);
		
		// Set header rows - play game and scoreboard.
		scoretime = new TextField("SCORE - Time: TBC");
		add(scoretime);
		scoretime.setBackground(Color.yellow);
		
		scoredispersion = new TextField("SCORE - Dispersion: TBC");
		add(scoredispersion);
		scoredispersion.setBackground(Color.yellow);
		
		scoretemp = new TextField("SCORE - Temperature: TBC");
		add(scoretemp);
		scoretemp.setBackground(Color.yellow);
		
		scoretotal = new TextField("SCORE Score: TBC");
		add(scoretotal);
		scoretotal.setBackground(Color.yellow);
		
		TextField computerscore = new TextField("COMPUTER SCORE: TBC");
		add(computerscore);
		computerscore.setBackground(Color.yellow);
		 
		// Set matrix headings
		TextField menuoptions = new TextField("MENU OPTIONS");
		add(menuoptions);
		menuoptions.setBackground(Color.GRAY);

		TextField expecteddtime = new TextField("EXPECTED TIME");
		add(expecteddtime);
		expecteddtime.setBackground(Color.GRAY);
		 
		TextField showorder = new TextField("SHOW ORDER");
		add(showorder);
		showorder.setBackground(Color.GRAY);
		 
		TextField preparefood = new TextField("PREPARE FOOD?");
		add(preparefood);
		preparefood.setBackground(Color.GRAY);
		 
		TextField foodstatus = new TextField("DISH STATUS");
		add(foodstatus);
		foodstatus.setBackground(Color.GRAY);
		 
		TextField customerscore = new TextField("CUSTOMER SCORE");
		add(customerscore);
		customerscore.setBackground(Color.GRAY);
		 
		/* Loop to set-up 4 rows for each of 4 tables in the standard format
		 * using TextFields and a Button to initiate preparation of the dish.  
		 * The TextFields and Buttons are stored in their respective arrays.
		 */
		int table_setup = 0;
		String which_dish = new String();
		int exp_time = 0;
		int text_field = 0;
		
		for (int i = 0; i < restaurant_size; i++)
		{	 
			 if (i == 0 || i%4 == 0){which_dish = "Soup"; exp_time = preparation_time[0]; table_setup++;}
			 else if (i == 1 || i == 5 || i == 9 || i == 13){which_dish = "Steak"; exp_time = preparation_time[1];}
			 else if (i == 2 || (i%2 == 0 && i%4 != 0) ){which_dish = "Burger"; exp_time = preparation_time[2];}
			 else {which_dish = "Fish"; exp_time = preparation_time[3];}
			 
			 textfields[text_field] = new TextField("Table " + table_setup + " - " + which_dish); add(textfields[text_field]);
			 textfields[text_field+1] = new TextField(exp_time + " seconds"); add(textfields[text_field+1]);
			 textfields[text_field+2] = new TextField("0"); add(textfields[text_field+2]);
			 buttons[i] = new Button("CLICK to prepare"); add(buttons[i]);
			 textfields[text_field+3] = new TextField("Prepared/Ready"); add(textfields[text_field+3]);
			 textfields[text_field+4] = new TextField("SCORE: TBC"); add(textfields[text_field+4]);
			 text_field +=5;
		 }
		 
		playgame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				playgame.setBackground(Color.GREEN);
		 
				/*
				 * Gets the game started by creating the first order and recording its time of creation. 
				 */
				ActionListener taskPerformer = new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						create_order(table_number);
						
		            	order_start_time[table_number-1] = new Date();
		            	
						//computer_cook(table_number+1);
					}
				};
	      
				final Timer timer = new Timer( (int) (1000) , taskPerformer);
				timer.setRepeats(false);
				timer.start();
				try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();} 
	        
				/* 
				 * ActionListener to control order generation for each table. Timer repeat produces a loop through each table
				 * calling the 'create_order' function to generate the order.
				 */
				ActionListener taskPerformer2 = new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						if (table_number < 4)
							table_number++;
						else
							table_number = 1;
	            
						create_order(table_number);
	            	
		            	order_start_time[table_number-1] = new Date();
					}
				};
	      
				final Timer timer2 = new Timer( (int) (1000*/*exp()*/ table_interval) , taskPerformer2);
				timer2.setRepeats(true);
				timer2.start();
				try {Thread.sleep(1000);} catch (InterruptedException e1) { e1.printStackTrace();}
      
				/* 
				 * ActionListener on repeat to check whether service has finished for each table and if so to 
				 * call 'score' and 'score_board' functions to calculate and update the table and game scoreboards.
				 */	
				ActionListener taskPerformer3 = new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						if (order_status[16] && order_status[17] && order_status[18] && order_status[19] && !scoreboard_status[0])
						{
							score(1);
							score_board(1);
						}
     	
						if (order_status[20] && order_status[21] && order_status[22] && order_status[23] && !scoreboard_status[1])
						{
							score(2);
							score_board(2);
						}
     	
						if (order_status[24] && order_status[25] && order_status[26] && order_status[27] && !scoreboard_status[2])
						{
							score(3);
							score_board(3);
						}

						if (order_status[28] && order_status[29] && order_status[30] && order_status[31] && !scoreboard_status[3])
						{
							score(4);
							score_board(4);
							
						}
					}
				};

					final Timer timer3 = new Timer( 50 , taskPerformer3);
					timer3.setRepeats(true);
					timer3.start();
					try {Thread.sleep(1000);} catch (InterruptedException e1) { e1.printStackTrace();}
	 
					/*
					 * Ends the game after a certain amount of time.
					 */
					ActionListener taskPerformer4 = new ActionListener()
					{
						public void actionPerformed(ActionEvent evt)
						{
							scoretotal.setText("Game Over!");
							scoretotal.setBackground(Color.blue);
							timer.setRepeats(false);
							timer2.setRepeats(false);
							timer3.setRepeats(false);
							timer.stop();
							timer2.stop();
							timer3.stop();
							
							for(int i = 0; i < 16; i++)
							{
								buttons[i].setEnabled(false);
							}
						}
					};

					Timer timer4 = new Timer((int)(1000) * game_length, taskPerformer4);
					timer4.setRepeats(false);
					timer4.start();
					try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			}
		});
 
		/*
		 * 'Prepare Dish' button actions. ActionListeners added to each button to initiate preparation of each dish
		 * when the button is pressed. This calls the 'prepare_button' method to update the display.
		 * The 'prepare_food' method returns the preparation time for this dish and drives a timer
		 * that initiates the embedded ActionListener to update the display, using the 'display_ready' method,
		 * after the allotted time. This introduces an element of real-time interaction to the game.
		 */
		 buttons[0].addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent e)
			 {	 
				 prepare_button(0, 1);
				 	 
				 final double wait_time = prepare_food(1);
			 
				 ActionListener taskPerformer = new ActionListener()
				 {
					 public void actionPerformed(ActionEvent evt)
					 {       	
						 display_ready(0);   	
				     }
				 };
				      
				 Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
				 timer.setRepeats(false);
				 timer.start();
				 
				 try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}	 
			 }
		 });
		 
		 buttons[1].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 
				 prepare_button(1, 2);
				            
				 final double wait_time = prepare_food(2);
				 
				 ActionListener taskPerformer = new ActionListener() {
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	display_ready(1);
			            	
			            }};
			            
			        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
			        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			 
				 }
		 });
		 

		 buttons[2].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 
				 prepare_button(2, 3);
				 
				 final double wait_time = prepare_food(3);
				 
				  ActionListener taskPerformer = new ActionListener() {
			            public void actionPerformed(ActionEvent evt) {
			                
			            	display_ready(2);
			               
			            }};
			           
			        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
			        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			 }
			 });
		 
		 buttons[3].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){

				 prepare_button(3,4);
				
				 final double wait_time = prepare_food(4);
				 
				 ActionListener taskPerformer = new ActionListener() {
			          
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	display_ready(3);
			    
			            }};
			        
			        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
			        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
				  
				}	 
			 
		 });
		 

		buttons[4].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 
				 prepare_button(4,1);
				            	
					 final double wait_time = prepare_food(1);
			 
					 ActionListener taskPerformer = new ActionListener() {
				            public void actionPerformed(ActionEvent evt) {
				            	
				            	display_ready(4);
				            }};
				      
				        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
				        timer.setRepeats(false);
				        timer.start();
				        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
				 
				 }
			 
		});

		buttons[5].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				
				 prepare_button(5,2);
			
				 final double wait_time = prepare_food(2);
				 
				 ActionListener taskPerformer = new ActionListener() {
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	display_ready(5);
			     
			            }};
			            
			        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
			        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			 }
			 
		});


		buttons[6].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 
				 prepare_button(6,3);
			
				 final double wait_time = prepare_food(3);
				 
				  ActionListener taskPerformer = new ActionListener() {
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	display_ready(6);
			     	            
			            }};
			            
			        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
			        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			 }
			 
		});

		buttons[7].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){

				 
				 prepare_button(7,4);
			
				 final double wait_time = prepare_food(4);
				 
				 ActionListener taskPerformer = new ActionListener() {
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	display_ready(7);
			            	
			            }};
			        
			        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
			        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
				
			 }
		});

		buttons[8].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 
				 
				 prepare_button(8,1);

				 final double wait_time = prepare_food(1);
			 
					 ActionListener taskPerformer = new ActionListener() {
				            public void actionPerformed(ActionEvent evt) {
				 
				            	
				            	display_ready(8);
				 
				            }};
				      
				        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
				        timer.setRepeats(false);
				        timer.start();
				        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
				 
				 }
		});

		buttons[9].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 
				 prepare_button(9,2);
				 
				
				 final double wait_time = prepare_food(2);
				 
				 ActionListener taskPerformer = new ActionListener() {
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	display_ready(9);
			             	
			            }};
			           
			        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
			        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			 }
			 
		});


		buttons[10].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 
				 prepare_button(10,3);
				 	 
				  final double wait_time = prepare_food(3);
				 
				  ActionListener taskPerformer = new ActionListener() {
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	display_ready(10);
			            }};
			   
			        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
			        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			 }
			 
		});

		buttons[11].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){

				 prepare_button(11,4);
			
				 final double wait_time = prepare_food(4);
				 
				 ActionListener taskPerformer = new ActionListener() {
			          
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	display_ready(11);
			            	
			            }};
			        
			        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
			        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
				  
				}	 
			
		});


		buttons[12].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				
				 prepare_button(12,1);
				            	
					 final double wait_time = prepare_food(1);
			 
					 ActionListener taskPerformer = new ActionListener() {
				            public void actionPerformed(ActionEvent evt) {
				            	
				            	display_ready(12);
				            	
				            }};
				      
				        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
				        timer.setRepeats(false);
				        timer.start();
				        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
				 
				 }

		});

		buttons[13].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 
				 prepare_button(13,2);
				 	            	
				 final double wait_time = prepare_food(2);
				 
				 ActionListener taskPerformer = new ActionListener() {
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	display_ready(13);
			            
			            }};
			            
			        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
			        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			 }
		});


		buttons[14].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 
				 prepare_button(14,3);
				 
				  final double wait_time = prepare_food(3);
				 
				  ActionListener taskPerformer = new ActionListener() {
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	
			            	display_ready(14);
			            	
			            }};
			         
			          
			        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
			        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			 }
			 
		});

		buttons[15].addActionListener(new ActionListener()
		{
			 public void actionPerformed(ActionEvent e)
			 {
				 prepare_button(15,4);
			
				 final double wait_time = prepare_food(4);
				 
				 ActionListener taskPerformer = new ActionListener()
				 {     
					 public void actionPerformed(ActionEvent evt)
					 {   	
						 display_ready(15);	
					 }
				 };
			        
			     Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			     timer.setRepeats(false);
			     timer.start();
			     
			     try
			     {
			    	 Thread.sleep(1000);
			     }
			     catch (InterruptedException e1)
			     {
			    	 e1.printStackTrace();
			     }
			}
		});	 
	}
	
	/*
	 * Generates a random order for the head chef (player) to introduce delays for.
	 * Each dish can be ordered only once.
	 * @return void
	 */
	void create_order(int order_number)
	{
		double value = random_var();
		
		// Select one dish:
		if(value >= 0 && value < 0.25)
		{
			double val1 = random_var();
			
			if(val1 >= 0 && val1 < 0.25)
				order_status[order_number*4-4] = true;
			else if(val1 >= 0.25 && val1 < 0.50)
				order_status[order_number*4-3] = true;
			else if(val1 >= 0.50 && val1 < 0.75)
				order_status[order_number*4-2] = true;
			else
				order_status[order_number*4-1] = true;
		}

		// Select two dishes:
		else if(value >= 0.25 && value < 0.50)
		{
			double val2 = random_var();
			
			// select the first dish:
			if(val2 >= 0 && val2 < 0.25)
			{
				order_status[order_number*4-4] = true;
				
				double val2a = random_var();
				
				// select the second dish:
				if(val2a >= 0 && val2a < 0.33)
					order_status[order_number*4-3] = true;
				else if(val2a >= 0.33 && val2a < 0.66)
					order_status[order_number*4-2] = true;
				else
					order_status[order_number*4-1] = true;
			}
			else if(val2 >= 0.25 && val2 < 0.50)
			{
				order_status[order_number*4-3] = true;
				
				double val2b = random_var();
				
				if(val2b >= 0 && val2b < 0.33)
					order_status[order_number*4-4] = true;
				else if(val2b >= 0.33 && val2b < 0.66)
					order_status[order_number*4-2] = true;
				else
					order_status[order_number*4-1] = true;
			}
			else if(val2 >= 0.50 && val2 < 0.75)
			{
				order_status[order_number*4-2] = true;
			
				double val2c = random_var();
				
				if(val2c >= 0 && val2c < 0.33)
					order_status[order_number*4-4] = true;
				else if(val2c >= 0.33 && val2c < 0.66)
					order_status[order_number*4-3] = true;
				else
					order_status[order_number*4-1] = true;
			}
			else
			{
				order_status[order_number*4-1] = true;
			
				double val2d = random_var();
				
				if(val2d >= 0 && val2d < 0.33)
					order_status[order_number*4-4] = true;
				else if(val2d >= 0.33 && val2d < 0.66)
					order_status[order_number*4-3] = true;
				else
					order_status[order_number*4-2] = true;
			}
		}
		
		// Select three dishes:
		else if(value >= 0.50 && value < 0.75)
		{
			double val3 = random_var();
			
			// select the first dish:
			if(val3 >= 0 && val3 < 0.25)
			{
				order_status[order_number*4-4] = true;
				
				double val3a = random_var();
				
				// select the second dish:
				if(val3a >= 0 && val3a < 0.33)
				{
					order_status[order_number*4-3] = true;
				
					double val3aa = random_var();
					
					// select the third dish:
					if(val3aa >= 0 && val3aa < 0.50)
						order_status[order_number*4-2] = true;
					else
						order_status[order_number*4-1] = true;
				}
				else if(val3a >= 0.33 && val3a < 0.66)
				{
					order_status[order_number*4-2] = true;
					
					double val3ab = random_var();
					
					if(val3ab >= 0 && val3ab < 0.50)
						order_status[order_number*4-3] = true;
					else
						order_status[order_number*4-1] = true;
				}
				else
				{
					order_status[order_number*4-1] = true;
					
					double val3ac = random_var();
					
					if(val3ac >= 0 && val3ac < 0.50)
						order_status[order_number*4-3] = true;
					else
						order_status[order_number*4-2] = true;
				}
			}
			else if(val3 >= 0.25 && val3 < 0.50)
			{
				order_status[order_number*4-3] = true;
				
				double val3b = random_var();
				
				if(val3b >= 0 && val3b < 0.33)
				{
					order_status[order_number*4-4] = true;
				
					double val3ba = random_var();
					
					if(val3ba >= 0 && val3ba < 0.50)
						order_status[order_number*4-2] = true;
					else
						order_status[order_number*4-1] = true;
				}
				else if(val3b >= 0.33 && val3b < 0.66)
				{
					order_status[order_number*4-2] = true;
					
					double val3bb = random_var();
					
					if(val3bb >= 0 && val3bb < 0.50)
						order_status[order_number*4-4] = true;
					else
						order_status[order_number*4-1] = true;
				}
				else
				{
					order_status[order_number*4-1] = true;
					
					double val3bc = random_var();
					
					if(val3bc >= 0 && val3bc < 0.50)
						order_status[order_number*4-4] = true;
					else
						order_status[order_number*4-2] = true;
				}
			}
			else if(val3 >= 0.50 && val3 < 0.75)
			{
				order_status[order_number*4-2] = true;
			
				double val3c = random_var();
				
				if(val3c >= 0 && val3c < 0.33)
				{
					order_status[order_number*4-4] = true;
					
					double val3ca = random_var();
					
					if(val3ca >= 0 && val3ca < 0.50)
						order_status[order_number*4-3] = true;
					else
						order_status[order_number*4-1] = true;
				}	
				else if(val3c >= 0.33 && val3c < 0.66)
				{
					order_status[order_number*4-3] = true;
					
					double val3cb = random_var();
					
					if(val3cb >= 0 && val3cb < 0.50)
						order_status[order_number*4-4] = true;
					else
						order_status[order_number*4-1] = true;
				}
				else
				{
					order_status[order_number*4-1] = true;
					
					double val3cc = random_var();
					
					if(val3cc >= 0 && val3cc < 0.50)
						order_status[order_number*4-4] = true;
					else
						order_status[order_number*4-3] = true;
				}
			}
			else
			{
				order_status[order_number*4-1] = true;
			
				double val3d = random_var();
				
				if(val3d >= 0 && val3d < 0.33)
				{
					order_status[order_number*4-4] = true;
					
					double val3da = random_var();
					
					if(val3da >= 0 && val3da < 0.50)
						order_status[order_number*4-3] = true;
					else
						order_status[order_number*4-2] = true;
				}
				else if(val3d >= 0.33 && val3d < 0.66)
				{
					order_status[order_number*4-3] = true;
					
					double val3db = random_var();
					
					if(val3db >= 0 && val3db < 0.50)
						order_status[order_number*4-4] = true;
					else
						order_status[order_number*4-2] = true;
				}
				else
				{
					order_status[order_number*4-2] = true;
					
					double val3dc = random_var();
					
					if(val3dc >= 0 && val3dc < 0.50)
						order_status[order_number*4-4] = true;
					else
						order_status[order_number*4-3] = true;
				}
			}
		}
		
		// Select four dishes:
		else
		{
			order_status[order_number*4-4] = true;
			order_status[order_number*4-3] = true;
			order_status[order_number*4-2] = true;
			order_status[order_number*4-1] = true;
		}
		
		// Set scoreboard status for this table to false to allow updating once table service is completed.
		scoreboard_status[order_number-1] = false;
		
		// Update the display to show the order.
		if(order_status[order_number*4-4])
		{
			textfields[order_number*20-18].setText("Items ordered: 1");
			textfields[order_number*20-18].setBackground(Color.GREEN);
			textfields[order_number*20-17].setText("Prepared/Ready");
			textfields[order_number*20-17].setBackground(Color.white);
		} 
		else
		{
			textfields[order_number*20-18].setText("Items ordered: 0");
			order_status[16+order_number*4-4] = true;
		}
			
		if(order_status[order_number*4-3])
		{
			textfields[order_number*20-13].setText("Items ordered: 1");
			textfields[order_number*20-13].setBackground(Color.GREEN);
			textfields[order_number*20-12].setText("Prepared/Ready");
			textfields[order_number*20-12].setBackground(Color.white);}
		else
		{
			textfields[order_number*20-13].setText("Items ordered: 0");
			order_status[16+order_number*4-3] = true;
		}
			
		if(order_status[order_number*4-2])
		{
			textfields[order_number*20-8].setText("Items ordered: 1");
			textfields[order_number*20-8].setBackground(Color.GREEN);
			textfields[order_number*20-7].setText("Prepared/Ready");
			textfields[order_number*20-7].setBackground(Color.white);
		}
		else
		{
			textfields[order_number*20-8].setText("Items ordered: 0");
			order_status[16+order_number*4-2] = true;
		}
			
		if(order_status[order_number*4-1])
		{
			textfields[order_number*20-3].setText("Items ordered: 1");
			textfields[order_number*20-3].setBackground(Color.GREEN);
			textfields[order_number*20-2].setText("Prepared/Ready");
			textfields[order_number*20-2].setBackground(Color.white);}
		else
		{
			textfields[order_number*20-3].setText("Items ordered: 0");
			order_status[16+order_number*4-1] = true;
		}
	}

	/*
	 * Produces a normal random variable between -2.14 and 2.14 for the dish delays.
	 * @return double
	 */
	public double delay_Norm_Dist()
	{
		double PI = 3.141592654;
        double x,y;
        double z,z1,z2;
        
        x = Math.random() * ( 99 - 0 );
        y = Math.random() * (99 - 0);
        
        while(x == 0)
            x = Math.random() * ( 99 - 0 );
        while(y == 0)
            y = Math.random() * ( 99 - 0 );
        
        x /= 100;
        y /= 100;
        
        z1 = Math.sqrt(-2*Math.log(x))*Math.cos(2*PI*y);
        z2 = Math.sqrt(-2*Math.log(x))*Math.sin(2*PI*y);
        z = (z1+z2) / 2;
        
        return Math.abs(z);
	}
	
	/*
	 * Adds a random number to the expected preparation time.
	 * @return double
	 */
	double prepare_food(int dish_number)
	{
		//double delay = norm_random_var();
		double delay = delay_Norm_Dist();
	
		return (preparation_time[dish_number - 1] + delay);
	}

	/*
	 * Can also use this for the delay.
	 * Uses the built-in nextGaussian method.
	 * @return double
	 */
	public static double norm_random_var()
	{
		Random generator = new Random();
	
		return(generator.nextGaussian());
	}

	/*
	 * Returns a random variable between 0 and 1.0.
	 * @return double
	 */
	public static double random_var()
	{
		Random generator = new Random();
	
		return(generator.nextDouble());
	}

	/*
	 * Calculates the score.
	 * @return void
	 */
	void score(double table)
	{
		double total_time, quickest, dispersion, temperature,
		score_time, score_dispersion, score_temp, score_total;
	
		total_time = quickest = dispersion = temperature = 
		score_time = score_dispersion = score_temp = score_total = 0;
		
		total_time = dish_time[(int) (table*4-4)];
		quickest = dish_time[(int) (table*4-4)];
		
		for (int i = (int) (table*4-3); i < (int) (table*4); i++)
		{
			if (dish_time[i] > total_time)
				total_time = dish_time[i];
			if (dish_time[i] < quickest  && dish_time[i] > 0)
				quickest = dish_time[i];
		}
		
		dispersion = total_time - quickest;
		temperature = dispersion;
		
		if (total_time < 10) score_time = 100;
		else score_time = 100 - 10*(total_time - 10);
		if (score_time < 0) score_time = 0;
		
		if (dispersion < 1) score_dispersion = 100;
		else score_dispersion = 100 - 20*(dispersion);
		if (score_dispersion < 0) score_dispersion = 0;
		
		score_temp = score_dispersion;
		score_total = (score_time + score_dispersion + score_temp)/3;
		
		score_board[(int) (table*4-3)] = (int) score_time;
		score_board[(int) (table*4-2)] = (int) score_dispersion;
		score_board[(int) (table*4-1)] = (int) score_temp;
		score_board[(int) (table*4)] = (int) score_total;
		
		textfields[(int) (table*20-16)].setText("SCORE - Time: " + (int) score_time + "%");
		textfields[(int) (table*20-11)].setText("SCORE - Dispersion: " + (int) score_dispersion + "%");
		textfields[(int) (table*20-6)].setText("SCORE - Temperature: " + (int) score_temp + "%");
		textfields[(int) (table*20-1)].setText("SCORE - TOTAL: " + (int) score_total + "%");
		
		if (score_time <= 40) textfields[(int) (table*20-16)].setBackground(Color.red);
        else if (score_time > 40 && score_time <= 70) textfields[(int) (table*20-16)].setBackground(Color.orange);
        else if (score_time > 70) textfields[(int) (table*20-16)].setBackground(Color.green);
		
		if (score_dispersion <= 40) textfields[(int) (table*20-11)].setBackground(Color.red);
        else if (score_dispersion > 40 && score_dispersion <= 70) textfields[(int) (table*20-11)].setBackground(Color.orange);
        else if (score_dispersion > 70) textfields[(int) (table*20-11)].setBackground(Color.green);
		
		if (score_temp <= 40) textfields[(int) (table*20-6)].setBackground(Color.red);
        else if (score_temp > 40 && score_temp <= 70) textfields[(int) (table*20-6)].setBackground(Color.orange);
        else if (score_temp > 70) textfields[(int) (table*20-6)].setBackground(Color.green);
		
		if (score_total <= 40) textfields[(int) (table*20-1)].setBackground(Color.red);
        else if (score_total > 40 && score_total <= 70) textfields[(int) (table*20-1)].setBackground(Color.orange);
        else if (score_total > 70) textfields[(int) (table*20-1)].setBackground(Color.green);
	}

	/*
	 * Create the scoreboard.
	 * @return void
	 */
	void score_board(int table_number)
	{
		int array_start = table_number*4-4;
		int dish_count = 0;

		for (int i = array_start; i < array_start + 5; i++) if (order_status[i]) dish_count++;
	
		score_board[table_number*4-4] = dish_count;
	
		score_board[20] +=score_board[table_number*4-4];
		score_board[21] +=score_board[table_number*4-3];
		score_board[22] +=score_board[table_number*4-2];
		score_board[23] +=score_board[table_number*4-1];
		score_board[24] +=score_board[table_number*4];
	
		scoreboard_status[table_number-1] = true;
	
		update_scoreboard(table_number);
	}

	/*
	 * Update the scoreboard.
	 * @return void
	 */
	void update_scoreboard(int table_number)
	{	
		if (score_board[20] == 0) score_board[20] = 1;
	
		int scoretime2 = score_board[21]/score_board[20];
		int scoredispersion2 =  score_board[22]/score_board[20];
		int scoretemp2 = score_board[23]/score_board[20];
		int scoretotal2 = score_board[24]/score_board[20];
	
		scoretime.setText("SCORE - Time: " + scoretime2 + "%");
		scoredispersion.setText("SCORE - Dispersion: " + scoredispersion2 + "%");
		scoretemp.setText("SCORE - Temperature: " + scoretemp2 + "%");
		scoretotal.setText("SCORE - TOTAL: " + scoretotal2 + "%");
	
		if (scoretime2 <= 40) scoretime.setBackground(Color.red);
		else if (scoretime2 > 40 && scoretime2 <= 70) scoretime.setBackground(Color.orange);
		else if (scoretime2 > 70) scoretime.setBackground(Color.green);
	
		if (scoredispersion2 <= 40) scoredispersion.setBackground(Color.red);
		else if (scoredispersion2 > 40 && scoredispersion2 <= 70) scoredispersion.setBackground(Color.orange);
		else if (scoredispersion2 > 70) scoredispersion.setBackground(Color.green);
	
		if (scoretemp2 <= 40) scoretemp.setBackground(Color.red);
		else if (scoretemp2 > 40 && scoretemp2 <= 70) scoretemp.setBackground(Color.orange);
		else if (scoretemp2 > 70) scoretemp.setBackground(Color.green);
	
		if (scoretotal2 <= 40) scoretotal.setBackground(Color.red);
		else if (scoretotal2 > 40 && scoretotal2 <= 70) scoretotal.setBackground(Color.orange);
		else if (scoretotal2 > 70) scoretotal.setBackground(Color.green);
	}
	
	
	/* 
	 * Function to update the display for a dish being prepared.  Initiated by the 'Press to prepare' button.
	 * The function also updates the order-status flag of the dish
	 */
	void prepare_button(final int dish_number, int dish)
	{
		if(order_status[dish_number])
		{
			textfields[dish_number*5+3].setText("Being Prepared");
			textfields[dish_number*5+3].setBackground(Color.GREEN);
			order_status[dish_number] = false;
			order_status[dish_number+16] = false;
			textfields[dish_number*5+2].setText("Items waiting: 0");
			textfields[dish_number*5+2].setBackground(Color.white);
		}
	}

	/* 
	 * Function to update the display when a dish has been prepared.
	 * The function also updates the order_status flag of the dish.
	 */
	void display_ready(int dish_number)
	{	
		// Calculate the actual preparation time for the dish.
		int table;
		
		if (dish_number >= 0 && dish_number <= 3) table = 1;
		else if (dish_number >= 4 && dish_number <= 7) table = 2;
		else if (dish_number >= 8 && dish_number <= 11) table = 3;
		else table = 4;    
	    
		Date date = new Date();
		dish_time[dish_number] = (date.getTime() - order_start_time[table].getTime())/1000;
		
		// Update the display.
		textfields[dish_number*5+3].setText("Dish is ready");
		textfields[dish_number*5+3].setBackground(Color.gray);
		order_status[dish_number+16] = true;
	}
}
