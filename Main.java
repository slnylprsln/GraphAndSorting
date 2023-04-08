import java.io.*;
import java.util.*;

//Name Surname: Selenay Alparslan
//Student ID: 110510194

public class Main {
	
	/////////////////////////////////QUICK SORT///////////////////////////////////////
	public static void quickSort(ArrayList<Integer> list, int start, int end) { 
        if (start < end) { 
            int pi = sorting(list, start, end); 
            quickSort(list, start, pi-1); 
            quickSort(list, pi+1, end); 
        } 
    } 
	
	public static int sorting(ArrayList<Integer> list, int start, int end) { 
        int pivot = list.get(end);  
        int i = (start-1);
        for (int j=start; j<end; j++) { 
            if (list.get(j) < pivot) { 
                i++; 
                
                //swapping
                int temp = list.get(i); 
                list.set(i, list.get(j)); 
                list.set(j, temp); 
            } 
        } 
        
        //changing pivot
        int temp = list.get(i+1); 
        list.set(i+1, list.get(end)); 
        list.set(end, temp); 
  
        return i+1; 
    } 
	
	/////////////////////////////////HEAP SORT///////////////////////////////////////
	public static void heapSort(ArrayList<Integer> list, ArrayList<ArrayList<Integer>> common) {
        int size = list.size();
 
        // Build heap (rearrange array)
        for (int i=size/2-1; i>=0; i--)
            heap(list, size, i, common);
 
        // One by one extract an element from heap
        for (int i=size-1; i>0; i--) {
        	
            // Move current root to end
            int temp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, temp);
            Collections.swap(common.get(0), 0, i);
            Collections.swap(common.get(1), 0, i);
 
            // call max heapify on the reduced heap
            heap(list, i, 0, common);
        }
    }
	
	public static void heap(ArrayList<Integer> list, int n, int i, ArrayList<ArrayList<Integer>> common){
        int largest = i; //Initialize largest item as root
        int left = 2*i + 1;
        int right = 2*i + 2;
 
        //When left child is larger than root(largest)
        if (left < n && list.get(left) > list.get(largest)) largest = left;
           
        //If right child is larger than root(largest)
        if (right < n && list.get(right) > list.get(largest)) largest = right;
 
        //If largest is not root
        if (largest != i) {
            int swap = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, swap);
            Collections.swap(common.get(0), i, largest);
            Collections.swap(common.get(1), i, largest);
 
            //Recursively heapify sub-trees
            heap(list, n, largest, common);
        }
    }
	
	/////////////////////////////////MERGE SORT///////////////////////////////////////
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> whole) {
        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();
        int mid;
 
        if (whole.size() == 1) return whole;

        else {
        	mid = whole.size()/2;
        	
            for(int i=0; i<mid; i++) {
            	left.add(whole.get(i));
            }

            for(int i=mid; i<whole.size(); i++) {
            	right.add(whole.get(i));
            }

            left  = mergeSort(left);
            right = mergeSort(right);
            merging(left, right, whole);
        }
        return whole;
    }
	
	public static void merging(ArrayList<Integer> left, ArrayList<Integer> right, ArrayList<Integer> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
 
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } 
            else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }
 
        ArrayList<Integer> array;
        int restIndex;
        
        if (leftIndex >= left.size()) {
        	array = right;
            restIndex = rightIndex;
        } 
        
        else {
        	array = left;
            restIndex = leftIndex;
        }
 
        for (int i=restIndex; i<array.size(); i++) {
            whole.set(wholeIndex, array.get(i));
            wholeIndex++;
        }
    }
	
	/////////////////////////////////INSERTION SORT///////////////////////////////////////
	public static void insertionSort(ArrayList<Integer> list) { 
        int n = list.size(); 
        for(int i=1; i<n; i++) { 
            int a = list.get(i); 
            int j = i-1; 
            
            while (j>=0 && list.get(j)>a) { 
            	list.set(j+1, list.get(j)); 
                j--;
            } 
            list.set(j+1, a); 
        } 
    } 

	public static void main(String[] args) throws IOException {
		File control = new File("control.txt");
		FileWriter fw = new FileWriter(control);
		
		File mainFile = new File("FinalExamGraphFile.txt");
		Scanner bigFile = new Scanner(mainFile);
		
        Graph wholeGraph = new Graph(32836);
        
        
        //Reading big FinalExamGraphFile.txt file and loading friendships into graph
        ArrayList<ArrayList<Integer>> friendship = new ArrayList<ArrayList<Integer>>();
        while(bigFile.hasNext()) {
        	int a = bigFile.nextInt();
        	int b = bigFile.nextInt();
        	int c = bigFile.nextInt();
        	ArrayList<Integer> friends = new ArrayList<Integer>();
        	friends.add(a);
        	friends.add(b);
        	friendship.add(friends);
        	wholeGraph.addEdge(a,b,c);
        }

        
        //Searching for adjacents of an ID and printing them out
		System.out.println("Please enter an ID to search: ");
		Scanner userInput = new Scanner(System.in);
		int toSearch = userInput.nextInt();
		wholeGraph.askForAdjacents(toSearch);
		System.out.println("\n------------------------------ Please wait for sorting ------------------------------");
		System.out.println("(If you wish, you can check 'control.txt' file to see common friends that pairs have.)\n");
		
		
		//Loading Positive.txt file 
		File positiveFile = new File("Positive.txt");
		Scanner readPositive = new Scanner(positiveFile);
		Graph positiveGraph = new Graph(15000);
		ArrayList<ArrayList<Integer>> positives = new ArrayList<ArrayList<Integer>>();
		while(readPositive.hasNext()) {
			int a = readPositive.nextInt();
			int b = readPositive.nextInt();
			ArrayList<Integer> positivePairs = new ArrayList<Integer>();
			positivePairs.add(a);
			positivePairs.add(b);
			positives.add(positivePairs);
			positiveGraph.addEdge(a, b, 1);
		}
		
		
		//Creating random negative pairs that are not included in 
		//the other two file (positive pairs and big file of friendships)
		Random r = new Random();
		Graph negativeGraph = new Graph(10000);
		ArrayList<ArrayList<Integer>> negatives = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<5592; i++) {
			int a = r.nextInt(2000);
			int b = r.nextInt(2000);
			ArrayList<Integer> negativePairs1 = new ArrayList<Integer>();
			negativePairs1.add(a);
			negativePairs1.add(b);
			if(a==b || positives.contains(negativePairs1) || friendship.contains(negativePairs1)) {
				int newa = r.nextInt(2000);
				int newb = r.nextInt(2000);
				ArrayList<Integer> negativePairs2 = new ArrayList<Integer>();
				negativePairs2.add(newa);
				negativePairs2.add(newb);
				negatives.add(negativePairs2);
				negativeGraph.addEdge(newa, newb, 1);
			}
			else {
				negatives.add(negativePairs1);
				negativeGraph.addEdge(a, b, 1);
			}
		}
		
		
		//Creating arraylist of arraylist in purpose of 
		//collecting all data (source,destination,common friend numbers)
		ArrayList<ArrayList<Integer>> commons = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> sources = new ArrayList<Integer>();
		ArrayList<Integer> destinations = new ArrayList<Integer>();
		ArrayList<Integer> score = new ArrayList<Integer>();
		
		
		//commons.get(0) will give "sources" in order
		//commons.get(1) will give "destinations" in order
		//commons.get(2) will give "scores" in order
		commons.add(sources);
		commons.add(destinations);
		commons.add(score);
		
		
		fw.write("-----------------------------------------NOT-SORTED-----------------------------------------\n");
		
		//Finding common friend scores of negative pairs
		Graph negativesWithScore = new Graph(10000);
		for(int i=0; i<negativeGraph.size; i++) {
			LinkedList<Edge> source = negativeGraph.list[i];
			for(int j=0; j<source.size(); j++) {
				int common = wholeGraph.findCommon(i, source.get(j).destination);
				fw.write(i + " and "+ source.get(j).destination + " have " + common +" common (Negative Pairs)\n");
				commons.get(0).add(i);
				commons.get(1).add(source.get(j).destination);
				commons.get(2).add(common);
				negativesWithScore.addEdge(i, source.get(j).destination, common);
			}
		}
		
		//Finding common friend scores of positive pairs
		Graph positivesWithScore = new Graph(10000);
		for(int i=0; i<positiveGraph.size; i++) {
			LinkedList<Edge> source = positiveGraph.list[i];
			for(int j=0; j<source.size(); j++) {
				int common = wholeGraph.findCommon(i, source.get(j).destination);
				fw.write(i + " and "+ source.get(j).destination + " have " + common +" common (Positive Pairs)\n");
				commons.get(0).add(i);
				commons.get(1).add(source.get(j).destination);
				commons.get(2).add(common);
				positivesWithScore.addEdge(i, source.get(j).destination, common);
			}
		}
		
		
		//Copying common friend score arraylist to use all sorting algorithms properly
		ArrayList<Integer> newCommons1 = new ArrayList<Integer>(score);
		ArrayList<Integer> newCommons2 = new ArrayList<Integer>(score);
		ArrayList<Integer> newCommons3 = new ArrayList<Integer>(score);
		
		
		//Performing quick sort and calculating its execution time
		long startQuick = System.currentTimeMillis();
		quickSort(score, 0, score.size()-1);
		long endQuick = System.currentTimeMillis();
        System.out.println("Quick Sort Execution Time: " + (endQuick - startQuick) + " ms");
        

        //Performing heap sort and calculating its execution time
        long startHeap = System.currentTimeMillis();
		heapSort(newCommons1, commons);
		long endHeap = System.currentTimeMillis();
        System.out.println("Heap Sort Execution Time: " + (endHeap - startHeap) + " ms");
        

        //Performing merge sort and calculating its execution time
        long startMerge = System.currentTimeMillis();
		mergeSort(newCommons2);
		long endMerge = System.currentTimeMillis();
        System.out.println("Merge Sort Execution Time: " + (endMerge - startMerge) + " ms");
        
        
        //Performing insertion sort and calculating its execution time
        long startInsertion = System.currentTimeMillis();
		insertionSort(newCommons3);
		long endInsertion = System.currentTimeMillis();
        System.out.println("Insertion Sort Execution Time: " + (endInsertion - startInsertion) + " ms");
		
        
        //Printing out the sorted list of negative/positive pairs to the "control.txt" file
        fw.write("-----------------------------------------SORTED-----------------------------------------\n");
        for(int i=0; i<commons.get(0).size(); i++) {
        	ArrayList<Integer> pair = new ArrayList<Integer>();
        	pair.add(commons.get(0).get(i));
        	pair.add(commons.get(1).get(i));
        	if(negatives.contains(pair)) {
        		fw.write(commons.get(0).get(i) + " and "+ commons.get(1).get(i) + " have " + commons.get(2).get(i)+" common (Negative Pairs)\n");
        	}
        	if(positives.contains(pair)) {
        		fw.write(commons.get(0).get(i) + " and "+ commons.get(1).get(i) + " have " + commons.get(2).get(i)+" common (Positive Pairs)\n");
        	}
        }
        
        
        //Calculating accuracy and printing out it to console
        //(Checking how many negative pair is included in the first 5592 item of mixed array)
        int count=0;
        for(int i=0; i<commons.get(0).size()/2; i++) {
        	ArrayList<Integer> neg = new ArrayList<Integer>();
        	neg.add(commons.get(0).get(i));
        	neg.add(commons.get(1).get(i));
        	if(negatives.contains(neg)) {
        		count++;
        	}
        }
        float accuracy = (float) count/11184;
        System.out.printf("Accuracy: %d / 11184 (%.4f) (Assumed that 0-5592 is negative, 5592-11184 is positive)\n", 2*count, accuracy);
        
        
        //Closing all scanners and file writer
		readPositive.close();
		userInput.close();
		bigFile.close();
		fw.close();
		
	}

}
