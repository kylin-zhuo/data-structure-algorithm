package com.kylin.ds.model;

public class SegmentTree { 
    
	int[] data; 
	int[] segtree;
    int nNumbers;
    int nNodes;

    /** Constructor
     * 
     * @param arr: the input array of numbers
     */
    public SegmentTree (int[] arr) {

		int n = arr.length;

		int h = (int) (Math.ceil(Math.log(n) / Math.log(2))); // height of the segment tree
		int nNodes = 2 * (int) Math.pow(2, h) - 1; // max size of nodes needed
		
		this.nNumbers = n; 
		this.nNodes = nNodes;
		
		data = arr;
		segtree = new int[nNodes];
		construct(arr, 0, n - 1, 0); 
	} 

    /** Recursively construct the segment tree for data[start: end]
     * 
     * @param arr
     * @param start
     * @param end
     * @param pos: the index of the current node in the array data
     * @return
     */
	int construct(int[] arr, int start, int end, int pos) { 

		data = arr;
        
        // edge case with only one element
		if (start == end) { 
			segtree[pos] = arr[start]; 
			return arr[start]; 
		} 

		// For more than one elements, recursively call for left and right subtrees 
		int mid = start + (end - start) / 2;
		segtree[pos] = construct(arr, start, mid, pos * 2 + 1) + construct(arr, mid + 1, end, pos * 2 + 2); 
		return segtree[pos]; 
    } 

    /** Recursive function to get the sum of values in given range.
     * 
     * @param sstart: start index of the segment tree represented by current node
     * @param send: end index of the segment tree represented by current node
     * @param qstart: start index of the query range
     * @param qend: end index of the query range
     * @param pos: index of the current node in the segment tree
     * @return
     */
	int sumUtil(int sstart, int send, int qstart, int qend, int pos) 
	{ 
		// node is a part of given range
		if (qstart <= sstart && qend >= send) {
            return segtree[pos]; 
        }

		// node is outside the given range 
		if (send < qstart || sstart > qend) {
            return 0;
        } 

		// segment overlapped
        int mid = sstart + (send - sstart) / 2;
        
		return sumUtil(sstart, mid, qstart, qend, 2 * pos + 1) + sumUtil(mid + 1, send, qstart, qend, 2 * pos + 2); 
    } 
    
    /** get the sum from qstart to qend
     * 
     * @param n
     * @param qstart
     * @param qend
     * @return
     */
	int sum(int n, int qstart, int qend) 
	{ 
        // invalid input
		if (qstart < 0 || qend > n - 1 || qstart > qend) { 
			return 0; 
		} 
		return sumUtil(0, n - 1, qstart, qend, 0); 
    } 
    
    public int sum(int qstart, int qend) {
        return sum(nNumbers, qstart, qend);
    }

	/** recursive function to update the segment tree nodes. 
	 * 
	 * @param sstart: start index of the segment tree represented by current node
	 * @param send: end index of the segment tree represented by current node
	 * @param i: index of the array element to update
	 * @param diff: the diff to update
	 * @param pos: index of the current node in the segment tree
	 */
	void updateUtil(int sstart, int send, int i, int diff, int pos) 
	{ 
		// outside segment
		if (i < sstart || i > send) 
			return; 

		segtree[pos] += diff; 

		if (sstart < send) { 
			int mid = sstart + (send - sstart) / 2; 
			updateUtil(sstart, mid, i, diff, 2 * pos + 1); 
			updateUtil(mid + 1, send, i, diff, 2 * pos + 2); 
		} 
	} 

	void update(int n, int i, int val) 
	{ 
		// invalid input
		if (i < 0 || i > n - 1) { 
			return; 
		} 

		int diff = val - data[i];
		data[i] = val; 

		// update in segment tree nodes
		updateUtil(0, n - 1, i, diff, 0); 
	}

	public void update(int i, int val) {
		update(this.nNumbers, i, val);
	}

	public int get(int id) {
		if (id < 0 || id > data.length - 1) {
			throw new IndexOutOfBoundsException("index out of bound.");
		}
		return data[id];
	}

	public int[] getData() {
		return data;
	}

	public int[] getSegtree() {
		return segtree;
	}


	public int getnNumbers() {
		return nNumbers;
	}


	public int getnNodes() {
		return nNodes;
	}

}
