
/* Syntax of predicate logic : */ 
/* 	if and only if :- */
/* 	and , */
/* 	or . */
/* 	not not() */
/*	variables - capitcal letters */
/* 	constants - small letters */
/* To run ["relative path"]. */

pig(pat).
slimy(steve).
creeps(steve).
faster(X,Z) :- pig(X) , slug(Z).
slug(Z) :- slimy(Z) , creeps(Z).
