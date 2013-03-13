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
	
	TextField tf, scoretime, scoredispersion, scoretemp, scoretotal;

	final int size = 35;
	int table_interval;
	int table_number;
	
	boolean[] order_status = new boolean[size];
	double[] dish_time = new double[size];
	int[] score_board = new int[25];
	Date[] order_start_time = new Date[4];
	boolean[] scoreboard_status = new boolean[4];
	
	public void init()
	{
		table_number = 1;
		table_interval = 7;
	
		resize(900,600);
  
		int rows = 0;
		int columns = 6;
		setLayout(new GridLayout(rows,columns,5,5)); 
		setFont(new Font("Calibri", Font.BOLD, 10));
		setBackground(new Color(85,153,187)); 
  
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
		 add(new TextField("Table 1 - Soup"));
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
		add(table4.scoredish[3]);
 
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
						order_start_time[0] = new Date();
					}
				};
	      
				final Timer timer = new Timer( (int) (1000) , taskPerformer);
				timer.setRepeats(false);
				timer.start();
				try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();} 
	        
				ActionListener taskPerformer2 = new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
	               
						if (table_number < 4) table_number++;
						else table_number = 1;
	            
						create_order(table_number);
	            	
						if (table_number == 1) order_start_time[0] = new Date();
						else if (table_number == 2) order_start_time[1] = new Date();
						else if (table_number == 3) order_start_time[2] = new Date();
						else if (table_number == 4) order_start_time[3] = new Date();
					}
				};
	      
				final Timer timer2 = new Timer( (int) (1000*table_interval) , taskPerformer2);
				timer2.setRepeats(true);
				timer2.start();
				try {Thread.sleep(1000);} catch (InterruptedException e1) { e1.printStackTrace();}
      
	        
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
							preparedish1.setEnabled(false);
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
							table4_preparedish4.setEnabled(false);
						}
					};

					Timer timer4 = new Timer((int)(1000)*5, taskPerformer4);
					timer4.setRepeats(false);
					timer4.start();
					try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
			}
		});
 
//TABLE 1 - Prepare Dish button actions:
 preparedish1.addActionListener(new ActionListener()
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
 
}

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
		
		// update the display
		if (order_number == 1)
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
		}
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
		
		
		if (table == 1){
		
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
    else if (score_total > 70) table4.scoredish[3].setBackground(Color.green);
	
	return 1;
	
}			


		
	
	else return ( (int) (100*table/9.0));
}

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
}
