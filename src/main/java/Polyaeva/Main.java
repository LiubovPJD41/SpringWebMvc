package Polyaeva;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.nio.file.Files;

public class Main {
    private static final String DIR_NAME = "tomcat";
    private static final int PORT = 9999;
    private static final String BASE = ".";
    private static final String CONTEX_PATH = "";
    public static void main(String[] args) throws LifecycleException, IOException {
        final var tomcat = new Tomcat();
        final var baseDir = Files.createTempDirectory(DIR_NAME);
        baseDir.toFile().deleteOnExit();
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        final var connector = new Connector();
        connector.setPort(PORT);
        tomcat.setConnector(connector);

        tomcat.getHost().setAppBase(BASE);
        tomcat.addWebapp(CONTEX_PATH, BASE);

        tomcat.start();
        tomcat.getServer().await();
    }
}