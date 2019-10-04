package sample.mikeyb303.unique_email.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import sample.mikeyb303.unique_email.exception.InvalidEmailException;

@Service("uniqueEmailService")
public class UniqueEmailServiceImpl implements UniqueEmailService {

	//simple email regex pattern
	private static Pattern EMAIL_PATTERN =  Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
	
	@Override
	public Integer uniqueEmailCount(List<String> emails) {
		//First validate emails, fail fast if not valid
		validateEmailList(emails);
		//Get set of unique meails after stripping away gmail characters
		Set<String> uniqueEmails = removeDuplicateEmails(emails);
		return uniqueEmails.size();
	}
	
	private void validateEmailList(List<String> emails) {
		emails.stream().map(email -> email.toLowerCase()).forEach(this::validate);
	}

	private void validate(String email) {
		Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
		if (!emailMatcher.matches() || !email.split("@")[1].equals("gmail.com")) {
			throw new InvalidEmailException(email);
		}
	}
	
	private Set<String> removeDuplicateEmails(List<String> emails) {
		final List<String> strippedEmails = stripEmails(emails);
		//Put list of stripped emails into a set to remove duplicates
		return new HashSet<String>(strippedEmails);
	}
	
	private List<String> stripEmails(List<String> emails) {
		List<String> strippedEmails = emails.stream()
			.map(email -> stripEmail(email))
			.collect(Collectors.toList());
		
		return strippedEmails;
	}
	
	private String stripEmail(String email) {
		//strips away characters gmail doesn't care about to get true email
		email = email.split("@")[0];
		email = email.split("\\+")[0];
		email = email.replaceAll("\\.", "");
		
		return email;
	}
}