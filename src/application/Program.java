package application;

import java.util.Locale;
import java.util.Scanner;

import DTO.PostAccountBody;
import entities.Account;
import entities.enums.AccountType;
import entities.exceptions.BusinessException;
import services.AccountService;
import services.PartyService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		boolean keepRunning = true;
		
		PartyService partyService = new PartyService();
		AccountService accountService = new AccountService();
		
		while(keepRunning) {
			
			UI.printInitialMenu();

			int selectedOption = sc.nextInt();

			switch (selectedOption) {
			case 1:
				UI.clearScreen();
				System.out.println("Para o cadastro da conta por favor informe");
				
				System.out.println("Tipo de conta: (1 para conta de pagamentos, 2 conta poupança)");
				AccountType accountType = AccountType.PAYMENTS;
				
				int accountTypeChoice = sc.nextInt();
				
				boolean selectedAccount = false; 
				
				while (!selectedAccount) {
					
					if (accountTypeChoice == 1) {
						accountType = AccountType.PAYMENTS;
						selectedAccount = true;
					} 
					else if (accountTypeChoice == 2) {
						accountType = AccountType.SAVINGS;
						selectedAccount = true;
					} else {
						System.out.println("Digite um dos valores mostrados na tela");
						System.out.println("Tipo de conta: (1 para pagamentos, 2 conta poupança)");
						accountTypeChoice = sc.nextInt();
					}
					
				} 
				
				
				sc.nextLine();
				
				System.out.print("Nome do titular da conta: ");
				String clientName = sc.nextLine();
				
				
				int partyId = partyService.postParty(clientName);
				
				System.out.print("Numero da conta: ");
				int accountNumber = sc.nextInt();
				
				System.out.print("Numero da agência: ");
				int branchNumber = sc.nextInt();
				
				System.out.print("Deposito inicial: ");
				double firstDeposit = sc.nextDouble();
				
				System.out.print("Limite da conta: ");
				double accountLimit = sc.nextDouble();
				
				
				
				PostAccountBody postAccountBody = new PostAccountBody(accountNumber, branchNumber, firstDeposit, partyId, accountLimit, accountType);
				
				int accountId = accountService.postAccount(postAccountBody);
				
				System.out.println("Id de usuario gerado: " + partyId);
				System.out.println("Id de conta gerado: " + accountId);
				
				UI.success();
				
				break;
			case 2:
				System.out.print("Informe o id da conta onde será feito o deposito:");
				accountId = sc.nextInt();
				
				try {
					Account accountToDeposit = accountService.getAccount(accountId);
					
					System.out.print("Informe o valor a ser depositado: ");
					double depositAmount = sc.nextDouble();
					
					
					
					accountToDeposit.deposit(depositAmount);
					
					UI.success();
				}
				catch (BusinessException e) {
					System.out.println(e.getMessage());
				}
				
				
				
				break;
			case 3:
				// code block
				break;
			case 4:
				// code block
			case 5:
				// code block
				break;
			case 6:
				// code block
				break;
			case 7:
				keepRunning = false;
			default:
				// code block
			}
			
		}
		
		

		sc.close();

	}

}
