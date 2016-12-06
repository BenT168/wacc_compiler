.data


.text

.global main
main:
		PUSH {lr}
		LDR t1, =1
		LDR t3, =2
		LDR t5, =3
		LDR t7, =4
		LDR t9, =5
		LDR t11, =6
		LDR t13, =7
		LDR t15, =8
		LDR t17, =9
		LDR t19, =10
		LDR t21, =11
		LDR t23, =12
		LDR t25, =13
		LDR t27, =14
		LDR t29, =15
		LDR t31, =16
		LDR t32, =17
		ADD t30, t31, t32
		ADD t28, t29, t30
		ADD t26, t27, t28
		ADD t24, t25, t26
		ADD t22, t23, t24
		ADD t20, t21, t22
		ADD t18, t19, t20
		ADD t16, t17, t18
		ADD t14, t15, t16
		ADD t12, t13, t14
		ADD t10, t11, t12
		ADD t8, t9, t10
		ADD t6, t7, t8
		ADD t4, t5, t6
		ADD t2, t3, t4
		ADD t0, t1, t2
		POP {pc}
.ltorg
