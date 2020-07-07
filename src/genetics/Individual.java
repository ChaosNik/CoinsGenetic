/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import static java.lang.Math.abs;
import java.util.Random;

/**
 *
 * @author nikol
 */
public class Individual
{
    boolean[] genes=new boolean[30]; //genes
    public static final double FAC_MUT = 2.0; //factor of mutation
    
    public Individual()
    {
        Random rand=new Random();
        for(int i=0;i<30;++i)
            genes[i]=rand.nextBoolean();
        fixToTenGenes();
    }
    public Individual(boolean[] genes)
    {
        Random rand=new Random();
        for(int i=0;i<30;++i)
            this.genes[i]=genes[i];
        fixToTenGenes();
    }
    public int countBits()
    {
        int sum=0;
        for(boolean gene : genes)
            if(gene)++sum;
        return sum;
    }
    public int summarise()
    {
        int sum=0;
        for(int i=0;i<30;++i)
            if(genes[i])sum+=(i/3+1);
        return sum;
    }
    public void fixToTenGenes()
    {
        /**Old solution**/
        /*int countTrue=countBits();
        if(countTrue==10)return;
        if(countTrue<10)
        {
            for(int i=countTrue;i<10;++i)
            {
                for(int j=0;j<30;++j)
                {
                    if(!genes[j])
                    {
                        genes[j]=true;
                        j=30;
                    }
                }
            }
            return;
        }
        for(int i=10;i<countTrue;++i)
        {
            for(int j=0;j<30;++j)
            {
                if(genes[j])
                {
                    genes[j]=false;
                    j=30;
                }
            }
        }*/
        /**Best solution**/
        int noGenes=countBits();
        Random rand = new Random();
        if(noGenes==10)return;
        else if(noGenes<10)
        {
            for(int i=noGenes;i<10;++i)
            {
                boolean fail=true;
                while(fail)
                {
                    //int index=rand()%30;
                    int index=rand.nextInt(30);
                    if(!genes[index])
                    {
                        genes[index]=true;
                        fail=false;
                    }
                }
            }
            return;
        }
        else if(noGenes>10)
        {
            for(int i=10;i<noGenes;++i)
            {
                boolean fail=true;
                while(fail)
                {
                    //int index=rand()%30;
                    int index=rand.nextInt(30);
                    if(genes[index])
                    {
                        genes[index]=false;
                        fail=false;
                    }
                }
            }
        }
    }
    public Individual cross(Individual mate)
    {
        Random rand=new Random();
        int cutIndex=abs(rand.nextInt()%30);
        boolean[] newGenes=new boolean[30];
        
        for(int i=0;i<30;++i)
        {
            if(i<cutIndex)newGenes[i]=genes[i];
            newGenes[i]=mate.genes[i];
        }
        return new Individual(newGenes);
    }
    public void mutate()
    {
        Random rand=new Random();
        int cutIndex=abs(rand.nextInt()%30);
        genes[cutIndex]=!genes[cutIndex];
        fixToTenGenes();
    }
    @Override
    public String toString()
    {
        //for(Individual ind : closestIndividual)temp+=ind.getId()+" ";
        String solution="";
        for(int i=0;i<30;++i)
        {
            if(genes[i])solution+=(i/3+1)+" ";
            else solution+="0 ";
        }
        return solution;
    }
}
