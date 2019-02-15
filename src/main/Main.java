package main;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import ga.AlgGen;
import tabu.Taboue;


public class Main {

public static void Create_bench(int nbr, String nom) throws IOException{
	
		Cube c = new Cube();
		Random rnd = new Random();
		int val;
		for(int i=0;i<nbr;i++)
		{
			val=rnd.nextInt(12);
			
			switch(val)
			{
			case 0 : c.Move_Back_Left();break;
			case 1 : c.Move_Back_Right();break;
			case 2 : c.Move_Face_Left();break;
			case 3 : c.Move_Face_Right();break;
			case 4 : c.Move_Hori_Down_Left();break;
			case 5 : c.Move_Hori_Down_Right();break;
			case 6 : c.Move_Hori_High_Left();break;
			case 7 : c.Move_Hori_High_Right();break;
			case 8 : c.Move_Vert_Left_Down();break;
			case 9 : c.Move_Vert_Left_Up();break;
			case 10 : c.Move_Vert_Right_Down();break;
			case 11 : c.Move_Vert_Right_Up();break;
			}
		}
		
		File Fichier = new File(nom);
	    BufferedWriter writer = new BufferedWriter(new FileWriter(Fichier));
	    writer.write("Fichier Benchmark :"+"\n"); 	
		writer.write(c.getCube()[0]+"\n");
		writer.write(c.getCube()[1]+"\n");
		writer.write(c.getCube()[2]+"\n");
		writer.write(c.getCube()[3]+"\n");
		writer.write(c.getCube()[4]+"\n");
		writer.write(c.getCube()[5]+"\n");
		writer.write(String.valueOf(nbr)+"\n");
		writer.write(c.getDep().toString());
		writer.close();
	}

	public static void Create_10_bench(int nbr) throws IOException
	{
		for (int i=1;i<=10;i++) Create_bench(nbr,"F:/bench_cube"+i+".txt");		
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
				c.getCube()[i]=txt;
				i++;
			}
			
			else if(i==6){ c.setSeuil(Integer.parseInt(txt));i++;}

		}
	}
	
	public static void write(int N, String s) throws IOException
	{
		File Fichier = new File("F:/Result"+N+".txt");
	    BufferedWriter writer = new BufferedWriter(new FileWriter(Fichier));
	    writer.write(s);
	    writer.close();	    
	}
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		
		Create_bench(10,"F:/bench_cube_ready.txt");
		Cube c = new Cube();
		Read_bench(c,"F:/bench_cube_ready.txt");
		/*Taboue a = new Taboue(c);
		double start=System.currentTimeMillis();
		Solution s = a.Tabu_Search(c.getSeuil());
		double end=System.currentTimeMillis();*/
		/*AlgGen ga = new AlgGen(c,10*((int) Math.pow(c.getSeuil(), 2)), 15*((int) Math.pow(c.getSeuil(), 1)), 0.8,0.1,0.7);
		double start=System.currentTimeMillis();
		Solution s = ga.Algorithm();
		double end=System.currentTimeMillis();
		s.clean();
		s.Affiche();
		System.out.println(s.getFitness());
		System.out.println("Temps : "+((end-start)/1000)+" millisecondes");*/
		Interface I = new Interface();
		
		/*for (int N=20;N<=100;N=N+10)
		{
			Create_10_bench(N);
			for (int k1=5;k1<=N/2;k1=k1+5)
			{
				for (int k2=5;k2<=N/2;k2=k2+5)
				{
					for (double tc=0.6;tc<=0.8;tc=tc+0.05)
					{
						for(double tm=0.05;tm<=0.15;tm=tm+0.05)
						{
							{
								s= "Pour k1="+k1+", k2="+k2+", tc="+tc+", tm="+tm+"\n";
								System.out.println("Pour k1="+k1+", k2="+k2+", tc="+tc+", tm="+tm);
								for (int i=1;i<=10;i++)
								{
									Cube c = new Cube();
									Read_bench(c,"C:/Users/profil/Desktop/Benchmarks cube/bench_cube"+i+".txt");
									AlgGen ga = new AlgGen(c,k1*((int)Math.pow(c.getSeuil(),2)),k2*c.getSeuil(),tc,tm,0.6);
									Solution sol=ga.Algorithm();
									s=s+sol.getFitness()+"\n";
									System.out.println(sol.getFitness());
								}
							}
						}
					}
				}
			}
			//write(N,s);
		}*/
	}

}

