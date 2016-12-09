.data

msg_02:	.word 5
	.ascii "%.*s\0"
msg_03:	.word 1
	.ascii "\0"
msg_00:	.word 12
	.ascii "r1: sending "
msg_01:	.word 13
	.ascii "r2: received "

.text

.global main
r1:
L01:
		LDR r5, =msg_00
		MOV r0, r5
		BL p_print_string
		MOV r5, r4
		MOV r0, r5
		BL p_print_string
		BL p_print_ln
		MOV r4, r4
		BL r1
		ADD sp, sp, #4
		MOV r4, r0
main:
		PUSH {lr}
		LDR r4, =0
		LDR r4, =8
		BL r1
		ADD sp, sp, #4
		MOV r4, r0
		MOV r4, r4
		LDR r0, =0
		POP {pc}
		.ltorg
p_print_string:
		PUSH {lr}
		LDR r1, [r0]
		ADD r2, r0, #4
		LDR r0, =msg_02
		ADD r0, r0, #4
		BL printf
		MOV r0, #0
		BL fflush
		POP {pc}
p_print_ln:
		PUSH {lr}
		LDR r0, =msg_03
		ADD r0, r0, #4
		BL puts
		MOV r0, #0
		BL fflush
		POP {pc}
