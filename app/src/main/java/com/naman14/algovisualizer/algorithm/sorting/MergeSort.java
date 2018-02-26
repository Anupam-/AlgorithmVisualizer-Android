package com.naman14.algovisualizer.algorithm.sorting;

import android.app.Activity;

import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.SortingVisualizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergio on 02/01/17.
 */

public class MergeSort extends SortAlgorithm implements DataHandler {

    private int[] array;

    public MergeSort(SortingVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    private void sort(){
        logArray("Original array - " ,array);
        mergeSort(0,array.length-1);
        completed();
    }

    public void mergeSort(int start, int end){
        addLog("Sorting array between " + start + " and " + end);
        if (start >= end){
            return;
        }
        int middle = (start + end)/2;
        highlightInterval(start,middle);
        sleep();
        mergeSort(start,middle);
        highlightInterval(middle+1,end);
        sleep();
        mergeSort(middle+1,end);
        highlightInterval(start,end);
        sleep();
        merge(start,middle,middle+1,end);
    }

    public void merge(int start1, int end1, int start2, int end2){
        addLog("merging sub-array " + start1 + " to " + end1 + " and subarray " + start2 + " to " +
        end2);
        List<Integer> sortedList = new ArrayList<Integer>();
        int s0 = start1;
        while(start1 <= end1){
            if (start2 > end2){
                sortedList.add(array[start1]);
                start1+=1;
                continue;
            }
            if (array[start1] <= array[start2]){
                sortedList.add(array[start1]);
                start1+=1;
            }
            else{
                sortedList.add(array[start2]);
                start2+=1;
            }
        }
        int i = 0;
        for (int number:sortedList) {
            array[s0+i]=number;
            i+=1;
        }
        sleep();
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void onDataRecieved(Object data) {
        super.onDataRecieved(data);
        this.array = (int[])data;

    }

    @Override
    public void onMessageReceived(String message) {
        super.onMessageReceived(message);
        if(message.equals(Algorithm.COMMAND_START_ALGORITHM)){
            startExecution();
            sort();
        }

    }
}
