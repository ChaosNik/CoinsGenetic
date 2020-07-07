/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import static java.lang.Math.abs;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author nikol
 */
public class Solution
{
    public static int POP_SIZE = 30; //number of individuals
    public static int MAX_ITER = 300; //max number of iterations
    public static int MAX_SAME = 60; //max number of iterations with repeated results
    public static double PROB_CO = 0.9; //probability of crossing
    public static double PROB_MUT = 0.2; //probability of mutation
    
    public static int iter = 0;
    public static int same = 0;
    public static int lastResult = 0;
    public static int value = 0; //value for solution
    
    public static int closestSum;
    public static Individual closestIndividual;
    
    public static String solve(int value)
    {
        /**Test**/
        /*boolean[] niz=new boolean[30];
        for(int i=0;i<30;++i)niz[i]=(i>3)&&(i<5);
        Individual indiv = new Individual(niz);
        System.out.println(""+indiv+"==="+indiv.countBits());*/
        /********/
        iter = 0;
        same = 0;
        lastResult = 0;
        closestSum=0;
        closestIndividual=null;
        long time=System.currentTimeMillis();
        
        Solution.value = value;
        Individual[] population = new Individual[POP_SIZE];
        for(int i=0;i<POP_SIZE;++i)
            population[i]=new Individual();
        
        closestIndividual=population[0];
        closestSum=closestIndividual.summarise();
        for (int i = 0; (iter < MAX_ITER) && (same<MAX_SAME) && (closestSum!=value); ++i) //iteration
        {
            /**Test**/
            //System.out.println("" + iter + "===" + closestSum + "===" + closestIndividual);
            /********/
            iter++;
            
            /**Crossing**/
            Random randCrossing = new Random();
            if (randCrossing.nextDouble() < PROB_CO)
            {
                /**Ranking**/
                Arrays.sort
                (
                        population,
                        new Comparator<Individual>()
                        {
                            @Override
                            public int compare(Individual male, Individual female)
                            {
                                int devMale = abs(male.summarise()-value);
                                int devFemale = abs(female.summarise()-value);
                                if(devMale>devFemale)return 1;
                                if(devMale<devFemale)return -1;
                                return 0;
                            }
                        }
                );
                /**Calculating maximum deviation**/
                int maxDev=0;
                for(Individual ind : population)
                {
                    int dev=abs(ind.summarise()-value);
                    if(dev>maxDev)maxDev=dev;
                }
                /**Calculating sum of inverse deviations**/
                int sumDev=0;
                for(Individual ind : population)
                    sumDev+=maxDev-abs(ind.summarise()-value);
                /**Roulette**/
                double[] probs=new double[POP_SIZE];
                for(int j=0;j<POP_SIZE;++j)
                    probs[j]=sumDev/(double)(maxDev-abs(population[j].summarise()-value));
                /**Mating**/
                Individual[] newPopulation=new Individual[POP_SIZE];
                for (int j = 0; j < POP_SIZE; ++j)
                {
                    Random rand = new Random();
                    /**Selecting male mate**/
                    double prob=rand.nextDouble();
                    int index=0;
                    while(prob>probs[index])
                    {
                        prob-=probs[index];
                        ++index;
                    }
                    Individual male = population[index];
                    /**Selecting female mate**/
                    prob=rand.nextDouble();
                    index=0;
                    while(prob>probs[index])
                    {
                        prob-=probs[index];
                        ++index;
                    }
                    Individual female = population[index];
                    newPopulation[j] = male.cross(female);
                }
                population=newPopulation;
            }
            /**Mutating**/
            Random randMutation = new Random();
            if(randMutation.nextDouble() < PROB_MUT)
                for (Individual ind : population)
                    ind.mutate();
            /**Finding best fitted individual**/
            int bestSum=1000;
            Individual bestIndividual=population[0];
            for(Individual ind : population)
            {
                int indSum=ind.summarise();
                if(abs(indSum-value)<abs(bestSum-value))
                {
                    bestSum=indSum;
                    bestIndividual=ind;
                }
            }
            /**Test**/
            //System.out.println("" + iter + "===" + bestSum + "===" + bestIndividual + "===" + bestIndividual.countBits());
            /********/
            /**Same solutions repeats, convergence**/
            if (bestSum == closestSum)
            {
                same++;
            }
            /**Reset if not converging**/
            else
            {
                same = 0;
            }
            /**If we get to the final value**/
            if(bestSum==value)
            {
                closestSum=bestSum;
                closestIndividual=bestIndividual;
                i=MAX_ITER;
            }
            /**If we find one better solution**/
            else if(abs(bestSum-value)<=abs(closestSum-value))
            {
                closestSum=bestSum;
                closestIndividual=bestIndividual;
            }
        }
        time=System.currentTimeMillis()-time;
        //System.out.println("" + closestSum + "===" + closestIndividual + "===" + iter + "===" + time);
        return "" + closestSum + "===" + closestIndividual + "===" + iter + "===" + time;
    }
    
    public static String solveGreedy(int value)
    {
        iter = 0;
        long time=System.currentTimeMillis();
        if(value<22)value=22;
        else if(value>88)value=88;
        
        String solution=solveGreedyTree(value,10,10);
        solution=solution.split("=")[1];
        
        time=System.currentTimeMillis()-time;
        
        return "" + value + "===" + solution + "===" + iter + "===" + time;
    }
    private static String solveGreedyTree(int value, int coin, int coinsLeft)
    {
        ++iter;
        /**Conditions for breaking recursion**/
        if(coinsLeft==0 && coin==0)
        {
            if(value==0)return "S=";
            else return "F=";
        }
        if(coin==0)return "F=";
        String tmp;
        /**Checking how many coins can we fit and is that a solution**/
        if(value>=(3*coin))
        {
            
            tmp=solveGreedyTree(value-(3*coin), coin-1, coinsLeft-3)+coin+" "+coin+" "+coin+" ";
            if(tmp.charAt(0)=='S')return tmp;
        }
        if(value>=(2*coin))
        {
            tmp=solveGreedyTree(value-(2*coin), coin-1, coinsLeft-2)+coin+" "+coin+" "+"0 ";
            if(tmp.charAt(0)=='S')return tmp;
        }
        if(value>=(1*coin))
        {
            tmp=solveGreedyTree(value-(1*coin), coin-1, coinsLeft-1)+coin+" "+"0 0 ";
            if(tmp.charAt(0)=='S')return tmp;
        }
        tmp=solveGreedyTree(value-(0*coin), coin-1, coinsLeft)+"0 0 0 ";
        if(tmp.charAt(0)=='S')return tmp;
        return "F=";
    }
    /**Changing coding from binary to number of repetitions of coin types**/
    public static String putCoinsToBuckets(String coins)
    {
        String[] array=coins.split(" ");
        int[] buckets=new int[11];
        for(int i=0;i<30;++i)
            ++buckets[Integer.parseInt(array[i])];
        String finalSolution="";
        for(int i=1;i<=10;++i)
            finalSolution+=buckets[i]+" ";
        return finalSolution;
    }
}
