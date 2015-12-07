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
import java.util.Random;




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
   String valid_schemes[]={"File://", "ftp://", "gopher://", "http://", "https://", "ldap://", "mailto://", "net.pipe://", "net.tcp://", "news://", "nntp://", "telnet://", "uuid://"};
   String valid_hosts[]={"foo.com", "www.example.com", "255.255.255.255"};
   String valid_ports[]={":8080", ":80",":300",":21"};
   String valid_queries[]={"?p=364&g=389", "?add=cats","?height=150&width=100"};
   String valid_paths[]={"/path", "","/ispath;variable=V4LUE"};
   String valid_fragments[]={"#section_2",""};
   //invalid urlParts
   String invalid_schemes[]={"http:///", "http://.", "http:// ", "http!://"};
   String invalid_hosts[]={"123.123.123", "269.434.383.375", "356.456.1.17"};
   String invalid_ports[]={":80000000", "::80", ";300", ",21"};
   String invalid_queries[]={"??add=add", "?p1=v1&&p2=v2", "?height=150&==100"};
   String invalid_paths[]={"/word space", " no/slash","/ space/path" };
   String invalid_fragments[]={"#\\?", "##"};
   
   
   
  
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
							   Boolean actual = urlVal.isValid(url_string);
							   System.out.println(url_string+"   Expected:"+ expected + " Actual:" + actual);
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
	   
       //true
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
       
       // this port is false, but should be true
       System.out.println(urlVal.isValid("ftp://ftp.xyz.com:6000"));
       System.out.println(urlVal.isValid("http://www.amazon.com:8080"));
	   
	
   }
   
   
   public void testAllValid()
   {
	   boolean expected = true;
	   System.out.println("		Testing all valid url combinations...");
	   genTestSet(expected,valid_schemes,valid_hosts,valid_ports, valid_queries,valid_paths,valid_fragments);
	   System.out.println("		Finish testing all valid url combinations...");	   
   }
   
   public void testInvalidPaths()
   {
	   System.out.println("		Testing url combinations with invalid path...");
	   boolean expected = false;
	   genTestSet(expected,valid_schemes,valid_hosts,valid_ports, valid_queries,invalid_paths,valid_fragments);
	   System.out.println("		Finish testing url combinations with invalid path...");
   }
   
   
   public void testInvalidFragments()
   {
	   System.out.println("		Testing url combinations with invalid fragments...");
	   boolean expected = false;
	   genTestSet(expected,valid_schemes,valid_hosts,valid_ports, valid_queries,valid_paths,invalid_fragments);
	   System.out.println("		Finish testing url combinations with invalid fragments...");
   }
   
    public void testInvalidPorts()
    {
        System.out.println("		Testing url combinations with invalid ports...");
        boolean expected = false;
        genTestSet(expected,valid_schemes, valid_hosts, valid_ports, valid_queries, valid_paths, valid_fragments);
        System.out.println("		Finish testing url combinations with invalid fragments...");
    }
    
    public void testInvalidQueries()
    {
        System.out.println("		Testing url combinations with invalid queries...");
        boolean expected = false;
        genTestSet(expected,valid_schemes,valid_hosts,valid_ports, valid_queries, valid_paths, valid_fragments);
        System.out.println("		Finish testing url combinations with invalid path...");
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
