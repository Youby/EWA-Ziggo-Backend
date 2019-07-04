package hva.ewa.rest.config;

import javax.servlet.annotation.WebFilter;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * The application config
 *
 * @author Emre Efe
 */
@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
@ApplicationPath("services/rest")
public class App extends Application {

    public App() {

    }



}
