package application;

public class UI {

	public static void printInitialMenu() {
		System.out.println("Digite o numero correspondente a opcao desejada:");
		System.out.println(
				"1.Cadastro de conta bancaria\n" 
				+ "2.Deposito\n" 
				+ "3.Retirada\n"
				+ "4.Alteracao de limite\n" 
				+ "5.Transferencias\n" 
				+ "6.Exportacao de historico de transacoes\n"
				+ "7.Sair");
	};

	public static void clearScreen() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}
	
	public static void success() {
		System.out.println("Operacao realizada com sucesso!");
		System.out.println();
	}
	
	public static void fail() {
		System.out.println("A operacao nao foi realizada");
		System.out.println();
	}

}
