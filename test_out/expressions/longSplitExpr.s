	.text

.global main
	main:
		PUSH {lr}
		SUB sp, sp, #36
		LDR r4,  =1
		LDR r5,  =2
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		STR r4, [sp, #32]
		LDR r4,  =3
		LDR r5,  =4
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		STR r4, [sp, #28]
		LDR r4,  =5
		LDR r5,  =6
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		STR r4, [sp, #24]
		LDR r4,  =7
		LDR r5,  =8
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		STR r4, [sp, #20]
		LDR r4,  =9
		LDR r5,  =10
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		STR r4, [sp, #16]
		LDR r4,  =11
		LDR r5,  =12
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		STR r4, [sp, #12]
		LDR r4,  =13
		LDR r5,  =14
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		STR r4, [sp, #8]
		LDR r4,  =15
		LDR r5,  =16
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		STR r4, [sp, #4]
		LDR r4,  =17
		STR r4, [sp]
		ADDS r4, r4, r4
		BLVS p_throw_overflow_error
		MOV r0, r4
		BL exit
		ADD sp, sp, #36
		LDR r0,  =0
		POP {pc}
	 	.ltorg
