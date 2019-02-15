package ga;

import java.util.ArrayList;
import java.util.Random;

import main.Cube;
import main.Solution;

public class AlgGen {
	
	private Cube init;
	private Population pop;
	private int maxIter;
	private double tc;
	private double tm;
	private double tr;
	
	public AlgGen(Cube c, int popsize, int maxIter, double tc, double tm, double tr) throws CloneNotSupportedException
	{
		init=c.clone();
		pop = new Population(c,popsize);
		this.maxIter = maxIter;
		this.tc=tc;
		this.tm=tm;
		this.tr=tr;
	}
	
	public ArrayList<Solution> Croisement(Solution s1, Solution s2) throws CloneNotSupportedException
	{
		ArrayList<Solution> enfants = new ArrayList<Solution>();
		Random r = new Random();
		Solution f1 = new Solution(init);
		Solution f2 = new Solution(init);
		int pos=1+r.nextInt(s1.getSize());
		for (int i=0;i<pos;i++){ f1.addInSol(s1.getRotAt(i)); f2.addInSol(s2.getRotAt(i)); }
		for (int i=pos;i<s1.getSol().size();i++) { f1.addInSol(s2.getRotAt(i)); f2.addInSol(s1.getRotAt(i)); }
		f1.Eval(); enfants.add(f1); 
		f2.Eval(); enfants.add(f2);
		return enfants;
	}
	
	public void Mutation (Solution s) throws CloneNotSupportedException
	{
		Random r = new Random();
		int index=r.nextInt(s.getSize());
		int val=r.nextInt(12);
		
		switch(val)
		{
		case 0 : s.setRot(index,"BL");break;
		case 1 : s.setRot(index,"BR");break;
		case 2 : s.setRot(index,"FL");break;
		case 3 : s.setRot(index,"FR");break;
		case 4 : s.setRot(index,"HDL");break;
		case 5 : s.setRot(index,"HDR");break;
		case 6 : s.setRot(index,"HHL");break;
		case 7 : s.setRot(index,"HHR");break;
		case 8 : s.setRot(index,"VLD");break;
		case 9 : s.setRot(index,"VLU");break;
		case 10 : s.setRot(index,"VRD");break;
		case 11 : s.setRot(index,"VRU");break;
		}
	}
	
	public void Remplacement (Population p0) throws CloneNotSupportedException
	{
		p0.cleanDup();
		while (p0.getSize()>pop.getPopSize()) p0.remove(pop.getPopSize());
		if (!p0.equals(pop)) pop=p0.clone();
		else
		{
			int p = (int) ((1-tr)*pop.getPopSize());
			for (int i=p;i<p0.getPopSize();i++)
			{
				p0.setSolAt(p, new Solution(init,init.getSeuil()));
			}
		}
	}
	
	public Solution Algorithm() throws CloneNotSupportedException
	{
		Population pop0 = pop.clone();
		int nbc=(int) (tc*pop.getPopSize()+0.5);
		int nbm=(int) (tm*pop.getPopSize());
		Random r = new Random();
		
		for (int i=0; i<maxIter;i++)
		{
			for (int j=0;j<nbc;j++)
			{
				Solution s1 = pop.getSolAt(r.nextInt(pop.getPopSize())).clone();
				Solution s2 = pop.getSolAt(r.nextInt(pop.getPopSize())).clone();
				ArrayList<Solution> enfants=Croisement(s1, s2);
				pop0.addInPop(enfants.get(0));
				pop0.addInPop(enfants.get(1));
			}	
			
			for (int j=0;j<nbm;j++)
			{
				Solution s = pop0.getSolAt(r.nextInt(pop0.getPopSize())).clone();
				Mutation(s);
				//System.out.println(s.getFitness());
			}	
			
			Remplacement(pop0);
			System.out.println(pop.getSolAt(0).getFitness());
			if (pop.getSolAt(0).getFitness()==0) return pop.getSolAt(0);

		}
		
		return pop.getSolAt(0);
	}

}
