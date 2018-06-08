; carrega x"FF" em S (todos pxs em '1')
leaw $0, %A
movw %A, %S
notw %S
leaw $R1, %A
movw %S, (%A)
leaw $18954, %A
movw %A, %S

WHILE:
leaw $R1, %A
movw (%A), %D
movw %S, %A
movw %D, (%A)

leaw $20, %A
movw %S,%D 
addw %D, %A ,%S

leaw $19274, %A
subw %A, %S, %D
leaw $WHILE, %A
jg %D
nop


