package application;

import java.util.Locale;
import java.util.Scanner;

import DTO.PostAccountBody;
import entities.Account;
import entities.enums.AccountType;
import entities.exceptions.BusinessException;
import services.AccountService;
import services.PartyService;
import services.TransactionService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		boolean keepRunning = true;

		PartyService partyService = new PartyService();
		AccountService accountService = new AccountService();
		TransactionService transactionService = new TransactionService();

		while (keepRunning) {

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
					} else if (accountTypeChoice == 2) {
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
				
				double accountLimit = 0.00;
				boolean hasLimit = false;
				
				if (accountType == AccountType.PAYMENTS) {
					System.out.print("Limite da conta: ");
					accountLimit = sc.nextDouble();
					hasLimit = true;
				}

				PostAccountBody postAccountBody = new PostAccountBody(accountNumber, branchNumber, firstDeposit,
						partyId, accountLimit, accountType, hasLimit);

				int accountId = accountService.postAccount(postAccountBody);

				System.out.println("Id de usuario gerado: " + partyId);
				System.out.println("Id de conta gerado: " + accountId);

				UI.success();

				break;
			case 2:
				UI.clearScreen();

				System.out.print("Informe o id da conta onde será feito o deposito: ");
				accountId = sc.nextInt();

				try {
					Account accountToDeposit = accountService.getAccount(accountId);
					
					System.out.print("Saldo atual: ");
					System.out.println(String.format("%.2f", accountToDeposit.getAccountBalance()));
					
					System.out.print("Informe o valor a ser depositado: ");
					double depositAmount = sc.nextDouble();

					accountToDeposit.deposit(depositAmount);
					
					System.out.print("Novo saldo: ");
					System.out.println(String.format("%.2f", accountToDeposit.getAccountBalance()));

					UI.success();
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 3:
				UI.clearScreen();

				System.out.print("Informe o id da conta de onde será feito o saque: ");
				accountId = sc.nextInt();

				try {
					Account accountToWithdraw = accountService.getAccount(accountId);
					
					System.out.print("Saldo atual: ");
					System.out.println(String.format("%.2f", accountToWithdraw.getAccountBalance()));

					System.out.print("Informe o valor a ser sacado: ");
					double withdrawAmount = sc.nextDouble();

					accountToWithdraw.withdraw(withdrawAmount);
					System.out.print("Novo saldo: ");
					System.out.println(String.format("%.2f", accountToWithdraw.getAccountBalance()));

					UI.success();
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 4:

				UI.clearScreen();

				System.out.print("Informe o id da conta de onde será feita a alteração do limite: ");
				accountId = sc.nextInt();

				try {
					Account accountToChangeLimit = accountService.getAccount(accountId);
					
					System.out.print("Limite atual da conta: ");
					System.out.println(String.format("%.2f", accountToChangeLimit.getAccountLimit()));
					
					System.out.print("Informe o valor do novo limite: ");
					double newLimit = sc.nextDouble();
					
					accountToChangeLimit.changeLimit(newLimit);
					
					System.out.print("Novo limite da conta: ");
					System.out.println(String.format("%.2f", accountToChangeLimit.getAccountLimit()));

					UI.success();
					
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}

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
