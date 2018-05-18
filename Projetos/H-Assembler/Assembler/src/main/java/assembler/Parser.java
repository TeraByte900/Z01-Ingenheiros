/**
 * Curso: Elementos de Sistemas
 * Arquivo: Parser.java
 */

package assembler;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
/**
 * Encapsula o código de leitura. Carrega as instruções na linguagem assembly,
 * analisa, e oferece acesso as partes da instrução  (campos e símbolos).
 * Além disso, remove todos os espaços em branco e comentários.
 */
public class Parser {
	
	public Scanner scanner;
	public String line;
	
    /** Enumerator para os tipos de comandos do Assembler. */
    public enum CommandType {
        A_COMMAND,      // comandos LEA, que armazenam no registrador A
        C_COMMAND,      // comandos de calculos
        L_COMMAND       // comandos de Label (símbolos)
    }

    /**
     * Abre o arquivo de entrada NASM e se prepara para analisá-lo.
     * @param file arquivo NASM que será feito o parser.
     */
    public Parser(String file) {
    	
    	this.line = new String();
    	
    	try {
    	    File arquivo = new File(file);
    		scanner = new Scanner(arquivo);
    	}
    	catch (FileNotFoundException e){
    		System.out.println("Arquivo n�o encontrado - Erro");
			e.printStackTrace();
    	}
    }

    /**
     * Carrega uma instrução e avança seu apontador interno para o próxima
     * linha do arquivo de entrada. Caso não haja mais linhas no arquivo de
     * entrada o método retorna "Falso", senão retorna "Verdadeiro".
     * @return Verdadeiro se ainda há instruções, Falso se as instruções terminaram.
     */
    public Boolean advance() {
    	if (scanner.hasNext()){
    		String helper = scanner.nextLine();
    		helper = helper.trim();
    		while(scanner.hasNext() && (helper.equals("") || helper.equals("\n") || helper.startsWith(";"))){
    			helper = scanner.nextLine();
    			helper = helper.trim();
    		}
    		line = helper;
    		line = line.replaceAll("\\s+", " ");
    		if(line.contains(";")){
    			line = line.substring(0, line.indexOf(';'));
    			line = line.trim();
    		}
    		return true;
    	}
    	return false;
    }

    /**
     * Retorna o comando "intrução" atual (sem o avanço)
     * @return a instrução atual para ser analilisada
     */
    public String command() {
    	return this.line;
    }

    /**
     * Retorna o tipo da instrução passada no argumento:
     *  A_COMMAND para leaw, por exemplo leaw $1,%A
     *  L_COMMAND para labels, por exemplo Xyz: , onde Xyz é um símbolo.
     *  C_COMMAND para todos os outros comandos
     * @param  command instrução a ser analisada.
     * @return o tipo da instrução.
     */
    public CommandType commandType(String command) {	
    	if (command.endsWith(":")){
    		return CommandType.L_COMMAND;
    	}
    	if (command.startsWith("leaw")){
    		return CommandType.A_COMMAND;
    	}
    	else{
    		return CommandType.C_COMMAND;
    	}

    }

    /**
     * Retorna o símbolo ou valor numérico da instrução passada no argumento.
     * Deve ser chamado somente quando commandType() é A_COMMAND.
     * @param  command instrução a ser analisada.
     * @return somente o símbolo ou o valor número da instrução.
     */
    // leaw $TESTE, %A

    public String symbol(String command) {
    	String symbol;
    	int cifr;
    	int virg;
    	cifr = command.indexOf("$")+1;
    	virg = command.indexOf(",");
    	symbol = command.substring(cifr, virg);
    	return symbol;
    }
    

    /**
     * Retorna o símbolo da instrução passada no argumento.
     * Deve ser chamado somente quando commandType() é L_COMMAND.
     * @param  command instrução a ser analisada.
     * @return o símbolo da instrução (sem os dois pontos).
     */
    public String label(String command) {
    	String symbol;
		symbol = command.substring(0, command.length()-1);
		return symbol;
    	
    }

    /**
     * Separa os mnemônicos da instrução fornecida em tokens em um vetor de Strings.
     * Deve ser chamado somente quando CommandType () é C_COMMAND.
     * @param  command instrução a ser analisada.
     * @return um vetor de string contento os tokens da instrução (as partes do comando).
     */
    public String[] instruction(String command) {
    	// MOVW %A, %D
    	// JMP %D
		String[] vector;
    	command = command.replace(",", " ");
    	command = command.replaceAll("  ", " ");
		vector = command.split(" ");
		return vector;
    }

}
