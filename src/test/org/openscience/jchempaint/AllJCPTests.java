package org.openscience.jchempaint;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(value=Suite.class)
@SuiteClasses( value = { 
        JCPEditorAppletMenuTest.class,
        JCPEditorAppletDrawingTest.class,
        JCPEditorAppletBugsTest.class,
        JCPEditorAppletUndoRedoTest.class,
        BugSF65Test.class,
        BugSF75Test.class,
        SetSmilesTest.class
        } )
public class AllJCPTests {

}
