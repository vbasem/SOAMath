/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine.commands;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Basem
 */
public interface IVirtualMachineCommand
{
    public String getCommand();
    public List<String> getCommandArguments();
    public IVirtualMachineUser getUser();
}
