package com.easybusiness.usermanagement.dao;

import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.list.SmsList;
import com.twilio.type.PhoneNumber;



@Component
public class SendingSms {
	
	public static final String ACCOUNT_SID = "AC1751a7d719a2145e01c603dd50ad4ba4";
	public static final String AUTH_TOKEN = "d751ead8618367e8fc522b72a10ee5cb";
	
	public void send() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message msg = Message.creator(new PhoneNumber("+919732861107"),
										new PhoneNumber("+15005550006"),
											"Hello  from spring boot")
				.create();
		System.out.println(msg.getAccountSid()+" "+msg.getStatus());
		
		/*TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		Account account = client.getAccount();
		SmsList msg = account.getSmsMessages();
		msg.forEach(m->{
			System.out.println("From: "+m.getFrom()+"body "+m.getBody());
		});*/
		
	}
	
}
