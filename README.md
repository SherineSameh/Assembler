## SIC Assembler Project
A simple assembler for SIC (standard version) that reads the source program and generates the object program and the listing file.

The assembler able to handle all standard SIC instructions.
Instruction operands must be of the form ‘address’ or ‘address,x’ where ‘ad-
dress’ is either a symbol that is used as a label in the source program or an
actual hexadecimal address. It also supports the following assembler directives: START, END, BYTE, WORD, RESB, RESW, USE, EQU and ORG
