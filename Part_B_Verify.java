//Part-B Output Verfication  
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part_B_Verify {

    
    public static int totalCombo(List<Integer> dieA, List<Integer> dieB) {
        return dieA.size() * dieB.size();
    }

    // display  all possible combinations
    public static void display(List<Integer> dieA, List<Integer> dieB) {
        int[][] matrix = new int[dieA.size()][dieB.size()]; 

        
        for (int i = 0; i < dieA.size(); ++i) {
            for (int j = 0; j < dieB.size(); ++j) {
                matrix[i][j] = dieA.get(i) + dieB.get(j);
            }
        }

        
        System.out.println("Combinations matrix:");
        for (int i = 0; i < dieA.size(); ++i) {
            for (int j = 0; j < dieB.size(); ++j) {
                System.out.printf("%-3d", matrix[i][j]);
            }
            System.out.println();
        }
    }

   //Gcd
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // calculate the probability of all possible sums 
    public static void probabilityCal(List<Integer> dieA, List<Integer> dieB) {
        int[] sums = new int[dieA.size() + dieB.size() - 1]; 

        
        for (int i = 0; i < dieA.size(); ++i) {
            for (int j = 0; j < dieB.size(); ++j) {
                sums[dieA.get(i) + dieB.get(j) - 2]++;
            }
        }

        // Display the probabilities- Fraction 
        System.out.println("\nProbabilities (in fraction format):");
        for (int i = 2; i <= dieA.size() + dieB.size(); ++i) {
            int numerator = sums[i - 2];
            int denominator = totalCombo(dieA, dieB);

            
            int gcd_val = gcd(numerator, denominator);

            
            numerator /= gcd_val;
            denominator /= gcd_val;

            System.out.println("P(Sum = " + i + ") = " + numerator + "/" + denominator);
        }
    }

    // generates all possible combination pairs
    public static List<List<Integer>> genPairs(List<Integer> dieA, List<Integer> dieB) {
        List<List<Integer>> pairs = new ArrayList<>();
        for (int i : dieA) {
            for (int j : dieB) {
                List<Integer> pair = new ArrayList<>();
                pair.add(i);
                pair.add(j);
                pairs.add(pair);
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        List<Integer> dieA = Arrays.asList(1,2,2,3,3,4);
        List<Integer> dieB = Arrays.asList(1,3,4,5,6,8);
        
        // total possible combinations-Ques-1
        System.out.println("\nPART-A - Question-1");
        System.out.println("\nTotal Combinations: " + totalCombo(dieA, dieB)+"\n");
        
        // Calculate and display the distribution of all possible combinations that can be obtained when rolling both Die A and Die B together.
        System.out.println("\nPART-A - Question-2");
        System.out.println("\nAll Possible Combination Pairs:");
        List<List<Integer>> pairs = genPairs(dieA, dieB);
        for (List<Integer> p : pairs) {
            System.out.print(p+",");
        }
        System.out.println();
        System.out.println();
        
        display(dieA, dieB);
        
        System.out.println();
        //Calculate the Probability of all Possible Sums occurring among the number of combinations-Ques-3
         System.out.println("\nPART-A - Question-3");
        probabilityCal(dieA, dieB);
    
        
    }
}
