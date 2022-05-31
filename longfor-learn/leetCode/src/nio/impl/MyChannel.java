package nio.impl;

import java.io.IOException;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public abstract class MyChannel {

    public static MyChannel open(Path path, OpenOption... options)
            throws IOException
    {

        return null;
    }

    public abstract void transferFrom();
}
