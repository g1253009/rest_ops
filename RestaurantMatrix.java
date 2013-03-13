import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.Date;
import java.util.Random;
import java.applet.*;

/*import java.io.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.lang.*;
import java.security.Timestamp;*/

public class RestaurantMatrix extends Applet
{
	final Table table1 = new Table(1);
	final Table table2 = new Table(2);
	final Table table3 = new Table(3);
	final Table table4 = new Table(4);
	
	TextField scoretime, scoredispersion, scoretemp, scoretotal;

	final int size = 35;
	int table_interval;
	int table_number;
	int total_dishes;
	
	boolean[] order_status = new boolean[size];
	double[] dish_time = new double[size];
	int[] score_board = new int[25];
	//Date[] order_start_time = new Date[4];
	boolean[] scoreboard_status = new boolean[4];
	
	java.util.Date[] date_table = new java.util.Date[4]; // order_start_time
	
	Button[] buttons = new Button[16];
	TextField[] textfields = new TextField[80];

	int dish1_time = 2;
	int dish2_time = 5;
	int dish3_time = 10;
	int dish4_time = 15;
	
	public void init()
	{
		table_number = 1;
		table_interval = 7;
		int restaurant_size = 16;
	
		// Set up grid layout display area
		resize(900,600);
  
		int rows = 0;
		int columns = 6;
		setLayout(new GridLayout(rows,columns,5,5)); 
		setFont(new Font("Calibri", Font.BOLD, 10));
		setBackground(new Color(85,153,187)); 
  
		 /*Add playgame button and add actionListener to initiate gameplay*/
		final Button playgame = new Button("CLICK to PLAY");
		add(playgame);
		
		//SET Header rows - play game and scoreboard
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
		 
		 //Matrix headings
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
		 
		 //SET-UP TABLE 1
		 //Line entries - dish - 1 - Table 1
		 /*add(new TextField("Table 1 - Soup"));
		 add(new TextField("2 seconds"));
		 table1.dishshoworder[0] = new TextField("0");
		 add(table1.dishshoworder[0]);
		 final Button preparedish1 = new Button("CLICK to prepare");
		 add(preparedish1);
		 table1.dishstatus[0] = new TextField("Prepared/Ready");
		 add(table1.dishstatus[0]);
		 table1.scoredish[0] = new TextField("SCORE - Time: TBC");
		 add(table1.scoredish[0]);
		 
		 //Line entries - dish - 2 - Table 1
		 add(new TextField("Table 1 - Steak"));
		 add(new TextField("5 seconds"));
		 table1.dishshoworder[1] = new TextField("0");
		 add(table1.dishshoworder[1]);
		 final Button preparedish2 = new Button("CLICK to prepare");
		 add(preparedish2);
		 table1.dishstatus[1] = new TextField("Prepared/Ready");
		 add(table1.dishstatus[1]);
		 table1.scoredish[1] = new TextField("SCORE - Dispersion: TBC");
		 add(table1.scoredish[1]);
		 
		//Line entries - dish - 3 - Table 1
		add(new TextField("Table 1 - Burger"));
		add(new TextField("10 seconds"));
		table1.dishshoworder[2] = new TextField("0");
		add(table1.dishshoworder[2]);
		final Button preparedish3 = new Button("CLICK to prepare");
		add(preparedish3);
		table1.dishstatus[2] = new TextField("Prepared/Ready");
		add(table1.dishstatus[2]);
		table1.scoredish[2] = new TextField("SCORE - Temperature: TBC");
		add(table1.scoredish[2]); 

		//Line entries - dish - 4 - Table 1
		add(new TextField("Table 1 - Fish"));
		add(new TextField("15 seconds"));
		table1.dishshoworder[3] = new TextField("0");
		add(table1.dishshoworder[3]);
		final Button preparedish4 = new Button("CLICK to prepare");
		add(preparedish4);
		table1.dishstatus[3] = new TextField("Prepared/Ready");
		add(table1.dishstatus[3]);
		table1.scoredish[3] = new TextField("SCORE - Total: TBC");
		add(table1.scoredish[3]);

		//SET-UP TABLE 2
		//Line entries - dish - 1 - table 2
		add(new TextField("Table 2 - Soup"));
		add(new TextField("2 seconds"));
		table2.dishshoworder[0] = new TextField("0");
		add(table2.dishshoworder[0]);
		final Button table2_preparedish1 = new Button("CLICK to prepare");
		add(table2_preparedish1);
		table2.dishstatus[0] = new TextField("Prepared/Ready");
		add(table2.dishstatus[0]);
		table2.scoredish[0] = new TextField("SCORE - Time: TBC");
		add(table2.scoredish[0]);

		//Line entries - dish - 2 - table 2
		add(new TextField("Table 2 - Steak"));
		add(new TextField("5 seconds"));
		table2.dishshoworder[1] = new TextField("0");
		add(table2.dishshoworder[1]);
		final Button table2_preparedish2 = new Button("CLICK to prepare");
		add(table2_preparedish2);
		table2.dishstatus[1] = new TextField("Prepared/Ready");
		add(table2.dishstatus[1]);
		table2.scoredish[1] = new TextField("SCORE - Dispersion: TBC");
		add(table2.scoredish[1]);

		//Line entries - dish - 3 - table 2
		add(new TextField("Table 2 - Burger"));
		add(new TextField("10 seconds"));
		table2.dishshoworder[2] = new TextField("0");
		add(table2.dishshoworder[2]);
		final Button table2_preparedish3 = new Button("CLICK to prepare");
		add(table2_preparedish3);
		table2.dishstatus[2] = new TextField("Prepared/Ready");
		add(table2.dishstatus[2]);
		table2.scoredish[2] = new TextField("SCORE - Temperature: TBC");
		add(table2.scoredish[2]); 

		//Line entries - dish - 4 - table 2
		add(new TextField("Table 2 - Fish"));
		add(new TextField("15 seconds"));
		table2.dishshoworder[3] = new TextField("0");
		add(table2.dishshoworder[3]);
		final Button table2_preparedish4 = new Button("CLICK to prepare");
		add(table2_preparedish4);
		table2.dishstatus[3] = new TextField("Prepared/Ready");
		add(table2.dishstatus[3]);
		table2.scoredish[3] = new TextField("SCORE - Total: TBC");
		add(table2.scoredish[3]);

		//SET-UP TABLE 3
		//Line entries - dish - 1 - table 3
		add(new TextField("Table 3 - Soup"));
		add(new TextField("2 seconds"));
		table3.dishshoworder[0] = new TextField("0");
		add(table3.dishshoworder[0]);
		final Button table3_preparedish1 = new Button("CLICK to prepare");
		add(table3_preparedish1);
		table3.dishstatus[0] = new TextField("Prepared/Ready");
		add(table3.dishstatus[0]);
		table3.scoredish[0] = new TextField("SCORE - Time: TBC");
		add(table3.scoredish[0]);

		//Line entries - dish - 2 - table 3
		add(new TextField("Table 3 - Steak"));
		add(new TextField("5 seconds"));
		table3.dishshoworder[1] = new TextField("0");
		add(table3.dishshoworder[1]);
		final Button table3_preparedish2 = new Button("CLICK to prepare");
		add(table3_preparedish2);
		table3.dishstatus[1] = new TextField("Prepared/Ready");
		add(table3.dishstatus[1]);
		table3.scoredish[1] = new TextField("SCORE - Dispersion: TBC");
		add(table3.scoredish[1]);

		//Line entries - dish - 3 - table 3
		add(new TextField("Table 3 - Burger"));
		add(new TextField("10 seconds"));
		table3.dishshoworder[2] = new TextField("0");
		add(table3.dishshoworder[2]);
		final Button table3_preparedish3 = new Button("CLICK to prepare");
		add(table3_preparedish3);
		table3.dishstatus[2] = new TextField("Prepared/Ready");
		add(table3.dishstatus[2]);
		table3.scoredish[2] = new TextField("SCORE - Temperature: TBC");
		add(table3.scoredish[2]); 

		//Line entries - dish - 4 - table 3
		add(new TextField("Table 3 - Fish"));
		add(new TextField("15 seconds"));
		table3.dishshoworder[3] = new TextField("0");
		add(table3.dishshoworder[3]);
		final Button table3_preparedish4 = new Button("CLICK to prepare");
		add(table3_preparedish4);
		table3.dishstatus[3] = new TextField("Prepared/Ready");
		add(table3.dishstatus[3]);
		table3.scoredish[3] = new TextField("SCORE - Total: TBC");
		add(table3.scoredish[3]);


		//SET-UP TABLE 4
		//Line entries - dish - 1 - table 4
		add(new TextField("Table 4 - Soup"));
		add(new TextField("2 seconds"));
		table4.dishshoworder[0] = new TextField("0");
		add(table4.dishshoworder[0]);
		final Button table4_preparedish1 = new Button("CLICK to prepare");
		add(table4_preparedish1);
		table4.dishstatus[0] = new TextField("Prepared/Ready");
		add(table4.dishstatus[0]);
		table4.scoredish[0] = new TextField("SCORE - Time: TBC");
		add(table4.scoredish[0]);

		//Line entries - dish - 2 - table 4
		add(new TextField("Table 4 - Steak"));
		add(new TextField("5 seconds"));
		table4.dishshoworder[1] = new TextField("0");
		add(table4.dishshoworder[1]);
		final Button table4_preparedish2 = new Button("CLICK to prepare");
		add(table4_preparedish2);
		table4.dishstatus[1] = new TextField("Prepared/Ready");
		add(table4.dishstatus[1]);
		table4.scoredish[1] = new TextField("SCORE - Dispersion: TBC");
		add(table4.scoredish[1]);

		//Line entries - dish - 3 - table 4
		add(new TextField("Table 4 - Burger"));
		add(new TextField("10 seconds"));
		table4.dishshoworder[2] = new TextField("0");
		add(table4.dishshoworder[2]);
		final Button table4_preparedish3 = new Button("CLICK to prepare");
		add(table4_preparedish3);
		table4.dishstatus[2] = new TextField("Prepared/Ready");
		add(table4.dishstatus[2]);
		table4.scoredish[2] = new TextField("SCORE - Temperature: TBC");
		add(table4.scoredish[2]); 

		//Line entries - dish - 4 - table 4
		add(new TextField("Table 4 - Fish"));
		add(new TextField("15 seconds"));
		table4.dishshoworder[3] = new TextField("0");
		add(table4.dishshoworder[3]);
		final Button table4_preparedish4 = new Button("CLICK to prepare");
		add(table4_preparedish4);
		table4.dishstatus[3] = new TextField("Prepared/Ready");
		add(table4.dishstatus[3]);
		table4.scoredish[3] = new TextField("SCORE - Total: TBC");
		add(table4.scoredish[3]);*/
		 
		 /*Loop to set-up 4 rows for each of 4 tables in the standard format
		  * using Textfields and a Button to initiate preparation of the dish.  
		  * The Textfields and Buttons are stored in their respecive arrays
		  *   */
		 int table_setup = 0;
		 String which_dish = new String();
		 int exp_time = 0;
		 int text_field = 0;
		 for (int i = 0; i<restaurant_size; i++){
			 
			 if (i == 0 || i%4 == 0){which_dish = "Soup"; exp_time = dish1_time; table_setup++;}
			 else if (i == 1 || i == 5 || i == 9 || i == 13){which_dish = "Steak"; exp_time = dish2_time;}
			 else if (i == 2 || (i%2 == 0 && i%4 != 0) ){which_dish = "Burger"; exp_time = dish3_time;}
			 else {which_dish = "Fish"; exp_time = dish4_time;}
			 
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
		 
				ActionListener taskPerformer = new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						create_order(table_number);
						
		            	date_table[table_number-1]= new java.util.Date();
						
						//date_table[0] = new Date();
					}
				};
	      
