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
public class Test
{
    public static final int REPS = 5;
    
    public static void main(String[] args)
    {
        System.out.println("Genetical:");
        //for(int i=1;i<=165;++i)
        for(int i=20;i<=90;++i)
            for(int j=0;j<REPS;++j)
                System.out.println("Number:"+i+"-Final:"+Solution.solve(i));
        
        System.out.println("");
        System.out.println("");
        
        System.out.println("Greedy:");
        for(int i=20;i<=90;++i)
            for(int j=0;j<REPS;++j)
                System.out.println("   "+i+" "+Solution.solveGreedy(i));
    }
}
