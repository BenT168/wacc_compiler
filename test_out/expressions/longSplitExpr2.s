	.data
	
	msg_0:
		.word 1
		.ascii	"\0"
	.text

.global main
	main:
		PUSH {lr}
		SUB sp, sp, #16
		LDR r4,  =1
		LDR r5,  =2
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =3
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =4
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =5
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =6
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =7
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =8
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =9
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =10
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =11
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =12
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =13
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =14
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =15
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =16
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		LDR r5,  =17
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		STR r4, [sp, #12]
		RSBS r4, r4, #0
		STR r4, [sp, #8]
		LDR r4,  =1
		LDR r5,  =2
		SMULL r4, r5, r4, r5
		CMP r5, r4, ASR #31
		BLNE p_throw_overflow_error
		LDR r5,  =3
		SMULL r4, r5, r4, r5
		CMP r5, r4, ASR #31
		BLNE p_throw_overflow_error
		LDR r5,  =4
		SMULL r4, r5, r4, r5
		CMP r5, r4, ASR #31
		BLNE p_throw_overflow_error
		LDR r5,  =5
		SMULL r4, r5, r4, r5
		CMP r5, r4, ASR #31
		BLNE p_throw_overflow_error
		LDR r5,  =6
		SMULL r4, r5, r4, r5
		CMP r5, r4, ASR #31
		BLNE p_throw_overflow_error
		LDR r5,  =7
		SMULL r4, r5, r4, r5
		CMP r5, r4, ASR #31
		BLNE p_throw_overflow_error
		LDR r5,  =8
		SMULL r4, r5, r4, r5
		CMP r5, r4, ASR #31
		BLNE p_throw_overflow_error
		LDR r5,  =9
		SMULL r4, r5, r4, r5
		CMP r5, r4, ASR #31
		BLNE p_throw_overflow_error
		LDR r5,  =10
		SMULL r4, r5, r4, r5
		CMP r5, r4, ASR #31
		BLNE p_throw_overflow_error
		EOR r4, r4, #1
		STR r4, [sp, #4]
		LDR r4,  =10
		STR r4, [sp]
		ADDS r4, r4, r4
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		MOV r0, r4
		BL p_print_int
		BL p_print_ln
		ADDS r4, r4, r4
		BLVS p_throw_overflow_error
		LDR r4,  =256
		MOV r0, r4
		MOV r1, r4
		BL p_check_divide_by_zero
		BL __aeabi_idivmod
		MOV r4, r1
		EOR r4, r4, #1
		MOV r0, r4
		BL p_print_int
		BL p_print_ln
		ADDS r4, r4, r4
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		MOV r0, r4
		BL exit
		ADD sp, sp, #16
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
