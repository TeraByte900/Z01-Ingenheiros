-- Elementos de Sistemas
-- developed by Luciano Soares
-- file: ControlUnit.vhd
-- date: 4/4/2017

-- Unidade que controla os componentes da CPU

library ieee;
use ieee.std_logic_1164.all;

entity ControlUnit is
    port(
		instruction                 : in STD_LOGIC_VECTOR(15 downto 0);  -- instrução para executar
		zr,ng                       : in STD_LOGIC;                      -- valores zr(se zero) e ng(se negativo) da ALU
		muxALUI_A                   : out STD_LOGIC;                     -- mux que seleciona entre instrução e ALU para reg. A
		muxAM_ALU                   : out STD_LOGIC;                     -- mux que seleciona entre reg. A e Mem. RAM para ALU
		muxSD_ALU                   : out STD_LOGIC;                     -- mux que seleciona entre reg. S e reg. D
		zx, nx, zy, ny, f, no       : out STD_LOGIC;                     -- sinais de controle da ALU
		loadA, loadD, loadS, loadM, loadPC : out STD_LOGIC               -- sinais de load do reg. A, reg. D,
                                                                     -- Mem. RAM e Program Counter
    );
end entity;

architecture arch of ControlUnit is

begin

	loadPC <= ((instruction(2) and ng) or 
		(instruction(1) and zr) or 
		(instruction(0) and (not zr) and (not ng))) and 
		instruction(15);

	loadA <= not instruction(15) or instruction(6);
	loadD <= instruction(15) and instruction(4);
	loadS <= instruction(15) and instruction(5);
	loadM <= instruction(15) and instruction(3);

	muxALUI_A <= not instruction(15) ;
	muxAM_ALU <= instruction(15) and instruction(14);
	muxSD_ALU <= not instruction(13) and instruction(15);

	zx <= instruction(12) when instruction(15) = '1';
	nx <= instruction(11) when instruction(15) = '1';
	zy <= instruction(10) when instruction(15) = '1';
	ny <= instruction(9) when instruction(15) = '1';
	f <= instruction(8) when instruction(15) = '1';
	no <= instruction(7) when instruction(15) = '1';





end architecture;
