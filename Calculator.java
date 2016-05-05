import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
	public static void main(String[] args) {
		Calculator c =new Calculator();
	}
		
		JPanel[]  row = new JPanel[5];
		 JButton[] button = new JButton[19];
		 String[] buttonString = {"7","8","9","+",
				 							"4","5","6","-",
				 							"1","2","3","*",
				 							".","/","C","√",
				 							"+/-","=","0"};
		 int[] dimW={300,45,100,90};
		 int[] dimH={35,40};
		 Dimension dispDimension=new Dimension(dimW[0],dimH[0]);
		 Dimension regularDimension = new Dimension(dimW[1],dimH[1]);
		 Dimension rcolumnDimension =new Dimension(dimW[2],dimH[1]);
		 Dimension zeroButtonDimension = new Dimension(dimW[3],dimH[1]);
		 //Add 4 booleans for add/sub/mul/div
		 boolean[] function =new boolean[4];  // 4 functions with true/false values
		 double[] temporary = {0,0}; //need temporary doubles for calculations /temp[0], temp[1]
		 JTextArea display = new JTextArea(1,20); //display area
		 Font font =new Font("Times New Roman", Font.BOLD, 14);
		 //constructor
		Calculator(){
			super("Calculator");
			setDesign();
			setSize(380,250);
			setLocation(900, 400);
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			GridLayout grid = new GridLayout(5,5); //pp
			setLayout(grid);
			for(int i=0;i<4;i++){
				function[i]=false;  //all 4 functionsa set to false;
			}
			FlowLayout f1=new FlowLayout(FlowLayout.CENTER);
			FlowLayout f2=new FlowLayout(FlowLayout.CENTER,1,1);
			for(int i=0;i<5;i++){
				row[i]=new JPanel();  //5 rows including display area, 789, 456, 123, 0./=
			}
			row[0].setLayout(f1); //display area center
			
			for(int i=1;i<5;i++){
				row[i].setLayout(f2); 
			}
			//few same things for every button, need to create a loop
			for(int i=0;i<19;i++){ //total 18 buttons
				button[i]=new JButton();
				button[i].setText(buttonString[i]);
				button[i].setFont(font);
				button[i].addActionListener(this);	 //button -> text, label, actionListener, font
			}
			display.setFont(font); //display area font
			display.setEditable(false); //not from keyboard
			display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); //   <- 
			display.setPreferredSize(dispDimension);   //height, width
			
			for(int i=0;i<14;i++){
				button[i].setPreferredSize(regularDimension);// from 0 to 13 
			}
			for (int i = 14; i < 18; i++) {
				button[i].setPreferredSize(rcolumnDimension); // C, √, +/-, =
			}
			button[18].setPreferredSize(zeroButtonDimension); //0= 18th button
		
		row[0].add(display); //0th row = display area
		add(row[0]); //pp // display area added but where
		
		for(int i=0;i<4;i++)
			row[1].add(button[i]);
			row[1].add(button[14]);
			add(row[1]);
		for(int i=4;i<8;i++)
			row[2].add(button[i]);
			row[2].add(button[15]);
			add(row[2]);
		for(int i=8;i<12;i++)
			row[3].add(button[i]);
			row[3].add(button[16]);
			add(row[3]);
		
		row[4].add(button[18]);
			for(int i =12;i<14;i++)
				row[4].add(button[i]);
			row[4].add(button[17]);
			add(row[4]);
			setVisible(true); 
	}
		public void clear(){
			try {
				display.setText("");
				for (int i = 0; i < 4; i++) {
					function[i]=false;
				for(int j=0;j<2;j++)
					temporary[j]=0;
			} 
			}catch (NullPointerException e) {
			}}
		public void getSqrt(){
			try {
				double value = Math.sqrt(Double.parseDouble(display.getText()));
				display.setText(Double.toString(value));
			} catch (NumberFormatException e) {
			}
		}
		public void getPosNeg(){
			try {
				double value = Double.parseDouble(display.getText());
				if(value!=0){
					value*=(-1);
					display.setText(Double.toString(value));
				}else{
				}
			} catch (Exception e) {
			}
		}
		public void getResult(){
			double result =0;
			temporary[1] = Double.parseDouble(display.getText());//eg 23.67-9.334 string ; split 
			String temp0 = Double.toString(temporary[0]); //eg  
			String temp1 = Double.toString(temporary[1]); // 23.67-9.334
			try {
				if(temp0.contains("-")){
				String[] temp00 = temp0.split("-", 2);
				temporary[0] = (Double.parseDouble(temp00[1]) *  -1); //ppp
				}
				if(temp1.contains("-")){
					String[] temp11 = temp1.split("-", 2);
					temporary[1] = (Double.parseDouble(temp11[1])* -1); //ppp
				}
			} catch (ArrayIndexOutOfBoundsException e) {
			}
			try {
				if(function[2]==true)
					result = temporary[0] * temporary[1];
				else if(function[3] == true)
					result = temporary[0] / temporary[1];
				else if(function[1] == true)
					result = temporary[0]-temporary[1];
				else if(function[0]==true)
					result = temporary[0]+temporary[1];
				display.setText(Double.toString(result));
				for(int i=0;i<4;i++)
					function[i]=false;
			} catch (NumberFormatException e) {
			}			
		}
	public void setDesign(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
	}

@Override
public void actionPerformed(ActionEvent ae){
	if(ae.getSource() == button[0])
		display.append("7");
	if(ae.getSource() == button[1])
		display.append("8");
	if(ae.getSource()==button[2])
		display.append("9");
	if(ae.getSource()==button[3]){
		//add function[0]
		function[0]=true; //function[0] is for +
		temporary[0]=Double.parseDouble(display.getText()); //the text before + goes into temporary[0]
		display.setText(""); //after + set text empty
	}
		if(ae.getSource()==button[4])
			display.append("4");
		if(ae.getSource()==button[5])
			display.append("5");
		if(ae.getSource()==button[6])
			display.append("6");
		if(ae.getSource()==button[7]){
			temporary[0] = Double.parseDouble(display.getText());
			function[1] = true; 
			display.setText("");
		}
		if(ae.getSource()==button[8])
			display.setText("1");
		if(ae.getSource() == button[9])
			display.setText("2");
		if(ae.getSource()==button[10])
			display.setText("3");
		if(ae.getSource()==button[11]){
			temporary[0] = Double.parseDouble(display.getText());
			function[2] = true;
			display.setText("");
		}
		if(ae.getSource()==button[12])
			display.append(".");
		if(ae.getSource()==button[13]){
			temporary[0] = Double.parseDouble(display.getText());
			function[3] =true;
			display.setText("");
		}
		if(ae.getSource()==button[14])
			clear();
		if(ae.getSource()==button[15])
			getSqrt();
		if(ae.getSource()==button[16])
			getPosNeg();
	    if(ae.getSource()==button[17])
			getResult();
		if(ae.getSource()==button[18])
			display.append("0");
}
}