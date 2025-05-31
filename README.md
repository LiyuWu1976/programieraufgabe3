# datenstructure-Programieraufgabe3
# SimpleSearch – Java Implementation of Basic Search Algorithms

## 👤 Author
**Maryam Taeid**  


---

## 📌 Overview

This file contains the implementation of two fundamental search algorithms in Java:

- `linearSearch(int[] a, int x)`
- `binarySearch(int[] a, int x)`

Both methods follow the exact specifications provided in the assignment.

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

---

## 🧪 How to Run

1. Compile the file:
   ```bash
   javac SimpleSearch.java
