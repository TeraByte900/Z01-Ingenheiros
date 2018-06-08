; pontinho
leaw $0, %A
movw %A, %S
notw %S
leaw $R1, %A
movw %S, (%A)
leaw $17354, %A
movw %A, %S

WHILEP:
leaw $R1, %A
movw (%A), %D
movw %S, %A
movw %D, (%A)

leaw $20, %A
movw %S,%D 
addw %D, %A, %S

leaw $17674, %A
subw %A, %S, %D
leaw $WHILEP, %A
jg %D
nop

; corpo do i

leaw $17994, %A
movw %A, %S

WHILEC:
leaw $R1, %A
movw (%A), %D
movw %S, %A
movw %D, (%A)

leaw $20, %A
movw %S, %D 
addw %D, %A ,%S

leaw $19914, %A
subw %A,%S,%D
leaw $WHILEC, %A
jg %D
nop
