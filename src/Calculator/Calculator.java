package Calculator;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Calculator extends JFrame implements ActionListener{
     
	private static final String FONT_STYLE="Times new Roman";
	private static final String LOOK_AND_FEEL="com.sun.java.swing.plaf.nimbus,NimbusLookAndFeel";
	Font font = new Font(FONT_STYLE, Font.BOLD, 14);
	OperatorName currentOperator;
	JPanel[] row=new JPanel[5]; //for reference array of panel
	JButton[] buttons=new JButton[19]; //to create button;
		String[] buttonString={"7","8","9","+",
				               "4","5","6","-",
				               "1","2","3","*",
				               ".", "/", "C", "�", "+/-",
				   			"=", "0" };
		JTextArea display=new JTextArea(2,20);// to set screen for text 2*20 size of screen
	int[] dimW={300,45,100,90};
	int[] dimH={35,40};
	Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
	Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
	Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
	Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]);
	
	double[] temporary={0,0};
	public Calculator(){
		super("Calculator");//to write calculator on UI calculator
	     init();
	}
	
	public void init(){
		setVisible(true);//property of frame(of calculator) so that it is visible and stable
       	setDesign();
       	setSize(380,250);//to set size of frame
       	setResizable(false); //so that frame cannot be resize nor can be extends
       	setDefaultCloseOperation(EXIT_ON_CLOSE);
       	GridLayout grid=new GridLayout(5,5);//type of layout
       	setLayout(grid);// to set layout of frame as grid layout
       	
       	FlowLayout f1=new FlowLayout(FlowLayout.CENTER);//flow layout is set when layout come one after another we make two flow layout one for screen and another for button
       	FlowLayout f2=new FlowLayout(FlowLayout.CENTER,1,1);
       	for(int i=0;i<5;i++)//to make panel
       	{
       		row[i]=new JPanel();//to create panel we cannot see panel 
       	}
       	row[0].setLayout(f1);//we have set layout for frame as well as for panels
       	
       	for(int i=1;i<5;i++)
       	{
       		row[i].setLayout(f2);
       	}
       	
       	for(int i=0;i<19;i++)
       	{
       		buttons[i]=new JButton();
       		buttons[i].setText(buttonString[i]);
       		buttons[i].setBackground(Color.blue);
       		buttons[i].setFont(font);
       		buttons[i].addActionListener(this);
       	}
       	display.setFont(font);// have to create object of font 
       	
       
       	display.setEditable(false);// so that we can only edit screen by buttons not keyboard
       	display.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);//how words are alligned in screen
       	display.setPreferredSize(new Dimension(300,35));
 
       	for (int i = 0; i < 14; i++)
			buttons[i].setPreferredSize(regularDimension);
		for (int i = 14; i < 18; i++)
			buttons[i].setPreferredSize(rColumnDimension);
		buttons[18].setPreferredSize(zeroButDimension);
		addButtonsToRow();
		setVisible(true);
	}
	

	private void addButtonsToRow() {
		row[0].add(display); // to add screen to row
		add(row[0]); // to add panel to frame
		for(int i=0;i<4;i++)
		{
			row[1].add(buttons[i]);
		}
		row[1].add(buttons[14]);
		add(row[1]);
		for (int i = 4; i < 8; i++) {
			row[2].add(buttons[i]);
		}
		row[2].add(buttons[15]);
		add(row[2]);

		for (int i = 8; i < 12; i++) {
			row[3].add(buttons[i]);
		}
		row[3].add(buttons[16]);
		add(row[3]);

		row[4].add(buttons[18]);
		for (int i = 12; i < 14; i++) {
			row[4].add(buttons[i]);
		}
		row[4].add(buttons[17]);
		add(row[4]);
	}
	
	public void clear() {
		try {
			display.setText("");
			currentOperator = OperatorName.NONE;
			for (int i = 0; i < 2; i++)
				temporary[i] = 0;
		} catch (NullPointerException e) {
		}
	}

	
	public void getSqrt() {
		try {
			double value = Math.sqrt(Double.parseDouble(display.getText()));
			display.setText(Double.toString(value));
		} catch (NumberFormatException e) {
		}
	}

	public void getPosNeg() {
		try {
			double value = Double.parseDouble(display.getText());
			if (value != 0) {
				value = value * (-1);
				display.setText(Double.toString(value));
			} else {
			}
		} catch (NumberFormatException e) {
		}
	}

	public void getResult() {
		double result = 0;
		temporary[1] = Double.parseDouble(display.getText());
		try {
			switch (currentOperator) {
			case MULTIPLICATION:
				result = temporary[0] * temporary[1];
				break;
			case DIVISION:
				result = temporary[0] / temporary[1];
				break;
			case ADDITION:
				result = temporary[0] + temporary[1];
				break;
			case SUBTRACTION:
				result = temporary[0] - temporary[1];
				break;
			default:
				break;
			}
			display.setText(Double.toString(result));
			currentOperator = OperatorName.NONE;
		} catch (NumberFormatException e) {
		}
	}

	public final void setDesign() {
		try {
			UIManager.setLookAndFeel(LOOK_AND_FEEL);
		} catch (Exception e) {
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == buttons[0])
			display.append("7");
		if (ae.getSource() == buttons[1])
			display.append("8");
		if (ae.getSource() == buttons[2])
			display.append("9");
		if (ae.getSource() == buttons[3]) {
			temporary[0] = Double.parseDouble(display.getText());
			currentOperator = OperatorName.ADDITION;
			display.setText("");
		}
		if (ae.getSource() == buttons[4])
			display.append("4");
		if (ae.getSource() == buttons[5])
			display.append("5");
		if (ae.getSource() == buttons[6])
			display.append("6");
		if (ae.getSource() == buttons[7]) {
			temporary[0] = Double.parseDouble(display.getText());
			currentOperator = OperatorName.SUBTRACTION;
			display.setText("");
		}
		if (ae.getSource() == buttons[8])
			display.append("1");
		if (ae.getSource() == buttons[9])
			display.append("2");
		if (ae.getSource() == buttons[10])
			display.append("3");
		if (ae.getSource() == buttons[11]) {
			temporary[0] = Double.parseDouble(display.getText());
			currentOperator = OperatorName.MULTIPLICATION;
			display.setText("");
		}
		if (ae.getSource() == buttons[12]){
			display.append(".");
		}
		if (ae.getSource() == buttons[13]) {
			temporary[0] = Double.parseDouble(display.getText());
			currentOperator = OperatorName.DIVISION;
			display.setText("");
		}
		if (ae.getSource() == buttons[14]){
			clear();
		}
		if (ae.getSource() == buttons[15]){
			getSqrt();
		}
		if (ae.getSource() == buttons[16]){
			getPosNeg();
		}
		if (ae.getSource() == buttons[17]){
			getResult();
		}
		if (ae.getSource() == buttons[18]){
			display.append("0");
		}
	}

	public static void main(String[] arguments) {
		Calculator c = new Calculator();
	}
}

	

