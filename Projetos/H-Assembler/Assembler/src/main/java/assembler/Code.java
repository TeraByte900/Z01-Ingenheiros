/**
 * Curso: Elementos de Sistemas
 * Arquivo: Code.java
 */

package assembler;

/**
 * Traduz mnemÃ´nicos da linguagem assembly para cÃ³digos binÃ¡rios da arquitetura Z0.
 */
public class Code {

    /**
     * Retorna o cÃ³digo binÃ¡rio do(s) registrador(es) que vÃ£o receber o valor da instruÃ§Ã£o.
     * @param  mnemnonic vetor de mnemÃ´nicos "instruÃ§Ã£o" a ser analisada.
     * @return Opcode (String de 4 bits) com cÃ³digo em linguagem de mÃ¡quina para a instruÃ§Ã£o.
     */
    public static String dest(String[] mnemnonic) {
    	String Dest = "0000";
    	
    	if(mnemnonic.length < 2) {
    		return Dest;
    	}  
    	else if(mnemnonic[0].equals("jmp") || mnemnonic[0].equals("je") || mnemnonic[0].equals("jne") || mnemnonic[0].equals("jg") || mnemnonic[0].equals("jge") || mnemnonic[0].equals("jl") || mnemnonic[0].equals("jle") ) {
    		return Dest;
    	}
      	else if (mnemnonic[0].equals("subw")||mnemnonic[0].equals("rsubw")||mnemnonic[0].equals("addw")||mnemnonic[0].equals("andw")||mnemnonic[0].equals("orw")) {
    		if (mnemnonic[3].equals("%A")) {
    		Dest = "1000";
    		}
    		else if (mnemnonic[3].equals("%D")) {
    		Dest = "0010";
    		}
    		else if (mnemnonic[3].equals("%S")) {
    		Dest = "0100";
    		}
    		else if (mnemnonic[3].equals("(%A)")) {
    		Dest = "0001";
    		}
      	}
    	else if (mnemnonic.length == 6) {
    			Dest = "1111";		
    	}
    	else if (mnemnonic.length == 5) {
    		if (mnemnonic[2].equals("%A") && mnemnonic[3].equals("%D")&& mnemnonic[4].equals("%D")) {
    		Dest = "1011";
    		}
    		else if (mnemnonic[2].equals("%A") && mnemnonic[3].equals("%S")&& mnemnonic[4].equals("(%A)")) {
    		Dest = "1101";
    		} 
    		else if (mnemnonic[2].equals("%A") && mnemnonic[3].equals("%S")&& mnemnonic[4].equals("%D")) {
    		Dest = "1110";
    		} 
    		else if (mnemnonic[2].equals("%S") && mnemnonic[3].equals("%D")&& mnemnonic[4].equals("(%A)")) {
    		Dest = "0111";
    		} 
    	}
    	else if (mnemnonic.length == 4) {
    		if (mnemnonic[2].equals("%D") && mnemnonic[3].equals("(%A)"))  {
    		Dest = "0011";
    		}
    		else if (mnemnonic[2].equals("%S") && mnemnonic[3].equals("(%A)")) {
    		Dest = "0101";
    		}
    		else if (mnemnonic[2].equals("%S") && mnemnonic[3].equals("%D")) {
    		Dest = "0110";
    		}
    		else if (mnemnonic[2].equals("%D") && mnemnonic[3].equals("%S")) {
        	Dest = "0110";
        		}
    		else if (mnemnonic[2].equals("%S") && mnemnonic[3].equals("%S")) {
            Dest = "0100";
            		}
    		else if (mnemnonic[2].equals("%A") && mnemnonic[3].equals("(%A)")) {
    		Dest = "1001";
    		}
    		else if (mnemnonic[2].equals("%A") && mnemnonic[3].equals("%D")) {
    		Dest = "1010";
    		}
    		else if (mnemnonic[2].equals("%D") && mnemnonic[3].equals("%A")) {
        	Dest = "1000";
        		}
    		else if (mnemnonic[2].equals("%A") && mnemnonic[3].equals("%S")) {
    		Dest = "1100";
    		}
    		else if (mnemnonic[2].equals("%D") && mnemnonic[3].equals("%D")) {
        	Dest = "0010";
        		}
    		else if (mnemnonic[2].equals("(%A)") && mnemnonic[3].equals("%A")) {
        	Dest = "1000";
        		}
    		else if (mnemnonic[2].equals("(%A)") && mnemnonic[3].equals("%S")) {
        	Dest = "0100";
        		}
    		else if (mnemnonic[2].equals("%A") && mnemnonic[3].equals("%A")) {
        	Dest = "1000";
        		}
    		else if (mnemnonic[2].equals("%D") && mnemnonic[3].equals("%D")) {
        	Dest = "0010";
        		}
    	}
    	else if (mnemnonic.length == 3) {
    		if (mnemnonic[2].equals("%A")) {
    		Dest = "1000";
    		}
    		else if (mnemnonic[2].equals("%D")) {
    		Dest = "0010";
    		}
    		else if (mnemnonic[2].equals("%S")) {
    		Dest = "0100";
    		}
    		else if (mnemnonic[2].equals("(%A)")) {
    		Dest = "0001";
    		}
    	}
      	else if (mnemnonic.length == 2) {
    		if (mnemnonic[1].equals("%A")) {
    		Dest = "1000";
    		}
    		else if (mnemnonic[1].equals("%D")) {
    		Dest = "0010";
    		}
    		else if (mnemnonic[1].equals("%S")) {
    		Dest = "0100";
    		}
    		else if (mnemnonic[1].equals("(%A)")) {
    		Dest = "0001";
    		}
    	
      	}
    	
      	return Dest;
    
    }
    
