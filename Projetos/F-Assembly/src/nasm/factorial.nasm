VERZERO:
leaw $R0, %A
movw (%A), %S
incw %D
leaw $R1, %A
movw %D, (%A)
leaw $FINAL, %A
je %S
nop
decw %D
leaw $R1, %A
movw %D, (%A)
leaw $R0, %A
movw (%A), %S
leaw $R0, %A
movw (%A), %D
VOLTA:
decw %D
leaw $R3, %A
movw %D, (%A)
WHILE:
leaw $R1, %A
addw (%A), %S, %S
decw %D
leaw $WHILE, %A
jg %D
nop 
leaw $R1, %A
movw %S, (%A)
leaw $R1, %A
movw (%A), %S
leaw $R3, %A
movw (%A), %D
CHECAR:
decw %D
leaw $FINAL, %A
je %D
nop
leaw $R3, %A
movw (%A), %D
leaw $VOLTA, %A
jg %D
nop
FINAL:




















