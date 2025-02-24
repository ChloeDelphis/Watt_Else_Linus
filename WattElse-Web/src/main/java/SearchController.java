import fr.eql.ai116.linus.wattelse.business.CalendarBusiness;
import fr.eql.ai116.linus.wattelse.business.ReservationBusiness;
import fr.eql.ai116.linus.wattelse.business.SearchBusiness;
import fr.eql.ai116.linus.wattelse.entity.dto.RegisterReservationInDto;
import fr.eql.ai116.linus.wattelse.entity.dto.StationDetailsDto;
import fr.eql.ai116.linus.wattelse.entity.dto.StationOutDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.Calendar;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import fr.eql.ai116.linus.wattelse.entity.pojo.Vehicle;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Stateless
@Path("/search")
public class SearchController {

    @EJB
    SearchBusiness searchBusiness;

    @EJB
    ReservationBusiness reservationBusiness;

    @EJB
    CalendarBusiness calendarBusiness;

    // Not used yet
    @GET
    @Path("/sockets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchSockets() {
        List<Socket> sockets = searchBusiness.getAllSockets();
        return Response.ok(sockets).build();
    }

    @GET
    @Path("/vehicles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchVehiclesByUser(@PathParam("id") long id) {
        List<Vehicle> vehicles = searchBusiness.getVehiclesByUser(id);
        if (vehicles == null || vehicles.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No vehicles found for user with ID: " + id)
                    .build();
        }
        return Response.ok(vehicles).build();
    }


// `${backUrl}/cs/${socketId}/${dptSelected}/onlyAvailableNow=${onlyAvailableNow}`,

    @GET
    @Path("/cs/{idSocket}/{nDpt}/onlyAvailableNow={onlyAvailableNow}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchCSbySocketAndDptAndAvailability(
            @PathParam("idSocket") long idSocket,
            @PathParam("nDpt") String nDpt,
            @PathParam("onlyAvailableNow") boolean onlyAvailableNow) {
        List<StationOutDto> stations = searchBusiness.getStationsBySocketAndDptAndAvailability(idSocket, nDpt, onlyAvailableNow);
        if (stations == null || stations.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No station found for user for socket ID " + idSocket +
                            " and dpt ID " + nDpt + " and onlyAvailableNow = "+onlyAvailableNow)
                    .build();
        }
        return Response.ok(stations).build();
    }




//       `${backUrl}/cs/${socketId}/${cityId}/${range}/${orderByCriterium}/${onlyAvailableNow}`
    @GET
    @Path("/cs/{idSocket}/{cityId}/{range}/{orderByCriterium}/onlyAvailableNow={onlyAvailableNow}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchCSbySocketCityZIPLatLongRangeAvailability(
            @PathParam("idSocket") long idSocket,
            @PathParam("cityId") String cityId,
            @PathParam("range") int range,
            @PathParam("orderByCriterium") String orderByCriterium,
            @PathParam("onlyAvailableNow") boolean onlyAvailableNow) {

        List<StationOutDto> stations = searchBusiness.getCSbySocketCityRangeAvailability(
                idSocket, cityId , range, orderByCriterium, onlyAvailableNow);

        if (stations == null || stations.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No station found for user for socket ID " + idSocket +
                            " and dpt city " + cityId + " and onlyAvailableNow = "+onlyAvailableNow)
                    .build();
        }
        return Response.ok(stations).build();
    }

    @GET
    @Path("/station/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchStation(@PathParam("id") long stationId) {
        StationDetailsDto station = searchBusiness.getStationDetails(stationId);
        System.out.println(station);
        if (station == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No station found with ID " + stationId).build();
        }
        return Response.ok(station).build();
    }

    @POST
    @Path("/station/reservate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response reservate(RegisterReservationInDto dto_in) {
        reservationBusiness.registerNewReservation(dto_in.getToken(), dto_in.getIdVehicule(), dto_in.getIdStation(), LocalDate.parse(dto_in.getDateReservation()), dto_in.getIdHeureDebutReservation(), dto_in.getIdMinuteDebutReservation(), dto_in.getIdHeureFinReservation(), dto_in.getIdMinuteFinReservation());
        return Response.ok().build();
    }

    @GET
    @Path("/station/{id}/calendar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCalendar(@PathParam("id") long stationId) {
        Calendar calendar = calendarBusiness.getStationCalendar(stationId);
        return Response.ok(calendar).build();
    }

}
