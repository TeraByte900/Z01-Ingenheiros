library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Or8Way is
	port ( 
			a:   in  STD_LOGIC;
			b:   in  STD_LOGIC;
			c:   in  STD_LOGIC;
			d:   in  STD_LOGIC;
			e:   in  STD_LOGIC;
			f:   in  STD_LOGIC;
			g:   in  STD_LOGIC;
			h:   in  STD_LOGIC;
			q:   out STD_LOGIC);
			
end entity;

architecture Comp of Or8Way is

	-- Declarations (optional)

begin

	q <= a OR b OR c OR d OR e OR f OR g OR h;

end Comp;