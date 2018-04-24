-- Elementos de Sistemas
-- by Luciano Soares
-- inversor16.vhd

library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity inversor16 is
   port(z   : in STD_LOGIC;
	a   : in STD_LOGIC_VECTOR(15 downto 0);
        y   : out STD_LOGIC_VECTOR(15 downto 0)
   );
end entity;

architecture rtl of inversor16 is
begin
process(a, z) is
begin
	if (z = '0') then y <= a;
	else y <= not a;
	end if;
end process;
end architecture;
