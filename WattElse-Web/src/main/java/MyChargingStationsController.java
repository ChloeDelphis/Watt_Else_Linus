import fr.eql.ai116.linus.wattelse.business.StationBusiness;
import fr.eql.ai116.linus.wattelse.business.exceptions.StationRegistrationException;
import fr.eql.ai116.linus.wattelse.entity.dto.MyChargingStationOutDto;
import fr.eql.ai116.linus.wattelse.entity.dto.RegisterStationInDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/myChargingStations")
public class MyChargingStationsController {

    @EJB
    StationBusiness stationBusiness;

    @POST
    @Path("/register/station")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerStation(RegisterStationInDto dto_in) {
        System.out.println("Bien rentré dans le back");
        try {
            stationBusiness.registerNewStation(dto_in);
            // stationBusiness.registerNewStation(dto_in.getName(), dto_in.getUserId(), dto_in.getTarificationId(), dto_in.getSocketId(), dto_in.getPowerId(), dto_in.getLatitude(), dto_in.getLongitude(), dto_in.getAddressDisplay());
            return Response.ok().build();
        } catch (StationRegistrationException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Path("/user/{id}/stations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchUserStations(@PathParam("id") long userId) {
        List<MyChargingStationOutDto> stations = stationBusiness.fetchUserStations(userId);
        return Response.ok(stations).build();
    }
}
