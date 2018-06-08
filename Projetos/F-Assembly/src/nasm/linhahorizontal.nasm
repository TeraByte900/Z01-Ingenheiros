; carrega x"FF" em S (todos pxs em '1')
leaw $0, %A
movw %A, %S
notw %S
leaw $R1, %A
movw %S, (%A)
leaw $18944, %A
movw %A, %S

WHILE:
leaw $R1, %A
movw (%A), %D
movw %S, %A
movw %D, (%A)
incw %S
leaw $19024, %A
subw %A, %S, %D
leaw $WHILE, %A
jg %D
nop

; LEDs
; endereco 21184
leaw $5, %A
movw %A, %S
leaw $21184, %A
movw %S, (%A)
