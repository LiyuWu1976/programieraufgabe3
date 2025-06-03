# datenstructure-Programieraufgabe3
# SimpleSearch – Java Implementation of Basic Search Algorithms

## 👤 Author
**Maryam Taeid**  
**Liyu WU**
**Helma Arjmand**


---

## 📌 Overview

This file contains the implementation of two fundamental search algorithms in Java:

- `linearSearch(int[] a, int x)`
- `binarySearch(int[] a, int x)`
- `interpolationSearch(int[] a, int x)`
- `uadraticBinarySearch(int[] a, int x)`


---

## 🔍 Implemented Methods

### 1. `linearSearch(int[] a, int x)`
- Loops through the array from left to right.
- Returns the index of the first occurrence of `x` or `-1` if not found.
- Does **not** require the array to be sorted.

### 2. `binarySearch(int[] a, int x)`
- Requires a **sorted array**.
- Uses divide-and-conquer to reduce search time.
- Returns the index of `x` or `-1` if not found.

### 3. `interpolationSearch(int[] a, int x)`
- Works best on **sorted, uniformly distributed** arrays.
- Estimates the position using interpolation formula.
- Falls back to `binarySearch` if too many iterations.
- Can be faster than binary search on well-distributed data.

### 4. `quadraticBinarySearch(int[] a, int x)`
- Requires a **sorted** array.
- Divides the range into √n-sized blocks instead of halves.
- Starts from an estimated position and searches recursively in quarters.
- Mixes interpolation and binary-like search for special cases.


---

## 🧪 How to Run

1. Compile the file:
   ```bash
   javac SimpleSearch.java
