/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.util.Random;

import junit.framework.TestCase;





/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	   //UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   //System.out.println(urlVal.isValid("http://www.amazon.com"));

	   //Testing the scheme
	   //scheme = ALPHA *( ALPHA / DIGIT / "+" / "-" / "." )

	    String[] my_schemes = {"http","https","ftp","ldap","mailto","net.pipe","net.tcp","news","nntp","telnet","uuid"};
	    UrlValidator urlVal_Sc = new UrlValidator(my_schemes);
	    System.out.println("*****Testing using given scheme list*****");
	    //true
	    System.out.println("Expected: true");
	    System.out.println(urlVal_Sc.isValid("https://foo.bar.com/"));
	    System.out.println(urlVal_Sc.isValid("mailto://foo.bar.com/"));
	    System.out.println(urlVal_Sc.isValid("net.pipe://foo.bar.com/"));
	    System.out.println(urlVal_Sc.isValid("news://foo.bar.com/"));
	    System.out.println(urlVal_Sc.isValid("telnet://foo.bar.com/"));
	    //false
	    System.out.println("Expected: false");
	    System.out.println(urlVal_Sc.isValid("htts://foo.bar.com/"));
	    System.out.println(urlVal_Sc.isValid("ftps://foo.bar.com/"));
	    System.out.println(urlVal_Sc.isValid("hello://foo.bar.com/"));
	    System.out.println(urlVal_Sc.isValid("1234://foo.bar.com/"));
	    System.out.println(urlVal_Sc.isValid("net.oops://foo.bar.com/"));
	    
	    UrlValidator urlVal_Sc2 = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	    System.out.println("*****Testing using scheme definition, where scheme = alpha*(alpha/digit/+/-/.)*****");
	    //true
	    System.out.println("Expected: true");
	    System.out.println(urlVal_Sc2.isValid("h1234://foo.bar.com/"));
	    System.out.println(urlVal_Sc2.isValid("f1+aka://foo.bar.com/"));
	    System.out.println(urlVal_Sc2.isValid("t-10://foo.bar.com/"));
	    System.out.println(urlVal_Sc2.isValid("net.-+://foo.bar.com/"));
	    System.out.println(urlVal_Sc2.isValid("Flop://foo.bar.com/"));
	    //false
	    System.out.println("Expected: false");
	    System.out.println(urlVal_Sc2.isValid("1ders://foo.bar.com/"));
	    System.out.println(urlVal_Sc2.isValid("+me://foo.bar.com/"));
	    System.out.println(urlVal_Sc2.isValid("/1234://foo.bar.com/"));
	    System.out.println(urlVal_Sc2.isValid("!ftp://foo.bar.com/"));
	    System.out.println(urlVal_Sc2.isValid("1llll://foo.bar.com/"));

	    UrlValidator urlVal_Auth2 = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	    System.out.println("*****Testing using authority with hostname*****");
	    //true
	    System.out.println("Expected: true");
	    System.out.println(urlVal_Auth2.isValid("http://userid:password@example.com/"));
	    System.out.println(urlVal_Auth2.isValid("http://userid@example.com/"));
	    System.out.println(urlVal_Auth2.isValid("http://userid@example.com:8080"));
	    System.out.println(urlVal_Auth2.isValid("http://username:password@example.com:123/path/data"));
	    //false
	    //System.out.println("Expected: false");
	    //System.out.println(urlVal_Auth2.isValid("http://userid:password@example.com/"));
	    //System.out.println(urlVal_Auth2.isValid("http://userid:password@example.com/"));
	    //System.out.println(urlVal_Auth2.isValid("http://userid:password@example.com/"));
	    //System.out.println(urlVal_Auth2.isValid("http://userid:password@example.com/"));
	    //System.out.println(urlVal_Auth2.isValid("http://userid:password@example.com/"));
	    
   }
   
   
   public void testYourFirstPartition()
   {
	   
   }
   
   public void testYourSecondPartition(){
	   
   }
   
   
   public void testIsValid()
   {
	   Random randomGenerator = new Random();
	   int randFirst, randSecond, randThird, randFourth;
	   String strIntfirst, strIntsec, strIntthird, strIntfour;
	   UrlValidator urlVal_Auth = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   System.out.println("*****Testing using randomly generated IPs in authority*****");
	   //true
	   System.out.println("Expected: true");
	   for(int i = 0;i<10;i++)
	   {
		     strIntfirst = Integer.toString(randomGenerator.nextInt(255));
		     strIntsec = Integer.toString(randomGenerator.nextInt(255));
		     strIntthird = Integer.toString(randomGenerator.nextInt(255));
		     strIntfour = Integer.toString(randomGenerator.nextInt(255));				     
		     
		     String ipTest = "http://" + strIntfirst + "." + strIntsec + "." + strIntthird + "." + strIntfour;
		     System.out.println(ipTest + " " + urlVal_Auth.isValid(ipTest));
		     //System.out.println(urlVal_Auth.isValid(ipTest));
		     
	   }
	   //false
	   System.out.println("*****IP with values that exceed 255*****");
	   System.out.println("Expected: false");
	   for(int i = 0;i<10;i++)
	   {
		     strIntfirst = Integer.toString(255 + randomGenerator.nextInt(255));
		     strIntsec = Integer.toString(255 + randomGenerator.nextInt(255));
		     strIntthird = Integer.toString(255 + randomGenerator.nextInt(255));
		     strIntfour = Integer.toString(255 + randomGenerator.nextInt(255));				     
		     
		     String ipTest = "http://" + strIntfirst + "." + strIntsec + "." + strIntthird + "." + strIntfour;
		     System.out.println(ipTest + " " + urlVal_Auth.isValid(ipTest));
		     //System.out.println(urlVal_Auth.isValid(ipTest));   
	   }
	   
	   //false
	   System.out.println("*****incorrect IP formatting*****");
	   System.out.println("Expected: false");	   
	     strIntfirst = Integer.toString(randomGenerator.nextInt(255));
	     strIntsec = Integer.toString(randomGenerator.nextInt(255));
	     strIntthird = Integer.toString(randomGenerator.nextInt(255));
	     
	     String ipTest = "http://" + strIntfirst + "." + strIntsec + "." + strIntthird;
	     System.out.println(ipTest + " " + urlVal_Auth.isValid(ipTest));
	     //System.out.println(urlVal_Auth.isValid(ipTest));  
   }
   
   public void testAnyOtherUnitTest()
   {
	   
   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   

}
