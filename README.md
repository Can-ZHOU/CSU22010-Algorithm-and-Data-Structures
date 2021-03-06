# CSU22010 Algorithm and Data Structures

## Sorting Algorithms
### Algorithm design
- Brute-force/exhaustive search
  - Selection sort
  - Bubble sort
  - In graphs - DFS, BFS
- Decrease and conquer
  - Establish relationship between a problem and a smaller instance of that problem
  - Insertion sort
  - In graphs - topological sorting
- Divide and conquer
  - Divide a problem into serveral subproblems of the same type, ideally of the same size.
  - Mergesort
  - Quicksort
  - Binary tree traversal - preorder, inorder, postorder
- Transform and conquer
  - Modify a problem to be more amenable to solution, them solve.
  - Balanced search trees
  - Gaussian elimination - solving a system of linear equations.
- Dynamic programming
  - Similar to divide and conquer, but subproblems are joint
  - Efficient solution to Knapsack problem.
  - Warshall’sand Floyd’s shortest path algorithms 
- Greedy
  - Make the choice that looks best at the moment.
  - Graphs: Dijkstra, Prim, Kruskal
  - String: Huffman coding tree
### Performance Analysis
- Cost models
  - Running time
  - Memory cost
- Stability
  - stable if two objects with equal keys appear in the same order in sorted output as they appear in the input array tp be sorted.
- In-place
  - Transforms input without additional auxiliary data structure.
- Scalability
  - Larger time complexity worse scalability
### Algorithm Properties
| Algorithm | Best Case | Average Case | Worst Case | Stable | In-place |
| :---: | :---: | :---: | :---: | :---: | :---: |
| Insertion | O(n)/Array is sorted. | O(n^2) | O(n^2)/Array is sorted in reverse order. | Yes | Yes |
| Bubble | O(n)/Array is sorted. | O(n^2) | O(n^2)/Array is sorted in reverse order. | Yes | Yes |
| Selection | O(n^2) | O(n^2) | O(n^2) | No | Yes |
| Shell | 
| Merge | O(nlogn) | O(nlogn) | O(nlogn) | Yes | No/O(n) |
| Quick | O(nlogn)/when the pivot element divides the list into two equal halves by coming exactly in the middle position. | O(nlogn) | O(n^2)/Array is in ascending or descending order. | No | Yes |

## Substring search
### Brute force

### KMP
- avoid back up by using DFA
- Key point update restart state: X = dfa[pat.charAt(j)][X]

### Boyer-Moore
- Big idea –when find a character not in the pattern, can skip up to M characters (so no need to loop through all N characters)
- Uses backup
- How much to skip?
  - Skip alignments until (a) mismatch becomes a match
  - Sort text moves past mismatched character

### Rabin-Karp - modular hashing
- First R entries:Use Horner'srule.
- Remaining entries:Use rolling hash (and %to avoid overflow)


## String
### Key-Indexed Counting
- Count frequencies of each letter using key as index.
- Computer frequency cumulates which specify destinations.
- Access cumulaters using key as index to move items.
- Copy back into original array.
- Propotion:
  - Time: N+R
  - Space: N+R
  - Stable: Yes

### Radix Sorts
  - LSD [Least Significant Digit first]
    - Strings have same length.
    - From right to left.
    - Propotion:
      - Time: W(N+R)
      - Space: N+R
      - Stable: Yes.
  - MSD [Most Significant Digit first]
    - Partition array into R (radix) pieces according to the first character (most significant digit) using key-indexed counting.
    - Recursively sort all strings that start with each character (key-indexed counts delineate subarraysto sort)
    - Propotion:
      - Time: W(N+R)
      - Space: N+DR
      - Stable: Yes.
### Select sorting algorithms
| Algorithm | Situation |
| :---: | :---: |
| Insertion | Small array, array in (almost) order |
| Quick | General purpose when space is tight |
| Merge | General purpose stable |
| 3-way quick | Larger number of equal keys |
| LSD | Short fixed-length strings |
| MSD | Random Strings|
| 3-way string quicksort | General purpose, string with long pre-fix matches |
    
     
## Tries
### R-way
### TST
