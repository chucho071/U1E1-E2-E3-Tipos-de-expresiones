
package com.mycompany.u1e1prolog;

import java.util.Scanner;
import java.util.Stack;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author chuch
 */
public class Operaciones {
    Scanner leer=new Scanner(System.in);
    Stack fin=new Stack();
    Stack temp=new Stack();
    boolean[] p={true,true,false,false};
    boolean[] q={true,false,true,false};
    float res;
    
        //Infija a postfija
    public void opc1(){
        /*
        Primera opcion ( 6 + 2 ) * 3 / 2 ^ 2 - 4
        Segunda opcion ( 9 + 22 ) * 1 / 4 ^ 2 - 2
        ( 9 + 19 ) * 2 / 66 ^ 4 - 76
        */
        String cadena="( 9 + 19 ) * 2 / 66 ^ 4 - 76";
        
        System.out.println("\nCambio de infija a postfija la cadena: "+cadena);
        String[] cadsep=cadena.split(" ");
        for (int i = 0; i < cadsep.length; i++) {
            //if(Character.isDigit(cadena.charAt(i))){
            if(StringUtils.isNumeric(cadsep[i])){
                fin.add(cadsep[i]);
            }
            else{
                if(cadsep[i].contains(".")){
                    fin.add(cadsep[i]);
                }
                precedencia(cadsep[i]);
            }
            if(i==(cadena.length()-1)){
                while(!temp.isEmpty()){
                    String viejo=(String) temp.peek();
                    fin.add(viejo);
                    temp.pop();
                }
            }
        }
        fin.add(temp.pop());
        System.out.println(fin.toString()+"\n");
        //System.out.println(temp.toString()+"\n");
        
        for (int i = 0; i < fin.size(); i++) {
            temp.push(String.valueOf(fin.get(i)));
        }
        //System.out.println("\n"+temp.toString()+"\n");
        String t1;
        String t2;
        String t3;
        while(fin.size()>3) {
            for (int i = 0; i < fin.size(); i++) {
                t1=(String)fin.get(i);
                t2=(String)fin.get(i+1);
                t3=(String)fin.get(i+2);
                if ((StringUtils.isNumeric((String)fin.get(i))||t1.contains("."))
                        &&(StringUtils.isNumeric((String)fin.get(i+1))||t2.contains("."))
                        &&(!StringUtils.isNumeric((String)fin.get(i+2))
                        )) {
                    swires(i);
                }
                else{
                    i++;
                    swires(i);
                }
                break;
            }
        }
        //System.out.println("Termina while>3");
        swires(0);
        System.out.println("Resultado: "+temp.toString()+"\n");
        fin.removeAllElements();
        temp.removeAllElements();
    }
    
    public void precedencia(String nuevo){
        if(!"(".equals(nuevo)){
            if(temp.isEmpty()) {
                temp.add(nuevo);
            }//Comprobacion suma y resta
            else if("+".equals(nuevo)||"-".equals(nuevo)){
                SR(nuevo);
            }//Comprobacion multi y div
            else if("*".equals(nuevo)||"/".equals(nuevo)){
                MD(nuevo);
            }//Comprobacion potencia
            else if("^".equals(nuevo)){
                Pot(nuevo);
            }//Comprobacion cierra parentesis 
            else if(")".equals(nuevo)){
                String viejo;
                while(!temp.isEmpty()){
                    viejo=(String) temp.peek();
                    fin.add(viejo);
                    temp.pop();
                }
            }
        }
    }
    
    public void SR(String nuevo){
        String viejo=(String) temp.peek();
        if("+".equals(viejo)||"-".equals(viejo)){
            fin.add(viejo);
            temp.pop();
            temp.push(nuevo);
        }
        else if("*".equals(viejo)||"/".equals(viejo)||"^".equals(viejo)){
            while(!temp.isEmpty()){
                viejo=(String) temp.peek();
                fin.add(viejo);
                temp.pop();
            }
            temp.push(nuevo);
        }
    }
    
