	.data
	
	msg_0:
		.word 1
		.ascii	"\0"
	.text

.global main
	main:
		PUSH {lr}
		SUB sp, sp, #8
		LDR r4,  =-5
		STR r4, [sp, #4]
		LDR r4,  =-3
		STR r4, [sp]
		LDR r4, [sp, #4]
		LDR r5, [sp]
		MOV r0, r4
		MOV r1, r5
		BL p_check_divide_by_zero
		BL __aeabi_idivmod
		MOV r4, r1
		MOV r0, r4
		BL p_print_int
		BL p_print_ln
		ADD sp, sp, #8
		LDR r0,  =0
		POP {pc}
	 	.ltorg
	p_print_ln:
		PUSH {lr}
		LDR r0,  =msg_0
		ADD r0, r0, #4
		BL puts
		MOV r0,  #0
		BL fflush
		POP {pc}
