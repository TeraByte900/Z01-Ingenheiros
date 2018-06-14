/**

 * Curso: Elementos de Sistemas
 * Arquivo: Code.java
 * Created by Luciano Soares <lpsoares@insper.edu.br>
 * Date: 2/05/2017
 * Adaptado por Rafael Corsi <rafael.corsi@insper.edu.br>
 * Date: 5/2018
 */

package vmtranslator;

import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 * Traduz da linguagem vm para códigos assembly.
 */
public class Code {

    PrintWriter outputFile = null;  // arquivo .nasm de saída
    String filename = null;         // arquivo .vm de entrada
    int lineCode = 0;               // Linha do codigo vm que gerou as instrucoes

    /**
     * Abre o arquivo de saida e prepara para escrever
     * @param filename nome do arquivo NASM que receberá o código traduzido.
     */
    public Code(String filename) throws FileNotFoundException,IOException {
        File file = new File(filename);
        this.outputFile = new PrintWriter(new FileWriter(file));
    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para executar o comando aritmético.
     * @param  command comando aritmético a ser analisado.
     */
    public void writeArithmetic(String command) {

        if ( command.equals("")) {
            Error.error("Instrução invalida");
        }

        List<String> commands = new ArrayList<String>();

        if(command.equals("add")) {
            commands.add(String.format("; %d - ADD", lineCode++));

           commands.add("leaw $SP, %A");
            commands.add("movw (%A), %A");
            commands.add("decw %A");
            commands.add("movw (%A), %D");
            commands.add("decw %A");
            commands.add("addw %D, (%A), %S");
            commands.add("movw %S, (%A)");
            commands.add("incw %A");
            commands.add("movw %A, %D");
            commands.add("leaw $SP, %A");
            commands.add("movw %D, (%A)");
            System.out.println("Soma realizada");

        } else if (command.equals("sub")) {
            commands.add(String.format("; %d - SUB", lineCode++));
            commands.add("leaw $SP, %A");
            commands.add("movw (%A), %A");
            commands.add("decw %A");
            commands.add("movw (%A), %D");
            commands.add("decw %A");
            commands.add("subw (%A), %D, %S");
            commands.add("movw %S, (%A)");
            commands.add("incw %A");
            commands.add("movw %A, %D");
            commands.add("leaw $SP, %A");
            commands.add("movw %D, (%A)");
            System.out.println("Subtração realizada");

        } else if (command.equals("neg")) {
            commands.add(String.format("; %d - NEG", lineCode++));
            commands.add("leaw $SP, %A");
            commands.add("movw (%A), %A");
            commands.add("decw %A");
            commands.add("movw (%A), %D");
            commands.add("negw %D");
            commands.add("movw %D, (%A)");
            System.out.println("Negação realizada");


        } else if (command.equals("eq")) {
        	String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 3) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
            String Str = salt.toString();
            
            String SALTCHARS1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder salt1 = new StringBuilder();
            Random rnd1 = new Random();
            while (salt1.length() < 4) { // length of the random string.
                int index1 = (int) (rnd1.nextFloat() * SALTCHARS1.length());
                salt1.append(SALTCHARS1.charAt(index1));
            }
            String Str1 = salt1.toString();
        	
        	
        	commands.add(String.format("; %d - EQ", lineCode++));
            commands.add("leaw $SP, %A");
            commands.add("movw (%A), %A");
            commands.add("decw %A");
            commands.add("movw (%A), %D");
            commands.add("decw %A");
            commands.add("movw %A, %S");
            commands.add("leaw $SP, %A");
            commands.add("movw %S, (%A)");
            commands.add("movw (%A), %A");
            commands.add("subw (%A), %D, %S");
            //commands.add("leaw $IGUAL, %A");
            commands.add("leaw $"+Str+", %A");
            commands.add("je %S");
            commands.add("nop");
            commands.add("leaw %SP, %A");
            commands.add("movw (%A), %A");
            commands.add("movw $0, (%A)");
            //commands.add("leaw $FIM, %A");
            commands.add("leaw $"+Str1+", %A");
            commands.add("jmp");
            commands.add("nop");
            //commands.add("IGUAL:");
            commands.add(Str+":");
            commands.add("leaw %SP, %A");
            commands.add("movw (%A), %A");
            commands.add("movw $-1, (%A)");
            //commands.add("FIM:");
            commands.add(Str1+":");
            commands.add("leaw %SP, %A");
            commands.add("movw (%A), %A");
            commands.add("incw %A");
            commands.add("movw %A, %D");
            commands.add("leaw $SP, %A");
            commands.add("movw %D, (%A)");        
                       
            
            System.out.println("ComparaÃ§Ã£o de igualdade realizada");       
                       
            
            System.out.println("Comparação de igualdade realizada");

        } else if (command.equals("gt")) {
            
            String SALTCHARS4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder salt4 = new StringBuilder();
            Random rnd4 = new Random();
            while (salt4.length() < 5) { // length of the random string.
                int index4 = (int) (rnd4.nextFloat() * SALTCHARS4.length());
                salt4.append(SALTCHARS4.charAt(index4));
            }
            String Str4 = salt4.toString();
            
            String SALTCHARS5 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder salt5 = new StringBuilder();
            Random rnd5 = new Random();
            while (salt5.length() < 4) { // length of the random string.
                int index5 = (int) (rnd5.nextFloat() * SALTCHARS5.length());
                salt5.append(SALTCHARS5.charAt(index5));
            }
            String Str5 = salt5.toString();
        	
        	
            commands.add(String.format("; %d - GT", lineCode++));
            commands.add("leaw $SP, %A");
            commands.add("movw (%A), %A");
            commands.add("decw %A");
            commands.add("movw (%A), %D");
            commands.add("decw %A");
            commands.add("movw %A, %S");
            commands.add("leaw $SP, %A");
            commands.add("movw %S, (%A)");
            commands.add("movw (%A), %A");
            commands.add("subw (%A), %D, %S");
            //commands.add("leaw $IGUAL, %A");
            commands.add("leaw $"+Str4+", %A");
            commands.add("jg %S");
            commands.add("nop");
            commands.add("leaw %SP, %A");
            commands.add("movw (%A), %A");
            commands.add("movw $0, (%A)");
            //commands.add("leaw $FIM, %A");
            commands.add("leaw $"+Str5+", %A");
            commands.add("jmp");
            commands.add("nop");
            //commands.add("IGUAL:");
            commands.add(Str4+":");
            commands.add("leaw %SP, %A");
            commands.add("movw (%A), %A");
            commands.add("movw $-1, (%A)");
            //commands.add("FIM:");
            commands.add(Str5+":");
            commands.add("leaw %SP, %A");
            commands.add("movw (%A), %A");
            commands.add("incw %A");
            commands.add("movw %A, %D");
            commands.add("leaw $SP, %A");
            commands.add("movw %D, (%A)");        
                       
            
            System.out.println("ComparaÃ§Ã£o de igualdade realizada");
            System.out.println("Comparação maior menor realizada");

        } else if (command.equals("lt")) {
            
            String SALTCHARS2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder salt2 = new StringBuilder();
            Random rnd2 = new Random();
            while (salt2.length() < 6) { // length of the random string.
                int index2 = (int) (rnd2.nextFloat() * SALTCHARS2.length());
                salt2.append(SALTCHARS2.charAt(index2));
            }
            String Str2 = salt2.toString();
            
            String SALTCHARS3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder salt3 = new StringBuilder();
            Random rnd3 = new Random();
            while (salt3.length() < 7) { // length of the random string.
                int index3 = (int) (rnd3.nextFloat() * SALTCHARS3.length());
                salt3.append(SALTCHARS3.charAt(index3));
            }
            String Str3 = salt3.toString();
        	
        	
            commands.add(String.format("; %d - LT", lineCode++));
            commands.add("leaw $SP, %A");
            commands.add("movw (%A), %A");
            commands.add("decw %A");
            commands.add("movw (%A), %D");
            commands.add("decw %A");
            commands.add("movw %A, %S");
            commands.add("leaw $SP, %A");
            commands.add("movw %S, (%A)");
            commands.add("movw (%A), %A");
            commands.add("subw (%A), %D, %S");
            //commands.add("leaw $IGUAL, %A");
            commands.add("leaw $"+Str2+", %A");
            commands.add("jl %S");
            commands.add("nop");
            commands.add("leaw %SP, %A");
            commands.add("movw (%A), %A");
            commands.add("movw $0, (%A)");
            //commands.add("leaw $FIM, %A");
            commands.add("leaw $"+Str3+", %A");
            commands.add("jmp");
            commands.add("nop");
            //commands.add("IGUAL:");
            commands.add(Str2+":");
            commands.add("leaw %SP, %A");
            commands.add("movw (%A), %A");
            commands.add("movw $-1, (%A)");
            //commands.add("FIM:");
            commands.add(Str3+":");
            commands.add("leaw %SP, %A");
            commands.add("movw (%A), %A");
            commands.add("incw %A");
            commands.add("movw %A, %D");
            commands.add("leaw $SP, %A");
            commands.add("movw %D, (%A)");        
                       
            
            System.out.println("ComparaÃ§Ã£o de igualdade realizada");
            System.out.println("Comparação menor maior realizada");

        } else if (command.equals("and")) {
            commands.add(String.format("; %d - AND", lineCode++));
            commands.add("leaw $SP, %A");
            commands.add("movw (%A), %A");
            commands.add("decw %A");
            commands.add("movw (%A), %D");
            commands.add("decw %A");
            commands.add("andw (%A), %D, %S");
            commands.add("movw %S, (%A)");
            commands.add("incw %A");
            commands.add("movw %A, %D");
            commands.add("leaw $SP, %A");
            commands.add("movw %D, (%A)");
            System.out.println("Comparação AND realizada");

        } else if (command.equals("or")) {
            commands.add(String.format("; %d - OR", lineCode++));
            commands.add("leaw $SP, %A");
            commands.add("movw (%A), %A");
            commands.add("decw %A");
            commands.add("movw (%A), %D");
            commands.add("decw %A");
            commands.add("orw (%A), %D, %S");
            commands.add("movw %S, (%A)");
            commands.add("incw %A");
            commands.add("movw %A, %D");
            commands.add("leaw $SP, %A");
            commands.add("movw %D, (%A)");
            System.out.println("Comparação OR realizada");

        } else if (command.equals("not")) {
            commands.add(String.format("; %d - NOT", lineCode++));
            commands.add("leaw $SP, %A");
            commands.add("movw (%A), %A");
            commands.add("decw %A");
            commands.add("movw (%A), %D");
            commands.add("notw %D");
            commands.add("movw %D, (%A)");
            System.out.println("NOT realizado");


        }

        String[] stringArray = new String[ commands.size() ];
        commands.toArray( stringArray );
        write(stringArray);

    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para executar o comando de Push ou Pop.
     * @param  command comando de push ou pop a ser analisado.
     * @param  segment segmento de memória a ser usado pelo comando.
     * @param  index índice do segkento de memória a ser usado pelo comando.
     */
    public void writePushPop(Parser.CommandType command, String segment, Integer index) {

        if ( command.equals("")) {
            Error.error("Instrução invalida");
        }

        List<String> commands = new ArrayList<String>();

        if(command == Parser.CommandType.C_POP) {
            commands.add(String.format("; %d - POP %s %d", lineCode++ ,segment, index));

            if (segment.equals("constant")) {
                Error.error("Não faz sentido POP com constant");
            } else if (segment.equals("local")) {
                commands.add("leaw $SP, %A");
                commands.add("movw (%A), %A");
                commands.add("decw %A");
                commands.add("movw (%A), %D");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw %S, (%A)");            
                commands.add("leaw $"+index.toString()+", %A");
                commands.add("movw %A, %S");
                commands.add("leaw $LCL, %A");
                commands.add("movw (%A), %A");
                commands.add("addw %S, %A, %A");
                commands.add("movw %D, (%A)");
                
                System.out.println(String.format("Guardando valor em local %d ", index));


            } else if (segment.equals("argument")) {
                commands.add("leaw $SP, %A");
                commands.add("movw (%A), %A");
                commands.add("decw %A");
                commands.add("movw (%A), %D");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw %S, (%A)");            
                commands.add("leaw $"+index.toString()+", %A");
                commands.add("movw %A, %S");
                commands.add("leaw $ARG, %A");
                commands.add("movw (%A), %A");
                commands.add("addw %S, %A, %A");
                commands.add("movw %D, (%A)");
                System.out.println(String.format("Guardando valor em argument %d ", index));


            } else if (segment.equals("this")) {
                commands.add("leaw $SP, %A");
                commands.add("movw (%A), %A");
                commands.add("decw %A");
                commands.add("movw (%A), %D");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw %S, (%A)");            
                commands.add("leaw $"+index.toString()+", %A");
                commands.add("movw %A, %S");
                commands.add("leaw $THIS, %A");
                commands.add("movw (%A), %A");
                commands.add("addw %S, %A, %A");
                commands.add("movw %D, (%A)");
                System.out.println(String.format("Guardando valor em THIS %d ", index));

            } else if (segment.equals("that")) {
                commands.add("leaw $SP, %A");
                commands.add("movw (%A), %A");
                commands.add("decw %A");
                commands.add("movw (%A), %D");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw %S, (%A)");            
                commands.add("leaw $"+index.toString()+", %A");
                commands.add("movw %A, %S");
                commands.add("leaw $THAT, %A");
                commands.add("movw (%A), %A");
                commands.add("addw %S, %A, %A");
                commands.add("movw %D, (%A)");
                System.out.println(String.format("Guardando valor em THAT %d ", index));

            } else if (segment.equals("static")) {
                commands.add("leaw $SP, %A");
                commands.add("movw (%A), %A");
                commands.add("dec %A");
                commands.add("movw (%A), %D");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw %S, (%A)");
                commands.add("leaw $LCL, %A");
                commands.add("movw (%A), %S"); 
              	commands.add("leaw $" + filename+ "-" + index.toString() +" ,%A");
                commands.add("addw %A, %S, %A");
                commands.add("movw %D, (%A)");
                System.out.println("Guardando valor no" + filename+ "-" + index.toString());



            } else if (segment.equals("temp")) {
                commands.add("leaw $SP, %A");
                commands.add("movw (%A), %A");
                commands.add("decw %A");
                commands.add("movw (%A), %D");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw %S, (%A)");            
                commands.add("leaw $"+index.toString()+", %A");
                commands.add("movw %A, %S");
                commands.add("leaw $5, %A");
                commands.add("addw %S, %A, %A");
                commands.add("movw %D, (%A)");


            } else if (segment.equals("pointer")) {
                if(index==0) {
                    commands.add("leaw $SP, %A");
                    commands.add("movw (%A), %A");
                    commands.add("decw %A");
                    commands.add("movw (%A), %D");
                    commands.add("movw %A, %S");
                    commands.add("leaw $SP, %A");
                    commands.add("movw %S, (%A)");            
//                    commands.add("leaw $"+index.toString()+", %A");
//                    commands.add("movw %A, %S");
                    commands.add("leaw $THIS, %A");
//                    commands.add("addw %S, %A, %A");
                    commands.add("movw %D, (%A)");
                    System.out.println("Alterando valor ponteiro do THIS");

                    
                } else {
                    commands.add("leaw $SP, %A");
                    commands.add("movw (%A), %A");
                    commands.add("decw %A");
                    commands.add("movw (%A), %D");
                    commands.add("movw %A, %S");
                    commands.add("leaw $SP, %A");
                    commands.add("movw %S, (%A)");            
                    //commands.add("leaw $"+index.toString()+", %A");
                    //commands.add("movw %A, %S");
                    commands.add("leaw $THAT, %A");
                    //commands.add("addw %S, %A, %A");
                    commands.add("movw %D, (%A)");
                    System.out.println("Alterando valor ponteiro do THAT");

                }
            }
        } else if (command == Parser.CommandType.C_PUSH) {
            commands.add(String.format("; %d - PUSH %s %d", lineCode++ ,segment, index));

            if (segment.equals("constant")) {
                commands.add("leaw $"+index.toString()+", %A");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw (%A), %A");
                commands.add("movw %S, (%A)");
                commands.add("incw %A");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw %S, (%A)");
                System.out.println("Colocando constante na pilha");

                

            } else if (segment.equals("local")) {
                commands.add("leaw $"+index.toString()+", %A");
                commands.add("movw %A, %S");
                commands.add("leaw $LCL, %A");
                commands.add("movw (%A), %A");
                commands.add("addw %S, %A, %A");
                commands.add("movw (%A), %S");
                commands.add("leaw $SP, %A");
                commands.add("movw (%A), %A");
                commands.add("movw %S, (%A)");
                commands.add("incw %A");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw %S, (%A)");
                System.out.println(String.format("Colocando valor do local %d na pilha", index));


            } else if (segment.equals("argument")) {
                commands.add("leaw $"+index.toString()+", %A");
                commands.add("movw %A, %S");
                commands.add("leaw $ARG, %A");
                commands.add("movw (%A), %A");
                commands.add("addw %S, %A, %A");
                commands.add("movw (%A), %S");
                commands.add("leaw $SP, %A");
                commands.add("movw (%A), %A");
                commands.add("movw %S, (%A)");
                commands.add("incw %A");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw %S, (%A)");
                System.out.println(String.format("Colocando argumento %d na pilha", index));


            } else if (segment.equals("this")) {
                commands.add("leaw $"+index.toString()+", %A");
                commands.add("movw %A, %S");
                commands.add("leaw $THIS, %A");
                commands.add("movw (%A), %A");
                commands.add("addw %S, %A, %A");
                commands.add("movw (%A), %S");
                commands.add("leaw $SP, %A");
                commands.add("movw (%A), %A");
                commands.add("movw %S, (%A)");
                commands.add("incw %A");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw %S, (%A)");

            } else if (segment.equals("that")) {
                commands.add("leaw $"+index.toString()+", %A");
                commands.add("movw %A, %S");
                commands.add("leaw $THAT, %A");
                commands.add("movw (%A), %A");
                commands.add("addw %S, %A, %A");
                commands.add("movw (%A), %S");
                commands.add("leaw $SP, %A");
                commands.add("movw (%A), %A");
                commands.add("movw %S, (%A)");
                commands.add("incw %A");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw %S, (%A)");

            } else if (segment.equals("static")) {
                 commands.add("leaw $"+index.toString()+", %A");
                 commands.add("movw %A, %S");
            	 commands.add("leaw $" + filename+ "-" + index.toString() +" ,%A");
                 commands.add("movw (%A), %A");
                 commands.add("addw %S, %A, %A");
                 commands.add("movw (%A), %S");
                 commands.add("leaw $SP, %A");
                 commands.add("movw (%A), %A");
                 commands.add("movw %S, (%A)");
                 commands.add("incw %A");
                 commands.add("movw %A, %S");
                 commands.add("leaw $SP, %A");
                 commands.add("movw %S, (%A)");
                 System.out.println(String.format("Colocando valor static %d na pilha", index));


            } else if (segment.equals("temp")) {
                commands.add("leaw $"+index.toString()+", %A");
                commands.add("movw %A, %S");
                commands.add("leaw $5, %A");
                commands.add("addw %S, %A, %A");
                commands.add("movw (%A), %S");
                commands.add("leaw $SP, %A");
                commands.add("movw (%A), %A");
                commands.add("movw %S, (%A)");
                commands.add("incw %A");
                commands.add("movw %A, %S");
                commands.add("leaw $SP, %A");
                commands.add("movw %S, (%A)");
                System.out.println(String.format("Colocando valor do temp %d na pilha", index));

            } else if (segment.equals("pointer")) {
                if(index==0) {
                    commands.add("leaw $"+index.toString()+", %A");
                    commands.add("movw %A, %S");
                    commands.add("leaw $THIS, %A");
                    commands.add("addw %S, %A, %A");
                    commands.add("movw (%A), %S");
                    commands.add("leaw $SP, %A");
                    commands.add("movw (%A), %A");
                    commands.add("movw %S, (%A)");
                    commands.add("incw %A");
                    commands.add("movw %A, %S");
                    commands.add("leaw $SP, %A");
                    commands.add("movw %S, (%A)");
                    System.out.println("Colocando ponteiro THIS na pilha");


                } else {
                    commands.add("leaw $"+index.toString()+", %A");
                    commands.add("movw %A, %S");
                    commands.add("leaw $THAT, %A");
                    commands.add("addw %S, %A, %A");
                    commands.add("movw (%A), %S");
                    commands.add("leaw $SP, %A");
                    commands.add("movw (%A), %A");
                    commands.add("movw %S, (%A)");
                    commands.add("incw %A");
                    commands.add("movw %A, %S");
                    commands.add("leaw $SP, %A");
                    commands.add("movw %S, (%A)");
                    System.out.println("Colocando ponteiro THAT na pilha");

                }
            }
        }

        String[] stringArray = new String[ commands.size() ];
        commands.toArray( stringArray );
        write(stringArray);

    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para inicializar o processo da VM (bootstrap).
     * Também prepara a chamada para a função Sys.init
     * O código deve ser colocado no início do arquivo de saída.
     */
    public void writeInit(boolean bootstrap, boolean isDir) {

        List<String> commands = new ArrayList<String>();

        if(bootstrap || isDir)
            commands.add( "; Inicialização para VM" );

        if(bootstrap) {
            commands.add("leaw $256,%A");
            commands.add("movw %A,%D");
            commands.add("leaw $SP,%A");
            commands.add("movw %D,(%A)");
        }

        if(isDir){
            commands.add("leaw $Main.main, %A");
            commands.add("jmp");
            commands.add("nop");
        }

        if(bootstrap || isDir) {
            String[] stringArray = new String[commands.size()];
            commands.toArray(stringArray);
            write(stringArray);
        }
    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para gerar o labels (marcadores de jump).
     * @param  label define nome do label (marcador) a ser escrito.
     */
    public void writeLabel(String label) {

        List<String> commands = new ArrayList<String>();
        commands.add( "; Label (marcador)" );
        commands.add(label+":");
        
        String[] stringArray = new String[commands.size()];
        commands.toArray(stringArray);
        write(stringArray);

    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para gerar as instruções de goto (jumps).
     * Realiza um jump incondicional para o label informado.
     * @param  label define jump a ser realizado para um label (marcador).
     */
    public void writeGoto(String label) {

        List<String> commands = new ArrayList<String>();
        commands.add(String.format("; %d - Goto Incondicional", lineCode++));
        
        commands.add("leaw $"+ label +", %A"); // Recebe o Label
        commands.add("jmp"); // jump
        commands.add("nop"); // nop
        
        String[] stringArray = new String[commands.size()];
        commands.toArray(stringArray);
        write(stringArray);
    
    System.out.println(String.format("Goto Incondicional : Pulando para o Label: ", label));
    

}

    /**
     * Grava no arquivo de saida as instruções em Assembly para gerar as instruções de goto condicional (jumps condicionais).
     * Realiza um jump condicional para o label informado.
     * @param  label define jump a ser realizado para um label (marcador).
     */
    public void writeIf(String label) {
    	List<String> commands = new ArrayList<String>();
        commands.add(String.format("; %d - If-Goto Condicional", lineCode++));
    	
        commands.add("leaw $SP, %A");
        commands.add("movw (%A), %A");
        commands.add("decw %A");
        commands.add("movw %A, %S");
        commands.add("movw (%A), %D");
        commands.add("leaw $"+ label +", %A");
        commands.add("jne %D");
        commands.add("nop");
       
        

        

        String[] stringArray = new String[commands.size()];
        commands.toArray(stringArray);
        write(stringArray);
        
        System.out.println(String.format("Goto Condicional : Pulando para o Label: ", label));


     }

    /**
     * Grava no arquivo de saida as instruções em Assembly para uma chamada de função (Call).
     * @param  functionName nome da função a ser "chamada" pelo call.
     * @param  numArgs número de argumentos a serem passados na função call.
     */
    public void writeCall(String functionName, Integer numArgs) {

        List<String> commands = new ArrayList<String>();
        commands.add(String.format("; %d - chamada de funcao %s", lineCode++, functionName));

    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para o retorno de uma sub rotina.
     */
    public void writeReturn() {

        List<String> commands = new ArrayList<String>();
        commands.add(String.format("; %d - Retorno de função", lineCode++));

    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para a declaração de uma função.
     * @param  functionName nome da função a ser criada.
     * @param  numLocals número de argumentos a serem passados na função call.
     */
    public void writeFunction(String functionName, Integer numLocals) {

        List<String> commands = new ArrayList<String>();
        commands.add(String.format("; %d - Declarando função %s", lineCode++, functionName));
        commands.add("funcao_teste:");
        
        String[] stringArray = new String[commands.size()];
        commands.toArray(stringArray);
        write(stringArray);
        
        System.out.println("Write function");
    }

    /**
     * Armazena o nome do arquivo vm de origem.
     * Usado para definir os dados estáticos do código (por arquivo).
     * @param file nome do arquivo sendo tratado.
     */
    public void vmfile(String file) {

        int i = file.lastIndexOf(File.separator);
        int j = file.lastIndexOf('.');
        this.filename = file.substring(i+1,j);

    }

    // grava as instruções em Assembly no arquivo de saída
    public void write(String[] stringArray) {
        // gravando comandos no arquivos
        for (String s: stringArray) {
            this.outputFile.println(s);
        }
    }

    // fecha o arquivo de escrita
    public void close() throws IOException {
        this.outputFile.close();
    }

}
