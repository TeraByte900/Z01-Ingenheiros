

leaw $R1, %A
movw (%A), %D
leaw $R0, %A
movw (%A), %S

leaw $ZERO, %A
je %S
nop
je %D
nop

WHILE:
decw %S

leaw $END, %A
je %S
nop

leaw $R1, %A
addw (%A), %D, %D

leaw $R3, %A
movw %D, (%A) 

leaw $WHILE, %A
jmp
nop

ZERO:
leaw $0, %A
movw %A, %S
leaw $R3, %A
movw %S, (%A)

END:
