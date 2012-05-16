package com.codenuance.messageboard.model;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class UserTest {

	private static final String MESSAGE3_USER1 = "message3-user1";
	private static final String MESSAGE1_USER2 = "message1-user2";
	private static final String MESSAGE2_USER2 = "message2-user2";
	private static final String MESSAGE3_USER2 = "message3-user2";
	private static final String MESSAGE2_USER1 = "message2-user1";
	private static final String MESSAGE1_USER1 = "message1-user1";

	@Test
	public void shouldCondenseObservedMessages() throws InterruptedException {
		User userPricipal = new User();

		createUserMessageFixture(userPricipal, "user1", MESSAGE1_USER1,
				MESSAGE2_USER1);

		createUserMessageFixture(userPricipal, "user2", MESSAGE1_USER2,
				MESSAGE2_USER2, MESSAGE3_USER2);

		createUserMessageFixture(userPricipal, "user1", MESSAGE3_USER1);

		List<Message> condenseObservedMessages = userPricipal
				.getObservedMessages();
		assertThat(condenseObservedMessages.size(), CoreMatchers.equalTo(6));

		assertThat(condenseObservedMessages.get(0).getMessage(),
				CoreMatchers.equalTo(MESSAGE1_USER1));
		assertThat(condenseObservedMessages.get(1).getMessage(),
				CoreMatchers.equalTo(MESSAGE2_USER1));
		assertThat(condenseObservedMessages.get(2).getMessage(),
				CoreMatchers.equalTo(MESSAGE1_USER2));
		assertThat(condenseObservedMessages.get(3).getMessage(),
				CoreMatchers.equalTo(MESSAGE2_USER2));
		assertThat(condenseObservedMessages.get(4).getMessage(),
				CoreMatchers.equalTo(MESSAGE3_USER2));
		assertThat(condenseObservedMessages.get(5).getMessage(),
				CoreMatchers.equalTo(MESSAGE3_USER1));

	}

	private void createUserMessageFixture(User userPricipal, String username,
			String... messages) throws InterruptedException {

		User userToAddMessage = new User();
		userToAddMessage.setUsername(username);

		for (User observedUser : userPricipal.getObservedUsers()) {
			if (observedUser.getUsername().equals(username)) {
				userToAddMessage = observedUser;
			}
		}

		for (String message : messages) {
			Message messageObj = new Message();
			messageObj.setMessage(message);
			userToAddMessage.getMessages().add(messageObj);
			Thread.sleep(1000);
		}
		userPricipal.getObservedUsers().add(userToAddMessage);
	}
}
