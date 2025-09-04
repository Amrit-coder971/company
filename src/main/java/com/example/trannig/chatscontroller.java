package com.example.trannig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class chatscontroller {

	List<Chat> chats = new ArrayList<>();
	 // toDo
	// create a page to list all the user in the system so that
	// clicking a username will open the chat room with that user

	@GetMapping ("/chat/users")
	public String showAlluserPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LoginInfo user = UsefulMethods.getLoggedInUser(request);
		
		// check if user is logged in 
		// if not ,redirect to / login
		if (user == null) {
			response.sendRedirect("/login");
			return "";
		}
		String userList="";
		for (LoginInfo info:UserRegistration.allLoggedInUsers) {
			userList = userList + String.format("<li>%s.%s</li>", info.getFullName(), info.getUsername());
		}
		return "<h3>Available users: </h3>" + "<ul>" + userList + "</ul>";
		
	}
	

	@GetMapping("/chat/room/{receiver}")
	public String showChatRoomPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// ToDO : eliminate the need for providing receiver's username all the time 
		// ToDO : Show chat room with a single user 
		
		//ToDO :use@paathvariable("receiver")to grad receiver's username 
		
		
		// check if user is logged in
		// if not, redirect to /login
		LoginInfo user = UsefulMethods.getLoggedInUser(request);

		if (user == null) {
			response.sendRedirect("/login");
			return "";
		}

		
		
		// create a list of <p> tags containing your received chats;
		String myChats = "";
		for (Chat chat : chats) {
			if (chat.getToUser().equals(user.getUsername())) {
				myChats += "<p>" + chat.getMessage() + " - " + chat.getFromUser() + "</p>";
			}
		}
		

		return "<h1>Chat Message Box</h1>" + myChats + """
				<form action="/chat/send" method="get">
					<input type="text" name="receiver" placeholder="Receiver's username" /> <br>
					<input type="text" name="message" placeholder="Message" /> <br>
					<input type="submit" value="Send" />
				</form>
				""";
	} 

	@GetMapping("/chat/send")
	public String sendChatMessage(@RequestParam("receiver") String receiverUsername,
			@RequestParam("message") String message, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// check if sender is logged in
		// if not redirect to /login
		LoginInfo user = UsefulMethods.getLoggedInUser(request);
		if (user == null) {
			response.sendRedirect("/login");
			return "";
		}

		// Check if receiversUsername actually exists in the system
		// and send error message if it doesn't
		boolean hasFoundReceiver = false;
		for (LoginInfo e : UserRegistration.allLoggedInUsers) {
			if (e.getUsername().equals(receiverUsername)) {
				hasFoundReceiver = true;
				break;
			}
		}
		if (hasFoundReceiver == false) {
			return "Sorry, could not find given receiver's username!";
		}

		// create a Chat object with following information:
		// fromUsername
		// toUsername
		// message
		Chat chat = new Chat(user.getUsername(), receiverUsername, message);

		// append that Chat object to Chat array database
		chats.add(chat);

		// redirect to /chat/room
		response.sendRedirect("/chat/room");

		return "DEBUG: " + receiverUsername + " <- " + message;
	}

}
