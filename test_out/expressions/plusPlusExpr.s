	.data
	
	msg_0:
		.word 1
		.ascii	"\0"
	.text

.global main
	main:
		PUSH {lr}
		LDR r4,  =1
		LDR r5,  =+2
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		MOV r0, r4
		BL p_print_int
		BL p_print_ln
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
