main:
		PUSH {lr}
		LDR t0, =1
		LDR t1, =msg_00
		BL p_print_ln
		B L00
L01:
		LDR t2, t0
		BL p_print_ln
		LDR t4, t0
		LDR t5, =1
		ADD t3, t4, t5
		LDR t0, t3
L00:
		LDR t7, t0
		LDR t8, =10
		CMP t7, t8
		MOVLE t6, #1
		MOVGT t6, #0
		CMP t6, #1
		BEQ L01
		LDR r0, 0
		POP {pc}
.ltorg
p_print_ln:
		PUSH {lr}
		LDR t9, =msg_01
		ADD t9, t9, #4
		BL puts
		MOV t9, #0
		BL fflush
		POP {pc}
p_print_ln:
		PUSH {lr}
		LDR t10, =msg_02
		ADD t10, t10, #4
		BL puts
		MOV t10, #0
		BL fflush
		POP {pc}
