/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine;

/**
 *
 * @author Basem
 */
public class VmConsoleAdmin
{
    public static void main(String[] args)
    {
        VirtualMachineClient client = new VirtualMachineClient();
        System.out.println("trying");
        client.stopVm("arithmatic1");
    }
}
