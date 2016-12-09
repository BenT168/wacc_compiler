r1:
		PUSH {lr}
		MOV t2, t0
		LDR t3, =0
		CMP t2, t3
		MOVEQ t1, #1
		MOVNE t1, #0
		BEQ L01
		LDR t4, =0
		B L00
L01:
		LDR t5, =msg_00
		MOV r0, t5
		BL p_print_string
		MOV t6, t0
		MOV r0, t6
		BL p_print_string
		BL p_print_ln
		MOV t7, t0
		BL r2
		ADD sp, sp, #4
		MOV t7, r0
L00:
		LDR t8, =42
		MOV r0, t8
		POP {pc}
		POP {pc}
.ltorg
r2:
		PUSH {lr}
		LDR t10, =msg_01
		MOV r0, t10
		BL p_print_string
		MOV t11, t9
		MOV r0, t11
		BL p_print_string
		BL p_print_ln
		MOV t13, t9
		LDR t14, =1
		ADD t12, t13, t14
		BL r1
		ADD sp, sp, #4
		MOV t12, r0
		LDR t15, =44
		MOV r0, t15
		POP {pc}
		POP {pc}
.ltorg
main:
		PUSH {lr}
		LDR t16, =0
		LDR t17, =8
		BL r1
		ADD sp, sp, #4
		MOV t17, r0
		MOV t16, t17
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
