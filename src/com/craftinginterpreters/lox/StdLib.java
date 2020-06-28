package com.craftinginterpreters.lox;

import java.util.List;

public class StdLib {

    public static void defineStdLib(Environment env) {
        defineFunction(env, "clock", new LoxCallable(){
            @Override
            public int arity() {
                return 0;
            }

            @Override
            public Object call(Interpreter interpreter, List<Object> arguments) {
                return (double) System.currentTimeMillis() / 1000.0;
            }

            @Override
            public String toString() {
                return "<native fn>";
            }
        });
        defineFunction(env, "round", new LoxCallable(){
        
            @Override
            public Object call(Interpreter interpreter, List<Object> arguments) {
                try {
                    Double arg = (double) arguments.get(0);
                    Double result = (double) Math.round(arg);
                    return result;
                } catch (Exception e) {}
                return null;
            }
        
            @Override
            public int arity() {
                return 1;
            }
        });
    }
    
    public static void defineFunction(Environment env, String name, LoxCallable function) {
        env.define(name, function);
    }
}