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


import junit.framework.TestCase;





/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest_Anne extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest_Anne(String testName) {
      super(testName);
   }


   
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	  
	   //true
	   System.out.println(urlVal.isValid("http://www.amazon.com"));
	   System.out.println(urlVal.isValid("http://www.amazon.com:80"));
	   System.out.println(urlVal.isValid("http://0.0.0.0:80")); 
	   System.out.println(urlVal.isValid("ftp://ftp.xyz.com:300"));
	   System.out.println(urlVal.isValid("ftp://ftp.xyz.com:21"));
	  
	   
	   //false
	   System.out.println(urlVal.isValid("ftp://:21"));
	   System.out.println(urlVal.isValid("http://:80")); 
	   System.out.println(urlVal.isValid("http://amazon.com::80"));
	   
	   //these queries are false, but should be true
	   System.out.println(urlVal.isValid("https://www.google.com/?id=200"));
	   System.out.println(urlVal.isValid("http://www.superman.com?p1=v1&p2=v2"));
	   System.out.println(urlVal.isValid("http://www.foo.bar/image.jpg?height=150&width=100"));
	   
	   //this port is false, but should be true
	   System.out.println(urlVal.isValid("ftp://ftp.xyz.com:6000"));
	   System.out.println(urlVal.isValid("http://www.amazon.com:8080"));
	   
   }
   
   
   public void testYourFirstPartition()
   {
	   
   }
   
   public void testYourSecondPartition(){
	   
   }
   
   
   public void testIsValid()
   {
	   for(int i = 0;i<10000;i++)
	   {
		   
	   }
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
