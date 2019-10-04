package sample.mikeyb303.unique_email.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sample.mikeyb303.unique_email.service.UniqueEmailService;

@RestController
public class UniqueEmailController {
	
	private final UniqueEmailService emailService;
	
	@Inject
	public UniqueEmailController(@Named("uniqueEmailService") final UniqueEmailService emailService) {
		this.emailService = emailService;
	}
	
	@PostMapping(value = "/unique-email-count", consumes = "application/json")
	@ResponseBody
	public Integer uniqueEmailCount(@RequestBody List<String> emails) {
		return emailService.uniqueEmailCount(emails);
	}
}