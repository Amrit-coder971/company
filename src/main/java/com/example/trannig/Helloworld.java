//package com.example.trannig;
//
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Random;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class hellowor {
//	int count=0;
//
//	@GetMapping("/hello")
//	public String greet()
//	{
//		return "helloworld";
//	}
//	
//	@GetMapping("/date")
//	public String datetime() {
//	    Date date = new Date();
//	    return " Current time: " + date;
//	}
//
//    @GetMapping("/count")
//    public String pagevisits() {
//    	count ++;
//    	return "page visited" + count + "time";
//    }
//    
////    @GetMapping("/dice")
////	public String dice()
////	{
////		int val=(new Random()).nextInt(6)+1;
////		String roll="";
////		switch(val)
////		{
////		case 1:
////			roll="ONE";
////			break;
////			
////		case 2:
////			roll="	TWO";
////			break;
////			
////		case 3:
////			roll="THREE";
////			break;
////			
////		case 4:
////			roll="FOUR";
////			break;
////			
////		case 5:
////			roll="FIVE";
////			break;
////			
////		case 6:
////			roll="SIX";
////			break;
////			
////		default:
////			roll="UNKNOWN";
////		
////		}
////		return "Your rolled:"+roll+" ("+val+" )";
////	}
////    
//    @GetMapping("/dices")
//    public String dices() {
//    	int val =(new Random()).nextInt(6);
//    	String []roll= {"one","two","three","four","five","six"};
//    	return """ 
//    			<p style="font-size:144px;">
//    			"""+roll[val]+"</p>"+"""
//    					<a href = \"/dices\">Again Roll </a>
//    					""" ;
//    }
//    @GetMapping("/multiples/{limit}")
//    public String multiples(@PathVariable("limit")int limit) {
//    	String result="";
//    	int fontsize=10;
//    	for (int i=7 ;i<=limit;i=i+7) {
//    		result =result +"<p style=\"font-size:"
//    				+fontsize+"\">"+i+"</p>";
//    		fontsize+=2;
//    	}
//    	return result;
//    }
////    @GetMapping("/squre{num}")
////    public String squre(@PathVariable("num")int n) {
////    	return n +"^2=" + n*n;
////    }
//    
//    //multiply 12/15
//    @GetMapping("/multiply/{num}/{num2}")
//    public String squre(@PathVariable("num")int n,@PathVariable("num2")int x) 
//    {
//    	// here n =12,x=15
//    	return n + "X" + x +"="+ n*x;
//    }
//    
//    @GetMapping("/home")
//    public String getHomepage() {
//		return """
//				<a href = "/greet">Greet Page</a><br>
//				<a href = "/dices">Roll Dice</a><br>
//				<a href = "/date">Date</a>
//				""";
//    	
//    }
//    // create getRegisterpage ()method to return this form as string
//    
//    /*
//    @GetMapping("/register")
//    public String getRegister() {
//		return """
//				<form action ="/save" method="get">
//				<label for="fullname"> Fullname:</label>
//				<input type ="text" name="fullname"/><br>
//				 <label for="address">Address:</label>
//				<input type ="text" name="address"/><br>
//				<label for="collage">Collage:</label>
//				<input type ="text" name="collage"/><br>
//				<input type ="submit" value="SUBMIT"/>
//				</form>
//				""";
//   	
//    }
//    
//    // create a saveRegisterpage() method to get data from above form
//    @GetMapping("/save")
//    public String saverRegister(@RequestParam("fullname")String fullname ,
//    		@RequestParam("address") String address,
//    		@RequestParam("collage") String collage) {
//    	
//    	System.out.println("Fullname: " + fullname);
//    	System.out.println("Address: " + address);
//    	System.out.println("collage: " + collage);
//    	return "Form submitted!";
//    }
//    */
//    @GetMapping("/shopping/list")
//    public String getshoppinglistpage() {
//    	return "insert you code";
//    }
//    
//    @GetMapping("/shopping/new")
//    public String getAddshoppinglistpage() {
//    	return """
//    			<form action="/shopping/save" method="get">
//    			<input type ="text" name="item"/>
//    			<input type ="submit" value="SUBMIT"/>
//    			</form>
//    			""";
//    }
//    ArrayList<String>shoppinglist =new ArrayList<>();
//    @GetMapping("/shopping/save")
//    public String saveNewshoppingItem(@RequestParam("item")String item) {
//    	
//    	System.out.print("Item:"+item);
//    	shoppinglist[index++]=item;
//    	return "Item added";
//    }
//    //create a method to show the collected shopping list item
//    // 1)loop throught shopping []
//    //conccatenate shoppingList[i] with result
////    result= r+ shoppinglist[]
////    		return result
//    
//    
//    
////    
////    @GetMapping ("/shopping/list")
////    public String getShowSholppingListPage() {
////    	String result="";
////    	for(int i=0;i<index;i++) {
////    	    result= result + shoppinglist[i];
////    	}
////    	return result;
////    }
////    
////    
////    
////    
//    
//    
////    @GetMapping("/page/{count}")
////    public String getpage(@PathVariable("count")int count){
////    	String lines=;
////    		"Spring Boot is a Java framework that makes it easier to create and run Java applications.",
////    		" It simplifies the configuration and setup process, allowing developers to focus more on writing code for their applications."
////    };
////    Random r= new Random();
////    String out ="";
////    for (int i =1;i<=count;i++) {
////    	int index=r.nextInt(lines.lenght);
////    	out=out + lines[index];
////    	out=out + "";
////    }
////    return out ;
////}
//
//
//}



package com.example.trannig;

import java.util.Date;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Helloworld {

	int count = 0;
	
	@GetMapping("/token/{num}")
	public String getRandomToken(@PathVariable("num") int len) {
		return UsefulMethods.getRandomString(len);
	}

	@GetMapping("/greet")
	public String greet() {
		return "Hello World!";
	}

	@GetMapping("/date")
	public String datetime() {
		Date date = new Date();
		return "Current time: " + date;
	}

	@GetMapping("/count")
	public String pageVisits() {
		count++;
		return "Page visited " + count + " times.";
	}

	@GetMapping("/dice")
	public String dice() {
		int val = (new Random()).nextInt(6);
		String[] roll = { "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX" };

		return """
				<p style="font-size: 144px;">
				""" + roll[val] + "</p>" + """
						<a href="/dice">Roll again!</a>
						""";
	}
	
//	/multiply/12/15
	@GetMapping("/multiply/{num}/{num2}")
	public String square(@PathVariable("num") int n, @PathVariable("num2") int x) {
		// Here n = 12, x = 15
		return n + " X " + x + " = " + n * x;
	}
	
	
	@GetMapping("/homepage")
	public String getHomePage() {
		return """
				<a href="/greet">Greet Page</a> <br>
				<a href="/date">View Date & Time</a> <br>
				<a href="/dice">Roll a dice</a> <br>
				""";
	}
	
	@GetMapping("/para/{count}")
	public String paragraph(@PathVariable("count") int count) {
		String[] lines = {
				"An apple a day keeps the doctor away.",
				"A quick brown fox jumps over the very lazy dog.",
				"Spring Boot servers are popular in finance and health sectors.",
				"We can write our own web servers in C as well.",
				"NVIDIA is the most valuable company in the world surpassing Aramco."
		};
		
		Random r = new Random();
		String out = "";
		for(int i = 1; i <= count; i ++) {
			int index = r.nextInt(lines.length);
			out = out + lines[index];
			out = out + " ";
		}
		
		return out;
		
	}
	
	@GetMapping("/multiples/{limit}")
	public String multiples(@PathVariable("limit") int limit) {
		
		String result = "";
		
		int fontSize = 10;
		for(int i = 7; i <= limit; i = i + 7) {
			result = result + "<p style=\"font-size:" 
					+ fontSize + "\">" + i + "</p>";
			fontSize += 2;
		}
		
		return result;
	}
	
}