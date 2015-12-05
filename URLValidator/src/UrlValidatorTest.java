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


import org.junit.Assert;
import junit.framework.TestCase;
import java.lang.*;
import java.util.Random;;




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
   // valid urlParts
   String valid_schemes[]={"File", "ftp", "gopher", "http", "https", "ldap", "mailto", "net.pipe", "net.tcp", "news", "nntp", "telnet", "uuid"};
   String valid_hosts[]={"foo.com", "www.example.com"};
   String valid_ports[]={"0.0.0.0:80", ":80",":300",":21"};
   String valid_queries[]={"?p=364&g=389", "?add=cats",""};
   String valid_paths[]={"/path", ""};
   String valid_fragments[]={"#section_2",""};
   //invalid urlParts
   String invalid_schemes[]={};
   String invalid_hosts[]={};
   String invalid_ports[]={};
   String invalid_queries[]={};
   String invalid_paths[]={};
   String invalid_fragments[]={};
   
   
   
  
   public void genTestSet(boolean expected, String schemes[],String hosts[],String ports[], String queries[],String paths[], String fragments[]){
	   
	  
	   
	   int scheme_count=schemes.length;
	   int host_count=hosts.length;
	   int port_count= ports.length;
	   int query_count=queries.length;
	   int path_count=paths.length;
	   int fragment_count= fragments.length;
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   for(int scheme_idx = 0; scheme_idx < scheme_count; scheme_idx++){   
			for(int host_idx = 0; host_idx < host_count; host_idx++){
			   for(int port_idx = 0; port_idx < port_count; port_idx++){
				   for(int query_idx=0; query_idx < query_count; query_idx++){
					   for(int path_idx=0; path_idx < path_count; path_idx++){
						   for(int fragment_idx = 0; fragment_idx < fragment_count; fragment_idx++){
							   String url_string = "";
							   url_string = url_string.concat(schemes[scheme_idx]);
							   url_string = url_string.concat(hosts[host_idx]);
							   url_string = url_string.concat(ports[port_idx]);
							   url_string = url_string.concat(queries[query_idx]);
							   url_string = url_string.concat(paths[path_idx]);
							   url_string = url_string.concat(fragments[fragment_idx]);
							   System.out.println(url_string);
							   Boolean actual = urlVal.isValid(url_string);
							   // System.out.println(expected + " == " + actual);
							   Assert.assertEquals(expected, actual);
							   // System.out.println(urlVal.isValid(url_string));
							   
						   }
					   }
				   }
			   }
			}
	}

   
   }
   
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	  // System.out.println(urlVal.isValid("http://www.amazon.com"));
	  // System.out.println(urlVal.isValid("http://www.amazon.com/path#div"));
	   
	   
	
   }
   
   
   public void testYourFirstPartition()
   {
	   boolean expected = false;
	   genTestSet(expected,valid_schemes,valid_hosts,valid_ports, valid_queries,valid_paths,valid_fragments);
	   
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
