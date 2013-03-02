
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
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





// This program illustrates a simple applet with a TextField,

// Panel, Button, Choice menu, and Canvas.
public class RestaurantMatrix extends Applet  {
TextField tf, dish1showorder, dish2showorder, dish3showorder, dish4showorder,
scoredish1, scoredish2, scoredish3, scoredish4;
DrawCanvas c;
Button drawBtn;
//Button preparedish1;
Choice ch;
// Add the Components to the screen...

boolean dish_1, dish_2, dish_3, dish_4;


public void init() {

 // Set up display area...
 resize(900,600);
  
 int rows = 0;
 int columns = 6;
 setLayout(new GridLayout(rows,columns,5,5)); 
 setFont(new Font("Calibri", Font.BOLD, 10));
 setBackground(new Color(85,153,187)); 
 //setBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70));

 
 //SET Header rows - play game and scoreboard
 
 final Button playgame = new Button("CLICK to PLAY");
 add(playgame);
 
 playgame.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 //gamestatus.setText("PLAY");
		 playgame.setBackground(Color.GREEN);
		 //Define order creation method
		//table table_1 = new table(1);
		 
		 //RestaurantMatrix table_1 = new RestaurantMatrix();
		 
		 create_order();
		 
	 }
 });
 
 
 TextField scoretime = new TextField("SCORE - Time: TBC");
 add(scoretime);
 scoretime.setBackground(Color.yellow);
 TextField scoredispersion = new TextField("SCORE - Dispersion: TBC");
 add(scoredispersion);
 scoredispersion.setBackground(Color.yellow);
 TextField scoretemp = new TextField("SCORE - Temperature: TBC");
 add(scoretemp);
 scoretemp.setBackground(Color.yellow);
 TextField scoretotal = new TextField("SCORE Score: TBC");
 add(scoretotal);
 scoretotal.setBackground(Color.yellow);
 TextField computerscore = new TextField("COMPUTER SCORE: TBC");
 add(computerscore);
 computerscore.setBackground(Color.yellow);
 
 //Matrix headings
 //menuoptions.setBounds(5, 5, 100, 100);
 //menuoptions.setPreferredSize(new Dimension(80,20));

 
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
 
 //Line entries - dish - 1
 add(new TextField("Table 1 - Soup"));
 add(new TextField("2 seconds"));
 dish1showorder = new TextField("0");
 add(dish1showorder);
 Button preparedish1 = new Button("CLICK to prepare");
 add(preparedish1);
 final TextField dish1status = new TextField("Prepared/Ready");
 add(dish1status);
 scoredish1 = new TextField("SCORE - Time: TBC");
 add(scoredish1);
 
 //Line entries - dish - 2
 add(new TextField("Table 1 - Steak"));
 add(new TextField("4 seconds"));
 dish2showorder = new TextField("0");
 add(dish2showorder);
 Button preparedish2 = new Button("CLICK to prepare");
 add(preparedish2);
 final TextField dish2status = new TextField("Prepared/Ready");
 add(dish2status);
 scoredish2 = new TextField("SCORE - Dispersion: TBC");
 add(scoredish2);
 
//Line entries - dish - 3
add(new TextField("Table 1 - Burger"));
add(new TextField("10 seconds"));
dish3showorder = new TextField("0");
add(dish3showorder);
Button preparedish3 = new Button("CLICK to prepare");
add(preparedish3);
final TextField dish3status = new TextField("Prepared/Ready");
add(dish3status);
scoredish3 = new TextField("SCORE - Temperature: TBC");
add(scoredish3); 

//Line entries - dish - 4
add(new TextField("Table 1 - Fish"));
add(new TextField("10 seconds"));
dish4showorder = new TextField("0");
add(dish4showorder);
Button preparedish4 = new Button("CLICK to prepare");
add(preparedish4);
final TextField dish4status = new TextField("Prepared/Ready");
add(dish4status);
scoredish4 = new TextField("SCORE - Total: TBC");
add(scoredish4);


