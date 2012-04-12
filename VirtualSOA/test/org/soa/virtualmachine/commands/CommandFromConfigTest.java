/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine.commands;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Basem
 */
public class CommandFromConfigTest
{

    public CommandFromConfigTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void getCommand_definedTestCommandId_returnsCommand()
    {
        String expectedResult = "/test/command";
        IVirtualMachineCommand com = new CommandFromConfig("test");

        assertEquals("command should be as specified in commands file", expectedResult , com.getCommand());
    }

    @Test
    public void getGommandArguments_definedTestCommandId_returnsArrayOfArgs()
    {
        ArrayList<String> expectedArguments = new ArrayList<String>();
        expectedArguments.add("param1");
        expectedArguments.add("--d=param2");
        expectedArguments.add("-param3=%s");
        
        IVirtualMachineCommand com = new CommandFromConfig("test");

        assertEquals("arguments should be as specified in commands file", expectedArguments, com.getCommandArguments());
    }
    
    @Test
    public void getUser_definedCommand_returnsUserObject()
    {
        IVirtualMachineCommand com = new CommandFromConfig("test");
        
        assertEquals("basemv", com.getUser().getUserName());
        assertEquals("basemv", com.getUser().getUserPassword());
    }
    
    @Test
    public void getCommandArguments_afterAddingAdditionalArgumentsManually_returnsNewArgsAsWell()
    {
        ArrayList<String> expectedArguments = new ArrayList<String>();
        expectedArguments.add("param1");
        expectedArguments.add("--d=param2");
        expectedArguments.add("-param3=%s");
        expectedArguments.add("manuallyAddedArg");
        
        IVirtualMachineCommand com = new CommandFromConfig("test");
        com.getCommandArguments().add("manuallyAddedArg");
        
        assertEquals("arguments should contain the manually added arg", expectedArguments, com.getCommandArguments());
    }
}
