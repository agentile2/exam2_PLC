# exam2_PLC
PLC exam 2 - includes code, readme, and 4 test case files

A. Tokens/lexemes

S’ —> 





Operators 
	ad_tion : ‘+’
	sub_trct : ‘-‘
	mlt_ply : ‘*’
	md_ule : ‘%’
	dv_ide : ‘/‘
	sm_ler : ‘<‘
	gr_atr : ‘>’
	le_eql : ‘<=‘
	gr_eql : ‘>=‘
	eq_al : ‘=‘
	no_eql : ‘!=‘
	ass_ign : ‘id’
	prec_ednc : ‘(‘


“<stmnt> —>  <loop_stmnt> | <ass_ign> | <blck> \n”,
“<blck_stmnt> —> ‘ { ‘ { <stmnt> ‘;’ } ‘}’ \n”,
“<loop_stmnt> —>  ‘loop’ ‘(‘ <bexpr> ‘)’ <stmnt> \n”,
“<ass_ign> —> ‘id’ “=‘ <op_rte>\n”,
“<op_rte> —> <opr_rte2> { (‘+’ ) <opr_rte2> }\n”,
“<op_rte> —> <opr_rte2> { (‘-‘) <opr_rte2> }\n”,
“<op_rte2> —> <fctor> { (‘*’ | ‘\\’ | ‘%’ ) <fctor> }\n”,
“<op_rte2> —> <fctor> { (‘*’ ) <fctor> }\n”,
“<op_rte2> —> <fctor> { (‘\\’  ) <fctor> }\n”,
“<op_rte2> —> <fctor> { (‘%’ ) <fctor> }\n”,
“<fctor> —> ‘id’ | ‘int_ltrl’ | ‘(‘ <op_rte> ‘)’ \n”,






 
