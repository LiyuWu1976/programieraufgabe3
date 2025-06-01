package at.fhj.msd;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SearchAlgorithmsTest {

    int[] sorted = {1, 3, 5, 7, 9, 11, 13, 15};
    int[] unsorted = {7, 1, 9, 3, 5, 11, 13, 15}; // for linearSearch only

    // -------- Linear Search --------
    @Test
    void testLinearSearch_found() {
        assertEquals(0, SearchAlgorithms.linearSearch(unsorted, 7));
        assertEquals(1, SearchAlgorithms.linearSearch(unsorted, 1));
        assertEquals(7, SearchAlgorithms.linearSearch(unsorted, 15));
    }

    @Test
    void testLinearSearch_notFound() {
        assertEquals(-1, SearchAlgorithms.linearSearch(unsorted, 100));
    }

    // -------- Binary Search --------
    @Test
    void testBinarySearch_found() {
        assertEquals(2, SearchAlgorithms.binarySearch(sorted, 5));
        assertEquals(0, SearchAlgorithms.binarySearch(sorted, 1));
        assertEquals(7, SearchAlgorithms.binarySearch(sorted, 15));
    }

    @Test
    void testBinarySearch_notFound() {
        assertEquals(-1, SearchAlgorithms.binarySearch(sorted, 8));
    }

    // -------- Interpolation Search --------
    @Test
    void testInterpolationSearch_found() {
        assertEquals(3, SearchAlgorithms.interpolationSearch(sorted, 7));
        assertEquals(0, SearchAlgorithms.interpolationSearch(sorted, 1));
        assertEquals(7, SearchAlgorithms.interpolationSearch(sorted, 15));
    }

    @Test
    void testInterpolationSearch_notFound() {
        assertEquals(-1, SearchAlgorithms.interpolationSearch(sorted, 100));
    }

    @Test
    void testInterpolationSearch_empty() {
        assertEquals(-1, SearchAlgorithms.interpolationSearch(new int[]{}, 5));
    }

    // -------- Quadratic Binary Search --------
    @Test
    void testQuadraticBinarySearch_found() {
        assertEquals(2, SearchAlgorithms.quadraticBinarySearch(sorted, 5));
        assertEquals(0, SearchAlgorithms.quadraticBinarySearch(sorted, 1));
        assertEquals(7, SearchAlgorithms.quadraticBinarySearch(sorted, 15));
    }

    @Test
    void testQuadraticBinarySearch_notFound() {
        assertEquals(-1, SearchAlgorithms.quadraticBinarySearch(sorted, 100));
    }
}

