/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.testcase;

/**
 *
 * @author ss
 */
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class TestJunit4 extends TestCase  {
   protected double fValue1;
   protected double fValue2;
   
   @Before 
   public void setUp() {
      fValue1 = 2.0;
      fValue2 = 3.0;
      System.out.println("fValue1   : "+fValue1);
      System.out.println("fValue2   : "+fValue2);
   }
	
   @Test
   public void testAdd() {
      //count the number of test cases
      System.out.println("No of Test Case = "+ this.countTestCases());
		
      //test getName 
      String name = this.getName();
      System.out.println("Test Case Name = "+ name);

      //test setName
      this.setName("testNewAdd");
      String newName = this.getName();
      System.out.println("Updated Test Case Name = "+ newName);
   }
	
   //tearDown used to close the connection or clean up activities
   public void tearDown(  ) {
   }
}
