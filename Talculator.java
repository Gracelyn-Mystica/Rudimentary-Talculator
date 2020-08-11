package com.java_repo;
import java.util.Scanner;
public class Talculator {

    public static void main(String[] args) {
	    toGetInfo();
    }

    public static void toGetInfo() {
        long salary=0, rent=0, HRA=0, LTA=0, splAllow=0, otherSrc=0;
        long taxSaveInvest=0, medInsurance=0, saveAccInvest=0;
        int age=0;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your age : ");
        age = scan.nextInt();//40
        System.out.print("Enter your Salary : ");
        salary = scan.nextLong();//233040
        System.out.print("Enter your Rent : ");
        rent = scan.nextLong();//150000
        System.out.print("Enter your HRA : ");
        HRA = scan.nextLong();//180000
        System.out.print("Enter your LTA : ");
        LTA = scan.nextLong();//0
        System.out.print("Enter your Special Allowance : ");
        splAllow = scan.nextLong();//674620
        System.out.print("Enter your income through other resources : ");
        otherSrc = scan.nextLong();//0
        System.out.print("Enter your tax saving investments : ");
        taxSaveInvest = scan.nextLong();//94470
        System.out.print("Enter your medical insurance : ");
        medInsurance = scan.nextLong();//10000
        System.out.print("Enter your savings account interest : ");
        saveAccInvest = scan.nextLong();//0
        long reduction = taxSaveInvest+medInsurance+saveAccInvest;
        long TS1 = oldRegimeCalc(salary,rent,HRA,LTA,splAllow,otherSrc,reduction);
        long TS2 = newRegimeCalc(salary,HRA,LTA,splAllow,otherSrc);
        if(TS1<=500000 || TS2<=500000){
            System.out.println("Need not pay income tax for the year.");
        }
        else {
            long result1 = oldRegimeSlabCalc(age, TS1);
            long result2 = newRegimeSlabCalc(TS2);
            System.out.println("Approximately your income tax will be around the range");
            System.out.println("The income tax amount need to be paid " +
                    "according to the old regime is : "+result1);
            System.out.println("The income tax amount need to be paid " +
                    "according to the new regime is : "+result2);
        }
    }

    public static long oldRegimeCalc(long salary, long rent, long hra, long lta, long splAllow,
                                     long otherSrc, long reduction) {
        long stdRed=50000,exemption=0,min=0,a=0,c=0,taxableAmt=0;
        a = (salary*50)/100;
        c = rent-((salary*10)/100);
        if(a<=hra && a<=c)
            min = a;
        else if(hra<=a && hra<=c)
            min = hra;
        else
            min = c;
        exemption = lta+min;
        taxableAmt = (salary+splAllow+otherSrc)-(stdRed+exemption+reduction);
        return taxableAmt;
    }

    public static long newRegimeCalc(long salary, long hra, long lta, long splAllow, long otherSrc) {
        return salary+hra+lta+splAllow+otherSrc;
    }

    public static long oldRegimeSlabCalc(int age,long TS1){
        long safe = TS1;
        int[] slab1 ;
        int[] percent1;
        if(age>=60 && age<80) {
            slab1 = new int[]{300000, 500000, 750000, 1000000, 1250000, 1500000, 1500001};
            percent1 = new int[]{0, 5, 20, 20, 30, 30, 30};
        }
        else if(age>=80){
            slab1 = new int[]{250000, 500000, 750000, 1000000, 1250000, 1500000, 1500001};
            percent1 = new int[]{0, 0, 20, 20, 30, 30, 30};
        }
        else {
            slab1 = new int[]{250000, 500000, 750000, 1000000, 1250000, 1500000, 1500001};
            percent1 = new int[]{0, 5, 20, 20, 30, 30, 30};
        }
        long diff = 0, result=0;
        int k = 0, flag = 0, ref=0;
        for(k=0;k<=6;k++){
            if(TS1<=slab1[k]){
                ref = k;
                flag=1;
                break;
            }
        }
        if(flag==0){
            ref=6;
        }
        k=0;
        do{
            if(k==0){
                result = result +((slab1[k]*percent1[k])/100) ;
                TS1=TS1-slab1[k];
            }
            else if(k<ref){
                diff=slab1[k]-slab1[k-1];
                result = result + ((diff*percent1[k])/100);
                TS1=TS1-diff;
            }
            else if(k==ref){
                result=result+((TS1*percent1[k])/100);
            }
            k++;
        }while(k<=ref);
        result = result + ((result*4)/100);
        return result;
    }
    public static long newRegimeSlabCalc(long ts2) {
        long safe = ts2;
        int[] slab1 = {250000,500000,750000,1000000,1250000,1500000,1500001};
        int[] percent1 = {0,5,10,15,20,25,30};
        long diff = 0, result=0;
        int k = 0, flag = 0, ref=0;
        for(k=0;k<=6;k++){
            if(ts2<=slab1[k]){
                ref = k;
                flag=1;
                break;
            }
        }
        if(flag==0){
            ref=6;
        }
        k=0;
        do{
            if(k==0){
                result = result +((slab1[k]*percent1[k])/100) ;
                ts2=ts2-slab1[k];
            }
            else if(k<ref){
                diff=slab1[k]-slab1[k-1];
                result = result + ((diff*percent1[k])/100);
                ts2=ts2-diff;
            }
            else if(k==ref){
                result=result+((ts2*percent1[k])/100);
            }
            k++;
        }while(k<=ref);
        result = result + ((result*4)/100);
        return result;

    }
}