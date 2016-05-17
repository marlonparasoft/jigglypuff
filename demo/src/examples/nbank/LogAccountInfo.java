/*
 * (C) Copyright Parasoft Corporation Jun 23, 2005. All rights reserved. THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Parasoft The copyright notice
 * above does not evidence any actual or intended publication of such source code.
 */

package examples.nbank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * LogStatement
 * 
 * @author Gina
 */
public class LogAccountInfo
{
    /**
     * @author Gina
     */
    public boolean Log(Account account)
        throws IOException
    {
        int balance = account.getBalance();
        String id = account.getID();
        File file = new File("account" + id);
        BufferedWriter out;
        out = new BufferedWriter(new FileWriter(file));
        out.write(id);
        out.write(balance);
        return true;
    }

}
