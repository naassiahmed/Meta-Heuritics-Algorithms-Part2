package main;

import java.util.ArrayList;
import java.util.Random;


public class Solution implements Cloneable{
	private Cube c;
	private ArrayList<String> sol;
	private int fitness=-1;
	
	public Solution()
	{
		c = new Cube();
		sol = new ArrayList<String>();
	}
	
	public Solution (Cube c)
	{
		this.c=c;
		sol=new ArrayList<String>();
	}
	
	public Solution(Cube c, int size) throws CloneNotSupportedException
	{
		this.c=c.clone();
		sol=new ArrayList<String>();
		Random rnd = new Random();
		int val;
		for(int i=0;i<size;i++)
		{
			val=rnd.nextInt(12);
			
			switch(val)
			{
			case 0 : sol.add("BL");break;
			case 1 : sol.add("BR");break;
			case 2 : sol.add("FL");break;
			case 3 : sol.add("FR");break;
			case 4 : sol.add("HDL");break;
			case 5 : sol.add("HDR");break;
			case 6 : sol.add("HHL");break;
			case 7 : sol.add("HHR");break;
			case 8 : sol.add("VLD");break;
			case 9 : sol.add("VLU");break;
			case 10 : sol.add("VRD");break;
			case 11 : sol.add("VRU");break;
			}
		}
		Eval();
	}
	
	public Cube getC() {return c;}
	
	public ArrayList<String> getSol() {return sol;}
	
	public String getRotAt(int index) { return sol.get(index); }
	
	public void addInSol(String s) { sol.add(s); }
	
	public void setRot(int index, String s) throws CloneNotSupportedException 
	{
		sol.set(index, s);
		Eval();
	}
	
	public int getFitness() {return fitness;}
	
	public int getSize() { return sol.size(); }
	
	public void Eval() throws CloneNotSupportedException
	{
		Cube temp=c.clone();
		int i=0; boolean b=true;
		while (i<sol.size() && b==true)
		{
			switch(sol.get(i))
			{
			case "BL" :
				temp.Move_Back_Left();
				temp.EvalCube();
				if (temp.getValH()==0) {fitness=0; b=false;}
				else if (temp.getValH()<fitness || fitness==-1) fitness=temp.getValH();
				break;
			case "BR" :
				temp.Move_Back_Right();
				temp.EvalCube();
				if (temp.getValH()==0) {fitness=0; b=false;}
				else if (temp.getValH()<fitness || fitness==-1) fitness=temp.getValH();
				break;
			case "FL" :
				temp.Move_Face_Left();
				temp.EvalCube();
				if (temp.getValH()==0) {fitness=0; b=false;}
				else if (temp.getValH()<fitness || fitness==-1) fitness=temp.getValH();
				break;
			case "FR" :
				temp.Move_Face_Right();
				temp.EvalCube();
				if (temp.getValH()==0) {fitness=0; b=false;}
				else if (temp.getValH()<fitness || fitness==-1) fitness=temp.getValH();
				break;
			case "HDL" :
				temp.Move_Hori_Down_Left();
				temp.EvalCube();
				if (temp.getValH()==0) {fitness=0; b=false;}
				else if (temp.getValH()<fitness || fitness==-1) fitness=temp.getValH();
				break;
			case "HDR" :
				temp.Move_Hori_Down_Right();
				temp.EvalCube();
				if (temp.getValH()==0) {fitness=0; b=false;}
				else if (temp.getValH()<fitness || fitness==-1) fitness=temp.getValH();
				break;
			case "HHL" :
				temp.Move_Hori_High_Left();
				temp.EvalCube();
				if (temp.getValH()==0) {fitness=0; b=false;}
				else if (temp.getValH()<fitness || fitness==-1) fitness=temp.getValH();
				break;
			case "HHR" :
				temp.Move_Hori_High_Right();
				temp.EvalCube();
				if (temp.getValH()==0) {fitness=0; b=false;}
				else if (temp.getValH()<fitness || fitness==-1) fitness=temp.getValH();
				break;
			case "VLD" :
				temp.Move_Vert_Left_Down();
				temp.EvalCube();
				if (temp.getValH()==0) {fitness=0; b=false;}
				else if (temp.getValH()<fitness || fitness==-1) fitness=temp.getValH();
				break;
			case "VLU" :
				temp.Move_Vert_Left_Up();
				temp.EvalCube();
				if (temp.getValH()==0) {fitness=0; b=false;}
				else if (temp.getValH()<fitness || fitness==-1) fitness=temp.getValH();
				break;
			case "VRD" :
				temp.Move_Vert_Right_Down();
				temp.EvalCube();
				if (temp.getValH()==0) {fitness=0; b=false;}
				else if (temp.getValH()<fitness || fitness==-1) fitness=temp.getValH();
				break;
			case "VRU" :
				temp.Move_Vert_Right_Up();
				temp.EvalCube();
				if (temp.getValH()==0) {fitness=0; b=false;}
				else if (temp.getValH()<fitness || fitness==-1) fitness=temp.getValH();
				break;
			}
			i++;
		}
	}
	
