
import fr.eql.ai116.linus.wattelse.business.SocketBusiness;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/socket")
public class SocketController {

    @EJB
    SocketBusiness socketBusiness;

    @GET
    @Path("/fetchsockets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchSockets() {
        List<Socket> sockets = socketBusiness.fetchSockets();
        return Response.ok(sockets).build();
    }
}
