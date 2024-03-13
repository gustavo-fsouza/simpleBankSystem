package application;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;

import DTO.PostAccountBody;
import DTO.PostPartyBody;
import DTO.PostTransactionBody;
import entities.Account;
import entities.enums.AccountType;
import entities.enums.BankNumbers;
import entities.enums.TransactionType;
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
		AccountService accountService = new AccountService(partyService);
		TransactionService transactionService = new TransactionService(accountService);

		while (keepRunning) {

			UI.printInitialMenu();

			int selectedOption = sc.nextInt();
			

			switch (selectedOption) {
			case 1:
				UI.clearScreen();
				
				try {
					System.out.println("Para o cadastro da conta por favor informe");

					System.out.println("Tipo de conta: (1 para conta de pagamentos, 2 conta poupanca)");
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
							System.out.println("Tipo de conta: (1 para pagamentos, 2 conta poupanca)");
							accountTypeChoice = sc.nextInt();
						}

					}

					sc.nextLine();

					System.out.print("Nome do titular da conta: ");
					String clientName = sc.nextLine();
					
					System.out.print("Numero do CPF (apenas numeros): ");
					String documentNumber = sc.next();
					
					PostPartyBody partyBody = new PostPartyBody(clientName, documentNumber);

					int partyId = partyService.postParty(partyBody);

					System.out.print("Numero da conta: ");
					int accountNumber = sc.nextInt();

					System.out.print("Deposito inicial: ");
					double firstDeposit = sc.nextDouble();
					
					double accountLimit = 0.00;
					boolean hasLimit = false;
					
					if (accountType == AccountType.PAYMENTS) {
						System.out.print("Deseja limite?: (s/n)");
						char s = sc.next().charAt(0);
						
						if (s == 's') {
							System.out.print("Limite da conta: ");
							accountLimit = sc.nextDouble();
							hasLimit = true;
						}
						
					}

					PostAccountBody postAccountBody = new PostAccountBody(accountNumber, accountService.getDefaultBranchNumber(), firstDeposit,
							partyId, accountLimit, accountType, hasLimit);
					
					accountService.postAccount(postAccountBody);
					
					System.out.println();
					System.out.println("numero de conta gerado: " + accountNumber);
					System.out.println("numero de agencia gerado: " + postAccountBody.getBranchNumber());

					UI.success();
					
				} catch (BusinessException e) {
					
					System.out.println(e.getMessage());
					
				}
				

				break;
			case 2:
				UI.clearScreen();

				System.out.print("Informe o numero da conta onde sera feito o deposito: ");
				Integer depositAccountNumber = sc.nextInt();
				
				System.out.print("Informe o numero da agencia onde sera feito o deposito: ");
				Integer depositBranchNumber = sc.nextInt();
				
				String accountAndBranchNumber = depositAccountNumber.toString() + depositBranchNumber.toString();
				

				try {
					
					Account accountToDeposit = accountService.getAccountByAccountAndBranchNumber(accountAndBranchNumber);
					
					System.out.print("Informe o valor a ser depositado: ");
					double depositAmount = sc.nextDouble();

					accountToDeposit.deposit(depositAmount);

					UI.success();
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 3:
				UI.clearScreen();
				
				System.out.print("Informe o numero da conta onde sera feito o saque: ");
				Integer withdrawAccountNumber = sc.nextInt();
				
				System.out.print("Informe o numero da agencia onde sera feito o saque: ");
				Integer withdrawBranchNumber = sc.nextInt();
				
				String wAccountAndBranchNumber = withdrawAccountNumber.toString() + withdrawBranchNumber.toString();


				try {
					Account accountToWithdraw = accountService.getAccountByAccountAndBranchNumber(wAccountAndBranchNumber);
					
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
				
				System.out.print("Informe o numero da conta onde sera feita a alteracao do limite: ");
				Integer limitAccountNumber = sc.nextInt();
				
				System.out.print("Informe o numero da agencia onde sera feita a alteracao do limite: ");
				Integer limitBranchNumber = sc.nextInt();
				
				String lAccountAndBranchNumber = limitAccountNumber.toString() + limitBranchNumber.toString();

				try {
					Account accountToChangeLimit = accountService.getAccountByAccountAndBranchNumber(lAccountAndBranchNumber);
					
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
				
				break;
			case 5:
				UI.clearScreen();

				System.out.print("Informe o numero da conta de onde sera feita a transacao: ");
				Integer trasnferAccountNumber = sc.nextInt();
				
				System.out.print("Informe o numero da agencia de onde sera feita a transacao: ");
				Integer trasnferBranchNumber = sc.nextInt();
				
				String tAccountAndBranchNumber = trasnferAccountNumber.toString() + trasnferBranchNumber.toString();
				

				try {
					Account accountToTransferFrom = accountService.getAccountByAccountAndBranchNumber(tAccountAndBranchNumber);
					
					System.out.print("Saldo atual da conta: ");
					System.out.println(String.format("%.2f", accountToTransferFrom.getAccountBalance()));
					
					System.out.print("Informe o valor a ser transferido: ");
					double transferAmount = sc.nextDouble();
					
					LocalDateTime transactionDateTime = LocalDateTime.now();
					
					System.out.print("Informe o numero da conta destino: ");
					int destinationAccountNumber = sc.nextInt();
					
					System.out.print("Informe o numero da agencia da conta destino: ");
					int destinationBranchNumber	= sc.nextInt();
					
					PostTransactionBody transactionBody = new PostTransactionBody(accountToTransferFrom.getAccountId(), transactionDateTime, transferAmount, destinationAccountNumber, destinationBranchNumber, BankNumbers.DEFAULT_BANK_NUMBER.getBankNumber(), TransactionType.INTERNAL_TRANSFER);
					
					transactionService.postTransaction(transactionBody);
					
					System.out.print("Saldo atual da conta: ");
					System.out.println(String.format("%.2f", accountToTransferFrom.getAccountBalance()));

					UI.success();
					
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				UI.clearScreen();
				
				System.out.print("Informe o numero da conta de onde sera exportado o historico de transferencias: ");
				Integer historyAccountNumber = sc.nextInt();
				
				System.out.print("Informe o numero da agencia de onde sera exportado o historico de transferencias: ");
				Integer historyBranchNumber = sc.nextInt();
				
				String hAccountAndBranchNumber = historyAccountNumber.toString() + historyBranchNumber.toString();


				try {
					
					Account accountToExport = accountService.getAccountByAccountAndBranchNumber(hAccountAndBranchNumber);
					
					transactionService.postExportTransactionHistory(accountToExport.getAccountId());
					
					UI.success();
					
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
					UI.fail();
				} 
				break;
			case 7:
				
				keepRunning = false;
				
				break;
			default:
				UI.clearScreen();
				System.out.println("Selecione uma opcao valida");
			}

		}

		sc.close();

	}

}