//add(new TextField("Table 2"));
for (int i = 0; i < 72; ++i) {
	  add(new TextField("Being Developed"));
	 }


 preparedish1.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(dish_1){
			 dish1status.setText("Being Prepared");
			 dish1status.setBackground(Color.GREEN);
			 
			 
			 final double wait_time = prepare_food(1);
	 
			 ActionListener taskPerformer = new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		                dish1status.setText("Dish is ready");
		                dish1status.setBackground(Color.gray);
		                
		                
		                int custScore = score(wait_time);
		                
		                scoredish1.setText("SCORE - Time: " + custScore);
		                
		                if (custScore <= 40)
		                scoredish1.setBackground(Color.red);
		                else if (custScore > 40 && custScore <= 70)
		                scoredish1.setBackground(Color.orange);
		                else if (custScore > 70)
			            scoredish1.setBackground(Color.green);
		            }
		            };
		      
		        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
		        timer.setRepeats(false);
		        timer.start();
		        try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 
		 }}
 });
 
 preparedish2.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(dish_2){
			 dish2status.setText("Being Prepared");
		 dish2status.setBackground(Color.GREEN);
		 
		 final double wait_time = prepare_food(2);
		 
		 ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                dish2status.setText("Dish is ready");
	                dish2status.setBackground(Color.gray);
	                
	                int custScore = score(wait_time);
	                
	                scoredish2.setText("SCORE - Dispersion: " + custScore);
	                
	                if (custScore <= 40)
	                scoredish2.setBackground(Color.red);
	                else if (custScore > 40 && custScore <= 70)
	                scoredish2.setBackground(Color.orange);
	                else if (custScore > 70)
		            scoredish2.setBackground(Color.green);
	            
	            }
	            };
	         
	            
	            
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 }}
 });
 

 preparedish3.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
		 if(dish_3){
			 dish3status.setText("Being Prepared");	 
		 dish3status.setBackground(Color.GREEN);
		 
		  final double wait_time = prepare_food(3);
		 
		  ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                dish3status.setText("Dish is ready");
	                dish3status.setBackground(Color.gray);
	                
	                int custScore = score(wait_time);
	                
	                scoredish3.setText("Score: " + custScore);
	                
	                if (custScore <= 40)
	                scoredish3.setBackground(Color.red);
	                else if (custScore > 40 && custScore <= 70)
	                scoredish3.setBackground(Color.orange);
	                else if (custScore > 70)
		            scoredish3.setBackground(Color.green);
	           
	            }
	            };
	         
	           
	            
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 }}
 });
 
 preparedish4.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent e){
if (dish_4){		
		 dish4status.setText("Being Prepared");
		 dish4status.setBackground(Color.GREEN);
		 
		 final double wait_time = prepare_food(4);
		 
		 ActionListener taskPerformer = new ActionListener() {
	          
	            public void actionPerformed(ActionEvent evt) {
	                dish4status.setText("Dish is ready");
	                dish4status.setBackground(Color.gray);
	                
	                int custScore = score(wait_time);
	                
	                scoredish4.setText("Score: " + custScore);
	                
	                if (custScore <= 40)
	                scoredish4.setBackground(Color.red);
	                else if (custScore > 40 && custScore <= 70)
	                scoredish4.setBackground(Color.orange);
	                else if (custScore > 70)
		            scoredish4.setBackground(Color.green);
	                
	            }
	            };
	         
	            
	            
	        Timer timer = new Timer( (int) (1000*wait_time) , taskPerformer);
	        timer.setRepeats(false);
	        timer.start();
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  
		}	 
	 }
 });
 

 
 
 //setLayout(new BorderLayout());

 // Add the components...
 // Add the text at the top.
// tf = new TextField();
// add("North",tf);

 // Add the custom Canvas to the center
 //c = new DrawCanvas(this);
 //add("Center",c);
 
 // Create the panel with button and choices at the bottom...
 /*Panel p = new Panel();
 drawBtn = new Button("Play Options");
 p.add(drawBtn);
 // Create the choice box and add the options...
 ch = new Choice();
 ch.addItem("Play Game");
 ch.addItem("Stop Game");
 p.add(ch);
 add("North",p);*/
}

void create_order() {
	// TODO Auto-generated method stub
	
	dish_1 = true;
	dish_2 = true;
	dish_3 = false;
	dish_4 = false;	
	
	if(dish_1){
	dish1showorder.setText("Items ordered: 1");
	dish1showorder.setBackground(Color.GREEN);} 
	else dish1showorder.setText("Items ordered: 0");
	
	if(dish_2){
	 dish2showorder.setText("Items ordered: 1");
	 dish2showorder.setBackground(Color.GREEN);}
	else dish2showorder.setText("Items ordered: 1");
	
	if(dish_3){
	 dish3showorder.setText("Items ordered: 0");
	 dish3showorder.setBackground(Color.GREEN);}
	else dish3showorder.setText("Items ordered: 0");
	
	if(dish_4){
	 dish4showorder.setText("Items ordered: 0");
	 dish4showorder.setBackground(Color.GREEN);}
	else dish4showorder.setText("Items ordered: 0");
}

double delay_Norm_Dist(){ 
    
    double PI = 3.141592654;
    double x,y, x_temp, y_temp;
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

double prepare_food(int dish_number){
	
	if(dish_number == 1) return delay_Norm_Dist()*5.0;
	else if (dish_number == 2) return delay_Norm_Dist()*7.0;
	else if (dish_number == 3) return delay_Norm_Dist()*8.0;
	else if (dish_number == 4) return delay_Norm_Dist()*9.0;
	else return delay_Norm_Dist()*5.3;
	
}


int score(double time){
	
	
	return ( (int) (100*time/9.0));
}




//CODE NOT REQUIRED - TO DELETE
// Handle events that have occurred
public boolean handleEvent(Event evt) {
switch(evt.id) {
 // This can be handled 
 case Event.ACTION_EVENT: {
  if(evt.target instanceof Button)    { 
   // Repaint canvas to use new choices...
	  //dish1showorder.setText("1");
   }  // end if
   return false;
 }
 default:
  return false;
 }
}

// Return the current choice to display...
public String getChoice() {
 return ch.getSelectedItem();
}

// Return the text in the list box...
public String getTextString() {
 return tf.getText();
}
}

// This is a custom canvas that is used for drawing
// text, a rectangle, or nothing...
class DrawCanvas extends Canvas {
RestaurantMatrix e1app;
 // Constructor - store the applet to get drawing info...
 public DrawCanvas(RestaurantMatrix a) {
  e1app = a;
 }
 // Draw the image per the choices in the applet...
 public synchronized void paint (Graphics g) {
  // Get the current size of the display area...
  Dimension dm = size();
  // Draw based on choice...
  String s = e1app.getChoice();
  // Calculate center coordinates...
  int x,y,width,height;
  x = dm.width/4;
  y = dm.height / 4;
  width = dm.width / 2;
  height = dm.height / 2;
  // Paint a rectangle in the center...
  if (s.compareTo("Rectangle") == 0) {
   // Draw the rectangle in the center with colors! 
   g.setColor(Color.blue);
   g.drawRect(x,y,width,height);
   g.setColor(Color.yellow);
   g.fillRect(x + 1,y + 1,width - 2,height - 2); 
  } // end if
  // Get the text in the applet and display in the middle...
  if (s.compareTo("Text") == 0) {
   String displayText = e1app.getTextString(); 
   g.setColor(Color.red);
   g.drawString(displayText,x,y + (height/2)); 
  }
 }
} 
