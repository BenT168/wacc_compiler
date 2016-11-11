import SemanticAnalyser.*;
import antlr.WaccParser;
import antlr.WaccParser.ExprContext;
import antlr.WaccParser.FuncContext;
import antlr.WaccParser.Func_ifContext;
import antlr.WaccParser.Func_standardContext;
import antlr.WaccParser.ParamContext;
import antlr.WaccParser.StatContext;
import antlr.WaccParser.Stat_returnContext;
import antlr.WaccParserBaseVisitor;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
//import sun.jvm.hotspot.debugger.cdbg.Sym;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyWaccVisitor<T> extends WaccParserBaseVisitor<T> {
    SymbolTable currentTable = new SymbolTable(null);
    
    boolean prints = false;


    @Override 
    public T visitStat_stat(@NotNull WaccParser.Stat_statContext ctx) {

        WaccParser.StatContext first = ctx.stat(0); 
        WaccParser.StatContext second = ctx.stat(1); 
        visit(first);
        visit(second); 
        ctx.typename = second.typename;

    	return null;

    }

    @Override 
    public T visitStat_assign(@NotNull WaccParser.Stat_assignContext ctx) {
    	if (prints) System.out.println("visitStat_assign");
        WaccParser.Assign_lhsContext lhs = ctx.assign_lhs();
        WaccParser.Assign_rhsContext rhs = ctx.assign_rhs();

        visit(lhs);    

        visit(rhs);
        if(lhs.typename == null){
          if (prints) System.out.println("assign to unknown");
          System.exit(200);
        }
        if(rhs.typename == null){
          if (prints) System.out.println("assigning unknown");
          System.exit(200);
        }
        if (prints) System.out.println("lhs typename " + lhs.typename);
        if (prints) System.out.println("rhs typename " + rhs.typename);

        if ((!SharedMethods.assignCompat(lhs.typename, rhs.typename)))  {
            if (prints) System.out.println("HERE");
        	System.exit(200);
        }
        
    	return null;
    }

    @Override 
    public T visitStat_declare(@NotNull WaccParser.Stat_declareContext ctx) {
    	if (prints) System.out.println("visitStat_declare");
  	
      WaccParser.Assign_rhsContext rhs = ctx.assign_rhs();
      visit(rhs);
      if (prints) System.out.println("SEP: ");
      visit(ctx.type());
      if (prints) System.out.println("After visit declare lhs and rhs");

      if (prints) System.out.println("declare rhs: " + rhs.typename);
      if (prints) System.out.println("declare lhs: " + ctx.type().typename);
      
      //---------------------- catching declaration of array with empty array ie int[] x = [] should be fine
      
      if(ctx.type().typename instanceof ARRAY_TYPE) {
        if(rhs.typename instanceof ARRAY_TYPE) {
          if(((ARRAY_TYPE)rhs.typename).TYPE() == null){
            VARIABLE var = new VARIABLE(rhs.typename);
            currentTable.add(ctx.ident().getText(), var);
            return null;
          }
        }
      }
      //------------------------
      
      if(rhs.typename == null) {
    	  rhs.typename = new NULL();
      }
      
      if(!SharedMethods.assignCompat(ctx.type().typename, rhs.typename)) {
 //   	  throw new Error("Different type");
      	  System.exit(200);
      }

      if (currentTable.lookup(ctx.ident().getText()) != null) {
 //   	  throw new Error("Variable already declared");
    	  if (prints) System.out.println("var already declared: " + ctx.ident().getText());
      	  System.exit(200);
      }

      VARIABLE var = new VARIABLE(rhs.typename);
      currentTable.add(ctx.ident().getText(), var);
      
  	  return null;
    }

    @Override
    public T visitFunc_standard(@NotNull WaccParser.Func_standardContext ctx) {
    	if (prints) System.out.println("visitFunc_std");
		IDENTIFIER id = currentTable.lookupAllFunc(ctx.ident().getText());
		if(((FUNCTION) id).symtab != null) System.exit(200);

		visit(ctx.type());

		TYPE returntypename = ctx.type().typename;

		SymbolTable newST = new SymbolTable(currentTable);
		


		ctx.funObj = new FUNCTION(returntypename);
		currentTable.funcadd(ctx.ident().getText(), ctx.funObj);
		ctx.funObj.symtab = newST;
		currentTable = newST;
		
		if(ctx.param_list() != null){
			visit(ctx.param_list());

			List <ParamContext> params = ctx.param_list().param();
			for(ParamContext p : params){
				ctx.funObj.formals.add(p.paramObj);
			}
			if (prints) System.out.println("Before stat");
		}
			

			if (!(ctx.stat() == null)){
				visit(ctx.stat());
			}
			visit(ctx.stat_return());

			if(!SharedMethods.assignCompat(ctx.stat_return().typename, returntypename)) {

				System.exit(200);
			}
			
			currentTable = currentTable.encSymTable;
		return null;
    }
    
    @Override
    public T visitFunc_if(@NotNull WaccParser.Func_ifContext ctx) {
    	if (prints) System.out.println("visitFunc_if");
		IDENTIFIER id = currentTable.lookupAllFunc(ctx.ident().getText());
		if(((FUNCTION) id).symtab != null) System.exit(200);

		visit(ctx.type());

		TYPE returntypename = ctx.type().typename;

		SymbolTable newST = new SymbolTable(currentTable);
		


		ctx.funObj = new FUNCTION(returntypename);
		currentTable.funcadd(ctx.ident().getText(), ctx.funObj);
		ctx.funObj.symtab = newST;
		currentTable = newST;
		
		if(ctx.param_list() != null){

			visit(ctx.param_list());

			List <ParamContext> params = ctx.param_list().param();
			for(ParamContext p : params){
				ctx.funObj.formals.add(p.paramObj);
			}
			if (prints) System.out.println("Before stat");
		}
		
		if (ctx.stat() != null) {
			visit(ctx.stat());
		}
		visit(ctx.if_layers());
		
		if (!SharedMethods.assignCompat(ctx.if_layers().typename, returntypename)){
        	System.exit(200);
		}

		currentTable = currentTable.encSymTable;
		return null;
    }
    
	@Override public T visitLayer_s_s(@NotNull WaccParser.Layer_s_sContext ctx) {
		if (prints) System.out.println("visitLayer_s_s");
		currentTable = new SymbolTable(currentTable);

		if (ctx.stat(0) != null) {
			visit(ctx.stat(0));
		}
		if (ctx.stat(1) != null) {
			visit(ctx.stat(1));
		}
		visit(ctx.stat_return(0));
		visit(ctx.stat_return(1));
		if (!SharedMethods.assignCompat(ctx.stat_return(0).typename, ctx.stat_return(1).typename)){
        	System.exit(200);
		}
		ctx.typename = ctx.stat_return(0).typename;

		currentTable = currentTable.encSymTable;
		return null;
	}
	
	@Override public T visitLayer_i_i(@NotNull WaccParser.Layer_i_iContext ctx) {
		if (prints) System.out.println("visitLayer_i_i");
		currentTable = new SymbolTable(currentTable);

		if (ctx.stat(0) != null) {
			visit(ctx.stat(0));
		}
		if (ctx.stat(1) != null) {
			visit(ctx.stat(1));
		}
		visit(ctx.if_layers(0));
		visit(ctx.if_layers(1));
		if (!SharedMethods.assignCompat(ctx.if_layers(0).typename, ctx.if_layers(1).typename)){
        	System.exit(200);
		}
		ctx.typename = ctx.if_layers(0).typename;

		currentTable = currentTable.encSymTable;
		return null;
	}
	
	@Override public T visitLayer_s_i(@NotNull WaccParser.Layer_s_iContext ctx) {
		if (prints) System.out.println("visitLayer_s_i");
		currentTable = new SymbolTable(currentTable);

		if (ctx.stat(0) != null) {
			visit(ctx.stat(0));
		}
		if (ctx.stat(1) != null) {
			visit(ctx.stat(1));
		}
		visit(ctx.if_layers());
		visit(ctx.stat_return());
		if (!SharedMethods.assignCompat(ctx.if_layers().typename, ctx.stat_return().typename)){
        	System.exit(200);
		}
		ctx.typename = ctx.stat_return().typename;

		currentTable = currentTable.encSymTable;
		return null;
	}


	@Override public T visitAssign_rhs_call(@NotNull WaccParser.Assign_rhs_callContext ctx) {
    	if (prints) System.out.println("visitAssign_rhs_call");
		String funcname = ctx.ident().getText();
		List<ExprContext> actuals = ctx.arg_list().expr();

		IDENTIFIER F = currentTable.lookupAllFunc(funcname);

		if (F == null) {

        	System.exit(200);
		}
		if (!(F instanceof FUNCTION)) {
        	System.exit(200); 
		}
		if (((FUNCTION) F).formals.size() != actuals.size()) {

        	System.exit(200);//throw new Error ("wrong number of parameters");
		}
		for(int i = 0; i < actuals.size(); i++){
			ExprContext each = actuals.get(i);
			visit(each);

			if (!SharedMethods.assignCompat(((FUNCTION) F).formals.get(i).TYPE(), each.typename)){
	        	System.exit(200);//throw new Error("type of func param " + i + " incompatible with declaration");
			}
		}
		FUNCTION fun = (FUNCTION) F;
		ctx.typename = fun.returntype;


		return null;
	}

	@Override public T visitAssign_rhs_call_empty(@NotNull WaccParser.Assign_rhs_call_emptyContext ctx) {
    	if (prints) System.out.println("visitAssign_rhs_call_empty");
		String funcname = ctx.ident().getText();
		IDENTIFIER F = currentTable.lookupAllFunc(funcname);

		if (F == null) {
        	System.exit(200);//throw new Error("unknown function" + funcname);
		}
		if (!(F instanceof FUNCTION)) {
        	System.exit(200);//throw new Error(funcname + "is not a function");
		}
		if (((FUNCTION) F).formals.size() != 0) {
        	System.exit(200);//throw new Error ("wrong number of parameters");
		}

		FUNCTION fun = (FUNCTION) F;
		ctx.typename = fun.returntype;
		return null;
	}


	@Override public T visitPair_liter(@NotNull WaccParser.Pair_literContext ctx) { 
    	if (prints) System.out.println("visitPair_liter");

		return null;
	}

	@Override public T visitParam(@NotNull WaccParser.ParamContext ctx) {
    	if (prints) System.out.println("visitParam");
		visit(ctx.type());

		String param_name = ctx.ident().getText();
		PARAM p = new PARAM(ctx.type().typename);
		currentTable.add(param_name, p);
		ctx.paramObj = p;

		return null;
	}

	@Override public T visitStat_if(@NotNull WaccParser.Stat_ifContext ctx) { 
    	if (prints) System.out.println("visitStat_if");
		visit(ctx.expr());
		if (prints) System.out.println("expr = "+ ctx.expr().toString());

		if(!(SharedMethods.assignCompat(ctx.expr().typename, new BOOL()))){
			System.out.print("if condition is not of type bool");
			System.exit(200);
		}

		currentTable = new SymbolTable(currentTable);
		visit(ctx.stat(0));
		currentTable = currentTable.encSymTable;
		
		currentTable = new SymbolTable(currentTable);
		visit(ctx.stat(1));
		currentTable = currentTable.encSymTable;
		
		ctx.typename = ctx.stat(0).typename;
		return null;
	}

	@Override public T visitStat_read(@NotNull WaccParser.Stat_readContext ctx) { 
    	if (prints) System.out.println("visitStat_read");
		
		visit(ctx.assign_lhs());
		if(ctx.assign_lhs().typename instanceof NULL) {
			return null;
		}


		if((!(ctx.assign_lhs().typename instanceof INT)) &&
		(!(ctx.assign_lhs().typename instanceof CHAR)))
        	System.exit(200);

		return null;
	}

	@Override 
	public T visitType_pairtype(@NotNull WaccParser.Type_pairtypeContext ctx) { 
    	if (prints) System.out.println("visitType_pairtype");
		visit(ctx.pair_type());
		ctx.typename = ctx.pair_type().typename;
		return null;
	}
	
	@Override 
	public T visitType_arraytype(@NotNull WaccParser.Type_arraytypeContext ctx) { 
    	if (prints) System.out.println("visitType_arraytype");
		  visit(ctx.array_type());
    	ctx.typename = ctx.array_type().typename;

		return null;
	}
	
	@Override 
	public T visitType_basetype(@NotNull WaccParser.Type_basetypeContext ctx) {
    	if (prints) System.out.println("visitType_basetype");
		visit(ctx.base_type());
		ctx.typename = ctx.base_type().typename;
		
		return null;
	}

	@Override public T visitStat_exit(@NotNull WaccParser.Stat_exitContext ctx) {
    	if (prints) System.out.println("visitStat_exit");
		visit(ctx.expr());
		ctx.typename = ctx.expr().typename;
		if(!SharedMethods.assignCompat(ctx.typename, new INT())) {
			System.exit(200);
		}
		
		return null; 
	}

	public T visitUnary_oper(@NotNull WaccParser.Unary_operContext ctx) { 
    	if (prints) System.out.println("visitUnary_oper");
		return visitChildren(ctx);
	}

	@Override public T visitStat_while(@NotNull WaccParser.Stat_whileContext ctx) { 
    	if (prints) System.out.println("visitStat_while");
		visit(ctx.expr());

		
		if(!(SharedMethods.assignCompat(ctx.expr().typename, new BOOL()))){
			System.out.print("if condition is not of type bool");
			System.exit(200);
		}

		visit(ctx.stat());
		return null; 
	}


	@Override public T visitIdent(@NotNull WaccParser.IdentContext ctx) {


		if (prints) System.out.println("visitIdent");
		if(ctx.getText().equals("null")) {
			ctx.typename = new NULL();
			return null;
		}
		IDENTIFIER id = currentTable.lookupAll(ctx.getText());
		if(id == null) if (prints) System.out.println("visitIndent: LHS IS NULLLLL");	//REMOVE
		if(id instanceof VARIABLE){
			ctx.typename = ((VARIABLE) id).TYPE();
		}
		
		return null;
	
	}
	
	@Override public T visitAssign_lhs_ident(@NotNull WaccParser.Assign_lhs_identContext ctx) { 
    	if (prints) System.out.println("visitAssign_lhs_ident");

		IDENTIFIER id = currentTable.lookupAll(ctx.getText());


		if(id == null) if (prints) System.out.println("visitAssign_lhs_indent: LHS IS NULLLLL");	////REMOVE
		if(id instanceof VARIABLE){

			ctx.typename = ((VARIABLE) id).TYPE();
		}
	
		return null;
}
	
	@Override public T visitAssign_lhs_array(@NotNull WaccParser.Assign_lhs_arrayContext ctx) {
    	if (prints) System.out.println("visitAssign_lhs_array");
		
    	
    	visit(ctx.array_elem());
    	
    	ctx.typename = ctx.array_elem().typename;
    	
		return null;
	}
	
	@Override 
	public T visitAssign_lhs_pair(@NotNull WaccParser.Assign_lhs_pairContext ctx) { 
    	if (prints) System.out.println("visitAssign_lhs_pair");

		visit(ctx.pair_elem());
		ctx.typename = ctx.pair_elem().typename;
		return null;
	}

	@Override public T visitStat_return(@NotNull WaccParser.Stat_returnContext ctx) { 
    	if (prints) System.out.println("visitStat_return");
		visit(ctx.expr());
		if (ctx.RETURN() != null) {
			assert ctx.EXIT() == null;
			ctx.typename = ctx.expr().typename;
		}
		else {
			assert ctx.EXIT() != null;
			ctx.typename = new NULL();
		}
		
		if(currentTable.encSymTable == null) {
			System.exit(200);
		}
		return null; 
		
	}

	@Override 
	public T visitArray_type_array(@NotNull WaccParser.Array_type_arrayContext ctx) { 
    	if (prints) System.out.println("visitArray_type_array");
		visit(ctx.array_type());
		
		ctx.typename = new ARRAY_TYPE(ctx.array_type().typename);

		return null;
	}
	
	@Override 
	public T visitArray_type_base(@NotNull WaccParser.Array_type_baseContext ctx) { 
    	if (prints) System.out.println("visitArray_type_base");
		visit(ctx.base_type());

		
		ctx.typename = new ARRAY_TYPE(ctx.base_type().typename);
		return null;
	}
	
	@Override 
	public T visitArray_type_pair(@NotNull WaccParser.Array_type_pairContext ctx) { 
    	if (prints) System.out.println("visitArray_type_pair");
		visit(ctx.pair_type());

		
		ctx.typename = new ARRAY_TYPE(ctx.pair_type().typename);
		return null;
	}

	
	@Override 
	public T visitPair_elem_fst(@NotNull WaccParser.Pair_elem_fstContext ctx) { 
		if (prints) System.out.println("visitPair_elem_fst");

		visit(ctx.expr());
		if(ctx.expr().typename instanceof NULL) {
			ctx.typename = new NULL();
			return null;
		}
		PAIR_TYPE pair = (PAIR_TYPE) ctx.expr().typename;
		ctx.typename = pair.firstType();
		return null; 
	}
	
	@Override 
	public T visitPair_elem_snd(@NotNull WaccParser.Pair_elem_sndContext ctx) {
		if (prints) System.out.println("visitPair_elem_snd");
		visit(ctx.expr());
		if(ctx.expr().typename instanceof NULL) {
			ctx.typename = new NULL();
			return null;
		}
		PAIR_TYPE pair = (PAIR_TYPE) ctx.expr().typename;
		ctx.typename = pair.secondType();
		return null; 
	}
	
	//assign rhs ------------------------
	
	@Override 
	public T visitAssign_rhs_newpair(@NotNull WaccParser.Assign_rhs_newpairContext ctx) { 
    	if (prints) System.out.println("visitAssign_rhs_newpair");
		visit(ctx.expr(0));
		visit(ctx.expr(1));
		if(ctx.expr(0).typename == null) {
			ctx.expr(0).typename = new NULL();
		}
		if(ctx.expr(1).typename == null) {
			ctx.expr(1).typename = new NULL();
		}
		ctx.typename = new PAIR_TYPE(ctx.expr(0).typename, ctx.expr(1).typename);
		return null;
	}
	
	@Override public T visitAssign_rhs_expr(@NotNull WaccParser.Assign_rhs_exprContext ctx) { 
    	if (prints) System.out.println("visitAssign_rhs_expr");
		visit(ctx.expr());
		ctx.typename = ctx.expr().typename;
		return null;
	}
	
	@Override public T visitAssign_rhs_ar_liter(@NotNull WaccParser.Assign_rhs_ar_literContext ctx) { 
    	if (prints) System.out.println("visitAssign_rhs_ar_liter");
		visit(ctx.array_liter());
		ctx.typename = new ARRAY_TYPE(ctx.array_liter().typename);
		return null;
	}
	
	@Override public T visitAssign_rhs_pair_elem(@NotNull WaccParser.Assign_rhs_pair_elemContext ctx) { 
    	if (prints) System.out.println("visitAssign_rhs_pair_elem");
		visit(ctx.pair_elem());
		ctx.typename = ctx.pair_elem().typename;
		return null;
	}
	
	//-------------------------------------------------
	@Override public T visitStat_skip(@NotNull WaccParser.Stat_skipContext ctx) {
    	if (prints) System.out.println("visitStat_skip");
		ctx.typename = null;
		return null; 
	}
	
	@Override 
	public T visitBase_type_int(@NotNull WaccParser.Base_type_intContext ctx) { 
    	if (prints) System.out.println("visitBase_type_int");

    	ctx.typename = new INT();
		return null;
	}
	
	@Override 
	public T visitBase_type_char(@NotNull WaccParser.Base_type_charContext ctx) {
    	if (prints) System.out.println("visitBase_type_char");
		ctx.typename = new CHAR();
		return null; 
	}
	
	@Override 
	public T visitBase_type_string(@NotNull WaccParser.Base_type_stringContext ctx) { 
    	if (prints) System.out.println("visitBase_type_string");
		ctx.typename = new STRING();
		return null;
	}
	
	@Override 
	public T visitBase_type_bool(@NotNull WaccParser.Base_type_boolContext ctx) { 
    	if (prints) System.out.println("visitBase_type_bool");
		ctx.typename = new BOOL();
		return null;
	}

	@Override public T visitPair_type(@NotNull WaccParser.Pair_typeContext ctx) {
    	if (prints) System.out.println("visitPair_type");
    	if (prints) System.out.println("Pair elem1: " + ctx.pair_elem_type(0).typename);
    	if (prints) System.out.println("Pair elem2: " + ctx.pair_elem_type(1).typename);
		visit(ctx.pair_elem_type(0));
		visit(ctx.pair_elem_type(1));

		ctx.typename = new PAIR_TYPE(ctx.pair_elem_type(0).typename, ctx.pair_elem_type(1).typename);
		return null;
	}

	@Override public T visitParam_list(@NotNull WaccParser.Param_listContext ctx) { 
		List<ParamContext> pctx = ctx.param();
		for (ParamContext p : pctx){
			visit(p);
		}

		return null;
	}

	@Override public T visitStat_begin_end(@NotNull WaccParser.Stat_begin_endContext ctx) {
		if (prints) System.out.println("visitStat_begin_end");
		SymbolTable table = new SymbolTable(currentTable);
		currentTable = table;


		visit(ctx.stat());

		currentTable = table.encSymTable;
		return null; 
	}

	@Override public T visitStat_free(@NotNull WaccParser.Stat_freeContext ctx) {
		if (prints) System.out.println("visitStat_free");
		visit(ctx.expr());
		ctx.typename = ctx.expr().typename;
		if(ctx.typename instanceof NULL) {

			return null;
		}
		if (!(ctx.typename instanceof PAIR_TYPE) && !(ctx.typename instanceof ARRAY_TYPE)){

        	System.exit(200);//throw new Error("Cannot free TYPE " + ctx.typename.toString() + ", ARRAY_TYPE or PAIR_TYPE expected.");
		}

		return null; 
	}

	@Override public T visitArg_list(@NotNull WaccParser.Arg_listContext ctx) {
		List<ExprContext> list = ctx.expr();
		for (ExprContext e : list){
			visit(e);
		}
		return null; 
	}

	@Override public T visitArray_elem(@NotNull WaccParser.Array_elemContext ctx) { 
		VARIABLE array_or_string = (VARIABLE) currentTable.lookupAll(ctx.ident().getText());
		
		if(array_or_string.TYPE() instanceof STRING){
		  if (prints) System.out.println("ITS A STRING");
		  ctx.typename = new CHAR();
		}
		else{
		  ctx.typename = ((ARRAY_TYPE)array_or_string.TYPE()).TYPE();
		}
		return null;
	}

	@Override public T visitProgram(@NotNull WaccParser.ProgramContext ctx) { 

		List<FuncContext> list = ctx.func();
		for(FuncContext func : list) {
			try {
				Func_standardContext f = (Func_standardContext) func;
				if (prints) System.out.println("IDENT: " + f.ident().getText());
				visit(f.type());
				FUNCTION newFunc = new FUNCTION(f.type().typename);
				if(f.param_list() != null){
					currentTable = new SymbolTable(currentTable);
					visit(f.param_list());
					currentTable = currentTable.encSymTable;
					
					List <ParamContext> params = f.param_list().param();
					for(ParamContext p : params){
						newFunc.formals.add(p.paramObj);
					}
				} 

				currentTable.funcadd(f.ident().getText(), newFunc);
			} catch (ClassCastException e) {
				Func_ifContext f = (Func_ifContext) func;currentTable = new SymbolTable(currentTable);
				if (prints) System.out.println("IDENT: " + f.ident().getText());
				visit(f.type());
				FUNCTION newFunc = new FUNCTION(f.type().typename);
				if(f.param_list() != null){
					currentTable = new SymbolTable(currentTable);
					visit(f.param_list());
					currentTable = currentTable.encSymTable;
					
					List <ParamContext> params = f.param_list().param();
					for(ParamContext p : params){
						newFunc.formals.add(p.paramObj);
					}
				} 

				currentTable.funcadd(f.ident().getText(), newFunc);
			}
			

			
		}
		visitChildren(ctx);

		return null; 
	}

	
	@Override 
	public T visitPair_elem_base_type(@NotNull WaccParser.Pair_elem_base_typeContext ctx) { 
		if (prints) System.out.println("visitPair_elem_base_type");
		visit(ctx.base_type());
		ctx.typename = ctx.base_type().typename;
		return null;
	}
	
	@Override 
	public T visitPair_elem_array_type(@NotNull WaccParser.Pair_elem_array_typeContext ctx) { 
		if (prints) System.out.println("visitPair_elem_array_type");
		visit(ctx.array_type());
		ctx.typename = ctx.array_type().typename;
		return null;
	}
	
	@Override 
	public T visitPair(@NotNull WaccParser.PairContext ctx) { 
		if (prints) System.out.println("visitPair");
		ctx.typename = new PAIR_TYPE(new NULL(), new NULL());  //<----not sure
		return null;
	}
	
	@Override public T visitArray_liter(@NotNull WaccParser.Array_literContext ctx) {
		if (prints) System.out.println("visitArray_liter");
		List<ExprContext> list = ctx.expr();

		if (list.isEmpty()){
			ctx.typename = null;
		} else {
			
			for (ExprContext e : list){
				visit(e);

//				if (!(e.typename.equals(ctx.expr().get(0).typename))){
				if(!SharedMethods.assignCompat(ctx.expr().get(0).typename, e.typename)){
		        	System.exit(200);//throw new Error("Array elem not the same type.");
				}
			}
			ctx.typename = ctx.expr().get(0).typename;
		}

		return null;
	}

	@Override public T visitStat_print(@NotNull WaccParser.Stat_printContext ctx) {
		if (prints) System.out.println("visitStat_print");
		visit(ctx.expr());
		if(ctx.expr().typename == null) {
			System.exit(200);
		}

		return null; 
	}

	@Override public T visitInt_liter(@NotNull WaccParser.Int_literContext ctx) { 
		List<TerminalNode> list = ctx.INTEGER();
		Iterator<TerminalNode> it = list.iterator();
		String number = "";
		if (ctx.int_sign() != null) {
			number = number + "-";
		}
		while (it.hasNext()) {
			number = number + it.next().getText();
		}

		try {
		Integer i = Integer.parseInt(number);
		} catch (NumberFormatException e) {
			if (prints) System.out.println("Number exceed limit");
			System.exit(100);
		}
		return null;
	}

	@Override public T visitStat_println(@NotNull WaccParser.Stat_printlnContext ctx) {
		if (prints) System.out.println("visitStat_println");
		visit(ctx.expr());
		if(ctx.expr().typename == null) {
			System.exit(200);
		}

		return null;
	}
	
	@Override 
	public T visitExpr_int(@NotNull WaccParser.Expr_intContext ctx) { 
		if (prints) System.out.println("visitExpr_int");
		visit(ctx.int_liter());

		ctx.typename = new INT();
		
		return null; 
	}
	
	@Override 
	public T visitExpr_bool(@NotNull WaccParser.Expr_boolContext ctx) {
		if (prints) System.out.println("visitExpr_bool");
		ctx.typename = new BOOL();
		return null; 
	}
	
	@Override 
	public T visitExpr_char(@NotNull WaccParser.Expr_charContext ctx) { 
		if (prints) System.out.println("visitExpr_char");
		ctx.typename = new CHAR();
		return null; 
	}
	
	@Override 
	public T visitExpr_str(@NotNull WaccParser.Expr_strContext ctx) { 
		if (prints) System.out.println("visitExpr_str");
		ctx.typename = new STRING();		
		return null;
	}
	
	@Override public T visitExpr_ident(@NotNull WaccParser.Expr_identContext ctx) {
		if (prints) System.out.println("visitExpr_ident");
		visit(ctx.ident());

		ctx.typename = ctx.ident().typename;
		String id = ctx.ident().getText();

		if(ctx.typename == null || ctx.typename instanceof NULL) {
			return null;
		}
		//also check if the ident has been declared

		if (currentTable.lookupAll(id) == null) System.exit(200);//throw new Error(id + "has not been declared");
		// do we have static variable in Wacc language. ^this would not support static var usage in stat in function declaration

		return null;
	}
	
	@Override public T visitExpr_pair(@NotNull WaccParser.Expr_pairContext ctx) { 
		ctx.typename = new PAIR_TYPE();
		return null;
	}
	
	@Override public T visitExpr_array_elem(@NotNull WaccParser.Expr_array_elemContext ctx) {
		visit(ctx.array_elem().ident());

		ARRAY_TYPE ar = (ARRAY_TYPE) ctx.array_elem().ident().typename;
		ctx.typename = ar.TYPE();
		return null;
	}
	
	@Override public T visitExpr_binary(@NotNull WaccParser.Expr_binaryContext ctx) { 
		if (prints) System.out.println("visitExpr_binary");
		visit(ctx.bin_bool());
		ctx.typename = ctx.bin_bool().returntype;
		return null;
	}
	
	@Override public T visitExpr_bin_bool_bool(@NotNull WaccParser.Expr_bin_bool_boolContext ctx) {
		if (prints) System.out.println("visitExpr_bin_bool_bool");
		visit(ctx.bin_bool(0));
		visit(ctx.bin_bool(1));
		ctx.returntype = new BOOL();
		ctx.argtype = new BOOL();
		if(!SharedMethods.assignCompat(ctx.bin_bool(0).returntype, ctx.argtype)) {
			System.exit(200);
		}
		if(!SharedMethods.assignCompat(ctx.bin_bool(1).returntype, ctx.argtype)) {
			System.exit(200);
		}
		return null; 
	}
	
	@Override 
	public T visitExpr_bin_bool_math_eq(@NotNull WaccParser.Expr_bin_bool_math_eqContext ctx) { 
		if (prints) System.out.println("visitExpr_bin_bool_math");
		visit(ctx.math(0));
		visit(ctx.math(1));

		ctx.returntype = new BOOL();
		ctx.argtype = new EQUALITY();
		
		if(!SharedMethods.assignCompat(ctx.math(0).returntype, ctx.math(1).returntype)) {
			System.exit(200);
		}

		if(!ctx.argtype.getClass().isAssignableFrom(ctx.math(0).returntype.getClass())) {

			System.exit(200);
		}
		return null; 
	}

	@Override 
	public T visitExpr_bin_bool_math_moreless(@NotNull WaccParser.Expr_bin_bool_math_morelessContext ctx) { 
		if (prints) System.out.println("visitExpr_bin_bool_math");
		visit(ctx.math(0));
		visit(ctx.math(1));

		if(ctx.math(0).returntype instanceof PAIR_TYPE || 
				ctx.math(0).returntype instanceof ARRAY_TYPE ||
				ctx.math(1).returntype instanceof PAIR_TYPE ||
				ctx.math(1).returntype instanceof ARRAY_TYPE) {
			System.exit(200);
		}
				
		
		ctx.returntype = new BOOL();
		ctx.argtype = new EQUALITY();
		
		if(!SharedMethods.assignCompat(ctx.math(0).returntype, ctx.math(1).returntype)) {
			System.exit(200);
		}

		if(!ctx.argtype.getClass().isAssignableFrom(ctx.math(0).returntype.getClass())) {

			System.exit(200);
		}
		return null; 
	}

	
	@Override public T visitExpr_bin_math(@NotNull WaccParser.Expr_bin_mathContext ctx) {
		if (prints) System.out.println("visitExpr_bin_math");
		visit(ctx.math());
		ctx.returntype = ctx.math().returntype;
		return null; 
	}
	
	@Override public T visitExpr_bin_math_math(@NotNull WaccParser.Expr_bin_math_mathContext ctx) {
		if (prints) System.out.println("visitExpr_bin_math_math");
		visit(ctx.math(0));
		visit(ctx.math(1));
		ctx.returntype = new INT();
		ctx.argtype = new INT();
		if(!SharedMethods.assignCompat(ctx.math(0).returntype, ctx.argtype)) {
			System.exit(200);
		}
		if(!SharedMethods.assignCompat(ctx.math(1).returntype, ctx.argtype)) {
			System.exit(200);
		}
		return null; 
	}
	
	@Override public T visitExpr_bin_math_atom(@NotNull WaccParser.Expr_bin_math_atomContext ctx) {
		if (prints) System.out.println("visitExpr_bin_math_atom");
		visit(ctx.atom(0));
		visit(ctx.atom(1));
		ctx.returntype = new INT();
		ctx.argtype = new INT();
		if(!SharedMethods.assignCompat(ctx.atom(0).typename, ctx.argtype)) {
			System.exit(200);
		}
		if (prints) System.out.println("HERE: " + ctx.atom(1).typename);
		if(!SharedMethods.assignCompat(ctx.atom(1).typename, ctx.argtype)) {
			System.exit(200);
		}
		return null; 
	}
	
	@Override public T visitExpr_bin_atom(@NotNull WaccParser.Expr_bin_atomContext ctx) {
		if (prints) System.out.println("visitExpr_bin_atom");
		visit(ctx.atom());
		ctx.returntype = ctx.atom().typename;
		return null; 
	}
	
	@Override public T visitAtom_int(@NotNull WaccParser.Atom_intContext ctx) {
		visit(ctx.int_liter());
		ctx.typename = new INT();
		return null;
	}
	
	@Override public T visitAtom_char(@NotNull WaccParser.Atom_charContext ctx) {
		ctx.typename = new CHAR();
		return null;
	}
	
	@Override public T visitAtom_bool(@NotNull WaccParser.Atom_boolContext ctx) {
		ctx.typename = new BOOL();
		return null;
	}
	
	@Override public T visitAtom_ident(@NotNull WaccParser.Atom_identContext ctx) {
		visit(ctx.ident());
		ctx.typename = ctx.ident().typename;
		return null;
	}
	
	@Override public T visitAtom_brackets(@NotNull WaccParser.Atom_bracketsContext ctx) {
		visit(ctx.expr());
		ctx.typename = ctx.expr().typename;
		return null;
	}
	
	
	
	@Override public T visitExpr_unary(@NotNull WaccParser.Expr_unaryContext ctx) {
		if (prints) System.out.println("visitExpr_unary");
		visit(ctx.unary_oper());
		visit(ctx.expr());
		if(ctx.expr().typename == null) {
			System.exit(200);
		}

		if(!((ctx.unary_oper().argtype.getClass()) == (ctx.expr().typename.getClass()))){
			System.exit(200);
		}
	
		ctx.typename = ctx.unary_oper().returntype;
		return null;
	}
	
	@Override public T visitUnary_not(@NotNull WaccParser.Unary_notContext ctx) { 
		if (prints) System.out.println("Unary_not");
		ctx.argtype = new BOOL(); ctx.returntype = new BOOL(); return null; }
	
	@Override public T visitUnary_minus(@NotNull WaccParser.Unary_minusContext ctx) { 
		if (prints) System.out.println("Unary_minus");
		ctx.argtype = new INT(); ctx.returntype = new INT(); return null; }
	
	@Override public T visitUnary_len(@NotNull WaccParser.Unary_lenContext ctx) {
		if (prints) System.out.println("Unary_len");
		if (prints) System.out.println("argtype before " + ctx.argtype);
		ctx.argtype = new ARRAY_TYPE(new NULL()); ctx.returntype = new INT(); return null; }
	
	@Override public T visitUnary_chr(@NotNull WaccParser.Unary_chrContext ctx) { 
		if (prints) System.out.println("Unary_chr");
		ctx.argtype = new INT(); ctx.returntype = new CHAR(); return null; }
	
	@Override public T visitUnary_ord(@NotNull WaccParser.Unary_ordContext ctx) { 
		if (prints) System.out.println("Unary_ord");
		ctx.argtype = new CHAR(); ctx.returntype = new INT(); return null; }

	@Override public T visitExpr_brackets(@NotNull WaccParser.Expr_bracketsContext ctx) {
		if (prints) System.out.println("Unary_brackets");
		visit(ctx.expr());
		ctx.typename = ctx.expr().typename;
		return null;
	}

}
