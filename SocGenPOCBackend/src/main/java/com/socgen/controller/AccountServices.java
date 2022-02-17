package com.socgen.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socgen.dto.Account;
import com.socgen.dto.Transfer;
import com.socgen.repository.AccountRepository;
import com.socgen.repository.TransferRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class AccountServices {
	
	@Autowired
	AccountRepository accountRepository;
	
	
	@GetMapping(value = "/addToMyAccount", produces = "application/json")
	public ResponseEntity<?> addToMyAccount(int id, int money) throws Exception {
		
		ResponseEntity<?> response = null;
		
		Account account = null;
		
		try {
			
			account = accountRepository.findById(id).get();
			
			account.setBalance(account.getBalance() + money);
			
			accountRepository.saveAndFlush(account);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		} finally {
			
			if (null == response) {
				response = ResponseEntity.ok(account.getBalance());
			}
			
		}
		
		return response;
		
	}
	
	@GetMapping(value = "/removeFromMyAccount", produces = "application/json")
	public ResponseEntity<?>  removeFromMyAccount(int id, int money) throws Exception {
		
		ResponseEntity<?> response = null;
		
		Account account = null;
		
		try {
			
			account = accountRepository.findById(id).get();
			
			account.setBalance(account.getBalance() - money);
			
			accountRepository.saveAndFlush(account);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		} finally {
			
			if (null == response) {
				response = ResponseEntity.ok(account.getBalance());
			}
			
		}
		
		return response;
		
		
	}

	@GetMapping(value = "/getAccountFromAccountnumber", produces = "application/json")
	public Account getAccountFromAccountnumber( int idaccount) {
		
		return accountRepository.findById(idaccount).get();
		
	}
	
	@RequestMapping(value = "/getAllAccounts",produces = "application/json", method = {RequestMethod.GET, RequestMethod.PUT})
	public ResponseEntity<?> getAllAccounts() {
		
		ResponseEntity<?> response = null;
		
		
		List<Account> accounts = null;
		
		try {
			accounts = accountRepository.findAll();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		} finally {
			
			if (null == response) {
				response = ResponseEntity.ok(accounts);
			}
			
		}
		
		return response;
		
	}
	
	// Trnasfer Serivce
	
	@Autowired
	TransferRepository transferRepository;
	
	@RequestMapping(value = "/getAllTransfers", produces = "application/json", method = {RequestMethod.GET, RequestMethod.PUT})
	public ResponseEntity<?> getAllTransfers() throws Exception {
		
		ResponseEntity<?> response = null;
		
		System.out.println("getAllTransfer");
		
		List<Transfer> transfers = transferRepository.findAll();
		
		try {
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		} finally {
			
			if (null == response) {
				response = ResponseEntity.ok(transfers);
			}
		}
		return response;
		
	}
	
	@RequestMapping(value = "/createTransfer", produces = "application/json", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
	public ResponseEntity<?> createTransfer(@RequestParam()String date, 
													  @RequestParam()String accountTransmitterNumber, 
													  @RequestParam()String accountReceiverNumber,
													  @RequestParam()String amount,
													  @RequestParam()String balance) {
		
		ResponseEntity<?> response = null;
		
		System.out.println("Create Transfer");
		
		try {
		
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
			Date transferDate = sdf.parse(date);
		
			Transfer transfert = new Transfer();
			
			transfert.setDate(transferDate);
			transfert.setAccountTransmitter(accountTransmitterNumber);
			transfert.setAccountReceiver(accountReceiverNumber);
			transfert.setAmount(amount);
			transfert.setMyBalance(Integer.parseInt(balance));
			
			transferRepository.saveAndFlush(transfert);
		
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			
			if (null == response) {
				response = new ResponseEntity<String>("Transfer OK", HttpStatus.ACCEPTED);
			}
		}
		
		return response;
		
	}
	

}