    public void MD(String nuevo){
        String viejo=(String) temp.peek();
        if("*".equals(viejo)||"/".equals(viejo)||"^".equals(viejo)){
            fin.add(viejo);
            temp.pop();
            temp.push(nuevo);
        }
        else if("+".equals(viejo)||"-".equals(viejo)){
            temp.push(nuevo);
        }
        /*Quito el siguiente porque se puede anidar con el primero ya que saca lo de la pila
        else if(viejo=='^'){
            fin.add(viejo);
            temp.pop();
            temp.push(nuevo);
        }
        */
    }
    
    public void Pot(String nuevo){
        String viejo=(String) temp.peek();
        if("^".equals(viejo)){
            fin.add(viejo);
            temp.pop();
            temp.push(nuevo);
        }
        else if("+".equals(viejo)||"-".equals(viejo)||"*".equals(viejo)||"/".equals(viejo)){
            temp.push(nuevo);
        }
    }
    
    public void swires(int i){
        double res=0;
        switch ((String)fin.get(i+2)){
            case "+":
                res=Double.parseDouble(String.valueOf(temp.remove(i)));
                fin.remove(i);
                res=res+Double.parseDouble(String.valueOf(temp.remove(i)));
                fin.remove(i);
                temp.remove(i);
                fin.remove(i);
                fin.add(i, "0");
                temp.add(i, String.valueOf(res));
                System.out.println("\n"+temp.toString()+"\n");
                break;
            case "-":
                res=Double.parseDouble(String.valueOf(temp.remove(i)));
                fin.remove(i);
                res=res-Double.parseDouble(String.valueOf(temp.remove(i)));
                fin.remove(i);
                temp.remove(i);
                fin.remove(i);
                fin.add(i, "0");
                temp.add(i, String.valueOf(res));
                System.out.println("\n"+temp.toString()+"\n");
                break;
            case "*":
                res=Double.parseDouble(String.valueOf(temp.remove(i)));
                fin.remove(i);
                res=res*Double.parseDouble(String.valueOf(temp.remove(i)));
                fin.remove(i);
                temp.remove(i);
                fin.remove(i);
                fin.add(i, "0");
                temp.add(i, String.valueOf(res));
                System.out.println("\n"+temp.toString()+"\n");
                break;
            case "/":
                res=Double.parseDouble(String.valueOf(temp.remove(i)));
                fin.remove(i);
                res=res/Double.parseDouble(String.valueOf(temp.remove(i)));
                fin.remove(i);
                temp.remove(i);
                fin.remove(i);
                fin.add(i, "0");
                temp.add(i, String.valueOf(res));
                System.out.println("\n"+temp.toString()+"\n");
                break;
            case "^":
                res=Double.parseDouble(String.valueOf(temp.remove(i)));
                fin.remove(i);
                res=Math.pow(res, Double.parseDouble(String.valueOf(temp.remove(i))));
                fin.remove(i);
                temp.remove(i);
                fin.remove(i);
                fin.add(i, "0");
                temp.add(i, String.valueOf(res));
                System.out.println("\n"+temp.toString()+"\n");
                break;
            default:
                System.out.println("no se encontro");
                i++;
                swires(i);
                break;
        }
    }

    
    
    //logica
    public void opc2(){
        System.out.println("Solución de expresión lógica[(p->q)^q]->q");
        String cadena="[(p->q)^q]->q";
        Stack var=new Stack();
        
        /*
        [(p->q)^q]->q solucion: v, v, v, v
         -> falso solo cuando es v f
        v  v   v
        v  f   f
        f  v   v
        f  f   v
        <-> verdadero cuando son iguales
        v  v   v
        v  f   f
        f  v   f
        f  f   v
        */
    
        for (int i = 0; i < cadena.length(); i++) {
            switch (cadena.charAt(i)) {
                case '-':
                    if(cadena.charAt(i+1)=='>'){
                        fin.add(cadena.charAt(i)+""+cadena.charAt(i+1)+"");
                        i++;
                    }
                    else{
                        System.out.println("expresion incorrecta");
                        System.exit(0);
                    }   break;
                case '<':
                    if(cadena.charAt(i+1)=='-'){
                        if(cadena.charAt(i+2)=='>'){
                            fin.add(cadena.charAt(i)+""+cadena.charAt(i+1)+""+cadena.charAt(i+2)+"");
                            i++;
                            i++;
                        }
                        else{
                            System.out.println("expresion incorrecta");
                            System.exit(0);
                        }
                    }
                    else{
                        System.out.println("expresion incorrecta");
                        System.exit(0);
                    }   break;
                default:
                    if(Character.isLetter(cadena.charAt(i))){
                        if(!var.contains(cadena.charAt(i))){
                            var.add(cadena.charAt(i));
                        }
                        fin.add(var.peek());
                               // var.contains(cadena.charAt(i)));
                                //System.out.println("en pila al añadir a variable "+fin.toString());

                    }
                    else{
                        fin.add(cadena.charAt(i)+"");
                                //System.out.println("en pila sin añadir a var"+fin.toString());

                    }
                    break;
            }
        }
        System.out.println("en pila "+fin.toString());
        System.out.println("variables "+var.toString());
        cicloexpresion(0);
        System.out.println("\nResultado:");
        for (int i = 0; i < 4; i++) {
            System.out.println(p[i]);
        }
        fin.removeAllElements();
        temp.removeAllElements();
    }
    
