public class Permutation {
	void permutation(Character[] str) {
		if (str == null)
			return;

		permutation(str, 0);
	}

	void permutation(Character[] str, int beginLocation) {
		if (beginLocation == str.length){
			System.out.println(str);
		}
		else {
			//每次将第一个元素与后面的所有元素相交换
			for (int i = 0; i < str.length; ++i) {
				swap(str[i], str[beginLocation]);

				permutation(str, beginLocation + 1);

				//迭代返回的时候要再交换一次恢复调用前的顺序
				swap(str[i], str[beginLocation]);
			}

		}
	}
	
	void swap(Object a, Object b){
		Object temp = a;
		a = b;
		b = temp;
	}
}
