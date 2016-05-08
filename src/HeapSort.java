
public class HeapSort {
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int k = N / 2; k >= 1; --k)
			sink(a, k, N);// 从一个子树开始构造有序堆
		while (N > 1) {
			exch(a, 1, --N);
			sink(a, 1, N);// 每次把最小的和最大的交换，再sink成有序堆，按大小顺序取出元素
		}
	}

	// 比子元素小，与较大那个交换位置
	private static void sink(Comparable[] a, int k, int N) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			exch(a, k, j);
			k = j;
		}
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
