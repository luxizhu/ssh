package common;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.servlet.ServletException;
import java.io.File;

/**
 * Created by caopeihe on 2016-3-23.
 */
public class EmbeddedTomcat {
    private final Log log = LogFactory.getLog(getClass());
    private String PROJECT_PATH = "E:\\project\\ssh";
    private String contextPath = "/";//WEB上下文名称
    private String WEB_APP_PATH = PROJECT_PATH + File.separatorChar +"\\src\\main\\webapp";
    private String CATALINA_HOME = PROJECT_PATH +"\\Embedded\\Tomcat";

    private Tomcat tomcat = new Tomcat();//嵌入式Tomcat
    private int port;
    public EmbeddedTomcat(int port){
        this.port=port;
    }

    /**
     * 启动Tomcat
     */
    public void startup() throws Exception {
        tomcat.setPort(port);
        tomcat.setBaseDir(CATALINA_HOME);
        tomcat.getHost().setAppBase(WEB_APP_PATH);
        try{
            StandardServer server = (StandardServer)tomcat.getServer();
            AprLifecycleListener listener = new AprLifecycleListener();
            server.addLifecycleListener(listener);
            tomcat.addWebapp(contextPath,WEB_APP_PATH);
        } catch (ServletException e){
            e.printStackTrace();
            log.error(e.getMessage());
            throw e;
        }
        try{
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e){
            e.printStackTrace();
            log.error(e.getMessage());
            throw e;
        }
        log.info("Tomcat started");
    }

    /**
     * 停止Tomcat
     */
    public void shutdown() throws Exception {
        try{
            tomcat.stop();
        } catch (LifecycleException e){
            e.printStackTrace();
            log.error(e.getMessage());
            throw e;
        }
        log.info("Tomcat stopped");
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        EmbeddedTomcat embeddedTomcat = new EmbeddedTomcat(6080);
        embeddedTomcat.startup();
    }
}
