package com.yourname.learningspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class LearningSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringBootApplication.class, args);
	}

	@RestController
	class Resource {

		@RequestMapping(
				method = RequestMethod.GET
		)
		Message getMessage() {
			return new Message("Hello World");
		}
	}

	class Message {
		private final String message;

		public Message(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		@Override
		public String toString() {
			return "Message{" +
					"message='" + message + '\'' +
					'}';
		}
	}
}
