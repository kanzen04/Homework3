package sorting_hat.data;

import java.util.ArrayList;
import java.util.HashMap;
import sorting_hat.ui.SortingHatTile;

/**
 * This factory class builds the sorting algorithm objects to be
 * used for sorting in the game.
 *
 * @author Richard McKenna & Dawa Lama
 */
public class SortingHatAlgorithmFactory
{
    // STORES THE SORTING ALGORITHMS WE MAY WISH TO USE
    static HashMap<SortingHatAlgorithmType, SortingHatAlgorithm> premadeSortingHatAlgorithms = null;

    /**
     * For getting a particular sorting algorithm. Note that the first
     * time it is called it initializes all the sorting algorithms and puts 
     * them in a hash map to be retrieved as needed to setup levels when loaded.
     */
    public static SortingHatAlgorithm buildSortingHatAlgorithm( SortingHatAlgorithmType algorithmType,
                                                                ArrayList<SortingHatTile> initDataToSort)
    {
        // INIT ALL THE ALGORITHMS WE'LL USE IF IT HASN'T DONE SO ALREADY
        if (premadeSortingHatAlgorithms == null)
        {
            premadeSortingHatAlgorithms = new HashMap();
            premadeSortingHatAlgorithms.put(SortingHatAlgorithmType.BUBBLE_SORT,    new BubbleSortAlgorithm(initDataToSort,        algorithmType.toString()));
            premadeSortingHatAlgorithms.put(SortingHatAlgorithmType.SELECTION_SORT,    new SelectionSortAlgorithm(initDataToSort,        algorithmType.toString()));
        }
        // RETURN THE REQUESTED ONE
        return premadeSortingHatAlgorithms.get(algorithmType);
    }
}

/**
 * This class builds all the transactions necessary for performing
 * bubble sort on the data structure. This can then be used to
 * compare to student moves during the game.
 */
class BubbleSortAlgorithm extends SortingHatAlgorithm
{
    /**
     * Constructor only needs to init the inherited stuff.
     */
    public BubbleSortAlgorithm(ArrayList<SortingHatTile> initDataToSort, String initName)
    {
        // INVOKE THE PARENT CONSTRUCTOR
        super(initDataToSort, initName);
    }
    
    /**
     * Build and return all the transactions necessary to sort using bubble sort.
     */
    public ArrayList<SortTransaction> generateSortTransactions()
    {
        // HERE'S THE LIST OF TRANSACTIONS
        ArrayList<SortTransaction> transactions = new ArrayList();
        
        // FIRST LET'S COPY THE DATA TO A TEMPORARY ArrayList
        ArrayList<SortingHatTile> copy = new ArrayList();
        for (int i = 0; i < dataToSort.size(); i++)
            copy.add(dataToSort.get(i));

        // NOW SORT THE TEMPORARY DATA STRUCTURE
        for (int i = copy.size()-1; i > 0; i--)
        {
            for (int j = 0; j < i; j++)
            {
                // TEST j VERSUS j+1
                if (copy.get(j).getID() > copy.get(j+1).getID())
                {
                    // BUILD AND KEEP THE TRANSACTION
                    SortTransaction sT = new SortTransaction(j, j+1);
                    transactions.add(sT);
                    
                    // SWAP
                    SortingHatTile temp = copy.get(j);
                    copy.set(j, copy.get(j+1));
                    copy.set(j+1, temp);
                }
            }
        }
        return transactions;
    }
}
/**
 * This class builds all the transactions necessary for selection sort
 * on the data structure. 
 */

// STILL NEED TO FIX THE ALGORITHM

class SelectionSortAlgorithm extends SortingHatAlgorithm
{
    /**
     * Constructor only needs to init the inherited stuff.
     */
    public SelectionSortAlgorithm(ArrayList<SortingHatTile> initDataToSort, String initName)
    {
        // INVOKE THE PARENT CONSTRUCTOR
        super(initDataToSort, initName);
    }
    
    /**
     * Build and return all the transactions necessary to sort using selection sort.
     */
    public ArrayList<SortTransaction> generateSortTransactions()
    {
        // HERE'S THE LIST OF TRANSACTIONS
        ArrayList<SortTransaction> transactions = new ArrayList();
        
        // FIRST LET'S COPY THE DATA TO A TEMPORARY ArrayList
        ArrayList<SortingHatTile> copy = new ArrayList();
        for (int i = 0; i < dataToSort.size(); i++)
            copy.add(dataToSort.get(i));
        int iMin, n;
        n = copy.size();

        //ADVANCE THE POSITION THROUGH THE ENTIRE ARRAY.
        // NOW SORT THE TEMPORARY DATA STRUCTURE
        for(int j = 0; j < n; j++)
        {
            //assume min is the first element
            iMin = j;
            //test to find the smallest element
            for (int i = j+1; i <= n; i++)
            {
                //if the next element is less than the min, it is the new min
                if(copy.get(i).getID() < copy.get(iMin).getID() ){
                    iMin = i;
                }
                // BUILD AND KEEP THE TRANSACTION
                // Keep track of the elements that have been swapped
                    SortTransaction sT = new SortTransaction(i-1, i);
                    transactions.add(sT);
            }
            //iMin is the index of the minimum element. Swap with the current position.
            if(iMin !=j)
            {
                SortingHatTile temp = copy.get(j);
                    copy.set(j, copy.get(j+1));
                    copy.set(j+1, temp);
            }
        }
        return transactions;
    }
}
