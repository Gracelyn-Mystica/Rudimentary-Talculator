package com.internshala.javaalways;

import java.util.Scanner;

public class IncomeTaxCalculation {
    //Get the required inputs in order to perform the Income Tax Calculation
    static void getDetails() {
        int num;
        int slabCount = 0;
        long rebate = 0;
        final long[] slabAmt = new long[20];
        final int[] percent = new int[20];
        int i = 0, j;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of Individuals " +
                "needs to be Calculated : ");
        num = scan.nextInt();
        long[] actual_salary = new long[num];
        String[] names = new String[num];
        long[] salary = new long[num];
        long[] result = new long[num];
        //Get the details of the Individuals(Name, Salary)
        for (i = 0; i < num; i++) {
            System.out.print("Enter the Name : ");
            names[i] = scan.next();
            System.out.print("Enter " + names[i] + "'s salary : ");
            salary[i] = scan.nextLong();
        }
        //Get slab details(Rebate Amount, Slab Count, Slab Amount, Slab Percentage)
        System.out.print("Enter the Rebate Amount : ");
        rebate = scan.nextLong();
        System.out.print("enter the Slab Count : ");
        slabCount = scan.nextInt();
        for (i = 0; i < slabCount; i++) {
            if (i != slabCount - 1) {
                System.out.print("Enter the slab : ");
                slabAmt[i] = scan.nextLong();
                System.out.print("Enter the respective slab percentage : ");
                percent[i] = scan.nextInt();
            } else if (i == slabCount - 1) {
                //System.out.print("This is the final slab, " +
                        //"So enter the starting value of the slab : ");
                //slabAmt[i] = scan.nextLong();
                slabAmt[i]=slabAmt[i-1]+1;
                System.out.print("Enter the percentage for the final slab : ");
                percent[i] = scan.nextInt();
            }
        }
        for (j = 0; j < num; j++) {
            actual_salary[j] = salary[j] - rebate;
            result[j] = toCalculate(actual_salary[j], slabAmt, percent, slabCount);
            if(result[j]==0){
                System.out.println("Based on the given salary and slab details " +
                        names[j]+" need not pay Income Tax this year.");
            }
            else {
                System.out.println("The Calculated Income Tax to be paid by " +
                        names[j] + " : Rs." + result[j]+".");
            }
        }
    }
    static long toCalculate(long salary,long[] slabAmt, int[] percent, int slabCount){
        long diff =0;
        int flag =0;
        int ref =0;
        int k=0;
        long result=0;
        for(k=0;k<slabCount;k++){
            if(salary<slabAmt[k]){
                ref = k;
                flag=1;
                break;
            }
        }
        if(flag==0){
            ref=slabCount-1;
        }
        k=0;
        do{
            if(k==0){
                result = result +((slabAmt[k]*percent[k])/100) ;
                salary=salary-slabAmt[k];
            }
            else if(k<ref){
                diff=slabAmt[k]-slabAmt[k-1];
                result = result + ((diff*percent[k])/100);
                salary=salary-diff;
            }
            else if(k==ref){
                result=result+((salary*percent[k])/100);
            }
            k++;
        }while(k<=ref);
        return result;
    }

    public static void main(String[] args) {
        getDetails();
    }
}