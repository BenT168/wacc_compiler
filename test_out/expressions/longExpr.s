	.text

.global main
	main:
		PUSH {lr}
		SUB sp, sp, #4
		LDR r4,  =1
		LDR r5,  =2
		LDR r6,  =3
		LDR r7,  =4
		LDR r8,  =5
		LDR r9,  =6
		LDR r10,  =7
		PUSH {r10}
		LDR r10,  =8
		PUSH {r10}
		LDR r10,  =9
		PUSH {r10}
		LDR r10,  =10
		PUSH {r10}
		LDR r10,  =11
		PUSH {r10}
		LDR r10,  =12
		PUSH {r10}
		LDR r10,  =13
		PUSH {r10}
		LDR r10,  =14
		PUSH {r10}
		LDR r10,  =15
		PUSH {r10}
		LDR r10,  =16
		PUSH {r10}
		LDR r10,  =17
		POP {r11}
		ADDS r10, r11, r10
		BLVS p_throw_overflow_error
		POP {r11}
		ADDS r10, r11, r10
		BLVS p_throw_overflow_error
		POP {r11}
		ADDS r10, r11, r10
		BLVS p_throw_overflow_error
		POP {r11}
		ADDS r10, r11, r10
		BLVS p_throw_overflow_error
		POP {r11}
		ADDS r10, r11, r10
		BLVS p_throw_overflow_error
		POP {r11}
		ADDS r10, r11, r10
		BLVS p_throw_overflow_error
		POP {r11}
		ADDS r10, r11, r10
		BLVS p_throw_overflow_error
		POP {r11}
		ADDS r10, r11, r10
		BLVS p_throw_overflow_error
		POP {r11}
		ADDS r10, r11, r10
		BLVS p_throw_overflow_error
		POP {r11}
		ADDS r10, r11, r10
		BLVS p_throw_overflow_error
		ADDS r9, r9, r10
		BLVS p_throw_overflow_error
		ADDS r8, r8, r9
		BLVS p_throw_overflow_error
		ADDS r7, r7, r8
		BLVS p_throw_overflow_error
		ADDS r6, r6, r7
		BLVS p_throw_overflow_error
		ADDS r5, r5, r6
		BLVS p_throw_overflow_error
		ADDS r4, r4, r5
		BLVS p_throw_overflow_error
		EOR r4, r4, #1
		STR r4, [sp]
		LDR r4, [sp]
		MOV r0, r4
		BL exit
		ADD sp, sp, #4
		LDR r0,  =0
		POP {pc}
	 	.ltorg
