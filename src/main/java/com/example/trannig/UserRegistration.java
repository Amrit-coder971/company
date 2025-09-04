package com.example.trannig;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserRegistration {

	public static List<LoginInfo> allLoggedInUsers = new ArrayList<>();

	@GetMapping("/")
	public String getHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// check if user has token in their cookies
		LoginInfo info = UsefulMethods.getLoggedInUser(request);
		if (info != null) {
			// user's cookie has SECUREID (which was probably given by the server)
			return "<h2>Hello, " + info.getUsername() + "!</h2>" + """
					<a href="/profile">Profile</a><br>
					<a href="/chat/room">Chat Room</a>

					""" + "<p>" + info.getFullName() + " • " + info.getEmail() + "</p>";
		} else {
			response.sendRedirect("/login");
		}

		return "";
	}

	@GetMapping("/login")
	public String getLoginPage() {
		return """
				<form action="/login/save" method="get">
				<input type="text" placeholder="Username" name="username" />
				<input type="password" placeholder="Password" name="password" />
				<input type="submit" value="LOGIN" />
				</form>""";
	}

	@GetMapping("/profile")
	public String getProfilePage(HttpServletRequest request, HttpServletResponse response) throws IOException {

		LoginInfo info = UsefulMethods.getLoggedInUser(request);

		if (info != null) {
			// user's cookie has SECUREID (which was probably given by the server)
			return "<h2>Hello, " + info.getUsername() + "!</h2>" + """
					<form action="/profile/save" method="get">
					<input type="text" placeholder="Full Name" name="fullName" />
					<input type="text" placeholder="Email" name="email" />
					<input type="submit" value="SAVE" />
					</form>""";
		} else {
			response.sendRedirect("/login");
		}
		return "";
	}

	@GetMapping("/profile/save")
	public String saveProfile(@RequestParam("fullName") String fullName, @RequestParam("email") String email,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		LoginInfo info = UsefulMethods.getLoggedInUser(request);

		if (info != null) {
			// actually save the fullname & email in database
			info.setFullName(fullName);
			info.setEmail(email);

			response.sendRedirect("/");

		} else {
			response.sendRedirect("/login");
		}
		return "";
	}

	@GetMapping("/login/save")
	public String saveLoginPage(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletResponse response) throws IOException {
		// This method generates, saves & provides new token to the user with given
		// username

		// TASK: if user is already in the system, return without creating a new user
		// TASK: ask for password as well to really secure the user

		LoginInfo loginInfo = null;

		// 1. Generate token
		String token = UsefulMethods.getRandomString(20);

		// Check if username already exists in the database
		for (LoginInfo info : allLoggedInUsers) {
			if (info.getUsername().equals(username)) {
				if (info.getPassword().equals(password)) {
					loginInfo = info;
					loginInfo.setToken(token);
					break;
				} else {
					return """
							<p style="color: red;">
							Invalid credentials!</p>""";
				}
			}
		}

		// If username is not already in the database, create new user as follows
		if (loginInfo == null) {
			// 2. Save token to database along with username for a NEW user
			loginInfo = new LoginInfo(username, token);
			loginInfo.setPassword(password);
			allLoggedInUsers.add(loginInfo);
		}

		// 3. send token to user/client/browser
		Cookie cookie = new Cookie("SECUREID", token);
		cookie.setPath("/");
		response.addCookie(cookie);

		response.sendRedirect("/");
		return "";
	}
}