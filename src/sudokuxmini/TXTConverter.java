package sudokuxmini;

import java.io.*;
import java.util.*;

public class TXTConverter {
    public TXTConverter(String fileinput){
        try {
                Scanner filein =  new Scanner(new FileInputStream(fileinput));
                FileWriter fileout = new FileWriter(new File("CLIPS//puzzles//out.clp"));

                //clp standard
                fileout.write("(defrule grid-values\n\n");
                fileout.write("	?f <- (phase grid-values)\n\n");
                fileout.write("	=> \n\n");
                fileout.write("	(retract ?f)\n\n");
                fileout.write("	(assert (phase expand-any))\n\n");
                fileout.write("	(assert (size 3))\n\n");			

                int i = 1;

                while(filein.hasNext()) {

                    String s=filein.nextLine();
                    int j = 0;

                    while(j!=s.length())
                    {
                        if(s.charAt(j)!=' ')
                        {
                            int row=0; int column=0; int group=0; int diagonal=0;

                            if(i <= 6)
                                    row = 1;
                            if(i > 6 && i <=12)
                                    row = 2;
                            if(i >= 13 && i <= 18)
                                    row = 3;
                            if(i >= 19 && i <= 24)
                                    row = 4;
                            if(i >= 25 && i <= 30)
                                    row = 5;
                            if(i >= 31 && i <= 36)
                                    row = 6;

                            if(i % 6 != 0)
                                    column = i%6;
                            else
                                    column = 6;

                            if(i >= 1 && i <= 3 || i >=7&& i<=9)
                                    group = 1;
                            if(i >= 4 && i <= 6 || i >=10&& i<=12)
                                    group = 2;
                            if(i >= 13 && i <= 15 || i >=19&& i<=21)
                                    group = 3;
                            if(i >= 16 && i <= 18 || i >=22&& i<=24)
                                    group = 4;
                            if(i >= 25 && i <= 27 || i >=31&& i<=33)
                                    group = 5;
                            if(i >= 28 && i <= 30 || i >=34&& i<=36)
                                    group = 6;

                            if(i == 1 || i == 8 || i == 15 || i == 22 || i == 29 || i == 36)
                            diagonal = 1;
                            else
                            {
                                if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26 || i == 31)
                                    diagonal = 2;
                                else diagonal = 0;
                            }	

                            if(s.charAt(j) !='*')
                            {
                                fileout.write("(assert (possible (row " + row + ") (column " + column + ") (value " + s.charAt(j) + ") (group " + group + ") (diagonal " + diagonal + ") (id " + i + ")))\n");
                                i++;
                            }
                            else
                            {
                                fileout.write("(assert (possible (row " + row + ") (column " + column + ") (value any) (group " + group + ") (diagonal " + diagonal + ") (id " + i + ")))\n");
                                i++;
                            }

                        }
                        j++;
                    }				
                }
                fileout.write(")");
                fileout.close();
            }
        catch (IOException ioe)
        {
                System.out.println("tidak ada file");
        }
    } 
}
