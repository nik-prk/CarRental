package com.nyit.carrental.usermanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.nyit.carrental.usermanagement.exception.UserException;
import com.nyit.carrental.usermanagement.model.UserDetails;

@Component
public class EmailService {
	
	@Value("${server.port:8003}")
	private String serverPort;

	@Autowired
	public JavaMailSender emailSender;

	public String sendSimpleMessage(UserDetails userDetails) throws UserException {
		try {
			List<String> recipients = new ArrayList<>();
			recipients.add(userDetails.getEmail());
			recipients.add("nyit.canada03@gmail.com");
			MimeMessage message = emailSender.createMimeMessage();
			if (recipients != null && !recipients.isEmpty()) {
				String recipientString = convertListToString(recipients);
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientString));
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setSubject("NYIT Car Rental Account Activation");
				helper.setText(generateTextTemplate(userDetails), true);
				emailSender.send(message);
			}
		} catch (Exception e) {
			throw new UserException(HttpStatus.CONFLICT.name(), "Something not working :"+e.getMessage());
		}
		return "Successful";

	}

	private String convertListToString(List<String> recipients) {
		int count = 1;
		StringBuilder stringBuilder = new StringBuilder();
		for (String recipient : recipients) {
			if (count < recipients.size()) {
				stringBuilder.append(recipient + ", ");
			} else {
				stringBuilder.append(recipient);
			}
			count++;
		}
		return stringBuilder.toString();
	}

	private String generateTextTemplate(UserDetails userDetails) {

		StringBuilder myvar = new StringBuilder();
		myvar.append("<html>").append("<body>").append("    <div class=\"es-wrapper-color\">")
				.append("        <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">")
				.append("            <tbody>").append("                <tr>")
				.append("                    <td class=\"esd-email-paddings\" valign=\"top\">")
				.append("                        <table class=\"es-content esd-footer-popover\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\">")
				.append("                            <tbody>").append("                                <tr>")
				.append("                                    <td class=\"esd-stripe\" align=\"center\">")
				.append("                                        <table class=\"es-content-body\" style=\"border-left:1px solid transparent;border-right:1px solid transparent;border-top:1px solid transparent;border-bottom:1px solid transparent;\" align=\"center\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\">")
				.append("                                            <tbody>")
				.append("                                                <tr>")
				.append("                                                    <td class=\"esd-structure es-p20t es-p40b es-p40r es-p40l\" esd-custom-block-id=\"8537\" align=\"left\">")
				.append("                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">")
				.append("                                                            <tbody>")
				.append("                                                                <tr>")
				.append("                                                                    <td class=\"esd-container-frame\" align=\"left\" width=\"518\">")
				.append("                                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">")
				.append("                                                                            <tbody>")
				.append("                                                                                <tr>")
				.append("                                                                                    <td class=\"esd-block-image es-m-txt-c es-p5b\" align=\"center\">")
				.append("                                                                                        <a target=\"_blank\"> <img src=\"https://tlr.stripocdn.email/content/guids/CABINET_3b108160279226d19ea952ffd5948152/images/92241523451206218.png\" alt=\"icon\" style=\"display: block;\" title=\"icon\" width=\"30\"> </a>")
				.append("                                                                                    </td>")
				.append("                                                                                </tr>")
				.append("                                                                                <tr>")
				.append("                                                                                    <td class=\"esd-block-text es-m-txt-c\" align=\"center\">")
				.append("                                                                                        <h2>Hey there!<br></h2>")
				.append("                                                                                    </td>")
				.append("                                                                                </tr>")
				.append("                                                                                <tr>")
				.append("                                                                                    <td class=\"esd-block-text es-m-txt-c es-p15t\" align=\"center\">")
				.append("                                                                                        <p>We received a request to set your email to NYIT Vehical Rental. If this is correct, please confirm by clicking the button below. If you don’t know why you got this email, please tell us straight away so we can fix this for you.</p>")
				.append("                                                                                    </td>")
				.append("                                                                                </tr>")
				.append("                                                                                <tr>")
				.append("                                                                                    <td class=\"esd-block-button es-p20t es-p15b es-p10r es-p10l\" align=\"center\"> <span class=\"es-button-border\"> <a href="+"\""+createUrl(userDetails)+"\""+ " class=\"es-button\" target=\"_blank\">Confirm Email</a> </span> </td>")
				.append("                                                                                </tr>")
				.append("                                                                            </tbody>")
				.append("                                                                        </table>")
				.append("                                                                    </td>")
				.append("                                                                </tr>")
				.append("                                                            </tbody>")
				.append("                                                        </table>")
				.append("                                                    </td>")
				.append("                                                </tr>")
				.append("                                            </tbody>")
				.append("                                        </table>")
				.append("                                    </td>").append("                                </tr>")
				.append("                            </tbody>").append("                        </table>")
				.append("                    </td>").append("                </tr>").append("            </tbody>")
				.append("        </table>").append("    </div>")
				.append("    <div style=\"position: absolute; left: -9999px; top: -9999px; margin: 0px;\"></div>")
				.append("</body>").append("</html>");

		return myvar.toString();
	}
	
	private String createUrl(UserDetails userDetails) {
		return "http://localhost:9001/api/usermanagement/user/account/"+userDetails.getEmail()+"/"+userDetails.getVerifiedCode();
	}
}