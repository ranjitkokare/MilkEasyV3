package com.milkeasy.service;

import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.milkeasy.model.MilkTransaction;
import com.milkeasy.model.User;
import com.milkeasy.repository.UserRepository;

@Service
public class EmailSenderService {
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private UserRepository userRepository;
	
	public void sendSimpleEmail(String toEmail,
            String subject,
            String body
	) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("milkeasyproject@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		mailSender.send(message);
	}
	
	private String constructEmailBody(MilkTransaction milkTransaction, String farmerFullName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder body = new StringBuilder();
        body.append("Hello ").append(farmerFullName).append(",\n\n")
        	.append("Here is the acknowledgment of your recent milk transaction:\n\n")
        	.append("Date: ").append(dateFormat.format(milkTransaction.getCollectionDate())).append("\n");
            
        Optional<User> optionalUser = userRepository.findById(milkTransaction.getCollectorId());
        if (optionalUser.isPresent()) {
            User CollectorUser = optionalUser.get();
            body.append("Collector Name: ").append(CollectorUser.getFullName()).append("\n");          
        } else {
            System.out.println("User with ID " + milkTransaction.getCollectorId() + " not found");
        }
        body.append("Quantity: ").append(milkTransaction.getQuantity()).append("\n")
	        .append("Rate: ").append(milkTransaction.getRate()).append("\n")
	        .append("Amount: ").append(milkTransaction.getAmount()).append("\n")
	        .append("Approval Status: ").append(milkTransaction.getApprovalStatus()).append("\n")
	        .append("\nThank you for your contribution!\n");
        return body.toString();
    }
	
	public void sendMilkTransactionToFarmerAndCollector(MilkTransaction milkTransaction) {
		String farmerFullName = milkTransaction.getFarmerFullName();
		User farmerUser = userRepository.findByFullName(farmerFullName);
        String farmerEmail = farmerUser.getEmail();
        String subject = "Aknowledgement of Milk Transaction";
        String farmerBody = constructEmailBody(milkTransaction, farmerFullName);
        sendSimpleEmail(farmerEmail, subject, farmerBody);            
        
        
        String collectorFullName = milkTransaction.getCollectorFullName();
        String collectorBody = constructEmailBody(milkTransaction, collectorFullName);
        User collectorUser = userRepository.findByFullName(collectorFullName);
        String collectorEmail = collectorUser.getEmail();
        sendSimpleEmail(collectorEmail, subject, collectorBody);  
	}
}
