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
		for(int i = 0; i < dish.length; i++)
		{
			dish[i] = false;
		}
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
	    
	    for(int i = 0; i < dish_input.length; i++)
	    {
	    	if(dish_input[i].equals("yes"))
	    		dish[i] = true;
	    }
	       
	    System.out.println(" ");
	}
	
	/*
	 * Generates a random order for the head chef (player) to introduce delays for.
	 * Each dish can be ordered only once.
	 * @return void
	 */
	void generate_order()
	{
		double value = random_var();
		
		// select one dish
		if(value >= 0 && value < 0.25)
		{
			double val1 = random_var();
			
			if(val1 >= 0 && val1 < 0.25)
				dish[0] = true;
			else if(val1 >= 0.25 && val1 < 0.50)
				dish[1] = true;
			else if(val1 >= 0.50 && val1 < 0.75)
				dish[2] = true;
			else
				dish[3] = true;
		}

		// select two dishes
		else if(value >= 0.25 && value < 0.50)
		{
			double val2 = random_var();
			
			// select the first dish
			if(val2 >= 0 && val2 < 0.25)
			{
				dish[0] = true;
				
				double val2a = random_var();
				
				// select the second dish
				if(val2a >= 0 && val2a < 0.33)
					dish[1] = true;
				else if(val2a >= 0.33 && val2a < 0.66)
					dish[2] = true;
				else
					dish[3] = true;
			}
			else if(val2 >= 0.25 && val2 < 0.50)
			{
				dish[1] = true;
				
				double val2b = random_var();
				
				if(val2b >= 0 && val2b < 0.33)
					dish[0] = true;
				else if(val2b >= 0.33 && val2b < 0.66)
					dish[2] = true;
				else
					dish[3] = true;
			}
			else if(val2 >= 0.50 && val2 < 0.75)
			{
				dish[2] = true;
			
				double val2c = random_var();
				
				if(val2c >= 0 && val2c < 0.33)
					dish[0] = true;
				else if(val2c >= 0.33 && val2c < 0.66)
					dish[1] = true;
				else
					dish[3] = true;
			}
			else
			{
				dish[3] = true;
			
				double val2d = random_var();
				
				if(val2d >= 0 && val2d < 0.33)
					dish[0] = true;
				else if(val2d >= 0.33 && val2d < 0.66)
					dish[1] = true;
				else
					dish[2] = true;
			}
		}
		
		// select three dishes
		else if(value >= 0.50 && value < 0.75)
		{
			double val3 = random_var();
			
			// select the first dish
			if(val3 >= 0 && val3 < 0.25)
			{
				dish[0] = true;
				
				double val3a = random_var();
				
				// select the second dish
				if(val3a >= 0 && val3a < 0.33)
				{
					dish[1] = true;
				
					double val3aa = random_var();
					
					// select the third dish
					if(val3aa >= 0 && val3aa < 0.50)
						dish[2] = true;
					else
						dish[3] = true;
				}
				else if(val3a >= 0.33 && val3a < 0.66)
				{
					dish[2] = true;
					
					double val3ab = random_var();
					
					if(val3ab >= 0 && val3ab < 0.50)
						dish[1] = true;
					else
						dish[3] = true;
				}
				else
				{
					dish[3] = true;
					
					double val3ac = random_var();
					
					if(val3ac >= 0 && val3ac < 0.50)
						dish[1] = true;
					else
						dish[2] = true;
				}
			}
			else if(val3 >= 0.25 && val3 < 0.50)
			{
				dish[1] = true;
				
				double val3b = random_var();
				
				if(val3b >= 0 && val3b < 0.33)
				{
					dish[0] = true;
				
					double val3ba = random_var();
					
					if(val3ba >= 0 && val3ba < 0.50)
						dish[2] = true;
					else
						dish[3] = true;
				}
				else if(val3b >= 0.33 && val3b < 0.66)
				{
					dish[2] = true;
					
					double val3bb = random_var();
					
					if(val3bb >= 0 && val3bb < 0.50)
						dish[0] = true;
					else
						dish[3] = true;
				}
				else
				{
					dish[3] = true;
					
					double val3bc = random_var();
					
					if(val3bc >= 0 && val3bc < 0.50)
						dish[0] = true;
					else
						dish[2] = true;
				}
			}
			else if(val3 >= 0.50 && val3 < 0.75)
			{
				dish[2] = true;
			
				double val3c = random_var();
				
				if(val3c >= 0 && val3c < 0.33)
				{
					dish[0] = true;
					
					double val3ca = random_var();
					
					if(val3ca >= 0 && val3ca < 0.50)
						dish[1] = true;
					else
						dish[3] = true;
				}	
				else if(val3c >= 0.33 && val3c < 0.66)
				{
					dish[1] = true;
					
					double val3cb = random_var();
					
					if(val3cb >= 0 && val3cb < 0.50)
						dish[0] = true;
					else
						dish[3] = true;
				}
				else
				{
					dish[3] = true;
					
					double val3cc = random_var();
					
					if(val3cc >= 0 && val3cc < 0.50)
						dish[0] = true;
					else
						dish[1] = true;
				}
			}
			else
			{
				dish[3] = true;
			
				double val3d = random_var();
				
				if(val3d >= 0 && val3d < 0.33)
				{
					dish[0] = true;
					
					double val3da = random_var();
					
					if(val3da >= 0 && val3da < 0.50)
						dish[1] = true;
					else
						dish[2] = true;
				}
				else if(val3d >= 0.33 && val3d < 0.66)
				{
					dish[1] = true;
					
					double val3db = random_var();
					
					if(val3db >= 0 && val3db < 0.50)
						dish[0] = true;
					else
						dish[2] = true;
				}
				else
				{
					dish[2] = true;
					
					double val3dc = random_var();
					
					if(val3dc >= 0 && val3dc < 0.50)
						dish[0] = true;
					else
						dish[1] = true;
				}
			}
		}
		
		// select four dishes
		else
		{
			for(int i = 0; i < dish.length; i++)
			{
				dish[i] = true;
			}
		}
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
		double delay = norm_random_var();
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
		// Timers not running concurrently
		
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
			//table_1.take_order();
			table_1.generate_order();
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
