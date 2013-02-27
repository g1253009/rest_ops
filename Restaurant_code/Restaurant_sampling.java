import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.*;
import javax.swing.*;
//import java.util.Timer;
//import java.util.TimerTask;

public class Restaurant
{	
	boolean dish_1, dish_2, dish_3, dish_4;
	
	static double dish_1_HC_delay, dish_2_HC_delay, dish_3_HC_delay, dish_4_HC_delay;

	static double dish_1_prep, dish_2_prep, dish_3_prep, dish_4_prep;
	
	static int table_number;
	
	/*
	 * Restaurant constructor.
	 * @param number
	 */
    public Restaurant(int number)
    {
    	dish_1 = false;
		dish_2 = false;
		dish_3 = false;
		dish_4 = false;
		table_number = number;		
	}
    
    /*
     * Produces a normal random variable for the dish delays.
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
     * Accepts order from the player.
     * @return void
     */
	public void take_order()
	{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 String dish_1_input = null;
		 String dish_2_input = null;
		 String dish_3_input = null;
		 String dish_4_input = null;
		 
	     System.out.println("Place Order: enter items to be ordered 'yes' or 'no': ");
	     System.out.println("Soup:");
		
	     try
	     {
	        dish_1_input = br.readLine();
	     }
	     catch (IOException ioe)
	     {
	        System.out.println("Error: please enter 'yes' or 'no'");
	        System.exit(1);
	     }
	      
	     System.out.println("Steak:");
			
	     try
	     {
	        dish_2_input = br.readLine();
	     }
	     catch (IOException ioe)
	     {
	        System.out.println("Error: please enter 'yes' or 'no'");
	        System.exit(1);
	     }
		
	     System.out.println("Burger:");
			
	     try
	     {
	        dish_3_input = br.readLine();
	     }
	     catch (IOException ioe)
	     {
	        System.out.println("Error: please enter 'yes' or 'no'");
	        System.exit(1);
	     }
	      
	     System.out.println("Fish:");
			
	     try
	     {
	        dish_4_input = br.readLine();
	     }
	     catch (IOException ioe)
	     {
	        System.out.println("Error: please enter 'yes' or 'no'");
	        System.exit(1);
	     }
	      
	     if(dish_1_input.equals("yes"))
	    	 dish_1 = true; 
	     if(dish_2_input.equals("yes"))
	    	 dish_2 = true;
	     if(dish_3_input.equals("yes"))
	    	 dish_3 = true;
	     if(dish_4_input.equals("yes"))
	    	 dish_4 = true;
	       
	     System.out.println(" ");
	}
	
