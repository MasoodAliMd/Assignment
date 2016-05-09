package com.GoJek.DTO;

/**
 * Created by masooda on 08/05/16.
 */
public class SearchCriteria {
    private CommandLineUtility.Command command;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CommandLineUtility.Command getCommand() {
        return command;
    }

    public void setCommand(CommandLineUtility.Command command) {
        this.command = command;
    }

    private String value;
}
