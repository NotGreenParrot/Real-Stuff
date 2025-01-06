
import java.util.Comparator;

public class Tester {
	public static class Comp implements Comparator<Integer> {
		public int compare(Integer x, Integer y) {
			if (x < y)
				return -1;
			else if (x > y)
				return 1;
			else return 0;
		}
	}

	public static void main(String[] args) {
		int[ ] elts = {16, 5, 8, 3, 27, 9, 4, 7,5};
		
		MyPriorityQueue<Integer> Q = new MyPriorityQueue<Integer>(new Comp());
		for(int e: elts)
			Q.offer(e);
		while (Q.size() > 0)
			System.out.printf( "%d ", Q.poll());
		System.out.println();

	}

}
