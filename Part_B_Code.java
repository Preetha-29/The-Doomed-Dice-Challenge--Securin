
/*

Now comes the real challenge. You were happily spending a lazy afternoon playing
your board game with your dice when suddenly the mischievous Norse God Loki ( You
love Thor too much & Loki didn’t like that much ) appeared.
Loki dooms your dice for his fun removing all the “Spots” off the dice.
No problem! You have the tools to re-attach the “Spots” back on the Dice.
However, Loki has doomed your dice with the following conditions:
● Die A cannot have more than 4 Spots on a face.
● Die A may have multiple faces with the same number of spots.
● Die B can have as many spots on a face as necessary i.e. even more than 6.
But in order to play your game, the probability of obtaining the Sums must remain the
same!
So if you could only roll P(Sum = 2) = 1/X, the new dice must have the spots reattached
such that those probabilities are not changed.
Input:
● Die_A = [1, 2, 3, 4, 5, 6] & Die B = Die_A = [1, 2, 3, 4, 5, 6]
Output:
● A Transform Function undoom_dice that takes (Die_A, Die_B) as input &
outputs New_Die_A = [?, ?, ?, ?, ?, ?],New_Die_B = [?, ?,
?, ?, ?, ?] where,
● No New_Die A[x] > 4

*/

import java.util.ArrayList;
import java.util.List;

public class Part_B_Code {
  //  all combinations of dice A
  public static void diceA(int[] ele, int length, List<Integer> current, List<List<Integer>> all) {
    if (current.size() == length) {
      all.add(new ArrayList<>(current));
      return;
    }
    for (int e : ele) {
      current.add(e);
      diceA(ele, length, current, all);
      current.remove(current.size() - 1);
    }
  }
  //  all combinations of dice B
  public static void diceB(int[] ele, int length, int start, List<Integer> current, List<List<Integer>> all) {
    if (current.size() == length) {
      all.add(new ArrayList<>(current));
      return;
    }
    for (int i = start; i < ele.length; i++) {
      current.add(ele[i]);
      diceB(ele, length, i + 1, current, all);
      current.remove(current.size() - 1);
    }
  }
// calculate the sum probability 
public static double[] probSum(List<Integer> arr1, List<Integer> arr2) {
    double[] sum = new double[12];
    for (int i : arr1) {
      for (int j : arr2) {
        int k = i + j;
        sum[k - 1] += 1.0;
      }
    }
    for (int i = 0; i < sum.length; i++) {
      if (sum[i] != 0.0) {
        sum[i] /= 36.0;
      }
    }
    return sum;
  }
// find new combinations
  public static void transform(int[] dieA, int[] dieB) {
    int[] ele1 = {1, 2, 3, 4};
    int length = 6;
    List<Integer> current = new ArrayList<>();
    List<List<Integer>> combo1 = new ArrayList<>();
    diceA(ele1, length, current, combo1);

    int[] ele2 = {1, 2, 3, 4, 5, 6, 7, 8};
    int start = 0;
    List<List<Integer>> combo2 = new ArrayList<>();
    diceB(ele2, length, start, current, combo2);

    double[] sum1 = probSum(toList(dieA), toList(dieB));

    boolean flag = false;
    for (List<Integer> i : combo1) {
      for (List<Integer> j : combo2) {
        if (java.util.Arrays.equals(probSum(i, j), sum1)) {
          System.out.print("New die_a: ");
          for (int x : i) {
            System.out.print(x + " ");
          }
          System.out.println();
          System.out.print("New die_b: ");
          for (int x : j) {
            System.out.print(x + " ");
          }
          System.out.println();
          flag = true;
          break;
        }
      }
      if (flag) {
        break;
      }
    }
  }

  public static List<Integer> toList(int[] arr) {
    List<Integer> list = new ArrayList<>();
    for (int num : arr) {
      list.add(num);
    }
    return list;
  }

  public static void main(String[] args) {
    int[] dieA = {1, 2, 3, 4, 5, 6};
    int[] dieB = {1, 2, 3, 4, 5, 6};
    System.out.println("PART-B- Answer\nThe new combination is:");
    transform(dieA, dieB);
  }
}

