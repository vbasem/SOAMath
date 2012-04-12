/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine.commands;

import org.soa.virtualmachine.config.SettingsRepository;

/**
 *
 * @author Basem
 */
public class UserFromConfig implements IVirtualMachineUser
{
    private String userName;
    private String pass;
    
    public UserFromConfig()
    {
        userName = SettingsRepository.getCommands().getProperty("user_for_commands");
        pass = SettingsRepository.getCommands().getProperty("pass_for_commands");
    }

    @Override
    public String getUserName()
    {
        return userName;
    }

    @Override
    public String getUserPassword()
    {
        return pass;
    }
    
}
