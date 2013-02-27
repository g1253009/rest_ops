import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.awt.event.*;
import javax.swing.*;
//import java.util.Timer;
//import java.util.TimerTask;

public class Restaurant
{	
	boolean[] dish = new boolean[4];
	
	static double[] dish_HC_delay = new double[4];

	static double dish_prep[] = new double[4];
	
	static int table_number;
	
	/*
	 * Restaurant constructor.
	 * @param number
	 */
	public Restaurant(int number)
	{
		dish[0] = false;
		dish[1] = false;
		dish[2] = false;
		dish[2] = false;
		table_number = number;		
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
     * Can also use this for the delay.
     * Uses the built-in nextGaussian method.
     * @return double
     */
	public static double delay_Norm_Dist_2()
	{
		Random generator = new Random();
		
		return(generator.nextGaussian());
	}
	
    /*
     * Accepts order from the player.
     * @return void
     */
	public void take_order()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		String[] dish_input = new String[4];
		 
	    System.out.println("Place Order: enter items to be ordered 'yes' or 'no': ");
	    System.out.println("Soup:");
	    try
	    {
	       dish_input[0] = br.readLine();
	    }
	    catch (IOException ioe)
	    {
	       System.out.println("Error: please enter 'yes' or 'no'");
	       System.exit(1);
	    }
	      
	    System.out.println("Steak:");
	    try
	    {
	       dish_input[1] = br.readLine();
	    }
	    catch (IOException ioe)
	    {
	       System.out.println("Error: please enter 'yes' or 'no'");
	       System.exit(1);
	    }
		
	    System.out.println("Burger:");
	    try
	    {
	       dish_input[2] = br.readLine();
	    }
	    catch (IOException ioe)
	    {
	       System.out.println("Error: please enter 'yes' or 'no'");
	       System.exit(1);
	    }
	      
	    System.out.println("Fish:");
	    try
	    {
	       dish_input[3] = br.readLine();
	    }
	    catch (IOException ioe)
	    {
	       System.out.println("Error: please enter 'yes' or 'no'");
	       System.exit(1);
	    }
	      
	    if(dish_input[0].equals("yes"))
	     dish[0] = true; 
	    if(dish_input[1].equals("yes"))
	     dish[1] = true;
	    if(dish_input[2].equals("yes"))
	     dish[2] = true;
	    if(dish_input[3].equals("yes"))
	     dish[3] = true;
	       
	    System.out.println(" ");
	}
	
	/*
	 * Prints out the confirmed order to the screen.
	 * @return void 
	 */
	public void confirm_order()
	{
		System.out.println("Order Confirmation: table " + table_number + " has ordered:");

		if(dish[0])
			System.out.println("1 soup - expected preparation time - 2 seconds");
		else
			System.out.println("0 soup ");
		if(dish[1])
			System.out.println("1 steak - expected preparation time - 5 seconds");
		else
			System.out.println("0 steak ");
		if(dish[2])
			System.out.println("1 burger - expected preparation time - 10 seconds");
		else
			System.out.println("0 burger ");
		if(dish[3])
			System.out.println("1 fish - expected preparation time - 15 seconds");
		else
			System.out.println("0 fish ");

		System.out.println(" ");
	}
	
