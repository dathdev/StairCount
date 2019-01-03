import java.util.Scanner;

public class MainClass {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = 0;
        System.out.print("Enter the number of blocks: ");
        n = sc.nextInt();
        System.out.printf("Number of cases for %d blocks: %d", n, DynamicStairCount2D(n));
   }

    private static int DynamicStairCount2D(int n) {
        int[][] stepArr2D = new int[n+1][n+1]; // [block no] [height]
        for (int i = 0; i < n+1; i++) { // initialize everything with 0s
            for (int j = 0; j<n+1; j++){
                stepArr2D[i][j] = 0;
            }
        }
        for (int i = 1; i < n+1; i++){ // initialize 1 block and 2 blocks cases
            stepArr2D[1][i] = 1;
            if (i>=2) {
                stepArr2D[2][i] = 1;
            }
        }
        for (int i = 3; i <= n; i++) { // i is the block no, going from 3 blocks
            System.out.println("block number i = " + i);
            int count = 0;
            for (int j = 1; j <= i; j++) { // height going up from 1 to current number of blocks
                // TODO: calculate the number of possible cases for stepArr2D[i,j]
                // aka number of cases for i blocks at j height and below
                // i-j is the remaining number of blocks
                // j-1 is the height of the stairs aka the number of blocks on the furthest left
                count += stepArr2D[i - j][j - 1]; //count keeps track of the total valid cases as we increase the height
                System.out.printf("    height j = %d\n", j);
                System.out.printf("    remaining blocks i-j = %d\n", i - j);
                System.out.printf("    arr[i-j][j-1] = %d\n", stepArr2D[i - j][j - 1]);
                System.out.println("      --");
                stepArr2D[i][j] = count; //maximum number of cases at j height and below
                // at max height, we fill out the rest of the table with the maximum number of cases for each number of blocks
            }
            for (int j = i; j <= n; j++) {
                stepArr2D[i][j] = count + 1;
            }
            System.out.println("StairCount(" + i + ") = " + stepArr2D[i][i - 1]);
            System.out.println("=====");
        }
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++){
//                System.out.printf("%5d", stepArr2D[i][j]);
//            }
//            System.out.println();
//        }
        return stepArr2D[n][n-1];
    }

   // my failed attempt with a 1D arraylist;
//    private static int DynamicStairCount(int n) {
//        ArrayList<Integer> stepArr = new ArrayList<>();
//        stepArr.add(0); // 0
//        stepArr.add(1); // 1
//        stepArr.add(1); // 2
//        for (int i = 3; i <= n; i++) {
//            System.out.println("i = " + i);
//            int count = 0;
//            for (int j = i-1; j > 1; j--) {
//                if (j>i-j) {
//                    System.out.println("    j = " + j);
//                    System.out.println("    i-j = " + (i-j));
//                    count += stepArr.get(i-j);
//                    if (i-j >= 3) {
//                        count++;
//                    }
//                } else if (j<i-j) {
//
//                } else if (j == i-j && i-j>2) {
//                    System.out.println("    j = " + j);
//                    System.out.println("    i-j = " + (i-j));
//                    count += stepArr.get(i-j);
//                }
//            }
//            System.out.println("StairCount(" + i + ") = " + count);
//            stepArr.add(count);
//        }
//        return stepArr.get(n);
//    }
//
    // my failed attempt with recursion
//    private static int ActualStairCount(int n) {
//        if (n == 1 || n == 2) {
//            return 1;
//        }
//        int count = 0;
//        for (int i = n-1; i > n/2; i--) { //i is the number of blocks on the left step
//            count++;
//            count += ActualStairCount(n-i); //n-i is the number of remaining blocks
//        }
//        return count;
//    }
//
//    private static int StairCount(int n) {
//        return ActualStairCount(n)-1;
//    }
}