	public void clean() throws CloneNotSupportedException
	{
		Cube temp=c.clone();
		int i=0; boolean b=true;
		while (i<sol.size() && b==true)
		{
			switch(sol.get(i))
			{
			case "BL" :
				temp.Move_Back_Left();
				temp.EvalCube();
				if (temp.getValH()==fitness) b=false;
				break;
			case "BR" :
				temp.Move_Back_Right();
				temp.EvalCube();
				if (temp.getValH()==fitness) b=false;
				break;
			case "FL" :
				temp.Move_Face_Left();
				temp.EvalCube();
				if (temp.getValH()==fitness) b=false;
				break;
			case "FR" :
				temp.Move_Face_Right();
				temp.EvalCube();
				if (temp.getValH()==fitness) b=false;
				break;
			case "HDL" :
				temp.Move_Hori_Down_Left();
				temp.EvalCube();
				if (temp.getValH()==fitness) b=false;
				break;
			case "HDR" :
				temp.Move_Hori_Down_Right();
				temp.EvalCube();
				if (temp.getValH()==fitness) b=false;
				break;
			case "HHL" :
				temp.Move_Hori_High_Left();
				temp.EvalCube();
				if (temp.getValH()==fitness) b=false;
				break;
			case "HHR" :
				temp.Move_Hori_High_Right();
				if (temp.getValH()==fitness) b=false;
				break;
			case "VLD" :
				temp.Move_Vert_Left_Down();
				temp.EvalCube();
				if (temp.getValH()==fitness) b=false;
				break;
			case "VLU" :
				temp.Move_Vert_Left_Up();
				temp.EvalCube();
				if (temp.getValH()==fitness) b=false;
				break;
			case "VRD" :
				temp.Move_Vert_Right_Down();
				temp.EvalCube();
				if (temp.getValH()==fitness) b=false;
				break;
			case "VRU" :
				temp.Move_Vert_Right_Up();
				temp.EvalCube();
				if (temp.getValH()==fitness) b=false;
				break;
			}
			i++;
		}
		i--;
		if (i<sol.size())
		{
			while (sol.size()>i+1) sol.remove(i+1);
		}
	}
	
	/*@Override
	public boolean equals(Object obj)
	{
		if (obj != null && obj instanceof Solution)
		{
			if (this.c.equals(((Solution)obj).getC()) && this.sol.equals(((Solution)obj).getSol()) && this.fitness==((Solution)obj).getFitness()) return true;
		}
		return false;
	}*/
	
	/*public boolean Same(Solution s)
	{
		if (this.c.equals(s.getC()) && this.sol.equals(s.getSol()) && this.fitness==s.getFitness()) return true;
		return false;
	}*/

	public Solution clone() throws CloneNotSupportedException
	{
		Solution s0=(Solution) super.clone();
		s0.c=this.c.clone();
		s0.sol=(ArrayList<String>) this.sol.clone();
		s0.fitness=this.fitness;
		return s0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + fitness;
		result = prime * result + ((sol == null) ? 0 : sol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solution other = (Solution) obj;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		if (fitness != other.fitness)
			return false;
		if (sol == null) {
			if (other.sol != null)
				return false;
		} else if (!sol.equals(other.sol))
			return false;
		return true;
	}

	public void Affiche()
	{
		System.out.println(sol);
	}
}
