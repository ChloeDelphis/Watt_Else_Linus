import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import fr.eql.ai116.linus.wattelse.business.exceptions.AuthorizationException;
import fr.eql.ai116.linus.wattelse.business.SecurityBusiness;
import fr.eql.ai116.linus.wattelse.entity.Role;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.regex.Pattern;

@Provider
public class SecurityFilter implements ContainerRequestFilter, ContainerResponseFilter, InjectableProvider<EJB, Type> {

    final String HEADERS = "Origin, Content-Type, Accept, Authorization";
    final String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    final String ALLOW_HEADERS = "Access-Control-Allow-Headers";
    final String ALLOW_METHODS = "Access-Control-Allow-Methods";

    @EJB
    private SecurityBusiness securityBusiness;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String resource = requestContext.getUriInfo().getPath();
        String authorisation = requestContext.getHeaders().getFirst("Authorization");
        try {
            if ( /// à mettre ici les liens pour lequels il faut être connecté
                    Pattern.compile("/socket/.*").matcher(resource).find() ||
                            Pattern.compile("/space/.*").matcher(resource).find() ||
                            Pattern.compile("/search/.*").matcher(resource).find() ||
                            Pattern.compile("/paymentMethods/.*").matcher(resource).find() ||
                            Pattern.compile("/myChargingStations/.*").matcher(resource).find() ||
                            Pattern.compile("/range/.*").matcher(resource).find() ||
                            Pattern.compile("/pay/.*").matcher(resource).find() ||
                            Pattern.compile("/user/info/.*").matcher(resource).find()
            ) {
                securityBusiness.authorize(authorisation, Role.USER);
            }
            if ( /// à mettre ici les liens pour lequels il faut être connecté en tant qu'admin
                    Pattern.compile("/admin/.*").matcher(resource).find()
            ) {
                securityBusiness.authorize(authorisation, Role.ADMIN);
            }
        } catch (AuthorizationException e) {
            requestContext.abortWith(Response
                    .status(Response.Status.FORBIDDEN)
                    .entity("Access to resource unauthorized.")
                    .build());
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add(ALLOW_ORIGIN, "*");
        responseContext.getHeaders().add(ALLOW_HEADERS, HEADERS);
        responseContext.getHeaders().add(ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }

    @Override
    public ComponentScope getScope() {
        return ComponentScope.Singleton;
    }

    @Override
    public Injectable getInjectable(ComponentContext cc, EJB ejb, Type t) {
        if (!(t instanceof Class)) return null;
        try {
            Class c = (Class)t;
            Context ic = new InitialContext();
            final Object o = ic.lookup(c.getName());
            return (Injectable<Object>) () -> o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}