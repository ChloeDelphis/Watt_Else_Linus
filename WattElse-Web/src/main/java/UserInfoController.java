import fr.eql.ai116.linus.wattelse.business.SearchBusiness;
import fr.eql.ai116.linus.wattelse.business.UserInfoBusiness;
import fr.eql.ai116.linus.wattelse.entity.dto.UserOutDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.Vehicle;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/user/info")
public class UserInfoController {

    @EJB
    UserInfoBusiness userInfoBusiness;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchUserInfoById(@PathParam("id") long id) {
        UserOutDto user = userInfoBusiness.getUserInfoById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No was found for id: " + id)
                    .build();
        }
        return Response.ok(user).build();
    }
}
