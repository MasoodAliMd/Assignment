package com.GoJek.DTO;

/**
 * Created by masooda on 08/05/16.
 */
public class OutputHandler {
    private boolean outputToFile;

    public String getOutputStr() {
        return outputStr;
    }

    public void setOutputStr(String outputStr) {
        this.outputStr = outputStr;
    }

    public boolean isOutputToFile() {
        return outputToFile;
    }

    public void setOutputToFile(boolean outputToFile) {
        this.outputToFile = outputToFile;
    }

    private String outputStr = "";

    public void output(String s)
    {
        if(outputToFile)
            outputStr+=s;
        else
            System.out.println(s);
    }
}
