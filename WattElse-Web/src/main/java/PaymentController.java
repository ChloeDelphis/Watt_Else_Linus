import fr.eql.ai116.linus.wattelse.business.ConnectionBusiness;
import fr.eql.ai116.linus.wattelse.business.PayementBusiness;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/pay")
public class PaymentController {

    @EJB
    PayementBusiness payementBusiness;

    @GET
    @Path("/payment/{id}/start")
    @Produces(MediaType.APPLICATION_JSON)
    public Response paymentDue(@PathParam("id") long tarificationId) {
        payementBusiness.getTarification(tarificationId);
        return Response.ok("oui").build();
    }
}
