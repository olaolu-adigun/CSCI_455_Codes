public class AssertTester {

    public static void main (String [ ] args) {
	
        
	System.out.println("Expected: Term[coeff = "+ 2.0 +",expon=" + 3 + "]");
	Term test1 = new Term(2.0 , 3);
	System.out.println("New Polynomial:" + " "+ test1.toString());

	System.out.println(" ");
	System.out.println("Expected: Exception error message because exponent is non-negative");
	Term test2 = new Term(2.0 , -3);
	System.out.println("New Polynomial:" + " "+ test2.toString());
   }	
}
