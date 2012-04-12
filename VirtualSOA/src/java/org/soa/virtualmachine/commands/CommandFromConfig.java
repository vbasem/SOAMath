/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.soa.virtualmachine.config.SettingsRepository;

/**
 *
 * @author Basem
 */
public class CommandFromConfig implements IVirtualMachineCommand
{
    private IVirtualMachineUser commandUser;
    private ArrayList<String> fullCommand;
    private final String COMMAND_ID_PREFIX = "command_";

    public CommandFromConfig(String commandIdentifier)
    {
        String stringOfCommandFromConfig = SettingsRepository
                .getCommands()
                .getProperty(COMMAND_ID_PREFIX + commandIdentifier);
        fullCommand = new ArrayList<String>(Arrays.asList(stringOfCommandFromConfig.split(" ")));
        commandUser = new UserFromConfig();

    }

    @Override
    public String getCommand()
    {
        return fullCommand.get(0);
    }

    @Override
    public List<String> getCommandArguments()
    {
        return fullCommand.subList(1, fullCommand.size());
    }

    @Override
    public IVirtualMachineUser getUser()
    {
        return commandUser;
    }
}
