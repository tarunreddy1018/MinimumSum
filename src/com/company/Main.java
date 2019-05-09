package com.company;

/*----------------------------------------------
----------------@author: Tarun Reddy---------------
----------------@credits: Sowjanya---------------
------------2019-04-28 14:07:17 IST------------
------------------------------------------------*/

import java.io.*;
import java.util.*;

public class Main {
    private static int getMaxNumber(int array[]) {
        int max = Integer.MIN_VALUE;
        for(int value:array) {
            max = Math.max(max,value);
        }
        return max;
    }

    private static int getMsbBitPosition(int maxNumber) {
        int pos = -1;
        while(maxNumber > 0) {
            pos++;
            maxNumber >>= 1;
        }
        return pos;
    }

    private static long power(long a, long b) {
        long result = 1, multiplier = a;
        while (b != 0) {
            if ((b & 1) != 0) {
                result *= multiplier;
            }
            multiplier *= multiplier;
            b = b >> 1;
        }
        return result;
    }

    private static long findMinimumSum(int array[]) {
        int maxNumber = getMaxNumber(array);
        int msbBitPositionInMaxNumber = getMsbBitPosition(maxNumber);
        int setBitCountArray[] = new int[msbBitPositionInMaxNumber+1];

        for(int value:array) {
            int pos = 0;
            while(value > 0) {
                if((value&1) != 0) {
                    setBitCountArray[pos]++;
                }
                pos++;
                value >>= 1;
            }
        }

        long minSum = 0;
        for(int i = 0;i < setBitCountArray.length;i++) {
            int minCount = Math.min(setBitCountArray[i] , (array.length - setBitCountArray[i]));
            minSum += (minCount*power(2,i));
        }
        return minSum;
    }

    private static int count(int array[],int num) {
        int c = 0;
        for(int value:array) {
            if(value%num == 0) {
                c++;
            }
        }
        return c;
    }

    private static int gcd(int a,int b) {
        if(b == 0) {
            return a;
        }
        return gcd(b,a%b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder outputString = new StringBuilder("");

        int testCases = Integer.parseInt(br.readLine());
        while(testCases --> 0) {
            int size = Integer.parseInt(br.readLine());
            int array[] = Arrays.stream(br.readLine().split(" ")).mapToInt(c->Integer.parseInt(c)).toArray();
            outputString.append(findMinimumSum(array)).append("\n");
        }
        bw.write(outputString.toString());
        bw.close();

//        int array[] = Arrays.stream(br.readLine().split(" ")).mapToInt(c->Integer.parseInt(c)).toArray();
//        for(int i = 1;i <= 20;i++) {
//            outputString.append(i + " --> " + count(array,i)).append("\n");
//        }
//        outputString.append("***********************************").append("\n");
//        int pairs = 0;
//        HashMap<Integer,Integer> map = new HashMap<>();
//        for(int i = 0;i < array.length;i++) {
//            for(int j = (i+1);j < array.length;j++) {
//                int res = gcd(array[i],array[j]);
//                if(res > 4) {
//                    if(map.containsKey(res)) {
//                        map.put(res,map.get(res)+1);
//                    }
//                    else {
//                        map.put(res,1);
//                    }
//                    outputString.append(array[i] + " " + array[j]).append("\n");
//                    pairs++;
//                }
//            }
//        }
//        outputString.append("***********************************").append("\n");
//        for(Map.Entry<Integer,Integer> entry:map.entrySet()) {
//            int key = entry.getKey();
//            int value = entry.getValue();
//            outputString.append(key + " --> " + value).append("\n");
//        }
//        outputString.append("***********************************").append("\n");
//        outputString.append("Pairs --> " + pairs);
//        bw.write(outputString.toString());
//        bw.close();
    }
}