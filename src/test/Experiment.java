/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.Solution;
import static java.lang.Math.sqrt;

/**
 *
 * @author nikol
 */
public class Experiment
{
    public static void main(String[] args)
    {
        System.out.println("Genetical:");
        //for(int i=1;i<=165;++i)
        for(int i=20;i<=90;++i)
        {
            //if(i<24 || i>86 || i==40 || i==50)
            if((i-20)%7==0)
            {
                double avgTime=0;
                double avgIter=0;
                int[] times=new int[10];
                int[] iterations=new int[10];
                for(int j=0;j<10;++j)
                {
                    String temp=Solution.solve(i);
                    iterations[j]=Integer.parseInt(temp.split("===")[2]);
                    times[j]=Integer.parseInt(temp.split("===")[3]);
                    System.out.println(""+i+":"+temp);
                    
                    avgTime+=times[j];
                    avgIter+=iterations[j];
                }
                avgTime/=10.0;
                avgIter/=10.0;
                
                double sd=0;
                for(int j=0;j<10;++j)
                    sd+=(avgTime-times[j])*(avgTime-times[j]);
                sd/=10;
                sd=sqrt(sd);
                System.out.println("iter:"+avgIter);
                System.out.println("srvr:"+avgTime);
                System.out.println("dev:"+sd);
                System.out.println("");
            }
        }
        System.out.println("");
        
    }
    
}
