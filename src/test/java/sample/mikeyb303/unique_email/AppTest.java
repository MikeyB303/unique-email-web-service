package sample.mikeyb303.unique_email;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sample.mikeyb303.unique_email.service.UniqueEmailService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {
   
	@Autowired
	private UniqueEmailService emailService;

	@After
	public void tearDown() {
	}
	
	@Test
	public void shouldReturnCorrectNumberOfUniqueEmails() {
		List<String> emailList = new ArrayList<String>();
		emailList.add("test.email@gmail.com");
		emailList.add("test.email+spam@gmail.com");
		emailList.add("testemail@gmail.com");
		final Integer uniqueCount = emailService.uniqueEmailCount(emailList);
		assertThat("Correct count of unique emails is returned", uniqueCount, is(1));
	}
}
