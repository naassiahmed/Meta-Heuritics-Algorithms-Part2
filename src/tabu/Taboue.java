package tabu;

import java.util.ArrayList;

import main.Cube;
import main.Solution;

public class Taboue {
	
	Cube init;
	ArrayList<Solution> Tabu;
	
	public Taboue(Cube init) throws CloneNotSupportedException
	{
		this.init=init.clone();
		Tabu=new ArrayList<Solution>();
	}
	
	public Solution choose (Solution temp, Solution best) throws CloneNotSupportedException
	{
		if (best.getFitness()==-1) best = temp.clone();
		else { if(temp.getFitness() < best.getFitness()) best=temp.clone(); }
		return best;
	}
	
	public Solution checkVoisin(Solution current, String rot, int index, Solution temp, Solution best, boolean allTabu) throws CloneNotSupportedException
	{
		temp.setRot(index, rot);
		if (!Tabu.contains(temp))
		{
			if (temp.getFitness()<current.getFitness()) return temp;
			else
			{
				best=choose(temp,best); return best;
			}
		}
		return best;
	}
	
	public Solution Explore(Solution s) throws CloneNotSupportedException
	{
		Solution temp = new Solution();
		Solution best = new Solution();
		Solution bestInTabu = new Solution();
		for (int i=0;i<s.getSol().size();i++)
		{
			for (int j=0;j<12;j++)
			{
				temp=s.clone();
				switch(j)
				{
				case 0 :
					if (!temp.getRotAt(i).equals("BL"))
					{
						temp.setRot(i, "BL");
						if (!Tabu.contains(temp))
						{
							if (temp.getFitness()<s.getFitness()) return temp;
							else
							{
								best=choose(temp,best);
							}
						}
						else bestInTabu=choose(temp,best);
					}
					break;
				case 1 :
					if (!temp.getRotAt(i).equals("BR"))
					{
						temp.setRot(i, "BR");
						if (!Tabu.contains(temp))
						{
							if (temp.getFitness()<s.getFitness()) return temp;
							else
							{
								best=choose(temp,best);
							}
						}
						else bestInTabu=choose(temp,best);
					}
					break;
				case 2 :
					if (!temp.getRotAt(i).equals("FL"))
					{
						temp.setRot(i, "FL");
						if (!Tabu.contains(temp))
						{
							if (temp.getFitness()<s.getFitness()) return temp;
							else
							{
								best=choose(temp,best);
							}
						}
						else bestInTabu=choose(temp,best);
					}
					break;
				case 3 :
					if (!temp.getRotAt(i).equals("FR"))
					{
						temp.setRot(i, "FR");
						if (!Tabu.contains(temp))
						{
							if (temp.getFitness()<s.getFitness()) return temp;
							else
							{
								best=choose(temp,best);
							}
						}
						else bestInTabu=choose(temp,best);
					}
					break;
				case 4 :
					if (!temp.getRotAt(i).equals("HDL"))
					{
						temp.setRot(i, "HDL");
						if (!Tabu.contains(temp))
						{
							if (temp.getFitness()<s.getFitness()) return temp;
							else
							{
								best=choose(temp,best);
							}
						}
						else bestInTabu=choose(temp,best);
					}
					break;
				case 5 :
					if (!temp.getRotAt(i).equals("HDR"))
					{
						temp.setRot(i, "HDR");
						if (!Tabu.contains(temp))
						{
							if (temp.getFitness()<s.getFitness()) return temp;
							else
							{
								best=choose(temp,best);
							}
						}
						else bestInTabu=choose(temp,best);
					}
					break;
				case 6 :
					if (!temp.getRotAt(i).equals("HHL"))
					{
						temp.setRot(i, "HHL");
						if (!Tabu.contains(temp))
						{
							if (temp.getFitness()<s.getFitness()) return temp;
							else
							{
								best=choose(temp,best);
							}
						}
						else bestInTabu=choose(temp,best);
					}
					break;
				case 7 :
					if (!temp.getRotAt(i).equals("HHR"))
					{
						temp.setRot(i, "HHR");
						if (!Tabu.contains(temp))
						{
							if (temp.getFitness()<s.getFitness()) return temp;
							else
							{
								best=choose(temp,best);
							}
						}
						else bestInTabu=choose(temp,best);
					}
					break;
				case 8 :
					if (!temp.getRotAt(i).equals("VLD"))
					{
						temp.setRot(i, "VLD");
						if (!Tabu.contains(temp))
						{
							if (temp.getFitness()<s.getFitness()) return temp;
							else
							{
								best=choose(temp,best);
							}
						}
						else bestInTabu=choose(temp,best);
					}
					break;
				case 9 :
					if (!temp.getRotAt(i).equals("VLU"))
					{
						temp.setRot(i, "VLU");
						if (!Tabu.contains(temp))
						{
							if (temp.getFitness()<s.getFitness()) return temp;
							else
							{
								best=choose(temp,best);
							}
						}
						else bestInTabu=choose(temp,best);
					}
					break;
				case 10 :
					if (!temp.getRotAt(i).equals("VRD"))
					{
						temp.setRot(i, "VRD");
						if (!Tabu.contains(temp))
						{
							if (temp.getFitness()<s.getFitness()) return temp;
							else
							{
								best=choose(temp,best);
							}
						}
						else bestInTabu=choose(temp,best);
					}
					break;
				case 11 :
					if (!temp.getRotAt(i).equals("VRU"))
					{
						temp.setRot(i, "VRU");
						if (!Tabu.contains(temp))
						{
							if (temp.getFitness()<s.getFitness()) return temp;
							else
							{
								best=choose(temp,best);
							}
						}
						else bestInTabu=choose(temp,best);
					}
					break;
				}				
			}
		}
		if (best.getFitness()==-1) return bestInTabu;
		return best;
	}
	
	public Solution Tabu_Search(int size) throws CloneNotSupportedException
	{
		Solution Best=new Solution(init, size);
		Solution BestV=Best.clone();
		System.out.println(">>> "+Best.getFitness());
		int reg=3;
		//int maxIter=(int) (1*Math.pow(size, 5));
		int maxIter=(int) (10*size*Math.pow(size, 1));
		for(int i=0;i<maxIter;i++)
		{
			if (!Tabu.contains(BestV)) Tabu.add(BestV.clone());
			BestV=Explore(BestV).clone();
			System.out.println(BestV.getFitness())	;
			if (Tabu.contains(BestV))
			{
				if (BestV.getFitness()>Best.getFitness())
				{
					reg--;
					if (reg==0)
					{
						BestV=new Solution(init, size);
						if (BestV.getFitness()<Best.getFitness()) Best = BestV.clone();
						reg=3; 
					}
				}
			}
			else if (BestV.getFitness()<Best.getFitness()) Best = BestV.clone();
		}
		
		Best.clean();
		return Best;
	}

}