				final Timer timer = new Timer( (int) (1000) , taskPerformer);
				timer.setRepeats(false);
				timer.start();
				try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();} 
	        
				/*ActionListener to control order generation for each table - timer repeat produces a loop through each table
				 * calling the 'create_order' function to generate the order   */
				ActionListener taskPerformer2 = new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
	               
						if (table_number < 4) table_number++;
						else table_number = 1;
	            
						create_order(table_number);
	            	
		            	date_table[table_number-1] = new java.util.Date();
						
						/*if (table_number == 1) order_start_time[0] = new Date();
						else if (table_number == 2) order_start_time[1] = new Date();
						else if (table_number == 3) order_start_time[2] = new Date();
						else if (table_number == 4) order_start_time[3] = new Date();*/
					}
				};
	      
				final Timer timer2 = new Timer( (int) (1000*table_interval) , taskPerformer2);
				timer2.setRepeats(true);
				timer2.start();
				try {Thread.sleep(1000);} catch (InterruptedException e1) { e1.printStackTrace();}
      
				/* ActionListener on repeat to check whether service has finishd for each table and if so to 
				 * call 'score' and 'score_board' functions to calculate and update the table and game scoreboards
				 * */	
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
							/*preparedish1.setEnabled(false);
							preparedish2.setEnabled(false);
							preparedish3.setEnabled(false);
							preparedish4.setEnabled(false);
							table2_preparedish1.setEnabled(false);
							table2_preparedish2.setEnabled(false);
							table2_preparedish3.setEnabled(false);
							table2_preparedish4.setEnabled(false);
							table3_preparedish1.setEnabled(false);
							table3_preparedish2.setEnabled(false);
							table3_preparedish3.setEnabled(false);
							table3_preparedish4.setEnabled(false);
							table4_preparedish1.setEnabled(false);
							table4_preparedish2.setEnabled(false);
							table4_preparedish3.setEnabled(false);
							table4_preparedish4.setEnabled(false);*/
							
							for(int i = 0; i < 16; i++)
							{
								buttons[i].setEnabled(false);
							}
							
						}
					};

					Timer timer4 = new Timer((int)(1000)*5, taskPerformer4);
					timer4.setRepeats(false);
					timer4.start();
					try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			}
		});
 
		/*Prepare Dish button actions.  ActionListeners added to each button to initiate preparation of each dish
		 * when the button is pressed.  This calls the 'prepare_button' method to update the display.
		 * The 'prepare_food' method returns the preparation time for this dish and drives a timer
		 * that initiates the embedded ActionListener to update the display, using the 'display_ready' method,
		 * after the allotted time.  This introduces an element of real-time interaction to the game.
		 * */

		 buttons[0].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 
				 prepare_button(0, 1);
				 	 
					 final double wait_time = prepare_food(1);
			 
					 ActionListener taskPerformer = new ActionListener() {
				            public void actionPerformed(ActionEvent evt) {
				            	
				            	display_ready(0);
				            	
				            }};
				      
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

		buttons[15].addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){

				 prepare_button(15,4);
			
				 final double wait_time = prepare_food(4);
				 
				 ActionListener taskPerformer = new ActionListener() {
			          
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	display_ready(15);
			            	
			            }};
			        
			        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
			        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
				  
				}	 

		});
		 
		}
		
