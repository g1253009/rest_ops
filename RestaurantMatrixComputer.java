import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.security.Timestamp;
import java.util.Date;
import java.util.Random;
import java.io.*;
import java.applet.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class RestaurantMatrixComputer extends Applet
{
    TextField scoretime, scoredispersion, scoretemp, scoretotal;

    int size = 35;
    boolean[] order_status = new boolean[size];
    boolean[] scoreboard_status = new boolean[4];
    double[] dish_time = new double[size];
    int[] score_board = new int[25];
    int table_interval, table_number, total_dishes;
    java.util.Date[] date_table = new java.util.Date[4];
    Button[] buttons = new Button[16];
    TextField[] textfields = new TextField[80];

    int dish1_time = 2;
    int dish2_time = 5;
    int dish3_time = 10;
    int dish4_time = 15;


    public void init()
    {
    	table_interval = 7;
    	table_number = 1;
    	int restaurant_size = 8;

    	// Set up grid layout display area
    	resize(900,600);

    	int rows = 0;
    	int columns = 6;
    	setLayout(new GridLayout(rows,columns,5,5));
    	setFont(new Font("Calibri", Font.BOLD, 10));
    	setBackground(new Color(85,153,187));

    	/*Add playgame button and add actionListener to initiate gameplay*/
    	final Button playgame = new Button("CLICK to PAY");
    	add(playgame);

    	playgame.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			playgame.setBackground(Color.GREEN);
    			//playgame.setText("CLICK to REPLAY");

    			ActionListener taskPerformer = new ActionListener()
    			{
    				public void actionPerformed(ActionEvent evt)
    				{
    					create_order(table_number);

    					date_table[table_number-1] = new java.util.Date();

    					date_table[table_number] = new java.util.Date();

    					computer_cook(table_number+1);
    				}
    			};

    			Timer timer = new Timer( (int) (1000) , taskPerformer);
    			timer.setRepeats(false);
    			timer.start();
    			try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}

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
    				}
    			};

    			Timer timer3 = new Timer( 50 , taskPerformer3);
    			timer3.setRepeats(true);
    			timer3.start();
    			try {Thread.sleep(1000);} catch (InterruptedException e1) { e1.printStackTrace();}
    		}
    	});


    	//SET Header rows - play game and scoreboard
    	TextField your_score = new TextField("Your SCORE"); add(your_score);
    	your_score.setBackground(Color.yellow);
	
    	scoretime = new TextField("SCORE - Time: TBC"); add(scoretime);
    	scoretime.setBackground(Color.yellow);
	
    	scoredispersion = new TextField("SCORE - Dispersion: TBC"); add(scoredispersion);
    	scoredispersion.setBackground(Color.yellow);
	
    	scoretemp = new TextField("SCORE - Temperature: TBC"); add(scoretemp);
    	scoretemp.setBackground(Color.yellow);
	
    	scoretotal = new TextField("SCORE Score: TBC"); add(scoretotal);
    	scoretotal.setBackground(Color.yellow);

    	final Button replay_game = new Button("CLICK to REPLAY");
    	add(replay_game);

    	TextField computer_score = new TextField("Computer's SCORE"); add(computer_score);
    	computer_score.setBackground(Color.yellow);
	
    	TextField computer_scoretime = new TextField("SCORE - Time: TBC"); add(computer_scoretime);
    	computer_scoretime.setBackground(Color.yellow);
    	
    	TextField computer_scoredispersion = new TextField("SCORE - Dispersion: TBC"); add(computer_scoredispersion);
    	computer_scoredispersion.setBackground(Color.yellow);
	
    	TextField computer_scoretemp = new TextField("SCORE - Temperature: TBC"); add(computer_scoretemp);
    	computer_scoretemp.setBackground(Color.yellow);
	
    	TextField computer_scoretotal = new TextField("SCORE Score: TBC"); add(computer_scoretotal);
    	computer_scoretotal.setBackground(Color.yellow);


    	//SET Matrix headings for the table
    	TextField menuoptions = new TextField("MENU OPTIONS"); add(menuoptions);
    	menuoptions.setBackground(Color.GRAY);
	
    	TextField expecteddtime = new TextField("EXPECTED TIME"); add(expecteddtime);
    	expecteddtime.setBackground(Color.GRAY);
	
    	TextField showorder = new TextField("SHOW ORDER"); add(showorder);
    	showorder.setBackground(Color.GRAY);
	
    	TextField preparefood = new TextField("PREPARE FOOD?"); add(preparefood);
    	preparefood.setBackground(Color.GRAY);
	
    	TextField foodstatus = new TextField("DISH STATUS"); add(foodstatus);
    	foodstatus.setBackground(Color.GRAY);
	
    	TextField customerscore = new TextField("CUSTOMER & COMPUTER SCORE");add(customerscore);
    	customerscore.setBackground(Color.GRAY);


    	/*
    	 * Loop to set-up 4 rows for each of 4 tables in the standard format
    	 * using Textfields and a Button to initiate preparation of the dish.
    	 * The Textfields and Buttons are stored in their respecive arrays
    	 */
    	int table_setup = 0;
    	String which_dish = new String();
    	int exp_time = 0;
    	int text_field = 0;

    	for (int i = 0; i<restaurant_size; i++)
    	{
    		if (i == 0 || i%4 == 0)
    		{
    			which_dish = "Soup"; exp_time = dish1_time; table_setup++;
    		}
    		else if (i == 1 || i == 5 || i == 9 || i == 13)
    		{
    			which_dish = "Steak"; exp_time = dish2_time;
    		}
    		else if (i == 2 || (i%2 == 0 && i%4 != 0) )
    		{
    			which_dish = "Burger"; exp_time = dish3_time;
    		}
    		else
    		{
    			which_dish = "Fish"; exp_time = dish4_time;
    		}

    		if(i>3 && i<8)
    		{
    			textfields[text_field] = new TextField("Computer's Table - " + which_dish); add(textfields[text_field]);
    			textfields[text_field+1] = new TextField(exp_time + " seconds"); add(textfields[text_field+1]);
    			textfields[text_field+2] = new TextField("0"); add(textfields[text_field+2]);
    			buttons[i] = new Button("Computer is Preparing"); add(buttons[i]);
    			textfields[text_field+3] = new TextField("Prepared/Ready"); add(textfields[text_field+3]);
    			textfields[text_field+4] = new TextField("SCORE: TBC"); add(textfields[text_field+4]);
    			text_field +=5;
    		}
    		else
    		{
    			textfields[text_field] = new TextField("Your Table - " + which_dish); add(textfields[text_field]);
    			textfields[text_field+1] = new TextField(exp_time + " seconds"); add(textfields[text_field+1]);
    			textfields[text_field+2] = new TextField("0"); add(textfields[text_field+2]);
    			buttons[i] = new Button("CLICK to prepare"); add(buttons[i]);
    			textfields[text_field+3] = new TextField("Prepared/Ready"); add(textfields[text_field+3]);
    			textfields[text_field+4] = new TextField("SCORE: TBC"); add(textfields[text_field+4]);
    			text_field +=5;
    		}
    	}

    	/*
    	 * Prepare Dish button actions.  ActionListeners added to each button to initiate preparation of each dish
    	 * when the button is pressed.  This calls the 'prepare_button' method to update the display.
    	 * The 'prepare_food' method returns the preparation time for this dish and drives a timer
    	 * that initiates the embedded ActionListener to update the display, using the 'display_ready' method,
    	 * after the allotted time.  This introduces an element of real-time interaction to the game.
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

    	buttons[1].addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			prepare_button(1, 2);

    			final double wait_time = prepare_food(2);

    			ActionListener taskPerformer = new ActionListener()
    			{
    				public void actionPerformed(ActionEvent evt)
    				{
    					display_ready(1);
    				}
    			};

    			Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
    			timer.setRepeats(false);
    			timer.start();
    			try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
    		}
    	});

    	buttons[2].addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			prepare_button(2, 3);

    			final double wait_time = prepare_food(3);

    			ActionListener taskPerformer = new ActionListener()
    			{
    				public void actionPerformed(ActionEvent evt)
    				{
    					display_ready(2);
    				}
    			};

    			Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
    			timer.setRepeats(false);
    			timer.start();
    			try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
    		}
    	});

    	buttons[3].addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			prepare_button(3,4);

    			final double wait_time = prepare_food(4);

    			ActionListener taskPerformer = new ActionListener()
    			{
    				public void actionPerformed(ActionEvent evt)
    				{
    					display_ready(3);
    				}
    			};

    			Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
    			timer.setRepeats(false);
    			timer.start();
    			try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
    		}
    	});
    }

    public void computer_cook_ready(final int dish_no)
    {
    	double wait_time_4 = computer_prepare_food(dish_no-3);

    	ActionListener taskPerformer_w4 = new ActionListener()
    	{
    		public void actionPerformed(ActionEvent evt)
    		{
    			display_ready(dish_no);
    		}
	    };

	    Timer timer_w4 = new Timer( (int) (1000*wait_time_4) , taskPerformer_w4);
	    timer_w4.setRepeats(false);
	    timer_w4.start();
	    try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
    }


    public void computer_cook(int table_number)
    {
    	double d1 = 3;
    	double d2 = 5;

    	if(order_status[table_number*4-1] == true)
	    {
    		prepare_button(7,4);
    		computer_cook_ready(7);

    		if(order_status[table_number*4-2] == true)
		    {
    			ActionListener taskPerformer1 = new ActionListener()
    			{
    				public void actionPerformed(ActionEvent evt)
    				{
    					prepare_button(6,3);
    					computer_cook_ready(6);
    				}
    			};

    			Timer timer1 = new Timer( (int) (1000*d2) , taskPerformer1);
    			timer1.setRepeats(false);
    			timer1.start();
    			try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		    }

    		if(order_status[table_number*4-3] == true)
		    {
    			ActionListener taskPerformer2 = new ActionListener()
    			{
    				public void actionPerformed(ActionEvent evt)
    				{
    					prepare_button(5,2);
    					computer_cook_ready(5);
    				}
			    };

			    Timer timer2 = new Timer( (int) (1000*2*d2) , taskPerformer2);
			    timer2.setRepeats(false);
			    timer2.start();
			    try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		    }

    		if(order_status[table_number*4-4] == true)
		    {
    			ActionListener taskPerformer3 = new ActionListener()
    			{
    				public void actionPerformed(ActionEvent evt)
    				{
    					prepare_button(4,1);
    					computer_cook_ready(4);
    				}
			    };

			    Timer timer3 = new Timer( (int) (1000*(2*d2+d1)) , taskPerformer3);
			    timer3.setRepeats(false);
			    timer3.start();
			    try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		    }
	    }
    	else if(order_status[table_number*4-2] == true && order_status[table_number*4-1] == false)
	    {
    		prepare_button(6,3);
    		computer_cook_ready(6);

    		if(order_status[table_number*4-3] == true)
		    {
    			ActionListener taskPerformer1 = new ActionListener()
    			{
    				public void actionPerformed(ActionEvent evt)
    				{
    					prepare_button(5,2);
    					computer_cook_ready(5);
    				}
    			};

    			Timer timer1 = new Timer( (int) (1000*d2) , taskPerformer1);
    			timer1.setRepeats(false);
    			timer1.start();
			
    			try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		    }

    		if(order_status[table_number*4-4] == true)
		    {
    			ActionListener taskPerformer3 = new ActionListener()
    			{
    				public void actionPerformed(ActionEvent evt)
    				{
    					prepare_button(4,1);
    					computer_cook_ready(4);
    				}
			    };

			    Timer timer3 = new Timer( (int) (1000*(d2+d1)) , taskPerformer3);
			    timer3.setRepeats(false);
			    timer3.start();
			
			    try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		    }
	    }
    	else if(order_status[table_number*4-3] == true && order_status[table_number*4-2] == false && order_status[table_number*4-1] == false)
	    {
    		prepare_button(5,2);
    		computer_cook_ready(5);

    		if(order_status[table_number*4-4] == true)
		    {
    			ActionListener taskPerformer1 = new ActionListener()
    			{
    				public void actionPerformed(ActionEvent evt)
    				{
    					prepare_button(4,1);
    					computer_cook_ready(4);
    				}
			    };

			    Timer timer1 = new Timer( (int) (1000*d1) , taskPerformer1);
			    timer1.setRepeats(false);
			    timer1.start();
			
			    try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		    }
	    }
    	else if(order_status[table_number*4-4] == true && order_status[table_number*4-3] == false
    			&& order_status[table_number*4-2] == false && order_status[table_number*4-1] == false)
	    {
    		prepare_button(4,1);
    		computer_cook_ready(4);
	    }
    }

    /* 
     * A random function is used to determine whether a dish is ordered or not.
     * A check is performed to ensure that if dishes 1-3 are not ordered then dish 4 is ordered.
     * The display is updated to confirm the items ordered
     * */
    void create_order(int order_number)
    {
    	if(0 + (int)(Math.random() * ((1 - 0) + 1)) == 1)
	    {
    		order_status[order_number*4-4] = true;
    		order_status[(order_number+1)*4-4] = true;
	    }
    	if(0 + (int)(Math.random() * ((1 - 0) + 1)) == 1)
	    {
    		order_status[order_number*4-3] = true;
    		order_status[(order_number+1)*4-3] = true;
	    }
    	if(0 + (int)(Math.random() * ((1 - 0) + 1)) == 1)
	    {
    		order_status[order_number*4-2] = true;
    		order_status[(order_number+1)*4-2] = true;
	    }
    	if (!order_status[order_number*4-4] && !order_status[order_number*4-3] && !order_status[order_number*4-2])
	    {
    		order_status[order_number*4-1] = true;
    		order_status[(order_number+1)*4-1] = true;
	    }
    	else if(0 + (int)(Math.random() * ((1 - 0) + 1)) == 1)
	    {
    		order_status[order_number*4-1] = true;
    		order_status[(order_number+1)*4-1] = true;
	    }

    	//Set scoreboard status for this table to false to allow updating once table service completed.
    	scoreboard_status[order_number-1] = false;

    	//Display order
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
    		textfields[order_number*20-12].setBackground(Color.white);
    	}
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
    		textfields[order_number*20-2].setBackground(Color.white);
    	}
    	else
    	{
    		textfields[order_number*20-3].setText("Items ordered: 0");
    		order_status[16+order_number*4-1] = true;
    	}

    	order_number++;

    	//Set scoreboard status for this table to false to allow updating once table service completed.
    	scoreboard_status[order_number-1] = false;

    	//Display order
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
    		textfields[order_number*20-12].setBackground(Color.white);
    	}
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
    		textfields[order_number*20-2].setBackground(Color.white);
    	}
    	else
    	{
    		textfields[order_number*20-3].setText("Items ordered: 0");
    		order_status[16+order_number*4-1] = true;
    	}
    }

    /* 
     * Functions to return the preparation time for the dishes.  Uses a normal delay to the standard
     * dish preparation time.
     */
    double prepare_food(int dish_number)
    {
    	double delay = norm_random_var();

    	if(dish_number == 1) return (dish1_time + delay);
    	else if (dish_number == 2) return (dish2_time + delay);
    	else if (dish_number == 3) return (dish3_time + delay);
    	else if (dish_number == 4) return (dish4_time + delay);
    	else return 5.3;
    }

    double computer_prepare_food(int dish_number)
    {
    	double delay = norm_random_var();
    	System.out.println(delay);
    	if(dish_number == 1) return (dish1_time + delay);
    	else if (dish_number == 2) return (dish2_time*(1 + delay));
    	else if (dish_number == 3) return (dish3_time*(1 + delay));
    	else if (dish_number == 4) return (dish4_time*(1 + delay));
    	else return 5.3;
    }

    public static double norm_random_var()
    {
    	double twoPI = 2 * Math.PI ;
    	double r1, r2, k ;

    	r1 = Math.random() ;
    	r2 = Math.random() ;

    	k = Math.sqrt( -2*Math.log( r1 ) ) ;

    	return ( k*Math.cos( twoPI*r2 ) / 2.14 );
    }

    /*
     * Function to calculate the customer score for each table.  It calculates and uses metrics for the
     * total preparation time and dispersion of dishes being served.  Temperature score is set to equal the
     * dispersion score.  The display is updated to reflect the score for the table.
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
    		if (dish_time[i] > total_time) total_time = dish_time[i];
    		if (dish_time[i] < quickest  && dish_time[i] > 0) quickest = dish_time[i];
    	}

    	dispersion = total_time - quickest;
    	temperature = dispersion;

    	if (total_time < 12) score_time = 100;
    	else score_time = 100 - 10*(total_time - 12);
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
    }

    /* 
     * Function to update the scoreboard metrics for each completed table.
     */
    void score_board(int table_number)
    {
    	int array_start = table_number*4-4;
    	int dish_count = 0;

    	for (int i = array_start; i < array_start + 5; i++)
    		if (order_status[i])
    			dish_count++;

    	score_board[table_number*4-4] += dish_count;

    	score_board[20] +=score_board[table_number*4-4];
    	score_board[21] +=score_board[table_number*4-3];
    	score_board[22] +=score_board[table_number*4-2];
    	score_board[23] +=score_board[table_number*4-1];
    	score_board[24] +=score_board[table_number*4];

    	scoreboard_status[table_number-1] = true;

    	if(table_number == 1)
    		update_scoreboard(table_number);
    	else if(table_number == 2)
    		computer_update_scoreboard(table_number);
    }

    /*
     * Function to display the latest scores on the game scoreboard.
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

    void computer_update_scoreboard(int table_number)
    {
    	if (score_board[20] == 0) score_board[20] = 1;

    	int scoretime2 = score_board[21]/score_board[20];
    	int scoredispersion2 =  score_board[22]/score_board[20];
    	int scoretemp2 = score_board[23]/score_board[20];
    	int scoretotal2 = score_board[24]/score_board[20];

    	computer_scoretime.setText("SCORE - Time: " + scoretime2 + "%");
    	computer_scoredispersion.setText("SCORE - Dispersion: " + scoredispersion2 + "%");
    	computer_scoretemp.setText("SCORE - Temperature: " + scoretemp2 + "%");
    	computer_scoretotal.setText("SCORE - TOTAL: " + scoretotal2 + "%");

    	if (scoretime2 <= 40) computer_scoretime.setBackground(Color.red);
    	else if (scoretime2 > 40 && scoretime2 <= 70) computer_scoretime.setBackground(Color.orange);
    	else if (scoretime2 > 70) computer_scoretime.setBackground(Color.green);

    	if (scoredispersion2 <= 40) computerscoredispersion.setBackground(Color.red);
    	else if (scoredispersion2 > 40 && scoredispersion2 <= 70) computer_scoredispersion.setBackground(Color.orange);
    	else if (scoredispersion2 > 70) computer_scoredispersion.setBackground(Color.green);

    	if (scoretemp2 <= 40) computer_scoretemp.setBackground(Color.red);
    	else if (scoretemp2 > 40 && scoretemp2 <= 70) computer_scoretemp.setBackground(Color.orange);
    	else if (scoretemp2 > 70) computer_scoretemp.setBackground(Color.green);

    	if (scoretotal2 <= 40) computer_scoretotal.setBackground(Color.red);
    	else if (scoretotal2 > 40 && scoretotal2 <= 70) computer_scoretotal.setBackground(Color.orange);
    	else if (scoretotal2 > 70) computer_scoretotal.setBackground(Color.green);
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