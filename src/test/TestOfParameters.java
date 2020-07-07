/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.Solution;

/**
 *
 * @author nikol
 */
public class TestOfParameters
{
    public static final int REPS = 5;
    
    public static void main(String[] args)
    {
        System.out.println("Numbers:");
        for(int i=20;i<=90;++i)
            System.out.print(i+" ");
        System.out.println();
        /**Uncomment for selection**/
        //basic();
        //populationCorrectness();
        //populationTimesPerGene();
        //populationTimes();
        
        //iterCorrectness();
        //iterTimesPerGene();
        //iterTimes();
        
        //sameCorrectness();
        //sameTimesPerGene();
        //sameTimes();
        
        //crossCorrectness();
        //crossTimesPerGene();
        //crossTimes();
        
        //mutCorrectness();
        //mutTimesPerGene();
        //mutTimes();
    }
    private static void basic()
    {
        System.out.println("Basic test:");
        //for(int i=1;i<=165;++i)
        for(int i=20;i<=90;++i)
            for(int j=0;j<REPS;++j)
                System.out.print(Solution.solve(i).split("=")[0]+" ");
        System.out.println();
    }
    private static void correctness()
    {
        int sum=0;
        for(int i=20;i<=90;++i)
        {
            for(int j=0;j<1;++j)
            {
                //System.out.print(Solution.solve(i).split("=")[0]+" ");
                if(Integer.parseInt(Solution.solve(i).split("=")[0])==i)++sum;
            }
        }
        System.out.println(71-sum-4);
    }
    private static void timesPerGene()
    {
        for(int i=20;i<=90;++i)
        {
            int sum=0;
            for(int j=0;j<REPS;++j)
                sum+=Integer.parseInt(Solution.solve(i).split("===")[3]);
            System.out.print(""+sum/REPS+" ");
        }
        System.out.println();
    }
    private static void times()
    {
        double average=0;
        for(int i=20;i<=90;++i)
        {
            int sum=0;
            for(int j=0;j<REPS;++j)
                sum+=Integer.parseInt(Solution.solve(i).split("===")[3]);
            average+=sum/(double)REPS;
        }
        average/=(71);
        System.out.println(average);
    }
    
    
    private static void populationCorrectness()
    {
        int temp=Solution.POP_SIZE;
        for(int k=1;k<50;k++)
        {
            Solution.POP_SIZE=k;
            System.out.println("Population size = "+k);
            correctness();
        }
        Solution.POP_SIZE=temp;
    }
    private static void populationTimesPerGene()
    {
        int temp=Solution.POP_SIZE;
        for(int k=1;k<100;k++)
        {
            Solution.POP_SIZE=k;
            System.out.println("Population size = "+k);
            timesPerGene();
        }
        Solution.POP_SIZE=temp;
    }
    private static void populationTimes()
    {
        int temp=Solution.POP_SIZE;
        for(int k=1;k<100;k++)
        {
            Solution.POP_SIZE=k;
            System.out.println("Population size = "+k);
            times();
        }
        Solution.POP_SIZE=temp;
    }
    
    private static void iterCorrectness()
    {
        int temp=Solution.MAX_ITER;
        for(int k=0;k<100;k+=10)
        {
            Solution.MAX_ITER=k;
            System.out.println("Max iterations = "+k);
            correctness();
        }
        Solution.MAX_ITER=temp;
    }
    private static void iterTimesPerGene()
    {
        int temp=Solution.MAX_ITER;
        for(int k=1;k<100;k++)
        {
            Solution.MAX_ITER=k;
            System.out.println("Max iterations = "+k);
            timesPerGene();
        }
        Solution.MAX_ITER=temp;
    }
    private static void iterTimes()
    {
        int temp=Solution.MAX_ITER;
        for(int k=1;k<100;k++)
        {
            Solution.MAX_ITER=k;
            System.out.println("Max iterations = "+k);
            times();
        }
        Solution.MAX_ITER=temp;
    }
    
    private static void sameCorrectness()
    {
        int temp=Solution.MAX_SAME;
        for(int k=0;k<100;++k)
        {
            Solution.MAX_SAME=k;
            System.out.println("Max same = "+k);
            correctness();
        }
        Solution.MAX_SAME=temp;
    }
    private static void sameTimesPerGene()
    {
        int temp=Solution.MAX_SAME;
        for(int k=1;k<100;k++)
        {
            Solution.MAX_SAME=k;
            System.out.println("Max same = "+k);
            timesPerGene();
        }
        Solution.MAX_SAME=temp;
    }
    private static void sameTimes()
    {
        int temp=Solution.MAX_SAME;
        for(int k=0;k<100;k++)
        {
            Solution.MAX_SAME=k;
            System.out.println("Max same = "+k);
            times();
        }
        Solution.MAX_SAME=temp;
    }
    
    private static void crossCorrectness()
    {
        double temp=Solution.PROB_CO;
        for(double k=0;k<=1;k+=0.01)
        {
            Solution.PROB_CO=k;
            System.out.println("Prob = "+k);
            correctness();
        }
        Solution.PROB_CO=temp;
    }
    private static void crossTimesPerGene()
    {
        double temp=Solution.PROB_CO;
        for(double k=0;k<=1;k+=0.01)
        {
            Solution.PROB_CO=k;
            System.out.println("Prob = "+k);
            timesPerGene();
        }
        Solution.PROB_CO=temp;
    }
    private static void crossTimes()
    {
        double temp=Solution.PROB_CO;
        for(double k=0;k<=1;k+=0.01)
        {
            Solution.PROB_CO=k;
            System.out.println("Prob = "+k);
            times();
        }
        Solution.PROB_CO=temp;
    }
    
    private static void mutCorrectness()
    {
        double temp=Solution.PROB_MUT;
        for(double k=0;k<=1;k+=0.01)
        {
            Solution.PROB_MUT=k;
            System.out.println("Prob = "+k);
            correctness();
        }
        Solution.PROB_MUT=temp;
    }
    private static void mutTimesPerGene()
    {
        double temp=Solution.PROB_MUT;
        for(double k=0;k<=1;k+=0.01)
        {
            Solution.PROB_MUT=k;
            System.out.println("Prob = "+k);
            timesPerGene();
        }
        Solution.PROB_MUT=temp;
    }
    private static void mutTimes()
    {
        double temp=Solution.PROB_MUT;
        for(double k=0;k<=1;k+=0.01)
        {
            Solution.PROB_MUT=k;
            System.out.println("Prob = "+k);
            times();
        }
        Solution.PROB_MUT=temp;
    }
    
}
