package ga;

import java.util.ArrayList;

import main.Cube;
import main.Solution;

public class Population implements Cloneable{

	private int popsize;
	private int size;
	private ArrayList<Solution> pop;
	
	public Population (Cube c, int popsize) throws CloneNotSupportedException
	{
		this.popsize=popsize;
		pop=new ArrayList<Solution>();
		int i=0;
		while (i<this.popsize)
		{
			Solution s0=new Solution(c,c.getSeuil());
			if (!pop.contains(s0))
			{
				addInPop(s0);
				i++;
			}
		}
		updateSize();
	}
	
	public int getPopSize()
	{
		return popsize;
	}
	
	public void setPopSize(int popsize)
	{
		this.popsize=popsize;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void updateSize()
	{
		size=pop.size();
	}
	
	public void setPop(ArrayList<Solution> p)
	{
		pop=(ArrayList<Solution>) p.clone();
		updateSize();
	}
	
	public Solution getSolAt(int index)
	{
		return pop.get(index);
	}
	
	public void setSolAt(int index, Solution s)
	{
		pop.set(index, s);
	}
	
	public void setSol(int index,Solution s)
	{
		pop.set(index, s);
	}
	
	public void addInPop (Solution s)
	{
		if(pop.size()==0) pop.add(s);
		else
		{
			int i=0;
			while (i<size && s.getFitness() >= pop.get(i).getFitness()) i++;
			if(i<size) pop.add(i, s);
			else pop.add(s);
		}		
		updateSize();
	}
	
	public void remove(int index)
	{
		pop.remove(index);
		updateSize();
	}
	
	public void cleanDup()
	{
		for (int i=0;i<size-1;i++)
		{
			for (int j=i+1;j<size;j++) if (pop.get(i).equals(pop.get(j))) remove(i);
		}
	}
	
	public Population clone() throws CloneNotSupportedException
	{
		Population p0=(Population) super.clone();
		p0.setPopSize(this.getPopSize());
		p0.setPop(pop);
		return p0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pop == null) ? 0 : pop.hashCode());
		result = prime * result + popsize;
		result = prime * result + size;
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
		Population other = (Population) obj;
		if (pop == null) {
			if (other.pop != null)
				return false;
		} else if (!pop.equals(other.pop))
			return false;
		if (popsize != other.popsize)
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	public void Affiche()
	{
		for(int i=0;i<size;i++)
		{
			pop.get(i).Affiche(); System.out.println(pop.get(i).getFitness());
		}
		System.out.println();
	}

}
