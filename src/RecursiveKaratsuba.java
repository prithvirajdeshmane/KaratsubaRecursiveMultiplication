/**
 * @author Prithviraj Deshmane
 * 
 * The following is a java solution for the Karatsuba multiplication technique
 * for determining the product of two very large numbers. 
 * The program recursively breaks down the larger numbers and then 
 * combines the individual results to calculate the final value of the product.
 * This results in the runtime O(n*log(n)) as opposed to O(n^2) 
 * of the traditional long multiplication technique.
 */


public class RecursiveKaratsuba {

	public static void main(String[] args) {
		long x = 82759;
		long y = 9824317;
		
		long result = karatsuba(x, y);
		
		System.out.println(x + " * " + y + " = " + result);
	}
	
	private static long karatsuba (long x, long y) {
		
		if(x == 0 || y == 0) return 0;
		
		if(x < 10 && y < 10) return (x * y);
		
		if (x == 1 || y == 1) {
			if(x == 1) return y;
			return x;
		}
		
		long biggerNumber = Math.max(x, y);
		long smallerNumber = Math.min(x, y);
		
		String bigNum = Long.toString(biggerNumber);
		String smallNum = Long.toString(smallerNumber);
		int bigLen = bigNum.length();
		int smallLen = smallNum.length();
		
		int nBy2 = Math.min(bigLen/2, smallLen);
		int n = 2 * nBy2;
		
		//pad smaller number with zeroes to aid splitting the numbers
		smallNum = padSmallNum(smallNum, bigLen);
		
		long a = Long.parseLong(bigNum.substring(0, bigNum.length()-nBy2));
		long b = Long.parseLong(bigNum.substring(bigNum.length()-nBy2, bigNum.length()));
		long c = Long.parseLong(smallNum.substring(0, smallNum.length()-nBy2));
		long d = Long.parseLong(smallNum.substring(smallNum.length()-nBy2, smallNum.length()));
		
		//Calculating the result
		long firstTerm = (long) (Math.pow(10, n) * karatsuba(a, c));
		long middleTerm = (long) (Math.pow(10, nBy2)) * (karatsuba(a, d) + karatsuba(b, c));
		long thirdTerm = karatsuba(b, d);
		
		return (firstTerm + middleTerm + thirdTerm);
	}
	
	private static String padSmallNum(String num, int targetLength) {
		StringBuilder numStr = new StringBuilder();
		numStr.append(num);
		int n = targetLength - num.length();
		
		for (int i = 0; i < n; i++) {
			numStr = numStr.insert(0, "0");
		}
		return numStr.toString();
	}

}