    public void cicloexpresion(int j){
        for (int i = j; i < fin.size(); i++) {
            if(fin.get(i).equals("(")||fin.get(i).equals("[")){
                fin.remove(i);
                    //System.out.println("en pila "+fin.toString());
                cicloexpresion(0);
            }
            else if(fin.get(i).equals(")")||fin.get(i).equals("]")){
                fin.remove(i);
                    //System.out.println("en pila "+fin.toString());
                cicloexpresion(0);
            }
            else if(fin.get(i).equals("¬")||fin.get(i).equals("/")){
                fin.remove(i);
                        //System.out.println("en pila "+fin.toString());
                if(fin.get(i).equals("(")){
                    cicloexpresion(i);
                }
                else{
                    for (int k = 0; k < q.length; k++) {
                        q[i]=!q[i];
                    }
                }
            }
            else if(fin.get(i).equals("^")){
                /*if(fin.get(i).equals("(")){
                    cicloexpresion(i);
                }
                else{*/
                    for (int k = 0; k < 4; k++) {
                        if(p[k]==true&&q[k]==true){
                            p[k]=true;
                        }
                        else{
                            p[k]=false;
                        }
                        System.out.println("^"+p[k]);
                    }
                    fin.remove(i);
                    fin.remove(i);
                        //System.out.println("en pila "+fin.toString());
                        cicloexpresion(0);


                //}
            }
            else if(fin.get(i).equals("->")){
                /*if(fin.get(i).equals("(")){
                    cicloexpresion(i);
                }
                else{*/
                    for (int k = 0; k < 4; k++) {
                        if(p[k]==true&&q[k]==false){
                            p[k]=false;
                        }
                        else{
                            p[k]=true;
                        }
                        System.out.println("->"+p[k]);
                    }
                    fin.remove(i);
                    fin.remove(i);
                                //System.out.println("en pila "+fin.toString());
                                cicloexpresion(0);

                //}
            }
        }
    }
    
    
    //Separar palabras
    public void opc3(){
        System.out.println("\nSeparar cadena");
        /*
        Separar la cadena dada por comas o espacios por numero, caracter o cadena y especificar cuales y cuantos
        */
        String cadena="( .9 + 8 ) * 1 / 5^2 - 76";
        System.out.println("La cadena a separar es "+cadena);
        String[] cadsep=cadena.split(" ");
        Stack num=new Stack();
        Stack carac=new Stack();
        Stack cade=new Stack();
        int tnum=0;
        int tcarac=0;
        int tcade=0;
        for (int i = 0; i < cadsep.length; i++) {
            if(StringUtils.isNumeric(cadsep[i])){
                tnum++;
                num.add(cadsep[i]);
            }
            else if(cadsep[i].length()==1){
                tcarac++;
                carac.add(cadsep[i]);
            }
            else{
                tcade++;
                cade.add(cadsep[i]);
            }
        }
        System.out.println("Hay "+tnum+" numeros y son "+num.toString());
        System.out.println("Hay "+tcarac+" caracteres y son "+carac.toString());
        System.out.println("Hay "+tcade+" cadenas y son "+cade.toString()+"\n");
    }
    
}
