.data

msg_02:
	.word 5
	.ascii "%.*s\0"
msg_01:
	.word 13
	.ascii "r2: received "
msg_03:
	.word 1
	.ascii "\0"
msg_00:
	.word 12
	.ascii "r1: sending "

.text

.global main
