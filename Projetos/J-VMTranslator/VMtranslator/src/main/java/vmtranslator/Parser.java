/**
 * Curso: Elementos de Sistemas
 * Arquivo: Parser.java
 * Created by Luciano Soares <lpsoares@insper.edu.br>
 * Date: 2/05/2017
 *
 * Adaptado por Rafael Corsi <rafael.corsi@insper.edu.br>
 * Date: 5/2018
 */
package vmtranslator;

import java.io.*;
import java.util.Scanner;

/**
 * Encapsula o código de leitura. Carrega as instruções na linguagem de máquina virtual a pilha,
 * analisa, e oferece acesso aos comandos.
 * Além disso, remove todos os espaços em branco e comentários.
 */
public class Parser {
	
	public String line;
	public Scanner scanner;

	
    public String currentCommand = "";  // comando atual
    private BufferedReader fileReader;  // leitor de arquivo

    /** Enumerator para os tipos de comandos de Linguagem de Máquina Virtua a Pilha. */
    public static enum CommandType {
        C_ARITHMETIC,      // comandos aritméticos
        C_PUSH,            // comandos de push //
        C_POP,             // comandos de pop //
        C_LABEL,           // label //
        C_GOTO,            // comando goto //
        C_IF,              // comando if-goto//
        C_FUNCTION,        // declaracao de funcao
        C_RETURN,          // retorno de funcao
        C_CALL             // chamada de funcao
    }

    /**
     * Abre o arquivo de entrada VM e se prepara para analisá-lo.
     * @param file arquivo VM que será feito o parser.
     */
    public Parser(String file) throws FileNotFoundException {
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
     * Carrega um comando e avança seu apontador interno para o próxima
     * linha do arquivo de entrada. Caso não haja mais linhas no arquivo de
     * entrada o método retorna "Falso", senão retorna "Verdadeiro".
     * @return Verdadeiro se ainda há instruções, Falso se as instruções terminaram.
     */
    public Boolean advance() throws IOException {
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
     *  C_PUSH para push, por exemplo push constant 1
     *  C_POP para pop, por exemplo pop constant 2
     * @param  command instrução a ser analisada.
     * @return o tipo da instrução.
     */
    public CommandType commandType(String command) {
    	if (command.startsWith("push")){
    		return CommandType.C_PUSH;
    	}
    	if (command.startsWith("pop")){
    		return CommandType.C_POP;
    	}
    	if (command.startsWith("goto")){
    		return CommandType.C_GOTO;
    	}
    	if (command.startsWith("if-goto")){
    		return CommandType.C_IF;
    	}
    	   
        if (command.startsWith("return")){
    		return CommandType.C_RETURN;
    	}
        if (command.startsWith("function")){
    		return CommandType.C_FUNCTION;
    	}
    	if (command.startsWith("add")||command.startsWith("sub")||command.startsWith("neg")||command.startsWith("eq")
    		||command.startsWith("gt")||command.startsWith("lt")||command.startsWith("and")||command.startsWith("or")
    		||command.startsWith("not")){
    		return CommandType.C_ARITHMETIC;
    	}
    	if (command.startsWith("call")){
    		return CommandType.C_CALL;
    	}
    	else{
    		return CommandType.C_LABEL;
    	}    }


    /**
     * Retorna o primeiro argumento de um comando push ou pop passada no argumento.
     * Se for um comando aritmético, retorna o próprio texto do comando
     * Deve ser chamado somente quando commandType() é diferente de C_RETURN.
     * @param  command instrução a ser analisada.
     * @return somente o símbolo ou o valor número da instrução.
     */
    public String arg1(String command) {
		return command;
    }

    /**
     * Retorna o segundo argumento de um comando push ou pop passada no argumento.
     * Deve ser chamado somente quando commandType() é C_PUSH, C_POP, C_FUNCTION, ou C_CALL.
     * @param  command instrução a ser analisada.
     * @return o símbolo da instrução (sem os dois pontos).
     */
    public Integer arg2(String command) {
		return null;
    }

    // fecha o arquivo de leitura
    public void close() throws IOException {
        fileReader.close();
    }

}