	/*
	 * Allows the player to enter the required delay for each dish.
	 * @return void
	 */
	public void head_chef_delay()
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] dish_delay = new String[4];
		
		// show player the expected preparation times
		System.out.println("Head Chef: order ready for preparation: ");

		if(dish[0])
			System.out.println(" 1 soup - expected preparation time: 2 seconds");
		else
			System.out.println(" 0 soup ");
		if(dish[1])
			System.out.println(" 1 steak - expected preparation time: 5 seconds");
		else
			System.out.println(" 0 steak ");
		if(dish[2])
			System.out.println(" 1 burger - expected preparation time: 10 seconds");
		else
			System.out.println(" 0 burger ");
		if(dish[3])
			System.out.println(" 1 fish - expected preparation time: 15 seconds");
		else
			System.out.println(" 0 fish ");

		System.out.println("Confirm delay for each dish");

		// allow player to enter delays for each dish
		if(dish[0])
		{
			System.out.println("Enter soup delay: "); 
			try
			{
		         dish_delay[0] = br.readLine();
		    }
			catch (IOException ioe)
			{
		         System.out.println("Error: please enter a number in seconds");
		         System.exit(1);
		    }
			dish_HC_delay[0] = Integer.parseInt(dish_delay[0]);
		}

		if(dish[1])
		{
			System.out.println("Enter steak delay: "); 
			try
			{
		         dish_delay[1] = br.readLine();
		    }
			catch (IOException ioe)
			{
		         System.out.println("Error: please enter a number in seconds");
		         System.exit(1);
		    }
			dish_HC_delay[1] = Integer.parseInt(dish_delay[1]);
		}
		
		if(dish[2])
		{
			System.out.println("Enter burger delay: "); 
			try
			{
		         dish_delay[2] = br.readLine();
		    }
			catch (IOException ioe)
		    {
		         System.out.println("Error: please enter a number in seconds");
		         System.exit(1);
		    }
			dish_HC_delay[2] = Integer.parseInt(dish_delay[2]);
		}
		
		if(dish[3])
		{
			System.out.println("Enter fish delay: "); 
			try
			{
		         dish_delay[3] = br.readLine();
		    }
			catch (IOException ioe)
			{
		         System.out.println("Error: please enter a number in seconds");
		         System.exit(1);
		    }
			dish_HC_delay[3] = Integer.parseInt(dish_delay[3]);
		}
		
		System.out.println(" ");
	}
	
	/*
	 * Adds an additional random delay to the user-specified delay for each dish.
	 * @return void
	 */
	public void food_prep()
	{
		// Preparation time is a constant plus a random delay.
		double delay = delay_Norm_Dist_2();
		dish_prep[0] = 2 + delay;
		dish_prep[1] = 5 + delay;
		dish_prep[2] = 10 + delay;
		dish_prep[3] = 15 + delay;	
	}
	
	/*
	 * Starts off the dish preparation and confirms the final preparation time
	 * when the dish is ready.
	 * @return void
	 */
	public void food_ready()
	{	
		/* Timers not running concurrently*/
		
		if(dish[0])
		{	
			final double prep_time = Math.abs(dish_HC_delay[0] + dish_prep[0]);
			
			ActionListener taskPerformer = new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					//System.out.println("Soup is ready for table " + table_number);
					//System.out.println("Soup took " + prep_time + " seconds");
					System.out.format("Soup is ready for table " + table_number + " and took approximately %.0f seconds%n", prep_time);
				}
            };
            
            Timer timer = new Timer((int)(1000*(dish_HC_delay[0] + dish_prep[0])), taskPerformer);
            timer.setRepeats(false);
            timer.start();
        
            try
            {
            	Thread.sleep((int)(1000*prep_time));
            }
            catch
            (InterruptedException e)
            {
            	e.printStackTrace();
            }
        
			//Timer timer = new Timer("HC Delay");
			//MyTask t = new MyTask();
			//timer.schedule(t, 1000*(dish_1_HC_delay+dish_1_prep), 1000*(dish_1_HC_delay+dish_1_prep));
			//System.out.println(" Soup took: ");
			//System.out.println(dish_1_HC_delay + dish_1_prep);
		}
		
		if(dish[1])
		{
			final double prep_time = Math.abs(dish_HC_delay[1] + dish_prep[1]);
			
			ActionListener taskPerformer = new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					//System.out.println("Steak is ready for table " + table_number);
					//System.out.println("Steak took " + prep_time + " seconds");
					System.out.format("Steak is ready for table " + table_number + " and took approximately %.0f seconds%n", prep_time);
				}
            };
           
            Timer timer = new Timer((int)(1000*(dish_HC_delay[1] + dish_prep[1])), taskPerformer);
            timer.setRepeats(false);
            timer.start();
       
            try
            {
            	Thread.sleep((int)(1000*prep_time));
            }
            catch (InterruptedException e)
            {
            	e.printStackTrace();
            }		
       }
		
		if(dish[2])
		{
			final double prep_time = Math.abs(dish_HC_delay[2] + dish_prep[2]);
			
			ActionListener taskPerformer = new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					//System.out.println("Burger is ready for table " + table_number);
					//System.out.println("Burger took " + prep_time + " seconds");
					System.out.format("Burger is ready for table " + table_number + " and took approximately %.0f seconds%n", prep_time);
				}
            };
           
            Timer timer = new Timer((int)(1000*(dish_HC_delay[2] + dish_prep[2])), taskPerformer);
            timer.setRepeats(false);
            timer.start();
       
            try
            {
            	Thread.sleep((int)(1000*prep_time));
            }
            catch (InterruptedException e)
            {
            	e.printStackTrace();
            }
		}
		
		if(dish[3])
		{
			final double prep_time = Math.abs(dish_HC_delay[3] + dish_prep[3]);
			
			ActionListener taskPerformer = new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					//System.out.println("Fish is ready for table " + table_number);
					//System.out.println("Fish took " + prep_time + " seconds");
					System.out.format("Fish is ready for table " + table_number + " and took approximately %.0f seconds%n", prep_time);
				}
            };
           
            Timer timer = new Timer((int)(1000*(dish_HC_delay[3] + dish_prep[3])), taskPerformer);
            timer.setRepeats(false);
            timer.start();
       
            try
            {
            	Thread.sleep((int)(1000*prep_time));
            }
            catch (InterruptedException e)
            {
            	e.printStackTrace();
            }
		}
	}
	
	/*
	 * Game controlled through a while loop test on the answer to the question "start taking orders".
	 * Main function proceeds through process methods of order, food preparation etc.
	 */
	public static void main(String[] args)
	{		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String play_game = null;
		 
	    System.out.println("Would you like to start taking orders? Enter yes or no: ");
		
	    try
	    {
	    	play_game = br.readLine();
	    }
	    catch (IOException ioe)
	    {
	         System.out.println("Error: please enter yes or no");
	         System.exit(1);
	    }

		while(play_game.equals("yes"))
		{	
			Restaurant table_1 = new Restaurant(1);
			table_1.take_order();
			//table_1.confirm_order();
			table_1.head_chef_delay();		
			table_1.food_prep();	
			table_1.food_ready();
		
			System.out.println("Would you like to take more orders? Enter yes or no: ");
		
			try
			{
				play_game = br.readLine();
			}
			catch (IOException ioe)
			{
				System.out.println("Error: please enter yes or no");
				System.exit(1);
			}
		}
		
		System.out.println("Thanks for playing!");
	}
}
