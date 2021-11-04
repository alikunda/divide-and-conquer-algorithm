/**
 * Name:Mohammed ali
 */

/*
this programs implement divide and conquer algorithm to find the max subarray to find the maximium profit
*/
package assignment.pkg3;

/**
 *
 * @author ali kunda
 */
import java.util.Random;
public class Assignment3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Welcome to buying selling max profit finder.");
        System.out.println(" ");
         int[] array = new int[100];    //created an array of size 100
         generateRandomNum(array);      //generating random number, assigning it to arrary and printing the days and the price
         
       int[] A = print_change_in_profit(array);   //print change in profit day by day
       
        int[] result = findMaxSubArray(A, 0, A.length-1);  //save the lowest highest day and max profit
        
        System.out.println("------------------------------------------------------Summary----------------------------------------------------------------------");
        
        //print the data
        if(result[9]==1)
        {
        System.out.println("Left i = "+result[3]);
        System.out.println("Left J = "+result[4]);
        System.out.println("Max Profit for left subarray = $"+result[5]);
        System.out.println("-----------------------------------------");   
        System.out.println("Right i = "+result[6]);
        System.out.println("Right J = "+result[7]);
        System.out.println("Max Profit for right subarray = $"+result[8]);
        System.out.println("-----------------------------------------");
        System.out.println("---------------Highest-----------------");
        System.out.println("Cross lowest Day  (i) = "+result[0]);    
        System.out.println("Cross Highest Day (J) = "+result[1]);
        System.out.println("Maximum Profit croos subaaray = $"+result[2]);
        }
        else if(result[9]==2)
        
        {
        System.out.println("cross i = "+result[3]);
        System.out.println("cross tJ = "+result[4]);
        System.out.println("Max Profit for Cross subarray = $"+result[5]);
        System.out.println("-----------------------------------------");            
        System.out.println("Right i = "+result[6]);
        System.out.println("Right J = "+result[7]);
        System.out.println("Max Profit for right subarray = $"+result[8]);
        System.out.println("-----------------------------------------");
        System.out.println("---------------Highest-----------------");
        System.out.println("left lowest Day  (i) = "+result[0]);    
        System.out.println("left Highest Day (J) = "+result[1]);
        System.out.println("Maximum Profit for left subarray = $"+result[2]);
        
        }
        else if(result[9]==3)
        { 
         System.out.println("cross i = "+result[3]);
        System.out.println("cross J = "+result[4]);
        System.out.println("Max Profit for cross subarray = $"+result[5]);
        System.out.println("-----------------------------------------");
            
        System.out.println("left i = "+result[6]);
        System.out.println("left J = "+result[7]);
        System.out.println("Max Profit for left subarray = $"+result[8]);
        System.out.println("-----------------------------------------");
        System.out.println("---------------Highest-----------------");
        System.out.println("Right lowest Day  (i) = "+result[0]);    
        System.out.println("Right Highest Day (J) = "+result[1]);
        System.out.println("Maximum Profit for right subarray = $"+result[2]);
            
        }
        
        System.out.println("-----------------------------------------");
        
        System.out.println("Therefore, if we buy stocks on "+result[0]+" day, at this price $"+array[result[0]-1]+" and sell on "+result[1]+" day, at this price $"+array[result[1]-1]
                +"\nwe will get maximium profit of $"+result[2]);
    }
    
    public static void generateRandomNum(int A[])
    {
     
        System.out.print("Day:      "); 
        Random rand = new Random();
        for(int i = 0; i<A.length;i++)
        {
             A[i]=rand.nextInt(151-50)+50;  //generating number in 50-150 range
             System.out.print((i+1)+"\t "); 
             
        }
        System.out.println(" ");
        
        //printing the price
        System.out.print("Price:   ");
        for(int i = 0;i< A.length;i++)
        {
            System.out.print(A[i]+"\t ");
        }
        System.out.println(" ");
        
       
    }
    public static int []print_change_in_profit(int arr[])
    {
        //printing and assigning the difference in price to day prior to it.
        int newArray[] = new int[100];
        System.out.print("Change:\t         ");
       int diff = 0;   
        for(int i = 0; i <99;i++)
        {
            if(i>98)
            {
                break;
            }
            diff = arr[i+1]-arr[i];
            newArray[i]= diff;
            System.out.print(diff+",\t");
        }
        System.out.println("\n");
        return newArray;
    }
  
    public static int[] findMaxSubArray(int A[],int low, int high)
    {
        
        if(high==low)
        {
            int[] result = {low,high,A[low]};
            return result;
        }
        else{
            int mid = (low+high)/2;  //find the mid point
            
            int[] leftSide = findMaxSubArray(A, low, mid); 
            int[] rightSide = findMaxSubArray(A, mid+1, high);
            int[] crossSide = findMaxCrossingSubArray(A, low, mid, high);
            
            
            
            int[] cross = {crossSide[0],crossSide[1],crossSide[2],leftSide[0],leftSide[1],leftSide[2],rightSide[0],rightSide[1],rightSide[2],1};
            int[] left = {leftSide[0],leftSide[1],leftSide[2],crossSide[0],crossSide[1],crossSide[2],rightSide[0],rightSide[1],rightSide[2],2};
            int[] right = {rightSide[0],rightSide[1],rightSide[2],crossSide[0],crossSide[1],crossSide[2],leftSide[0],leftSide[1],leftSide[2],3};

            if(leftSide[2]>=rightSide[2] && leftSide[2]>= crossSide[2])
            {
                return left;
            }
            else if(rightSide[2]>=leftSide[2] && rightSide[2]>= crossSide[2])
            {
                return right;
            }
            else{
                
                return cross;
            }
            
            
            
        }
        
    }
    public static int[] findMaxCrossingSubArray(int A[],int low, int mid, int high)
    {
        int leftSum = Integer.MIN_VALUE;
        int maxLeft = 0;
        int sum = 0;
        for(int i = mid; i>= low;i--)
        {
            sum=sum+A[i];
            if(sum > leftSum)
            {
                leftSum=sum;
                maxLeft = i;  
            }
        }
        int rightSum = Integer.MIN_VALUE;
       int maxRight =0;
        sum = 0;
        for(int j = mid+1; j<=high;j++)
        {
            sum=sum+A[j];
            if(sum>rightSum)
            {
                rightSum = sum;
                maxRight = j;
            }
        }
        int total = leftSum+rightSum;
        int result[]= {maxLeft+1,maxRight+2,total};
        return result;
    }
}
