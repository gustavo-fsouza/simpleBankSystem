package application;

public class UI {

	public static void printInitialMenu() {
		System.out.println("Digite o número correspondente a opção desejada:");
		System.out.println(
				"1.Cadastro de conta bancária\n" 
				+ "2.Depósito\n" 
				+ "3.Retirada\n"
				+ "4.Alteração de limite\n" 
				+ "5.Transferências\n" 
				+ "6.Exportação de historico de transações\n"
				+ "7.Sair");
	};

	public static void clearScreen() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}
	
	public static void success() {
		System.out.println("Operação realizada com sucesso!");
		System.out.println();
	}

}
