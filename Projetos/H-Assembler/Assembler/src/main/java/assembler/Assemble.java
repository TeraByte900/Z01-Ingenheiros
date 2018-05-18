/**
 * Curso: Elementos de Sistemas
 * Arquivo: Assemble.java
 * Created by Luciano <lpsoares@insper.edu.br> 
 * Date: 04/02/2017
 *
 * 2018 @ Rafael Corsi
 */

package assembler;

import java.io.*;
import java.util.*;

import assembler.Parser.CommandType;

/**
 * Faz a geração do código gerenciando os demais módulos
 */
public class Assemble {
    private String inputFile;              // arquivo de entrada nasm
    File hackFile = null;                  // arquivo de saída hack
    private PrintWriter outHACK = null;    // grava saida do código de máquina em Hack
    boolean debug;                         // flag que especifica se mensagens de debug são impressas
    private SymbolTable table;             // tabela de símbolos (variáveis e marcadores)

    /**
     * Retorna o código binário do mnemônico para realizar uma operação de cálculo.
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 7 bits) com código em linguagem de máquina para a instrução.
     */

    public Assemble(String inFile, String outFileHack, boolean debug) throws IOException {
        this.debug = debug;
        inputFile  = inFile;
        hackFile   = new File(outFileHack);                      // Cria arquivo de saída .hack
        outHACK    = new PrintWriter(new FileWriter(hackFile));  // Cria saída do print para
                                                                 // o arquivo hackfile
        table      = new SymbolTable();                          // Cria e inicializa a tabela de simbolos
       
        
    }

    /**
     * primeiro passo para a construção da tabela de símbolos de marcadores (labels)
     * varre o código em busca de Símbolos novos Labels e Endereços de memórias
     * e atualiza a tabela de símbolos com os endereços.
     *
     * Dependencia : Parser, SymbolTable
     */
    public void fillSymbolTable() throws FileNotFoundException, IOException {
    	
    	Parser parserFill_labels = new Parser(inputFile);
    	Parser parserFill_symbols = new Parser(inputFile);
    	
    	int numero_de_linhas = 0; // conta numero de linhas que passa a cada iteração
    	int endereco_ROM = 16; // qual o prox endereço ROM a ser armazenado
    	
    	/*
    	 * leaw $1, %A
    	 * loop: 
    	 * leaw $CNT, %A
    	 * leaw $loop, %A
    	 * leaw %CNT2, %A
    	 * jmp 
    	 * nop
    	 * END:
    	 * loop:
    	 */
    	
    	while(parserFill_labels.advance()) { //avança de linha em linha procurando labels novos
    		
    		if(parserFill_labels.commandType(parserFill_labels.command()) == CommandType.L_COMMAND) { //Entra se o comando for label
    			
    			table.addEntry(parserFill_labels.label(parserFill_labels.command()), new Integer(numero_de_linhas)); //adiciona o endereço da label como a linha de baixo
    				
    		}
    		else {
    			numero_de_linhas += 1;
    		}
    	}
    	
    	while(parserFill_symbols.advance()) { //avança de linha em linha buscando instruções novas
    		
    		if(parserFill_symbols.commandType(parserFill_symbols.command()) == CommandType.A_COMMAND) {
    			boolean isNumeric = (parserFill_symbols.symbol(parserFill_symbols.command())).chars().allMatch(Character :: isDigit);
    			if (!isNumeric){
    				 			
    			 if (!table.contains(parserFill_symbols.symbol(parserFill_symbols.command()))) {
    				 
    	    		table.addEntry(parserFill_symbols.label(parserFill_symbols.command()), new Integer(endereco_ROM)); //adiciona o endereço do symbol no endereço da ROM a partir do 16			 
    				endereco_ROM += 1;
    			 }
    			}
    		}
    		
    	}
    }

    /**
     * Segundo passo para a geração do código de máquina
     * Varre o código em busca de instruções do tipo A, C
     * gerando a linguagem de máquina a partir do parse das instruções.
     *
     * Dependencias : Parser, Code
     */
    public void generateMachineCode() throws FileNotFoundException, IOException{
        Parser parser = new Parser(inputFile);  // abre o arquivo e aponta para o começo
        String gMCtext = "";
        while(parser.advance()) { //avança de linha em linha	
        	gMCtext = "";

        	if(parser.commandType(parser.command()) == CommandType.A_COMMAND) { //Entra se o comando for do tipo A
			    boolean isNumeric = (parser.symbol(parser.command())).chars().allMatch(Character :: isDigit);
	    		if (isNumeric){
	        		gMCtext += "0";
	        		gMCtext += Code.toBinary(parser.symbol(parser.command()));
				}
		        else{
					gMCtext += "0";
					gMCtext += Code.toBinary((table.getAddress((parser.symbol(parser.command()))).toString()));
				}
				outHACK.println(gMCtext);
        	}
        	if(parser.commandType(parser.command()) == CommandType.C_COMMAND) { //Entra se o comando for do tipo C
        		gMCtext += "1";
        		gMCtext += Code.comp(parser.instruction(parser.command()));
        		gMCtext += Code.dest(parser.instruction(parser.command()));
        		gMCtext += Code.jump(parser.instruction(parser.command()));
        		outHACK.println(gMCtext);
        		
    		}

		}
        
    }
        

    /**
     * Fecha arquivo de escrita
     */
    public void close() throws IOException {
        if(outHACK!=null) {
            outHACK.close();
        }
    }

    /**
     * Remove o arquivo de .hack se algum erro for encontrado
     */
    public void delete() {
        try{
            if(hackFile!=null) {
                hackFile.delete();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