    /**
     * Retorna o cÃ³digo binÃ¡rio do mnemÃ´nico para realizar uma operaÃ§Ã£o de cÃ¡lculo.
     * @param  mnemnonic vetor de mnemÃ´nicos "instruÃ§Ã£o" a ser analisada.
     * @return Opcode (String de 8 bits) com cÃ³digo em linguagem de mÃ¡quina para a instruÃ§Ã£o.
     */
    public static String comp(String[] mnemnonic) {
    	String Calc = "10101010";
    	
    	if (mnemnonic[0].equals("jmp") && mnemnonic.length == 1){
    		Calc = "00110000";
    	}
    	else if (mnemnonic[0].equals("jmp")|| mnemnonic[0].equals("je") || mnemnonic[0].equals("jne") || mnemnonic[0].equals("jg") || mnemnonic[0].equals("jge") || mnemnonic[0].equals("jl") || mnemnonic[0].equals("jle")) {
    		if (mnemnonic[1].equals("%A")) {
    		Calc = "00110000";
    		}
    	else if (mnemnonic[1].equals("(%A)")) {
    		Calc = "10110111";
    		}
    	else if (mnemnonic[1].equals("%S")) {
    		Calc = "01001100";
    		}
    	else if (mnemnonic[1].equals("%D")) {
    		Calc = "00001100";
    		}
    	}
    	else if (mnemnonic[0].equals("incw")) {
    		if (mnemnonic[1].equals("%A")) {
    			Calc = "00110111";
    		}
    		else if (mnemnonic[1].equals("(%A)")) {
    			Calc = "10110111";
    		}
    		else if (mnemnonic[1].equals("%S")) {
    			Calc = "01011111";
    		}
    		else if (mnemnonic[1].equals("%D")) {
    			Calc = "00011111";
    		}
    	}
    	
    	else if (mnemnonic[0].equals("decw")) {
    		if (mnemnonic[1].equals("%A")) {
    			Calc = "00110010";
    		}
    		else if (mnemnonic[1].equals("(%A)")) {
			Calc = "10110010";
    		}
    		else if (mnemnonic[1].equals("%S")) {
    			Calc = "01001110";
    		}
    		else if (mnemnonic[1].equals("%D")) {
    			Calc = "00001110";
    		}
    }
    	else if (mnemnonic[0].equals("movw")) {
    		if (mnemnonic[1].equals("%A")) {
    			Calc = "00110000";
    		}
    		else if (mnemnonic[1].equals("(%A)")) {
			Calc = "10110000";
    		}
    		else if (mnemnonic[1].equals("%S")) {
    			Calc = "01001100";
    		}
    		else if (mnemnonic[1].equals("%D")) {
    			Calc = "00001100";
    	}
    	return Calc;
    }
    	else if (mnemnonic[0].equals("notw")) {
        	if (mnemnonic[1].equals("%A")) {
        		Calc = "00110001";
        	}
        	else if (mnemnonic[1].equals("(%A)")) {
    		Calc = "10110001";
        	}
       		else if (mnemnonic[1].equals("%S")) {
       			Calc = "01001101";
       		}
       		else if (mnemnonic[1].equals("%D")) {
        			Calc = "00001101";
       	}
    }
       	else if (mnemnonic[0].equals("negw")) {
       		if (mnemnonic[1].equals("%A")) {
       			Calc = "00110011";
       		}
       		else if (mnemnonic[1].equals("(%A)")) {
       			Calc = "10110011";
       		}
       		else if (mnemnonic[1].equals("%S")) {        		
       			Calc = "01001111";
       		}
       		else if (mnemnonic[1].equals("%D")) {
       			Calc = "00001111";
       		}
       	}
       	
       	else if (mnemnonic[0].equals("addw")) {
       		if (mnemnonic[1].equals("%D") && mnemnonic[2].equals("%A")) {
       			Calc = "00000010";
       		}
       		else if (mnemnonic[1].equals("%A") && mnemnonic[2].equals("%D")) {
       			Calc = "00000010";
       		}
       		else if (mnemnonic[1].equals("%S") && mnemnonic[2].equals("%A")) {
       			Calc = "01000010";
       		}
       		else if (mnemnonic[1].equals("%A") && mnemnonic[2].equals("%S")) {
       			Calc = "01000010";
       		}
       		else if (mnemnonic[1].equals("%D") && mnemnonic[2].equals("(%A)")) {
       			Calc = "10000010";
       		}
       		else if (mnemnonic[1].equals("(%A)") && mnemnonic[2].equals("%D")) {
       			Calc = "10000010";
       		}
       		else if (mnemnonic[1].equals("%S") && mnemnonic[2].equals("(%A)")) {
       			Calc = "11000010";
       		}
       		else if (mnemnonic[1].equals("(%A)") && mnemnonic[2].equals("%S")) {
       			Calc = "11000010";
       		}
       	}  	
       		else if (mnemnonic[0].equals("subw")) {
           		if (mnemnonic[1].equals("%D") && mnemnonic[2].equals("%A")) {
           			Calc = "00010011";
           		}
           		else if (mnemnonic[1].equals("%A") && mnemnonic[2].equals("%D")) {
           			Calc = "00000111";
           		}
           		else if (mnemnonic[1].equals("%S") && mnemnonic[2].equals("%A")) {
           			Calc = "01010011";
           		}
           		else if (mnemnonic[1].equals("%A") && mnemnonic[2].equals("%S")) {
           			Calc = "01000111";
           		}
           		else if (mnemnonic[1].equals("%D") && mnemnonic[2].equals("(%A)")) {
           			Calc = "10010011";
           		}
           		else if (mnemnonic[1].equals("(%A)") && mnemnonic[2] .equals("%D")) {
           			Calc = "10000111";
           		}
           		else if (mnemnonic[1].equals("%S") && mnemnonic[2].equals("(%A)")) {
           			Calc = "11010011";
           		}
           		else if (mnemnonic[1].equals("(%A)") && mnemnonic[2].equals("%S")) {
           			Calc = "11000111";
           		}
           		else if (mnemnonic[1].equals("$1") && mnemnonic[2].equals("%A")) {
           			Calc = "00110010";
           		}
           		else if (mnemnonic[1].equals("$1") && mnemnonic[2].equals("(%A)")) {
           			Calc = "10110010";
           		}
           		else if (mnemnonic[1].equals("$1") && mnemnonic[2].equals("%S")) {
           			Calc = "01001110";
           		}
           		else if (mnemnonic[1].equals("$1") && mnemnonic[2].equals("%D")) {
           			Calc = "00001110";
           		}
       	
        		else if (mnemnonic[1].equals("%A") && mnemnonic[2].equals("$1")) {
           			Calc = "00110010";
           		}
           		else if (mnemnonic[1].equals("(%A)") && mnemnonic[2].equals("$1")) {
           			Calc = "10110010";
           		}
           		else if (mnemnonic[1].equals("%S") && mnemnonic[2].equals("$1")) {
           			Calc = "01001110";
           		}
           		else if (mnemnonic[1].equals("%D") && mnemnonic[2].equals("$1")) {
           			Calc = "00001110";
           		}
       		}
       		else if (mnemnonic[0].equals("rsubw")) {
           		if (mnemnonic[1].equals("%A") && mnemnonic[2].equals("%D")) {
           			Calc = "00010011";
           		}
           		else if (mnemnonic[1].equals("%D") && mnemnonic[2].equals("%A")) {
           			Calc = "00000111";
           		}
           		else if (mnemnonic[1].equals("%S") && mnemnonic[2].equals("%A")) {
           			Calc = "01000111";
           		}
           		else if (mnemnonic[1].equals("%A") && mnemnonic[2].equals("%S")) {
           			Calc = "01010011";
           		}
           		else if (mnemnonic[1].equals("%D") && mnemnonic[2].equals("(%A)")) {
           			Calc = "10000111";
           		}
           		else if (mnemnonic[1].equals("(%A)") && mnemnonic[2].equals("%D")) {
           			Calc = "10010011";
           		}
           		else if (mnemnonic[1].equals("%S") && mnemnonic[2].equals("(%A)")) {
           			Calc = "11000111";
           		}
           		else if (mnemnonic[1].equals("(%A)") && mnemnonic[2].equals("%S")) {
           			Calc = "11010011";
           		}
       		
           		else if (mnemnonic[1].equals("%A") && mnemnonic[2].equals("$1")) {
           			Calc = "00110010";
           		}
           		else if (mnemnonic[1].equals("(%A)") && mnemnonic[2].equals("$1")) {
           			Calc = "10110010";
           		}
           		else if (mnemnonic[1].equals("%S") && mnemnonic[2].equals("$1")) {
           			Calc = "01001110";
           		}
           		else if (mnemnonic[1].equals("%D") && mnemnonic[2].equals("$1")) {
           			Calc = "00001110";
           		}
       	
       		}
       		else if (mnemnonic[0].equals("andw")) {
           		if (mnemnonic[1].equals("%D") && mnemnonic[2].equals("%A")) {
           			Calc = "00000000";
           		}
           		else if (mnemnonic[1].equals("%A") && mnemnonic[2].equals("%D")) {
               		Calc = "00000000";
           		}
           		else if (mnemnonic[1].equals("%S") && mnemnonic[2].equals("%A")) {
           			Calc = "01000000";
           		}
               	else if (mnemnonic[1].equals("%A") && mnemnonic[2].equals("%S")) {
               		Calc = "01000000";
               	}
               	else if (mnemnonic[1].equals("%D") && mnemnonic[2].equals("(%A)")) {
               		Calc = "10000000";
               	}
               	else if (mnemnonic[1].equals("(%A)") && mnemnonic[2].equals("%D")) {
               		Calc = "10000000";
               	}
               	else if (mnemnonic[1].equals("%S") && mnemnonic[2].equals("(%A)")) {
               		Calc = "11000000";
               	}
               	else if (mnemnonic[1].equals("(%A)") && mnemnonic[2].equals("%S")) {
               		Calc = "11000000";
               	}
       	}
          	else if (mnemnonic[0].equals("orw")) {
           		if (mnemnonic[1].equals("%D") && mnemnonic[2].equals("%A")) {
           			Calc = "00010101";
           		}
           		else if (mnemnonic[1].equals("%A") && mnemnonic[2].equals("%D")) {
               		Calc = "00010101";
           		}
           		else if (mnemnonic[1].equals("%S") && mnemnonic[2].equals("%A")) {
           			Calc = "01010101";
           		}
               	else if (mnemnonic[1].equals("%A") && mnemnonic[2].equals("%S")) {
               		Calc = "01010101";
               	}
               	else if (mnemnonic[1].equals("%D") && mnemnonic[2].equals("(%A)")) {
               		Calc = "10010101";
               	}
               	else if (mnemnonic[1].equals("(%A)") && mnemnonic[2].equals("%D")) {
               		Calc = "10010101";
               	}
               	else if (mnemnonic[1].equals("%S") && mnemnonic[2].equals("(%A)")) {
               		Calc = "11010101";
               	}
               	else if (mnemnonic[1].equals("(%A)") && mnemnonic[2].equals("%S")) {
               		Calc = "11010101";
               	}
       	}
                        
      return Calc;  
        	
    }

