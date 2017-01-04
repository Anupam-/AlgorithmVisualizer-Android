package com.naman14.algovisualizer;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.test.mock.MockContext;

import com.naman14.algovisualizer.algorithm.sorting.MergeSort;
import com.naman14.algovisualizer.visualizer.SortingVisualizer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by sergio on 02/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MergeSortTest {

    @Mock
    private SortingVisualizer mSortVisualizer;
    @Mock
    private Activity mActivity;
    @Mock
    private LogFragment mLogFragment;

    private MergeSort mergeSort;

    public MergeSortTest(){

    }

    @Before
    public void before(){
        mergeSort = new MergeSort(mSortVisualizer, mActivity,mLogFragment);
    }

    @Test
    public void mergeSort() throws Exception {
        int [] array = new int[] {4,1,2,4,6,8,7,10,9};
        int [] sortedArray = new int [] {1,2,4,4,6,7,8,9,10};

        mergeSort.onDataRecieved(array);
        mergeSort.mergeSort(0,array.length-1);

        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i],sortedArray[i]);
        }
    }

    @Test
    public void mergeSort_empty_array() throws Exception{
        int [] array = new int[]{};
        mergeSort.onDataRecieved(array);
        mergeSort.mergeSort(0,array.length-1);
        assertEquals(array.length,0);
    }

    @Test
    public void mergeSort_one_element_array() throws Exception{
        int [] array = new int[]{1};
        mergeSort.onDataRecieved(array);
        mergeSort.mergeSort(0,array.length-1);
        assertEquals(array.length,1);
        assertEquals(array[0],1);
    }

    @Test
    public void merge() throws Exception {

        int [] array = new int[] {1,5,6,7,1,2,3,10};
        int [] mergedArray = new int[] {1,1,2,3,5,6,7,10};
        mergeSort.onDataRecieved(array);
        mergeSort.merge(1,3,4,6);

        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i],mergedArray[i]);
        }
    }
}