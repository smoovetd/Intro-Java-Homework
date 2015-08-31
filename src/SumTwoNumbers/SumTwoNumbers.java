public class SumTwoNumbers {

	public static void main(String[] args) {
		int firstNumber = Integer.parseInt(args[0]);
		int secondNumber = Integer.parseInt(args[1]);
		int sum = firstNumber + secondNumber;
		
		System.out.println(String.format("Sum of %d and %d is %d", firstNumber,secondNumber,sum));
	}

}
