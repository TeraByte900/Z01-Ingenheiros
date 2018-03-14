library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Mux2Way is
	port ( 
			a:   in  STD_LOGIC;
			b:   in  STD_LOGIC;
			sel: in  STD_LOGIC;
			q:   out STD_LOGIC);
end entity;

architecture arq of Mux2Way is
begin
process(a,b,sel) is
begin
q <= a when sel = '0';
q <= b when sel = '1';
end process;
end arq;