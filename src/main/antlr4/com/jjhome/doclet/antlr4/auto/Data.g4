grammar Data;

file : group+ ;

group : INT sequence[$INT.int] ;

sequence[int n]
  locals [int i=1;]
   : ( {$i<=$n}? INT {$i++;} )* // match n integers
   ;

INT : [0-9]+ ; // Define token INT as one or more digits
 WS : [ \t\r\n]+ -> skip ; // Define whitespace rule, toss it out