//TABLE 1 - Prepare Dish button actions:
 /*preparedish1.addActionListener(new ActionListener()
 {
	 public void actionPerformed(ActionEvent e)
	 {
		 if(order_status[0])
		 {
			 table1.dishstatus[0].setText("Being Prepared");
			 table1.dishstatus[0].setBackground(Color.GREEN);
			 order_status[0] = false;
			 order_status[16] = false;
			 table1.dishshoworder[0].setText("Items waiting: 0");
		     table1.dishshoworder[0].setBackground(Color.white);
			 
			 final double wait_time = prepare_food(1);
	 
			 ActionListener taskPerformer = new ActionListener()
			 {
		            public void actionPerformed(ActionEvent evt)
		            {
		                table1.dishstatus[0].setText("Dish is ready");
		                table1.dishstatus[0].setBackground(Color.gray);
		                order_status[16] = true;
		                
		                Date date = new java.util.Date();
		                dish_time[0] =  (date.getTime() - order_start_time[0].getTime())/1000;
		            }
		     };
		      
		     Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
		     timer.setRepeats(false);
		     timer.start();
		     try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		 
		 }
	 }
 });
 
 preparedish2.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(order_status[1]){
			 table1.dishstatus[1].setText("Being Prepared");
			 table1.dishstatus[1].setBackground(Color.GREEN);
			 order_status[1] = false;
			 order_status[17] = false;
			 table1.dishshoworder[1].setText("Items waiting: 0");
			 table1.dishshoworder[1].setBackground(Color.white);
	                
		 final double wait_time = prepare_food(2);
		 
		 ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                table1.dishstatus[1].setText("Dish is ready");
	                table1.dishstatus[1].setBackground(Color.gray);
	                order_status[17] = true;
	                
	                Date date = new java.util.Date();
	                dish_time[1] =  (date.getTime() - order_start_time[0].getTime())/1000;
	              
	            }};
	            
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
	 }}
 });
 

 preparedish3.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(order_status[2]){
			 table1.dishstatus[2].setText("Being Prepared");	 
			 table1.dishstatus[2].setBackground(Color.GREEN);
			 order_status[2] = false;
			 order_status[18] = false;
			 table1.dishshoworder[2].setText("Items waiting: 0");
			 table1.dishshoworder[2].setBackground(Color.white);

		  final double wait_time = prepare_food(3);
		 
		  ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                table1.dishstatus[2].setText("Dish is ready");
	                table1.dishstatus[2].setBackground(Color.gray);
	                order_status[18] = true;
	                
	                Date date = new java.util.Date();
	                dish_time[2] =  (date.getTime() - order_start_time[0].getTime())/1000;
	               
	            }};
	           
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
	 }}
 });
 
 preparedish4.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
if (order_status[3]){		
		 table1.dishstatus[3].setText("Being Prepared");
		 table1.dishstatus[3].setBackground(Color.GREEN);
		 order_status[3] = false;
		 order_status[19] = false;
		 table1.dishshoworder[3].setText("Items waiting: 0");
		 table1.dishshoworder[3].setBackground(Color.white);
		 
		 final double wait_time = prepare_food(4);
		 
		 ActionListener taskPerformer = new ActionListener() {
	          
	            public void actionPerformed(ActionEvent evt) {
	            	table1.dishstatus[3].setText("Dish is ready");
	            	table1.dishstatus[3].setBackground(Color.gray);
	                order_status[19] = true;
	                
	                Date date = new java.util.Date();
	                dish_time[3] =  (date.getTime() - order_start_time[0].getTime())/1000;
	              
	            }};
	        
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		  
		}	 
	 }
 });
 

//TABLE 2 - Prepare Dish button actions:
table2_preparedish1.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(order_status[4]){
			 table2.dishstatus[0].setText("Being Prepared");
			 table2.dishstatus[0].setBackground(Color.GREEN);
			 order_status[4] = false;
			 order_status[20] = false;
			 table2.dishshoworder[0].setText("Items waiting: 0");
		     table2.dishshoworder[0].setBackground(Color.white);
		            	
			 final double wait_time = prepare_food(1);
	 
			 ActionListener taskPerformer = new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	table2.dishstatus[0].setText("Dish is ready");
		            	table2.dishstatus[0].setBackground(Color.gray);
		            	order_status[20] = true;
		       
		            	Date date = new java.util.Date();
		                dish_time[4] =  (date.getTime() - order_start_time[1].getTime())/1000;
		            	
		            }};
		      
		        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
		        timer.setRepeats(false);
		        timer.start();
		        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		 
		 }}
});

table2_preparedish2.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(order_status[5]){
			 table2.dishstatus[1].setText("Being Prepared");
			 table2.dishstatus[1].setBackground(Color.GREEN);
			 order_status[5] = false;
			 order_status[21] = false;
			 table2.dishshoworder[1].setText("Items waiting: 0");
	         table2.dishshoworder[1].setBackground(Color.white);
	            	
		 final double wait_time = prepare_food(2);
		 
		 ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	table2.dishstatus[1].setText("Dish is ready");
	            	table2.dishstatus[1].setBackground(Color.gray);
	            	order_status[21] = true;
	                
	            	Date date = new java.util.Date();
	                dish_time[5] =  (date.getTime() - order_start_time[1].getTime())/1000;
	            	
	            }};
	            
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
	 }}
});


table2_preparedish3.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(order_status[6]){
			 table2.dishstatus[2].setText("Being Prepared");	 
			 table2.dishstatus[2].setBackground(Color.GREEN);
			 order_status[6] = false; 
			 order_status[22] = false;
			 table2.dishshoworder[2].setText("Items waiting: 0");
         	table2.dishshoworder[2].setBackground(Color.white);
			 
			 
		  final double wait_time = prepare_food(3);
		 
		  ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	table2.dishstatus[2].setText("Dish is ready");
	            	table2.dishstatus[2].setBackground(Color.gray);
	            	order_status[22] = true;
	            	
	            	Date date = new java.util.Date();
	                dish_time[6] =  (date.getTime() - order_start_time[1].getTime())/1000;
	            
	            }};
	            
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
	 }}
});

table2_preparedish4.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
if (order_status[7]){		
	table2.dishstatus[3].setText("Being Prepared");
	table2.dishstatus[3].setBackground(Color.GREEN);
	order_status[7] = false;
	order_status[23] = false;
	 table2.dishshoworder[3].setText("Items waiting: 0");
 	table2.dishshoworder[3].setBackground(Color.white);	 
	
		 final double wait_time = prepare_food(4);
		 
		 ActionListener taskPerformer = new ActionListener() {
	          
	            public void actionPerformed(ActionEvent evt) {
	            	table2.dishstatus[3].setText("Dish is ready");
	            	table2.dishstatus[3].setBackground(Color.gray);
	            	order_status[23] = true;
	            	
	            	Date date = new java.util.Date();
	                dish_time[7] =  (date.getTime() - order_start_time[1].getTime())/1000;
	            	
	            }};
	        
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		  
		}	 
	 }
});

//TABLE 3 - Prepare Dish button actions:
table3_preparedish1.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(order_status[8]){
			 table3.dishstatus[0].setText("Being Prepared");
			 table3.dishstatus[0].setBackground(Color.GREEN);
			 order_status[8] = false;
			 order_status[24] = false;
			 table3.dishshoworder[0].setText("Items waiting: 0");
		     table3.dishshoworder[0].setBackground(Color.white);
			 final double wait_time = prepare_food(1);
	 
			 ActionListener taskPerformer = new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	table3.dishstatus[0].setText("Dish is ready");
		            	table3.dishstatus[0].setBackground(Color.gray);
		            	order_status[24] = true;
		               
		            	Date date = new java.util.Date();
		                dish_time[8] =  (date.getTime() - order_start_time[2].getTime())/1000;
		            	
		            }};
		      
		        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
		        timer.setRepeats(false);
		        timer.start();
		        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		 
		 }}
});

table3_preparedish2.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(order_status[9]){
			 table3.dishstatus[1].setText("Being Prepared");
			 table3.dishstatus[1].setBackground(Color.GREEN);
			 order_status[9] = false;
			 order_status[25] = false;
		 table3.dishshoworder[1].setText("Items waiting: 0");
	            	table3.dishshoworder[1].setBackground(Color.white);
	            	
		 final double wait_time = prepare_food(2);
		 
		 ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	table3.dishstatus[1].setText("Dish is ready");
	            	table3.dishstatus[1].setBackground(Color.gray);
	            	order_status[25] = true;
	                
	            	Date date = new java.util.Date();
	                dish_time[9] =  (date.getTime() - order_start_time[2].getTime())/1000;
	            	
	            }};
	           
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
	 }}
});


table3_preparedish3.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(order_status[10]){
			 table3.dishstatus[2].setText("Being Prepared");	 
			 table3.dishstatus[2].setBackground(Color.GREEN);
			 order_status[10] = false;
			 order_status[26] = false;
			 table3.dishshoworder[2].setText("Items waiting: 0");
         	table3.dishshoworder[2].setBackground(Color.white);
			 
			 
		  final double wait_time = prepare_food(3);
		 
		  ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	table3.dishstatus[2].setText("Dish is ready");
	            	table3.dishstatus[2].setBackground(Color.gray);
	            	order_status[26] = true;
	               
	            	Date date = new java.util.Date();
	                dish_time[10] =  (date.getTime() - order_start_time[2].getTime())/1000;
	            	
	            }};
	   
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
	 }}
});

table3_preparedish4.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
if (order_status[11]){		
	table3.dishstatus[3].setText("Being Prepared");
	table3.dishstatus[3].setBackground(Color.GREEN);
	order_status[11] = false;
	order_status[27] = false;
	 table3.dishshoworder[3].setText("Items waiting: 0");
 	table3.dishshoworder[3].setBackground(Color.white);	 
	
		 final double wait_time = prepare_food(4);
		 
		 ActionListener taskPerformer = new ActionListener() {
	          
	            public void actionPerformed(ActionEvent evt) {
	            	table3.dishstatus[3].setText("Dish is ready");
	            	table3.dishstatus[3].setBackground(Color.gray);
	            	order_status[27] = true;
	             	
	            	Date date = new java.util.Date();
	                dish_time[11] =  (date.getTime() - order_start_time[2].getTime())/1000;
	            	
	            }};
	        
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		  
		}	 
	 }
});

//TABLE 4 - Prepare Dish button actions:
table4_preparedish1.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(order_status[12]){
			 table4.dishstatus[0].setText("Being Prepared");
			 table4.dishstatus[0].setBackground(Color.GREEN);
			 order_status[12] = false;
			 order_status[28] = false;
			 table4.dishshoworder[0].setText("Items waiting: 0");
		            	table4.dishshoworder[0].setBackground(Color.white);
		            	
			 final double wait_time = prepare_food(1);
	 
			 ActionListener taskPerformer = new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	table4.dishstatus[0].setText("Dish is ready");
		            	table4.dishstatus[0].setBackground(Color.gray);
		            	order_status[28] = true;
		       
		            	Date date = new java.util.Date();
		                dish_time[12] =  (date.getTime() - order_start_time[3].getTime())/1000;
		            	
		            }};
		      
		        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
		        timer.setRepeats(false);
		        timer.start();
		        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		 
		 }}
});

table4_preparedish2.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(order_status[13]){
			 table4.dishstatus[1].setText("Being Prepared");
			 table4.dishstatus[1].setBackground(Color.GREEN);
			 order_status[13] = false;
			 order_status[29] = false;
		 table4.dishshoworder[1].setText("Items waiting: 0");
	            	table4.dishshoworder[1].setBackground(Color.white);
	            	
		 final double wait_time = prepare_food(2);
		 
		 ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	table4.dishstatus[1].setText("Dish is ready");
	            	table4.dishstatus[1].setBackground(Color.gray);
	            	order_status[29] = true;
	                
	            	Date date = new java.util.Date();
	                dish_time[13] =  (date.getTime() - order_start_time[3].getTime())/1000;
	            
	            }};
	            
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
	 }}
});


table4_preparedish3.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(order_status[14]){
			 table4.dishstatus[2].setText("Being Prepared");	 
			 table4.dishstatus[2].setBackground(Color.GREEN);
			 order_status[14] = false;
			 order_status[30] = false;
			 table4.dishshoworder[2].setText("Items waiting: 0");
         	table4.dishshoworder[2].setBackground(Color.white);
		 
		  final double wait_time = prepare_food(3);
		 
		  ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	table4.dishstatus[2].setText("Dish is ready");
	            	table4.dishstatus[2].setBackground(Color.gray);
	            	order_status[30] = true;
	               
	            	Date date = new java.util.Date();
	                dish_time[14] =  (date.getTime() - order_start_time[3].getTime())/1000;
	            	
	            }};
	         
	          
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
	 }}
});

table4_preparedish4.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
if (order_status[15]){		
	table4.dishstatus[3].setText("Being Prepared");
	table4.dishstatus[3].setBackground(Color.GREEN);
	order_status[15] = false;
	order_status[31] = false;
	table4.dishshoworder[3].setText("Items waiting: 0");
	table4.dishshoworder[3].setBackground(Color.white);	 
	
	
		 final double wait_time = prepare_food(4);
		 
		 ActionListener taskPerformer = new ActionListener() {
	          
	            public void actionPerformed(ActionEvent evt) {
	            	table4.dishstatus[3].setText("Dish is ready");
	            	table4.dishstatus[3].setBackground(Color.gray);
	            	order_status[31] = true;
	                
	            	Date date = new java.util.Date();
	                dish_time[15] =  (date.getTime() - order_start_time[3].getTime())/1000;
	            	
	            }};
	        
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		  
		}	 
	 }
});
 
}*/

	/*
	 * Generates a random order for the head chef (player) to introduce delays for.
	 * Each dish can be ordered only once.
	 * @return void
	 */
	void create_order(int order_number)
	{
		double value = random_var();
		
		// select one dish
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

		// select two dishes
		else if(value >= 0.25 && value < 0.50)
		{
			double val2 = random_var();
			
			// select the first dish
			if(val2 >= 0 && val2 < 0.25)
			{
				order_status[order_number*4-4] = true;
				
				double val2a = random_var();
				
				// select the second dish
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
		
		// select three dishes
		else if(value >= 0.50 && value < 0.75)
		{
			double val3 = random_var();
			
			// select the first dish
			if(val3 >= 0 && val3 < 0.25)
			{
				order_status[order_number*4-4] = true;
				
				double val3a = random_var();
				
				// select the second dish
				if(val3a >= 0 && val3a < 0.33)
				{
					order_status[order_number*4-3] = true;
				
					double val3aa = random_var();
					
					// select the third dish
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
		
		// select four dishes
		else
		{
			order_status[order_number*4-4] = true;
			order_status[order_number*4-3] = true;
			order_status[order_number*4-2] = true;
			order_status[order_number*4-1] = true;
		}
		
		//Set scoreboard status for this table to false to allow updating once table service completed.
		scoreboard_status[order_number-1] = false;
		
		//Display order 
		if(order_status[order_number*4-4]){
			textfields[order_number*20-18].setText("Items ordered: 1");
			textfields[order_number*20-18].setBackground(Color.GREEN);
			textfields[order_number*20-17].setText("Prepared/Ready");
			textfields[order_number*20-17].setBackground(Color.white);} 
			else {
				textfields[order_number*20-18].setText("Items ordered: 0");
				order_status[16+order_number*4-4] = true;
			}
			
			if(order_status[order_number*4-3]){
				textfields[order_number*20-13].setText("Items ordered: 1");
				textfields[order_number*20-13].setBackground(Color.GREEN);
				textfields[order_number*20-12].setText("Prepared/Ready");
				textfields[order_number*20-12].setBackground(Color.white);}
			else {
				textfields[order_number*20-13].setText("Items ordered: 0");
				order_status[16+order_number*4-3] = true;
			}
			
			if(order_status[order_number*4-2]){
				textfields[order_number*20-8].setText("Items ordered: 1");
				textfields[order_number*20-8].setBackground(Color.GREEN);
				textfields[order_number*20-7].setText("Prepared/Ready");
				textfields[order_number*20-7].setBackground(Color.white);}
			else{
				textfields[order_number*20-8].setText("Items ordered: 0");
				order_status[16+order_number*4-2] = true;
			}
			
			if(order_status[order_number*4-1]){
				textfields[order_number*20-3].setText("Items ordered: 1");
				textfields[order_number*20-3].setBackground(Color.GREEN);
				textfields[order_number*20-2].setText("Prepared/Ready");
				textfields[order_number*20-2].setBackground(Color.white);}
			else {
				textfields[order_number*20-3].setText("Items ordered: 0");
				order_status[16+order_number*4-1] = true;
			}
		
		// update the display
		/*if (order_number == 1)
		{	
			scoreboard_status[0] = false;
				
			for(int i = 0; i < 4; i++)
			{
				if(order_status[i])
				{
					table1.dishshoworder[i].setText("Items ordered: 1");
					table1.dishshoworder[i].setBackground(Color.GREEN);
					table1.dishstatus[i].setText("Prepared/Ready");
					table1.dishstatus[i].setBackground(Color.white);
				} 
				else
				{
					table1.dishshoworder[i].setText("Items ordered: 0");
					order_status[i + 16] = true;
				}
			}
		}	
		
		if (order_number == 2)
		{		
			scoreboard_status[1] = false;
			
			for(int i = 0, j = 4; i < 4; i++, j--)
			{
				if(order_status[order_number*4 - j])
				{
					table2.dishshoworder[i].setText("Items ordered: 1");
					table2.dishshoworder[i].setBackground(Color.GREEN);
					table2.dishstatus[i].setText("Prepared/Ready");
					table2.dishstatus[i].setBackground(Color.white);
				} 
				else
				{
					table2.dishshoworder[i].setText("Items ordered: 0");
					order_status[order_number*4 - j + 16] = true;
				}
			}	
		}

		if (order_number == 3)
		{		
			scoreboard_status[2] = false;
		
			for(int i = 0, j = 4; i < 4; i++, j--)
			{
				if(order_status[order_number*4 - j])
				{
					table3.dishshoworder[i].setText("Items ordered: 1");
					table3.dishshoworder[i].setBackground(Color.GREEN);
					table3.dishstatus[i].setText("Prepared/Ready");
					table3.dishstatus[i].setBackground(Color.white);
				} 
				else
				{
					table3.dishshoworder[i].setText("Items ordered: 0");
					order_status[order_number*4 - j + 16] = true;
				}
			}
		}
			
		if (order_number == 4)
		{	
			scoreboard_status[3] = false;
		
			for(int i = 0, j = 4; i < 4; i++, j--)
			{
				if(order_status[order_number*4 - j])
				{
					table4.dishshoworder[i].setText("Items ordered: 1");
					table4.dishshoworder[i].setBackground(Color.GREEN);
					table4.dishstatus[i].setText("Prepared/Ready");
					table4.dishstatus[i].setBackground(Color.white);} 
				else
				{
					table4.dishshoworder[i].setText("Items ordered: 0");
					order_status[order_number*4 - j + 16] = true;
				}
			}	
		}*/
	}

double prepare_food(int dish_number)
{
	double delay = norm_random_var();
	
	if(dish_number == 1)
		return (2.0 + delay);
	else if (dish_number == 2)
		return (5.0 + delay);
	else if (dish_number == 3)
		return (10.0 + delay);
	else
		return (15.0 + delay);
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


int score(double table)
{
	
	double total_time, quickest, dispersion, temperature,
	score_time, score_dispersion, score_temp, score_total;
	
	total_time = quickest = dispersion = temperature = 
	score_time = score_dispersion = score_temp = score_total = 0;
		
		total_time = dish_time[(int) (table*4-4)];
		quickest = dish_time[(int) (table*4-4)];
		
		for (int i = (int) (table*4-3); i < (int) (table*4); i++){
			if (dish_time[i] > total_time) total_time = dish_time[i];
			if (dish_time[i] < quickest  && dish_time[i] > 0) quickest = dish_time[i];
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
		
		/*if (table == 1){
		
		table1.scoredish[0].setText("SCORE - Time: " + (int) score_time + "%");
		table1.scoredish[1].setText("SCORE - Dispersion: " + (int) score_dispersion + "%");
		table1.scoredish[2].setText("SCORE - Temperature: " + (int) score_temp + "%");
		table1.scoredish[3].setText("SCORE - TOTAL: " + (int) score_total + "%");
		
		if (score_time <= 40) table1.scoredish[0].setBackground(Color.red);
        else if (score_time > 40 && score_time <= 70) table1.scoredish[0].setBackground(Color.orange);
        else if (score_time > 70) table1.scoredish[0].setBackground(Color.green);
		
		if (score_dispersion <= 40) table1.scoredish[1].setBackground(Color.red);
        else if (score_dispersion > 40 && score_dispersion <= 70) table1.scoredish[1].setBackground(Color.orange);
        else if (score_dispersion > 70) table1.scoredish[1].setBackground(Color.green);
		
		if (score_temp <= 40) table1.scoredish[2].setBackground(Color.red);
        else if (score_temp > 40 && score_temp <= 70) table1.scoredish[2].setBackground(Color.orange);
        else if (score_temp > 70) table1.scoredish[2].setBackground(Color.green);
		
		if (score_total <= 40) table1.scoredish[3].setBackground(Color.red);
        else if (score_total > 40 && score_total <= 70) table1.scoredish[3].setBackground(Color.orange);
        else if (score_total > 70) table1.scoredish[3].setBackground(Color.green);
		
		return 1;
		
	}
		
		if (table == 2){
			
			table2.scoredish[0].setText("SCORE - Time: " + (int) score_time + "%");
			table2.scoredish[1].setText("SCORE - Dispersion: " + (int) score_dispersion + "%");
			table2.scoredish[2].setText("SCORE - Temperature: " + (int) score_temp + "%");
			table2.scoredish[3].setText("SCORE - TOTAL: " + (int) score_total + "%");
			
			if (score_time <= 40) table2.scoredish[0].setBackground(Color.red);
	        else if (score_time > 40 && score_time <= 70) table2.scoredish[0].setBackground(Color.orange);
	        else if (score_time > 70) table2.scoredish[0].setBackground(Color.green);
			
			if (score_dispersion <= 40) table2.scoredish[1].setBackground(Color.red);
	        else if (score_dispersion > 40 && score_dispersion <= 70) table2.scoredish[1].setBackground(Color.orange);
	        else if (score_dispersion > 70) table2.scoredish[1].setBackground(Color.green);
			
			if (score_temp <= 40) table2.scoredish[2].setBackground(Color.red);
	        else if (score_temp > 40 && score_temp <= 70) table2.scoredish[2].setBackground(Color.orange);
	        else if (score_temp > 70) table2.scoredish[2].setBackground(Color.green);
			
			if (score_total <= 40) table2.scoredish[3].setBackground(Color.red);
	        else if (score_total > 40 && score_total <= 70) table2.scoredish[3].setBackground(Color.orange);
	        else if (score_total > 70) table2.scoredish[3].setBackground(Color.green);
			
			return 1;
			
		}	
		
if (table == 3){
			
			table3.scoredish[0].setText("SCORE - Time: " + (int) score_time + "%");
			table3.scoredish[1].setText("SCORE - Dispersion: " + (int) score_dispersion + "%");
			table3.scoredish[2].setText("SCORE - Temperature: " + (int) score_temp + "%");
			table3.scoredish[3].setText("SCORE - TOTAL: " + (int) score_total + "%");
			
			if (score_time <= 40) table3.scoredish[0].setBackground(Color.red);
	        else if (score_time > 40 && score_time <= 70) table3.scoredish[0].setBackground(Color.orange);
	        else if (score_time > 70) table3.scoredish[0].setBackground(Color.green);
			
			if (score_dispersion <= 40) table3.scoredish[1].setBackground(Color.red);
	        else if (score_dispersion > 40 && score_dispersion <= 70) table3.scoredish[1].setBackground(Color.orange);
	        else if (score_dispersion > 70) table3.scoredish[1].setBackground(Color.green);
			
			if (score_temp <= 40) table3.scoredish[2].setBackground(Color.red);
	        else if (score_temp > 40 && score_temp <= 70) table3.scoredish[2].setBackground(Color.orange);
	        else if (score_temp > 70) table3.scoredish[2].setBackground(Color.green);
			
			if (score_total <= 40) table3.scoredish[3].setBackground(Color.red);
	        else if (score_total > 40 && score_total <= 70) table3.scoredish[3].setBackground(Color.orange);
	        else if (score_total > 70) table3.scoredish[3].setBackground(Color.green);
			
			return 1;
			
		}	
		
if (table == 4){
	
	table4.scoredish[0].setText("SCORE - Time: " + (int) score_time + "%");
	table4.scoredish[1].setText("SCORE - Dispersion: " + (int) score_dispersion + "%");
	table4.scoredish[2].setText("SCORE - Temperature: " + (int) score_temp + "%");
	table4.scoredish[3].setText("SCORE - TOTAL: " + (int) score_total + "%");
	
	if (score_time <= 40) table4.scoredish[0].setBackground(Color.red);
    else if (score_time > 40 && score_time <= 70) table4.scoredish[0].setBackground(Color.orange);
    else if (score_time > 70) table4.scoredish[0].setBackground(Color.green);
	
	if (score_dispersion <= 40) table4.scoredish[1].setBackground(Color.red);
    else if (score_dispersion > 40 && score_dispersion <= 70) table4.scoredish[1].setBackground(Color.orange);
    else if (score_dispersion > 70) table4.scoredish[1].setBackground(Color.green);
	
	if (score_temp <= 40) table4.scoredish[2].setBackground(Color.red);
    else if (score_temp > 40 && score_temp <= 70) table4.scoredish[2].setBackground(Color.orange);
    else if (score_temp > 70) table4.scoredish[2].setBackground(Color.green);
	
	if (score_total <= 40) table4.scoredish[3].setBackground(Color.red);
    else if (score_total > 40 && score_total <= 70) table4.scoredish[3].setBackground(Color.orange);
    else if (score_total > 70) table4.scoredish[3].setBackground(Color.green);*/
	
	return 1;
	
}			

//	else return ( (int) (100*table/9.0));
//}

void score_board(int table_number){
	
	int array_start = table_number*4-4;
	int dish_count = 0;

	for (int i = array_start; i < array_start + 5; i++) if (order_status[i]) dish_count++;
	
	score_board[table_number*4-4] += dish_count;
	
	score_board[20] +=score_board[table_number*4-4];
	score_board[21] +=score_board[table_number*4-3];
	score_board[22] +=score_board[table_number*4-2];
	score_board[23] +=score_board[table_number*4-1];
	score_board[24] +=score_board[table_number*4];
	
	scoreboard_status[table_number-1] = true;
	
	//reset dishes score
	
	update_scoreboard(table_number);
}

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
	
	
	/* Function to update the display for a dish being prepared.  Initiated by the 'Press to prepare' button.
	 * The function also updates the order-status flag of the dish
	 */

	void prepare_button(final int dish_number, int dish){
		
		if(order_status[dish_number]){
			 textfields[dish_number*5+3].setText("Being Prepared");
			 textfields[dish_number*5+3].setBackground(Color.GREEN);
			 order_status[dish_number] = false;
			 order_status[dish_number+16] = false;
			 textfields[dish_number*5+2].setText("Items waiting: 0");
			 textfields[dish_number*5+2].setBackground(Color.white);
		
		}
	}


	/* Function to update the display when a dish has been prepared.
	 * The function also updates the order_status flag of the dish.
	 */
	void display_ready(int dish_number){
		
		textfields[dish_number*5+3].setText("Dish is ready");
		textfields[dish_number*5+3].setBackground(Color.gray);
	    order_status[dish_number+16] = true;
	    
	    int table;
	    
	    if (dish_number >= 0 && dish_number <= 3) table = 1;
	    else if (dish_number >= 4 && dish_number <= 7) table = 2;
	    else if (dish_number >= 8 && dish_number <= 11) table = 3;
	    else table = 4;    
	    
	    Date date = new java.util.Date();
	    dish_time[dish_number] =  (date.getTime() - date_table[table].getTime())/1000;
		
		
	}
	
}