    /**
     * Retorna o cÃ³digo binÃ¡rio do mnemÃ´nico para realizar uma operaÃ§Ã£o de jump (salto).
     * @param  mnemnonic vetor de mnemÃ´nicos "instruÃ§Ã£o" a ser analisada.
     * @return Opcode (String de 3 bits) com cÃ³digo em linguagem de mÃ¡quina para a instruÃ§Ã£o.
     */
    public static String jump(String[] mnemnonic) {
    	String JumpBin = "000";
    	
    	if (mnemnonic[0].equals("jmp")) {
    		JumpBin = "111";
    	}
    	else if (mnemnonic[0].equals("jle")){
    		JumpBin = "110";
    	}
    	else if (mnemnonic[0].equals("jne")){
    		JumpBin = "101";
    	}
    	else if (mnemnonic[0].equals("jl")){
    		JumpBin = "100";
    	}
    	else if (mnemnonic[0].equals("jge")){
    		JumpBin = "011";
    	}
    	else if (mnemnonic[0].equals("je")){
    		JumpBin = "010";
    	}
    	else if (mnemnonic[0].equals("jg")){
    		JumpBin = "001";
    	}
    	else if (mnemnonic[0].equals("jge")){
    		JumpBin = "011";
    	}
    	return JumpBin;
    }
    

    /**
     * Retorna o cÃ³digo binÃ¡rio de um valor decimal armazenado numa String.
     * @param  symbol valor numÃ©rico decimal armazenado em uma String.
     * @return Valor em binÃ¡rio (String de 15 bits) representado com 0s e 1s.
     */
    public static String toBinary(String symbol) {
    	int as;	
    	as = Integer.valueOf(symbol);
    	int d = as;
    		StringBuffer binario = new StringBuffer(); // guarda os dados
    		while (binario.length() < 15) {
    			int b = d % 2;
    			binario.append(b);
    			d = d >> 1; // é a divisão que você deseja
    		}
    		return binario.reverse().toString(); // inverte a ordem e imprime
    	}
    }


