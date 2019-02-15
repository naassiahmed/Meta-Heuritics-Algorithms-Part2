package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import ga.AlgGen;
import tabu.Taboue;

public class Interface extends JFrame implements ActionListener{
	
	JPanel p = new JPanel();
	JLabel leb = new JLabel("Donnez l'emplacement du benchmark :");
	JTextField eb = new JTextField();
	JButton ts = new JButton("Recherche taboue");
	JButton ga = new JButton("Algorithme génétique");
	JLabel lres = new JLabel("Solution trouvée :");
	JTextArea result = new JTextArea();
	JScrollPane jsp=new JScrollPane(result);
	
	public Interface()
	{
		this.setTitle("Metaheuristiques 2");
		this.setSize(600, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p.setLayout(null);
		
		leb.setBounds(75, 50, 225, 25);
		eb.setBounds(75, 75, 450, 25);;
		ts.setBounds(100, 125, 175, 25);
		ga.setBounds(310, 125, 175, 25);
		jsp.setBounds(75, 175, 450, 200);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		result.setEditable(false);
		
		p.add(leb);
		p.add(eb);
		p.add(ts);
		ts.addActionListener(this);
		p.add(ga);
		ga.addActionListener(this);
		p.add(jsp);
		
		this.setContentPane(p);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==ts)
		{
			Cube c = new Cube();
			try {
				Read_bench(c,eb.getText());
			}catch (IOException e1) {
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(this, "Fichier introuvable !", "Erreur !", JOptionPane.ERROR_MESSAGE);
			}
			Taboue a = null;
			try {
				a = new Taboue(c);
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
			Solution sol = new Solution();
			double start=System.currentTimeMillis();
			try {
				sol=a.Tabu_Search(c.getSeuil()).clone();
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
			double end=System.currentTimeMillis();
			String s="["+sol.getSol().get(0);
			for (int i=1;i<sol.getSol().size();i++) s=s+", "+sol.getSol().get(i);
			s=s+"]\n"+sol.getFitness()+"\nTemps : "+((end-start)/1000)+" secondes";
			result.setText(s);
		}
		
		if (e.getSource()==ga)
		{
			Cube c = new Cube();
			try {
				Read_bench(c,eb.getText());
			}catch (IOException e1) {
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(this, "Fichier introuvable !", "Erreur !", JOptionPane.ERROR_MESSAGE);
			}
			AlgGen a = null;
			try {
				//a = new AlgGen(c,10*((int) Math.pow(c.getSeuil(), 2)), 15*((int) Math.pow(c.getSeuil(), 3)), 0.6,0.1,0.7);
				a = new AlgGen(c,10*((int) Math.pow(c.getSeuil(), 2)), 15*((int) Math.pow(c.getSeuil(), 1)), 0.6,0.1,0.7);
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
			Solution sol = new Solution();
			double start=System.currentTimeMillis();
			try {
				sol=a.Algorithm().clone();
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
			double end=System.currentTimeMillis();
			String s="["+sol.getSol().get(0);
			for (int i=1;i<sol.getSol().size();i++) s=s+", "+sol.getSol().get(i);
			s=s+"]\n"+sol.getFitness()+"\nTemps : "+((end-start)/1000)+" secondes";
			result.setText(s);
		}
		
	}
	
	public static void Read_bench(Cube c, String bench) throws IOException
	{
		File file = new File(bench);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String txt;int i=-1;
		
		while ((txt = in.readLine()) != null)
		{
			if(i>6)break;
			if (i==-1 )
			{
			i++;	
			}
			else if (i<6){
				c.setCube(i,txt);
				i++;
			}
			
			else if(i==6){ c.setSeuil(Integer.parseInt(txt));i++;}

		}
	}

}