	/*
	 * Prints out the confirmed order to the screen.
	 * @return void 
	 */
	public void confirm_order()
	{
		System.out.println(" Order Confirmation: table ");
		System.out.println(table_number);
		System.out.println(" has ordered ");

		if(dish_1)
			System.out.println("1 soup - expected preparation time - 2 seconds");
		else
			System.out.println("0 soup ");
		if(dish_2)
			System.out.println("1 steak - expected preparation time - 5 seconds");
		else
			System.out.println("0 steak ");
		if(dish_3)
			System.out.println("1 burger - expected preparation time - 10 seconds");
		else
			System.out.println("0 burger ");
		if(dish_4)
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
		
		String dish_1_delay = null;
		String dish_2_delay = null;
		String dish_3_delay = null;
		String dish_4_delay = null;
		
		// show player the expected preparation times
		System.out.println("Head Chef: order ready for preparation: ");

		if(dish_1)
			System.out.println(" 1 soup - expected preparation time: 2 seconds");
		else
			System.out.println(" 0 soup ");
		if(dish_2)
			System.out.println(" 1 steak - expected preparation time: 5 seconds");
		else
			System.out.println(" 0 steak ");
		if(dish_3)
			System.out.println(" 1 burger - expected preparation time: 10 seconds");
		else
			System.out.println(" 0 burger ");
		if(dish_4)
			System.out.println(" 1 fish - expected preparation time: 15 seconds");
		else
			System.out.println(" 0 fish ");

		System.out.println("Confirm delay for each dish");

		// allow player to enter delays for each dish
		if(dish_1)
		{
			System.out.println("Enter soup delay: "); 
			try
			{
		         dish_1_delay = br.readLine();
		    }
			catch (IOException ioe)
			{
		         System.out.println("Error: please enter a number in seconds");
		         System.exit(1);
		    }
			
			dish_1_HC_delay = Integer.parseInt(dish_1_delay);
		}

		if(dish_2)
		{
			System.out.println("Enter steak delay: "); 
			try
			{
		         dish_2_delay = br.readLine();
		    }
			catch (IOException ioe)
			{
		         System.out.println("Error: please enter a number in seconds");
		         System.exit(1);
		    }
			
			dish_2_HC_delay = Integer.parseInt(dish_2_delay);
		}
		
		if(dish_3)
		{
			System.out.println("Enter burger delay: "); 
			try
			{
		         dish_3_delay = br.readLine();
		    }
			catch (IOException ioe)
		    {
		         System.out.println("Error: please enter a number in seconds");
		         System.exit(1);
		    }
			
			dish_3_HC_delay = Integer.parseInt(dish_3_delay);
		}
		
		if(dish_4)
		{
			System.out.println("Enter fish delay: "); 
			try
			{
		         dish_4_delay = br.readLine();
		    }
			catch (IOException ioe)
			{
		         System.out.println("Error: please enter a number in seconds");
		         System.exit(1);
		    }
			
			dish_4_HC_delay = Integer.parseInt(dish_4_delay);
		}
		
		System.out.println(" ");
	}
	
	/*
	 * Adds an additional random delay to the user-specified delay for each dish.
	 * @return void
	 */
	public void food_prep()
	{
		double delay = delay_Norm_Dist();
		dish_1_prep = 2.0 + (delay*5);
		dish_2_prep = 5.0 + (delay*10);
		dish_3_prep = 10.0 + (delay*15);
		dish_4_prep = 15.0 + (delay*20);	
	}
	
	/*
	 * Confirms the final preparation time.
	 */
	public void food_ready()
	{	
		/* Timers not running concurrently*/
		
		if(dish_1)
		{	
			final double prep_time = Math.abs(dish_1_HC_delay + dish_1_prep);
			
			ActionListener taskPerformer = new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					System.out.println("Soup is ready for table " + table_number);
					System.out.println("Soup took " + prep_time + " seconds");
					System.out.println();
				}
            };
            
            Timer timer = new Timer((int)(1000*(dish_1_HC_delay + dish_1_prep)), taskPerformer);
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
		
		if(dish_2)
		{
			final double prep_time = Math.abs(dish_2_HC_delay + dish_2_prep);
			
			ActionListener taskPerformer = new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					System.out.println("Steak is ready for table " + table_number);
					System.out.println("Steak took " + prep_time + " seconds");
					System.out.println();
				}
            };
           
            Timer timer = new Timer((int)(1000*(dish_2_HC_delay + dish_2_prep)), taskPerformer);
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
		
		if(dish_3)
		{
			final double prep_time = Math.abs(dish_3_HC_delay + dish_3_prep);
			
			ActionListener taskPerformer = new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					System.out.println("Burger is ready for table " + table_number);
					System.out.println("Burger took " + prep_time + " seconds");
					System.out.println();
				}
            };
           
            Timer timer = new Timer((int)(1000*(dish_3_HC_delay + dish_3_prep)), taskPerformer);
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
		
		if(dish_4)
		{
			final double prep_time = Math.abs(dish_4_HC_delay+dish_4_prep);
			
			ActionListener taskPerformer = new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					System.out.println("Fish is ready for table " + table_number);
					System.out.println("Fish took " + prep_time + " seconds");
					System.out.println();
				}
            };
           
            Timer timer = new Timer((int)(1000*(dish_4_HC_delay+dish_4_prep)), taskPerformer);
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
	 * Game controlled through a while loop test on the answer to the question - start taking orders.
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
