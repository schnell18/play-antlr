grammar Rows;

@parser::members {
    int col;
    public RowsParser(TokenStream stream, int col) {
        this(stream);
        this.col = col;
    }
}


file : (row NL)+ ;

row
  locals [int i=0]
   : (STUFF
       {
          $i++;
          if ($i == col) System.out.println($STUFF.text);
       }
   )+
   ;

TAB    : '\t' -> skip ;
NL     : '\r'? '\n' ;
STUFF  : ~[\t\r\n]+ ;
